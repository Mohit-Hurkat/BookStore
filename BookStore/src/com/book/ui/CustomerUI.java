package com.book.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

import com.book.bean.Customer;
import com.book.bean.Product;
import com.book.bl.CentralLogic;
import com.book.bl.CustomerCartMap;
import com.book.bl.CustomerLogic;
import com.book.bl.ProductLogic;
import com.book.bl.ShoppingCart;
import com.book.helper.CustomerAuthenticator;
import com.book.helper.CustomerData;

public class CustomerUI {
	private Customer customer;
	private ShoppingCart shoppingCart;
	
	private CentralLogic centralBl = new CentralLogic();
	private CustomerLogic cbl = new CustomerLogic();
	private ProductLogic pbl = new ProductLogic();
	
	//String Constants
	private static final String MENU_OPTIONS = "1. View catalogue" +
			"\n" + "2. Add Products to Cart" + "\n" + "3. Checkout" +
			"\n" + "4. View Cart" + "\n" + "5. Discard products from cart" + 
			"\n" + "6. Update product quantities in cart" + "\n" + "7. Exit";
	private static final String CATALOGUE_DISPLAY_FAILED_MSG =
			"Could not display product catalogue";
	private static final String NONEXISTENT_PRODUCT_MSG = 
			"Product does not exist";
	private static final String PRODUCT_DISPLAY_FAILED_MSG = 
			"Product display failed";
	private static final String CHECKOUT_FAILED_MSG =
			"Checkout failed";
	private static final String PRODUCT_NOT_IN_CART_MSG = 
			"Product is not in cart";
	private static final String EXIT_MSG = "Exit";
	private static final String ERROR_DESC_PREFIX_MSG = "Error";
	private static final String INVALID_CHOICE_MSG = "Invalid Choice";
	
	
	public CustomerUI(Customer customer) {
		this.customer = customer;
		cbl.assignCartToCustomer(customer);
		shoppingCart = CustomerCartMap.getCartFromCustomer(customer);
	}
	
	public void displayMenu(){
		System.out.println(MENU_OPTIONS);
	}
	
	public boolean choice(int choice) {
		Scanner scanner = new Scanner(System.in);
		
		switch(choice){
		case 1:
			try {
				System.out.println(pbl.allRecords());
			} catch (IOException | ClassNotFoundException | SQLException e) {
				System.out.println(CATALOGUE_DISPLAY_FAILED_MSG +
						ERROR_DESC_PREFIX_MSG + ": " + e);
				e.printStackTrace();
			} 
			break;
		case 2:
			try {
				int productId;
				do{
					System.out.print("Enter product ID (0 to cancel): ");
					String tempInput = scanner.next();
					if(!tempInput.matches("\\d+")){
						System.out.println("Please enter a positive integral value.");
					}
					else{
						productId = Integer.parseInt(tempInput);
						break;
					}
				} while(true);
				if(productId == 0)
					break;
				Product product = pbl.search(productId);
				if(product == null){
					System.out.println(NONEXISTENT_PRODUCT_MSG);
				}
				else{
					int productQuantity;
					do {
						System.out.print("Enter product quantity (0 to cancel): ");
						productQuantity = scanner.nextInt();
						if(productQuantity < 0)
							System.out.println("Quantity should be a positive integer.");
					} while(productQuantity < 0);
					if(productQuantity > 0){
						boolean isSuccessful = centralBl.addProductToCart(customer, product, productQuantity);
						if(!isSuccessful) 
							System.out.println("Desired quantity > Stock.");
						else 
							System.out.println("Product added to cart");
					}
						
				}
				
			}
			catch(IOException | ClassNotFoundException | SQLException e){
				System.out.println(PRODUCT_DISPLAY_FAILED_MSG +
						". " + ERROR_DESC_PREFIX_MSG + ":");
				e.printStackTrace();
			} 
			break;
		case 3:
			try{
				centralBl.checkoutCart(customer);
			}
			catch(IOException | ClassNotFoundException | SQLException e){
				System.out.println(CHECKOUT_FAILED_MSG);
				e.printStackTrace();
			}
			break;
		case 4:
			System.out.println(shoppingCart.getProducts());
			break;
		case 5:
			try{
				System.out.println("Enter id of product to be discarded: ");
				int productId = scanner.nextInt();
				Map.Entry<Product, Boolean> result = centralBl.deleteItemFromCart(customer, productId);
				if(result.getKey() == null)
					System.out.println(NONEXISTENT_PRODUCT_MSG);
				else if(!result.getValue())
					System.out.println(PRODUCT_NOT_IN_CART_MSG);
			}
			catch(IOException | ClassNotFoundException | SQLException e){
				System.out.println("ERROR");
			}
			break;
		case 6:
			try{
				System.out.println("Enter product id: ");
				int productId = scanner.nextInt();
				System.out.println("Enter product quantity: ");
				int productQuantity = scanner.nextInt();
				centralBl.updateProductQuantityInCart(customer, productId, productQuantity);
			}
			catch(IOException | ClassNotFoundException | SQLException e){
				System.out.println("ERROR");
			}
			break;
		case 7:
			return false;
		default:
			System.out.println(INVALID_CHOICE_MSG);
		}
		return true;
	}
	
	
	
	
	
}
