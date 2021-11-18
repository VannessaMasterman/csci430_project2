package nessa.process.Admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import display.DisplayManager;
import fsm.FSMManager;
import nessa.process.UIProcess;
import warehouseInventory.warehouse.Inventory;
import warehouseInventory.warehouse.Product;
import warehouseInventory.warehouse.ProductList;

/**
	This class performs the "Query Inventory" business process
*/
public class PQueryInventory extends UIProcess {
	
	public PQueryInventory(String category, String name, String description){
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
		d.setHeader("Inventory Query");
		
		List<ProductListEntry> inventory = new ArrayList<ProductListEntry>();
		Iterator<ProductList> itr = Inventory.instance().getInventoryIterator();
		while(itr.hasNext()){
			// process entire inventory
			ProductList pl = itr.next();

			int numSuppliers = pl.getNumberofSuppliers();
			String productID = pl.getProductID();
			int quantity = pl.getQuantity();
			int waitlisted = pl.getWaitlistQuantity();
			double retailPrice = pl.getRetailPrice();

			List<String[]> supplierLines = new ArrayList<String[]>();
			Iterator<Product> supItr = pl.productInventory.iterator();
			boolean firstSupplier = true;
			while(supItr.hasNext()){
				Product product = supItr.next();
				String supplierID = product.getsupplierID();
				double supplierPrice = product.getsupplierPrice();
				int supplierQuantity = product.getsupplierQuantity();
				String[] supplierLine = new String[]{(firstSupplier? productID : "-"),
					 supplierID,"$" + supplierPrice, Integer.toString(supplierQuantity)};
				supplierLines.add(supplierLine);
				if(firstSupplier) firstSupplier = false;
			}
			// load entry

			ProductListEntry ple = new ProductListEntry();
			ple.productID = productID;
			ple.numSuppliers = Integer.toString(numSuppliers);
			ple.quantity = Integer.toString(quantity);
			ple.waitlisted = Integer.toString(waitlisted);
			ple.retailPrice = "$" + retailPrice;
	
			ple.supplierRows = supplierLines;
			inventory.add(ple);
		}
		if (inventory.size() <= 0){
			d.displayMessage("No products in inventory.", true);
		}else{
			// display table of individual products
			int totalSupplierRows = 0;
			String[][] cells = new String[inventory.size()+1][5];
			cells[0] = new String[]{"productID", "numSuppliers", "quantity", "waitlisted", "retailPrice"};
			for (int i = 0; i < inventory.size(); i++){
				ProductListEntry ple = inventory.get(i);
				cells[i+1] = ple.getRowOfData();
				totalSupplierRows += ple.supplierRows.size();
			}
			d.displayTable(cells, 5, cells.length, true);
			// display table of supplier data
			cells = new String[totalSupplierRows + 1][4];
			cells[0] = new String[]{
				"Product","supplierID","supplierPrice", "supplierQuantity"};
			for (int i = 0; i < inventory.size(); i++){
				ProductListEntry ple = inventory.get(i);
				List<String[]> rows = ple.supplierRows;
				for(int j = 0; j < rows.size(); j++){
					String[] row = rows.get(j);
					cells[i+1+j] = row;
				}
			}
			d.displayTable(cells, 4, cells.length, true);

		}
	}

	/**
	 * Temp record for holding data of a single product listing entry
	 */
	private class ProductListEntry {
		public String productID = "";
		public String numSuppliers = "";
		public String quantity = "";
		public String waitlisted = "";
		public String retailPrice = "";

		public List<String[]> supplierRows = new ArrayList<String[]>();

		public String[] getRowOfData(){
			return new String[]{productID, numSuppliers, quantity, waitlisted, retailPrice};
		}
	} 
}