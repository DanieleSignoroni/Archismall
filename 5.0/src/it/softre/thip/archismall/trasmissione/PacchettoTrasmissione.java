package it.softre.thip.archismall.trasmissione;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Database;

import it.thera.thip.base.azienda.Azienda;

/**
 * Rappresenta un pacchettone da inviare verso Archismall.<br>
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 08/07/2024
 * <br><br>
 * <b>71578	DSSOF3	08/07/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

public class PacchettoTrasmissione extends PacchettoTrasmissionePO {

	public static final char NON_PROCESSATO = '0';
	public static final char PROCESSATO = '1';
	public static final char PROCESSATO_CON_ERRORE = '2';

	public static final String STMT_SELECT_SOCIETA = "SELECT ID FROM FP.SOCIETA WHERE CARTELLA = ? ";

	public static CachedStatement cSelectSocietaByCompany = new CachedStatement(STMT_SELECT_SOCIETA);
	
	public static final String STMT_SELECT_ID_AZIENDA_FROM_SOCIETA = "SELECT CARTELLA FROM FP.SOCIETA WHERE ID = ? ";
	public static CachedStatement cSelectAziendaBySocieta = new CachedStatement(STMT_SELECT_ID_AZIENDA_FROM_SOCIETA);

	private static final String INSERT_REPLICA_PACCHETTI = "INSERT "
			+ "	INTO "
			+ "	SOFTRE.PACCHETTO_TRASMISSIONE "
			+ "(STATO_PACCHETTO, "
			+ "	ID, "
			+ "	ID_LANCIO, "
			+ "	DATA_CREAZIONE, "
			+ "	NOME, "
			+ "	DESCRIZIONE, "
			+ "	TIMESTAMP,ID_SOCIETA) "
			+ "SELECT "
			+ "	'"+NON_PROCESSATO+"', "
			+ "	ID, "
			+ "	ID_LANCIO, "
			+ "	DATA_CREAZIONE, "
			+ "	NOME, "
			+ "	DESCRIZIONE, "
			+ "	CURRENT_TIMESTAMP,ID_SOCIETA "
			+ "FROM "
			+ "	FP.SUBMISSION_PACK D "
			+ "WHERE "
			+ "	NOT EXISTS ( "
			+ "	SELECT "
			+ "		* "
			+ "	FROM "
			+ "		SOFTRE.PACCHETTO_TRASMISSIONE P "
			+ "	WHERE "
			+ "		D.ID = P.ID "
			+ ")";

	public static CachedStatement cInsertPacchettiReplicati = new CachedStatement(INSERT_REPLICA_PACCHETTI);

	private static final String STMT_SELECT_SUBMISSION_PACK_DETT = "SELECT * FROM FP.SUBMISSION_PACK_DETT P "
			+ "WHERE P.ID_LANCIO = ? ";

	public static CachedStatement cRetrieveSubmissionPackDettaglio = new CachedStatement(STMT_SELECT_SUBMISSION_PACK_DETT);

	private static final String STMT_TIPO_PACK = "SELECT TIPO FROM FP.SUBMISSION_PACK P "
			+ "WHERE P.ID_LANCIO = ? ";

	public static CachedStatement cRetrieveTipoPacchetto = new CachedStatement(STMT_TIPO_PACK);

	public ErrorMessage checkDelete() {
		return null;
	}

	public PacchettoTrasmissione() {
		setStatoPacchetto(NON_PROCESSATO);
	}

	@Override
	public int delete() throws SQLException {
		int rcDel = super.delete();
		if(rcDel > 0) {
			//devo anche cancellare tutti i pacchetti figli
		}
		return rcDel;
	}

	/**
	 * @author Daniele Signoroni 14/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * Si occupa di replicare i pacchetti della vista <code>FP.SUBMISSION_PACK</code> nella tabella personalizzata : 
	 * {@value PacchetttoTrasmissioneTM#TABLE_NAME}, solo per i record non gia' processati verso Archismall.<br>
	 * </p>
	 * @return
	 */
	public static int replicaPacchettiFP() {
		int ris = 0;
		PreparedStatement ps = null;
		try {
			ps = cInsertPacchettiReplicati.getStatement();
			//Database db = ConnectionManager.getCurrentDatabase();
			//db.setString(ps, 1, getIdSocietaAziendaCorrente());
			ris = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}finally {
			try {
				ps.close();
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return ris;
	}

	public static Integer getIdSocietaPacchetto(Integer id) {
		String stmt = "SELECT ID_SOCIETA FROM FP.SUBMISSION_PACK WHERE ID = '"+id+"' ";
		ResultSet rs = null;
		CachedStatement cs = new CachedStatement(stmt);
		try {
			rs = cs.executeQuery();
			if(rs.next()) {
				return rs.getInt("ID_SOCIETA");
			}
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(cs != null)
					cs.free();
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return 0;
	}

	public static String getIdSocietaAziendaCorrente() {
		String idSocieta = null;
		ResultSet rs = null;
		try{
			PreparedStatement ps = cSelectSocietaByCompany.getStatement();
			Database db = ConnectionManager.getCurrentDatabase();
			db.setString(ps, 1, Azienda.getAziendaCorrente());
			rs = ps.executeQuery();
			if(rs.next()) {
				idSocieta = Integer.valueOf(rs.getInt("ID")).toString();
			}
		}
		catch(SQLException e){
			e.printStackTrace(Trace.excStream);
		}
		finally{
			try{
				rs.close();
			}
			catch(SQLException e){
				e.printStackTrace(Trace.excStream);
			}
		}
		return idSocieta;
	}
	
	public String getIdAziendaPacchetto() {
		String idSocieta = null;
		ResultSet rs = null;
		try{
			PreparedStatement ps = cSelectAziendaBySocieta.getStatement();
			Database db = ConnectionManager.getCurrentDatabase();
			db.setString(ps, 1, getIdSocieta().toString());
			rs = ps.executeQuery();
			if(rs.next()) {
				idSocieta = rs.getString("CARTELLA");
			}
		}
		catch(SQLException e){
			e.printStackTrace(Trace.excStream);
		}
		finally{
			try{
				rs.close();
			}
			catch(SQLException e){
				e.printStackTrace(Trace.excStream);
			}
		}
		return idSocieta;
	}

	@SuppressWarnings({ })
	protected List<SubmissionPackDett> submissionPacksDettaglioDaPacchetto() {
		ResultSet rs = null;
		SubmissionPackDettRsIterator iter = null;
		List<SubmissionPackDett> submissionPackDettList = new ArrayList<SubmissionPackDett>();
		try{
			PreparedStatement ps = cRetrieveSubmissionPackDettaglio.getStatement();
			Database db = ConnectionManager.getCurrentDatabase();
			db.setString(ps, 1, getIdLancio());
			rs = ps.executeQuery();
			iter = new SubmissionPackDettRsIterator(rs);
			while(iter.hasNext()) {
				submissionPackDettList.add((SubmissionPackDett) iter.next());
			}
		}
		catch(SQLException e){
			e.printStackTrace(Trace.excStream);
		}
		finally{
			try{
				iter.closeCursor();
			}
			catch(SQLException e){
				e.printStackTrace(Trace.excStream);
			}
		}
		return submissionPackDettList;
	}

	@SuppressWarnings("unchecked")
	public List<PacchettoInviato> pacchettiInviati(){
		List<PacchettoInviato> pacchetti = new ArrayList<PacchettoInviato>();
		try {
			pacchetti = PacchettoInviato.retrieveList(PacchettoInviato.class,
					" "+PacchettoInviatoTM.ID_LANCIO+" = '"+getIdLancio()+"' ",
					""+PacchettoInviatoTM.ID_PACCHETTO+" ASC ", false);
		} catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
		return pacchetti;

	}

	public static Character getTipoPacchettone(String idLancio) {
		ResultSet rs = null;
		try{
			PreparedStatement ps = cRetrieveTipoPacchetto.getStatement();
			Database db = ConnectionManager.getCurrentDatabase();
			db.setString(ps, 1, idLancio);
			rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1).charAt(0);
			}
		}
		catch(SQLException e){
			e.printStackTrace(Trace.excStream);
		}
		finally{
			try{
				rs.close();
			}
			catch(SQLException e){
				e.printStackTrace(Trace.excStream);
			}
		}
		return null;
	}

}

