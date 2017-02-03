package com.book.bean;

import java.io.Serializable;

public class Customer extends User implements Serializable{
	
	private String name;
	private String phone;
	private String address;
	
	
	

	public Customer(String username, String password, String name, String phone, String address) {
		super(username, password);
		this.name = name;
		this.phone = phone;
		this.address = address;
	}



	public String getName() {
		return name;
	}

	

	@Override
	public String toString() {
		return "Customer [name=" + name + ", phone=" + phone + ", username="
				+ getUsername() + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	
}
