package it.softre.thip.archismall.base.configuration;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.TableManager;

import it.thera.thip.cs.DatiComuniEstesiTTM;

public class ConfigurazioneArchismallTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String URL = "URL";

	public static final String CLIENT_SECRET = "CLIENT_SECRET";

	public static final String CLIENT_ID = "CLIENT_ID";

	public static final String ID_UTENTE = "ID_UTENTE";

	public static final String PASSWORD = "PASSWORD";

	public static final String TOKEN = "TOKEN";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "CONFIGURAZIONE_ARCHISMALL";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.softre.thip.archismall.base.configuration.ConfigurazioneArchismall.class
			.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager) Factory.createObject(ConfigurazioneArchismallTM.class);
		}
		return cInstance;
	}

	public ConfigurazioneArchismallTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("Url", URL);
		addAttribute("ClientSecret", CLIENT_SECRET);
		addAttribute("ClientId", CLIENT_ID);
		addAttribute("IdUtente", ID_UTENTE);
		addAttribute("Password", PASSWORD);
		addAttribute("Token", TOKEN);
		addAttribute("IdAzienda", ID_AZIENDA);

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM) getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure(URL + ", " + CLIENT_SECRET + ", " + CLIENT_ID + ", " + ID_UTENTE + ", " + PASSWORD + ", " + TOKEN
				+ ", " + ID_AZIENDA + ", " + STATO + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG
				+ ", " + TIMESTAMP_AGG);
	}

}
