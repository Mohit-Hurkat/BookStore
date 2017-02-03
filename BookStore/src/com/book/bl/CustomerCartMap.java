package com.book.bl;

import java.util.HashMap;

import com.book.bean.Customer;
import com.book.bean.Product;

public class CustomerCartMap {
	public static HashMap<Customer, ShoppingCart> customerCartMap = 
			new HashMap<>();
	
	public static void input(Customer customer, ShoppingCart shoppingCart){
		customerCartMap.put(customer, shoppingCart);
	}
	
	public static ShoppingCart getCartFromCustomer(Customer customer){
		return customerCartMap.get(customer);
	}
	
	
}
