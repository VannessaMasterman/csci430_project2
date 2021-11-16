package nessa.process.Admin;

import java.util.Arrays;

import display.DisplayManager;
import ethanlo.SupplierCollection;
import fsm.FSMManager;
import nessa.process.UIProcess;

/**
	This class performs the "Add Supplier" business process
*/
public class PAddSupplier extends UIProcess {
	
	public PAddSupplier(String category, String name, String description){
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
		d.setHeader("Adding Supplier");
		d.displayMessage("You will need to provide specific information related to the product you are adding. Please have this information ready", false);

		// supplier name
		String name = d.getInputString("Enter supplier name: ", true);
		// supplier phone number
		String phoneNumber = d.getInputString("Enter supplier phone number: ", true);
		// supplier address
		String address = d.getInputString("Enter supplier address: ", true);
		// verify info
		d.displayLargeMessage(Arrays.asList(new String[]{
			"supplierName = " + name ,
			"supplierPhoneNumber = " + phoneNumber,
			"supplierAddress = " + address
		}), false);
		if (! d.verify("Is the information correct?")){
			this.process();
			return;
		}
		// register supplier
		SupplierCollection.instance().addSupplier(name, phoneNumber, address);

	}
}