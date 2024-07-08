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

import it.softre.thip.archismall.base.generale.StatoPacchettoArchismall;

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

public abstract class PacchettoInviatoPO extends PersistentObject
		implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static PacchettoInviato cInstance;

	protected Integer iIdPacchetto;

	protected String iIdLancio;

	protected String iIdArchiPro;

	protected char iStatoPacchetto = '0';

	protected char iStatoArchismall = '0';

	protected String iDescrErrore;

	protected java.sql.Timestamp iTimestamp;

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (PacchettoInviato) Factory.createObject(PacchettoInviato.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static PacchettoInviato elementWithKey(String key, int lockType) throws SQLException {
		return (PacchettoInviato) PersistentObject.elementWithKey(PacchettoInviato.class, key, lockType);
	}

	public PacchettoInviatoPO() {
		setStatoPacchetto(PacchettoTrasmissione.NON_PROCESSATO);
		setStatoArchismall(StatoPacchettoArchismall.DA_CONSERVARE);
	}

	public void setIdPacchetto(Integer idPacchetto) {
		this.iIdPacchetto = idPacchetto;
		setDirty();
		setOnDB(false);
	}

	public Integer getIdPacchetto() {
		return iIdPacchetto;
	}

	public void setIdLancio(String idLancio) {
		this.iIdLancio = idLancio;
		setDirty();
		setOnDB(false);
	}

	public String getIdLancio() {
		return iIdLancio;
	}

	public void setIdArchiPro(String idArchiPro) {
		this.iIdArchiPro = idArchiPro;
		setDirty();
		setOnDB(false);
	}

	public String getIdArchiPro() {
		return iIdArchiPro;
	}

	public void setStatoPacchetto(char statoPacchetto) {
		this.iStatoPacchetto = statoPacchetto;
		setDirty();
	}

	public char getStatoPacchetto() {
		return iStatoPacchetto;
	}

	public void setStatoArchismall(char statoArchismall) {
		this.iStatoArchismall = statoArchismall;
		setDirty();
	}

	public char getStatoArchismall() {
		return iStatoArchismall;
	}

	public void setDescrErrore(String descrErrore) {
		this.iDescrErrore = descrErrore;
		setDirty();
	}

	public String getDescrErrore() {
		return iDescrErrore;
	}

	public void setTimestamp(java.sql.Timestamp timestamp) {
		this.iTimestamp = timestamp;

	}

	public java.sql.Timestamp getTimestamp() {
		return iTimestamp;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		PacchettoInviatoPO pacchettoInviatoPO = (PacchettoInviatoPO) obj;
		if (pacchettoInviatoPO.iTimestamp != null)
			iTimestamp = (java.sql.Timestamp) pacchettoInviatoPO.iTimestamp.clone();
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdLancio(KeyHelper.getTokenObjectKey(key, 1));
		setIdPacchetto(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 2)));
		setIdArchiPro(KeyHelper.getTokenObjectKey(key, 3));
	}

	public String getKey() {
		String idLancio = getIdLancio();
		Integer idPacchetto = getIdPacchetto();
		String idArchiPro = getIdArchiPro();
		Object[] keyParts = { idLancio, idPacchetto, idArchiPro };
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return PacchettoInviatoTM.getInstance();
	}

}
