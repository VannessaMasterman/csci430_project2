package display;

/**
 * DisplayManager handles display requests
 * Acts as a facade for the display functions to ease the expected transition to using a GUI for interactions
 * 
 * Implemented as a singleton because we shouldn't have more than one instance of this class
 * @author Vannessa
 */
public abstract class DisplayManager {

    //
    // = = = Singleton Stuff END = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
    //

    protected String header = "";

    /**
     * Assigns the provided header to be the header shared for the display
     * For a console based view, this is printed ahead of the prompts and messages
     * @param header the new header
     */
    public void setHeader(String header){
        this.header = header;
    }

    /**
     * Displays the message to the user and awaits an input before continuing the program
     * @param message the message to display to the user
     */
    public abstract void displayMessage(String message, boolean holdThread);

    /**
     * Displays a message that spans many lines
     * @param lines the individual lines to display
     */
    public abstract void displayLargeMessage(Iterable<String> lines, boolean holdThread);

    /**
     * Displays the available options to the user and returns the option the user selected as an index
     * This method should be 
     * @param prompt the text prompt to introduce and request the option selection.
     * @param options a string array of the options to select from
     * @return the index of the selected option
     */
    public abstract int selectFromOptions(String prompt, String[] options);

    /**
     * Prompts the user for a string input given the specific prompt and limiting to a single token if singleToken==true.
     * @param prompt the text prompt to introduce and request the input. Should specify the kind of input needed
     * @param singleToken true if the input should be limited to a single token (space delimited). False to allow for multiple tokens i.e. a full sentence
     * @return the input String
     */
    public abstract String getInputString(String prompt, boolean singleToken);
    
    /**
     * Gets a single string token as an input
     * @see getInputString(String, boolean)
     */
    public String getInputString(String prompt){
        return getInputString(prompt, false);
    }


    public double getInputDouble(String prompt){
        double result = 0.0;
        boolean isValid = false;
        do {
            String s_var = getInputString(prompt, true); // numbers have to be a single token
            try{
                result = Double.parseDouble(s_var);
                isValid = true;
            }catch(Exception e) {}
        }while(!isValid);

        return result;
    }

    public int getInputInteger(String prompt){
        int result = 0;
        boolean isValid = false;
        do {
            String s_var = getInputString(prompt, true); // numbers have to be a single token
            try{
                result = Integer.parseInt(s_var);
                isValid = true;
            }catch(Exception e) {}
        }while(!isValid);

        return result;
    }

    public abstract boolean verify(String prompt);

    /**
     * Displays the provided table of strings to the screen
     * @param cells a 2-dimensional (String[][]) in (y,x) format
     * @param width the number of columns (length of second level arrays)
     * @param height the number of rows (length of first level array)
     * @param holdThread should the method hold the thread or allow for more to follow?
     */
    public abstract void displayTable(String[][] cells, int width, int height, boolean holdThread);



    
}
