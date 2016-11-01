package edu.esprit.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.ApplicationException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import tunisia.mall.interfaces.CartServiceLocal;
import tunisia.mall.interfaces.ItemServiceLocal;
import tunisia.mall.interfaces.UserServicesLocal;
import tunisia.mall.persistance.Cart;
import tunisia.mall.persistance.CartPK;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Order;

@ManagedBean
@ViewScoped

public class CartBean implements Serializable {

	@EJB
	CartServiceLocal cartServiceLocal;
	@EJB
	UserServicesLocal user;

	@EJB
	ItemServiceLocal itemServiceLocal;
	private StreamedContent myImage;
	private StreamedContent myImage2;
	private StreamedContent myImage3;

	private Item item;
	private Item item2;
	private Item item3;
	private Cart cart;
	private List<Cart> carts;
	Order order;
	private CartPK cpk;
	String idC;
	String idI;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		setCarts(cartServiceLocal.listCart());
		
		setItem(itemServiceLocal.recupererItem(999));
		setItem2(itemServiceLocal.recupererItem(998));
		setItem3(itemServiceLocal.recupererItem(997));
		setCart(new Cart());
		setCpk(new CartPK());
		setItem(itemServiceLocal.recupererItem(200));
		this.idC = getIdcParam(fc);
		this.idI = getIdIParam(fc);

	}

	public String getIdcParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("idC");

	}

	public String getIdIParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("idI");

	}

	public void doSave() {
		FacesContext fc = FacesContext.getCurrentInstance();
		this.idC = getIdcParam(fc);
		this.idI = getIdIParam(fc);
		cpk = new CartPK(Integer.parseInt(this.idC), Integer.parseInt(this.idI));
		cart.setCartPk(cpk);
		cart.setQuantity(1);
		cartServiceLocal.addCart(cart);
	}

	public StreamedContent getMyImage2() {

		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {

			return new DefaultStreamedContent(new ByteArrayInputStream(item2.getPhoto()));
		}

	}

	public StreamedContent getMyImage3() {

		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {

			return new DefaultStreamedContent(new ByteArrayInputStream(item3.getPhoto()));
		}

	}

	public void setMyImage2(StreamedContent myImage2) {
		this.myImage2 = myImage2;
	}

	public void setMyImage3(StreamedContent myImage3) {
		this.myImage3 = myImage3;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public String doDelete(Cart cart) {
		cartServiceLocal.deleteCart(cart);
		setCarts(cartServiceLocal.listCart());
		return "/tunisia.mall-web/template/checkout?faces-redirect=true";
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String payment() {
		return "payment?faces-redirect=true";
	}

	public CartPK getCpk() {
		return cpk;
	}

	public void setCpk(CartPK cpk) {
		this.cpk = cpk;
	}

	public String getIdC() {
		return idC;
	}

	public void setIdC(String idC) {
		this.idC = idC;
	}

	public String getIdI() {
		return idI;
	}

	public void setIdI(String idI) {
		this.idI = idI;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public StreamedContent getMyImage() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			return new DefaultStreamedContent(new ByteArrayInputStream(item.getPhoto()));
		}

	}

	public void setMyImage(StreamedContent myImage) {
		this.myImage = myImage;
	}

	public long docount() {
		return cartServiceLocal.countCarts();
	}

	public Item getItem2() {
		return item2;
	}

	public void setItem2(Item item2) {
		this.item2 = item2;
	}

	public Item getItem3() {
		return item3;
	}

	public void setItem3(Item item3) {
		this.item3 = item3;
	}

}
