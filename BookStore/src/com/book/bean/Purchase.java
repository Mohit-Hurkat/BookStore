package com.book.bean;

import java.io.Serializable;
import java.util.HashMap;

public class Purchase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7264039234911519263L;
	static private int id=0;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getId() {
		return id;
	}
	
	public static void setId(int i) {
		id=i;
	}

	private Customer customer;
	private HashMap<Product, Integer> productMap;

	public Purchase(Customer customer, HashMap<Product, Integer> productMap) {
		this.customer = customer;
		this.productMap = productMap;
	}

	public Customer getCustomer() {
		return customer;
	}

	public HashMap<Product, Integer> getProductMap() {
		return productMap;
	}

	@Override
	public String toString() {
		return "Purchase [customer=" + customer + ", productMap=" + productMap + "]";
	}
	
	
}
