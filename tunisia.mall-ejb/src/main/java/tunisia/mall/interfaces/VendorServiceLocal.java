package tunisia.mall.interfaces;

import java.util.List;

import javax.ejb.Local;

import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.Vendor;

@Local
public interface VendorServiceLocal {
	boolean addVendor(Vendor vendor);
	boolean removeVendor(Vendor vendor);
	Vendor findVendorById(int id);
	List<Vendor> listVendor(int id);
	boolean updateVendor(Vendor vendor);
	boolean JoinVendorToShop(Vendor vendor,Shop shop);
	Shop findShopById(int id);
	List<Vendor> findVendorByFname(String fname,int idshop);
	boolean isFloat(String s);
}
