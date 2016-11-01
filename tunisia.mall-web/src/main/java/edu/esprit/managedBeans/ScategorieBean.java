package edu.esprit.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import tunisia.mall.interfaces.ScategorieServiceLocal;
@ManagedBean
public class ScategorieBean implements Serializable{

	@EJB
	ScategorieServiceLocal scategorieServiceLocal;
	@PostConstruct
	public void init() {


	}
}
