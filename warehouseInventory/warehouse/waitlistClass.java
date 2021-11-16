package warehouseInventory.warehouse;
/**
 * The waitlistClass class represents a waitlist using a client ID and quantity
 * of product requested. Each waitlist request is stored under individual
 * product IDs in ProductList.
 * 
 * @author Melissa Murray
 * @version 1.0, September 2021 Note that this version is not thread safe.
 */
public class waitlistClass {
    // Variable Declaration
    private int customer;
    private int waitlistQuantity;

    /**
     * Class constructor for waitlistClass specifying the number of objects to
     * create.
     */
    public waitlistClass(int customer, int waitlistQuantity) {
        this.customer = customer;
        this.waitlistQuantity = waitlistQuantity;
    }

    /**
     * This method is used to return the customer ID in the waitlist 
     * 
     * @param none
     * @return customer An integer ID representing a client
     */
    public int getCustomer() {
        return customer;
    }

    /**
     * This method is used to return the waitlist quantity in the waitlist 
     * 
     * @param none
     * @return waitlistQuantity An integer representing a product amount
     */
    public int getWaitlistQuantity() {
        return waitlistQuantity;
    }

    /**
     * This method is used to remove a quantity from the waitlist. If the
     * quantity is larger than the waitlist, the waitlist is set to 0.
     * Otherwise, the quantity is subtracted from the waitlist.
     * 
     * @param remove The quantity to be removed from the waitlist
     * @return int The remainder of subtracting the parameter from the
     * waitlist
     */
    public int removeWaitlistQuantity(int remove) {
        if (remove <= this.waitlistQuantity) { // the amount to remove
            // from the waitlist is not larger than the waitlist amount
            this.waitlistQuantity = this.waitlistQuantity - remove;
            return 0;
        } else { // The waitlist is cleared
            int remainder = remove - this.waitlistQuantity;
            this.waitlistQuantity = 0;
            return remainder;
        }
    }
}
