package display;

public class DisplayConsole extends DisplayManager {

    @Override
    public void displayMessage(String message, boolean holdThread) {
        header();
        println(message);
        if (holdThread) awaitInput();
    }

    @Override
    public void displayLargeMessage(Iterable<String> lines, boolean holdThread) {
        header();
        for (String s : lines)
            println(s);
        if(holdThread) awaitInput();
    }

    @Override
    public int selectFromOptions(String prompt, String[] options) {
        header();
        println(prompt);
        println("");
        for (int i = 0; i < options.length; i++){
            println(String.join("", new String[]{ ""+i, "\t", options[i]}));
        }
        String input = "";
        int index = -1;
        do {
            input = getInputString("Please enter the integer index for your chosen option: ", true);
            try {
                index = Integer.parseInt(input);
            }catch(Exception e){
                // invalid integer input, let the loop
                println("You entered '"+input+"', please enter a valid integer : 0, 1, 2, etc...");
            }
            if (index >= options.length){
                println("The integer you entered was too large. Please enter an integer in range from 0 to " + (options.length-1));
            }
        } while(index < 0 || index >= options.length );
        // at this point we should have a valid index for the list of options
        return index;
    }

    @Override
    public String getInputString(String prompt, boolean singleToken) {
        print(prompt);
        String line = readLine();
        if (singleToken){
            String[] tokens = line.split(" ");
            if( tokens.length > 0){
                String t = tokens[0]; // get first token, discard all else 
                //println("Reading token '"+t+"' ");
                return t;
            }else{
                // if failed to read a token, loop
                println("failed to read a token...");
                return getInputString(prompt, singleToken);
            }
        }
        // singleToken == false, get entire line
        return line;
    }

    private void header(){
        clearScreen();
        println("================");
        println("\t" + header);
        println("================");
        
    }

    private void clearScreen(){
        System.out.print("\033[H\033[2J");
		System.out.flush();
    }


    private String readLine(){
        return System.console().readLine();
    }

    private void awaitInput(){
        getInputString("Press enter to continue", false);
    }

    private void println(String msg){
        System.out.println(msg);
    }
    private void print(String msg){
        System.out.print(msg);
    }

    @Override
    public boolean verify(String prompt) {
        String token = getInputString(prompt + "(Y/N) ", true);
        return token.trim().toLowerCase().contains("y");
    }

   
    
}
