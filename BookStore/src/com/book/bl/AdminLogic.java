package com.book.bl;

import java.io.IOException;
import java.sql.SQLException;

import com.book.bean.Admin;
import com.book.dao.AdminDao;
import com.book.dao.AdminDaoImpl;


public class AdminLogic {
	private AdminDao adminDao=new AdminDaoImpl();
	
	public boolean update(String username, Admin newAdmin) throws IOException, ClassNotFoundException, SQLException{
		return adminDao.update(username, newAdmin);
	}
	
	public Admin retrieveAdminRecord() throws IOException, ClassNotFoundException, SQLException {
		return adminDao.retrieveAdminRecord();
	}
}
