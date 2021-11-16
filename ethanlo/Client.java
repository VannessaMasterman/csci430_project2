package ethanlo;
//import java.io.*;
//import java.util.*;

public class Client {
	private String name;
	private String phone;
	private String address;
	private int id;
	
	public Client() {
		
	}
	
	public Client(String name, String phone, String address, int id) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return("| Client id: " + this.getId() + " | Client Name: " + this.getName() + 
		" | Phone: " + this.getPhone() + " | Address: " + this.getAddress() + " |\n");
	}
	
}

