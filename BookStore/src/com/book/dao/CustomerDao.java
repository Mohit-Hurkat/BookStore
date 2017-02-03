package com.book.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.book.bean.Customer;


public interface CustomerDao {
	
Customer search(String username) throws IOException, ClassNotFoundException, SQLException;
boolean insert(Customer c) throws IOException, ClassNotFoundException, SQLException;
List<Customer> displayAll()throws IOException,ClassNotFoundException, SQLException;
boolean delete(String username) throws IOException, ClassNotFoundException, SQLException;
}