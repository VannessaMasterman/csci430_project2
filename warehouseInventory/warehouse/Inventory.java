package warehouseInventory.warehouse;
import java.util.*;
/**
 * The Inventory class represents a Warehouse using nested ArrayLists of classes
 * ProductList and Product.
 * 
 * @author Melissa Murray
 * @version 1.0, September 2021 Note that this version is not thread safe.
 */
public class Inventory {

    // Variable Declaration
    private static Inventory singleton;
    public ArrayList<ProductList> warehouseInventory = new ArrayList<ProductList>();

    /**
     * Class constructor for Inventory.
     */
    private Inventory() {
    }

    /**
     * Class constructor for Inventory specifying the number of objects to create.
     */
    private Inventory(ArrayList<ProductList> warehouseInventory) {
        this.warehouseInventory = warehouseInventory;
    }

    /**
     * This method is used to prevent the creation of multiple Inventory instances.
     * 
     * @param none
     * @return Inventory An instance of Inventory is
     */
    public static Inventory instance() {
        if (singleton == null) {
            singleton = new Inventory();
        }
        return singleton;
    }

    /**
     * This method is used to return the index of productID in the ProductList. If
     * it is not found in the ArrayList, -1 is returned
     * 
     * @param productID string
     * @return int The index of productID in ProductList
     */
    public int isNewProduct(String productID) {
        int count = warehouseInventory.size();

        for (int i = 0; i < count; i++) {
            ProductList test = warehouseInventory.get(i);
            String b = test.getProductID();
            if (b.equalsIgnoreCase(productID)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * This method is used to return the waitlist amount of productID in ArrayList
     * Product.
     * 
     * @param productID string
     * @param index     int
     * @return int The amount of waitlist requests for a product
     */
    private int isProductWaitlisted(String productID, int index) {
        ProductList pList = warehouseInventory.get(index);

        if (pList.getWaitlistQuantity() > 0) {
            int temp = pList.getWaitlistQuantity();
            return temp;
        } else
            return 0;
    }

    /**
     * This method is used to return the index of a supplier in the Product
     * Arraylist. If the supplier is not present, a non-index is returned.
     * 
     * @param supplierID   string
     * @param productIndex int
     * @return int The index of the suplierID in Product
     */
    private int isNewSupplier(String supplierID, int productIndex) {
        ProductList test = warehouseInventory.get(productIndex);
        int testSizeAtIndex = test.getProductSupSize(productIndex);
        ArrayList<Product> testProductArray = test.productInventory;

        for (int j = 0; j < testSizeAtIndex; j++) {
            Product testProduct = testProductArray.get(j);
            String b = testProduct.getsupplierID();
            if (b.equalsIgnoreCase(supplierID)) {
                return j;
            }
        }
        return -1;
    }

    /**
     * This method is used to fill waitlist requests with incoming shipments. As
     * individual requests are filled, they are removed from the waitliest queue.
     * Leftover products are sent to the inventory.
     * 
     * @param productID        string
     * @param quantityAdded    int
     * @param amountWaitlisted int
     * @param index            int
     * @return int The remainder of quantityAdded
     */
    private int ClearWaitList(String productID, int quantityAdded, int amountWaitlisted, int index) {
        int remainder = 0;
        ProductList listP = warehouseInventory.get(index);
        ArrayList<waitlistClass> wList = listP.waitlistPair;
        int waitingCustomers = wList.size();
        //int temp = 0;

        if (waitingCustomers == 0) {
            return quantityAdded;
        } // The waitlist is empty

        // The incoming shipment can fill all waitlist requests
        if (quantityAdded >= amountWaitlisted) {
            for (int i = 0; i < waitingCustomers; i++) {
                //waitlistClass w = wList.get(i);
                //int customer = w.getCustomer();
                //int waitlistQuantity = w.getWaitlistQuantity();

                // @TODO send customer and order to shopping cart
            }

            remainder = quantityAdded - amountWaitlisted;
            listP.setWaitlisedQuantity(0);
            wList.clear(); // The waitlist is cleared
            return remainder;
        }

        // Individual client requests are processed
        for (int i = 0; i <= waitingCustomers; i++) {
            waitlistClass w = wList.get(i);
            //int customer = w.getCustomer();
            //int waitlistQuantity = w.getWaitlistQuantity();
            int t = w.removeWaitlistQuantity(quantityAdded);
            quantityAdded = t;

            // @TODO send customer and order to shopping cart

            if (t <= 0) {
                return 0;
            } else if (t > 0) {
                // if the client's request is filled, they
                // are removed from the waitlist
                wList.remove(i);
                waitingCustomers--;
                i--;
            }
            if (i == waitingCustomers - 1) {
                // The incoming shipment was not enough
                // to fulfill the waitlist requests.
                return t;
            }
        }
        return -1;
    }

    /**
     * This method is used to add a product to the warehouse inventory by a
     * supplier. If the product does not exist in the inventory, it is added. If it
     * exists, but the supplier has not provided the product prior, the supplier is
     * added to the list of suppliers for the product.
     * 
     * @param productID     string
     * @param supplierID    string
     * @param supplierPrice double
     * @param retailPrice   double
     * @param quantity      int
     * @return none
     */
    public void addProduct(String productID, String supplierID, double supplierPrice, double retailPrice,
            int quantity) {
        int index = isNewProduct(productID);

        if (index == -1) { // if the product does not exist in the inventory
            Product newProduct = new Product(supplierID, quantity, supplierPrice);
            ArrayList<Product> newProductArrayList = new ArrayList<Product>();

            newProductArrayList.add(newProduct);
            ProductList newProductList = new ProductList(productID, quantity, 0, retailPrice, newProductArrayList);
            warehouseInventory.add(newProductList);

        } else { // the product does exist in the inventory
            int amountWaitlisted = isProductWaitlisted(productID, index);
            int remainder = ClearWaitList(productID, quantity, amountWaitlisted, index);

            ProductList addP = warehouseInventory.get(index);
            int productIndex = isNewSupplier(supplierID, index);
            ArrayList<Product> testProductArray = addP.productInventory;

            if (productIndex == -1) { // the supplier is new to a particular product
                Product newSupplier = new Product(supplierID, remainder, supplierPrice);
                testProductArray.add(newSupplier);

            } else { // the supplier is already registered for the product
                Product updateSupplier = testProductArray.get(productIndex);
                updateSupplier.addSupplierQuantity(remainder);
            }
            addP.updateTotalQuantity(remainder); // The total quantity of the product
            // is incremented by the quantity passed through the function
        }
    }

    /**
     * This method is used to remove a product from the warehouse inventory by the
     * client's cart. If the client's request exceeds the current quantity of the
     * requested product, the remaining request is added to the product's waitlist
     * to be filled at a later date.
     * 
     * @param productID       int
     * @param amountPurchased int
     * @param customerID      string
     * @return int This is the value added to the waitlist of productID
     */
    public int removeProduct(String productID, int amountPurchased, int customerID) {
        int index = isNewProduct(productID);

        if (index != -1) { // The productID was found in the inventory
            ProductList currentProduct = warehouseInventory.get(index);
            ArrayList<Product> ProductArray = currentProduct.productInventory;
            int supSize = ProductArray.size();
            int leftoverRequest;
            currentProduct.removeTotalQuantity(amountPurchased);

            for (int i = 0; i < supSize; i++) {
                Product indvSupplierProduct = ProductArray.get(i);
                leftoverRequest = indvSupplierProduct.removeSupplierQuantity(amountPurchased);
                amountPurchased = leftoverRequest;

                if (leftoverRequest <= 0) { // The quantity of productID exceeeds the
                    // amount purchased by the client -- no value added to the waitlist
                    return 0;
                }

                if ((leftoverRequest > 0) && (i == supSize - 1)) { // The quantity of
                    // leftoverRequest exceeeds the supply of productID. The remaining
                    // requested amount is added to the product waitlist
                    ArrayList<waitlistClass> customerWaitlist = currentProduct.waitlistPair;
                    waitlistClass w = new waitlistClass(customerID, leftoverRequest);
                    customerWaitlist.add(w);
                    currentProduct.updateWaitlistQuantity(leftoverRequest);
                    return leftoverRequest;
                }
            }
        }
        return 0;
    }

    /**
     * This method is used to print the list of suppliers for an individual product.
     * 
     * @param none
     * @return none
     */
    public void printSupplierList(String productID) {
        int index = isNewProduct(productID);
        System.out.println("Product ID: " + productID);
        System.out.println("~~~~~~~~~~~~~~~~~~~");

        if (index == -1) { // if the product does not exist in the inventory
            System.out.println("That product does not exist in our inventory. Apologies.");
        } else { // the product does exist in the inventory
            ProductList printSupplier = warehouseInventory.get(index);
            ArrayList<Product> printSupplierArray = printSupplier.productInventory;
            int count = printSupplierArray.size();
            System.out.println("Current Suppliers: ");

            for (int i = 0; i < count; i++) {
                Product supplierName = printSupplierArray.get(i);
                System.out.println((i + 1) + ". " + supplierName.getsupplierID());
            }
        }
    }

    /**
     * This method is used to print the complete inventory of the virtual warehouse,
     * along with individual suppliers and quantities.
     * 
     * @param none
     * @return none
     */
    public void printCompleteInventory() {
        int count = warehouseInventory.size();
        System.out.println("There are currently " + count + " products in our inventory.");

        for (int i = 0; i < count; i++) {
            ProductList test = warehouseInventory.get(i);
            int testSizeAtIndex = test.getProductSupSize(i);
            ArrayList<Product> testProductArray = test.productInventory;

            int a = test.getNumberofSuppliers();
            String b = test.getProductID();
            int c = test.getQuantity();
            int d = test.getWaitlistQuantity();
            double retail = test.getRetailPrice();

            System.out.println("No. Suppliers \tProductID \tTotal Quantity \tWaitlist \tRetailPrice");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(a + "\t\t" + b + "\t\t" + c + "\t\t" + d + "\t\t$" + retail);
            System.out.println();
            System.out.println("\tSupplierID \tSupplier Price \tSupplier Quantity");
            System.out.println("\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            for (int j = 0; j < testSizeAtIndex; j++) {
                Product testProduct = testProductArray.get(j);
                String e = testProduct.getsupplierID();
                double f = testProduct.getsupplierPrice();
                int g = testProduct.getsupplierQuantity();

                System.out.println("\t" + (j + 1) + " " + e + "\t$" + f + "\t\t" + g);
            }
            System.out.println();
        }
    }

    public Iterator<ProductList> getInventoryIterator(){
        return warehouseInventory.iterator();
    }
}