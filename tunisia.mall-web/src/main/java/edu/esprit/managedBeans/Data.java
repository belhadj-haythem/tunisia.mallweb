package edu.esprit.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;

@ManagedBean
public class Data {
	
	private StreamedContent image;
    
	@PostConstruct
	public void init(){
		
	}
	
	public StreamedContent getImage() {
		return image;
	}

	public void setImage(StreamedContent image) {
		this.image = image;
	}
	
	
}
