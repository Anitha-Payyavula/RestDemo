package com.anitha.demorest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.anitha.exception.DataNotFoundException;
import com.anitha.exception.ServiceException;
import com.anitha.model.Email;
import com.anitha.model.JsonObj;
import com.anitha.model.Sender;

@Path("restmethod")
public class RestMethod {
	@GET
	@Path("/getemail/{id}")
	//@Produces(MediaType.APPLICATION_JSON)
	public Response getEmail(@PathParam("id")String id) throws ServiceException 
	{
		throw new ServiceException("email id Not Found : " + id, Status.NOT_FOUND.toString());
		//return Response.status(200).entity(msg).build();
	}
	
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createEmail1(Sender sender) throws ServiceException 
	{
		if(sender.getUserId().isEmpty() || sender.getUserId()==null) {
			throw new ServiceException("id should not be empty or null", Status.BAD_REQUEST.toString());
		}
		
	}
	@POST
	@Path("/postemail")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmail(JsonObj json) 
	{

		String result = "email saved : " + json.getEmail();
		SendEmailService service=new SendEmailService();
		service.sent(json.getEmail(),json.getSender().getUserId(),json.getSender().getPassword());
		
		return Response.status(201).entity(result).build();
	}
	
	
}
