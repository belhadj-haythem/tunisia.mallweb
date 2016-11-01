package edu.esprit.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import tunisia.mall.interfaces.CategorieLocal;
import tunisia.mall.persistance.ProductCategorie;

@ManagedBean
public class CategorieBean implements Serializable{

	@EJB
	CategorieLocal categorieLocal;
	String cat;
	ProductCategorie pc;
	
	@PostConstruct
	public void init() {
		setPc(categorieLocal.findCategorieByName(cat));
	}
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	public ProductCategorie getPc() {
		return pc;
	}
	public void setPc(ProductCategorie pc) {
		this.pc = pc;
	}
	
	
}
