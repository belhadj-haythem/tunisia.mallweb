package tunisia.mall.interfaces;

import java.util.List;

import javax.ejb.Local;

import tunisia.mall.persistance.Shop;

@Local
public interface ShopServiceLocal {
	public boolean addShop(Shop shop);
	public boolean deleteShop(Shop shop);
	public Shop findShopById(int id);
    public List<Shop> listShop();
    public boolean updateShop(Shop shop);
    public Shop searchShopByName(String nameShop);
    public List<Shop> listCat1();
    public List<Shop> listCat2();
    public List<Shop> listCat3();
    public List<Shop> listCat4();
    public List<Shop> listCat5();
    public List<Shop> listCat6();
}
