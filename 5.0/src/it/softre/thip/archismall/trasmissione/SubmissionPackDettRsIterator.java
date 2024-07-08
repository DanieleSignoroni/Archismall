package it.softre.thip.archismall.trasmissione;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.thera.thermfw.persist.Factory;

import it.thera.thip.cs.ResultSetIterator;

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

public class SubmissionPackDettRsIterator extends ResultSetIterator {

	public SubmissionPackDettRsIterator(ResultSet rs) {
		super(rs);
	}

	@Override
	protected Object createObject() throws SQLException {
		SubmissionPackDett obj = (SubmissionPackDett) Factory.createObject(SubmissionPackDett.class);
	    obj.setId(cursor.getLong("ID"));
	    obj.setCodiceErrore(cursor.getString("CODICE_ERRORE"));
	    obj.setCodiceFiscale(cursor.getString("CODICE_FISCALE"));
	    obj.setDataDoc(cursor.getDate("DATA_DOC"));
	    obj.setDescrErrore(cursor.getString("DESCR_ERRORE"));
	    obj.setIdLancio(cursor.getString("ID_LANCIO"));
	    obj.setNomeFile(cursor.getString("NOME_FILE"));
	    obj.setNumero(cursor.getString("NUMERO"));
	    obj.setPiva(cursor.getString("PIVA"));
	    obj.setTipoDoc(cursor.getString("TIPODOC"));
	    obj.setIdSocieta(cursor.getLong("ID_SOCIETA"));
	    obj.setIdSubmissionPack(cursor.getLong("ID_SUBMISSION_PACK"));
	    obj.setRagioneSociale(cursor.getString("RAGIONE_SOCIALE"));
	    obj.setF9iiuric(cursor.getDate("F9IIURIC"));
	    obj.setF9inpri(cursor.getString("F9INPRI"));
	    obj.setF9isiva(cursor.getString("F9ISIVA"));
	    obj.setF9icreg(cursor.getString("F9ICREG"));
	    obj.setF9itprt(cursor.getString("F9ITPRT"));
	    obj.setF9iaprt(cursor.getInt("F9IAPRT"));
	    obj.setF9inprt(cursor.getInt("F9INPRT"));
	    obj.setF9iupri(cursor.getDate("F9IUPRI"));
	    return obj;
	}

}
