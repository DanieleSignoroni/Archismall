package it.softre.thip.archismall.trasmissione;

import com.thera.thermfw.common.*;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 11/01/2025
 * <br><br>
 * <b>71823	DSSOF3	11/01/2025</b>
 * <p></p>
 */

public class SendedPackArchismall extends SendedPackArchismallPO {
	
	//72XXX <
	protected String iDescrErrore;
	
	public void setDescrErrore(String descrErrore) {
		this.iDescrErrore = descrErrore;
		setDirty();
	}

	public String getDescrErrore() {
		return iDescrErrore;
	}
	//72XXX >

	public ErrorMessage checkDelete() {
		return null;
	}

	public Integer recuperaIdPacchetto() {
		if(getIdArchiPro() != null) {
			String id = getIdArchiPro().substring(getIdArchiPro().lastIndexOf("-") + 1, getIdArchiPro().length());
			return Integer.valueOf(id);
		}
		return null;
	}

}

