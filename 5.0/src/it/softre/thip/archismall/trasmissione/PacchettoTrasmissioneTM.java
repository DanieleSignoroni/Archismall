package it.softre.thip.archismall.trasmissione;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.TableManager;

/**
 * 
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 08/07/2024
 * <br><br>
 * <b>71578	DSSOF3	08/07/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

public class PacchettoTrasmissioneTM extends TableManager {

	public static final String STATO_PACCHETTO = "STATO_PACCHETTO";

	public static final String ID = "ID";

	public static final String ID_LANCIO = "ID_LANCIO";

	public static final String DATA_CREAZIONE = "DATA_CREAZIONE";

	public static final String NOME = "NOME";

	public static final String DESCRIZIONE = "DESCRIZIONE";

	public static final String TIMESTAMP = "TIMESTAMP";
	
	public static final String ID_SOCIETA = "ID_SOCIETA";

	public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "PACCHETTO_TRASMISSIONE";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.softre.thip.archismall.trasmissione.PacchettoTrasmissione.class
			.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager) Factory.createObject(PacchettoTrasmissioneTM.class);
		}
		return cInstance;
	}

	public PacchettoTrasmissioneTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("StatoPacchetto", STATO_PACCHETTO);
		addAttribute("Id", ID, "getIntegerObject");
		addAttribute("IdLancio", ID_LANCIO);
		addAttribute("DataCreazione", DATA_CREAZIONE);
		addAttribute("Nome", NOME);
		addAttribute("Descrizione", DESCRIZIONE);
		addTimestampAttribute("Timestamp", TIMESTAMP);
		
		addAttribute("IdSocieta", ID_SOCIETA);
		
		setKeys(ID);
	}

	private void init() throws SQLException {
		configure();
	}

}
