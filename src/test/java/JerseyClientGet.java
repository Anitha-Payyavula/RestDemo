



import com.anitha.util.Authentication;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import sun.misc.BASE64Encoder;

public class JerseyClientGet {

	public static void main(String[] args) {
		try {
			String url="http://localhost:8087/demorest/webapi/restmethod/getemail/25";
			String name = "anitha.payyavula97@gmail.com";
	        String password = "fhA9vNeE2VHylB98NAVpGw==";
	        String authString = name + ":" + password;
	        authString=Authentication.encrypt(authString);
	       // String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
	        System.out.println("Base64 encoded auth string: " + authString);
	        Client restClient = Client.create();
	        WebResource webResource = restClient.resource(url);

			/*Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8087/demorest/webapi/restmethod/getemail/15");
*/
	        ClientResponse response = webResource.accept("application/json")
                    .header("Authorization", "Basic " + authString)
                    .get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}