package com.book.helper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Map;

import com.book.bean.Admin;
import com.book.bean.Customer;
import com.book.dao.AdminDao;
import com.book.dao.AdminDaoImpl;
import com.book.dao.CustomerDao;
import com.book.dao.CustomerDaoImpl;

public class AdminAuthenticator {
	public Map.Entry<Admin, Boolean> authenticate(String username, String password) throws ClassNotFoundException, IOException, SQLException{
		AdminDao adminDao = new AdminDaoImpl();
		Admin admin = adminDao.retrieveAdminRecord();
		if(admin == null)
			return null;
		if(password.equals(admin.getPassword()))
			return new AbstractMap.SimpleEntry<>(admin, true);
		return new AbstractMap.SimpleEntry<>(admin, false);
	}
}
