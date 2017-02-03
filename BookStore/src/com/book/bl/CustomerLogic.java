package com.book.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.book.bean.Customer;
import com.book.dao.CustomerDao;
import com.book.dao.CustomerDaoImpl;

public class CustomerLogic {
	private CustomerDao cd=new CustomerDaoImpl();
	
	public boolean add(Customer customer) throws IOException, ClassNotFoundException, SQLException{
		return cd.insert(customer);
	}

	public Customer search(String username) throws ClassNotFoundException, IOException, SQLException{
		return cd.search(username);
	}
	
	public List<Customer> allRecords() throws ClassNotFoundException, IOException, SQLException{
		return cd.displayAll();
	}
	
	public boolean delete(String username) throws IOException, ClassNotFoundException, SQLException {
		return cd.delete(username);
	}
	
	public void assignCartToCustomer(Customer customer){
		ShoppingCart shoppingCart = new ShoppingCart();
		CustomerCartMap.input(customer, shoppingCart);
	}
	
}
