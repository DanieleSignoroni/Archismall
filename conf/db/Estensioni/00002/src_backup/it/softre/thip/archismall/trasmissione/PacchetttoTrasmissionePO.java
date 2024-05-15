/*
 * @(#)PacchetttoTrasmissionePO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 15/05/2024 at 08:57:03
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 15/05/2024    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.archismall.trasmissione;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import java.math.*;
import com.thera.thermfw.common.*;
import com.thera.thermfw.security.*;

public abstract class PacchetttoTrasmissionePO extends PersistentObject implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static PacchetttoTrasmissione cInstance;

  /**
   * Attributo iDizstato
   */
  protected char iDizstato = '1';

  /**
   * Attributo iDizutcre
   */
  protected String iDizutcre;

  /**
   * Attributo iDizdtcre
   */
  protected java.sql.Date iDizdtcre;

  /**
   * Attributo iDizhhcre
   */
  protected java.sql.Time iDizhhcre;

  /**
   * Attributo iDizutagg
   */
  protected String iDizutagg;

  /**
   * Attributo iDizdtagg
   */
  protected java.sql.Date iDizdtagg;

  /**
   * Attributo iDizhhagg
   */
  protected java.sql.Time iDizhhagg;

  /**
   * Attributo iT01cd
   */
  protected String iT01cd;

  /**
   * Attributo iF9pidpac
   */
  protected String iF9pidpac;

  /**
   * Attributo iF9pddes
   */
  protected String iF9pddes;

  /**
   * Attributo iT64copbc
   */
  protected String iT64copbc;

  /**
   * Attributo iT64desno
   */
  protected String iT64desno;

  /**
   * Attributo iF9pdocel
   */
  protected char iF9pdocel = 'V';

  /**
   * Attributo iF9ridas
   */
  protected String iF9ridas = "00";

  /**
   * Attributo iWf9nrlog
   */
  protected BigDecimal iWf9nrlog;

  /**
   * Attributo iF9ptppac
   */
  protected char iF9ptppac = 'P';

  /**
   * Attributo iF9prieme
   */
  protected char iF9prieme = '0';

  /**
   * Attributo iF9pidpri
   */
  protected String iF9pidpri;

  /**
   * Attributo iF9gdtini
   */
  protected java.sql.Date iF9gdtini;

  /**
   * Attributo iF9gdtfin
   */
  protected java.sql.Date iF9gdtfin;

  /**
   * Attributo iF9pnrdoc
   */
  protected BigDecimal iF9pnrdoc;

  /**
   * Attributo iF9pnrdca
   */
  protected BigDecimal iF9pnrdca;

  /**
   * Attributo iF9pnrann
   */
  protected BigDecimal iF9pnrann;

  /**
   * Attributo iF9picona
   */
  protected char iF9picona = '1';

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
   * 15/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (PacchetttoTrasmissione)Factory.createObject(PacchetttoTrasmissione.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return PacchetttoTrasmissione
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static PacchetttoTrasmissione elementWithKey(String key, int lockType) throws SQLException {
    return (PacchetttoTrasmissione)PersistentObject.elementWithKey(PacchetttoTrasmissione.class, key, lockType);
  }

  /**
   * PacchetttoTrasmissionePO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public PacchetttoTrasmissionePO() {
    setDizstato('1');
    setF9pdocel('V');
    setF9ridas("00");
    setF9ptppac('P');
    setF9prieme('0');
    setF9picona('1');
  }

  /**
   * Valorizza l'attributo. 
   * @param dizstato
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDizstato(char dizstato) {
    this.iDizstato = dizstato;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public char getDizstato() {
    return iDizstato;
  }

  /**
   * Valorizza l'attributo. 
   * @param dizutcre
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDizutcre(String dizutcre) {
    this.iDizutcre = dizutcre;
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
  public String getDizutcre() {
    return iDizutcre;
  }

  /**
   * Valorizza l'attributo. 
   * @param dizdtcre
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDizdtcre(java.sql.Date dizdtcre) {
    this.iDizdtcre = dizdtcre;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return java.sql.Date
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.sql.Date getDizdtcre() {
    return iDizdtcre;
  }

  /**
   * Valorizza l'attributo. 
   * @param dizhhcre
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDizhhcre(java.sql.Time dizhhcre) {
    this.iDizhhcre = dizhhcre;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return java.sql.Time
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.sql.Time getDizhhcre() {
    return iDizhhcre;
  }

  /**
   * Valorizza l'attributo. 
   * @param dizutagg
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDizutagg(String dizutagg) {
    this.iDizutagg = dizutagg;
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
  public String getDizutagg() {
    return iDizutagg;
  }

  /**
   * Valorizza l'attributo. 
   * @param dizdtagg
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDizdtagg(java.sql.Date dizdtagg) {
    this.iDizdtagg = dizdtagg;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return java.sql.Date
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.sql.Date getDizdtagg() {
    return iDizdtagg;
  }

  /**
   * Valorizza l'attributo. 
   * @param dizhhagg
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDizhhagg(java.sql.Time dizhhagg) {
    this.iDizhhagg = dizhhagg;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return java.sql.Time
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.sql.Time getDizhhagg() {
    return iDizhhagg;
  }

  /**
   * Valorizza l'attributo. 
   * @param t01cd
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setT01cd(String t01cd) {
    this.iT01cd = t01cd;
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
  public String getT01cd() {
    return iT01cd;
  }

  /**
   * Valorizza l'attributo. 
   * @param f9pidpac
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setF9pidpac(String f9pidpac) {
    this.iF9pidpac = f9pidpac;
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
  public String getF9pidpac() {
    return iF9pidpac;
  }

  /**
   * Valorizza l'attributo. 
   * @param f9pddes
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setF9pddes(String f9pddes) {
    this.iF9pddes = f9pddes;
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
  public String getF9pddes() {
    return iF9pddes;
  }

  /**
   * Valorizza l'attributo. 
   * @param t64copbc
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setT64copbc(String t64copbc) {
    this.iT64copbc = t64copbc;
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
  public String getT64copbc() {
    return iT64copbc;
  }

  /**
   * Valorizza l'attributo. 
   * @param t64desno
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setT64desno(String t64desno) {
    this.iT64desno = t64desno;
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
  public String getT64desno() {
    return iT64desno;
  }

  /**
   * Valorizza l'attributo. 
   * @param f9pdocel
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setF9pdocel(char f9pdocel) {
    this.iF9pdocel = f9pdocel;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public char getF9pdocel() {
    return iF9pdocel;
  }

  /**
   * Valorizza l'attributo. 
   * @param f9ridas
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setF9ridas(String f9ridas) {
    this.iF9ridas = f9ridas;
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
  public String getF9ridas() {
    return iF9ridas;
  }

  /**
   * Valorizza l'attributo. 
   * @param wf9nrlog
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setWf9nrlog(BigDecimal wf9nrlog) {
    this.iWf9nrlog = wf9nrlog;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getWf9nrlog() {
    return iWf9nrlog;
  }

  /**
   * Valorizza l'attributo. 
   * @param f9ptppac
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setF9ptppac(char f9ptppac) {
    this.iF9ptppac = f9ptppac;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public char getF9ptppac() {
    return iF9ptppac;
  }

  /**
   * Valorizza l'attributo. 
   * @param f9prieme
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setF9prieme(char f9prieme) {
    this.iF9prieme = f9prieme;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public char getF9prieme() {
    return iF9prieme;
  }

  /**
   * Valorizza l'attributo. 
   * @param f9pidpri
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setF9pidpri(String f9pidpri) {
    this.iF9pidpri = f9pidpri;
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
  public String getF9pidpri() {
    return iF9pidpri;
  }

  /**
   * Valorizza l'attributo. 
   * @param f9gdtini
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setF9gdtini(java.sql.Date f9gdtini) {
    this.iF9gdtini = f9gdtini;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return java.sql.Date
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.sql.Date getF9gdtini() {
    return iF9gdtini;
  }

  /**
   * Valorizza l'attributo. 
   * @param f9gdtfin
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setF9gdtfin(java.sql.Date f9gdtfin) {
    this.iF9gdtfin = f9gdtfin;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return java.sql.Date
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.sql.Date getF9gdtfin() {
    return iF9gdtfin;
  }

  /**
   * Valorizza l'attributo. 
   * @param f9pnrdoc
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setF9pnrdoc(BigDecimal f9pnrdoc) {
    this.iF9pnrdoc = f9pnrdoc;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getF9pnrdoc() {
    return iF9pnrdoc;
  }

  /**
   * Valorizza l'attributo. 
   * @param f9pnrdca
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setF9pnrdca(BigDecimal f9pnrdca) {
    this.iF9pnrdca = f9pnrdca;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getF9pnrdca() {
    return iF9pnrdca;
  }

  /**
   * Valorizza l'attributo. 
   * @param f9pnrann
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setF9pnrann(BigDecimal f9pnrann) {
    this.iF9pnrann = f9pnrann;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getF9pnrann() {
    return iF9pnrann;
  }

  /**
   * Valorizza l'attributo. 
   * @param f9picona
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setF9picona(char f9picona) {
    this.iF9picona = f9picona;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public char getF9picona() {
    return iF9picona;
  }

  /**
   * Valorizza l'attributo. 
   * @param timestamp
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 15/05/2024    Wizard     Codice generato da Wizard
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
   * 15/05/2024    Wizard     Codice generato da Wizard
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
   * 15/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    PacchetttoTrasmissionePO pacchetttoTrasmissionePO = (PacchetttoTrasmissionePO)obj;
    if (pacchetttoTrasmissionePO.iDizdtcre != null)
        iDizdtcre = (java.sql.Date)pacchetttoTrasmissionePO.iDizdtcre.clone();
    if (pacchetttoTrasmissionePO.iDizhhcre != null)
        iDizhhcre = (java.sql.Time)pacchetttoTrasmissionePO.iDizhhcre.clone();
    if (pacchetttoTrasmissionePO.iDizdtagg != null)
        iDizdtagg = (java.sql.Date)pacchetttoTrasmissionePO.iDizdtagg.clone();
    if (pacchetttoTrasmissionePO.iDizhhagg != null)
        iDizhhagg = (java.sql.Time)pacchetttoTrasmissionePO.iDizhhagg.clone();
    if (pacchetttoTrasmissionePO.iF9gdtini != null)
        iF9gdtini = (java.sql.Date)pacchetttoTrasmissionePO.iF9gdtini.clone();
    if (pacchetttoTrasmissionePO.iF9gdtfin != null)
        iF9gdtfin = (java.sql.Date)pacchetttoTrasmissionePO.iF9gdtfin.clone();
    if (pacchetttoTrasmissionePO.iTimestamp != null)
        iTimestamp = (java.sql.Timestamp)pacchetttoTrasmissionePO.iTimestamp.clone();
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
    setT01cd(KeyHelper.getTokenObjectKey(key, 1));
    setF9pidpac(KeyHelper.getTokenObjectKey(key, 2));
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
    String t01cd = getT01cd();
    String f9pidpac = getF9pidpac();
    Object[] keyParts = {t01cd, f9pidpac};
    return KeyHelper.buildObjectKey(keyParts);
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
    return PacchetttoTrasmissioneTM.getInstance();
  }

}

