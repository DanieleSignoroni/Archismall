package it.thera.thip.api.client.jersey;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

/*
 * Revisions:
 * Fix     Date          Owner      Description
 * 38753   22/05/2023    PJ         rilascio
 */

public class JerseyClientManager {

	public Client httpClientFor(String url) throws KeyManagementException, NoSuchAlgorithmException {
		if (url != null && url.toLowerCase().startsWith("https")) {
			return httpsClient();
		}
		return httpClient();
	}
	
	public Client httpClient() {
		ClientConfig config = new ClientConfig();
		config.register(JacksonFeature.class);
		Client client = ClientBuilder.newClient(config);
		return client;
	}

	public Client httpsClient() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("TLS");
        TrustManager[] trustAll = { new UnsecureTrustManager() };
        sc.init(null, trustAll, new java.security.SecureRandom());
        HostnameVerifier allHosts = new UnsecureHostnameVerifier();
        
        Client client = ClientBuilder.newBuilder().sslContext(sc).hostnameVerifier(allHosts).build();
		return client;

	}

	
	/* - in futuro

	//per gestire il force https con jdk < 1.8 serve bouncycastle ma le librerie non sono le stesse che abbiamo 
	//ora in panthera e ci son incompatibilità con CERVED - rimandiamo
	
	//import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;

	
	public Client httpsClient() throws NoSuchAlgorithmException, KeyManagementException {
		return httpsClient(false);
	}

	private boolean isLessThanJava18() {
        double version = Double.parseDouble(System.getProperty("java.specification.version"));
        return (version < 1.8);
	}
	
	public Client httpsClient(boolean forceTLSv1_2) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = null;
        
        if (forceTLSv1_2) {
        	if (isLessThanJava18()) {
        		sc = SSLContext.getInstance("TLSv1.2", new BouncyCastleJsseProvider());
        	}
        	else {
        		sc = SSLContext.getInstance("TLSv1.2");
        	}
        }
    	else {
        	sc = SSLContext.getInstance("TLS");
    	}

        TrustManager[] trustAll = { new UnsecureTrustManager() };
        sc.init(null, trustAll, new java.security.SecureRandom());
        HostnameVerifier allHosts = new UnsecureHostnameVerifier();
        
        Client client = ClientBuilder.newBuilder().sslContext(sc).hostnameVerifier(allHosts).build();
		return client;
	}
	*/
	
}
