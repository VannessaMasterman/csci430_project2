package nessa;

import fsm.Context;
import fsm.FSMManager;

/**
 * 
 * @author Vannessa
 */
public class UserInterface {
	
	public static void main(String[] args){
		for (String s : args){
			if (s.startsWith("-")){
				// treat as an argument
				String key = s.substring(s.lastIndexOf("-"));
				System.out.println("Handling argument key : \"" + key + "\"");
				if (key.toLowerCase().equals("headless")){
					Context.get().headless = true;
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