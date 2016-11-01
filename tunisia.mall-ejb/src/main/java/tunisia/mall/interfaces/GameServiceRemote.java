package tunisia.mall.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Games;

@Remote
public interface GameServiceRemote {
	
	public List<Games> listgames();
	public List<Games> listgameCustomer(Customer cus);	
	public List<Customer> listCustomerGame(Games gam);
	public void subscribeToAGame(Games games, Customer customer);
	

}
