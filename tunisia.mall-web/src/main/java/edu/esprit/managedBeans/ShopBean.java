package edu.esprit.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import tunisia.mall.interfaces.ShopServiceLocal;
import tunisia.mall.persistance.News;
import tunisia.mall.persistance.Shop;

@ManagedBean
@ViewScoped
public class ShopBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	ShopServiceLocal shopServiceLocal;
	private String id;
	private String phone;
	private String nameShop;
	private String description;
	private String website;
	private List<Shop> allShops;
	private List<Shop> shops;
	private List<Shop> shops2;
	private List<Shop> shops3;
	private List<Shop> shops4;
	private List<Shop> shops5;
	private List<Shop> shops6;
	private String navigateTo;
	
	private StreamedContent myImage;
	
	
	@PostConstruct
	public void init() {
		setShops(shopServiceLocal.listCat1());
		setShops2(shopServiceLocal.listCat2());
		setShops3(shopServiceLocal.listCat3());
		setShops4(shopServiceLocal.listCat4());
		setShops5(shopServiceLocal.listCat5());
		setShops6(shopServiceLocal.listCat6());
		setAllShops(shopServiceLocal.listShop());
	}
	
	public String redirect() {
		FacesContext fc = FacesContext.getCurrentInstance();
		this.id = getIdParam(fc);
		this.description= getdesParam(fc);
		this.nameShop = gettitParam(fc);
		this.phone = getAdressParam(fc);
		this.website = getWebParam(fc);
		return "single?faces-redirect=true";
	}

	public String getAdressParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("phone");

	}
	public String getWebParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("website");

	}
	public String getIdParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("id");

	}

	public String gettitParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("nameShop");

	}

	public String getdesParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("description");

	}
	
	
	public StreamedContent getMyImage() {
		
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			String id = context.getExternalContext().getRequestParameterMap().get("id");
			Shop ne = shopServiceLocal.findShopById(Integer.parseInt(id));
			return new DefaultStreamedContent(new ByteArrayInputStream(ne.getPicture()));
		}
    
}


	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

	public List<Shop> getShops2() {
		return shops2;
	}

	public void setShops2(List<Shop> shops2) {
		this.shops2 = shops2;
	}

	public List<Shop> getShops3() {
		return shops3;
	}

	public void setShops3(List<Shop> shops3) {
		this.shops3 = shops3;
	}

	public List<Shop> getShops4() {
		return shops4;
	}

	public void setShops4(List<Shop> shops4) {
		this.shops4 = shops4;
	}

	public List<Shop> getShops5() {
		return shops5;
	}

	public void setShops5(List<Shop> shops5) {
		this.shops5 = shops5;
	}

	public List<Shop> getShops6() {
		return shops6;
	}

	public void setShops6(List<Shop> shops6) {
		this.shops6 = shops6;
	}

	public String getNavigateTo() {
		return navigateTo;
	}

	public void setNavigateTo(String navigateTo) {
		this.navigateTo = navigateTo;
	}

	public void setMyImage(StreamedContent myImage) {
		this.myImage = myImage;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNameShop() {
		return nameShop;
	}

	public void setNameShop(String nameShop) {
		this.nameShop = nameShop;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<Shop> getAllShops() {
		return allShops;
	}

	public void setAllShops(List<Shop> allShops) {
		this.allShops = allShops;
	}
	
	


	

	
}
