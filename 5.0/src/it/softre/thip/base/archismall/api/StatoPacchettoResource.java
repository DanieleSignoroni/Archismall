package it.softre.thip.base.archismall.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONException;
import org.json.JSONObject;

import com.thera.thermfw.rs.BaseResource;

import it.softre.thip.archismall.base.generale.ArchismallErrors;
import it.softre.thip.archismall.trasmissione.PacchettoInviato;
import it.softre.thip.archismall.trasmissione.SubmissionPackDett;

@Path("/archismall/stato-conservazione")
public class StatoPacchettoResource extends BaseResource {

	@GET
	public Response getStatoConservazionePacchetto(@QueryParam("IdPacchetto") Integer idPacchetto) {
		PacchettoInviato pacchettoInviato = PacchettoInviato.pacchettoInviatoDaCodice(idPacchetto);
		if(pacchettoInviato != null) {
			SubmissionPackDett dettaglio = pacchettoInviato.dettaglio();
			JSONObject stato = dettaglio.recuperaStatoConservazionePacchettoArchismall(pacchettoInviato.getIdArchiPro());
			StatoConservazione statoConservazione = new StatoConservazione(
					pacchettoInviato.getIdPacchetto().toString(),
					pacchettoInviato.getIdLancio(),
					pacchettoInviato.getIdArchiPro(),
					String.valueOf(pacchettoInviato.getStatoPacchetto()),
					String.valueOf(pacchettoInviato.getStatoArchismall()),
					"",dettaglio.getNumero().trim(),dettaglio.getDataDoc().toString(),dettaglio.getRagioneSociale().trim(),dettaglio.getTipoDoc().trim());
			if(stato != null) {
				try {
					Integer status = null;
					if(stato.has("errorCode") && stato.get("errorCode") != JSONObject.NULL) {
						status = stato.getInt("errorCode"); 
						statoConservazione.setStatoArchismall(String.valueOf(status));
						ArchismallErrors errore = ArchismallErrors.getByErrorCode(status);
						if(errore != null)
							statoConservazione.setDescrizioneErrore(errore.getErrorDescription());
					}else if(stato.has("statusDescription") && stato.get("statusDescription") != JSONObject.NULL){
						String descr = (String) (stato.has("statusDescription") ? stato.get("statusDescription") : "");
						statoConservazione.setDescrizioneErrore(descr);
					}
				}catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return Response.ok(statoConservazione).build();
		}else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	public static class StatoConservazione {
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

}
