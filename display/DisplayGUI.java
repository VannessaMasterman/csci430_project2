package display;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
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
        JButton btnContinue = null;
        if(holdThread){
            btnContinue = new JButton(new ButtonActionGUI(this){

                @Override
                public void actionPerformed(ActionEvent e) {
                    display.unlockThread();                    
                }

            });
            btnContinue.setText("Continue");
            // at least on ubuntu this lets the spacebar perform the action, allowing the user to skip using their mouse if preferred
            btnContinue.setAlignmentX(Component.CENTER_ALIGNMENT);
            content.add(btnContinue);
        }

        int padding = 20;
        content.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));

        updateFrameDisplay(content);
        if (btnContinue != null) btnContinue.requestFocus(); // make this button auto selected


        keepPrevious = !holdThread;    
        
        if(holdThread) holdThreadLocked();
    }

    @Override
    public void displayLargeMessage(Iterable<String> lines, boolean holdThread) {
        System.err.println("displayLargeMessage not implemented");
        // TODO implement
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
            panel.add(btnSelect);
            panel.add(padding(0, 5));
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
        JPanel panel = createPanel(PANEL_VBOX);

        JLabel label = new JLabel(prompt);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        panel.add(padding(0, 10));
        ButtonActionGUI actionConfirm = new ButtonActionGUI(this) {

            @Override
            public void actionPerformed(ActionEvent e) {
                display.unlockThread();
            }
            
        };



        JTextField inputField = new JTextField(15);
        inputField.setAlignmentX(Component.LEFT_ALIGNMENT);
        inputField.addActionListener(actionConfirm); // this will let the user press enter from the text field to perform the same action

        panel.add(inputField);

        panel.add(padding(0, 5));

        JButton btnConfirm = new JButton(actionConfirm);
        btnConfirm.setText("Confirm");
        btnConfirm.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnConfirm);

        int padding = 15;
        panel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        updateFrameDisplay(panel);
        inputField.requestFocus();

        holdThreadLocked();

        String input = inputField.getText();
        if (singleToken){
            String[] tokens = input.split(" ");
            input = tokens[0];
        }
        System.out.println("Got input \"" + input + "\"");
        keepPrevious = false;
        return input;
    }

    @Override
    public boolean verify(String prompt) {     
        JPanel panel = createPanel(PANEL_VBOX);

        JLabel label = new JLabel(prompt);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label);
        
        JPanel buttonsPanel = createPanel(PANEL_HBOX);
        
        ButtonActionGUI actionYes = new ButtonActionGUI(this) {

            @Override
            public void actionPerformed(ActionEvent e) {
                display.swapDataObject = true;
                display.unlockThread();
            }
            
        };
        
        ButtonActionGUI actionNo = new ButtonActionGUI(this) {

            @Override
            public void actionPerformed(ActionEvent e) {
                display.swapDataObject = false;
                display.unlockThread();               
            }
            
        };
        
        JButton btnYes = new JButton(actionYes);
        btnYes.setText("Yes");
        buttonsPanel.add(btnYes);

        JButton btnNo = new JButton(actionNo);
        btnNo.setText("No");
        buttonsPanel.add(btnNo);
        
        int padding = 10;
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(padding,padding, padding, padding));
        panel.setBorder(BorderFactory.createEmptyBorder(padding,padding, padding, padding));
        panel.add(buttonsPanel);
        updateFrameDisplay(panel);
        btnYes.requestFocus();

        holdThreadLocked();

        boolean result = (boolean) swapDataObject;
        System.out.println("Verified : " + result);
        return result;
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

    private Component padding(int width, int height){
        return Box.createRigidArea(new DimensionUIResource(width, height));
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
