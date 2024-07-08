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

	public static ArchismallService getInstance() {
		if(instance == null) {
			instance = (ArchismallService) Factory.createObject(ArchismallService.class);
		}
		return instance;
	}

	public StatoConservazione statoConservazionePacchetto(Integer idPacchetto) {
		PacchettoInviato pacchettoInviato = PacchettoInviato.pacchettoInviatoDaCodice(idPacchetto);
		StatoConservazione statoConservazione = null;
		if(pacchettoInviato != null) {
			SubmissionPackDett dettaglio = pacchettoInviato.dettaglio();
			JSONObject stato = dettaglio.recuperaStatoConservazionePacchettoArchismall(pacchettoInviato.getIdArchiPro());
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
}
