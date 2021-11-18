package fsm;

import display.DisplayConsole;
import display.DisplayGUI;
import display.DisplayManager;
import fsm.states.State;
import fsm.states.clerk.StateClerk;
import fsm.states.clerk.StateViewClerk;
import fsm.states.client.StateClient;
import fsm.states.client.StateViewClient;
import fsm.states.login.StateLogin;
import fsm.states.manager.StateManager;

public class FSMManager {

    /**
     * The display manager instance which will be used by processes that need to be displayed
     */
    public static DisplayManager display = new DisplayGUI();


    /**
     * Transition matrix for the FSM
    */
    private final int[][] transitions = {
        {1,2,3,0,0,0,0},
        {1,1,1,1,1,1,1},
        {2,2,2,5,2,2,2},
        {3,3,3,5,4,3,3},
        {4,4,4,4,4,4,3},
        {5,5,5,5,5,2,3}
    };

    /**
     * Array of states, indices bound to transition matrix
     */
    private State[] states = {
        new StateLogin(),       // 0
        new StateClient(),      // 1
        new StateClerk(),       // 2
        new StateManager(),     // 3
        new StateViewClerk(),   // 4
        new StateViewClient()   // 5
    };

    /**
     * Called to run the FSM
     */
    public void run(){

        display.displayTable(new String[][]{{"A", "B"}, {"C", "D"}, {"E", "F"}}, 2, 3, true);


        FSMEvent event = FSMEvent.NO_EVENT;
        doTransition(0); // setup start state
        while (event != FSMEvent.EXIT_PROGRAM){
            // loop while not receiving EXIT_PROGRAM event 
            State current = states[Context.get().currentState]; // get current state
            current.runState(); // run the state, any holds on the thread handled by the state
            event = current.getEvent(); // get resulting event from the state
            if (event != FSMEvent.NO_EVENT) handleEvent(event); // as long as there is a valid event, handle it
        }
        System.exit(0); // need this to close the frame as well if using a GUI
        // once out of loop, we can just return
    }

    private void handleEvent(FSMEvent event){
        if (event == FSMEvent.EXIT_PROGRAM) return; // let the run loop handle exit case 
        int index = event.ordinal(); // get index of enum (bound to transition indices)
        int currentState = Context.get().currentState; // store current state
        int nextState = currentState;
        if (event == FSMEvent.EXIT_VIEW){
            if (currentState > 3){ // only for states that are "View As..."
                nextState = Context.get().previousState; // transition to previous state
            }else{ // normal states
                nextState = 0; // return to login screen
            }
        }else{
            nextState = transitions[currentState][index]; // use transition matrix to get next state
        }

        if (nextState != currentState){
            // if the next state is not the current state
            // i.e. there is an actual transition needed
            doTransition(nextState);
        }

    }

    private void doTransition(int nextState){
        
        // assign previous state for memory
        Context.get().previousState = Context.get().currentState;
        // assign current state, which will be accessed in the run loop
        Context.get().currentState = nextState;
        State state = states[nextState];
        display.setHeader(state.getName());
        System.out.println("Performing transition to next state: [" + nextState + "] " + state.getName()); // DEBUG print
    }
}
