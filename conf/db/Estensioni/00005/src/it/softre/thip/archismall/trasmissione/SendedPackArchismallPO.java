/*
 * @(#)SendedPackArchismallPO.java
 */

/**
 * null
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
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class SendedPackArchismallPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static SendedPackArchismall cInstance;

  /**
   * Attributo iIdAnnoFattura
   */
  protected String iIdAnnoFattura;

  /**
   * Attributo iDataFattura
   */
  protected java.sql.Date iDataFattura;

  /**
   * Attributo iIdNumeroFattura
   */
  protected String iIdNumeroFattura;

  /**
   * Attributo iStatoPacchetto
   */
  protected char iStatoPacchetto = '0';

  /**
   * Attributo iStatoArchismall
   */
  protected char iStatoArchismall = '1';

  /**
   * Attributo iIdLancio
   */
  protected String iIdLancio;

  /**
   * Attributo iIdArchiPro
   */
  protected String iIdArchiPro;

  
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
   * 11/01/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (SendedPackArchismall)Factory.createObject(SendedPackArchismall.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return SendedPackArchismall
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static SendedPackArchismall elementWithKey(String key, int lockType) throws SQLException {
    return (SendedPackArchismall)PersistentObject.elementWithKey(SendedPackArchismall.class, key, lockType);
  }

  /**
   * SendedPackArchismallPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public SendedPackArchismallPO() {
    setStatoPacchetto('0');
    setStatoArchismall('1');
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param idAnnoFattura
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAnnoFattura(String idAnnoFattura) {
    this.iIdAnnoFattura = idAnnoFattura;
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
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAnnoFattura() {
    return iIdAnnoFattura;
  }

  /**
   * Valorizza l'attributo. 
   * @param dataFattura
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setDataFattura(java.sql.Date dataFattura) {
    this.iDataFattura = dataFattura;
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return java.sql.Date
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public java.sql.Date getDataFattura() {
    return iDataFattura;
  }

  /**
   * Valorizza l'attributo. 
   * @param idNumeroFattura
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdNumeroFattura(String idNumeroFattura) {
    this.iIdNumeroFattura = idNumeroFattura;
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
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdNumeroFattura() {
    return iIdNumeroFattura;
  }

  /**
   * Valorizza l'attributo. 
   * @param statoPacchetto
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    Wizard     Codice generato da Wizard
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
   * 11/01/2025    Wizard     Codice generato da Wizard
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
   * 11/01/2025    Wizard     Codice generato da Wizard
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
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public char getStatoArchismall() {
    return iStatoArchismall;
  }

  /**
   * Valorizza l'attributo. 
   * @param idLancio
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    Wizard     Codice generato da Wizard
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
   * 11/01/2025    Wizard     Codice generato da Wizard
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
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdArchiPro(String idArchiPro) {
    this.iIdArchiPro = idArchiPro;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdArchiPro() {
    return iIdArchiPro;
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAzienda(String idAzienda) {
    iAzienda.setKey(idAzienda);
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
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAzienda() {
    String key = iAzienda.getKey();
    return key;
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    SendedPackArchismallPO sendedPackArchismallPO = (SendedPackArchismallPO)obj;
    if (sendedPackArchismallPO.iDataFattura != null)
        iDataFattura = (java.sql.Date)sendedPackArchismallPO.iDataFattura.clone();
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    Wizard     Codice generato da Wizard
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
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
    setIdAnnoFattura(KeyHelper.getTokenObjectKey(key, 2));
    setDataFattura(KeyHelper.stringToDate(KeyHelper.getTokenObjectKey(key, 3)));
    setIdNumeroFattura(KeyHelper.getTokenObjectKey(key, 4));
  }

  /**
   *  getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    String idAzienda = getIdAzienda();
    String idAnnoFattura = getIdAnnoFattura();
    java.sql.Date dataFattura = getDataFattura();
    String idNumeroFattura = getIdNumeroFattura();
    Object[] keyParts = {idAzienda, idAnnoFattura, dataFattura, idNumeroFattura};
    return KeyHelper.buildObjectKey(keyParts);
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 11/01/2025    Wizard     Codice generato da Wizard
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
   * 11/01/2025    Wizard     Codice generato da Wizard
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
   * 11/01/2025    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return SendedPackArchismallTM.getInstance();
  }

}

