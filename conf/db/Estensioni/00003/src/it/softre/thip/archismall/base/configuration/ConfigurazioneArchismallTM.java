/*
 * @(#)ConfigurazioneArchismallTM.java
 */

/**
 * ConfigurazioneArchismallTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 15/05/2024 at 12:11:50
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 15/05/2024    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.archismall.base.configuration;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class ConfigurazioneArchismallTM extends TableManager {

  
  /**
   * Attributo ID_AZIENDA
   */
  public static final String ID_AZIENDA = "ID_AZIENDA";

  /**
   * Attributo URL
   */
  public static final String URL = "URL";

  /**
   * Attributo CLIENT_SECRET
   */
  public static final String CLIENT_SECRET = "CLIENT_SECRET";

  /**
   * Attributo CLIENT_ID
   */
  public static final String CLIENT_ID = "CLIENT_ID";

  /**
   * Attributo ID_UTENTE
   */
  public static final String ID_UTENTE = "ID_UTENTE";

  /**
   * Attributo PASSWORD
   */
  public static final String PASSWORD = "PASSWORD";

  /**
   * Attributo TOKEN
   */
  public static final String TOKEN = "TOKEN";

  /**
   * Attributo STATO
   */
  public static final String STATO = "STATO";

  /**
   * Attributo R_UTENTE_CRZ
   */
  public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

  /**
   * Attributo TIMESTAMP_CRZ
   */
  public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

  /**
   * Attributo R_UTENTE_AGG
   */
  public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

  /**
   * Attributo TIMESTAMP_AGG
   */
  public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "CONFIGURAZIONE_ARCHISMALL";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.softre.thip.archismall.base.configuration.ConfigurazioneArchismall.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(ConfigurazioneArchismallTM.class);
    }
    return cInstance;
  }

  /**
   *  ConfigurazioneArchismallTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public ConfigurazioneArchismallTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected void initialize() throws SQLException {
    setTableName(TABLE_NAME);
    setObjClassName(CLASS_NAME);
    init();
  }

  /**
   *  initializeRelation
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
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
    ((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
  }

  /**
   *  init
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(URL + ", " + CLIENT_SECRET + ", " + CLIENT_ID + ", " + ID_UTENTE
         + ", " + PASSWORD + ", " + TOKEN + ", " + ID_AZIENDA + ", " + STATO
         + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG
        );
  }

}

