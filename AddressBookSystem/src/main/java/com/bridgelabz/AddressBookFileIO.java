package com.bridgelabz;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddressBookFileIO {
	public void writeToAddressBookFile(String fileName, Map<String, ContactPerson> contactList) {

		StringBuffer addressBookBuffer = new StringBuffer();
		contactList.values().stream().forEach(contact -> {
			String personDataString = contact.toString().concat("\n");
			addressBookBuffer.append(personDataString);
		});

		try {
			Files.write(Paths.get(fileName), addressBookBuffer.toString().getBytes());
		} 
		catch (IOException e) {
			System.out.println(e);
		}
	}

	public void printData(String fileName) {

		try {
			Files.lines(new File(fileName).toPath()).forEach(System.out::println);
		}
		catch(IOException e) {			
			System.out.println(e);
		}

	}

	public long countEntries(String fileName) {

		long entries=0;
		try {
			entries = Files.lines(new File(fileName).toPath()).count();
		}
		catch(IOException e) {
			System.out.println(e);
		}
		return entries;
	}

	public List<String> readDataFromFile(String fileName) {

		List<String> addressBookList = new ArrayList<String>();

		System.out.println("Reading from : "+fileName+"\n");
		try {
			Files.lines(new File(fileName).toPath())
			.map(line -> line.trim())
			.forEach(p -> {
				System.out.println(p);
				addressBookList.add(p);
			});

		}
		catch(IOException e){
			System.out.println(e);
		}
		return addressBookList;
	}
}
