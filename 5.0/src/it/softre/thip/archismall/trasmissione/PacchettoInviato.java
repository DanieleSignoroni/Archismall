package it.softre.thip.archismall.trasmissione;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.CachedStatement;

public class PacchettoInviato extends PacchettoInviatoPO {

	public ErrorMessage checkDelete() {
		return null;
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

