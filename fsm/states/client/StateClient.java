package fsm.states.client;

import fsm.states.State;
import fsm.states.client.processes.ProcessClientViewProducts;
import fsm.states.client.processes.ProcessClientViewWaitlist;
import fsm.states.client.processes.ProcessDisplayClientDetails;
import fsm.states.client.processes.ProcessDisplayClientTransactions;
import fsm.states.client.processes.ProcessModifyShoppingCart;
import fsm.states.sharedprocesses.ProcessLogout;

public class StateClient  extends State {
/* Design Documentation
    In the ClientMenuState, the Context has stored the ClientID for the current client; all operations are for that ClientID. The state will have operations for the following:

    (a)	Show client details. The state invokes a method on Facade to get the Client object and then gets the client details. Note that the ClientID is available in the Context.

    (b)	Show list of products with sale prices.  The state invokes a method on Facade to get an iterator, and then extracts the needed information.

    (c)	Show client transactions. The state invokes a method on Facade to get the Client object and then gets the transaction details for the client. Note that the ClientID is available in the Context.

    (d)	Modify client’s shopping cart. System displays contents. Actor can add product, remove product or change quantity.

    (e)	Display client’s waitlist.

    (f)	Logout. System transitions to the previous  state, which has to be remembered in the context. (If previous state was the OpeningState, it goes there; otherwise it goes to ClerkMenuState.)
*/

    public StateClient() {
        super("Client");
    }

    @Override
    public void addProcesses() {
        // logout
        processes.add(new ProcessLogout());
        // see client details
        processes.add(new ProcessDisplayClientDetails());
        // show list of products with sales prices
        processes.add(new ProcessClientViewProducts());
        // show client transactions
        processes.add(new ProcessDisplayClientTransactions());
        // modify shopping cart (add, remove, change quantity)
        processes.add(new ProcessModifyShoppingCart());
        // display waitlist
        processes.add(new ProcessClientViewWaitlist());
    }

}