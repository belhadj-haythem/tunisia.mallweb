package edu.esprit.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tunisia.mall.interfaces.UserServicesLocal;
import tunisia.mall.persistance.Administrator;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.User;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private String username;
	private String password;
	private User user;
	public static Customer customer;
	private Shop shop;
	private Administrator admin;

	@EJB
	UserServicesLocal userService;



	public String doLogin() {
		user = new User();
		String navigateTo = null;
		user = userService.authentification(username, password);

		if (user != null) {
			if (user instanceof Customer){
				navigateTo = "/template/test?faces-redirect=true";
			setCustomer(userService.findCustomer(user.getId_account()));
			}
			if (user instanceof Administrator){
				navigateTo = "/admin/home?faces-redirect=true";
			}
			if (user instanceof Shop){
				navigateTo = "/shopOwner/home?faces-redirect=true";
			}
		}
		return navigateTo;
	}

	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		setCustomer(null);
		setAdmin(null);
		setShop(null);
		return "/template/login?faces-redirect=true";
	}

	public String doUpdateCustomer() {
		String navigateTo = null;
		boolean a = userService.updateCustomer(customer);
		if (a)
			navigateTo = "/template/test?faces-redirect=true";
		return navigateTo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	public UserServicesLocal getUserService() {
		return userService;
	}

	public void setUserService(UserServicesLocal userService) {
		this.userService = userService;
	}
	
	

}
