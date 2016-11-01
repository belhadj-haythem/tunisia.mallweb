package edu.esprit.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import tunisia.mall.interfaces.ItemServiceRemote;
import tunisia.mall.persistance.News;
import tunisia.mall.persistance.Item;
@ManagedBean
@ApplicationScoped
public class ImageStreamer {

	private List<Item> items;
	private StreamedContent image;
    @EJB
    private ItemServiceRemote service;

    public StreamedContent getImage() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        } else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            //String imageId = context.getExternalContext().getRequestParameterMap().get("imageId");
            items = service.listItems(1);
            return new DefaultStreamedContent(new ByteArrayInputStream(items.get(0).getPhoto()));
        }
    }

	public void setImage(StreamedContent image) {
		this.image = image;
	}


    
    

}