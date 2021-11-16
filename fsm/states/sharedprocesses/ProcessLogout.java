package fsm.states.sharedprocesses;

import fsm.FSMEvent;

public class ProcessLogout extends ProcessCreateEvent {

    public ProcessLogout() {
        super(FSMEvent.EXIT_VIEW, "Logout", "", "logs out of the user");
    }

}
