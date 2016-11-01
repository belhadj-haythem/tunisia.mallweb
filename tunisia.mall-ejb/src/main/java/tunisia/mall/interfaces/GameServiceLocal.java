package tunisia.mall.interfaces;

import java.util.List;

import javax.ejb.Local;

import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Games;

@Local
public interface GameServiceLocal {

	public List<Games> listgames();
	public List<Games> listgameCustomer(Customer cus);
	public List<Customer> listCustomerGame(Games gam);
	public void subscribeToAGame(Games games, Customer customer);

}
