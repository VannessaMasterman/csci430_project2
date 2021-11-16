package fsm.states.login.processes;

import fsm.Context;
import fsm.FSMEvent;
import fsm.FSMManager;
import nessa.process.UIProcess;

public class ProcessLoginGeneral extends UIProcess {

    protected boolean requiresID = false;
    protected FSMEvent loginEvent;

    public ProcessLoginGeneral(String name, String desc, boolean requiresID, FSMEvent loginEvent) {
        super("Login", name, desc);
        this.requiresID = requiresID;
        this.loginEvent = loginEvent;
    }

    @Override
    public void process() {
        if (requiresID){
            String loginData = FSMManager.display.getInputString("Please input your login: ", true);
            Context.get().clientID = Integer.parseInt(loginData);
        }
    }

	public FSMEvent getEvent(){
		return loginEvent;
	}


}
