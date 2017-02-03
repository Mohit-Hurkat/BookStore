package com.book.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import com.book.bean.Product;

public interface ProductDao {
	public boolean insert(Product product)throws IOException, SQLException, ClassNotFoundException;
	public Product search(int id)throws IOException,ClassNotFoundException, SQLException;
	public List<Product> displayAll()throws IOException,ClassNotFoundException, SQLException;
	public boolean delete(int id) throws IOException, ClassNotFoundException, SQLException;
	public boolean update(int id,Product newEmp) throws IOException, ClassNotFoundException, SQLException;
	public int getMaxId() throws SQLException, ClassNotFoundException;
}
