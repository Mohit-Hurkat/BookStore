package com.book.bl;

import java.util.HashMap;
import java.util.Map;

import com.book.bean.Customer;
import com.book.bean.Product;

public class PaymentLogic {
	
	public boolean processPayment(Customer customer, HashMap<Product, Integer> productMap){
		PaymentGatewayInterface pgi = new PaymentGatewayInterface();
		int totalPrice = calculateTotalPrice(productMap);
		if(pgi.processPayment(customer, totalPrice))
			return true;
		return false;
	}
	
	private int calculateTotalPrice(HashMap<Product, Integer> productMap){
		int totalPrice = 0;
		for(Product product : productMap.keySet()){
			totalPrice = totalPrice + product.getPrice() * productMap.get(product);
		}
		return totalPrice;
	}
	
	
}
