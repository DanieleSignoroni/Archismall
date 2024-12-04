package it.softre.thip.archismall.trasmissione.web;

import java.sql.SQLException;
import java.util.Iterator;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.gui.cnr.DOList;
import com.thera.thermfw.gui.cnr.DisplayObject;
import com.thera.thermfw.persist.PersistentObject;

import it.softre.thip.archismall.trasmissione.PacchettoTrasmissione;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 08/07/2024
 * <br><br>
 * <b>71578	DSSOF3	28/10/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 *
 */

public class PacchettoTrasmissioneDoList extends DOList {

	@SuppressWarnings("rawtypes")
	@Override
	protected int getElements() {
		int number = super.getElements();
		Iterator iter = detailsDO.iterator();
		String idSocieta = PacchettoTrasmissione.getIdSocietaAziendaCorrente();
		while(iter.hasNext()) {
			DisplayObject obj = (DisplayObject) iter.next();
			String key = obj.getObjectKey();

			try {
				PacchettoTrasmissione pckt = (PacchettoTrasmissione) PacchettoTrasmissione.elementWithKey(PacchettoTrasmissione.class, key, PersistentObject.NO_LOCK);
				if(pckt != null) {
					Integer idSocietaInteger = Integer.valueOf(idSocieta);
					Integer idSocietaPacchetto = PacchettoTrasmissione.getIdSocietaPacchetto(pckt.getId());
					if(idSocietaInteger.compareTo(idSocietaPacchetto) != 0) {
						iter.remove();
					}
				}
			} catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		number = detailsDO.size();
		return number;
	}
}
