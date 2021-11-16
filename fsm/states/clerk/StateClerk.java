package fsm.states.clerk;

import fsm.FSMEvent;
import fsm.states.State;
import fsm.states.sharedprocesses.ProcessCreateEvent;
import fsm.states.sharedprocesses.ProcessLogout;
import nessa.process.Admin.PAcceptShipment;
import nessa.process.Admin.PAddClient;
import nessa.process.Admin.PPrintClientList;
import nessa.process.Admin.PQueryInventory;
import nessa.process.Admin.PViewBalances;

public class StateClerk  extends State {

/*
    In the ClerkMenuState, we have the following operations:

    (a)	Add a client. Gets details of new client; calls method on Façade.

    (b)	Show list of products with quantities and sale prices.  The state invokes a method on Facade to get an iterator, and then extracts the needed information.

    (c)	Show list of clients. The state invokes a method on Facade to get an iterator, and then extracts the needed information.

    (d)	Show list of clients with outstanding balance. The state invokes a method on Facade to get an iterator, and then extracts the needed information.

    (e)	Become a client. The actor will be asked to input a ClientID; if valid, this ID will be stored in Context, and the system transitions to the  ClientMenuState.

    (f)	Display the waitlist for a product. The state asks the actor for productid; calls method on Façade to get an iterator. 

    (g)	Receive a shipment. The state asks the actor for productid and quantity; calls method on Façade to get an iterator. Displays each waitlisted order and performs operation requested by actor (skip or fill).

    (h)	Logout. System transitions to the previous  state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ManagerMenuState.)
*/

    public StateClerk() {
        super("Clerk");
    }

    @Override
    public void addProcesses() {
        // Logout.
        processes.add(new ProcessLogout());
        //	Add a client. Gets details of new client; calls method on Façade.
        processes.add(new PAddClient("Client", "Add Client", "description"));
        //	Show list of clients
        processes.add(new PPrintClientList("Client", "Display Clients", "description"));
        //	Show list of clients with outstanding balance.
        processes.add(new PViewBalances("Client", "Display Client Balances", "UNIMPLEMENTED"));
        //	Become a client.
        processes.add(new ProcessCreateEvent(FSMEvent.VIEW_CLIENT, "Client", "View As", "temporarily views as a client"));
        
        //	Show list of products with quantities and sale prices.
        processes.add(new PQueryInventory("Inventory", "Display Inventory", "displays a list of products with quatities and sales prices"));
        //	Receive a shipment.
        processes.add(new PAcceptShipment("Inventory", "Accept Shipment", "process an incoming shipment of product"));
        //	Display the waitlist for a product.
        processes.add(new ProcessLoadTestingInventory());

    }

    
}
