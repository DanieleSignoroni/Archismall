package it.thera.thip.api.client;

import java.util.Map;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import it.thera.thip.api.client.auth.ApiAuthentication;
import it.thera.thip.api.client.auth.NoAuthentication;
import it.thera.thip.api.client.jersey.ApiClientTimeoutException;
import it.thera.thip.api.client.jersey.ApiClientTimeoutException.TimeoutType;
import it.thera.thip.api.client.jersey.JerseyClientManager;

/*
 * Revisions:
 * Fix     Date          Owner      Description
 * 38753   22/05/2023    PJ         rilascio
 * 47935   05/12/2025    PJ         timeout e basic auth
 */

public class ApiClient {
	
	private ApiAuthentication authentication = new NoAuthentication();
	
	private final String apiPath;
	
	public String getApiPath() {
		return apiPath;
	}
	
	public ApiClient(String apiPath) {
		this.apiPath = apiPath;
	}
	
	public void setAuthentication(ApiAuthentication authentication) {
		this.authentication = authentication;
	}
	
	//47935 - inizio
	private Integer connectionTimeout = 15000;  //  default - sovrabbondante - di 15 secondi
	private Integer readTimeout;

	public ApiClient setConnectionTimeout(Integer connectionTimeoutInMillis) {
		this.connectionTimeout = connectionTimeoutInMillis;
		return this;
	}
	
	public ApiClient setReadTimeout(Integer readTimeoutInMillis) {
		this.readTimeout = readTimeoutInMillis;
		return this;
	}

	public Integer getConnectionTimeout() {
		return this.connectionTimeout;
	}
	
	public Integer getReadTimeout() {
		return this.readTimeout;
	}
	//47935 - fine
	
	public ApiResponse send(ApiRequest request) throws KeyManagementException, NoSuchAlgorithmException {
		String absoluteURL = this.apiPath + request.getURL(); 

		JerseyClientManager cm = new JerseyClientManager();
		
		Client c = cm.httpClientFor(absoluteURL);

		if (request.getParameters().size() > 0) {
			String queryString = null;
			for (Map.Entry<String, String> param : request.getParameters().entrySet()) {
				queryString = (queryString == null ? "" : queryString + "&") + param.getKey() + "=" + param.getValue();
			}
			absoluteURL = absoluteURL + (absoluteURL.contains("?") ? "&" : "?") + queryString;
		}
		
		WebTarget wt = c.target(absoluteURL);
        Invocation.Builder ib = wt.request();
		ib.header("accept", request.getAccept());
		ib.header("content-type", request.getContentType());

		if (authentication != null) {
			String authorizationHeader = authentication.authorizationHeader(this.apiPath);
			if (authorizationHeader != null) {
				ib.header("Authorization", authorizationHeader);
			}
		}
		
		for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
			ib.header(header.getKey(), header.getValue());
		}
		
		//47935 - inizio
		Response response = null;
		String timedOutCause = null;
		
		boolean connectionTimedOut = false;
		boolean readTimedOut = false;
		
		try {
			String method = request.hasCustomMethod() ? request.getCustomMethod() : request.getMethod().name();
			String check = method.toUpperCase();
			if (check.equals("GET") || check.equals("DELETE") || check.equals("OPTIONS") || check.equals("HEAD")) {
				response = ib.method(method);
			}
			else {
				response = ib.method(method, request.getBodyEntity());
			}
		}
		catch (ProcessingException e) {
			if (e.getCause() instanceof ApiClientTimeoutException) {
				ApiClientTimeoutException ae = (ApiClientTimeoutException) e.getCause();
				if (ae.timeoutType() == TimeoutType.CONNECTION_TIMEOUT) {
					connectionTimedOut = true;
				}
				else {
					readTimedOut = true;
				}
				timedOutCause = ae.timeoutType().name(); 
			}
			else {
				throw e;
			}
		}
		
		ApiResponse pr = new ApiResponse(response);
		if (timedOutCause != null) {
			pr.setReadTimedOut(readTimedOut);
			pr.setConnectionTimedOut(connectionTimedOut);
			pr.setTimedOut(timedOutCause);
		}
		//47935 - fine
		
		return pr;
	}

}
