package warehouseInventory.warehouse;

import java.util.*;

/**
 * The Product class represents a product type in the warehouse inventory.
 * 
 * @author Melissa Murray
 * @version 1.0, September 2021 Note that this version is not thread safe.
 */
public class ProductList {
    // Variable Declaration
    private String productID;
    private int quantity;
    private int waitlistQuantity = 0;
    public ArrayList<waitlistClass> waitlistPair = new ArrayList<waitlistClass>();
    public ArrayList<Product> productInventory = new ArrayList<Product>();
    private double retailPrice;

    /**
     * Class constructor for ProductList.
     */
    public ProductList() {
    }
    
    /**
     * Class constructor for ProductList specifying the number of objects to create.
     */
    public ProductList(String productID, int quantity, int waitlistQuantity, double retailPrice,
            ArrayList<Product> productInventory) {
        this.productID = productID;
        this.quantity = quantity;
        this.waitlistQuantity = 0;
        this.retailPrice = retailPrice;
        this.productInventory = productInventory;
    }

    /**
     * Class constructor for ProductList specifying the number of objects to create.
     */
    public ProductList(String productID, int quantity, int waitlistQuantity, double retailPrice,
            ArrayList<Product> productInventory, ArrayList<waitlistClass> waitlistPair) {
        this.productID = productID;
        this.quantity = quantity;
        this.waitlistQuantity = 0;
        this.retailPrice = retailPrice;
        this.productInventory = productInventory;
        this.waitlistPair = waitlistPair;
    }

    /**
     * This method is used to return the name of a product
     * 
     * @param none
     * @return productID A String representing a product name
     */
    public String getProductID() {
        return productID;
    }

    /**
     * This method is used to change the quantity
     * 
     * @param q The new value of the quantity
     * @return none
     */
    void setQuantity(int q) {
        this.quantity = q;
    }

    /**
     * This method is used to return the quantity of a product
     * 
     * @param none
     * @return quantity An integer representing a product quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * This method is used to increment the waitlist quantity
     * 
     * @param w The value added to the waitlist
     * @return none
     */
    public void updateWaitlistQuantity(int w) {
        this.waitlistQuantity += w;
    }

    /**
     * This method is used to return the waitlist quantity of a product
     * 
     * @param none
     * @return waitlistQuantity An integer representing a waitlist quantity of a
     *         product
     */
    public int getWaitlistQuantity() {
        return waitlistQuantity;
    }

    /**
     * This method is used to change the waitlist quantity
     * 
     * @param q The new value of the waitlist
     * @return none
     */
    public void setWaitlisedQuantity(int q) {
        this.waitlistQuantity = q;
    }

    /**
     * This method is used to return the retail price of a product
     * 
     * @param none
     * @return retailPrice A double representing a retail price
     */
    public double getRetailPrice() {
        return this.retailPrice;
    }

    /**
     * This method is used to return the number of suppliers of a product
     * 
     * @param none
     * @return int A integer representing a the number of product suppliers
     */
    public int getNumberofSuppliers() {
        return productInventory.size();
    }

    /**
     * This method is used to return the number of suppliers of a product at a
     * specified index
     * 
     * @param productListIndex The index of the product in ProductList
     * @return supSize A integer representing a the number of product suppliers
     */
    public int getProductSupSize(int productListIndex) {
        // Nessa: Why is this taking a param???
        int supSize = productInventory.size();
        return supSize;
    }

    /**
     * This method is used to increment the total quantity of a product
     * 
     * @param q The amount added to the current total quantity
     * @return none
     */
    public void updateTotalQuantity(int q) {
        this.quantity += q;
    }

    /**
     * This method is used to subtract from the total quantity of a product
     * 
     * @param q The amount removed from the current total quantity
     * @return none
     */
    public void removeTotalQuantity(int q) {
        if (q < this.quantity) { // if the amount removed is less than
            // the current product quantity
            this.quantity -= q;
        } else // quantity is set to 0
            this.quantity = 0;

    }

}
