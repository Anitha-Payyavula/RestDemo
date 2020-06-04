package com.anitha.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.anitha.model.ErrorMsg;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

	
	public Response toResponse(ServiceException ex) {
		ErrorMsg error = new ErrorMsg();
		error.setErrorMessage(ex.getMessage());
		error.setStatus(ex.getStatusCode());
		StringWriter writter = new StringWriter();
		ex.printStackTrace(new PrintWriter(writter));
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).type(MediaType.APPLICATION_JSON)
				.build();
	}

}