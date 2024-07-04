package it.softre.thip.archismall.trasmissione;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.base.Utils;
import com.thera.thermfw.persist.CachedStatement;

import it.softre.thip.archismall.base.generale.ArchismallUtils;
import it.softre.thip.archismall.base.generale.ArchismallUtils.TipoDocumentiAttivo;
import it.softre.thip.archismall.base.generale.ArchismallUtils.TipoDocumentoPassivo;
import it.softre.thip.base.archismall.api.BaseArchismallApi;
import it.thera.thip.cs.ThipException;

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

	/**
	 * Tramite il {@link #tipoDoc}, ritorna l'URL di chiamata verso Archismall.<br>
	 * Base URL + endpoint.<br>
	 * Se il tipo documento e' null o non rientra in quelli registrati qui {@link ArchismallUtils}, allora ritorna null.
	 * @author Daniele Signoroni 20/05/2024
	 * <p>
	 * Prima stesura.<br>
	 *
	 * </p>
	 * @return
	 */
	public String getEndpointDaTipoDocumento() {
		String baseURL = BaseArchismallApi.getInstance().getFormattedBaseURL();
		if(getTipoDoc() == null)
			return null;
		if(TipoDocumentiAttivo.contains(getTipoDoc())) {
			return baseURL + BaseArchismallApi.CONSERVAZIONE_ATTIVA_VERSAMENTO_ENDPOINT;
		}else if(TipoDocumentoPassivo.contains(getTipoDoc())) {
			return baseURL + BaseArchismallApi.CONSERVAZIONE_PASSIVA_VERSAMENTO_ENDPOINT;
		}else {
			return null;
		}
	}
	
	public String getEndpointStatoConservazione() {
		String baseURL = BaseArchismallApi.getInstance().getFormattedBaseURL();
		if(getTipoDoc() == null)
			return null;
		if(TipoDocumentiAttivo.contains(getTipoDoc())) {
			return baseURL + BaseArchismallApi.CONSERVAZIONE_ATTIVA_STATO_VERSAMENTO_ENDPOINT;
		}else if(TipoDocumentoPassivo.contains(getTipoDoc())) {
			return baseURL + BaseArchismallApi.CONSERVAZIONE_PASSIVA_STATO_VERSAMENTO_ENDPOINT;
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

	public JSONObject getJSONVersamento() throws ThipException {
		JSONObject json = new JSONObject();
		try {
			SubmissionPackMetadata metadati = recuperaMetadati("THIP.F9IVA00K");
			if(metadati == null) {
				throw new ThipException("Impossibile trovare i metadati per la fattura "+getNumero()+"\\"+getDataDoc());
			}
			json.put("file", getFileJSONObject());
			//ora ci aggiungiamo tutto il resto 
			if(TipoDocumentiAttivo.contains(getTipoDoc()))
				json.put("idArchiPro", "SOFTRE_FATATT_"+metadati.getF9IAFES().trim()+"-"+getId());
			else
				json.put("idArchiPro", "SOFTRE_FATPASS_"+metadati.getF9IAFES().trim()+"-"+getId());
			json.put("fornitorePiva", getPiva().trim());
			json.put("numeroFattura", getNumero().trim());
			json.put("dataFattura", getDataDoc());
			json.put("annoFattura", metadati.getF9IAFES().trim());
			json.put("dataProtocollo", metadati.getF9IUPRI());
			json.put("sezionaleIva", metadati.getF9ICREG().trim());
			json.put("tipoDocumento", getTipoDoc());
			json.put("numeroProtocollo", metadati.getF9INPRI().trim());
			json.put("fornitoreDenominazione", metadati.getF9IDRSO().trim());
		} catch (JSONException e) {
			json = null;
			e.printStackTrace(Trace.excStream);
		} catch (FileNotFoundException e) {
			json = null;
			e.printStackTrace(Trace.excStream);
		}
		return json;
	}
	
	public JSONObject recuperaStatoConservazionePacchettoArchismall(String idArchiPro) {
		String endpoint = getEndpointStatoConservazione();
		if(endpoint != null) {
			try {
				HashMap<String, String> parameters = new HashMap<String, String>();
				parameters.put("idArchiPro", idArchiPro);
				JSONObject response = BaseArchismallApi.getInstance().sendGet(endpoint, parameters, new HashMap<String,String>(),3);
				if(response.has("result")) {
					JSONObject result = response.getJSONObject("result");
					if(result.has("results")) {
						JSONArray results = result.getJSONArray("results");
						for(int i = 0; i < results.length();) {
							JSONObject obj = results.getJSONObject(i);
							return obj;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return null;
	}

	public SubmissionPackMetadata recuperaMetadati(String nomeTabella) {
		SubmissionPackMetadata metadata = null;
		String stmt = " SELECT * FROM "+nomeTabella+" WHERE F9INDOC = '"+getNumero()+"' AND F9IUDOC = '"+getDataDoc()+"' ";
		ResultSet rs = null;
		CachedStatement cs = null;
		SubmissionPackMetadataRsIterator rsIterator = null;
		try {
			cs = new CachedStatement(stmt);
			rs = cs.executeQuery();
			rsIterator = new SubmissionPackMetadataRsIterator(rs);
			while(rsIterator.hasNext()) {
				metadata = (SubmissionPackMetadata) rsIterator.next();
			}
		}catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}finally {
			try {
				if(cs != null) {
					cs.free();
				}
				if(rsIterator != null) {
					rsIterator.closeCursor();
				}
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		if(metadata == null) {
			return recuperaMetadati("FP.F9IVA00K");
		}
		return metadata;
	}
}
