package com.anitha.demorest;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("restmethod")
public class RestMethod {
	
	@POST
	@Path("/postemail")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmail(Email[] emails) 
	{
		String result = "email saved : " + emails;
		SendEmailService service=new SendEmailService();
		service.sent(emails);
		
		return Response.status(201).entity(result).build();
	}


}
