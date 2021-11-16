package fsm.states.login.processes;

import fsm.FSMEvent;
import nessa.process.UIProcess;

public class ProcessExitProgram extends UIProcess {

    public ProcessExitProgram() {
        super("Exit", "", "exits the program");
    }

    @Override
    public void process() {
    }

    public FSMEvent getEvent(){
		return FSMEvent.EXIT_PROGRAM;
	}
    
}
