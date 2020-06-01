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
	@GET
	@Path("/getemail")
	@Produces(MediaType.APPLICATION_JSON)
	public Email getEmail() 
	{
		Email email = new Email("anitha.payyavula1997@gmail.com","Hii","Helooooo....");
	    
	    return email;
	}
	@POST
	@Path("/postemail")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmail(Email email) 
	{
		String result = "email saved : " + email;
		SendEmailService service=new SendEmailService();
		service.sent(email.getTo(),email.getSubject(),email.getBody());
		
		return Response.status(201).entity(result).build();
	}


}
