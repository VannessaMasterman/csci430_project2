package ethanlo;
import java.util.Scanner;
//import java.io.*;
//import java.util.*; 

public class TestSupplier {
	public static void main(String args[]) {
		SupplierCollection s = SupplierCollection.instance();
		String name, phoneNumber, address;
		char input;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Would you like to create a supplier? y / n");
		input = scanner.next().charAt(0);
		scanner.nextLine();
		
		if(input == 'y') {
			while(input == 'y') {
				System.out.println("Please enter the supplier name: ");
				name = scanner.nextLine();
				
				System.out.println("Please enter the supplier phone number: ");
				phoneNumber = scanner.nextLine();
				
				System.out.println("Please enter the supplier address: ");
				address = scanner.nextLine();
				
				s.addSupplier(name, phoneNumber, address);
				
				System.out.println("Would you like to add another supplier? y / n ");
				input = scanner.next().charAt(0);
				scanner.nextLine();
			}
			
			//s.printSupplierList();
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