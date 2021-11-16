package nessa.process.Client;

import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;
import warehouseInventory.warehouse.Inventory;

/**
	This class performs the "View List of Suppliers" business process
*/
public class PViewSupplier extends UIProcess {
	
	public PViewSupplier(String category, String name, String description){
		super(category, name, description);	
	}
	
	/**
		This function is the common inherited function for all of the process classes
		This function is called when the user selects it from the menu
		@param clientID the ID for the client logged in, else -1
	*/
	@Override
	public void process(){
		
		System.out.println("Enter Product ID to see available suppliers : ");
		String productID = ConsoleUtil.readLine();

		Inventory.instance().printSupplierList(productID);

		ConsoleUtil.sleepForSeconds(1.5f);
	}
}