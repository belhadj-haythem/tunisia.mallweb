package tunisia.mall.interfaces;

import java.util.List;

import javax.ejb.Local;

import tunisia.mall.persistance.Cart;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Order;

@Local
public interface CartServiceLocal {
	boolean addCart(Cart cart);
	boolean addOrder(Order order);
	boolean update(Order order);
	List<Order> listOrder();
	List<Order> findOrderByadress(String fadress);
	List<Cart> findpriceItem(int id,int idshop);
	Order findOrder(int id);
	List<Cart> listCartByIdShop(int idshop);
	long countItems(int idshop);
	boolean updateCart(Cart cart);
	boolean deleteCart(Cart cart);
	List<Cart> listCart();
	List<Item> listItemsInCarts();
	long countCarts();
	boolean DeleteAll(List<Cart> carts);

}
