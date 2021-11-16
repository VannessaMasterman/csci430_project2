package nessa.process;

import fsm.FSMEvent;

//import java.io.*;

/**
	The parent class for all 'UIProcess's. Which are any business process we want to expose to the user. Each process has a category, name, and description which the menu can use to display proper information.
*/
public abstract class UIProcess {
	
	// all fields are final, no need for these to change over the life cycle
	protected final String category;
	protected final String name;
	protected final String description;

	public UIProcess(String category, String name, String description){
		// setup final variables
		this.category = category;
		this.name = name;
		this.description = description;
	}

	// Getters for the protected fields
	public String getCategory(){ return category; }
	public String getName(){ return name; }
	public String getDescription(){ return description; }
	
	// - - - - - - - - - - - - - - - - - - - - - -
	//	Process Specific Implementations
	// - - - - - - - - - - - - - - - - - - - - - -
	
	/**
		Process is the function called when the process is selected from the menu. It is called on the single thread this program runs on so if something blocks this thread is blocks the entire program. This is intended as a process may need to call for additional information or pause to display some information.
	*/
	public abstract void process();

	/**
	 * @return the event this process produced, often NO_EVENT
	 */
	public FSMEvent getEvent(){
		return FSMEvent.NO_EVENT;
	}

	@Override
	public String toString(){
		return String.join("", new String[]{"[", getCategory(), "] ", getName(), ": ", getDescription()});
	}
	
}