package edu.esprit.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import tunisia.mall.interfaces.ItemServiceLocal;
import tunisia.mall.interfaces.UserServicesLocal;
import tunisia.mall.persistance.Item;

@ManagedBean
@SessionScoped
public class CatalogueBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StreamedContent image;
	
	@EJB
	UserServicesLocal userService;
	@EJB
	ItemServiceLocal itemService;
	
	public StreamedContent getImage() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			String id = context.getExternalContext().getRequestParameterMap().get("id");
			Item ne = itemService.findById(Integer.parseInt(id));
			return new DefaultStreamedContent(new ByteArrayInputStream(ne.getPhoto()));
		}
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}
	public List<Item> listItems(){
		List<StreamedContent> a = new ArrayList<StreamedContent>();
		List<Item> maListe = new ArrayList<Item>();
		List<Item> maListefiltre = new ArrayList<Item>();
		maListe = userService.listItem();
		
	    maListefiltre.add(maListe.get(0));
	    int k=1;
	    for(int j=1;j<maListe.size();j++){
	    	if(!maListe.get(j).getReference().equals(maListefiltre.get(j-k).getReference())){
	    		maListefiltre.add(maListe.get(j));
	    	}
	    	else{
	    		k++;
	    	}
	    }
	    
//	    for (int i = 0; i < maListefiltre.size(); i++) {
//			a.add(new DefaultStreamedContent(new ByteArrayInputStream(maListefiltre.get(i).getPhoto())));
//		}

		return maListefiltre;
	}
}
