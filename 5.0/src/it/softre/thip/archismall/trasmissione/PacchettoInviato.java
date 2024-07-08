package it.softre.thip.archismall.trasmissione;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Database;

/**
 * Rappresenta un pacchetto inviato verso Archismall.<br>
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 08/07/2024
 * <br><br>
 * <b>71578	DSSOF3	08/07/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

public class PacchettoInviato extends PacchettoInviatoPO {
	
	private static final String STMT_SELECT_SUBMISSION_PACK_DETT = "SELECT * FROM FP.SUBMISSION_PACK_DETT P "
			+ "WHERE P.ID = ? ";

	public static CachedStatement cRetrieveSubmissionPackDettaglio = new CachedStatement(STMT_SELECT_SUBMISSION_PACK_DETT);

	public ErrorMessage checkDelete() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static PacchettoInviato pacchettoInviatoDaCodice(Integer idPacchetto) {
		Vector result;
		try {
			result = retrieveList(PacchettoInviato.class, " "+PacchettoInviatoTM.ID_PACCHETTO+" = '"+idPacchetto+"' ", "", false);

			if(result.size() > 0) {
				return (PacchettoInviato) result.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace(Trace.excStream);
		}
		return null;
	}
	
	public SubmissionPackDett dettaglio() {
		SubmissionPackDett dettaglio = null;
		ResultSet rs = null;
		SubmissionPackDettRsIterator iter = null;
		try{
			PreparedStatement ps = cRetrieveSubmissionPackDettaglio.getStatement();
			Database db = ConnectionManager.getCurrentDatabase();
			db.setString(ps, 1, getIdPacchetto().toString());
			rs = ps.executeQuery();
			iter = new SubmissionPackDettRsIterator(rs);
			if(iter.hasNext()) {
				dettaglio = (SubmissionPackDett) iter.next();
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
		return dettaglio;
	}

	public static char statoInvioPacchettone(String idLancio) {
		char stato = PacchettoTrasmissione.NON_PROCESSATO;
		String stmt = " SELECT MIN("+PacchettoInviatoTM.STATO_PACCHETTO+") "
				+ "FROM "+PacchettoInviatoTM.TABLE_NAME+" "
				+ "WHERE "+PacchettoInviatoTM.ID_LANCIO+" = '"+idLancio+"' ";
		ResultSet rs = null;
		CachedStatement cs = null;
		try {
			cs = new CachedStatement(stmt);
			rs = cs.executeQuery();
			if(rs.next()) {
				stato = rs.getString(1).charAt(0);
			}
		}catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}finally {
			try {
				if(cs != null) 
					cs.free();
				if(rs != null)
					rs.close();
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return stato;
	}

}

