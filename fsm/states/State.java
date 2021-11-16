package fsm.states;

import java.util.ArrayList;
import java.util.List;

import fsm.FSMEvent;
import fsm.FSMManager;
import nessa.process.UIProcess;

/**
 * The abstract State class for the FSM
 * 
 * @author Vannessa
 */
public abstract class State {
    /**
     * The list of processes accessible to the state
     */
    protected List<UIProcess> processes = new ArrayList<UIProcess>();

    protected final String StateName;

    protected FSMEvent event = FSMEvent.NO_EVENT;

    public State(String name){
        StateName = name;
        addProcesses();
    }

    public abstract void addProcesses();

    /**
     * Called when running the current state
     */
    public void runState(){
        FSMManager.display.setHeader(getName());
        event = FSMEvent.NO_EVENT;
        String[] options = getProcessesAsStringArray();
        if (options.length <= 0) return;
        int sel = FSMManager.display.selectFromOptions("Options:",options);
        runProcess(sel);
    }

    /**
     * Runs a process at the given index and returns the instance of that UIProcess after it has completed running
     * @param index the index of the process to run
     * @return the process that was run, in case child classes need to get data from that process instance
     */
    protected UIProcess runProcess(int index){
        UIProcess proc = processes.get(index);
        proc.process();
        FSMEvent e = proc.getEvent();
        if (e != FSMEvent.NO_EVENT) this.event = e; // if the process produced an event, pass that event upstream
        return proc;
    }

    /**
     * @return an array of strings representing the UIProcess list for display uses
     */
    private String[] getProcessesAsStringArray(){
        String[] list = new String[processes.size()];
        for (int i = 0; i < processes.size(); i++){
            list[i] = processes.get(i).toString();
        }
        return list;
    }

    /**
     * @return the current event for the state
     */
    public FSMEvent getEvent(){
        return event;
    }

    /**
     * @return the name of this state
     */
    public String getName(){
        return StateName;
    }



}
