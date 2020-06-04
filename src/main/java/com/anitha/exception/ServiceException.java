package com.anitha.exception;

public class ServiceException extends Exception{

	private String statusCode;
	

	public String getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public ServiceException(String message,String statusCode) {
		super(message);
		this.statusCode=statusCode;
	}
	
	

}