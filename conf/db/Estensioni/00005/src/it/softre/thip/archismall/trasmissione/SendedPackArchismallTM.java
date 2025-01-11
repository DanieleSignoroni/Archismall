/*
 * @(#)SendedPackArchismallTM.java
 */

/**
 * SendedPackArchismallTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 11/01/2025 at 14:31:32
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 11/01/2025    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.archismall.trasmissione;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class SendedPackArchismallTM extends TableManager {

  
  /**
   * Attributo ID_AZIENDA
   */
  public static final String ID_AZIENDA = "ID_AZIENDA";

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
   * Attributo R_ANNO_FATTURA
   */
  public static final String R_ANNO_FATTURA = "R_ANNO_FATTURA";

  /**
   * Attributo DATA_FATTURA
   */
  public static final String DATA_FATTURA = "DATA_FATTURA";

  /**
   * Attributo R_NUMERO_FATTURA
   */
  public static final String R_NUMERO_FATTURA = "R_NUMERO_FATTURA";

  /**
   * Attributo STATO_PACCHETTO
   */
  public static final String STATO_PACCHETTO = "STATO_PACCHETTO";

  /**
   * Attributo STATO_ARCHISMALL
   */
  public static final String STATO_ARCHISMALL = "STATO_ARCHISMALL";

  /**
   * Attributo R_LANCIO
   */
  public static final String R_LANCIO = "R_LANCIO";

  /**
   * Attributo ID_ARCHI_PRO
   */
  public static final String ID_ARCHI_PRO = "ID_ARCHI_PRO";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "SENDED_PACK_ARCHISMALL";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.softre.thip.archismall.trasmissione.SendedPackArchismall.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(SendedPackArchismallTM.class);
    }
    return cInstance;
  }

  /**
   *  SendedPackArchismallTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public SendedPackArchismallTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    CodeGen     Codice generato da CodeGenerator
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
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
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
    ((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
  }

  /**
   *  init
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(R_ANNO_FATTURA + ", " + DATA_FATTURA + ", " + R_NUMERO_FATTURA + ", " + STATO_PACCHETTO
         + ", " + STATO_ARCHISMALL + ", " + R_LANCIO + ", " + ID_ARCHI_PRO + ", " + ID_AZIENDA
         + ", " + STATO + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG
         + ", " + TIMESTAMP_AGG);
  }

}

