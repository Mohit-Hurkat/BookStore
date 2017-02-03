package com.book.client;

import java.io.IOException;
import java.sql.SQLException;

import com.book.bean.Admin;
import com.book.dao.AdminDao;
import com.book.dao.AdminDaoImpl;
import com.book.dao.CustomerDao;
import com.book.dao.CustomerDaoImpl;
import com.book.dao.ProductDao;
import com.book.dao.ProductDaoImpl;

public class TempMain {
	public static void main(String args[]) {
		ProductDao productDao = new ProductDaoImpl();
		try {
			System.out.println(productDao.search(1).getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
