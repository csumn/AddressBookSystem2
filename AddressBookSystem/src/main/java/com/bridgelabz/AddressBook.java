package com.bridgelabz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class AddressBook implements AddressBookInterface{
	ArrayList <ContactPerson> contactList = new ArrayList<ContactPerson>();

	@Override
	public void displayContents() {
		Iterator<ContactPerson> iterator = contactList.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}		
	}

	@Override
	public void operation() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean condition = true;
		do{
			System.out.println("\nSelect any option from below\n");
			System.out.println("1. Add To Address Book\n2. Edit Existing Entry\n3. Display Address book\n4. Exit");

			switch (scanner.nextInt()) {
			case 1:
				addContact();
				break;
			case 2:
				editPerson();
				break;
			case 3:
				displayContents();
				break;
			case 4:
				condition = false;
				System.out.println("Terminated...");
				break;
			default: System.out.println("Kindly enter a valid inpu...");
			operation();
			break;
			}

		}while(condition);		
	}

	@Override
	public void addContact() {
		System.out.println("\n***Welcome To Address Book Program***\n");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		ContactPerson contactPerson = new ContactPerson();
		Address address = new Address();

		System.out.println("Enter First Name : ");
		contactPerson.setFirstName(scanner.next());

		System.out.println("Enter Last Name : ");
		contactPerson.setLastName(scanner.next());

		System.out.println("Enter ph.no : ");
		contactPerson.setPhoneNumber(scanner.nextLong());

		System.out.println("Enter City : ");
		address.setCity(scanner.next());

		System.out.println("Enter State : ");
		address.setState(scanner.next());

		System.out.println("Enter Zipcode : ");
		address.setZip(scanner.nextLong());

		System.out.println("Enter EmailID : ");
		contactPerson.setEmail(scanner.next());

		contactPerson.setAddress(address);

		contactList.add(contactPerson);
	}

	@Override
	public void editPerson() {
		System.out.println("Enter the first name on which you would like to make changes : ");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String fname = scanner.next();
		Iterator<ContactPerson> iterator = contactList.listIterator();

		while(iterator.hasNext()) {
			ContactPerson person = iterator.next();

			if(fname.equals(person.getFirstName()) ) {

				Address address = person.getAddress();
				System.out.println("\nSelect which one you want to update : ");
				System.out.println("1. Firstname\n2. Last Name\n3. Phone Number\n4. Email\n5. City\n6. State\n7. ZipCode\n8. Back");
				int choice = scanner.nextInt();

				switch(choice) {
				case 1: 
					System.out.println("Enter the correct First Name :");
					person.setFirstName(scanner.next());
					System.out.println("Updated!");
					break;
				case 2: 
					System.out.println("Enter the correct Last Name :");
					person.setLastName(scanner.next());
					System.out.println("Updated!");

					break;
				case 3: 
					System.out.println("Enter the correct Phone Number :");
					person.setPhoneNumber(scanner.nextLong());		
					System.out.println("Updated!");
					break;
				case 4: 
					System.out.println("Enter the correct Email Address :");
					person.setEmail(scanner.next());
					System.out.println("Updated!");
					break;
				case 5:
					System.out.println("Enter the correct City :");
					address.setCity(scanner.next());	
					System.out.println("Updated!");
					break;
				case 6:
					System.out.println("Enter the correct State :");
					address.setState(scanner.next());	
					System.out.println("Updated!");
					break;
				case 7:
					System.out.println("Enter the correct ZipCode :");
					address.setZip(scanner.nextLong());	
					System.out.println("Updated!");
					break;
				case 8:
					System.out.println("Edit Section got terminated");
					operation();
				default: 
					System.out.println("Kindly enter a valid input");
					break;
				}

			}else {
				System.out.println("Contact not found...");
			}
		}
	}
}
