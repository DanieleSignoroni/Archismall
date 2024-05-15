package it.softre.thip.archismall.trasmissione;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.batch.BatchRunnable;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.security.Authorizable;

import it.softre.thip.base.archismall.api.BaseArchismallApi;
import it.thera.thip.cs.ColonneFiltri;

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

public class PacchetttoTrasmissioneInvio extends BatchRunnable implements Authorizable {

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
		output.println(" --> Recupero la lista di pacchetti da inviare... ");
		List<PacchetttoTrasmissione> pacchetti = recuperaListaPacchettiDaSelezionati(getChiaviSelezionati());
		if(pacchetti.size() > 0) {
			for (Iterator iterator = pacchetti.iterator(); iterator.hasNext();) {
				PacchetttoTrasmissione pacchetto = (PacchetttoTrasmissione) iterator.next();
				List<SubmissionPackDett> submissionPackages = pacchetto.submissionPacksDettaglioDaPacchetto();
				for(SubmissionPackDett submissionPack : submissionPackages) {
					String endpoint = submissionPack.getEndpointDaTipoDocumento();
					//ora qui chiamo l'api con il tipo doc
					
					//se 401 allora rinnovo il token
				}
				pacchetto.setStatoPacchetto(PacchetttoTrasmissione.PROCESSATO);
				try {
					int rc = pacchetto.save();
					if(rc > 0)
						ConnectionManager.commit();
					else
						ConnectionManager.rollback();
				} catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
				}
			}
		}else {
			output.println(" --> Non ci sono pacchetti da inviare, termino... ");
			return true;
		}
		return false;
	}

	protected static List<PacchetttoTrasmissione> recuperaListaPacchettiDaSelezionati(String chiaviSelezionati){
		List<PacchetttoTrasmissione> pacchetti = new ArrayList<PacchetttoTrasmissione>();
		List<String> keyList = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(chiaviSelezionati, ColonneFiltri.LISTA_SEP);
		while (tokenizer.hasMoreTokens()) {
			String next = tokenizer.nextToken(ColonneFiltri.LISTA_SEP);
			keyList.add(next);
		}
		for(String key : keyList) {
			try {
				PacchetttoTrasmissione pacchetto = (PacchetttoTrasmissione) 
						PacchetttoTrasmissione.elementWithKey(PacchetttoTrasmissione.class, key, PersistentObject.NO_LOCK);
				if(pacchetto != null && pacchetto.getStatoPacchetto() != PacchetttoTrasmissione.PROCESSATO)
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
