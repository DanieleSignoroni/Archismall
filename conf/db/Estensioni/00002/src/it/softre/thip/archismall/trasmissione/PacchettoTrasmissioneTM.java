/*
 * @(#)PacchettoTrasmissioneTM.java
 */

/**
 * PacchettoTrasmissioneTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 03/07/2024 at 09:28:03
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 03/07/2024    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.archismall.trasmissione;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;

public class PacchettoTrasmissioneTM extends TableManager {

  
  /**
   * Attributo STATO_PACCHETTO
   */
  public static final String STATO_PACCHETTO = "STATO_PACCHETTO";

  /**
   * Attributo ID
   */
  public static final String ID = "ID";

  /**
   * Attributo ID_LANCIO
   */
  public static final String ID_LANCIO = "ID_LANCIO";

  /**
   * Attributo DATA_CREAZIONE
   */
  public static final String DATA_CREAZIONE = "DATA_CREAZIONE";

  /**
   * Attributo NOME
   */
  public static final String NOME = "NOME";

  /**
   * Attributo DESCRIZIONE
   */
  public static final String DESCRIZIONE = "DESCRIZIONE";

  /**
   * Attributo TIMESTAMP
   */
  public static final String TIMESTAMP = "TIMESTAMP";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "PACCHETTO_TRASMISSIONE";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.softre.thip.archismall.trasmissione.PacchettoTrasmissione.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(PacchettoTrasmissioneTM.class);
    }
    return cInstance;
  }

  /**
   *  PacchettoTrasmissioneTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public PacchettoTrasmissioneTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    CodeGen     Codice generato da CodeGenerator
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
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    addAttribute("StatoPacchetto", STATO_PACCHETTO);
    addAttribute("Id", ID, "getIntegerObject");
    addAttribute("IdLancio", ID_LANCIO);
    addAttribute("DataCreazione", DATA_CREAZIONE);
    addAttribute("Nome", NOME);
    addAttribute("Descrizione", DESCRIZIONE);
    addTimestampAttribute("Timestamp" , TIMESTAMP);
    setKeys(ID);
  }

  /**
   *  init
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(STATO_PACCHETTO + ", " + ID + ", " + ID_LANCIO + ", " + DATA_CREAZIONE
         + ", " + NOME + ", " + DESCRIZIONE + ", " + TIMESTAMP);
  }

}

