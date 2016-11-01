package edu.esprit.managedBeans;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import tunisia.mall.interfaces.NewsServiceLocal;
import tunisia.mall.interfaces.NewsServiceRemote;
import tunisia.mall.interfaces.ShopServiceLocal;
import tunisia.mall.interfaces.ShopServiceRemote;
import tunisia.mall.persistance.News;
import tunisia.mall.persistance.Shop;

@ManagedBean
@javax.faces.bean.SessionScoped
public class ImageBean implements Serializable {
	/**
	 * 
	 */
	private String id;
	private String nameShop;
	private String website;
	private String phone;
	private String description;
	private static final long serialVersionUID = 1L;
	@EJB
	NewsServiceLocal newsService;

	private StreamedContent myImage;
	private StreamedContent myImage2;
	private List<News> news;
	List<StreamedContent> maliste;

	@PostConstruct
	public void init() {
		//setNews(newsService.list());
		// setMaliste(ajouterListe());
	}

	public String redirect() {
		FacesContext fc = FacesContext.getCurrentInstance();
		this.id = getIdParam(fc);
		setNameShop(gettitParam(fc));
		this.description = getdesParam(fc);
		this.phone = getPhoneParam(fc);
		this.website = getWebParam(fc);
		return "singleShop?faces-redirect=true";
	}

	public String getIdParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("id");

	}

	public String gettitParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("nameShop");

	}
	
	public String getWebParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("website");

	}
	
	public String getdesParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("description");

	}
	
	public String getPhoneParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("phone");

	}


	public StreamedContent getMyImage() {

		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			String id = context.getExternalContext().getRequestParameterMap().get("id");
			Shop ne = newsService.findShop(Integer.parseInt(id));
			return new DefaultStreamedContent(new ByteArrayInputStream(ne.getPicture()));
		}

	}

	public void setMyImage(StreamedContent myImage) {
		this.myImage = myImage;
	}

	public List<StreamedContent> getMaliste() {
		return maliste;
	}

	public void setMaliste(List<StreamedContent> maliste) {
		this.maliste = maliste;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	public void setMyImage2(StreamedContent myImage2) {
		this.myImage2 = myImage2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameShop() {
		return nameShop;
	}

	public void setNameShop(String nameShop) {
		this.nameShop = nameShop;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}