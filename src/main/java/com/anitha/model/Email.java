package com.anitha.model;

public class Email {
	int email_id;
	String to_address;
	String subject;
	String body;
		
		
	public int getEmail_id() {
			return email_id;
		}

		public void setEmail_id(int email_id) {
			this.email_id = email_id;
		}

		public String getTo_address() {
			return to_address;
		}

		public void setTo_address(String to_address) {
			this.to_address = to_address;
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

	public Email() {
		
	}
	

	@Override
	public String toString() {
		return "Email [email_id=" + email_id + ", to_address=" + to_address + ", subject=" + subject + ", body=" + body
				+ "]";
	}

	public Email(int email_id, String to_address, String subject, String body) {
		super();
		this.email_id = email_id;
		this.to_address = to_address;
		this.subject = subject;
		this.body = body;
	}
	

}
