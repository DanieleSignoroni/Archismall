package it.softre.thip.archismall.base.configuration;

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

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.cs.EntitaAzienda;

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

public abstract class ConfigurazioneArchismallPO extends EntitaAzienda
		implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static ConfigurazioneArchismall cInstance;

	protected String iUrl;

	protected String iClientSecret;

	protected String iClientId;

	protected String iIdUtente;

	protected String iPassword;

	protected String iToken;

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (ConfigurazioneArchismall) Factory.createObject(ConfigurazioneArchismall.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static ConfigurazioneArchismall elementWithKey(String key, int lockType) throws SQLException {
		return (ConfigurazioneArchismall) PersistentObject.elementWithKey(ConfigurazioneArchismall.class, key,
				lockType);
	}

	public ConfigurazioneArchismallPO() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setUrl(String url) {
		this.iUrl = url;
		setDirty();
	}

	public String getUrl() {
		return iUrl;
	}

	public void setClientSecret(String clientSecret) {
		this.iClientSecret = clientSecret;
		setDirty();
	}

	public String getClientSecret() {
		return iClientSecret;
	}

	public void setClientId(String clientId) {
		this.iClientId = clientId;
		setDirty();
	}

	public String getClientId() {
		return iClientId;
	}

	public void setIdUtente(String idUtente) {
		this.iIdUtente = idUtente;
		setDirty();
	}

	public String getIdUtente() {
		return iIdUtente;
	}

	public void setPassword(String password) {
		this.iPassword = password;
		setDirty();
	}

	public String getPassword() {
		return iPassword;
	}

	public void setToken(String token) {
		this.iToken = token;
		setDirty();
	}

	public String getToken() {
		return iToken;
	}

	public void setIdAzienda(String idAzienda) {
		iAzienda.setKey(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getIdAzienda() {
		String key = iAzienda.getKey();
		return key;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(key);
	}

	public String getKey() {
		return getIdAzienda();
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return ConfigurazioneArchismallTM.getInstance();
	}

}
