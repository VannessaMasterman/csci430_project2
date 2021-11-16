package nessa.process.Admin;

import fsm.Context;
import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;

/**
	This class performs the "View Transactions - Admin" business process
*/
public class PViewTransactionsAdmin extends UIProcess {
	
	public PViewTransactionsAdmin(String category, String name, String description){
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
		int clientID = Context.get().clientID;
		if(clientID != -1) return;
		System.out.println("This process is not yet implemented. Sorry for the inconvenience.");
		System.out.println("Press enter to continue");
		ConsoleUtil.readLine();
	}
}