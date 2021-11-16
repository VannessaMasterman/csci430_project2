package fsm.states.login;

import fsm.FSMEvent;
import fsm.states.State;
import fsm.states.login.processes.ProcessExitProgram;
import fsm.states.login.processes.ProcessLoginGeneral;

public class StateLogin extends State {

    public StateLogin() {
        super("Login");
    }

    @Override
    public void addProcesses() {
        processes.add(new ProcessExitProgram());
        processes.add(new ProcessLoginGeneral("Client", "login for clients", true, FSMEvent.LOGIN_CLIENT));
        processes.add(new ProcessLoginGeneral("Clerk", "login for clerks", false, FSMEvent.LOGIN_CLERK));
        processes.add(new ProcessLoginGeneral("Manager", "login for managers", false, FSMEvent.LOGIN_MANAGER));
    }
}
