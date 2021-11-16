package nessa.process;

//import nessa.process.UIProcess; turns out import is unecessary
import nessa.util.ConsoleUtil;

/**
	This is merely a placeholder UIProcess so that menus can be padded out for visual testing
*/
public class DummyProcess extends UIProcess {
	
	/**
		The constructor allows for all fields to be defined at point of creation.
	*/
	public DummyProcess(String category, String name, String description){
		super(category, name, description);	
	}
	
	/**
		The process function for this Dummy Process prints out a string of text to to console and pauses for a brief second before releasing back to the menu.
		Again, this is a placeholder so that the functionality can be tested without needing to call any other module functions
	*/
	@Override
	public void process(){
		System.out.println("Performing dummy process " + category + ":" + name.replace(" ", "_"));
		ConsoleUtil.sleepForSeconds(1.5f);
	}
}