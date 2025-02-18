
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
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.cs.EntitaAzienda;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 11/01/2025
 * <br><br>
 * <b>71823	DSSOF3	11/01/2025</b>
 * <p></p>
 */

public abstract class SendedPackArchismallPO extends EntitaAzienda
		implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static SendedPackArchismall cInstance;

	protected String iIdAnnoFattura;

	protected java.sql.Date iDataFattura;

	protected String iIdNumeroFattura;

	protected char iStatoPacchetto = PacchettoTrasmissione.NON_PROCESSATO;

	protected char iStatoArchismall = StatoPacchettoArchismall.DA_CONSERVARE;

	protected String iIdLancio;

	protected String iIdArchiPro;

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (SendedPackArchismall) Factory.createObject(SendedPackArchismall.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static SendedPackArchismall elementWithKey(String key, int lockType) throws SQLException {
		return (SendedPackArchismall) PersistentObject.elementWithKey(SendedPackArchismall.class, key, lockType);
	}

	public SendedPackArchismallPO() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setIdAnnoFattura(String idAnnoFattura) {
		this.iIdAnnoFattura = idAnnoFattura;
		setDirty();
		setOnDB(false);
	}

	public String getIdAnnoFattura() {
		return iIdAnnoFattura;
	}

	public void setDataFattura(java.sql.Date dataFattura) {
		this.iDataFattura = dataFattura;
		setDirty();
		setOnDB(false);
	}

	public java.sql.Date getDataFattura() {
		return iDataFattura;
	}

	public void setIdNumeroFattura(String idNumeroFattura) {
		this.iIdNumeroFattura = idNumeroFattura;
		setDirty();
		setOnDB(false);
	}

	public String getIdNumeroFattura() {
		return iIdNumeroFattura;
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

	public void setIdLancio(String idLancio) {
		this.iIdLancio = idLancio;
		setDirty();
	}

	public String getIdLancio() {
		return iIdLancio;
	}

	public void setIdArchiPro(String idArchiPro) {
		this.iIdArchiPro = idArchiPro;
		setDirty();
	}

	public String getIdArchiPro() {
		return iIdArchiPro;
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
		SendedPackArchismallPO sendedPackArchismallPO = (SendedPackArchismallPO) obj;
		if (sendedPackArchismallPO.iDataFattura != null)
			iDataFattura = (java.sql.Date) sendedPackArchismallPO.iDataFattura.clone();
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setIdAnnoFattura(KeyHelper.getTokenObjectKey(key, 2));
		setDataFattura(KeyHelper.stringToDate(KeyHelper.getTokenObjectKey(key, 3)));
		setIdNumeroFattura(KeyHelper.getTokenObjectKey(key, 4));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		String idAnnoFattura = getIdAnnoFattura();
		java.sql.Date dataFattura = getDataFattura();
		String idNumeroFattura = getIdNumeroFattura();
		Object[] keyParts = { idAzienda, idAnnoFattura, dataFattura, idNumeroFattura };
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return SendedPackArchismallTM.getInstance();
	}

}
