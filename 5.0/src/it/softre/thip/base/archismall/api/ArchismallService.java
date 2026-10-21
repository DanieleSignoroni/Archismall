package it.softre.thip.base.archismall.api;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.type.EnumType;

import it.softre.thip.archismall.base.generale.ArchismallErrors;
import it.softre.thip.archismall.trasmissione.PacchettoInviato;
import it.softre.thip.archismall.trasmissione.PacchettoTrasmissione;
import it.softre.thip.archismall.trasmissione.SendedPackArchismall;
import it.softre.thip.archismall.trasmissione.SubmissionPackDett;

/**
 * Classe di servizio per la gestione di Archismall.<br>
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 08/07/2024
 * <br><br>
 * <b>71578	DSSOF3	08/07/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

public class ArchismallService {

	private static ArchismallService instance = null;
	private static BaseArchismallApi baseArchismallApi;

	public static ArchismallService getInstance() {
		if(instance == null) {
			instance = (ArchismallService) Factory.createObject(ArchismallService.class);
			baseArchismallApi = (BaseArchismallApi) Factory.createObject(BaseArchismallApi.class);
		}
		return instance;
	}

	@Deprecated
	/**
	 * @deprecated 
	 * deprecato in quanto la tabella sotto non e' piu quella usata per conservare i pacchetti inviati, 
	 * @param idPacchetto
	 * @param idLancio
	 * @return
	 */
	public StatoConservazione statoConservazionePacchetto(Integer idPacchetto, String idLancio) {
		BaseArchismallApi baseArchismallApi = (BaseArchismallApi) Factory.createObject(BaseArchismallApi.class);
		PacchettoInviato pacchettoInviato = PacchettoInviato.pacchettoInviatoDaCodice(idPacchetto,idLancio);
		StatoConservazione statoConservazione = null;
		if(pacchettoInviato != null) {
			Character tipoPacchetto = PacchettoTrasmissione.getTipoPacchettone(idLancio);
			SubmissionPackDett dettaglio = pacchettoInviato.dettaglio();
			JSONObject stato = dettaglio.recuperaStatoConservazionePacchettoArchismall(pacchettoInviato.getIdArchiPro(),tipoPacchetto,baseArchismallApi);
			statoConservazione = new StatoConservazione(
					pacchettoInviato.getIdPacchetto().toString(),
					pacchettoInviato.getIdLancio(),
					pacchettoInviato.getIdArchiPro(),
					String.valueOf(pacchettoInviato.getStatoPacchetto()),
					String.valueOf(pacchettoInviato.getStatoArchismall()),
					"",dettaglio.getNumero().trim(),dettaglio.getDataDoc().toString(),dettaglio.getRagioneSociale().trim(),dettaglio.getTipoDoc().trim());
			EnumType enumType = EnumType.getEnumTypeInstance("YStatoInvioPacchettoArchi", EnumType.class);
			statoConservazione.setStatoPanthera(enumType.descriptionFromValue(String.valueOf(pacchettoInviato.getStatoPacchetto())));
			if(stato != null) {
				try {
					Integer status = null;
					Integer errorCode = null;
					if(stato.has("status") && stato.get("status") != JSONObject.NULL) {
						//se lo status e' != null allora considero questo
						enumType = EnumType.getEnumTypeInstance("YStatoPacchettoArchismall", EnumType.class);
						status = stato.getInt("status"); 
						String descr = enumType.descriptionFromValue(String.valueOf(status));
						statoConservazione.setDescrizioneStatoArchismall(descr);
						statoConservazione.setStatoArchismall(String.valueOf(status));
						pacchettoInviato.setStatoArchismall(String.valueOf(status).charAt(0));
						pacchettoInviato.setDescrErrore(descr);
					}
					if(stato.has("errorCode") && stato.get("errorCode") != JSONObject.NULL) {
						errorCode = stato.getInt("errorCode"); 
						statoConservazione.setStatoArchismall(String.valueOf(errorCode));
						ArchismallErrors errore = ArchismallErrors.getByErrorCode(errorCode);
						if(errore != null) {
							statoConservazione.setDescrizioneErrore(errore.getErrorDescription());
							pacchettoInviato.setDescrErrore(errore.getErrorDescription());
						}
					}
					if(pacchettoInviato.dirty) { //salvo cosi mi tengo aggiornata anche la tabella
						int rc = pacchettoInviato.save();
						if(rc > 0) {
							ConnectionManager.commit();
						}else {
							ConnectionManager.rollback();
						}
					}
				}catch (JSONException e) {
					e.printStackTrace(Trace.excStream);
				} catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
				}
			}
			return statoConservazione;
		}
		return statoConservazione;

	}

	public StatoConservazione statoConservazionePacchetto(SendedPackArchismall pacchettoInviato, Integer idPacchetto, String idLancio, boolean salva, boolean committa) {
		StatoConservazione statoConservazione = null;
		if(pacchettoInviato != null) {
			Character tipoPacchetto = PacchettoTrasmissione.getTipoPacchettone(idLancio);
			SubmissionPackDett dettaglio = PacchettoInviato.dettaglio(idPacchetto);
			JSONObject stato = dettaglio.recuperaStatoConservazionePacchettoArchismall(pacchettoInviato.getIdArchiPro(), tipoPacchetto, baseArchismallApi);
			statoConservazione = new StatoConservazione(
					idPacchetto.toString(),
					pacchettoInviato.getIdLancio(),
					pacchettoInviato.getIdArchiPro(),
					String.valueOf(pacchettoInviato.getStatoPacchetto()),
					String.valueOf(pacchettoInviato.getStatoArchismall()),
					"",
					pacchettoInviato.getIdNumeroFattura(),
					pacchettoInviato.getDataFattura().toString(),
					dettaglio.getRagioneSociale().trim(),
					dettaglio.getTipoDoc().trim());
			EnumType enumType = EnumType.getEnumTypeInstance("YStatoInvioPacchettoArchi", EnumType.class);
			statoConservazione.setStatoPanthera(enumType.descriptionFromValue(String.valueOf(pacchettoInviato.getStatoPacchetto())));
			if(stato != null) {
				try {
					Integer status = null;
					Integer errorCode = null;
					if(stato.has("status") && stato.get("status") != JSONObject.NULL) {
						//se lo status e' != null allora considero questo
						enumType = EnumType.getEnumTypeInstance("YStatoPacchettoArchismall", EnumType.class);
						status = stato.getInt("status"); 
						String descr = enumType.descriptionFromValue(String.valueOf(status));
						statoConservazione.setDescrizioneStatoArchismall(descr);
						statoConservazione.setStatoArchismall(String.valueOf(status));
						pacchettoInviato.setStatoArchismall(String.valueOf(status).charAt(0));
						pacchettoInviato.setDescrErrore(descr);
					}
					if(stato.has("errorCode") && stato.get("errorCode") != JSONObject.NULL) {
						errorCode = stato.getInt("errorCode"); 
						statoConservazione.setStatoArchismall(String.valueOf(errorCode));
						ArchismallErrors errore = ArchismallErrors.getByErrorCode(errorCode);
						if(errore != null) {
							statoConservazione.setDescrizioneErrore(errore.getErrorDescription());
							pacchettoInviato.setDescrErrore(errore.getErrorDescription());
						}
					}
					if(salva) { //salvo cosi mi tengo aggiornata anche la tabella
						int rc = pacchettoInviato.save();
						if(rc > 0 && committa) {
							ConnectionManager.commit();
						}else if(committa){
							ConnectionManager.rollback();
						}
					}
				}catch (JSONException e) {
					e.printStackTrace(Trace.excStream);
				} catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
				}
			}
			return statoConservazione;
		}
		return statoConservazione;

	}
}
