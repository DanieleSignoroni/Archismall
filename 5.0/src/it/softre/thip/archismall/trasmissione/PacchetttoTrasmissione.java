package it.softre.thip.archismall.trasmissione;

import com.thera.thermfw.persist.*;

import java.sql.*;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 15/05/2024
 * <br><br>
 * <b>71XXX	DSSOF3	15/05/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

public class PacchetttoTrasmissione extends PacchetttoTrasmissionePO {

	public static final char NON_PROCESSATO = '0';
	public static final char PROCESSATO = '1';

	private static final String INSERT_REPLICA_PACCHETTI = "INSERT "
			+ "	INTO "
			+ "	SOFTRE.PACCHETTTO_TRASMISSIONE (DIZSTATO, "
			+ "	DIZUTCRE, "
			+ "	DIZDTCRE, "
			+ "	DIZHHCRE, "
			+ "	DIZUTAGG, "
			+ "	DIZDTAGG, "
			+ "	DIZHHAGG, "
			+ "	T01CD, "
			+ "	F9PIDPAC, "
			+ "	F9PDDES, "
			+ "	T64COPBC, "
			+ "	T64DESNO, "
			+ "	F9PDOCEL, "
			+ "	F9RIDAS, "
			+ "	WF9NRLOG, "
			+ "	F9PTPPAC, "
			+ "	F9PRIEME, "
			+ "	F9PIDPRI, "
			+ "	F9GDTINI, "
			+ "	F9GDTFIN, "
			+ "	F9PNRDOC, "
			+ "	F9PNRDCA, "
			+ "	F9PNRANN, "
			+ "	F9PICONA, "
			+ "	STATO_PACCHETTO, "
			+ "	[TIMESTAMP]) "
			+ "SELECT "
			+ "	DIZSTATO, "
			+ "	DIZUTCRE, "
			+ "	DIZDTCRE, "
			+ "	DIZHHCRE, "
			+ "	DIZUTAGG, "
			+ "	DIZDTAGG, "
			+ "	DIZHHAGG, "
			+ "	T01CD, "
			+ "	F9PIDPAC, "
			+ "	F9PDDES, "
			+ "	T64COPBC, "
			+ "	T64DESNO, "
			+ "	F9PDOCEL, "
			+ "	F9RIDAS, "
			+ "	WF9NRLOG, "
			+ "	F9PTPPAC, "
			+ "	F9PRIEME, "
			+ "	F9PIDPRI, "
			+ "	F9GDTINI, "
			+ "	F9GDTFIN, "
			+ "	F9PNRDOC, "
			+ "	F9PNRDCA, "
			+ "	F9PNRANN, "
			+ "	F9PICONA, "
			+ "	'"+NON_PROCESSATO+"', "
			+ "	CURRENT_TIMESTAMP "
			+ "FROM "
			+ "	FINANCE.BCF9PVG F "
			+ "WHERE NOT EXISTS ( "
			+ "	SELECT * FROM SOFTRE.PACCHETTTO_TRASMISSIONE P "
			+ "	WHERE P.T01CD = F.T01CD "
			+ "	AND P.F9PIDPAC = F.F9PIDPAC "
			+ "	AND P.STATO_PACCHETTO = '"+PROCESSATO+"' "
			+ ") ";

	private static final String STMT_SELECT_SUBMISSION_PACK_DETT = "SELECT * FROM FP.SUBMISSION_PACK_DETT P "
			+ "WHERE P.ID_LANCIO = ? ";

	public static CachedStatement cRetrieveSubmissionPackDettaglio = new CachedStatement(STMT_SELECT_SUBMISSION_PACK_DETT);

	public ErrorMessage checkDelete() {
		return null;
	}

	public PacchetttoTrasmissione() {
		setStatoPacchetto(NON_PROCESSATO);
	}

	/**
	 * @author Daniele Signoroni 14/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * Si occupa di replicare i pacchetti della vista <code>FINANCE.BCF9PVG</code> nella tabella personalizzata : 
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

	@SuppressWarnings("rawtypes")
	protected List<SubmissionPackDett> submissionPacksDettaglioDaPacchetto() {
		ResultSet rs = null;
		SubmissionPackDettRsIterator iter = null;
		try{
			PreparedStatement ps = cRetrieveSubmissionPackDettaglio.getStatement();
			Database db = ConnectionManager.getCurrentDatabase();
			db.setString(ps, 1, getF9pidpac());
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
}

