package it.softre.thip.archismall.trasmissione;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.json.JSONObject;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.batch.BatchRunnable;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.rs.errors.PantheraApiException;
import com.thera.thermfw.security.Authorizable;

import it.softre.thip.base.archismall.api.BaseArchismallApi;
import it.thera.thip.cs.ColonneFiltri;
import it.thera.thip.cs.ThipException;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 15/05/2024
 * <br><br>
 * <b>71XXX	DSSOF3	15/05/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

public class PacchettoTrasmissioneInvio extends BatchRunnable implements Authorizable {

	protected String iChiaviSelezionati;

	public String getChiaviSelezionati() {
		return iChiaviSelezionati;
	}

	public void setChiaviSelezionati(String iChiaviSelezionati) {
		this.iChiaviSelezionati = iChiaviSelezionati;
	}

	@Override
	protected boolean run() {
		boolean everythingOk = true;
		everythingOk = invaPacchettiVersoArchismall();
		return everythingOk;
	}

	@SuppressWarnings("rawtypes")
	protected boolean invaPacchettiVersoArchismall() {
		boolean result = true;
		output.println(" --> Recupero la lista di pacchetti da inviare... ");
		List<PacchettoTrasmissione> pacchetti = recuperaListaPacchettiDaSelezionati(getChiaviSelezionati());
		if(pacchetti.size() > 0) {
			for (Iterator iterator = pacchetti.iterator(); iterator.hasNext();) {
				PacchettoTrasmissione pacchetto = (PacchettoTrasmissione) iterator.next();
				List<SubmissionPackDett> submissionPackages = pacchetto.submissionPacksDettaglioDaPacchetto();

				//devo pero' raggruppare i dettagli per numero e data fattura
				Map<String, List<SubmissionPackDett>> groupedMap = new HashMap<String, List<SubmissionPackDett>>();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				for (SubmissionPackDett item : submissionPackages) {
					String key = dateFormat.format(item.getDataDoc()) + "-" + item.getNumero();
					List<SubmissionPackDett> group = groupedMap.get(key);
					if (group == null) {
						group = new ArrayList<SubmissionPackDett>();
						groupedMap.put(key, group);
					}
					group.add(item);
				}

				for (Map.Entry<String, List<SubmissionPackDett>> entry : groupedMap.entrySet()) {
					//String key = entry.getKey();
					List<SubmissionPackDett> dettagli = entry.getValue();
					String validPath = null;
					for(SubmissionPackDett submissionPack : dettagli) {
						File file = new File(submissionPack.getNomeFile());
						if(file.exists()) {
							validPath = submissionPack.getNomeFile();
						}else {
							if(validPath != null) {
								//il nome file e' quello del precedente cambiando in notifiche
								int ultimoBackSlash = validPath.lastIndexOf("\\");
								String path = validPath.substring(0,validPath.lastIndexOf("\\"));
								int penultimoBackSlas = path.lastIndexOf("\\");
								String toSubstitute = path.substring(penultimoBackSlas+1, ultimoBackSlash);
								path = path.replace(toSubstitute, "notifiche");
								path += "\\" + submissionPack.getNomeFile();
								file = new File(path);
								if(!file.exists()) {
									//errore
									output.println(" --> File : "+path+", non trovato nel sistema");
								}
								submissionPack.setNomeFile(path);
							}
						}
						String endpoint = submissionPack.getEndpointDaTipoDocumento();
						if(endpoint == null) {
							output.println(" --> Per il Tipo Documento : "+submissionPack.getTipoDoc()+", il sistema non ha determinato se passivo/attivo ");
							continue;
						}
						JSONObject json;
						try {
							json = submissionPack.getJSONVersamento();
							if(endpoint != null && json != null && !json.isEmpty()) {

								String key = KeyHelper.buildObjectKey(new String[] {
										submissionPack.getIdLancio().trim(),
										String.valueOf(submissionPack.getId()),
										(String) json.get("idArchiPro")
								});
								PacchettoInviato pacchettoInviato = (PacchettoInviato) Factory.createObject(PacchettoInviato.class);
								pacchettoInviato.setKey(key);
								boolean inviato = pacchettoInviato.retrieve();
								boolean daInviare = true;
								//Non e' da inviare se e' presente il record con stato = PROCESSATO
								if(inviato && pacchettoInviato.getStatoPacchetto() == PacchettoTrasmissione.PROCESSATO) {
									daInviare = false;
								}
								if(daInviare) {
									Map headers = new java.util.HashMap<String,String>();
									try {
										JSONObject response = BaseArchismallApi.getInstance().sendJSON("POST", endpoint, json.toString(), true, headers);
										String statusCode = response.getString("status");
										if(!statusCode.equals("200")) {
											String errors = (String) (response.has("errors") ? response.get("errors") : ""); 
											if(!errors.isEmpty()) {
												JSONObject jsonObject = new JSONObject(errors);
												String message = jsonObject.has("message") ? jsonObject.getString("message") : null;
												if(message != null && !message.contains("Document already uploaded")) {
													//output.println(" --> Pacchetto:"+submissionPack.getId()+", inviato verso Archismall con errori: \n "+message);
												}else if(message != null && message.contains("Document already uploaded")){
													pacchettoInviato.setStatoPacchetto(PacchettoTrasmissione.PROCESSATO);
												}
												pacchettoInviato.setDescrErrore(message);
											}else {
												//output.println(" --> Pacchetto:"+submissionPack.getId()+", inviato verso Archismall con status: \n "+statusCode);
											}
										}

										//Registro il pacchetto come inviato o non inviato
										if(statusCode.equals("200")) 
											pacchettoInviato.setStatoPacchetto(PacchettoTrasmissione.PROCESSATO);
										int rc = pacchettoInviato.save();
										if(rc >= 0) {
											ConnectionManager.commit();
										}else {
											ConnectionManager.rollback();
										}

									} catch (Exception e) {
										if(e instanceof PantheraApiException) {
											if(e.getMessage().contains("Impossibile autenticarsi")) {
												output.println(e.getMessage());
												return false;
											}
										}
										e.printStackTrace(Trace.excStream);
									}
								}else {
									//gia inviato
								}
							}
						} catch (ThipException e) {
							//non ci sono i metadati
							output.println(" --> Pacchetto processato con errore : "+e.getMessage());
							e.printStackTrace(Trace.excStream);
						} catch (SQLException e1) {
							e1.printStackTrace(Trace.excStream);
						}
					}
				}
				//lo stato lo prendo da una MIN su SOFTRE.PACCHETTO_INVIATO
				pacchetto.setStatoPacchetto(PacchettoInviato.statoInvioPacchettone(pacchetto.getIdLancio()));
				try {
					int rc = pacchetto.save();
					if(rc > 0)
						ConnectionManager.commit();
					else
						ConnectionManager.rollback();
				} catch (SQLException e) {
					result = false;
					output.println(" --> Errore nel salvataggio del pacchettone : "+e.getMessage());
					e.printStackTrace(Trace.excStream);
				}
			}
		}else {
			output.println(" --> Non ci sono pacchetti da inviare, termino... ");
			return true;
		}
		return result;
	}

	protected static List<PacchettoTrasmissione> recuperaListaPacchettiDaSelezionati(String chiaviSelezionati){
		List<PacchettoTrasmissione> pacchetti = new ArrayList<PacchettoTrasmissione>();
		List<String> keyList = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(chiaviSelezionati, ColonneFiltri.LISTA_SEP);
		while (tokenizer.hasMoreTokens()) {
			String next = tokenizer.nextToken(ColonneFiltri.LISTA_SEP);
			keyList.add(next);
		}
		for(String key : keyList) {
			try {
				PacchettoTrasmissione pacchetto = (PacchettoTrasmissione) 
						PacchettoTrasmissione.elementWithKey(PacchettoTrasmissione.class, key, PersistentObject.NO_LOCK);
				if(pacchetto != null && pacchetto.getStatoPacchetto() != PacchettoTrasmissione.PROCESSATO)
					pacchetti.add(pacchetto);
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return pacchetti;
	}


	@Override
	protected String getClassAdCollectionName() {
		return "PachettoTraInvio";
	}

}
