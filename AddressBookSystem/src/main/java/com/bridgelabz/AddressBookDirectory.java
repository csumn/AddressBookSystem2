package com.bridgelabz;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookDirectory implements AddressBookDirectoryInterface{
	private static final int MAIN_EXIT = 4;
	public AddressBook addressBook;
	Map<String,AddressBook> addressBookDirectory = new HashMap<String,AddressBook>();

	@Override
	public void operationDirectory() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean condition = true;
		do {
			System.out.println("\nSelect any option : \n\n1. Add AddressBook"
					+ "\n2. Edit Existing Addressbook\n3. Display Addressbook"
					+ "\n"+MAIN_EXIT+". Exit");
			switch(scanner.nextInt()) {
			case 1:
				addAddressBook();
				break;
			case 2:
				editAddressBook();
				break;
			case 3:
				displayDirectoryDetails();
				break;
			case 4:
				condition = false;
				System.out.println("Terminated....");
				break;
			default:
				System.out.println("Kindly Enter a valid input!");
			}
		}while(condition);
	}

	@Override
	public void addAddressBook() {
		AddressBook addressBook = new AddressBook();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the AddressBook name you want to add ?");
		String bookName = scanner.next();
		if(addressBookDirectory.containsKey(bookName)) {
			System.out.println("Book with that name already exist");
		}else {
			addressBook.setAddressBookName(bookName);
			addressBookDirectory.put(bookName, addressBook);
			System.out.println("\nCreated a AddressBook : "+bookName);
		}
	}

	@Override
	public void editAddressBook() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the AddressBook name on which you want to add the details");
		String bookName = scanner.next();
		if(addressBookDirectory.containsKey(bookName)) {
			addressBook = addressBookDirectory.get(bookName);
			addressBook.operation();
		}else
			System.out.println("Book with that name doesn't exist..");
	}

	@Override
	public void displayDirectoryDetails() {
		System.out.println("----- Address Book Details -----");
		for (String completeAddressBook : addressBookDirectory.keySet()) {

			System.out.println(completeAddressBook);
		}
		System.out.println("-----------------------------------------");
	}
}
