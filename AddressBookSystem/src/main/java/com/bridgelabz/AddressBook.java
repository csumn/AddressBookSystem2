package com.bridgelabz;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBook implements AddressBookInterface{
	public static final int ADDRESS_BOOK_EXIT = 10;
	Map<String, ContactPerson> contactList = new HashMap<String,ContactPerson>();

	public static HashMap<String, ArrayList<ContactPerson>> personByCity  = new HashMap<String, ArrayList<ContactPerson>>();
	public static HashMap<String, ArrayList<ContactPerson>> personByState = new HashMap<String, ArrayList<ContactPerson>>();
	public static String addressBookName;
	boolean conditionForDuplicateCheck = false;

	public enum IOService {
		CONSOLE_IO, FILE_IO
	}

	public void addPersonToCity(ContactPerson contact) {
		if (personByCity.containsKey(contact.getCity())) {
			personByCity.get(contact.getCity()).add(contact);
		}
		else {
			ArrayList<ContactPerson> cityList = new ArrayList<ContactPerson>();
			cityList.add(contact);
			personByCity.put(contact.getCity(), cityList);
		}
	}

	public void addPersonToState(ContactPerson contact) {
		if (personByState.containsKey(contact.getState())) {			
			personByState.get(contact.getState()).add(contact);
		}
		else {
			ArrayList<ContactPerson> stateList = new ArrayList<ContactPerson>();
			stateList.add(contact);
			personByState.put(contact.getState(), stateList);
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
	public void operation(){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean condition = true;
		do{
			System.out.println("\nSelect any option which you want to perform on selected AddressBook\n");
			System.out.println("1. Add To Address Book\n2. Edit Existing Entry\n3. Display Address book\n"
					+ "4. Delete Contact\n5. Sort the Address Book\n6. Write To File\n7. Read From File\n"
					+ "8. Write To CSV File\n9. Read From CSV File\n"+ADDRESS_BOOK_EXIT+". Exit");
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
			case 5:
				sortAddressBook();
				break;
			case 6:
				writeToAddressBookFile(IOService.FILE_IO);
				System.out.println("Written To file");
				break;
			case 7:
				readDataFromFile(IOService.FILE_IO);
				break;
			case 8:
				try {
					writeDataToCSV();
				}catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
					System.out.println(e);
				}
				break;
			case 9:
				try {
					readDataFromCSV();
				}catch (IOException e) {
					System.out.println(e);
				}
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
			contactPerson.setCity(scanner.next());

			System.out.println("Enter State : ");
			contactPerson.setState(scanner.next());

			System.out.println("Enter Zipcode : ");
			contactPerson.setZip(scanner.nextLong());

			System.out.println("Enter EmailID : ");
			contactPerson.setEmail(scanner.next());

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
				person.setCity(scanner.next());	
				System.out.println("Updated!");
				break;
			case 6:
				System.out.println("Enter the correct State :");
				person.setState(scanner.next());	
				System.out.println("Updated!");
				break;
			case 7:
				System.out.println("Enter the correct ZipCode :");
				person.setZip(scanner.nextLong());	
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

	@Override
	public void printSortedList(List<ContactPerson> sortedContactList) {
		System.out.println("*** Sorted Address Book "+AddressBook.getAddressBookName()+"***");
		Iterator<ContactPerson> iterator = sortedContactList.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
			System.out.println();
		}
		System.out.println("-----------------------------------------");
	}

	@Override
	public void sortAddressBook() {
		List<ContactPerson> sortedContactList;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Bookw to be sorted in : ");
		System.out.println("1. FirstName\n2. City\n3. State\n4. Zip Code");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			sortedContactList = contactList.values().stream().sorted((firstperson, secondperson) -> firstperson.getFirstName().compareTo(secondperson.getFirstName()))
			.collect(Collectors.toList());
			printSortedList(sortedContactList);
			break;
		case 2: sortedContactList = contactList.values().stream()
				.sorted((firstperson, secondperson) -> firstperson.getCity().compareTo(secondperson.getCity()))
				.collect(Collectors.toList());
		printSortedList(sortedContactList);
		break;
		case 3: sortedContactList = contactList.values().stream()
				.sorted((firstperson, secondperson) -> firstperson.getState().compareTo(secondperson.getState()))
				.collect(Collectors.toList());
		printSortedList(sortedContactList);
		break;
		case 4: sortedContactList = contactList.values().stream()
				.sorted((firstperson, secondperson) -> Long.valueOf(firstperson.getZip()).compareTo(Long.valueOf(secondperson.getZip())))
				.collect(Collectors.toList());
		printSortedList(sortedContactList);
		break;
		default:
			System.out.println("Kindly enter a valid input...");
			break;
		}
	}

	@Override
	public void writeToAddressBookFile(IOService ioService) {
		if(ioService.equals(IOService.CONSOLE_IO))
			displayContents();

		else if(ioService.equals(IOService.FILE_IO)) {
			String bookName = AddressBook.getAddressBookName();
			String fileName = bookName+".txt";
			new AddressBookFileIO().writeToAddressBookFile(fileName, contactList);
		}
	}

	@Override
	public void printData(IOService fileIo) {
		String bookName = AddressBook.getAddressBookName();
		String fileName = bookName+".txt";
		if(fileIo.equals(IOService.FILE_IO)) new AddressBookFileIO().printData(fileName);
	}

	@Override
	public long countEntries(IOService fileIo) {

		String bookName = AddressBook.getAddressBookName();
		String fileName = bookName+".txt";
		if(fileIo.equals(IOService.FILE_IO)) 
			return new AddressBookFileIO().countEntries(fileName);

		return 0;
	}

	@Override
	public List<String> readDataFromFile(IOService fileIo) {

		List<String> detailsFile = new ArrayList<String>();
		if(fileIo.equals(IOService.FILE_IO)) {
			System.out.println("Contacts from file are : ");
			String bookName = AddressBook.getAddressBookName();
			String fileName = bookName+".txt";
			detailsFile = new AddressBookFileIO().readDataFromFile(fileName);

		}
		return detailsFile;
	}

	@Override
	public void writeDataToCSV() throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		String fileName = "./"+AddressBook.getAddressBookName()+"Contacts.csv";
		try (Writer writer = Files.newBufferedWriter(Paths.get(fileName));) {

			StatefulBeanToCsvBuilder<ContactPerson> builder = new StatefulBeanToCsvBuilder<>(writer);
			StatefulBeanToCsv<ContactPerson> beanWriter = builder.build();
			ArrayList<ContactPerson> listOfContacts= contactList.values().stream().collect(Collectors.toCollection(ArrayList::new));
			beanWriter.write(listOfContacts);
			writer.close();
			System.out.println("Written To CSV Successfully !");		
		}
		catch (IOException e) {
			System.out.println(e);
		}
	}


	@Override
	public <CsvValidationException extends Throwable> void readDataFromCSV()
			throws IOException, CsvValidationException {
		String fileName = "./"+AddressBook.getAddressBookName()+"Contacts.csv";
		try (Reader reader = Files.newBufferedReader(Paths.get(fileName));
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();){

			String[] nextRecord;
			while ((nextRecord = csvReader.readNext()) != null) {
				System.out.println("First Name = " + nextRecord[2]);
				System.out.println("Last Name = " + nextRecord[3]);
				System.out.println("City = " + nextRecord[0]);
				System.out.println("State = " + nextRecord[5]);
				System.out.println("Email = " + nextRecord[1]);
				System.out.println("Phone Number = " + nextRecord[4]);
				System.out.println("Zip Code = " + nextRecord[6]);
				System.out.println("\n");
			}
		}
	}
}