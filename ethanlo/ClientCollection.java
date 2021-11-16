package ethanlo;
//import java.util.Scanner;
import java.util.Random;
//import java.io.*;
import java.util.*;

public class ClientCollection {
	ArrayList<Client> clientList = new ArrayList<Client>();
	static ClientCollection singleton;
	
	public ClientCollection() {
	}
	
	public ClientCollection(ArrayList<Client> clientList) {
		this.clientList = clientList;
	}
	
	public static ClientCollection instance() {
		if(singleton == null)
			singleton = new ClientCollection();
		return singleton;
	}
	
	public void addClient(String name, String phoneNumber, String address) {
		Random rand = new Random();
		int id = rand.nextInt(1000);
		Client newClient = new Client(name, phoneNumber, address, id);
		clientList.add(newClient);
	}
	
	public void printClientList() {
		/* for(Client client: clientList) {
			System.out.println(clientList);
		} */
		
		System.out.println(clientList);
	}

	public Iterator<Client> getIterator(){
		return clientList.iterator();
	}
	
}