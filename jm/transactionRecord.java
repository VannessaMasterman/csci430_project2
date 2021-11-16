package jm;

/*---TransactionRecord Class
Author: Israel Musa--*/

public class transactionRecord{
	private String date;
	private String clientID;
	private double price;
	
    //Date
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}

    //clientID
	public void setVendor(String vendor) {
		// what is the purpose here?
		// no uses found, no tangible results...why does this exist?
		/**Nessa: I commented this out to remove warnings */
		//this.clientID = clientID;
	}
	public String getClientID() {
		return clientID;
	}

    //Price
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
}