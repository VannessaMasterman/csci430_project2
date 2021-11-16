package nessa.process.Admin;

import java.util.Arrays;
import java.util.List;

import display.DisplayManager;
import ethanlo.Supplier;
import ethanlo.SupplierCollection;
import fsm.FSMManager;
import nessa.process.UIProcess;

/**
	This is merely a placeholder UIProcess so that menus can be padded out for visual testing
*/
public class PViewSupplierList extends UIProcess {
	
	/**
		The constructor allows for all fields to be defined at point of creation.
	*/
	public PViewSupplierList(String category, String name, String description){
		super(category, name, description);	
	}
	
	/**
		The process function for this Dummy Process prints out a string of text to to console and pauses for a brief second before releasing back to the menu.
		Again, this is a placeholder so that the functionality can be tested without needing to call any other module functions
	*/
	@Override
	public void process(){
		DisplayManager d = FSMManager.display;
		d.setHeader("Adding Supplier");
		// print out client list
		List<Supplier> list = SupplierCollection.instance().supplierList;
		String[] suppliers = new String[list.size()];
		for(int i = 0; i < list.size(); i++){
			suppliers[i] = list.get(i).toString();
		}
		d.displayLargeMessage(Arrays.asList(suppliers), true);
	}
}