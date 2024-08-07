package it.softre.thip.archismall.base.generale;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.thera.thermfw.persist.Factory;

/**
 * Contiene alcune utilita' per l'integrazione tra Panthera e Archismall.<br></br>
 * Contiene due enumerati utili per determinate se un pacchetto e' attivo o passivo:
 * <list>
 * 	<li>{@link TipoDocumentiAttivo}</li>
 *  <li>{@link TipoDocumentoPassivo}</li>
 * </list>
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 15/05/2024
 * <br><br>
 * <b>71578	DSSOF3	15/05/2024</b>
 */

public class ArchismallUtils {

	private ArchismallUtils instance;

	public ArchismallUtils getInstance() {
		if(instance == null) {
			instance = (ArchismallUtils) Factory.createObject(ArchismallUtils.class);
		}
		return instance;
	}

	/**
	 * Contiene i codici dei tipi documento di fatturazione elettronica attiva.
	 * <h1>Softre Solutions</h1>
	 * <br>
	 * @author Daniele Signoroni 15/05/2024
	 */
	public enum TipoDocumentiAttivo {
		TD01,TD02,TD03,TD04,TD05,TD24,TD25,TD26; 

		private static final Set<String> tipiDocumentoAttivi = new HashSet<String>(Arrays.asList("TD01","TD02","TD03","TD04","TD05","TD24","TD25","TD26"));

		public static boolean contains(String code) {
			return tipiDocumentoAttivi.contains(code);
		}
	}

	/**
	 * Contiene i codici dei tipi documento di fatturazione elettronica passiva.
	 * <h1>Softre Solutions</h1>
	 * <br>
	 * @author Daniele Signoroni 15/05/2024
	 */
	public enum TipoDocumentoPassivo {
		T06,TD16,TD17,TD18,TD19; 

		private static final Set<String> tipiDocumentoPassivi = new HashSet<String>(Arrays.asList("TD06", "TD16", "TD17", "TD18", "TD19"));

		public static boolean contains(String code) {
			return tipiDocumentoPassivi.contains(code);
		}
	}
}
