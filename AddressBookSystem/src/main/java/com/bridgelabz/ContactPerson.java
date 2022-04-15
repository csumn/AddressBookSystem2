package com.bridgelabz;

import com.opencsv.bean.CsvBindByName;

public class ContactPerson {
	@CsvBindByName(column = "First Name")
	private String firstName;
	@CsvBindByName(column = "Last Name")
	private String lastName;
	@CsvBindByName(column = "email")
	private String email;
	@CsvBindByName(column = "phoneNumber")
	private long phoneNumber;
	@CsvBindByName(column = "city")
	private String city; 
	@CsvBindByName(column = "state")
	private String state; 
	@CsvBindByName(column = "zip")
	private long zip;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getZip() {
		return zip;
	}
	public void setZip(long zip) {
		this.zip = zip;
	}
	@Override
	public String toString() {
		return "ContactPerson [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", city=" + city + ", state=" + state + ", zip=" + zip + "]";
	}
}
