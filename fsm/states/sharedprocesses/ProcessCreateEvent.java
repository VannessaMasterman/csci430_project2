package fsm.states.sharedprocesses;

import fsm.FSMEvent;
import nessa.process.UIProcess;

public class ProcessCreateEvent extends UIProcess {

    private FSMEvent viewEvent;

    public ProcessCreateEvent(FSMEvent viewEvent, String category, String name, String description) {
        super(category, name, description);
        this.viewEvent = viewEvent;
    }
    @Override
    public void process() {
    }

    public FSMEvent getEvent(){
		return viewEvent;
	}



    
}
