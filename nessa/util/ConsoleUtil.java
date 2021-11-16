package nessa.util;

//import java.lang.*;

/**
	This is a utility class that only has static methods for use with the console. 
	It is external from the UserInterface class so that individual processes can utilize these helper functions without needless entangelement.
*/
public class ConsoleUtil {
	
	/**
		Reads a line of input from the console as a string.
		@returns a string of the full line of input from the console user.
	*/
	public static String readLine(){
		return System.console().readLine();
	}
	
	/**
		Clears the console
	*/
	public static void clearConsole(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	/**
		A helper for calling the Thread.sleep function using a floating point in seconds
		@param the amount of time in seconds for the main thread to sleep.
	*/
	public static void sleepForSeconds(float seconds){
		int milli = (int)(seconds * 1000f);
		sleepForMilli(milli);
	}
	
	/**
		A helper function for the Thread.sleep function that handles exception should one be thrown.
	*/
	public static void sleepForMilli(int milliseconds){
		try{
			Thread.sleep(milliseconds);
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
			System.err.println(e);
		}		
	}
	
}