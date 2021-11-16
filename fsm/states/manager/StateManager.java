package fsm.states.manager;

import fsm.FSMEvent;
import fsm.states.State;
import fsm.states.sharedprocesses.ProcessCreateEvent;
import fsm.states.sharedprocesses.ProcessLogout;
import nessa.process.Admin.PAddProduct;
import nessa.process.Admin.PAddSupplier;
import nessa.process.Admin.PViewSupplierList;
import nessa.process.Client.PViewBySupplier;
import nessa.process.Client.PViewProduct;

public class StateManager extends State {

/*
    In the ManagerMenuState, we have the following operations:

    (a)	Add a product

    (b)	Add a supplier

    (c)	Show list of suppliers

    (d)	Show list of suppliers for a product, with purchase prices

    (e)	Show list of products for a supplier, with purchase prices

    (f)	Update products and purchase prices for a particular supplier. Actor provides supplierID; system displays a list of productIDs and purchase prices. Actor can remove product, add product or change purchase price. 

    (g)	Become a salesclerk

    (h)	Logout.
*/

    public StateManager() {
        super("Manager");
    }

    @Override
    public void addProcesses() {
        // Logout
        processes.add(new ProcessLogout());

        // add a product
        processes.add(new PAddProduct("Inventory", "Add product", "adds a product to the inventory"));
        // add a supplier
        processes.add(new PAddSupplier("Inventory", "Add supplier","adds a supplier to a list of known suppliers"));
        // show list of suppliers

        processes.add(new PViewSupplierList("Inventory", "Display Supplier List", "views the list of known suppliers"));
        // show list of suppliers for a product, with purchase prices
        processes.add(new PViewBySupplier("Inventory", "Display Suppliers for Product", "NOT IMPLEMENTED!"));
        // show list of products for a supplier, with purchase prices
        processes.add(new PViewProduct("Inventory", "Display Products for Supplier", "NOT IMPLEMENTED!"));
        // update products and purchase prices for a particular supplier
        processes.add(new ProcessCreateEvent(FSMEvent.NO_EVENT, "Inventory", "Update Products", "NOT IMPLEMENTED!"));
        // become a salesclerk
        processes.add(new ProcessCreateEvent(FSMEvent.VIEW_CLERK, "Views", "View as clerk", "Perform operations as a salesclerk"));
    }
}
