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

			String headerString = String.join("", new String[]{
				productID, " || " + numSuppliers, " | " + quantity, " | " + waitlisted, " | " + retailPrice
			});

			List<String> supplierLines = new ArrayList<String>();
			supplierLines.add("\tSupplierID \tSupplier Price \tSupplier Quantity");
			Iterator<Product> supItr = pl.productInventory.iterator();
			while(supItr.hasNext()){
				Product product = supItr.next();
				String supplierID = product.getsupplierID();
				double supplierPrice = product.getsupplierPrice();
				int supplierQuantity = product.getsupplierQuantity();
				String supplierLine = String.join("", new String[]{
					"\t|| ", supplierID, "| $" + supplierPrice, " | " + supplierQuantity, "qty |"
				});
				supplierLines.add(supplierLine);
			}
			// load entry
			inventory.add(new ProductListEntry(headerString, supplierLines));
		}
		List<String> allLines = new ArrayList<String>();

		allLines.add("ProductID \tNo. Suppliers \tTotal Quantity \tWaitlist \tRetailPrice");
		for(ProductListEntry ple : inventory){
			allLines.add(ple.headerLine);
			allLines.addAll(ple.supplierLines);
		}
		if (allLines.size() == 1){
			// no products in inventory
			allLines.add("\t|| No Products In Inventory ||");
		}

		d.displayLargeMessage(allLines, true);

		// Product ID || # Suppliers | Total Quantity | Waitlisted | Retail Price |
		//		|| Supplier ID | Supplier Price | Supplier Quantity |

	}

	/**
	 * Temp record for holding data of a single product listing entry
	 */
	private class ProductListEntry {
		public final String headerLine;
		public final List<String> supplierLines;

		public ProductListEntry(String header, List<String> lines){
			headerLine = header;
			supplierLines = lines;
		}
	} 
}