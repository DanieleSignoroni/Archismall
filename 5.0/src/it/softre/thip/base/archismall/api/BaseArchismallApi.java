package it.softre.thip.base.archismall.api;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.base.util.WrapperJSON;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.rs.errors.PantheraApiException;

import it.softre.thip.archismall.base.configuration.ConfigurazioneArchismall;
import it.thera.thip.base.azienda.Azienda;

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

public class BaseArchismallApi {

	public static final String AUTH_ENDPOINT = "oauth/token";

	public static final String CONSERVAZIONE_PASSIVA_VERSAMENTO_ENDPOINT = "api/v1/conservazione/fattura-passiva/versamento";

	public static final String CONSERVAZIONE_ATTIVA_VERSAMENTO_ENDPOINT = "api/v1/conservazione/fattura-attivo/versamento";

	private ConfigurazioneArchismall configurazioneArchismall;

	public ConfigurazioneArchismall getConfigurazioneArchismall() {
		return configurazioneArchismall;
	}

	public void setConfigurazioneArchismall(ConfigurazioneArchismall configurazioneArchismall) {
		this.configurazioneArchismall = configurazioneArchismall;
	}

	public BaseArchismallApi() {
		initializeConfiguration();
	}

	/**
	 * @author Daniele Signoroni 15/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * Inizializza la configurazione, recuperandola dall'azienda corrente.<br>Z
	 * </p>
	 */
	protected void initializeConfiguration() {
		try {
			setConfigurazioneArchismall((ConfigurazioneArchismall) 
					ConfigurazioneArchismall.elementWithKey(ConfigurazioneArchismall.class, Azienda.getAziendaCorrente(), PersistentObject.NO_LOCK));
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}

	}

	/**
	 * @author Daniele Signoroni 15/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * Ritorna l'URL formattato con / finale.<br>
	 * es : https://ws-quality-archipro.archismall.com/
	 * </p>
	 * @return
	 */
	protected String getFormattedURL() {
		String url = null;
		if(getConfigurazioneArchismall() == null) 
			return null;
		url = getConfigurazioneArchismall().getUrl();
		if(url.charAt(url.length()-1)!='/')
			url = url + "/";
		return url;
	}

	/**
	 * @author Daniele Signoroni 15/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * Si occupa di ritornare la basic Authorization.<br>
	 * Per noi e' composta da client_secret + client_id
	 * </p>
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected Map getBasicAuthorization() {
		if(getConfigurazioneArchismall() == null)
			return null;
		Map<String,String> basicAuth = new HashMap<String, String>();
		String auth = getConfigurazioneArchismall().getClientId() + ":" + getConfigurazioneArchismall().getClientSecret();
		String encodedAuth = WrapperJSON.getInstance().encodeBase64(auth);
		basicAuth.put("Authorization", "Basic " + encodedAuth);
		return basicAuth;
	}

	/**
	 * @author Daniele Signoroni 15/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * Si occupa di ritornare la Bearer Authorization.<br>
	 * </p>
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected Map getBearerAuthorization(String token) {
		if(token == null)
			return null;
		Map<String,String> basicAuth = new HashMap<String, String>();
		String encodedAuth = WrapperJSON.getInstance().encodeBase64(token);
		basicAuth.put("Authorization", "Bearer " + encodedAuth);
		return basicAuth;
	}

	/**
	 * @author Daniele Signoroni 15/05/2024
	 * <p>
	 * Prima stesura.<br>
	 *
	 * </p>
	 * @return
	 * @throws Exception
	 */
	public Token getNewToken() throws Exception {
		WrapperJSON wj = (WrapperJSON) WrapperJSON.getInstance();
		String[] result = wj.sendPost(getFormattedURL() + AUTH_ENDPOINT, getBaseParametersForAuthentication("password"), getBasicAuthorization());
		String out = null;
		if (result[0].equals(Status.OK.toString())) {
			out = result[1];
			JSONObject response = new JSONObject(out);
			Gson gson = new GsonBuilder().create();
			Token token = gson.fromJson(response.toString(), Token.class);
			return token;
		} else {
			throw new PantheraApiException(Status.fromStatusCode(Integer.valueOf(result[0].toString())),"Impossibile autenticarsi verso Archismall");
		}
	}

	/**
	 * @author Daniele Signoroni 15/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * Ritorna i parametri di base per l'autenticazione.<br>
	 * </p>
	 * @param grantType puo' essere di due tipi ('password','refresh_token').
	 * @return
	 */
	private String getBaseParametersForAuthentication(String grantType) {
		String parameters = "";
		if(getConfigurazioneArchismall() != null) {
			parameters += "&grant_type="+grantType+"&username="+getConfigurazioneArchismall().getIdUtente()+"&password="+getConfigurazioneArchismall().getPassword();
		}
		return parameters;
	}

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
	public class Token {

		private String access_token;
		private String token_type;
		private String refresh_token;
		private long expires_in;
		private String scope;
		private String jti;

		public String getAccess_token() {
			return access_token;
		}
		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}
		public String getToken_type() {
			return token_type;
		}
		public void setToken_type(String token_type) {
			this.token_type = token_type;
		}
		public String getRefresh_token() {
			return refresh_token;
		}
		public void setRefresh_token(String refresh_token) {
			this.refresh_token = refresh_token;
		}
		public long getExpires_in() {
			return expires_in;
		}
		public void setExpires_in(long expires_in) {
			this.expires_in = expires_in;
		}
		public String getScope() {
			return scope;
		}
		public void setScope(String scope) {
			this.scope = scope;
		}
		public String getJti() {
			return jti;
		}
		public void setJti(String jti) {
			this.jti = jti;
		}


	}

}
