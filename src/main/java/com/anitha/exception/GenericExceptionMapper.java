package com.anitha.exception;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.anitha.model.ErrorMsg;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {
	@Override
	public Response toResponse(Throwable exception) {
		ErrorMsg msg=new ErrorMsg("500",exception.getMessage());
		return Response.status(Status.INTERNAL_SERVER_ERROR).
				entity(msg).
				build();
	}

}
