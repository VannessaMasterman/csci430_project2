package jm;

/**
 * A product entry for the cart
 * @author Nessa
 */
public class CartProductEntry {

	private final String name;
	private final double unitPrice;
	
	public int quantity;

	CartProductEntry(String name, int quantity, double unitPrice){
		this.name = name;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public String getProductName() {
		return name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public int getQuantity(){
		return quantity;
	}

	public double getTotalPrice(){
		return unitPrice * quantity;
	}

	public String toString(){
		return name + " x" + quantity + " : $" + unitPrice; 
	}

}