package tunisia.mall.interfaces;

import java.util.List;

import javax.ejb.Local;

import tunisia.mall.persistance.Event;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Shop;

@Local
public interface EventServicesLocal {
	public boolean addEvent(Event e);
	public int addEvent2(Event e);
	public boolean updateEvent(Event e);
	public boolean deleteEvent(Event e);
	public Event findEvent(int id);
	public List<Event> ListEvent();
	public List<Event> searchListEventById(int id);
	public Shop searchUserByid(int id);
	public List<Item> listItems(int id);
	public boolean updateItem(Item e);
	public List<Item> listItemsByIdEvent(Event event);
	public Shop searchShopByName(String name);
}
