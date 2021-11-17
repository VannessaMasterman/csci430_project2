package display.gui;


import javax.swing.AbstractAction;

import display.DisplayGUI;

public abstract class ButtonActionGUI extends AbstractAction {


    public final DisplayGUI display;

    public ButtonActionGUI(DisplayGUI display){
        this.display = display;
    }
    
}
