package ethanlo;
import java.util.Scanner;
//import java.io.*;
//import java.util.*; 

public class TestClient {
	public static void main(String args[]) {
		ClientCollection c = ClientCollection.instance();
		String name, phoneNumber, address;
		char input;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Would you like to create a client? y / n");
		input = scanner.next().charAt(0);
		scanner.nextLine();
		
		if(input == 'y') {
			while(input == 'y') {
				System.out.println("Please enter the client name: ");
				name = scanner.nextLine();
				
				System.out.println("Please enter the client phone number: ");
				phoneNumber = scanner.nextLine();
				
				System.out.println("Please enter the client address: ");
				address = scanner.nextLine();
				
				c.addClient(name, phoneNumber, address);
				
				System.out.println("Would you like to add another client? y / n ");
				input = scanner.next().charAt(0);
				scanner.nextLine();
			}
			
			c.printClientList();
		}
		else if(input == 'n') {
			System.out.println("exiting test");
			System.exit(0);
		}
		else
			System.out.println("Please answer either y or n.");
		scanner.close();
	}
}