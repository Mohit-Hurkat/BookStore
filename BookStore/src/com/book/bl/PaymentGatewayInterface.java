package com.book.bl;

import com.book.bean.Customer;

public class PaymentGatewayInterface {
	public boolean processPayment(Customer customer, int totalPrice){
		//Interact with payment gateway, and return true if payment
		//was successful
		return true;
	}
}
