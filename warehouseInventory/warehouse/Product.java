package warehouseInventory.warehouse;
/**
 * The Product class represents a single product in the warehouse inventory.
 * 
 * @author Melissa Murray
 * @version 1.0, September 2021 Note that this version is not thread safe.
 */
public class Product {
    // Variable Declaration
    private String supplierID;
    private int supplierQuantity;
    private double supplierPrice;

    /**
     * Class constructor for Product.
     */
    public Product() {
    }

    /**
     * Class constructor for Product specifying the number of objects to create.
     */
    public Product(String supplierID, int supplierQuantity, double supplierPrice) {
        this.supplierID = supplierID;
        this.supplierQuantity = supplierQuantity;
        this.supplierPrice = supplierPrice;
    }

    /**
     * This method is used to return the supplier ID
     * 
     * @param none
     * @return supplierQuantity A String representing a supllier name
     */
    public String getsupplierID() {
        return supplierID;
    }

    /**
     * This method is used to alter the supplier quantity of a product
     * 
     * @param s An integer representing the new product quantity
     * @return none
     */
    public void setsupplierQuantity(int s) {
        this.supplierQuantity = s;
    }

    /**
     * This method is used to return the supplier quantity of a product
     * 
     * @param none
     * @return supplierQuantity An integer representing a product quantity from a
     *         specific supplier
     */
    public int getsupplierQuantity() {
        return supplierQuantity;
    }

    /**
     * This method is used to return the supplier price of a product
     * 
     * @param none
     * @return supplierPrice An integer representing a product price
     */
    public double getsupplierPrice() {
        return supplierPrice;
    }

    /**
     * This method is used to increment the supplier quantity of a product
     * 
     * @param addition An integer representing the addition to the product quantity
     * @return none
     */
    public void addSupplierQuantity(int addition) {
        this.supplierQuantity += addition;
    }

    /**
     * This method is used to remove a product quantity from a specific supplier. If
     * the quantity is larger than the current supply, supllier inventory is set to
     * zero. Otherwise, the quantity is subtracted from the supplier inventory.
     * 
     * @param remove The quantity to be removed from the product inventory
     * @return int The remainder of subtracting the parameter from the inventory
     */
    public int removeSupplierQuantity(int remove) {
        if (remove <= this.supplierQuantity) {
            this.supplierQuantity = this.supplierQuantity - remove;
            return 0;
        } else {
            int remainder = remove - this.supplierQuantity;
            this.supplierQuantity = 0;
            return remainder;
        }
    }
}
