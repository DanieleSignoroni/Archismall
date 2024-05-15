/*
 * @(#)PacchetttoTrasmissioneTM.java
 */

/**
 * PacchetttoTrasmissioneTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 15/05/2024 at 08:57:42
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 15/05/2024    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.archismall.trasmissione;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;

public class PacchetttoTrasmissioneTM extends TableManager {

  
  /**
   * Attributo DIZSTATO
   */
  public static final String DIZSTATO = "DIZSTATO";

  /**
   * Attributo DIZUTCRE
   */
  public static final String DIZUTCRE = "DIZUTCRE";

  /**
   * Attributo DIZDTCRE
   */
  public static final String DIZDTCRE = "DIZDTCRE";

  /**
   * Attributo DIZHHCRE
   */
  public static final String DIZHHCRE = "DIZHHCRE";

  /**
   * Attributo DIZUTAGG
   */
  public static final String DIZUTAGG = "DIZUTAGG";

  /**
   * Attributo DIZDTAGG
   */
  public static final String DIZDTAGG = "DIZDTAGG";

  /**
   * Attributo DIZHHAGG
   */
  public static final String DIZHHAGG = "DIZHHAGG";

  /**
   * Attributo T01CD
   */
  public static final String T01CD = "T01CD";

  /**
   * Attributo F9PIDPAC
   */
  public static final String F9PIDPAC = "F9PIDPAC";

  /**
   * Attributo F9PDDES
   */
  public static final String F9PDDES = "F9PDDES";

  /**
   * Attributo T64COPBC
   */
  public static final String T64COPBC = "T64COPBC";

  /**
   * Attributo T64DESNO
   */
  public static final String T64DESNO = "T64DESNO";

  /**
   * Attributo F9PDOCEL
   */
  public static final String F9PDOCEL = "F9PDOCEL";

  /**
   * Attributo F9RIDAS
   */
  public static final String F9RIDAS = "F9RIDAS";

  /**
   * Attributo WF9NRLOG
   */
  public static final String WF9NRLOG = "WF9NRLOG";

  /**
   * Attributo F9PTPPAC
   */
  public static final String F9PTPPAC = "F9PTPPAC";

  /**
   * Attributo F9PRIEME
   */
  public static final String F9PRIEME = "F9PRIEME";

  /**
   * Attributo F9PIDPRI
   */
  public static final String F9PIDPRI = "F9PIDPRI";

  /**
   * Attributo F9GDTINI
   */
  public static final String F9GDTINI = "F9GDTINI";

  /**
   * Attributo F9GDTFIN
   */
  public static final String F9GDTFIN = "F9GDTFIN";

  /**
   * Attributo F9PNRDOC
   */
  public static final String F9PNRDOC = "F9PNRDOC";

  /**
   * Attributo F9PNRDCA
   */
  public static final String F9PNRDCA = "F9PNRDCA";

  /**
   * Attributo F9PNRANN
   */
  public static final String F9PNRANN = "F9PNRANN";

  /**
   * Attributo F9PICONA
   */
  public static final String F9PICONA = "F9PICONA";

  /**
   * Attributo STATO_PACCHETTO
   */
  public static final String STATO_PACCHETTO = "STATO_PACCHETTO";

  /**
   * Attributo TIMESTAMP
   */
  public static final String TIMESTAMP = "TIMESTAMP";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "PACCHETTTO_TRASMISSIONE";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.softre.thip.archismall.trasmissione.PacchetttoTrasmissione.class.getName();

  
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
      cInstance = (TableManager)Factory.createObject(PacchetttoTrasmissioneTM.class);
    }
    return cInstance;
  }

  /**
   *  PacchetttoTrasmissioneTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public PacchetttoTrasmissioneTM() throws SQLException {
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
    addAttribute("Dizstato", DIZSTATO);
    addAttribute("Dizutcre", DIZUTCRE);
    addAttribute("Dizdtcre", DIZDTCRE);
    addAttribute("Dizhhcre", DIZHHCRE);
    addAttribute("Dizutagg", DIZUTAGG);
    addAttribute("Dizdtagg", DIZDTAGG);
    addAttribute("Dizhhagg", DIZHHAGG);
    addAttribute("T01cd", T01CD);
    addAttribute("F9pidpac", F9PIDPAC);
    addAttribute("F9pddes", F9PDDES);
    addAttribute("T64copbc", T64COPBC);
    addAttribute("T64desno", T64DESNO);
    addAttribute("F9pdocel", F9PDOCEL);
    addAttribute("F9ridas", F9RIDAS);
    addAttribute("Wf9nrlog", WF9NRLOG);
    addAttribute("F9ptppac", F9PTPPAC);
    addAttribute("F9prieme", F9PRIEME);
    addAttribute("F9pidpri", F9PIDPRI);
    addAttribute("F9gdtini", F9GDTINI);
    addAttribute("F9gdtfin", F9GDTFIN);
    addAttribute("F9pnrdoc", F9PNRDOC);
    addAttribute("F9pnrdca", F9PNRDCA);
    addAttribute("F9pnrann", F9PNRANN);
    addAttribute("F9picona", F9PICONA);
    addAttribute("StatoPacchetto", STATO_PACCHETTO);
    addTimestampAttribute("Timestamp" , TIMESTAMP);
    setKeys(T01CD + "," + F9PIDPAC);
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
    configure(DIZSTATO + ", " + DIZUTCRE + ", " + DIZDTCRE + ", " + DIZHHCRE
         + ", " + DIZUTAGG + ", " + DIZDTAGG + ", " + DIZHHAGG + ", " + T01CD
         + ", " + F9PIDPAC + ", " + F9PDDES + ", " + T64COPBC + ", " + T64DESNO
         + ", " + F9PDOCEL + ", " + F9RIDAS + ", " + WF9NRLOG + ", " + F9PTPPAC
         + ", " + F9PRIEME + ", " + F9PIDPRI + ", " + F9GDTINI + ", " + F9GDTFIN
         + ", " + F9PNRDOC + ", " + F9PNRDCA + ", " + F9PNRANN + ", " + F9PICONA
         + ", " + STATO_PACCHETTO + ", " + TIMESTAMP);
  }

}

