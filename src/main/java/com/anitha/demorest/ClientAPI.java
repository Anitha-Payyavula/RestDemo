package com.anitha.demorest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;


public class ClientAPI {
	
	
	public  String getRequest() {
		String response ="";
	    URL urlForGetRequest = null;
		try {
			urlForGetRequest = new URL("http://localhost:8087/demorest/webapi/restmethod/email");
		
	    String readLine = null;
	    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
	    conection.setRequestMethod("GET");
	    conection.setRequestProperty("Content-Type", "application/json");
	    
	    conection.setRequestProperty("userId", "anitha-payyavula"); 
	    int responseCode = conection.getResponseCode();
	    if (responseCode == HttpURLConnection.HTTP_OK) {
	        BufferedReader in = new BufferedReader(
	            new InputStreamReader(conection.getInputStream()));
	        
	        while ((readLine = in .readLine()) != null) {
	            response+=readLine;
	        } in .close();
	   
	       System.out.println("JSON String Result " + response.toString());
	      
	      
	        
	    } else {
	        System.out.println("GET NOT WORKED");
	    }
	    
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.toString();
	
	}
	 

}
