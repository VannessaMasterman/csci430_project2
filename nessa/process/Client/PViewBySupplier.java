package nessa.process.Client;

import display.DisplayManager;
import fsm.FSMManager;
import nessa.process.UIProcess;

/**
	This class performs the "View Products by Supplier" business process
*/
public class PViewBySupplier extends UIProcess {
	
	public PViewBySupplier(String category, String name, String description){
		super(category, name, description);	
	}
	
	/**
		This function is the common inherited function for all of the process classes
		This function is called when the user selects it from the menu
		@param clientID the ID for the client logged in, else -1
	*/
	@Override
	public void process(){
		DisplayManager d = FSMManager.display;
		d.setHeader("View Products By Supplier");
		d.displayMessage("This process is not yet implemented. Sorry for the inconvenience.", true);
	}
}