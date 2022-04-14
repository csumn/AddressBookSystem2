package com.bridgelabz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookDirectory implements AddressBookDirectoryInterface{
	private static final int MAIN_EXIT = 10;
	public AddressBook addressBook;
	Map<String,AddressBook> addressBookDirectory = new HashMap<String,AddressBook>();

	@Override
	public void operationDirectory() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean condition = true;
		do {
			System.out.println("\nSelect any option : \n\n1. Add AddressBook"
					+ "\n2. Edit Existing Addressbook"
					+"\n3. Search Person by City\n4. Search Person by State\n5. Display Addressbook"
					+"\n6. View by City\n7. View by State\n8. Count by City\n9. Count by State"
					+ "\n"+MAIN_EXIT+". Exit");
			switch(scanner.nextInt()) {
			case 1:
				addAddressBook();
				break;
			case 2:
				editAddressBook();
				break;
			case 3:
				searchPersonByCity();
				break;
			case 4:
				searchPersonByState();
				break;
			case 5:
				displayDirectoryDetails();
			case 6:
				displayPeopleByRegion(AddressBook.personByCity);
				break;
			case 7:
				displayPeopleByRegion(AddressBook.personByState);
				break;
			case 8:
				countPeopleByCity();
				break;
			case 9:
				countPeopleByState();
				break;
			case MAIN_EXIT:
				condition = false;
				System.out.println("Terminated....");
				break;
			default:
				System.out.println("Kindly enter a valid input");
				break;
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

	@Override
	public void searchPersonByCity() {
		if (!addressBookDirectory.isEmpty()) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter city name to search a person");
			String cityName = scanner.next();
			System.out.println("Name of the Person : ");
			String personName = scanner.next();

			for(AddressBook addressBook : addressBookDirectory.values()) {
				ArrayList<ContactPerson> contactList = addressBook.getContact();
				contactList.stream()
				.filter(p -> p.getFirstName().equals(personName) && p.getAddress().getCity().equals(cityName))
				.forEach(contact -> System.out.println(contact));
			}
			System.out.println("end of list...");
		}else {
			System.out.println("No contacts as of now");
		}
	}	

	@Override
	public void searchPersonByState(){
		if (!addressBookDirectory.isEmpty()) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter state name to search a person");
			String stateName = scanner.next();
			System.out.println("Enter the name of the Person : ");
			String personName = scanner.next();
			for(AddressBook addressBook : addressBookDirectory.values()) {
				ArrayList<ContactPerson> contactList = ((AddressBook) addressBook).getContact();
				contactList.stream().filter(p -> p.getFirstName().equals(personName) && p.getAddress()
						.getState().equals(stateName))
				.forEach(contact -> System.out.println(contact));		
			}
			System.out.println("end of list...");
		}else {
			System.out.println("No contacts as of now");
		}
	}

	@Override
	public void displayPeopleByRegion(HashMap<String, ArrayList<ContactPerson>> listToDisplay) {
		ArrayList<ContactPerson> list;
		for (String name : listToDisplay.keySet()) {
			System.out.println("Contacts in : " + name);
			list = listToDisplay.get(name);
			for (ContactPerson contact : list) {
				System.out.println(contact);
			}
		}		
	}

	@Override
	public void countPeopleByCity() {
		ArrayList<ContactPerson> contactList = null ;
		if (!addressBookDirectory.isEmpty()) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter City Name : ");
			String city = scanner.next();

			for(AddressBook addressBook : addressBookDirectory.values()) {
				contactList = addressBook.getContact();
				System.out.println(contactList.stream()
						.filter(p -> p.getAddress().getCity().equals(city)).count());

			}
		}else
			System.out.println("empty as of now....");
	}

	@Override
	public void countPeopleByState() {
		ArrayList<ContactPerson> contactList = null ;
		if (!addressBookDirectory.isEmpty()) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter State Name : ");
			String state = scanner.next();

			for(AddressBook addressBook : addressBookDirectory.values()) {
				contactList = addressBook.getContact();
				System.out.println(contactList.stream()
						.filter(p -> p.getAddress().getState().equals(state)).count()); 

			}
		}else
			System.out.println("empty as of now....");
	}
}