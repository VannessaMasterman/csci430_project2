package fsm.states.client.processes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import display.DisplayManager;
import fsm.Context;
import fsm.FSMManager;
import jm.Cart;
import jm.CartProductEntry;
import nessa.process.UIProcess;

public class ProcessModifyShoppingCart extends UIProcess {

    public ProcessModifyShoppingCart() {
        super("TBD", "Modify Shopping Cart", "add, remove, or change quantity of items in your cart");
    }

    @Override
    public void process() {
        DisplayManager d = FSMManager.display;
        d.setHeader("Cart - " + Context.get().clientID);
        int index = d.selectFromOptions("Please choose an operation", new String[]{"View Cart", "Add Item(s)", "Remove Item(s)", "Finish"});
        if (index == 0){
            viewCart();
        }else if(index == 1){
            // add
            addItems();
        } else if (index == 2){
            // remove
            removeItems();
        }else{
            // exit from loop
            return;
        }
        process();
    }

    private void viewCart(){
        DisplayManager d = FSMManager.display;
        d.setHeader("Cart - " + Context.get().clientID + ": View Carts");

        Cart cart = Context.get().getCart();
        Iterator<CartProductEntry> itr = cart.getIterator();

        List<String[]> rows = new ArrayList<String[]>();
        rows.add(new String[] { "Product", "Unit Price", "Quantity", "Total Price"});
        while(itr.hasNext()){
            CartProductEntry cpe = itr.next();
            rows.add(new String[]{cpe.getProductName(), "$" + cpe.getUnitPrice(), "" +cpe.getQuantity(), "$" +cpe.getTotalPrice()});
        }
        if(rows.size() == 1){
            d.displayMessage("No items in cart", true);
            return;
        }
        String[][] cells = new String[rows.size()][4];
        for(int i = 0; i < cells.length; i++){
            cells[i] = rows.get(i);
        }
        d.displayMessage("Items currently in cart", false);
        d.displayMessage("Total Price: $" + cart.getTotalAmount(), false);
        d.displayTable(cells, 4, cells.length, true);
    }

    private void addItems(){
        DisplayManager d = FSMManager.display;
        d.setHeader("Cart - " + Context.get().clientID + ": Adding Items");
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
