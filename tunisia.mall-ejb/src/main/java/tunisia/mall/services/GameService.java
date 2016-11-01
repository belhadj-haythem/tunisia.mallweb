package tunisia.mall.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tunisia.mall.interfaces.GameServiceLocal;
import tunisia.mall.interfaces.GameServiceRemote;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Event;
import tunisia.mall.persistance.Games;

/**
 * Session Bean implementation class GameService
 */
@Stateless
@LocalBean
public class GameService implements GameServiceRemote, GameServiceLocal {

	@PersistenceContext(name = "ejb-sample")
	EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public GameService() {
       
    }

	@Override
	public List<Games> listgames() {
		return entityManager.createQuery("select e from Games e").getResultList();
	}

	@Override
	public List<Games> listgameCustomer(Customer cus) {
				
		String requete = "select i from Games i join i.cus c where c.id_account=:id";

		Query query = entityManager.createQuery(requete).setParameter("id", cus.getId_account());

		return query.getResultList();
	}

	@Override
	public List<Customer> listCustomerGame(Games gam) {
		
		String requete = "select i from Customer i join i.games c where c.id=:id";

		Query query = entityManager.createQuery(requete).setParameter("id", gam.getId());

		return query.getResultList();
	}

	@Override
	public void subscribeToAGame(Games game, Customer customer) {


		List<Customer> lsr = new ArrayList<>();
		lsr = game.getCus();
		List<Games> lg = new ArrayList<>();
				lg=customer.getGames();
		lg.add(game);
		customer.setGames(lg);
		entityManager.merge(customer);

		lsr.add(customer);
		game.setCus(lsr);
		entityManager.merge(game);

	}
   
    
}
