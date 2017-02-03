package com.book.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.book.bean.Admin;
import com.book.bean.Customer;
import com.book.bean.Product;
import com.book.bean.Purchase;
import com.book.bl.AdminLogic;
import com.book.bl.CustomerLogic;
import com.book.bl.ProductLogic;
import com.book.bl.PurchaseLogic;
import com.book.helper.ProductData;


public class AdminUI {
	private CustomerLogic cbl = new CustomerLogic();
	private AdminLogic abl = new AdminLogic();
	private ProductLogic pbl = new ProductLogic();
	private ProductData pd=new ProductData();
	private PurchaseLogic purchasebl= new PurchaseLogic();


	private static final String ADMIN_MENU_OPTIONS ="1. List Customers"+	
			"\n" + "2. Remove Customer" +	
			"\n" + "3. Add Product" + "\n" + "4. Remove Product" +
			"\n" + "5. List All Products"+"\n"+ "6. View Purchase History" +
			"\n" + "7. Update Admin" + "\n" + "8. Exit";
	private static final String CATALOGUE_DISPLAY_FAILED_MSG =
			"Could not display product catalogue";
	private static final String ERROR_DESC_PREFIX_MSG = "Error";
	
	
	public void displayMenu(){
		System.out.println(ADMIN_MENU_OPTIONS);
	}
	
	public boolean choice(int choice) throws IOException {
		Scanner sc= new Scanner(System.in);
		
		switch(choice){
		
		case 1:
			try {
				List<Customer> cusList=cbl.allRecords();
				for(Customer cus:cusList){
					System.out.println(cus.getUsername());
				}
			} catch (ClassNotFoundException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		
		case 2:
			System.out.println("Enter Username you want to Delete : ");
			String j=sc.next();
			try {
				boolean c=cbl.delete(j);
				if(c==true){
					System.out.println("Record Deleted!");
				}
				else{
					System.out.println("Username does not exist!");
				}
			} catch (ClassNotFoundException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		

	case 3:
			try {
				pd.input();
				boolean status=pbl.add(pd.getProduct());
				if(status){
					System.out.println("Prdocut Record Inserted!");
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		break;
		
	case 4:	
		System.out.println("Enter Product-ID you want to Delete : ");
		int k=sc.nextInt();
		try {
			boolean c=pbl.deleteRecord(k);
			if(c==true){
				System.out.println("Product Record Deleted!");
			}
			else{
				System.out.println("Product does not exist!");
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		break;
	case 5:
		try {
			List<Product> proList=pbl.allRecords();
			for(Product pro:proList){
				System.out.println(pro);
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		break;
		
	case 6:
		try{
			HashMap<Integer, HashMap<Customer, HashMap<Product, Integer>>> purchaseList=purchasebl.allRecords();
		for(Integer pur:purchaseList.keySet()){
			System.out.print(pur + "\t");
			System.out.println(purchaseList.get(pur));
		}
	} catch (ClassNotFoundException | IOException | SQLException e) {
		e.printStackTrace();
	}
		break;
		
	case 7:
		try{
			System.out.println("Enter Username: ");
			String username = sc.next();
			System.out.println("Enter Password: ");
			String password = sc.next();
			Admin newAdmin=new Admin(password);
			abl.update(username, newAdmin );
		}
		catch(IOException | ClassNotFoundException | SQLException e){
			System.out.println("ERROR");
		}
		break;
	case 8:
		return false;
	default:
		System.out.println("Invalid choice");
	}
		return true;
	}
	
}