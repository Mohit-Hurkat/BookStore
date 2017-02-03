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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.book.bean.Customer;
import com.book.bean.Product;
import com.book.bean.Purchase;
import com.book.helper.JDBCConnection;
import com.book.helper.MyAppendObjectOutputStream;

public class PurchaseDaoImpl implements PurchaseDao {

	HashMap<Product,Integer> map;
	CustomerDaoImpl cdl=new CustomerDaoImpl();
	ProductDaoImpl pdl=new ProductDaoImpl();
	//ArrayList<Product> productsList=new ArrayList<Product>();
	private static final String INSERT_QUERY = "INSERT INTO PURCHASE(PURCHASE_ID, USERNAME, PRODUCT_ID, PRICE, QUANTITY) VALUES(?, ?, ?, ?, ?)";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM PURCHASE";
	private static final String GET_MAX_ID_QUERY = "SELECT COALESCE(MAX(PURCHASE_ID), 0) AS COUNT FROM PURCHASE";
	private static Customer customer = null;
	private static Product product = null;
	@Override
	public boolean insert(Purchase purchase) throws IOException, ClassNotFoundException, SQLException {
		HashMap<Product,Integer> productsList=purchase.getProductMap();
		int numAffectedRows = 0;
		Connection connection = JDBCConnection.getConnection();
		int purchaseId = this.getMaxId() + 1;
		for(Map.Entry<Product,Integer> p:productsList.entrySet()){
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
			preparedStatement.setInt(1, purchaseId);
			preparedStatement.setString(2, purchase.getCustomer().getUsername());
			preparedStatement.setInt(3, p.getKey().getId());
			preparedStatement.setInt(4, ((p.getKey().getPrice())*(p.getValue())));
			preparedStatement.setInt(5, p.getValue());
			numAffectedRows = preparedStatement.executeUpdate();
			preparedStatement.close();
		}
		connection.close();
		return numAffectedRows > 0;
	}
	
	public HashMap<Integer, HashMap<Customer, HashMap<Product, Integer>>> displayAll() throws IOException,ClassNotFoundException, SQLException{
		HashMap<Integer, HashMap<Customer, HashMap<Product, Integer>>> purchaseMap = new HashMap<>();
		HashMap<Product, Integer> productMap = new HashMap<>();
		HashMap<Customer, HashMap<Product, Integer>> customerMap = new HashMap<>();
		map = new HashMap<>();
		List<ArrayList<Purchase>> purchaseList = new ArrayList<>();
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()){
			int purchaseId = rs.getInt("PURCHASE_ID");
			String userName = rs.getString("USERNAME");
			customer=cdl.search(userName);
			int productId = rs.getInt("PRODUCT_ID");
			int productPrice = rs.getInt("PRICE");
			int productQuantity = rs.getInt("QUANTITY");
			product=pdl.search(productId);
			map.put(product, productQuantity);
			//Purchase purchase= new Purchase(customer, map);
			
			if(!purchaseMap.containsKey(purchaseId)){
				purchaseMap.put(purchaseId, new HashMap<>());
			}
			customerMap = purchaseMap.get(purchaseId);
			if(!customerMap.containsKey(userName)){
				customerMap.put(customer, new HashMap<>());
			}
			productMap = customerMap.get(customer);
			productMap.put(product, productQuantity);
		}
		rs.close();
		preparedStatement.close();
		connection.close();
		return purchaseMap;
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
	
	@Override
	public Purchase search(int id) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
