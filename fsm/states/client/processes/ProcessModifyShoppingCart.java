package fsm.states.client.processes;

import display.DisplayManager;
import fsm.Context;
import fsm.FSMManager;
import nessa.process.UIProcess;

public class ProcessModifyShoppingCart extends UIProcess {

    public ProcessModifyShoppingCart() {
        super("TBD", "Modify Shopping Cart", "add, remove, or change quantity of items in your cart");
    }

    @Override
    public void process() {
        DisplayManager d = FSMManager.display;
        d.setHeader("Cart - " + Context.get().clientID);
        int index = d.selectFromOptions("Please choose an operation", new String[]{"Add Item(s)", "Remove Item(s)", "Finish"});
        if (index == 0){
            // add
            addItems();
        }else if(index == 1){
            // remove
            removeItems();
        } else{
            // exit from loop
            return;
        }
        process();
    }

    private void addItems(){
        DisplayManager d = FSMManager.display;
        d.setHeader("Cart - " + Context.get().clientID + "\tAdding Items");
        displayCart();
        String id = d.getInputString("Please enter the product ID to add ", false);
        int quantity = d.getInputInteger("How many would you like to add? ");
        Context.get().getCart().addToCart(id, quantity);
        if(d.verify("Would you like to add more items?")) addItems(); // recurse for more
    }

    private void removeItems(){
        DisplayManager d = FSMManager.display;
        d.setHeader("Cart - " + Context.get().clientID + "\tRemoving Items");
        displayCart();
        String id = d.getInputString("Please enter the product ID to remove ", false);
        int quantity = d.getInputInteger("How many would you like to remove?\n(Entering a larger quantity than is present will remove the item from your cart.) ");
        Context.get().getCart().removeFromCart(id, quantity);
        if(d.verify("Would you like to remove more items?")) removeItems(); // recurse for more
    }

    private void displayCart(){
        Context.get().getCart().showCart(); // TODO this is bad. Need a better system for porting to GUI
    }
    
}
