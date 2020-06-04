



import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

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
			
			JSONObject json = new JSONObject();
			
			JSONObject email = new JSONObject();

			email.put("to", "anitha.payyavula1997@gmail.com");
			email.put("subject", "Hii");
			email.put("body", "Hello...");
			
			JSONObject sender = new JSONObject();
			sender.put("userId", "anitha.payyavula1997@gmail.com");
			sender.put("password", "Hii");
		      
			json.put("email", email);
			json.put("sender", sender);
			
		     

			for(int i=0;i<5;i++) {
			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, json);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
