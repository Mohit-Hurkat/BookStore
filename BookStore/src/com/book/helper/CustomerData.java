package com.book.helper;

import java.util.Scanner;

import com.book.bean.Customer;


	public class CustomerData {
		private Customer customer;
		public void input(){
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Username : ");
			String username=sc.next();
			System.out.println("Enter Password : ");
			String password=sc.next();
			System.out.println("Enter Name : ");
			String name=sc.next();
			System.out.println("Enter Phone Number : ");
			String phone=sc.next();
			System.out.println("Enter Address : ");
			String address=sc.next();
			customer=new Customer(username,password,name,phone, address);
		}
		
		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer=customer;
		}
	}
