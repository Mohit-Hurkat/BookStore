package com.book.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.book.bean.Admin;
import com.book.bean.Customer;
import com.book.bean.Product;
import com.book.helper.JDBCConnection;
import com.book.helper.MyAppendObjectOutputStream;


public class AdminDaoImpl implements AdminDao {

	private static final String INSERT_QUERY = "INSERT INTO ADMIN(USERNAME,PASSWORD) VALUES(?, ?)";	
	private static final String SELECT_QUERY = "SELECT * FROM ADMIN WHERE USERNAME = ?";
	private static final String UPDATE_QUERY = "UPDATE ADMIN SET PASSWORD = ? WHERE USERNAME = ?";
	
	@Override
	public boolean insert(Admin admin) throws IOException, ClassNotFoundException, SQLException {
	int numAffectedRows;
	Connection connection = JDBCConnection.getConnection();
	PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
	preparedStatement.setString(1, admin.getUsername());
	preparedStatement.setString(2, admin.getPassword());
	numAffectedRows = preparedStatement.executeUpdate();
	preparedStatement.close();
	connection.close();
	System.out.println(numAffectedRows);
	return numAffectedRows > 0;
	}
	
	
	public Admin retrieveAdminRecord() throws IOException, ClassNotFoundException, SQLException{
	Admin admin=null;
	//List<Customer> cList = new ArrayList<>();
	Connection connection = JDBCConnection.getConnection();
	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
	preparedStatement.setString(1, Admin.USER_NAME);
	ResultSet rs = preparedStatement.executeQuery();
	if(rs.next()){
		String b = rs.getString("PASSWORD");
		admin = new Admin(b);
		
	}
	preparedStatement.close();
	connection.close();
	return admin;
}
	
	@Override
	public boolean update(String username, Admin newAdmin) throws IOException, ClassNotFoundException, SQLException {
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
		preparedStatement.setString(1, newAdmin.getPassword());
		preparedStatement.setString(2, newAdmin.getUsername());
		int numRows = preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
		return numRows > 0;
	}
//	
//	@Override
//	public boolean update(Admin newAdmin) throws IOException, ClassNotFoundException {
//		File tempFile=new File("temp");
//		FileOutputStream fos=new FileOutputStream(tempFile);
//		ObjectOutputStream oos=new ObjectOutputStream(fos);
//		
//		File originalFile = new File("admin");
//		FileInputStream fis=new FileInputStream(originalFile);
//		ObjectInputStream ois=new ObjectInputStream(fis);
//		
//		oos.writeObject(newAdmin);
//		ois.close();
//		fis.close();
//		oos.close();
//		fos.close();
//		return true;
//	}
//	
//	public Admin retrieveAdminRecord() throws IOException, ClassNotFoundException{
//		File adminFile = new File("admin");
//		FileInputStream fis = new FileInputStream(adminFile);
//		ObjectInputStream ois = new ObjectInputStream(fis);
//		Admin admin = (Admin) ois.readObject();
//		fis.close();
//		ois.close();
//		return admin;
//	}

//	@Override
//	public boolean insert(Admin admin) throws IOException {
//		// TODO Auto-generated method stub
//		File adminFile=new File("admin");
//		ObjectOutputStream oos=null;
//		FileOutputStream  fos=null;
//		fos=new FileOutputStream(adminFile);
//		oos=new ObjectOutputStream(fos);
//		oos.writeObject(admin);
//		oos.close();
//		fos.close();
//		return true;
//	}


	
}
