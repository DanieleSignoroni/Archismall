package it.softre.thip.archismall.trasmissione;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.TableManager;

public class PacchettoInviatoTM extends TableManager {

	public static final String ID_PACCHETTO = "ID_PACCHETTO";

	public static final String ID_LANCIO = "ID_LANCIO";

	public static final String ID_ARCHI_PRO = "ID_ARCHI_PRO";

	public static final String STATO_PACCHETTO = "STATO_PACCHETTO";

	public static final String STATO_ARCHISMALL = "STATO_ARCHISMALL";

	public static final String DESCR_ERRORE = "DESCR_ERRORE";

	public static final String TIMESTAMP = "TIMESTAMP";

	public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "PACCHETTO_INVIATO";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.softre.thip.archismall.trasmissione.PacchettoInviato.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager) Factory.createObject(PacchettoInviatoTM.class);
		}
		return cInstance;
	}

	public PacchettoInviatoTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("IdPacchetto", ID_PACCHETTO, "getIntegerObject");
		addAttribute("IdLancio", ID_LANCIO);
		addAttribute("IdArchiPro", ID_ARCHI_PRO);
		addAttribute("StatoPacchetto", STATO_PACCHETTO);
		addAttribute("StatoArchismall", STATO_ARCHISMALL);
		addAttribute("DescrErrore", DESCR_ERRORE);
		addTimestampAttribute("Timestamp", TIMESTAMP);
		setKeys(ID_LANCIO + "," + ID_PACCHETTO + "," + ID_ARCHI_PRO);
	}

	private void init() throws SQLException {
		configure();
	}

}
