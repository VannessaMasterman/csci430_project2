package nessa;

import fsm.Context;
import fsm.FSMManager;
import fsm.states.clerk.ProcessLoadTestingInventory;

/**
 * 
 * @author Vannessa
 */
public class UserInterface {
	
	public static void main(String[] args){
		for (String s : args){
			if (s.startsWith("-")){
				// treat as an argument
				String key = s.substring(s.lastIndexOf("-")+1).toLowerCase();
				System.out.println("Handling argument key : \"" + key + "\"");
				if (key.equals("headless")){
					Context.get().headless = true;
				}
				if (key.equals("rng")){
					ProcessLoadTestingInventory plti = new ProcessLoadTestingInventory();
					plti.processHeadless(50, -1); // generate 50 items completely randomly
				}
			}
		}
		UserInterface ui = new UserInterface();
		ui.start();
	}

	public void start(){
		FSMManager fsm = new FSMManager();
		fsm.run(); // holds thread until program exit
	}
}