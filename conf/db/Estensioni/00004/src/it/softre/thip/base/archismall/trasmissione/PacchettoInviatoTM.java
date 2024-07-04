/*
 * @(#)PacchettoInviatoTM.java
 */

/**
 * PacchettoInviatoTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 04/07/2024 at 10:20:19
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 04/07/2024    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.base.archismall.trasmissione;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;

public class PacchettoInviatoTM extends TableManager {

  
  /**
   * Attributo ID_PACCHETTO
   */
  public static final String ID_PACCHETTO = "ID_PACCHETTO";

  /**
   * Attributo ID_LANCIO
   */
  public static final String ID_LANCIO = "ID_LANCIO";

  /**
   * Attributo ID_ARCHI_PRO
   */
  public static final String ID_ARCHI_PRO = "ID_ARCHI_PRO";

  /**
   * Attributo STATO_PACCHETTO
   */
  public static final String STATO_PACCHETTO = "STATO_PACCHETTO";

  /**
   * Attributo STATO_ARCHISMALL
   */
  public static final String STATO_ARCHISMALL = "STATO_ARCHISMALL";

  /**
   * Attributo DESCR_ERRORE
   */
  public static final String DESCR_ERRORE = "DESCR_ERRORE";

  /**
   * Attributo TIMESTAMP
   */
  public static final String TIMESTAMP = "TIMESTAMP";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "PACCHETTO_INVIATO";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.softre.thip.base.archismall.trasmissione.PacchettoInviato.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(PacchettoInviatoTM.class);
    }
    return cInstance;
  }

  /**
   *  PacchettoInviatoTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public PacchettoInviatoTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    CodeGen     Codice generato da CodeGenerator
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
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    addAttribute("IdPacchetto", ID_PACCHETTO, "getIntegerObject");
    addAttribute("IdLancio", ID_LANCIO);
    addAttribute("IdArchiPro", ID_ARCHI_PRO);
    addAttribute("StatoPacchetto", STATO_PACCHETTO);
    addAttribute("StatoArchismall", STATO_ARCHISMALL);
    addAttribute("DescrErrore", DESCR_ERRORE);
    addTimestampAttribute("Timestamp" , TIMESTAMP);
    setKeys(ID_LANCIO + "," + ID_PACCHETTO + "," + ID_ARCHI_PRO);
  }

  /**
   *  init
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(ID_PACCHETTO + ", " + ID_LANCIO + ", " + ID_ARCHI_PRO + ", " + STATO_PACCHETTO
         + ", " + STATO_ARCHISMALL + ", " + DESCR_ERRORE + ", " + TIMESTAMP);
  }

}

