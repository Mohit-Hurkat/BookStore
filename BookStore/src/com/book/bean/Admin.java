package com.book.bean;

import java.io.Serializable;

public class Admin extends User implements Serializable{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 5250990250402521453L;
	public final static String USER_NAME = "admin";

	public Admin(String password) {
		super(USER_NAME, password);
		// TODO Auto-generated constructor stub
	}


}
