package com.bridgelabz;

import java.io.IOException;
import java.util.List;

import com.bridgelabz.AddressBook.IOService;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public interface AddressBookInterface {

	public void operation();
	public void addContact();
	public void displayContents();
	public void editPerson();
	public void deletePerson();
	public void sortAddressBook();
	public void printSortedList(List<ContactPerson> sortedContactList);
	public void writeToAddressBookFile(IOService ioService) ;
	public void printData(IOService fileIo) ;
	public long countEntries(IOService fileIo); 
	public List<String> readDataFromFile(IOService fileIo); 
	public void writeDataToCSV() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;
	public <CsvValidationException extends Throwable> void readDataFromCSV() throws IOException, CsvValidationException;
	public void writeDataToJson() throws IOException;
	public void readDataFromJson() throws IOException;
}