package it.softre.thip.archismall.trasmissione;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Database;

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

	private static final String INSERT_REPLICA_PACCHETTI = "INSERT "
			+ "	INTO "
			+ "	SOFTRE.PACCHETTO_TRASMISSIONE "
			+ "(STATO_PACCHETTO, "
			+ "	ID, "
			+ "	ID_LANCIO, "
			+ "	DATA_CREAZIONE, "
			+ "	NOME, "
			+ "	DESCRIZIONE, "
			+ "	TIMESTAMP) "
			+ "SELECT "
			+ "	'"+NON_PROCESSATO+"', "
			+ "	ID, "
			+ "	ID_LANCIO, "
			+ "	DATA_CREAZIONE, "
			+ "	NOME, "
			+ "	DESCRIZIONE, "
			+ "	CURRENT_TIMESTAMP "
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
			+ "		AND P.STATO_PACCHETTO = '"+PROCESSATO+"' "
			+ ")";

	private static final String STMT_SELECT_SUBMISSION_PACK_DETT = "SELECT * FROM FP.SUBMISSION_PACK_DETT P "
			+ "WHERE P.ID_LANCIO = ? ";

	public static CachedStatement cRetrieveSubmissionPackDettaglio = new CachedStatement(STMT_SELECT_SUBMISSION_PACK_DETT);

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
		CachedStatement cs = new CachedStatement(INSERT_REPLICA_PACCHETTI);
		try {
			ris = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}finally {
			try {
				if(cs != null) {
					cs.free();
				}
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return ris;
	}

	@SuppressWarnings({ "unchecked" })
	protected List<SubmissionPackDett> submissionPacksDettaglioDaPacchetto() {
		ResultSet rs = null;
		SubmissionPackDettRsIterator iter = null;
		try{
			PreparedStatement ps = cRetrieveSubmissionPackDettaglio.getStatement();
			Database db = ConnectionManager.getCurrentDatabase();
			db.setString(ps, 1, getIdLancio());
			rs = ps.executeQuery();
			iter = new SubmissionPackDettRsIterator(rs);
			return IteratorUtils.toList(iter);
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
		return null;
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

}

