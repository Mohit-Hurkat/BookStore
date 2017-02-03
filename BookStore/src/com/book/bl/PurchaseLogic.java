package com.book.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.book.bean.Customer;
import com.book.bean.Product;
import com.book.bean.Purchase;
import com.book.dao.PurchaseDao;
import com.book.dao.PurchaseDaoImpl;

public class PurchaseLogic {
	private PurchaseDao purchaseDao = new PurchaseDaoImpl();
	
	public void logPurchase(Customer customer, ShoppingCart shoppingCart) throws IOException, ClassNotFoundException, SQLException {
		Purchase purchase = new Purchase(customer, shoppingCart.getProducts());
		this.add(purchase);
	}
	
	public boolean add(Purchase purchase) throws IOException, ClassNotFoundException, SQLException{
		return purchaseDao.insert(purchase);
	}

	public Purchase search(int id) throws ClassNotFoundException, IOException{
		return purchaseDao.search(id);
	}
	public HashMap<Integer,HashMap<Customer,HashMap<Product,Integer>>> allRecords() throws ClassNotFoundException, IOException, SQLException{
		return purchaseDao.displayAll();
	}
	
	


}
