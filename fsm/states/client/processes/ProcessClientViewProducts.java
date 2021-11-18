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
        ArrayList<String[]> lines = new ArrayList<String[]>();

        lines.add(new String[]{"Item","Quantity","Price"});
        while(itr.hasNext()){
            ProductList pl = itr.next();
            String id = pl.getProductID();
            double price = pl.getRetailPrice();
            int qty = pl.getQuantity();
            lines.add(new String[]{
                "\"" +id  +"\"",
                "" + qty,
                "$" + price
            }); 
        }
        String[][] cells = new String[lines.size()][3];
        for(int i = 0; i < cells.length; i++){
            cells[i] = lines.get(i);
        }
        d.displayMessage("Available Items:", false);
        d.displayTable(cells, 3, cells.length, true);


    }
    
}
