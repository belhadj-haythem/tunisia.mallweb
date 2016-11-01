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
import tunisia.mall.interfaces.ShopServiceRemote;
import tunisia.mall.persistance.News;
import tunisia.mall.persistance.Shop;

@ManagedBean
@javax.faces.bean.SessionScoped
public class NewsBean implements Serializable {
	/**
	 * 
	 */
	private String id;
	private String title;
	private String description;
	private String subject;
	private String datedebut;
	private static final long serialVersionUID = 1L;
	@EJB
	NewsServiceRemote newsService;

	private StreamedContent myImage;
	private StreamedContent myImage2;
	private List<News> news;
	private List<News> listnews;
	List<StreamedContent> maliste;

	@PostConstruct
	public void init() {
		setListnews(newsService.findLastNews());
		setNews(newsService.list());
		// setMaliste(ajouterListe());
	}

	public String redirect() {
		FacesContext fc = FacesContext.getCurrentInstance();
		this.id = getIdParam(fc);
		this.subject = getdesParam(fc);
		this.datedebut= getdateParam(fc);
		this.title = gettitParam(fc);
		return "single?faces-redirect=true";
	}

	public String getIdParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("id");

	}

	public String gettitParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("title");

	}

	public String getdesParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("subject");

	}
	public String getdateParam(FacesContext fc) {

		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return params.get("datedebut");

	}

	public StreamedContent getMyImage() {

		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			String id = context.getExternalContext().getRequestParameterMap().get("id");
			News ne = newsService.findNews(Integer.parseInt(id));
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDatedebut() {
		return datedebut;
	}

	public void setDatedebut(String datedebut) {
		this.datedebut = datedebut;
	}

	public List<News> getListnews() {
		return listnews;
	}

	public void setListnews(List<News> listnews) {
		this.listnews = listnews;
	}

}