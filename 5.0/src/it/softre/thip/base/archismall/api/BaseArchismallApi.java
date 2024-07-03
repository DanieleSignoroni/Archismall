package it.softre.thip.base.archismall.api;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.base.util.WebServiceUtils;
import com.thera.thermfw.base.util.WebServiceUtilsTherm;
import com.thera.thermfw.base.util.WrapperJSON;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.rs.errors.PantheraApiException;

import it.softre.thip.archismall.base.configuration.ConfigurazioneArchismall;
import it.thera.thip.base.azienda.Azienda;

/**
 * La seguente classe e' una utils che si occupa di gestire le chiamate verso Archismall.<br></br>
 * All'interno di essa abbiamo alcune variabili chiave.<br>
 * <list>
 * <li>{@link #configurazioneArchismall} : qui risiedono alcune informazioni per la connessione verso Archismall,
 * come URL, Client_Secret, Client_Id, Password, Username.</li>
 * <li>{@link #token}, un wrapper del token di autenticazione fornito dopo il login, che verra' usato per le future chiamate e rinfrescato alla necessita'</li>
 * <li>Alcune variabili finali che contengono gli endpoint generici usati dal programma di interscambio</li>
 * </list>
 * <br>
 * La classe vorrebbe essere single instance, quindi dovrebbe essere usata tramite metodo {@link #getInstance()}.<br></br>
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

	private static BaseArchismallApi instance;

	public static BaseArchismallApi getInstance() {
		if(instance == null) {
			instance = (BaseArchismallApi) Factory.createObject(BaseArchismallApi.class);
		}
		return instance;
	}

	public static final String AUTH_ENDPOINT = "oauth/token";

	public static final String CONSERVAZIONE_PASSIVA_VERSAMENTO_ENDPOINT = "api/v1/conservazione/fattura-passiva/versamento";

	public static final String CONSERVAZIONE_ATTIVA_VERSAMENTO_ENDPOINT = "api/v1/conservazione/fattura-attivo/versamento";

	private Token token = null;

	private ConfigurazioneArchismall configurazioneArchismall;

	public ConfigurazioneArchismall getConfigurazioneArchismall() {
		return configurazioneArchismall;
	}

	public void setConfigurazioneArchismall(ConfigurazioneArchismall configurazioneArchismall) {
		this.configurazioneArchismall = configurazioneArchismall;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public BaseArchismallApi() {
		init();
	}

	/**
	 * @author Daniele Signoroni 15/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * Inizializza la configurazione, recuperandola dall'azienda corrente.<br>
	 * </p>
	 */
	protected void init() {
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
	public String getFormattedBaseURL() {
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
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	protected Map getBearerAuthorization() throws Exception {
		if(token == null) {
			valorizzaTokenAuthentication();
		}
		Map<String,String> basicAuth = new HashMap<String, String>();
		String encodedAuth = getToken().getAccess_token();
		basicAuth.put("Authorization", "Bearer " + encodedAuth);
		return basicAuth;
	}

	/**
	 * Si occupa di effettuare la chiamata verso l'endpoint di autenticazione {@value #AUTH_ENDPOINT} del fornitore.<br>
	 * In seguito valorizza, se la chiamata ha resituito 200, {@link #token}.<br>
	 * Questo oggetto e' un wrapper del token che viene fornito dal fornitore.<br>
	 * @author Daniele Signoroni 15/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * </p>
	 * @return
	 * @throws Exception generica, e una {@link PantheraApiException} nel caso in cui il login sia fallito
	 */
	public void valorizzaTokenAuthentication() throws Exception {
		WrapperJSON wj = (WrapperJSON) WrapperJSON.getInstance();
		String[] result = wj.sendPost(getFormattedBaseURL() + AUTH_ENDPOINT, getBaseParametersForAuthentication("password"), getBasicAuthorization());
		String out = null;
		if (result[0].equals(String.valueOf(Status.OK.getStatusCode()))) {
			out = result[1];
			JSONObject response = new JSONObject(out);
			Gson gson = new GsonBuilder().create();
			Token token = gson.fromJson(response.toString(), Token.class);
			setToken(token);;
		} else {
			throw new PantheraApiException(
					Status.fromStatusCode(Integer.valueOf(result[0].toString())),
					"Impossibile autenticarsi verso Archismall");
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
	 * Si occupa di effettuare una richiesta GET verso l'endpoint indicato.<br>
	 * L'utente non ha bisogno di fornire il token di autenticazione in quanto e' preso dalla variabile {@link #token},
	 * e automaticamente settato negli headers della request.<br></br>
	 * 
	 * Nel caso in cui la richiesta fallisse, con conseguente codice di errore {401} il programma considera
	 * il token come expired e prova a rinfrescare il token e ad effettuare nuovamente la chiamata.<br>
	 * @author Daniele Signoroni 16/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * </p>
	 * @param url
	 * @param parameters
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JSONObject sendGet(String url, String parameters, Map properties) throws Exception {
		Map bearerAuthorization = getBearerAuthorization();
		properties.putAll(bearerAuthorization);
		WebServiceUtilsTherm wj = WebServiceUtilsTherm.getInstance();
		String[] result = wj.sendGet(url, parameters, properties);
		//se 401 allora magari il token e' expired, quindi lo ricarico e rifaccio la request
		if(result[0].equals(String.valueOf(Status.UNAUTHORIZED.getStatusCode()))) {
			valorizzaTokenAuthentication();
			result = wj.sendGet(url, parameters, properties);
		}
		return formatResultToJSON(result);
	}

	/**
	 * Si occupa di effettuare una richiesta POST verso l'endpoint indicato.<br>
	 * L'utente non ha bisogno di fornire il token di autenticazione in quanto e' preso dalla variabile {@link #token},
	 * e automaticamente settato negli headers della request.<br></br>
	 * 
	 * Nel caso in cui la richiesta fallisse, con conseguente codice di errore {401} il programma considera
	 * il token come expired e prova a rinfrescare il token e ad effettuare nuovamente la chiamata.<br>
	 * @author Daniele Signoroni 16/05/2024
	 * <p>
	 * Prima stesura.<br>
	 *
	 * </p>
	 * @param url
	 * @param parameters
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JSONObject sendPost(String url, String parameters, Map properties) throws Exception {
		Map bearerAuthorization = getBearerAuthorization();
		properties.putAll(bearerAuthorization);
		WebServiceUtilsTherm wj = WebServiceUtilsTherm.getInstance();
		String[] result = wj.sendPost(url, parameters, properties);
		//se 401 allora magari il token e' expired, quindi lo ricarico e rifaccio la request
		if(result[0].equals(String.valueOf(Status.UNAUTHORIZED.getStatusCode()))) {
			valorizzaTokenAuthentication();
			result = wj.sendPost(url, parameters, properties);
		}
		return formatResultToJSON(result);
	}

	/**
	 * Si occupa di effettuare una richiesta GET verso l'endpoint indicato.<br>
	 * L'utente non ha bisogno di fornire il token di autenticazione in quanto e' preso dalla variabile {@link #token},
	 * e automaticamente settato negli headers della request.<br></br>
	 * 
	 * Nel caso in cui la richiesta fallisse, con conseguente codice di errore {401} il programma considera
	 * il token come expired e prova a rinfrescare il token e ad effettuare nuovamente la chiamata.<br>
	 * @author Daniele Signoroni 16/05/2024
	 * <p>
	 * Prima stesura.<br>
	 *
	 * </p>
	 * @param method
	 * @param url
	 * @param json
	 * @param asBody
	 * @param properties
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject sendJSON(String method, String url, String json, boolean asBody, Map properties) throws Exception {
		Map bearerAuthorization = getBearerAuthorization();
		properties.putAll(bearerAuthorization);
		WebServiceUtilsTherm wj = WebServiceUtilsTherm.getInstance();
		String[] result = wj.sendJSON(method, url, json, asBody,properties);
		//se 401 allora magari il token e' expired, quindi lo ricarico e rifaccio la request
		if(result[0].equals(String.valueOf(Status.UNAUTHORIZED.getStatusCode()))) {
			valorizzaTokenAuthentication();
			result = wj.sendJSON(method, url, json, asBody);
		}
		return formatResultToJSON(result);
	}
	
	/**
	 * Si occupa di ritornare un {@link JSONObject} a partire da un array di stringhe contenente la response.<br>
	 * Per vedere cosa ritorna la response vedere i metodi:
	 * <list>
	 * 	<li>{@link WebServiceUtils#sendGet(String, String, Map, boolean)} </li>
	 * 	<li>{@link WebServiceUtils#sendPost(String, String, Map, boolean)} </li>
	 * </list>
	 * @author Daniele Signoroni 17/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * </p>
	 * @param result
	 * @return
	 */
	private JSONObject formatResultToJSON(String result[]) {
		JSONObject response = new JSONObject();
		if(result != null) {
			if(result.length > 0 && result[0] != null) {
				response.put("status", result[0]);
			}
			if(result.length > 1 && result[1] != null) {
				response.put("response", result[1]);
			}
			if(result.length > 2 && result[2] != null) {
				response.put("errors", result[2]);
			}
			if(result.length > 3 && result[3] != null) {
				response.put("headers", result[3]);
			}
		}
		return response;
	}

	/**
	 * Wrapper del token di autenticazione fornito dalla chiamata all'endpoint URL+{@value BaseArchismallApi#AUTH_ENDPOINT}
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
