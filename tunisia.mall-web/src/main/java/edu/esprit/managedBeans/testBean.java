package edu.esprit.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tunisia.mall.interfaces.UserServicesLocal;
import tunisia.mall.interfaces.UserServicesRemote;
import tunisia.mall.persistance.Administrator;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.User;


@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class testBean implements Serializable {
	private String aaaa;

	private String username;
	private String password;
	private User user;
	private Customer customer;

	
	@EJB
	UserServicesLocal userService;
	
	@PostConstruct
	public void init() {
		setCustomer(new Customer());
		customer = userService.findCustomer(1);
		//setUsername(user.getUsername());
		//setPassword(user.getPassword());
	}
	
	
	public String doSaveCustomer(){
		String navigateTo = null;
		if (userService.addCustomer(customer))
			navigateTo = "/template/template?faces-redirect=true";
		return navigateTo;
	}
	
	public void doUpdateCustomer(){
		userService.updateCustomer(customer);
	}
	public void doDeleteCustomer(){
		userService.deleteCustomer(customer);
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


	public String getAaaa() {
		return aaaa;
	}


	public void setAaaa(String aaaa) {
		this.aaaa = aaaa;
	}
	
	public String redirection(){
		return "/template/test?faces-redirect=true";
	}

	
	
	
}
