package it.softre.thip.archismall.trasmissione;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.TableManager;

import it.thera.thip.cs.DatiComuniEstesiTTM;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 11/01/2025
 * <br><br>
 * <b>71XXX	DSSOF3	11/01/2025</b>
 * <p></p>
 */

public class SendedPackArchismallTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String R_ANNO_FATTURA = "R_ANNO_FATTURA";

	public static final String DATA_FATTURA = "DATA_FATTURA";

	public static final String R_NUMERO_FATTURA = "R_NUMERO_FATTURA";

	public static final String STATO_PACCHETTO = "STATO_PACCHETTO";

	public static final String STATO_ARCHISMALL = "STATO_ARCHISMALL";

	public static final String R_LANCIO = "R_LANCIO";

	public static final String ID_ARCHI_PRO = "ID_ARCHI_PRO";

	public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "SENDED_PACK_ARCHISMALL";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.softre.thip.archismall.trasmissione.SendedPackArchismall.class
			.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager) Factory.createObject(SendedPackArchismallTM.class);
		}
		return cInstance;
	}

	public SendedPackArchismallTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("IdAnnoFattura", R_ANNO_FATTURA);
		addAttribute("DataFattura", DATA_FATTURA);
		addAttribute("IdNumeroFattura", R_NUMERO_FATTURA);
		addAttribute("StatoPacchetto", STATO_PACCHETTO);
		addAttribute("StatoArchismall", STATO_ARCHISMALL);
		addAttribute("IdLancio", R_LANCIO);
		addAttribute("IdArchiPro", ID_ARCHI_PRO);
		addAttribute("IdAzienda", ID_AZIENDA);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA + "," + R_ANNO_FATTURA + "," + DATA_FATTURA + "," + R_NUMERO_FATTURA);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM) getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure(R_ANNO_FATTURA + ", " + DATA_FATTURA + ", " + R_NUMERO_FATTURA + ", " + STATO_PACCHETTO + ", "
				+ STATO_ARCHISMALL + ", " + R_LANCIO + ", " + ID_ARCHI_PRO + ", " + ID_AZIENDA + ", " + STATO + ", "
				+ R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG);
	}

}
