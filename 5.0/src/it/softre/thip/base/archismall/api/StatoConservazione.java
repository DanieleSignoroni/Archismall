package it.softre.thip.base.archismall.api;

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

public class StatoConservazione {
	protected String lancio;
	protected String pacchetto;
	protected String archiPro;
	protected String statoPanthera;
	protected String statoArchismall;
	protected String descrizioneErrore;
	protected String numeroFattura;
	protected String dataFattura;
	protected String ragioneSociale;
	protected String tipoDocumento;
	protected String descrizioneStatoArchismall;

	public StatoConservazione(String lancio, String pacchetto, String archiPro, String statoPanthera, String statoArchismall, String descrizioneErrore
			,String numeroFattura, String dataFattura,  String ragioneSociale, String tipoDocumento) {
		this.lancio = lancio;
		this.pacchetto = pacchetto;
		this.archiPro = archiPro;
		this.statoPanthera = statoPanthera;
		this.statoArchismall = statoArchismall;
		this.descrizioneErrore = descrizioneErrore;
		this.numeroFattura = numeroFattura;
		this.dataFattura = dataFattura;
		this.ragioneSociale = ragioneSociale;
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroFattura() {
		return numeroFattura;
	}

	public void setNumeroFattura(String numeroFattura) {
		this.numeroFattura = numeroFattura;
	}

	public String getDataFattura() {
		return dataFattura;
	}

	public void setDataFattura(String dataFattura) {
		this.dataFattura = dataFattura;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDescrizioneStatoArchismall() {
		return descrizioneStatoArchismall;
	}

	public void setDescrizioneStatoArchismall(String descrizioneStatoArchismall) {
		this.descrizioneStatoArchismall = descrizioneStatoArchismall;
	}

	public void setLancio(String lancio) {
		this.lancio = lancio;
	}

	public void setPacchetto(String pacchetto) {
		this.pacchetto = pacchetto;
	}

	public void setArchiPro(String archiPro) {
		this.archiPro = archiPro;
	}

	public void setStatoPanthera(String statoPanthera) {
		this.statoPanthera = statoPanthera;
	}

	public void setStatoArchismall(String statoArchismall) {
		this.statoArchismall = statoArchismall;
	}

	public void setDescrizioneErrore(String descrizioneErrore) {
		this.descrizioneErrore = descrizioneErrore;
	}

	public String getLancio() {
		return lancio;
	}

	public String getPacchetto() {
		return pacchetto;
	}

	public String getArchiPro() {
		return archiPro;
	}

	public String getStatoPanthera() {
		return statoPanthera;
	}

	public String getStatoArchismall() {
		return statoArchismall;
	}

	public String getDescrizioneErrore() {
		return descrizioneErrore;
	}
}