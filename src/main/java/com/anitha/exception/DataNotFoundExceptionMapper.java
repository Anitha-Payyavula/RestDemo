package com.anitha.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.anitha.model.ErrorMsg;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{
	
	public Response toResponse(DataNotFoundException ex)
	{
		return Response.status(Status.NOT_FOUND)
				.entity(new ErrorMsg("404", ex.getMessage()))
				.build();
	}
}