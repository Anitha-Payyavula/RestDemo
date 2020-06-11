package com.anitha.demorest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
import com.anitha.util.Authentication;
import com.anitha.util.ConnectionHelper;

@Path("restmethod")
public class RestMethod {
	Email_DAO e=new Email_DAO();
	@GET
	@Path("/getemails")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Email> getEmailPage(@HeaderParam("authorization") String authString,
			@QueryParam("limit") int limit,
			@QueryParam("offset") int offset) throws ServiceException 
	{
		String[] auth=Authentication.isUserAuthenticated(authString);
		if(!e.findByUserId(auth[0],auth[1])) {
			throw new ServiceException("user is not authorized : ", Status.UNAUTHORIZED.toString());
			
		}
		List<Email> list = new ArrayList<Email>();
		list=e.getAllEmailsPaginated(limit,offset);
		
		return list;
	}
	
	
	
	@GET
	@Path("/getemail/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getEmail(@PathParam("id")int id,@HeaderParam("authorization") 
	String authString) throws ServiceException 
	{
		String[] list=Authentication.isUserAuthenticated(authString);
		if(!e.findByUserId(list[0],list[1])) {
			return "{\"error\":\"User not authenticated\"}";
			
		}
		Email email=e.findById(id,list[0]);
		if(email==null)
			throw new ServiceException("email id Not Found : " + id, Status.NOT_FOUND.toString());
		return email;
		//return Response.status(200).entity(msg).build();
	}
	
	@GET
	@Path("/getallemails")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Email> getEmail(@HeaderParam("authorization") String authString) throws ServiceException 
	{
		String[] auth=Authentication.isUserAuthenticated(authString);
		if(!e.findByUserId(auth[0],auth[1])) {
			throw new ServiceException("user is not authorized : ", Status.UNAUTHORIZED.toString());
			
		}
		List<Email> list = new ArrayList<Email>();
		list=e.findAll(auth[0]);
		
		return list;
	}
	
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public void example(Sender sender) throws ServiceException 
	{
		if(sender.getUserId().isEmpty() || sender.getUserId()==null) {
			throw new ServiceException("id should not be empty or null", Status.BAD_REQUEST.toString());
		}
		e.saveUser(sender);
		
	}
	@POST
	@Path("/postemail")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmail(JsonObj json) 
	{
		int id=e.create(json.getEmail());
		String result = "email saved with id: " +id;
		SendEmailService service=new SendEmailService();
		boolean flag=service.sent(json.getEmail(),json.getSender().getUserId(),json.getSender().getPassword());
		e.update(id,flag);
		return Response.status(201).entity(result).build();
	}
	
	
}