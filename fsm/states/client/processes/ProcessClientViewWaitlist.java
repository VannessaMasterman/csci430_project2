package fsm.states.client.processes;

import display.DisplayManager;
import fsm.FSMManager;
import nessa.process.UIProcess;

public class ProcessClientViewWaitlist extends UIProcess {

    public ProcessClientViewWaitlist() {
        super("TBD", "View Waitlist", "view your waitlisted items");
    }

    @Override
    public void process() {
        DisplayManager d = FSMManager.display;
        d.displayMessage("Unfortunately this function is not currently implemented.", true);
        
    }
    
}
