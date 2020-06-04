package com.anitha.model;

public class JsonObj {
	Sender sender;
	Email email;
	public Sender getSender() {
		return sender;
	}
	public void setSender(Sender sender) {
		this.sender = sender;
	}
	public Email getEmail() {
		return email;
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	public JsonObj() {
		
	}
	public JsonObj(Sender sender, Email email) {
		super();
		this.sender = sender;
		this.email = email;
	}
	

}
