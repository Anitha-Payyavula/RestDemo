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

import com.anitha.DAO.Email_DAO;
import com.anitha.exception.DataNotFoundException;
import com.anitha.exception.ServiceException;
import com.anitha.model.Email;
import com.anitha.model.JsonObj;
import com.anitha.model.Sender;
import com.anitha.util.ConnectionHelper;

@Path("restmethod")
public class RestMethod {
	Email_DAO e=new Email_DAO();
	@GET
	@Path("/getemail/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Email getEmail(@PathParam("id")int id) throws ServiceException 
	{
		
		Email email=e.findById(id);
		if(email==null)
			throw new ServiceException("email id Not Found : " + id, Status.NOT_FOUND.toString());
		return email;
		//return Response.status(200).entity(msg).build();
	}
	
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public void example(Sender sender) throws ServiceException 
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
		int id=e.create(json.getEmail());

		String result = "email saved with id: " +id;
		SendEmailService service=new SendEmailService();
		service.sent(json.getEmail(),json.getSender().getUserId(),json.getSender().getPassword());
		
		return Response.status(201).entity(result).build();
	}
	
	
}