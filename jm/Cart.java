package jm;

/*---Cart Class
Author: Israel Musa--*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import fsm.FSMManager;
import warehouseInventory.warehouse.Inventory;
import warehouseInventory.warehouse.ProductList;

public class Cart {
	
/**
 * Nessa's changes:11/5
 * The cart really doesn't need to worry about who is supplying the product. The warehouse is where the client is purchasing from. 
 * The warehouse itself acts like a facade for the supply chain that produces the items and supplies them to the warehouse.
 * 
 * I added an inner class to hold product data to help seperate from the external product classes which are highly specialized to the needs of the Inventory
 * 
 * @author Vannessa
 */

	ArrayList<CartProductEntry> product;
	double totalAmount;
	double chargeAmount;
	double tax;
    
	public Cart() {
		this.product = new ArrayList<CartProductEntry>();
		this.totalAmount = 0;
		this.chargeAmount = 0;
		this.tax = 0;
	}
	public void addToCart(String productID, int quantity) {
		int index = Inventory.instance().isNewProduct(productID);
		if (index == -1){
			// this product doesn't exist
			FSMManager.display.displayMessage("ERROR: product of id '"+productID+"' does not exist. Failed to add to inventory", true);
			return;
		}
		ProductList pl = Inventory.instance().warehouseInventory.get(index);
		this.product.add(new CartProductEntry(productID, quantity, pl.getRetailPrice()));
	}
	public void showCart() {
		ListIterator<CartProductEntry> iterator = product.listIterator();
		while(iterator.hasNext()) {
			// TODO this is really not what we want for this. I'm too tired to port it though so this is going to get left to the individual project submissions
			// leaving a to do so people using IDEs can find this quickly
			CartProductEntry product1 = iterator.next();
			System.out.println(product1);
		}
	}
	public void removeFromCart(String productID, int quantity) {
		ListIterator<CartProductEntry> iterator1 = product.listIterator();
		while(iterator1.hasNext()) {
			CartProductEntry pe = iterator1.next();
			if (pe.getProductName().equals(productID)) {
				pe.quantity -= quantity;
				if (pe.quantity <= 0){
					this.product.remove(pe);
				}
				return;
			}
		}
	}
	public double getTotalAmount() {
		ListIterator<CartProductEntry> iterator2 = product.listIterator();
		this.totalAmount = 0;
		while(iterator2.hasNext()) {
			CartProductEntry product3 = iterator2.next();
			this.totalAmount = this.totalAmount + (product3.getUnitPrice() * product3.quantity);
		}
		return this.totalAmount;
	}
	public double getChargeAmount() {
		this.chargeAmount = 0;
		this.tax = this.totalAmount * (0.14);
		this.chargeAmount = this.totalAmount + this.tax;
		return this.chargeAmount;
	}
	public void printInvoice() {
		ListIterator<CartProductEntry> iterator3 = product.listIterator();
		while(iterator3.hasNext()) {
			CartProductEntry product4 = iterator3.next();
			System.out.print(product4.getProductName() + "\t");
			System.out.print(product4.quantity + "\t");
			System.out.print(product4.getUnitPrice() + "\t");
			System.out.println(product4.getUnitPrice() * product4.quantity);
		}
		System.out.println("\t\t\t" + "Total: " + this.getTotalAmount());
		this.getTotalAmount();
		System.out.println("\t\t\t" + "Tax  : " + this.tax);
		System.out.println("\t\t\t" + "Total: " + this.getChargeAmount());
	}

	public Iterator<CartProductEntry> getIterator(){
		return product.iterator();
	}
}