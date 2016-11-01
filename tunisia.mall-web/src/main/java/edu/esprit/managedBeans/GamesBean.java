package edu.esprit.managedBeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import tunisia.mall.interfaces.GameServiceLocal;
import tunisia.mall.interfaces.UserServicesLocal;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Games;

@ManagedBean
@RequestScoped
public class GamesBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Games games;
	private Customer cus;
	private List<Games> maListe;
	private List<Customer> cusList ;
	private Customer cu;
	
	
	@EJB
	GameServiceLocal gameServiceLocal;
	@EJB
	UserServicesLocal userServicesLocal;
	@PostConstruct
	public void init() {
		setGames(new Games());
		cus = new Customer();
		maListe= gameServiceLocal.listgames();
		cu=userServicesLocal.findCustomer(LoginBean.customer.getId_account());
	}
	
	public void doSubscribe(Games g){
		gameServiceLocal.subscribeToAGame(g, cu);
	}
	
	public List<Games> retourListGames(){
		 return gameServiceLocal.listgameCustomer(cu);
	}
	
	public Games getGames() {
		return games;
	}
	public void setGames(Games games) {
		this.games = games;
	}
	public Customer getCus() {
		return cus;
	}
	public void setCus(Customer cus) {
		this.cus = cus;
	}
	public List<Games> getMaListe() {
		return maListe;
	}
	public void setMaListe(List<Games> maListe) {
		this.maListe = maListe;
	}
	public List<Customer> getCusList() {
		return cusList;
	}
	public void setCusList(List<Customer> cusList) {
		this.cusList = cusList;
	}
	public GameServiceLocal getGameServiceLocal() {
		return gameServiceLocal;
	}
	public void setGameServiceLocal(GameServiceLocal gameServiceLocal) {
		this.gameServiceLocal = gameServiceLocal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
