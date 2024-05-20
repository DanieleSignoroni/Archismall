package it.softre.thip.archismall.trasmissione;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.base.Utils;

import it.softre.thip.archismall.base.generale.ArchismallUtils.TipoDocumentiAttivo;
import it.softre.thip.archismall.base.generale.ArchismallUtils.TipoDocumentoPassivo;
import it.softre.thip.base.archismall.api.BaseArchismallApi;

public class SubmissionPackDett {

	private long id;
	private String codiceErrore;
	private String codiceFiscale;
	private Date dataDoc;
	private String descrErrore;
	private String idLancio;
	private String nomeFile;
	private String numero;
	private String piva;
	private String tipoDoc;
	private long idSocieta;
	private long idSubmissionPack;
	private String ragioneSociale;
	private Date f9iiuric;
	private String f9inpri;
	private String f9isiva;
	private String f9icreg;
	private String f9itprt;
	private Integer f9iaprt;
	private Integer f9inprt;
	private Date f9iupri;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodiceErrore() {
		return codiceErrore;
	}

	public void setCodiceErrore(String codiceErrore) {
		this.codiceErrore = codiceErrore;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDataDoc() {
		return dataDoc;
	}

	public void setDataDoc(Date dataDoc) {
		this.dataDoc = dataDoc;
	}

	public String getDescrErrore() {
		return descrErrore;
	}

	public void setDescrErrore(String descrErrore) {
		this.descrErrore = descrErrore;
	}

	public String getIdLancio() {
		return idLancio;
	}

	public void setIdLancio(String idLancio) {
		this.idLancio = idLancio;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPiva() {
		return piva;
	}

	public void setPiva(String piva) {
		this.piva = piva;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public long getIdSocieta() {
		return idSocieta;
	}

	public void setIdSocieta(long idSocieta) {
		this.idSocieta = idSocieta;
	}

	public long getIdSubmissionPack() {
		return idSubmissionPack;
	}

	public void setIdSubmissionPack(long idSubmissionPack) {
		this.idSubmissionPack = idSubmissionPack;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public Date getF9iiuric() {
		return f9iiuric;
	}

	public void setF9iiuric(Date f9iiuric) {
		this.f9iiuric = f9iiuric;
	}

	public String getF9inpri() {
		return f9inpri;
	}

	public void setF9inpri(String f9inpri) {
		this.f9inpri = f9inpri;
	}

	public String getF9isiva() {
		return f9isiva;
	}

	public void setF9isiva(String f9isiva) {
		this.f9isiva = f9isiva;
	}

	public String getF9icreg() {
		return f9icreg;
	}

	public void setF9icreg(String f9icreg) {
		this.f9icreg = f9icreg;
	}

	public String getF9itprt() {
		return f9itprt;
	}

	public void setF9itprt(String f9itprt) {
		this.f9itprt = f9itprt;
	}

	public Integer getF9iaprt() {
		return f9iaprt;
	}

	public void setF9iaprt(Integer f9iaprt) {
		this.f9iaprt = f9iaprt;
	}

	public Integer getF9inprt() {
		return f9inprt;
	}

	public void setF9inprt(Integer f9inprt) {
		this.f9inprt = f9inprt;
	}

	public Date getF9iupri() {
		return f9iupri;
	}

	public void setF9iupri(Date f9iupri) {
		this.f9iupri = f9iupri;
	}

	public String getEndpointDaTipoDocumento() {
		if(getTipoDoc() == null)
			return null;
		if(TipoDocumentiAttivo.contains(getTipoDoc())) {
			return BaseArchismallApi.CONSERVAZIONE_ATTIVA_VERSAMENTO_ENDPOINT;
		}else if(TipoDocumentoPassivo.contains(getTipoDoc())) {
			return BaseArchismallApi.CONSERVAZIONE_PASSIVA_VERSAMENTO_ENDPOINT;
		}else {
			return null;
		}
	}

	public JSONObject getFileJSONObject() throws FileNotFoundException {
		if(getNomeFile() == null)
			return null;
		JSONObject json = new JSONObject();
		File file = new File(getNomeFile()); 
		if(file.exists()) {
			try {
				json.put("content", getEncodedFile(getFileBytes(file)));
				json.put("name", file.getName());
			} catch (JSONException e) {
				e.printStackTrace(Trace.excStream);
			} catch (Exception e) {
				e.printStackTrace(Trace.excStream);
			}
		}else {
			throw new FileNotFoundException("File non trovato con percorso : "+getNomeFile());
		}
		return json;
	}

	public byte[] getFileBytes(File file) throws Exception{
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Utils.copyStream(fis, baos);
		return baos.toByteArray();
	}

	public String getEncodedFile(byte [] bytes){
		return new String (Base64.getEncoder().encode(bytes));
	}

	public JSONObject getJSONVersamento() {
		JSONObject json = new JSONObject();
		try {
			json.put("file", getFileJSONObject());
			//ora ci aggiungiamo tutto il resto 
			json.put("idArchiPro", "");
			json.put("fornitorePiva", "");
			json.put("numeroFattura", "");
			json.put("dataFattura", "");
			json.put("annoFattura", "");
			json.put("dataProtocollo", "");
			json.put("sezionaleIva", "");
			json.put("tipoDocumento", "");
			json.put("numeroProtocollo", "");
			json.put("fornitoreDenominazione", "");
		} catch (JSONException e) {
			json = null;
			e.printStackTrace(Trace.excStream);
		} catch (FileNotFoundException e) {
			json = null;
			e.printStackTrace(Trace.excStream);
		}
		return json;
	}
}
