package display;

/**
 * An empty display class that does not perform any function besides a placeholder.
 * 
 */
public class DisplayEmpty extends DisplayManager {

    @Override
    public void displayMessage(String message, boolean holdThread) {
    }

    @Override
    public void displayLargeMessage(Iterable<String> lines, boolean holdThread) {
    }

    @Override
    public int selectFromOptions(String prompt, String[] options) {
        return 0;
    }

    @Override
    public String getInputString(String prompt, boolean singleToken) {
        return null;
    }

    @Override
    public boolean verify(String prompt) {
        return false;
    }

    @Override
    public void displayTable(String[][] cells, int width, int height, boolean holdThread) {
    }
    
}
