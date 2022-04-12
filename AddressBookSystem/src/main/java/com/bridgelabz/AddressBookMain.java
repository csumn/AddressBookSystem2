package com.bridgelabz;

import java.util.Scanner;

public class AddressBookMain {
	public static void main(String[] args) {
		System.out.println("\n***Welcome To Address Book Program***\n");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		ContactPerson contactPerson = new ContactPerson();
		Address address = new Address();
		AddressBook addressBook = new AddressBook();

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

		addressBook.addContact(contactPerson);
		addressBook.displayContents();
	}
}
