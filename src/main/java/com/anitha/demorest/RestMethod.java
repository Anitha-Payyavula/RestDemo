package com.anitha.demorest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("restmethod")
public class RestMethod {
	@GET
	@Path("/email")
	@Produces(MediaType.APPLICATION_JSON)
	public Email getEmail() 
	{
		Email email = new Email("anitha.payyavula1997@gmail.com","Hii","Helooooo....");
	    
	    return email;
	}


}
