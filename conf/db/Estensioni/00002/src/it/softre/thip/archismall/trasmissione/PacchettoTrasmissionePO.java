/*
 * @(#)PacchettoTrasmissionePO.java
 */

/**
 * null
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
import java.sql.*;
import java.util.*;
import com.thera.thermfw.common.*;
import com.thera.thermfw.security.*;

public abstract class PacchettoTrasmissionePO extends PersistentObject implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static PacchettoTrasmissione cInstance;

  /**
   * Attributo iStatoPacchetto
   */
  protected char iStatoPacchetto = '0';

  /**
   * Attributo iId
   */
  protected Integer iId;

  /**
   * Attributo iIdLancio
   */
  protected String iIdLancio;

  /**
   * Attributo iDataCreazione
   */
  protected java.sql.Date iDataCreazione;

  /**
   * Attributo iNome
   */
  protected String iNome;

  /**
   * Attributo iDescrizione
   */
  protected String iDescrizione;

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
   * 03/07/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (PacchettoTrasmissione)Factory.createObject(PacchettoTrasmissione.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return PacchettoTrasmissione
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static PacchettoTrasmissione elementWithKey(String key, int lockType) throws SQLException {
    return (PacchettoTrasmissione)PersistentObject.elementWithKey(PacchettoTrasmissione.class, key, lockType);
  }

  /**
   * PacchettoTrasmissionePO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public PacchettoTrasmissionePO() {
    setStatoPacchetto('0');
  }

  /**
   * Valorizza l'attributo. 
   * @param statoPacchetto
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
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
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public char getStatoPacchetto() {
    return iStatoPacchetto;
  }

  /**
   * Valorizza l'attributo. 
   * @param id
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setId(Integer id) {
    this.iId = id;
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
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public Integer getId() {
    return iId;
  }

  /**
   * Valorizza l'attributo. 
   * @param idLancio
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdLancio(String idLancio) {
    this.iIdLancio = idLancio;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getIdLancio() {
    return iIdLancio;
  }

  /**
   * Valorizza l'attributo. 
   * @param dataCreazione
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDataCreazione(java.sql.Date dataCreazione) {
    this.iDataCreazione = dataCreazione;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return java.sql.Date
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.sql.Date getDataCreazione() {
    return iDataCreazione;
  }

  /**
   * Valorizza l'attributo. 
   * @param nome
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setNome(String nome) {
    this.iNome = nome;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getNome() {
    return iNome;
  }

  /**
   * Valorizza l'attributo. 
   * @param descrizione
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDescrizione(String descrizione) {
    this.iDescrizione = descrizione;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getDescrizione() {
    return iDescrizione;
  }

  /**
   * Valorizza l'attributo. 
   * @param timestamp
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
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
   * 03/07/2024    Wizard     Codice generato da Wizard
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
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    PacchettoTrasmissionePO pacchettoTrasmissionePO = (PacchettoTrasmissionePO)obj;
    if (pacchettoTrasmissionePO.iDataCreazione != null)
        iDataCreazione = (java.sql.Date)pacchettoTrasmissionePO.iDataCreazione.clone();
    if (pacchettoTrasmissionePO.iTimestamp != null)
        iTimestamp = (java.sql.Timestamp)pacchettoTrasmissionePO.iTimestamp.clone();
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
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
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setId(KeyHelper.stringToIntegerObj(key));
  }

  /**
   *  getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    return KeyHelper.objectToString(getId());
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 03/07/2024    Wizard     Codice generato da Wizard
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
   * 03/07/2024    Wizard     Codice generato da Wizard
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
   * 03/07/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return PacchettoTrasmissioneTM.getInstance();
  }

}

