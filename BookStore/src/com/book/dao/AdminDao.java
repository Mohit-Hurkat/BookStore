package com.book.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.book.bean.*;

public interface AdminDao {
	
	public boolean insert(Admin admin)throws IOException, ClassNotFoundException, SQLException;
	public boolean update(String username, Admin newAdmin) throws ClassNotFoundException, IOException, SQLException;
	public Admin retrieveAdminRecord() throws IOException, ClassNotFoundException, SQLException;
}
