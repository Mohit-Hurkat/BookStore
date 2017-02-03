package com.book.helper;

import java.sql.SQLException;
import java.util.Scanner;

import com.book.bean.Product;
import com.book.bl.CentralLogic;

public class ProductData {
	private Product product;
	
	public void input() throws ClassNotFoundException, SQLException{
		CentralLogic centralBl = new CentralLogic();
		int productId, productPrice, productStock;
		Scanner scanner = new Scanner(System.in);
		productId = centralBl.getMaxProductId() + 1;
		System.out.println("Enter Product name: ");
		String productName = scanner.nextLine();
		System.out.println("Enter Product Price: ");
		productPrice  = Integer.parseInt(scanner.nextLine());
		System.out.println("Enter Product Stock: ");
		productStock  = Integer.parseInt(scanner.nextLine());
		product = new Product(productId, productName, productPrice, productStock);
	}
	
	public Product getProduct(){
		return product;
	}
}
