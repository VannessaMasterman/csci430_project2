package nessa.process.Client;

import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;
/**
	This class performs the "Process Cart" business process
*/
public class PProcessCart extends UIProcess {
	
	public PProcessCart(String category, String name, String description){
		super(category, name, description);	
	}
	
	/**
		This function is the common inherited function for all of the process classes
		This function is called when the user selects it from the menu
		@param clientID the ID for the client logged in, else -1
	*/
	@Override
	public void process(){
		System.out.println("This process is not yet implemented. Sorry for the inconvenience.");
		System.out.println("Press enter to continue");
		ConsoleUtil.readLine();
	}
}