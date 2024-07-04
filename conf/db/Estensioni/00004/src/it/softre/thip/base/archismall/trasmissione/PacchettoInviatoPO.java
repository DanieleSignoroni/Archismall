/*
 * @(#)PacchettoInviatoPO.java
 */

/**
 * null
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
import java.sql.*;
import java.util.*;
import com.thera.thermfw.common.*;
import com.thera.thermfw.security.*;

public abstract class PacchettoInviatoPO extends PersistentObject implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static PacchettoInviato cInstance;

  /**
   * Attributo iIdPacchetto
   */
  protected Integer iIdPacchetto;

  /**
   * Attributo iIdLancio
   */
  protected String iIdLancio;

  /**
   * Attributo iIdArchiPro
   */
  protected String iIdArchiPro;

  /**
   * Attributo iStatoPacchetto
   */
  protected char iStatoPacchetto = '0';

  /**
   * Attributo iStatoArchismall
   */
  protected char iStatoArchismall = '0';

  /**
   * Attributo iDescrErrore
   */
  protected String iDescrErrore;

  /**
   * Attributo iTimestamp
   */
  protected java.sql.Timestamp iTimestamp;

  
  /**
   *  retrieveList
   * @param where
   * @param orderBy
   * @param optimistic
   * @return Vector
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (PacchettoInviato)Factory.createObject(PacchettoInviato.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return PacchettoInviato
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static PacchettoInviato elementWithKey(String key, int lockType) throws SQLException {
    return (PacchettoInviato)PersistentObject.elementWithKey(PacchettoInviato.class, key, lockType);
  }

  /**
   * PacchettoInviatoPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public PacchettoInviatoPO() {
    setStatoPacchetto('0');
    setStatoArchismall('0');
  }

  /**
   * Valorizza l'attributo. 
   * @param idPacchetto
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdPacchetto(Integer idPacchetto) {
    this.iIdPacchetto = idPacchetto;
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return Integer
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public Integer getIdPacchetto() {
    return iIdPacchetto;
  }

  /**
   * Valorizza l'attributo. 
   * @param idLancio
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdLancio(String idLancio) {
    this.iIdLancio = idLancio;
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getIdLancio() {
    return iIdLancio;
  }

  /**
   * Valorizza l'attributo. 
   * @param idArchiPro
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdArchiPro(String idArchiPro) {
    this.iIdArchiPro = idArchiPro;
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getIdArchiPro() {
    return iIdArchiPro;
  }

  /**
   * Valorizza l'attributo. 
   * @param statoPacchetto
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setStatoPacchetto(char statoPacchetto) {
    this.iStatoPacchetto = statoPacchetto;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public char getStatoPacchetto() {
    return iStatoPacchetto;
  }

  /**
   * Valorizza l'attributo. 
   * @param statoArchismall
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setStatoArchismall(char statoArchismall) {
    this.iStatoArchismall = statoArchismall;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public char getStatoArchismall() {
    return iStatoArchismall;
  }

  /**
   * Valorizza l'attributo. 
   * @param descrErrore
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDescrErrore(String descrErrore) {
    this.iDescrErrore = descrErrore;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getDescrErrore() {
    return iDescrErrore;
  }

  /**
   * Valorizza l'attributo. 
   * @param timestamp
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setTimestamp(java.sql.Timestamp timestamp) {
    this.iTimestamp = timestamp;
    
  }

  /**
   * Restituisce l'attributo. 
   * @return java.sql.Timestamp
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.sql.Timestamp getTimestamp() {
    return iTimestamp;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    PacchettoInviatoPO pacchettoInviatoPO = (PacchettoInviatoPO)obj;
    if (pacchettoInviatoPO.iTimestamp != null)
        iTimestamp = (java.sql.Timestamp)pacchettoInviatoPO.iTimestamp.clone();
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public Vector checkAll(BaseComponentsCollection components) {
    Vector errors = new Vector();
    components.runAllChecks(errors);
    return errors;
  }

  /**
   *  setKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setIdLancio(KeyHelper.getTokenObjectKey(key, 1));
    setIdPacchetto(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 2)));
    setIdArchiPro(KeyHelper.getTokenObjectKey(key, 3));
  }

  /**
   *  getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    String idLancio = getIdLancio();
    Integer idPacchetto = getIdPacchetto();
    String idArchiPro = getIdArchiPro();
    Object[] keyParts = {idLancio, idPacchetto, idArchiPro};
    return KeyHelper.buildObjectKey(keyParts);
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public boolean isDeletable() {
    return checkDelete() == null;
  }

  /**
   * toString
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public String toString() {
    return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
  }

  /**
   *  getTableManager
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 04/07/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return PacchettoInviatoTM.getInstance();
  }

}

