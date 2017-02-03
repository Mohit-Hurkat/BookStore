package com.book.bean;

import java.io.Serializable;

public abstract class User implements Serializable{
	private String username;
	private String password;
	
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	

	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getUsername() {
		return username;
	}
	
	
	
}