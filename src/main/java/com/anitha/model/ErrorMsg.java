package com.anitha.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMsg {
	
	private String status;
	private String errorMessage;
	
	public ErrorMsg(){}
	
	public ErrorMsg(String statusFromOutside, String errorMessageFromOutside)
	{
		this.status = statusFromOutside;
		this.errorMessage = errorMessageFromOutside;
	}
	
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	

}