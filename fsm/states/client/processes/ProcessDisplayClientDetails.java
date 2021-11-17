package fsm.states.client.processes;

import java.util.ArrayList;
import java.util.Iterator;

import display.DisplayManager;
import ethanlo.Client;
import ethanlo.ClientCollection;
import fsm.Context;
import fsm.FSMManager;
import nessa.process.UIProcess;

public class ProcessDisplayClientDetails extends UIProcess {

    public ProcessDisplayClientDetails() {
        super("TBD", "Display Client Details", "view your personal details");
    }

    @Override
    public void process() {
        DisplayManager d = FSMManager.display;
        // The process of getting a single client should probably be handled by the actual ClientCollection
        Iterator<Client> itr = ClientCollection.instance().getIterator(); 
        Client self = null;
        while(itr.hasNext()){
            self = itr.next();
            // if we have the current client break
            if(self.getId() == Context.get().clientID) break;
        }
        d.setHeader("Client Details");
        ArrayList<String> lines = new ArrayList<String>();
        if (self == null){
            lines.add("Logged in as an invalid client");
            lines.add("No client data found for id \"" + String.valueOf(Context.get().clientID) + "\"");
        }else{
            lines.add("\tName:\t\t" + self.getName());
            lines.add("\tPhone:\t\t" + self.getPhone());
            lines.add("\tAddress:\t" + self.getAddress());
            lines.add("\tID:\t\t" + self.getId());
        }
        d.displayLargeMessage(lines, true);
    }
    
}
