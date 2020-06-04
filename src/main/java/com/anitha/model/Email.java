package com.anitha.model;

public class Email {
	int id;
	String to;
	String subject;
	String body;
	public String getTo() {
		return to;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Email(int id,String to, String subject, String body) {
		super();
		this.id=id;
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	public Email() {
		
	}
	@Override
	public String toString() {
		return "Email [id="+ id +", to=" + to + ", subject=" + subject + ", body=" + body + "]";
	}
	

}
