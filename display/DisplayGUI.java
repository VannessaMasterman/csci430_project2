package display;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import display.gui.ButtonActionGUI;

/**
 * @author Vannesa
 * 
 * A class that extends from DisplayManager that displays the requested items in a graphical user interface
 * @see DisplayManager
 */
public class DisplayGUI extends DisplayManager {

    private static final int PANEL_VBOX = BoxLayout.PAGE_AXIS; // option for a vertically stacked box layout
    private static final int PANEL_HBOX = BoxLayout.LINE_AXIS; // option for a horizontally stacked box layout


    private boolean threadLock = false; // simple latch for thread locking
    protected Object swapDataObject = null;

    private boolean keepPrevious = false;

    private final JFrame frame; // keep this frame throughout, change the content
    private final JPanel frameContent;

    public DisplayGUI(){
        frame = new JFrame("DisplayHeader");
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frameContent = createPanel(PANEL_VBOX);
        frame.getContentPane().add(frameContent);
        updateFrameDisplay(null);
    }

    public void setHeader(String header){
        this.header = header;
        frame.setTitle(header);
    }



    @Override
    public void displayMessage(String message, boolean holdThread) {
        JPanel content = createPanel(PANEL_VBOX);

        JLabel label = new JLabel(message);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(label);
        // TODO do I need to implement anything for hold thread? The frame does that too???

        content.add(Box.createRigidArea(new DimensionUIResource(0, 20)));
        
        if(holdThread){
            JButton btnContinue = new JButton(new ButtonActionGUI(this){

                @Override
                public void actionPerformed(ActionEvent e) {
                    display.unlockThread();                    
                }

            });
            btnContinue.setText("Continue");
            btnContinue.requestFocus(); // make this button auto selected
            // at least on ubuntu this lets the spacebar perform the action, allowing the user to skip using their mouse if preferred
            btnContinue.setAlignmentX(Component.CENTER_ALIGNMENT);
            content.add(btnContinue);
        }

        int padding = 20;
        content.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        updateFrameDisplay(content);
        keepPrevious = !holdThread;    
        
        if(holdThread) holdThreadLocked();
    }

    @Override
    public void displayLargeMessage(Iterable<String> lines, boolean holdThread) {
        // TODO implement
        System.err.println("displayLargeMessage not implemented");
    }

    @Override
    public int selectFromOptions(String prompt, String[] options) {
        JPanel panel = createPanel(PANEL_VBOX);
        
        // prompt
        JLabel label = new JLabel(prompt);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        for (int i = 0; i < options.length; i++){
            String option = options[i];

            final int index = i; // make a final int to propogate the index
            ButtonActionGUI actionSelect = new ButtonActionGUI(this) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    display.swapDataObject = index;
                    display.unlockThread();
                }  
            };
            JButton btnSelect = new JButton(actionSelect);
            btnSelect.setAlignmentX(Component.LEFT_ALIGNMENT);
            btnSelect.setText(option);
            if (i == 0) btnSelect.requestFocus();
            panel.add(btnSelect);
        }

        int padding = 20;
        panel.setBorder(BorderFactory.createEmptyBorder(padding,padding,padding,padding));
        
        
        updateFrameDisplay(panel);
        threadLock = true;
        holdThreadLocked();
        int index = (int)swapDataObject;
        return index;
    }

    @Override
    public String getInputString(String prompt, boolean singleToken) {
        System.err.println("getInputString not implemented");
         // TODO implement
        
        return null;
    }

    @Override
    public boolean verify(String prompt) {        
        System.err.println("verify not implemented");
         // TODO implement
        return false;
    }

    private void updateFrameDisplay(Component newComp){
        if (!keepPrevious) frameContent.removeAll();
        if(newComp != null) frameContent.add(newComp);
        frame.pack(); // set size to be appropriate for contents
        frame.setLocationRelativeTo(null); // center on screen
        frame.setVisible(true); // make visible (in case it was invisible before)
    }


    private JPanel createPanel(int axis){
        JPanel content = new JPanel();
        BoxLayout layout = new BoxLayout(content, axis);
        content.setLayout(layout);
        return content;
    }

    private void holdThreadLocked(){
        threadLock = true;
        while (threadLock){
            try {
                Thread.sleep(100); // thread has to sleep to make this work properly. idk why
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void unlockThread() {
        threadLock = false;
    }
    
}
