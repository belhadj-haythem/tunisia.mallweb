package edu.esprit.managedBeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class MapMallBean {

	@PostConstruct
	public void init(){
		
	}
	
	public String changelevel1(){
		return "level1?faces-redirect=true";
	}
	
	public String changelevel0(){
		return "level0?faces-redirect=true";
	}

}
