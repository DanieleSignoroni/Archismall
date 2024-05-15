/*
 * @(#)ConfigurazioneArchismallPO.java
 */

/**
 * null
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
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class ConfigurazioneArchismallPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static ConfigurazioneArchismall cInstance;

  /**
   * Attributo iUrl
   */
  protected String iUrl;

  /**
   * Attributo iClientSecret
   */
  protected String iClientSecret;

  /**
   * Attributo iClientId
   */
  protected String iClientId;

  /**
   * Attributo iIdUtente
   */
  protected String iIdUtente;

  /**
   * Attributo iPassword
   */
  protected String iPassword;

  /**
   * Attributo iToken
   */
  protected String iToken;

  
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
   * 15/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (ConfigurazioneArchismall)Factory.createObject(ConfigurazioneArchismall.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return ConfigurazioneArchismall
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static ConfigurazioneArchismall elementWithKey(String key, int lockType) throws SQLException {
    return (ConfigurazioneArchismall)PersistentObject.elementWithKey(ConfigurazioneArchismall.class, key, lockType);
  }

  /**
   * ConfigurazioneArchismallPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public ConfigurazioneArchismallPO() {
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param url
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setUrl(String url) {
    this.iUrl = url;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getUrl() {
    return iUrl;
  }

  /**
   * Valorizza l'attributo. 
   * @param clientSecret
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setClientSecret(String clientSecret) {
    this.iClientSecret = clientSecret;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getClientSecret() {
    return iClientSecret;
  }

  /**
   * Valorizza l'attributo. 
   * @param clientId
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setClientId(String clientId) {
    this.iClientId = clientId;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getClientId() {
    return iClientId;
  }

  /**
   * Valorizza l'attributo. 
   * @param idUtente
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdUtente(String idUtente) {
    this.iIdUtente = idUtente;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getIdUtente() {
    return iIdUtente;
  }

  /**
   * Valorizza l'attributo. 
   * @param password
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setPassword(String password) {
    this.iPassword = password;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getPassword() {
    return iPassword;
  }

  /**
   * Valorizza l'attributo. 
   * @param token
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setToken(String token) {
    this.iToken = token;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getToken() {
    return iToken;
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
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
   * 15/05/2024    Wizard     Codice generato da Wizard
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
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
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
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setIdAzienda(key);
  }

  /**
   *  getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    return getIdAzienda();
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
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
   * 15/05/2024    Wizard     Codice generato da Wizard
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
   * 15/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return ConfigurazioneArchismallTM.getInstance();
  }

}

