package it.softre.thip.archismall.trasmissione;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.thera.thermfw.base.IniFile;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.batch.BatchRunnable;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.rs.errors.PantheraApiException;
import com.thera.thermfw.security.Authorizable;

import it.softre.thip.archismall.base.configuration.ConfigurazioneArchismall;
import it.softre.thip.archismall.base.configuration.ConfigurazioneArchismallTM;
import it.softre.thip.archismall.base.generale.StatoPacchettoArchismall;
import it.softre.thip.base.archismall.api.BaseArchismallApi;
import it.thera.thip.cs.ColonneFiltri;
import it.thera.thip.cs.ThipException;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 15/05/2024
 * <br><br>
 * <b>71578	DSSOF3	15/05/2024</b>
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
		String dbName = IniFile.getValue("thermfw.ini","Web", "Database");
		dbName = dbName.substring(0,dbName.indexOf(","));
		boolean everythingOk = true;
		if(dbName.equals("PANTH01")) {
			everythingOk = invaPacchettiVersoArchismall();
		}
		return everythingOk;
	}

	@SuppressWarnings("rawtypes")
	protected boolean invaPacchettiVersoArchismall() {
		boolean result = true;
		ConfigurazioneArchismall conf = ConfigurazioneArchismall.getConfigurazioneArchismall();
		if(conf != null) {
			BaseArchismallApi baseArchismallApi = (BaseArchismallApi) Factory.createObject(BaseArchismallApi.class);
			List<PacchettoTrasmissione> pacchetti = recuperaListaPacchettiDaSelezionati(getChiaviSelezionati());
			if(pacchetti.size() > 0) {
				for (Iterator iterator = pacchetti.iterator(); iterator.hasNext();) {
					PacchettoTrasmissione pacchetto = (PacchettoTrasmissione) iterator.next();
					List<SubmissionPackDett> submissionPackages = pacchetto.submissionPacksDettaglioDaPacchetto();
					Character tipoPacchetto = PacchettoTrasmissione.getTipoPacchettone(pacchetto.getIdLancio());
					boolean isInError = false;
					if(tipoPacchetto != null) {
						Map<String, List<SubmissionPackDett>> groupedMap = groupSubmissionPackagesForInvoice(submissionPackages);
						int totSize = 0;
						int successPackets = 0;
						int errorPackets = 0;
						for (Map.Entry<String, List<SubmissionPackDett>> entry : groupedMap.entrySet()) {
							List<SubmissionPackDett> dettagli = entry.getValue();
							Collections.sort(dettagli, new Comparator<SubmissionPackDett>() {
								public int compare(SubmissionPackDett o1, SubmissionPackDett o2) {
									return Long.compare(o1.getId(), o2.getId());
								}
							});
							String validPath = null;
							for(SubmissionPackDett submissionPack : dettagli) {
								List<String> errors = new ArrayList<String>();
								File file = new File(submissionPack.getNomeFile());
								if(file.exists()) {
									validPath = submissionPack.getNomeFile();
								}else {
									if(validPath != null) {
										String path = findValidPathFromPrevoiusSubmissionPack(validPath,submissionPack);
										file = new File(path);
										if(!file.exists()) {
											errors.add(" --> File : "+path+", non trovato nel sistema");
										}
										submissionPack.setNomeFile(path);
									}
								}
								String endpoint = submissionPack.getEndpointDaTipoDocumento(tipoPacchetto,baseArchismallApi);
								if(endpoint == null) {
									errors.add(" --> Per il Tipo Documento : "+submissionPack.getTipoDoc()+", il sistema non ha determinato se passivo/attivo ");
									continue;
								}
								if(errors.isEmpty()) {
									JSONObject json;
									try {
										json = submissionPack.getJSONVersamento(tipoPacchetto);
										if(endpoint != null && json != null && !json.isEmpty()) {

											String key = KeyHelper.buildObjectKey(new String[] {
													submissionPack.getIdLancio().trim(),
													String.valueOf(submissionPack.getId()),
													(String) json.get("idArchiPro")
											});
											PacchettoInviato pacchettoInviato = (PacchettoInviato) Factory.createObject(PacchettoInviato.class);
											pacchettoInviato.setKey(key);
											pacchettoInviato.retrieve();
											char statoArchismall = pacchettoInviato.getStatoArchismall();
											switch (statoArchismall) {
											case StatoPacchettoArchismall.DA_CONSERVARE:
												Map headers = new java.util.HashMap<String,String>();
												try {
													JSONObject response = baseArchismallApi.sendJSON(endpoint, json.toString(),headers,3);
													Status status = (Status) response.get("status");
													if(status != Status.OK) {
														if(response.has("result")) {
															JSONObject r = response.getJSONObject("result");
															if(r.has("errors")) {
																errors.add(r.get("errors").toString());
																errorPackets++;
															}else if(r.has("message")) {
																String message = r.getString("message");
																if(message.equals("Document already uploaded")) {
																	JSONObject stato = submissionPack.recuperaStatoConservazionePacchettoArchismall(pacchettoInviato.getIdArchiPro(),tipoPacchetto,baseArchismallApi);
																	if(stato.has("statusDescription") && stato.getString("statusDescription").equals("Da conservare")) {
																		pacchettoInviato.setStatoArchismall(StatoPacchettoArchismall.CONSERVATO);
																		successPackets++;
																	}else if(stato.has("statusDescription") && stato.getString("statusDescription").equals("Conservato")) {
																		pacchettoInviato.setStatoArchismall(StatoPacchettoArchismall.CONSERVATO);
																		successPackets++;
																	}
																}
															}
														}
													}else {
														successPackets++;
													}

												} catch (Exception e) {
													if(e instanceof PantheraApiException) {
														if(e.getMessage().contains("Impossibile autenticarsi")) {
															output.println(e.getMessage());
															return false;
														}
													}
													e.printStackTrace(Trace.excStream);
													errorPackets++;
												}
												pacchettoInviato.save();
												break;
											case StatoPacchettoArchismall.CONSERVATO: 
												successPackets++;
												break;
											case StatoPacchettoArchismall.WORKER_ERROR:
												JSONObject stato = submissionPack.recuperaStatoConservazionePacchettoArchismall(pacchettoInviato.getIdArchiPro(),tipoPacchetto,baseArchismallApi);
												if(stato.has("statusDescription") && stato.getString("statusDescription").equals("Da conservare")) {
													pacchettoInviato.setStatoArchismall(StatoPacchettoArchismall.CONSERVATO);
													successPackets++;
												}else if(stato.has("statusDescription") && stato.getString("statusDescription").equals("Conservato")) {
													pacchettoInviato.setStatoArchismall(StatoPacchettoArchismall.CONSERVATO);
													successPackets++;
												}
												pacchettoInviato.save();
												break;
											default:
												break;
											}
										}else {
											errors.add("JSON versamento NULL, FP.SUBMISSION_PACK_DETT.DESCR_ERRORE = "+submissionPack.getDescrErrore());
											errorPackets++;
										}
									} catch (ThipException e) {
										errors.add(e.getMessage());
										e.printStackTrace(Trace.excStream);
										errorPackets++;
									} catch (SQLException e1) {
										e1.printStackTrace(Trace.excStream);
									}
								}

								if(!errors.isEmpty()) {
									output.println("Pacchetto "+submissionPack.getId()+", processato con errori: ");
									for(String error : errors) {
										output.println(error+" \n");
									}
									output.println("");
									isInError = true;
								}

								totSize++;
							}
						}
						try {
							ConnectionManager.commit();
						} catch (SQLException e) {
							e.printStackTrace(Trace.excStream);
						}
						output.println("Pacchetti totali = "+totSize);
						output.println("Processati status code 200 = "+successPackets);
						output.println("Con errore = "+errorPackets);
						if(!isInError)
							pacchetto.setStatoPacchetto(PacchettoTrasmissione.PROCESSATO);
						else
							pacchetto.setStatoPacchetto(PacchettoTrasmissione.PROCESSATO_CON_ERRORE);
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
				}
			}else {
				output.println(" --> Non ci sono pacchetti da inviare con "+PacchettoTrasmissioneTM.TABLE_NAME+"."+PacchettoTrasmissioneTM.STATO_PACCHETTO+" = '"+PacchettoTrasmissione.NON_PROCESSATO+"' ");
				return true;
			}
		}else {
			output.print(" ** Prima di inviare i pacchetti verso Archismall va definita la ConfigurazioneArchismall :"+ConfigurazioneArchismallTM.TABLE_NAME);
		}
		return result;
	}

	/**
	 * Cerca il path valido a partire dal path precedente.<br>
	 * Questo accade quando sto procesasndo la notifica della fattua (attiva/passiva), questa e' salvata a database senza percorso.<br>
	 * Risiede pero' nello stesso posto della fattura, quindi prendo quel path e creo il nuovo con il nome della RC.
	 * @author Daniele Signoroni 17/10/2024
	 * <p>
	 * Prima stesura.<br>
	 *
	 * </p>
	 * @param validPath
	 * @param submissionPack
	 * @return
	 */
	protected String findValidPathFromPrevoiusSubmissionPack(String validPath, SubmissionPackDett submissionPack) {
		//il nome file e' quello del precedente cambiando in notifiche
		int ultimoBackSlash = validPath.lastIndexOf("\\");
		String path = validPath.substring(0,validPath.lastIndexOf("\\"));
		int penultimoBackSlas = path.lastIndexOf("\\");
		String toSubstitute = path.substring(penultimoBackSlas+1, ultimoBackSlash);
		path = path.replace(toSubstitute, "notifiche");
		path += "\\" + submissionPack.getNomeFile();
		return path;
	}

	/**
	 * Raggruppa i submission packages per data e numero fattura.<br>
	 * @author Daniele Signoroni 17/10/2024
	 * <p>
	 * Prima stesura.<br>
	 *
	 * </p>
	 * @param submissionPackages
	 * @return
	 */
	protected Map<String, List<SubmissionPackDett>> groupSubmissionPackagesForInvoice(List<SubmissionPackDett> submissionPackages){
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
		return groupedMap;
	}


	/**
	 * Recupera i pacchetti da inviare dalla lista delle chiavi selezionate in griglia.<br>
	 * @author Daniele Signoroni 17/10/2024
	 * <p>
	 * Prima stesura.<br>
	 *
	 * </p>
	 * @param chiaviSelezionati
	 * @return
	 */
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
				if(pacchetto != null)
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
