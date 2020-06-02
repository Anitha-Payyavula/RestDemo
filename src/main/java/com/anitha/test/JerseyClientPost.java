package com.anitha.test;



import org.json.simple.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientPost {

	public static void main(String[] args) {

		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8087/demorest/webapi/restmethod/postemail");

			
			JSONObject obj = new JSONObject();

		      obj.put("to", "anitha.payyavula1997@gmail.com");
		      obj.put("subject", "Hii");
		      obj.put("body", "Hello...");
		     

					
			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, obj);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
