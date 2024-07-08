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

public class SubmissionPackMetadataRsIterator extends ResultSetIterator {

	public SubmissionPackMetadataRsIterator(ResultSet rs) {
		super(rs);
	}

	@Override
	protected Object createObject() throws SQLException {
		SubmissionPackMetadata metadata = (SubmissionPackMetadata) Factory.createObject(SubmissionPackMetadata.class);
		metadata.setF9ICSOC(cursor.getString("F9ICSOC"));
        metadata.setF9IIDLA(cursor.getString("F9IIDLA"));
        metadata.setF9IFPDE(cursor.getString("F9IFPDE"));
        metadata.setF9IAFES(cursor.getString("F9IAFES"));
        metadata.setF9IGIVA(cursor.getString("F9IGIVA"));
        metadata.setF9ITPGI(cursor.getString("F9ITPGI"));
        metadata.setF9IDXML(cursor.getString("F9IDXML"));
        metadata.setF9ITCFO(cursor.getString("F9ITCFO"));
        metadata.setF9ICCFO(cursor.getString("F9ICCFO"));
        metadata.setF9IDRSO(cursor.getString("F9IDRSO"));
        metadata.setF9IIDFI(cursor.getString("F9IIDFI"));
        metadata.setF9IPIVA(cursor.getString("F9IPIVA"));
        metadata.setF9ICFIS(cursor.getString("F9ICFIS"));
        metadata.setF9ICADO(cursor.getString("F9ICADO"));
        metadata.setF9ICSOS(cursor.getString("F9ICSOS"));
        metadata.setF9INDOC(cursor.getString("F9INDOC"));
        metadata.setF9IUDOC(cursor.getDate("F9IUDOC"));
        metadata.setF9ISIVA(cursor.getString("F9ISIVA"));
        metadata.setF9ICREG(cursor.getString("F9ICREG"));
        metadata.setF9INPRI(cursor.getString("F9INPRI"));
        metadata.setF9IUPRI(cursor.getDate("F9IUPRI"));
        metadata.setF9IFOOA(cursor.getString("F9IFOOA"));
        metadata.setF9ITROT(cursor.getString("F9ITROT"));
        metadata.setF9INRRO(cursor.getString("F9INRRO"));
        metadata.setF9IURGO(cursor.getDate("F9IURGO"));
        metadata.setF9ICLDF(cursor.getString("F9ICLDF"));
        metadata.setF9INMF1(cursor.getString("F9INMF1"));
        metadata.setF9ICLDN(cursor.getString("F9ICLDN"));
        metadata.setF9INMF2(cursor.getString("F9INMF2"));
        metadata.setF9ICLDA(cursor.getString("F9ICLDA"));
        metadata.setF9INMF3(cursor.getString("F9INMF3"));
        metadata.setF9ICLD4(cursor.getString("F9ICLD4"));
        metadata.setF9INMF4(cursor.getString("F9INMF4"));
        metadata.setF9ICLD5(cursor.getString("F9ICLD5"));
        metadata.setF9INMF5(cursor.getString("F9INMF5"));
        metadata.setF9ICLD6(cursor.getString("F9ICLD6"));
        metadata.setF9INMF6(cursor.getString("F9INMF6"));
        metadata.setF9ICLD7(cursor.getString("F9ICLD7"));
        metadata.setF9INMF7(cursor.getString("F9INMF7"));
        metadata.setF9ICLD8(cursor.getString("F9ICLD8"));
        metadata.setF9INMF8(cursor.getString("F9INMF8"));
        metadata.setF9ICLD9(cursor.getString("F9ICLD9"));
        metadata.setF9INMF9(cursor.getString("F9INMF9"));
        metadata.setF9ICL10(cursor.getString("F9ICL10"));
        metadata.setF9INM10(cursor.getString("F9INM10"));
        metadata.setF9IUTEE(cursor.getString("F9IUTEE"));
        metadata.setF9IUHEL(cursor.getDate("F9IUHEL"));
        metadata.setF9IUHEW(cursor.getDate("F9IUHEW"));
        metadata.setF9IDDES(cursor.getString("F9IDDES"));
        metadata.setF9ITPRT(cursor.getString("F9ITPRT"));
        metadata.setF9IAPRT(cursor.getBigDecimal("F9IAPRT"));
        metadata.setF9INPRT(cursor.getBigDecimal("F9INPRT"));
        metadata.setF9IURIC(cursor.getDate("F9IURIC"));
		return metadata;
	}

}
