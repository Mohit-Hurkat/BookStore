package com.book.bl;

import java.util.HashMap;

import com.book.bean.Product;

public class ShoppingCart {
	HashMap<Product, Integer> cart = new HashMap<>();
	
	public void addProductToCart(Product product) {
		Integer productQuantity = cart.get(product);
		if(productQuantity == null) productQuantity = 0;
		cart.put(product, productQuantity + 1);
	}
	
	public void addProductToCart(Product product, int productQuantity) {
		if(!cart.containsKey(product))
			cart.put(product, 0);
		int totalQuantity = cart.get(product) + productQuantity;
		this.updateProductQuantity(product, totalQuantity);
	}
	
	
	public void deleteProductFromCart(Product product) {
		if(cart.containsKey(product))
			cart.remove(product);
	}
	
	public boolean updateProductQuantity(Product product, int quantity) {
		if(cart.containsKey(product) && quantity >= 0) {
			if(quantity > 0)
				cart.put(product, quantity);
			else
				deleteProductFromCart(product);
			return true;
		}
		return false;
	}
	
	public boolean containsProduct(Product product){
		return cart.containsKey(product);
	}
	
	public HashMap<Product, Integer> getProducts(){
		return cart;
	}
	
	public void clear() {
		cart.clear();
	}
}
