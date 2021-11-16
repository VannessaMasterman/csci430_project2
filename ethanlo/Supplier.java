package ethanlo;
//import java.io.*;
import java.util.*;
import warehouseInventory.warehouse.Product;

public class Supplier {
	private String name;
	private String phone;
	private String address;
	private int id;
	private static ArrayList<Product> productInventory;
	
	public Supplier() {
		
	}
	
	public Supplier(String name, String phone, String address, int id) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.id = id;
	}

	public Supplier(String name, String phone, String address, int id, ArrayList<Product> productInventory) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.id = id;
		Supplier.productInventory = productInventory;
	}

	public void addProductInventory(Product p)
	{
		productInventory.add(p);
	}
	
	public ArrayList<Product> getProductInventory() {
		return productInventory;
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
		return("|id: " + this.getId() + " | Supplier Name: " + this.getName() + 
		" | Phone: " + this.getPhone() + " | Address: " + this.getAddress() + " |");
	}

	public void printProductList() {
		int temp = productInventory.size();

		System.out.println("Current Inventory : ");

		for (int i = 0; i < temp; i++)
		{
			Product supplierProduct = productInventory.get(i);
			String productName = supplierProduct.getsupplierID();
			System.out.println((i+1) + productName);
		}
	}
	
	
}