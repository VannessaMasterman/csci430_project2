package nessa;

import fsm.FSMManager;

/**
 * 
 * @author Vannessa
 */
public class UserInterface {
	
	public static void main(String[] args){
		UserInterface ui = new UserInterface();
		ui.start();
	}

	public void start(){
		FSMManager fsm = new FSMManager();
		fsm.run(); // holds thread until program exit
	}
}