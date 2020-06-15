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
import com.anitha.exception.ServiceException;
import com.anitha.model.Email;
import com.anitha.model.JsonObj;
import com.anitha.model.Sender;
import com.anitha.util.Authentication;
import com.anitha.util.ConnectionHelper;

@Path("restmethod")
public class RestMethod {
	Email_DAO emailDAO=new Email_DAO();
	@GET
	@Path("/getemails")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Email> getEmailPage(@HeaderParam("authorization") String authString,
			@QueryParam("limit") int limit,
			@QueryParam("offset") int offset) throws ServiceException 
	{
		String[] auth=Authentication.isUserAuthenticated(authString);
		if(!emailDAO.findByUserId(auth[0],auth[1])) {
			throw new ServiceException("user is not authorized : ", Status.UNAUTHORIZED.toString());
			
		}
		List<Email> emailList = new ArrayList<Email>();
		emailList=emailDAO.getAllEmailsPaginated(limit,offset,auth[0]);
		
		return emailList;
	}
	
	
	
	@GET
	@Path("/getemail/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getEmail(@PathParam("id")int id,@HeaderParam("authorization") 
	String authString) throws ServiceException 
	{
		String[] list=Authentication.isUserAuthenticated(authString);
		if(!emailDAO.findByUserId(list[0],list[1])) {
			return "{\"error\":\"User not authenticated\"}";
			
		}
		Email email=emailDAO.findById(id,list[0]);
		if(email==null)
			throw new ServiceException("email id Not Found : " + id, Status.NOT_FOUND.toString());
		return email;
	}
	
	@GET
	@Path("/getallemails")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Email> getEmail(@HeaderParam("authorization") String authString) throws ServiceException 
	{
		String[] auth=Authentication.isUserAuthenticated(authString);
		if(!emailDAO.findByUserId(auth[0],auth[1])) {
			throw new ServiceException("user is not authorized : ", Status.UNAUTHORIZED.toString());
			
		}
		List<Email> emailList = new ArrayList<Email>();
		emailList=emailDAO.findAll(auth[0]);
		
		return emailList;
	}
	
	
	@POST
	@Path("/postsender")
	@Consumes(MediaType.APPLICATION_JSON)
	public void example(Sender sender) throws ServiceException 
	{
		if(sender.getUserId().isEmpty() || sender.getUserId()==null) {
			throw new ServiceException("id should not be empty or null", Status.BAD_REQUEST.toString());
		}
		emailDAO.saveUser(sender);
		
	}
	@POST
	@Path("/postemail")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createEmail(JsonObj json) throws ServiceException 
	{
		Sender sender=json.getSender();
		Email email=json.getEmail();
		if(!emailDAO.findByUserId(sender.getUserId(),sender.getPassword())) {
			throw new ServiceException("user is not authorized : ", Status.UNAUTHORIZED.toString());
			
		}
		if(email.getTo_address().isEmpty() || email.getTo_address()==null) {
			throw new ServiceException("to_address should not be empty or null", Status.BAD_REQUEST.toString());
		}
		if(!emailDAO.findByUserId(sender.getUserId(),sender.getPassword())) {
			throw new ServiceException("user is not authorized : ", Status.UNAUTHORIZED.toString());
			
		}
		int id=emailDAO.create(email,sender.getUserId());
		String result = "email saved with id: " +id;
		SendEmailService service=new SendEmailService();
		boolean flag=service.sent(json.getEmail(),json.getSender());
		emailDAO.update(id,flag);
		return Response.status(201).entity(result).build();
	}
	
	
}