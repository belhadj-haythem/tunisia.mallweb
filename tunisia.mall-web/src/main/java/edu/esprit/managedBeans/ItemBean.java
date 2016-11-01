package edu.esprit.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import tunisia.mall.interfaces.ItemServiceLocal;
import tunisia.mall.interfaces.UserServicesLocal;
import tunisia.mall.persistance.CartPK;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.News;
import tunisia.mall.persistance.Stock;

@ManagedBean
@SessionScoped
public class ItemBean implements Serializable{
	private String min="0";

	private Item item;
	private List<Item> l;
	private StreamedContent myImage;
	private List<Item> liste;
	private List<Item> l1;
	private Customer c;
	private CartPK cpk;
	private List<Stock> stocks;
	private RepeatPaginator paginator;
	private String reference;
	private String id;
	private List<Item> liste1;

	@EJB
	ItemServiceLocal itemServiceLocal;
	
	@EJB
	UserServicesLocal userServiceLocal;
	
	@PostConstruct
	public void init() {
		
		setItem(new Item());
		
		l=null;
	    l= itemServiceLocal.listItems(9);
	    liste = new ArrayList<Item>();
	    liste.add(l.get(0));
	    int k=1;
	    for(int j=1;j<l.size();j++){
	    	if(!l.get(j).getReference().equals(liste.get(j-k).getReference())){
	    		liste.add(l.get(j));
	    	}
	    	else{
	    		k++;
	    	}
	    }
	    setPaginator(new RepeatPaginator(this.liste));
	   
	}
	
	public String outcome2(){
		FacesContext fc = FacesContext.getCurrentInstance();
		this.id = getIdParam(fc);

		
	    l= itemServiceLocal.listItems(Integer.parseInt(id));
	    liste = new ArrayList<Item>();
	    liste.add(l.get(0));
	    int k=1;
	    for(int j=1;j<l.size();j++){
	    	if(!l.get(j).getReference().equals(liste.get(j-k).getReference())){
	    		liste.add(l.get(j));
	    	}
	    	else{
	    		k++;
	    	}
	    }
	    setPaginator(new RepeatPaginator(this.liste));
	   
		return "products";
	}
	
   public String outcome(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		this.reference=getCountryParam(fc) ;
		
		FacesContext fc1 = FacesContext.getCurrentInstance();
		this.id = getIdParam(fc1);
		
		l=new ArrayList<Item>();
		l=itemServiceLocal.searchItemByReference(reference, Integer.parseInt(id));
		stocks=null;
		stocks=itemServiceLocal.listStock(l.get(0).getId());
		item=l.get(0);
		c= new Customer();
		c= userServiceLocal.findCustomer(20);
		System.out.println(c.getId_account());
//		cpk=new CartPK();
//		cpk.setIdCustomer(20);
//		cpk.setIdItems(1);
		return "SingleProduct";
	}

	//get value from "f:param"
	public String getCountryParam(FacesContext fc){
	
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return  params.get("item");
		
	}
	public String getIdParam(FacesContext fc){
		
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return  params.get("id");
		
	}
	public String getIdcParam(FacesContext fc){
		
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return  params.get("idC");
		
	}
	public String getIdiParam(FacesContext fc){
		
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		return  params.get("idI");
		
	}
	
	
	public StreamedContent getMyImage() {
		
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }
        else {
            String id = context.getExternalContext().getRequestParameterMap().get("id");
            Item ne = itemServiceLocal.findById(Integer.parseInt(id));
            return new DefaultStreamedContent(new ByteArrayInputStream(ne.getPhoto()));
        }
    
}
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public List<Item> getL() {
		return l;
	}
	public void setL(List<Item> l) {
		this.l = l;
	}
	
	public void setMyImage(StreamedContent myImage) {
		this.myImage = myImage;
	}
	public List<Item> getListe() {
		return liste;
	}
	public void setListe(List<Item> liste) {
		this.liste = liste;
	}

	public RepeatPaginator getPaginator() {
		return paginator;
	}

	public void setPaginator(RepeatPaginator paginator) {
		this.paginator = paginator;
	}

	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public List<Stock> getStocks() {
		return stocks;
	}
	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
	public List<Item> getL1() {
		return l1;
	}
	public void setL1(List<Item> l1) {
		this.l1 = l1;
	}
	public Customer getC() {
		return c;
	}
	public void setC(Customer c) {
		this.c = c;
	}
	public CartPK getCpk() {
		return cpk;
	}
	public void setCpk(CartPK cpk) {
		this.cpk = cpk;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	


public String outcome3() {
		
		l = itemServiceLocal.listItems(Integer.parseInt(id));
		liste = new ArrayList<Item>();
		liste.add(l.get(0));
		int k = 1;
		for (int j = 1; j < l.size(); j++) {
			if (!l.get(j).getReference().equals(liste.get(j - k).getReference())) {
				liste.add(l.get(j));
			} else {
				k++;
			}
		}
		liste1 = new ArrayList<Item>();
		for(Item i:liste){
			if(i.getPrice() < Float.parseFloat(min)){
				liste1.add(i);
			}
		}
		setPaginator(new RepeatPaginator(this.liste1));
		return "products";
	}



public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public List<Item> getListe1() {
		return liste1;
	}

	public void setListe1(List<Item> liste1) {
		this.liste1 = liste1;
	}





	
	
	
	
}
