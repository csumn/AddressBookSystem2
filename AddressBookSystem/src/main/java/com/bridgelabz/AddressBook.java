package com.bridgelabz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBook implements AddressBookInterface{
	public static final int ADDRESS_BOOK_EXIT = 5;
	Map<String, ContactPerson> contactList = new HashMap<String,ContactPerson>();

	public static HashMap<String, ArrayList<ContactPerson>> personByCity  = new HashMap<String, ArrayList<ContactPerson>>();
	public static HashMap<String, ArrayList<ContactPerson>> personByState = new HashMap<String, ArrayList<ContactPerson>>();
	public static String addressBookName;
	boolean conditionForDuplicateCheck = false;

	public void addPersonToCity(ContactPerson contact) {
		if (personByCity.containsKey(contact.getAddress().getCity())) {
			personByCity.get(contact.getAddress().getCity()).add(contact);
		}
		else {
			ArrayList<ContactPerson> cityList = new ArrayList<ContactPerson>();
			cityList.add(contact);
			personByCity.put(contact.getAddress().getCity(), cityList);
		}
	}

	public void addPersonToState(ContactPerson contact) {
		if (personByState.containsKey(contact.getAddress().getState())) {			
			personByState.get(contact.getAddress().getState()).add(contact);
		}
		else {
			ArrayList<ContactPerson> stateList = new ArrayList<ContactPerson>();
			stateList.add(contact);
			personByState.put(contact.getAddress().getState(), stateList);
		}
	}

	public static String getAddressBookName() {
		return addressBookName;
	}

	public void setAddressBookName(String addressBookName) {
		AddressBook.addressBookName = addressBookName;
	}

	public ArrayList<ContactPerson> getContact() {
		return new ArrayList<ContactPerson>(contactList.values());
	}

	@Override
	public void displayContents() {
		System.out.println("----- Contents of the Address Book "+AddressBook.getAddressBookName()+" -----");
		for (String eachContact : contactList.keySet()) {
			ContactPerson person = contactList.get(eachContact);
			System.out.println(person);
		}
		System.out.println("-----------------------------------------");
	}

	@Override
	public void operation() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean condition = true;
		do{
			System.out.println("\nSelect any option which you want to perform on selected AddressBook\n");
			System.out.println("1. Add To Address Book\n2. Edit Existing Entry\n3. Display Address book\n"+"4. Delete Contact\n"  +ADDRESS_BOOK_EXIT+". Exit");

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
				deletePerson();
				break;
			case ADDRESS_BOOK_EXIT:
				condition = false;
				System.out.println("Terminated and redirected to main menu...");
				break;
			default: 
				System.out.println("Kindly enter a valid input...");
				operation();
				break;
			}

		}while(condition);		
	}

	@Override
	public void addContact() {
		ContactPerson contactPerson = new ContactPerson();
		Address address = new Address();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter First Name : ");
		String firstName = scanner.next();
		contactPerson.setFirstName(firstName);

		contactList.entrySet().stream().forEach(p -> {
			if(p.getKey().equals(firstName)) {
				System.out.println("Contact Already Exists");
				conditionForDuplicateCheck = true;
				return;
			}
		});
		contactPerson.setFirstName(firstName);
		if(conditionForDuplicateCheck == false) {

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

			contactList.put(contactPerson.getFirstName(), contactPerson);
			addPersonToCity(contactPerson);
			addPersonToState(contactPerson);
		}
	}

	@Override
	public void editPerson() {
		ContactPerson person = new ContactPerson();

		System.out.println("Enter the first name on which you would like to make changes : ");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String fname = scanner.next();
		if(contactList.containsKey(fname)) {
			person = contactList.get(fname);
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

	@Override
	public void deletePerson() {
		System.out.println("Enter the first name of the person to be deleted");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String firstName = scanner.next();

		if (contactList.containsKey(firstName)){
			contactList.remove(firstName);
			System.out.println("Done, contact removed!");
		}else
		{
			System.out.println("Contact not found...");
		}
	}
}


