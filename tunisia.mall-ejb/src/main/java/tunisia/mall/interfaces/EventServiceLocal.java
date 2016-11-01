package tunisia.mall.interfaces;

import javax.ejb.Local;

import tunisia.mall.persistance.Event;

@Local
public interface EventServiceLocal {
	public boolean ajouter(Event v);
}
