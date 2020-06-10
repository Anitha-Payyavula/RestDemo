package com.anitha.model;

import java.io.Serializable;

public class Email implements Serializable{
	private static final long serialVersionUID = 1;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + email_id;
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((to_address == null) ? 0 : to_address.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (email_id != other.email_id)
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (to_address == null) {
			if (other.to_address != null)
				return false;
		} else if (!to_address.equals(other.to_address))
			return false;
		return true;
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
