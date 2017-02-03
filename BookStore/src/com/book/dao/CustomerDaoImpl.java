package com.book.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.bean.Customer;
import com.book.bean.Product;
import com.book.helper.JDBCConnection;
import com.book.helper.MyAppendObjectOutputStream;



public class CustomerDaoImpl implements CustomerDao{
	
	
	private static final String INSERT_QUERY = "INSERT INTO CUSTOMER(USERNAME,PASSWORD,NAME,PHONE,ADDRESS) VALUES(?, ?, ?, ?, ?)";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM CUSTOMER";	
	private static final String SELECT_QUERY = "SELECT * FROM CUSTOMER WHERE USERNAME = ?";
	private static final String DELETE_QUERY = "DELETE FROM CUSTOMER WHERE USERNAME = ?";
	@Override
	public Customer search(String username) throws IOException, ClassNotFoundException, SQLException {
		Customer c=null;
		//List<Customer> cList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
		preparedStatement.setString(1, username);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			String a = rs.getString("USERNAME");
			String b = rs.getString("PASSWORD");
			String c1 = rs.getString("NAME");
			String e = rs.getString("PHONE");
			String g = rs.getString("ADDRESS");
			c = new Customer(a, b, c1,e,g);
			
		}
		preparedStatement.close();
		connection.close();
		return c;
	}

	@Override
	public boolean insert(Customer c) throws IOException, ClassNotFoundException, SQLException {
	    
		int numAffectedRows;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
		if(c.getUsername().equals("admin"))
		{
			return false;
		}
		preparedStatement.setString(1, c.getUsername());
		preparedStatement.setString(2, c.getPassword());
		preparedStatement.setString(3, c.getName());
		preparedStatement.setString(4, c.getPhone());
		preparedStatement.setString(5, c.getAddress());
		numAffectedRows = preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
		System.out.println(numAffectedRows);
		return numAffectedRows > 0;
	}

	@Override
	public List<Customer> displayAll() throws IOException, ClassNotFoundException, SQLException {
		List<Customer> cList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			
			String a = rs.getString("USERNAME");
			String b = rs.getString("PASSWORD");
			String c1 = rs.getString("NAME");
			String e = rs.getString("PHONE");
			String g = rs.getString("ADDRESS");
			Customer c = new Customer(a, b, c1,e,g);
			cList.add(c);
		}
		preparedStatement.close();
		connection.close();
		return cList;
	}

	@Override
	public boolean delete(String username) throws IOException, ClassNotFoundException, SQLException {
		int updateCount;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
		preparedStatement.setString(1, username);
		preparedStatement.execute();
		updateCount = preparedStatement.getUpdateCount();
		preparedStatement.close();
		connection.close();
		return updateCount > 0;
	} 

//	@Override
//	public Customer search(String username) throws IOException, ClassNotFoundException {
//		File file=new File("customer");
//		FileInputStream fin=new FileInputStream(file);
//		ObjectInputStream foo=new ObjectInputStream(fin);
//		while(fin.available()>0){
//			Customer c=(Customer)foo.readObject();
//			if(c.getUsername().equals(username)){
//				foo.close();
//				fin.close();
//				return c;
//			}
//		}
//		foo.close();
//		fin.close();
//		return null;
//	}
//
//	@Override
//	public boolean insert(Customer customer) throws IOException, ClassNotFoundException {
//		File file=new File("customer");
//		String username = customer.getUsername();
//		//Verification
//		if(file.exists() && null != (search(username)))
//			return false;
//		
//		
//		ObjectOutputStream oos=null;
//		FileOutputStream  fos=null;
//		if(!file.exists()){
//			fos=new FileOutputStream(file);
//			oos=new ObjectOutputStream(fos);
//		}
//		else{
//			fos=new FileOutputStream(file,true);
//			oos=new MyAppendObjectOutputStream(fos);
//		}
//		oos.writeObject(customer);
//		oos.close();
//		fos.close();
//		return true;
//	}
//
//	public List<Customer> displayAll() throws IOException,ClassNotFoundException{
//		File file=new File("customer");
//		FileInputStream fis=new FileInputStream(file);
//		ObjectInputStream ois=new ObjectInputStream(fis);
//		List<Customer> empList=new ArrayList<Customer>();
//		
//		while(fis.available()>0){
//			Customer c=(Customer)ois.readObject();
//			empList.add(c);
//		}
//		ois.close();
//		fis.close();
//		return empList;
//	}
//
//	@Override
//	public boolean delete(String username) throws IOException, ClassNotFoundException {
//		boolean flag = false;
//		File old=new File("Customer");
//		FileInputStream fis=new FileInputStream(old);
//		ObjectInputStream ois=new ObjectInputStream(fis);
//		File temp=new File("TempFile");
//		FileOutputStream fos=new FileOutputStream(temp);
//		ObjectOutputStream oos=new ObjectOutputStream(fos);
//		Customer cmp=null;
//	
//		while(fis.available()>0){
//			cmp=(Customer)ois.readObject();
//			if(cmp.getUsername().equals(username)){
//				flag=true;
//				}
//			else{
//				oos.writeObject(cmp);
//			}
//		}
//		ois.close();
//		fis.close();		
//		oos.close();
//		fos.close();
//		old.delete();
//		temp.renameTo(old);
//		return flag;
//	}

	
     
}
