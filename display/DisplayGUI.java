package display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import display.gui.ButtonActionContinue;

/**
 * @author Vannesa
 * 
 * A class that extends from DisplayManager that displays the requested items in a graphical user interface
 * @see DisplayManager
 */
public class DisplayGUI extends DisplayManager {

    private final JFrame frame; // keep this frame throughout, change the content
    public DisplayGUI(){
        frame = new JFrame("DisplayHeader");
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        updateFrameDisplay();
    }


    @Override
    public void displayMessage(String message, boolean holdThread) {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));

        JLabel label = new JLabel(message);
        content.add(label);
        // TODO do I need to implement anything for hold thread? The frame does that too???
        
        ButtonActionContinue actionContinue = new ButtonActionContinue();
        if(holdThread){
            JButton btnContinue = new JButton(actionContinue);
            btnContinue.setText("Continue");

            content.add(btnContinue);
        }

        frame.getContentPane().add(content);
        updateFrameDisplay();          
        
        if(holdThread){
            while (!actionContinue.getShouldContinue()); // hold thread until action is completed
            System.out.println("Continue has been pressed!");
        }
    }

    @Override
    public void displayLargeMessage(Iterable<String> lines, boolean holdThread) {
         // TODO implement
    }

    @Override
    public int selectFromOptions(String prompt, String[] options) {
        System.out.println("We made it to the next step!"); // TODO implement
        return 0;
    }

    @Override
    public String getInputString(String prompt, boolean singleToken) {
         // TODO implement
        return null;
    }

    @Override
    public boolean verify(String prompt) {
         // TODO implement
        return false;
    }

    private void updateFrameDisplay(){
        frame.pack(); // set size to be appropriate for contents
        frame.setLocationRelativeTo(null); // center on screen
        frame.setVisible(true); // make visible (in case it was invisible before)
    }
    
}
