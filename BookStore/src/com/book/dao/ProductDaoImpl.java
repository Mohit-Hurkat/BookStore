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

import com.book.helper.JDBCConnection;
import com.book.helper.MyAppendObjectOutputStream;
import com.book.bean.Product;

public class ProductDaoImpl implements ProductDao {
	
	private static final String INSERT_QUERY = "INSERT INTO PRODUCT(ID, NAME, PRICE, STOCK) VALUES(?, ?, ?, ?)"; 
	private static final String UPDATE_QUERY = "UPDATE PRODUCT SET NAME = ?, PRICE = ?," + 
			"STOCK = ? WHERE ID = ?";
	private static final String DELETE_QUERY = "DELETE FROM PRODUCT WHERE ID = ?";
	private static final String GET_MAX_ID_QUERY = "SELECT COALESCE(MAX(ID), 0) AS COUNT FROM PRODUCT";
	private static final String SELECT_QUERY = "SELECT * FROM PRODUCT WHERE ID = ?";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM PRODUCT";	
	@Override
	public boolean insert(Product product) throws IOException, ClassNotFoundException, SQLException {
		int numAffectedRows;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
		preparedStatement.setInt(1, product.getId());
		preparedStatement.setString(2, product.getName());
		preparedStatement.setInt(3, product.getPrice());
		preparedStatement.setInt(4, product.getStock());
		numAffectedRows = preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
		System.out.println(numAffectedRows);
		return numAffectedRows > 0;
	}
	

	@Override
	public Product search(int productId)throws IOException,ClassNotFoundException, SQLException {
		Product product = null;
		List<Product> productList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
		preparedStatement.setInt(1, productId);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			String productName = rs.getString("NAME");
			int productPrice = rs.getInt("PRICE");
			int productStock = rs.getInt("STOCK");
			product = new Product(productId, productName, productPrice, productStock);
			productList.add(product);
		}
		rs.close();
		preparedStatement.close();
		connection.close();
		return product;
	}

	@Override
	public List<Product> displayAll() throws IOException,ClassNotFoundException, SQLException{
		List<Product> productList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			int productId = rs.getInt("ID");
			String productName = rs.getString("NAME");
			int productPrice = rs.getInt("PRICE");
			int productStock = rs.getInt("STOCK");
			Product product = new Product(productId, productName, productPrice, productStock);
			productList.add(product);
		}
		rs.close();
		preparedStatement.close();
		connection.close();
		return productList;
	}

	@Override
	public boolean delete(int productId) throws IOException, ClassNotFoundException, SQLException {
		int updateCount;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
		preparedStatement.setInt(1, productId);
		preparedStatement.execute();
		updateCount = preparedStatement.getUpdateCount();
		preparedStatement.close();
		connection.close();
		return updateCount > 0;
	}
	
	@Override
	public boolean update(int productId, Product newProduct) throws IOException, ClassNotFoundException, SQLException {
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
		preparedStatement.setString(1, newProduct.getName());
		preparedStatement.setInt(2, newProduct.getPrice());
		preparedStatement.setInt(3, newProduct.getStock());
		preparedStatement.setInt(4, productId);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();
		return true;
	}
	

	public int getMaxId() throws SQLException, ClassNotFoundException{
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(GET_MAX_ID_QUERY);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		int result = rs.getInt("COUNT");
		rs.close();
		preparedStatement.close();
		connection.close();
		return result;
	}
}
