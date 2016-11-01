package edu.esprit.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import tunisia.mall.interfaces.VendorServiceLocal;

@ManagedBean
public class VendorBean implements Serializable{

	@EJB
	VendorServiceLocal vendorServiceLocal;
	@PostConstruct
	public void init() {


	}
}
