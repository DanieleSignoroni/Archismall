package it.softre.thip.archismall.trasmissione;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.thera.thermfw.base.IniFile;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.batch.BatchRunnable;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.security.Authorizable;

import it.softre.thip.archismall.base.configuration.ConfigurazioneArchismall;
import it.softre.thip.archismall.base.configuration.ConfigurazioneArchismallTM;
import it.softre.thip.base.archismall.api.ArchismallService;
import it.softre.thip.base.archismall.api.StatoConservazione;

/**
 *
 * <p></p>
 *
 * <p>
 * Company: Softre Solutions<br>
 * Author: Daniele Signoroni<br>
 * Date: 21/10/2026
 * </p>
 */

/*
 * Revisions:
 * Number   Date        Owner    Description
 * 72XXX    21/10/2026  DSSOF3   Prima stesura
 */

public class VerificaStatoConservazionePacchetti extends BatchRunnable implements Authorizable {

	private static ArchismallService service = ArchismallService.getInstance();

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
		try {
			dbName = "PANTH01"; //TODO
			if(dbName.equals("PANTH01")) {
				ConfigurazioneArchismall conf = ConfigurazioneArchismall.getConfigurazioneArchismall();
				if(conf != null) {
					List<PacchettoTrasmissione> pacchetti = PacchettoTrasmissioneInvio.recuperaListaPacchettiDaSelezionati(getChiaviSelezionati());
					if(pacchetti.size() > 0) {
						for (Iterator iterator = pacchetti.iterator(); iterator.hasNext();) {
							boolean isOk = verificaPacchettone((PacchettoTrasmissione) iterator.next());
						}
					}
				}else {
					output.println(" ** Prima di inviare i pacchetti verso Archismall va definita la ConfigurazioneArchismall :"+ConfigurazioneArchismallTM.TABLE_NAME);
				}
			}
		}catch (Exception e) {
			output.println("Exc : " + e.getMessage());
			everythingOk = false;
			e.printStackTrace(Trace.excStream);
		}
		return everythingOk;
	}

	@SuppressWarnings("rawtypes")
	protected boolean verificaPacchettone(PacchettoTrasmissione pacchettone) {
		boolean everythingOk = true;

		List<SendedPackArchismall> pacchettiInviati = pacchettone.recuperaPacchettiInviati();

		try {
			for (Iterator iterator = pacchettiInviati.iterator(); iterator.hasNext();) {
				SendedPackArchismall pacchettoInviato = (SendedPackArchismall) iterator.next();

				Integer idPacchetto = pacchettoInviato.recuperaIdPacchetto();

				StatoConservazione statoConservazione = service.statoConservazionePacchetto(pacchettoInviato, idPacchetto, pacchettoInviato.getIdLancio()
						, true, false);


			}
			ConnectionManager.commit();
		}catch (Exception e) {
			try {
				ConnectionManager.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace(Trace.excStream);
			}
			e.printStackTrace(Trace.excStream);
		}

		return everythingOk;
	}

	@Override
	protected String getClassAdCollectionName() {
		return "YVerificaStatoConsPacArch";
	}

}
