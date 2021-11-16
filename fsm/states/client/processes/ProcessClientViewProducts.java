package fsm.states.client.processes;

import java.util.ArrayList;
import java.util.Iterator;

import display.DisplayManager;
import fsm.FSMManager;
import nessa.process.UIProcess;
import warehouseInventory.warehouse.Inventory;
import warehouseInventory.warehouse.ProductList;

public class ProcessClientViewProducts extends UIProcess {

    public ProcessClientViewProducts() {
        super("TBD", "View Products", "view available products and their prices");
    }

    @Override
    public void process() {
        DisplayManager d = FSMManager.display;
        d.setHeader("Products Listings");
        Iterator<ProductList> itr = Inventory.instance().getInventoryIterator();
        ArrayList<String> lines = new ArrayList<String>();

        lines.add("Item\t| Quantity\t| Price");
        while(itr.hasNext()){
            ProductList pl = itr.next();
            String id = pl.getProductID();
            double price = pl.getRetailPrice();
            int qty = pl.getQuantity();
            lines.add(String.join("\t| ", new String[]{
                "\"" +id  +"\"",
                "" + qty,
                "$" + price
            })); 
        }
        d.displayLargeMessage(lines, true);

    }
    
}
