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
import com.thera.thermfw.persist.ErrorCodes;
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

	BaseArchismallApi baseArchismallApi = null;

	public String getChiaviSelezionati() {
		return iChiaviSelezionati;
	}

	public void setChiaviSelezionati(String iChiaviSelezionati) {
		this.iChiaviSelezionati = iChiaviSelezionati;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected boolean run() {
		String dbName = IniFile.getValue("thermfw.ini","Web", "Database");
		dbName = dbName.substring(0,dbName.indexOf(","));
		boolean everythingOk = true;
		dbName = "PANTH01";
		try {
			if(dbName.equals("PANTH01")) {
				ConfigurazioneArchismall conf = ConfigurazioneArchismall.getConfigurazioneArchismall();
				this.baseArchismallApi = (BaseArchismallApi) Factory.createObject(BaseArchismallApi.class);
				if(conf != null) {
					List<PacchettoTrasmissione> pacchetti = recuperaListaPacchettiDaSelezionati(getChiaviSelezionati());
					if(pacchetti.size() > 0) {
						for (Iterator iterator = pacchetti.iterator(); iterator.hasNext();) {
							PacchettoTrasmissione pacchetto = (PacchettoTrasmissione) iterator.next();
							Map<String, List<SubmissionPackDett>> dettagli = normalizzazionePacchetto(pacchetto);
							int rc = inviaDettagliPacchettiVersoArchismall(dettagli,pacchetto);

							//se tutto ok flaggo come processato, altrimenti processato come errore
							if(rc > ErrorCodes.OK) {
								pacchetto.setStatoPacchetto(PacchettoTrasmissione.PROCESSATO);
							}else {
								pacchetto.setStatoPacchetto(PacchettoTrasmissione.PROCESSATO_CON_ERRORE);
							}

							try {
								rc = pacchetto.save();
								if(rc > 0)
									ConnectionManager.commit();
								else
									ConnectionManager.rollback();
							} catch (SQLException e) {
								output.println(" --> Errore nel salvataggio del pacchettone : "+e.getMessage());
								e.printStackTrace(Trace.excStream);
							}

						}
					}
				}else {
					output.print(" ** Prima di inviare i pacchetti verso Archismall va definita la ConfigurazioneArchismall :"+ConfigurazioneArchismallTM.TABLE_NAME);
				}
			}
		}catch (Exception e) {
			output.println("Exc : " + e.getMessage());
			everythingOk = false;
			e.printStackTrace(Trace.excStream);
		}
		return everythingOk;
	}


	/**
	 * <h1>Softre Solutions</h1>
	 * <br>
	 * @author Daniele Signoroni 10/01/2025
	 * <br><br>
	 * <b>71XXX	DSSOF3	10/01/2025</b>
	 * <p></p>
	 * @param pacchetto 
	 * @param dettagli
	 * @return
	 * @throws PantheraApiException 
	 */
	public int inviaDettagliPacchettiVersoArchismall(Map<String, List<SubmissionPackDett>> groupedMap, PacchettoTrasmissione pacchetto) throws PantheraApiException {
		Character tipoPacchetto = PacchettoTrasmissione.getTipoPacchettone(pacchetto.getIdLancio());
		int rc = ErrorCodes.OK;
		int totSize = 0;
		int successPackets = 0;
		int errorPackets = 0;
		Integer submissionPackDettTot = 0;
		Integer inviatiCorrettamente = 0;
		String idAzienda = pacchetto.getIdAziendaPacchetto();
		for (Map.Entry<String, List<SubmissionPackDett>> entry : groupedMap.entrySet()) {
			List<SubmissionPackDett> dettagli = entry.getValue();
			submissionPackDettTot += dettagli.size();
			for(SubmissionPackDett submissionPack : dettagli) {
				List<String> errors = new ArrayList<String>();

				//1.Recupero l'endpoint per capire se sto versando una fattura attiva/passiva o una notifica
				String endpoint = submissionPack.getEndpointDaTipoDocumento(tipoPacchetto,baseArchismallApi);
				if(endpoint == null) {
					errors.add(" --> Per il Tipo Documento : "+submissionPack.getTipoDoc()+", il sistema non ha determinato se passivo/attivo ");
					continue;
				}
				if(errors.isEmpty()) {
					JSONObject json = null;
					try {
						//2.Recupero il json del pacchetto che sto andando a caricare in Archismall
						json = submissionPack.getJSONVersamento(tipoPacchetto);
						if(json != null && !json.isEmpty()) {

							String key = KeyHelper.buildObjectKey(new String[] {
									idAzienda,json.get("annoFattura").toString().trim(),
									json.get("dataFattura").toString().trim(),json.get("numeroFattura").toString().trim()
							});

							//3.Creo/Recupero il record nella tabella di appoggio che mi aiuta a capire quali pacchetti sono gia' stati inviati
							SendedPackArchismall pacchettoInviato = getPacchettoInviatoArchismall(key);

							//4.Pacchetto gia' processato con successo quindi lo salto
							if(pacchettoInviato.isOnDB() && pacchettoInviato.getStatoPacchetto() == PacchettoTrasmissione.PROCESSATO) {
								continue;
							}else {
								pacchettoInviato.setIdLancio(pacchetto.getIdLancio());
								pacchettoInviato.setIdArchiPro(json.getString("idArchiPro"));
							}

							char statoArchismall = pacchettoInviato.getStatoArchismall();
							switch (statoArchismall) {
							//5.Il pacchetto e' da conservare quindi da caricare in Archismall
							case StatoPacchettoArchismall.DA_CONSERVARE:
								try {
									//6.Tramite la mia classe di utilities mando la POST al portale
									JSONObject response = baseArchismallApi.sendJSON(endpoint, json.toString(),new HashMap<String,String>(),3);
									Status status = (Status) response.get("status");
									if(status != Status.OK) {
										if(response.has("result")) {
											JSONObject r = response.getJSONObject("result");
											if(r.has("errors")) {
												errors.add(r.get("errors").toString());
												errorPackets++;
											}else if(r.has("message")) {
												errorPackets++;
											}
										}
										//Se ci sono errori allora il pacchetto e' da processare nuovamente
										pacchettoInviato.setStatoPacchetto(PacchettoTrasmissione.PROCESSATO_CON_ERRORE);
									}else {
										//Se tutto ok lo flaggo come processato e vado avanti
										pacchettoInviato.setStatoPacchetto(PacchettoTrasmissione.PROCESSATO);
										successPackets++;
										inviatiCorrettamente++;
									}

								} catch (Exception e) {
									if(e instanceof PantheraApiException) {
										if(e.getMessage().contains("Impossibile autenticarsi")) {
											output.println(e.getMessage());
											throw (PantheraApiException) e;
										}
									}
									e.printStackTrace(Trace.excStream);
									errorPackets++;
								}
								break;
							default:
								break;
							}

							//Infine aggiorno il record
							//Qui devo committare per forza ad ogni singolo dettaglio in quanto la chiamata API e' stata eseguita
							int rcPckt = pacchettoInviato.save();
							if(rcPckt > 0) {
								ConnectionManager.commit();
							}else {
								ConnectionManager.rollback();
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

					rc = ErrorCodes.GENERIC_ERROR;
				}
				totSize++;
			}

			if(inviatiCorrettamente.compareTo(submissionPackDettTot) == 0) {
				pacchetto.setStatoPacchetto(PacchettoTrasmissione.PROCESSATO);
			}else {
				pacchetto.setStatoPacchetto(PacchettoTrasmissione.PROCESSATO_CON_ERRORE);
			}
			try {
				rc = pacchetto.save();
				if(rc >= ErrorCodes.OK) {
					ConnectionManager.commit();
				}else {
					ConnectionManager.rollback();
				}
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}

			output.println("** Pacchetti totali processati = "+totSize);
			output.println("** Pacchetti totali processati con successo = "+successPackets);
			output.println("** Pacchettei totali processati con errore = "+errorPackets);
		}
		return rc;
	}

	public SendedPackArchismall getPacchettoInviatoArchismall(String key) {
		SendedPackArchismall packet = (SendedPackArchismall) Factory.createObject(SendedPackArchismall.class);
		packet.setKey(key);
		try {
			packet.retrieve();
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return packet;
	}

	/**
	 * <h1>Softre Solutions</h1>
	 * <br>
	 * @author Daniele Signoroni 10/01/2025
	 * <br><br>
	 * <b>71XXX	DSSOF3	10/01/2025</b>
	 * <p></p>
	 * @param pacchetti
	 * @return
	 */
	public Map<String, List<SubmissionPackDett>> normalizzazionePacchetto(PacchettoTrasmissione pacchetto) {
		List<SubmissionPackDett> submissionPackages = pacchetto.submissionPacksDettaglioDaPacchetto();
		Map<String, List<SubmissionPackDett>> groupedMap = groupSubmissionPackagesForInvoice(submissionPackages);
		for (Map.Entry<String, List<SubmissionPackDett>> entry : groupedMap.entrySet()) {
			List<SubmissionPackDett> dettagli = entry.getValue();
			Collections.sort(dettagli, new Comparator<SubmissionPackDett>() {
				public int compare(SubmissionPackDett o1, SubmissionPackDett o2) {
					return Long.compare(o1.getId(), o2.getId());
				}
			});
			String validPath = null;
			for(SubmissionPackDett submissionPack : dettagli) {
				File file = new File(submissionPack.getNomeFile());
				if(file.exists()) {
					validPath = submissionPack.getNomeFile();
				}else {
					if(validPath != null) {
						String path = findValidPathFromPrevoiusSubmissionPack(validPath,submissionPack);
						file = new File(path);
						if(!file.exists()) {
							output.println(" --> File : "+path+", non trovato nel sistema");
							output.println("Il pacchetto : " + entry.getKey() + " non verra' processato");
							groupedMap.remove(entry.getKey());
						}
						submissionPack.setNomeFile(path);
					}
				}
			}
		}
		return groupedMap;
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
