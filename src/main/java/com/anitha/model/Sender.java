package com.anitha.model;

public class Sender {
	String userId;
	String password;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Sender [userId=" + userId + ", password=" + password + "]";
	}
	public Sender(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}
	public Sender() {
		
	}

}
