package display.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class ButtonActionContinue extends AbstractAction {

    private boolean shouldContinue = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action performed!"); // TODO
        shouldContinue = true;
    }

    public boolean getShouldContinue(){
        return shouldContinue;
    }


    
}
