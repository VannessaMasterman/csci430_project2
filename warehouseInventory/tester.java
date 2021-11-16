package warehouseInventory;


public class tester {
    public static void main(String[] args) {

        warehouseInventory.warehouse.Inventory i = warehouseInventory.warehouse.Inventory.instance();
        warehouseInventory.warehouse.Inventory b = warehouseInventory.warehouse.Inventory.instance();

        double retailp = 1, supplierp = 0.5;
        int q = 6;
        String proID = "apple", suppID = "store46";

        i.addProduct(proID, suppID, supplierp, retailp, q);
        i.addProduct("banana", "store23", .5, 2, 6);
        b.addProduct("banana", "store46", .5, 2, 6);
        i.addProduct("banana", "store39", .3, 2, 11);

        i.printCompleteInventory();

        b.removeProduct("banana", 20, 6123);
        b.removeProduct("apple", 10, 4184);

        System.out.println(" ~~~~~~ UPDATE SYSTEM ~~~~~~~~~");
        System.out.println();

        b.printCompleteInventory();

        b.addProduct("apple", "store46", .5, 2, 6);
        b.removeProduct("banana", 4, 6123);
        b.removeProduct("banana", 3, 4184);
        b.removeProduct("banana", 1, 4584);
        b.addProduct("banana", "store95", .5, 2, 6);

        System.out.println(" ~~~~~~ UPDATE SYSTEM ~~~~~~~~~");
        System.out.println();

        b.printCompleteInventory();

        System.out.println();
        i.printSupplierList("banana");
        System.out.println();
        i.printSupplierList("milk");
    }
}
