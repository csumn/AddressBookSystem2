package com.bridgelabz;

public class Address {

	private String city; 
	private String state; 
	private long zip;

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public long getZip() {
		return zip;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return " city=" + city + ", state=" + state + ", zip=" + zip + "]";
	}

}
