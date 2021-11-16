package nessa.process.Admin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import display.DisplayManager;
import fsm.FSMManager;
//import ethanlo.*;
import nessa.process.UIProcess;
import warehouseInventory.warehouse.*;

/**
	This class performs the "Accept Shipment" business process
*/
public class PAcceptShipment extends UIProcess {
	
	public PAcceptShipment(String category, String name, String description){
		super(category, name, description);	
	}
	
	/**
		This function is the common inherited function for all of the process classes
		This function is called when the user selects it from the menu
		Because this process is an admin only process, it should fail when given a vaild customer ID.
		@param clientID the ID for the client logged in, else -1
	*/
	@Override
	public void process(){
		DisplayManager d = FSMManager.display;
		d.setHeader("Accept Shipment");
		d.displayMessage("You will need to provide specific information related to the shipment. Please have this information ready", true);

		boolean running = true;
		int uniqueProducts = 0;
		int grossProduct = 0;
		// loop		- track values
		Map<String, Integer> shipment = new HashMap<String, Integer>();
		while(running){
			// display running header
			d.setHeader("Adding Product(s)");
			d.displayLargeMessage(Arrays.asList(new String[]{
				"-----Products-------------------------------",
				"Unique = " + uniqueProducts + " | Gross = " + grossProduct,
				"--------------------------------------------",
			}), false);
			// get product
			String productID = d.getInputString("Enter the product id: ", true);
			// get quantity
			int quantity = d.getInputInteger("Enter the quantity: ");
			// get supplier name
			String supplierID = d.getInputString("Enter the supplier id: ");
			// allocate vars
			double supplierPrice = 0.0;
			double retailPrice = 0.0;
			//  > if new: get registration data
			int index = Inventory.instance().isNewProduct(productID);
			if(index == -1){
				// new product
				// get purchase price
				supplierPrice = d.getInputDouble("Enter the purchase price: $");
				// get retail price				
				retailPrice = d.getInputDouble("Enter the retail price: $");
			}else{
				ProductList pl = Inventory.instance().warehouseInventory.get(index);
				supplierPrice = pl.productInventory.get(0).getsupplierPrice();
				retailPrice = pl.getRetailPrice();
			}
			// verify information
			d.setHeader("Verify Product Info");
			d.displayLargeMessage(Arrays.asList(new String[]{
				"productID = " + productID,
				"supplierID = " + supplierID,
				"supplierPrice = $" + supplierPrice,
				"retailPrice = $" + retailPrice,
				"quantity = " + quantity
			}), false);
			if(!d.verify("Is this information correct?")) continue;
			Inventory.instance().addProduct(productID, supplierID, supplierPrice, retailPrice, quantity);
			
			if(shipment.containsKey(productID)){
				int prev = shipment.get(productID);
				shipment.put(productID, prev+quantity);
			}else{
				shipment.put(productID, quantity);
			}		

			uniqueProducts = shipment.keySet().size();
			grossProduct += quantity;

			d.displayMessage("Product '" + productID + "' added", false);
			if(!d.verify("Would you like to add more products?")) running = false;
		}
		// display a review page
		d.setHeader("Shipment Review");
		d.displayMessage("Please review the following shipment data", true);

		String[] lines = new String[uniqueProducts];
		int i = 0;
		for(String s : shipment.keySet()){
			int amnt = shipment.get(s);
			lines[i] = s+"\tX " + amnt;
			i++;
		}
		d.displayLargeMessage(Arrays.asList(lines), true);
		
	}

}