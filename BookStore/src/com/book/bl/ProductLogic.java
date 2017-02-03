package com.book.bl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.book.bean.Customer;
import com.book.bean.Product;
import com.book.dao.ProductDao;
import com.book.dao.ProductDaoImpl;

public class ProductLogic {
	private ProductDao pd=new ProductDaoImpl();
	
	public boolean add(Product product) throws IOException, SQLException, ClassNotFoundException{
		return pd.insert(product);
	}

	public Product search(int id) throws ClassNotFoundException, IOException, SQLException{
		return pd.search(id);
	}
	
	public List<Product> allRecords() throws ClassNotFoundException, IOException, SQLException{
		return pd.displayAll();
	}
	
	public boolean deleteRecord(int id) throws FileNotFoundException, ClassNotFoundException, IOException, SQLException{
		return pd.delete(id);
	}
	
	public boolean updateRecord(int id, Product newProduct) throws ClassNotFoundException, IOException, SQLException{
		return pd.update(id, newProduct);
	}

	public int getMaxId() throws ClassNotFoundException, SQLException {
		return pd.getMaxId();
	}

	
}
