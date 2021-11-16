package display.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import display.DisplayGUI;

public abstract class ButtonActionGUI extends AbstractAction {


    public final DisplayGUI display;

    public ButtonActionGUI(DisplayGUI display){
        this.display = display;
    }
    
}
