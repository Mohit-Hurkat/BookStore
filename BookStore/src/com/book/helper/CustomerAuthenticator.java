package com.book.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Map;

import com.book.bean.Customer;
import com.book.dao.CustomerDao;
import com.book.dao.CustomerDaoImpl;

public class CustomerAuthenticator {
	
	public Map.Entry<Customer, Boolean> authenticate(String username, String password) throws ClassNotFoundException, IOException, SQLException{
		CustomerDao cdao = new CustomerDaoImpl();
		Customer customer = cdao.search(username);
		if(customer == null)
			return new AbstractMap.SimpleEntry<>(null, false);
		if(password.equals(customer.getPassword()))
			return new AbstractMap.SimpleEntry<>(customer, true);
		return new AbstractMap.SimpleEntry<>(customer, false);
	}
}
