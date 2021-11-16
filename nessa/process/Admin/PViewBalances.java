package nessa.process.Admin;

import display.DisplayManager;
import fsm.FSMManager;
import nessa.process.UIProcess;

/**
	This class performs the "View Balances" business process
*/
public class PViewBalances extends UIProcess {
	
	public PViewBalances(String category, String name, String description){
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
		d.setHeader("Unimplemented");
		d.displayMessage("Unfortunately this process is not currently implemented. We apologize for any inconvenience", true);
	}
}