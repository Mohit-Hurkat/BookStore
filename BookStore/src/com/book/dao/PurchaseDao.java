package com.book.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.book.bean.Customer;
import com.book.bean.Product;
import com.book.bean.Purchase;

public interface PurchaseDao {
	boolean insert(Purchase purchase)throws IOException, ClassNotFoundException, SQLException;
	Purchase search(int customerId)throws IOException,ClassNotFoundException;
	HashMap<Integer, HashMap<Customer, HashMap<Product, Integer>>> displayAll()throws IOException,ClassNotFoundException, SQLException;
}
