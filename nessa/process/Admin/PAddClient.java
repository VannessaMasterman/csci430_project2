package nessa.process.Admin;

import java.util.Arrays;

import display.DisplayManager;
import ethanlo.ClientCollection;
import fsm.FSMManager;
import nessa.process.UIProcess;

/**
	This class performs the "Add Client" business process
*/
public class PAddClient extends UIProcess {
	
	public PAddClient(String category, String name, String description){
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
		d.setHeader("Adding Client");
		d.displayMessage("Please enter the information as correctly as possible", false);
		String name = d.getInputString("Client Name : ", false);		
		String phone = d.getInputString("Client Phone Number : ", false);
		String address = d.getInputString("Client Address : ", false);

		d.displayLargeMessage(Arrays.asList(new String[]{
			"name = " + name,
			"phone # = " + phone,
			"address = " + address
		}), false);

 		if(!d.verify("Verify the above information is correct")){
			process();
			return;
		}

		ClientCollection.instance().addClient(name, phone, address);
		d.displayMessage("Client '"+name+"' has been added", false);
		if(d.verify("Would you like to add another client?")){
			// recurse to add more clients
			process();
		}
	}
}