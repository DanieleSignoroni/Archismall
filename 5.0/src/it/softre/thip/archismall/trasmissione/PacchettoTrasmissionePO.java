package it.softre.thip.archismall.trasmissione;

import java.sql.SQLException;
import java.util.Vector;

import com.thera.thermfw.common.BaseComponentsCollection;
import com.thera.thermfw.common.BusinessObject;
import com.thera.thermfw.common.Deletable;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.TableManager;
import com.thera.thermfw.security.Authorizable;
import com.thera.thermfw.security.Conflictable;

/**
 * 
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 08/07/2024
 * <br><br>
 * <b>71578	DSSOF3	08/07/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

public abstract class PacchettoTrasmissionePO extends PersistentObject
		implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static PacchettoTrasmissione cInstance;

	protected char iStatoPacchetto = '0';

	protected Integer iId;

	protected String iIdLancio;

	protected java.sql.Date iDataCreazione;

	protected String iNome;

	protected String iDescrizione;

	protected java.sql.Timestamp iTimestamp;
	
	protected Integer iIdSocieta;

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (PacchettoTrasmissione) Factory.createObject(PacchettoTrasmissione.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static PacchettoTrasmissione elementWithKey(String key, int lockType) throws SQLException {
		return (PacchettoTrasmissione) PersistentObject.elementWithKey(PacchettoTrasmissione.class, key, lockType);
	}

	public PacchettoTrasmissionePO() {
		setStatoPacchetto('0');
	}

	public void setStatoPacchetto(char statoPacchetto) {
		this.iStatoPacchetto = statoPacchetto;
		setDirty();
	}

	public char getStatoPacchetto() {
		return iStatoPacchetto;
	}

	public void setId(Integer id) {
		this.iId = id;
		setDirty();
		setOnDB(false);
	}

	public Integer getId() {
		return iId;
	}

	public void setIdLancio(String idLancio) {
		this.iIdLancio = idLancio;
		setDirty();
	}

	public String getIdLancio() {
		return iIdLancio;
	}

	public void setDataCreazione(java.sql.Date dataCreazione) {
		this.iDataCreazione = dataCreazione;
		setDirty();
	}

	public java.sql.Date getDataCreazione() {
		return iDataCreazione;
	}

	public void setNome(String nome) {
		this.iNome = nome;
		setDirty();
	}

	public String getNome() {
		return iNome;
	}

	public void setDescrizione(String descrizione) {
		this.iDescrizione = descrizione;
		setDirty();
	}

	public String getDescrizione() {
		return iDescrizione;
	}

	public void setTimestamp(java.sql.Timestamp timestamp) {
		this.iTimestamp = timestamp;

	}

	public java.sql.Timestamp getTimestamp() {
		return iTimestamp;
	}
	
	public Integer getIdSocieta() {
		return iIdSocieta;
	}

	public void setIdSocieta(Integer iIdSocieta) {
		this.iIdSocieta = iIdSocieta;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		PacchettoTrasmissionePO pacchettoTrasmissionePO = (PacchettoTrasmissionePO) obj;
		if (pacchettoTrasmissionePO.iDataCreazione != null)
			iDataCreazione = (java.sql.Date) pacchettoTrasmissionePO.iDataCreazione.clone();
		if (pacchettoTrasmissionePO.iTimestamp != null)
			iTimestamp = (java.sql.Timestamp) pacchettoTrasmissionePO.iTimestamp.clone();
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setId(KeyHelper.stringToIntegerObj(key));
	}

	public String getKey() {
		return KeyHelper.objectToString(getId());
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return PacchettoTrasmissioneTM.getInstance();
	}

}
