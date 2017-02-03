package com.book.bl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import com.book.bean.Customer;
import com.book.bean.Product;

public class CentralLogic {
	private ProductLogic productBl = new ProductLogic();
	private PurchaseLogic purchaseBl = new PurchaseLogic();
	private PaymentLogic paymentBl = new PaymentLogic();
	
	public boolean addProductToCart(Customer customer, Product product, int productQuantity) {
		
		ShoppingCart shoppingCart = CustomerCartMap.getCartFromCustomer(customer);
		int cartQuantity = shoppingCart.getProducts().get(product) == null ? 0 : shoppingCart.getProducts().get(product);
		if(cartQuantity + productQuantity > product.getStock()||productQuantity < 0)
			return false;
		shoppingCart.addProductToCart(product, productQuantity);
		return true;
	}
	
	public Map.Entry<Product, Boolean> updateProductQuantityInCart(Customer customer, int productId, int productQuantity) throws ClassNotFoundException, IOException, SQLException {
		Product product = productBl.search(productId);
		if(product == null)
			return new AbstractMap.SimpleEntry<>(null, false);
		ShoppingCart shoppingCart = CustomerCartMap.getCartFromCustomer(customer);
		if(!shoppingCart.containsProduct(product) || productQuantity > product.getStock())
			return new AbstractMap.SimpleEntry<>(product, false);
		shoppingCart.updateProductQuantity(product, productQuantity);
		return new AbstractMap.SimpleEntry<>(product, true);
	}
	
	public boolean checkoutCart(Customer customer) throws IOException, ClassNotFoundException, SQLException{
		boolean isPaymentSuccessful = false;
		ShoppingCart shoppingCart = CustomerCartMap.getCartFromCustomer(customer);
		isPaymentSuccessful = paymentBl.processPayment(customer, 
				shoppingCart.getProducts());
		if(isPaymentSuccessful){
			HashMap<Product, Integer> productMap = shoppingCart.getProducts();
			//Updating product stock in DB
			for(Product product : productMap.keySet()){
				int productQuantity = productMap.get(product);
				product.setStock(product.getStock() - productQuantity);
				productBl.updateRecord(product.getId(), product);
			}
			//Empty cart
			shoppingCart.clear();
			//Logging purchase
			purchaseBl.logPurchase(customer, shoppingCart);
		}
		return isPaymentSuccessful;
		
	}

	public Map.Entry<Product, Boolean> deleteItemFromCart(Customer customer, int productId) throws ClassNotFoundException, IOException, SQLException {
		Product product = productBl.search(productId);
		if(product == null)
			return new AbstractMap.SimpleEntry<>(null, false);
		ShoppingCart shoppingCart = CustomerCartMap.getCartFromCustomer(customer);
		if(!shoppingCart.containsProduct(product))
			return new AbstractMap.SimpleEntry<>(product, false);
		shoppingCart.deleteProductFromCart(product);
		return new AbstractMap.SimpleEntry<>(product, true);
	}
	
	public int getMaxProductId() throws ClassNotFoundException, SQLException{
		return productBl.getMaxId();
	}
	
}
