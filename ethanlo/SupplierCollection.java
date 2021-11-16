package ethanlo;
import java.util.Random;

//import warehouseInventory.warehouse.Product;

//import java.io.*;
import java.util.*;

public class SupplierCollection {
	
	public ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
	static SupplierCollection singleton;

	private SupplierCollection() {}

	private SupplierCollection(ArrayList<Supplier> supplierList) {
		this.supplierList = supplierList;
	}

	public int findSupplierIndex(String name) {
		int count = supplierList.size();
		if (count == 0) {
			return -1;
		}

        for (int i = 0; i < count; i++) {
            Supplier test = supplierList.get(i);
            String b = test.getName();
            if (b.equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
	}
	
	public void addSupplier(String name, String phoneNumber, String address) {
		Random rand = new Random();
		int id = rand.nextInt(1000);
		Supplier newSupplier = new Supplier(name, phoneNumber, address, id);
		supplierList.add(newSupplier);
	}
	
	public static SupplierCollection instance() {
		if(singleton == null)
			singleton = new SupplierCollection();
		return singleton;
	}
	
}