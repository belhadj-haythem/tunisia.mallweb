package edu.esprit.managedBeans;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import tunisia.mall.interfaces.NewsServiceRemote;
import tunisia.mall.interfaces.SurveyServiceLocal;
import tunisia.mall.interfaces.SurveyServiceRemote;
import tunisia.mall.persistance.*;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
@ManagedBean
public class CheckboxView {
	@EJB
	SurveyServiceRemote surveyService;
    private String[] selectedConsoles;
    private String[] selectedConsoles2;
    private String[] selectedCities;
    private String[] selectedCities2;
    private String[] selectedCities3;
    private String[] selectedCities4;
    private String[] selectedCities5;
    private String[] selectedCities6;
    private String[] selectedCities7;

    private List<String> cities;
    private List<String> cities1;
    private List<String> cities2;
    private List<String> cities3;
    private List<String> cities4;
    private List<String> cities5;

 
    @PostConstruct
    public void init() {
        cities = new ArrayList<String>();
        cities1 = new ArrayList<String>();
        cities2 = new ArrayList<String>();
        cities3 = new ArrayList<String>();
        cities4 = new ArrayList<String>();
        cities5 = new ArrayList<String>();
        cities.add("yes");
        cities.add("no");
        cities1.add("yes");
        cities1.add("no");
        cities2.add("yes");
        cities2.add("no");
        cities3.add("yes");
        cities3.add("no");
        cities4.add("yes");
        cities4.add("no");
        cities5.add("yes");
        cities5.add("no");
        
        
    }
    
public void doPdf( String[] selectedCities2,String[] selectedCities3,String[] selectedCities4,String[] selectedCities5,String[] selectedCities6,String[] selectedCities7){
		
	
		Survey  s = new Survey();
		s.setR1(selectedCities2[0]);
		s.setR2(selectedCities3[0]);
		s.setR3(selectedCities4[0]);
		s.setR4(selectedCities5[0]);
		s.setR5(selectedCities6[0]);
		s.setR6(selectedCities7[0]);

		surveyService.addSurvey(s);
		

	}

 
    public List<String> getCities1() {
	return cities1;
}

public void setCities1(List<String> cities1) {
	this.cities1 = cities1;
}

public List<String> getCities2() {
	return cities2;
}

public void setCities2(List<String> cities2) {
	this.cities2 = cities2;
}

public List<String> getCities3() {
	return cities3;
}

public void setCities3(List<String> cities3) {
	this.cities3 = cities3;
}

public List<String> getCities4() {
	return cities4;
}

public void setCities4(List<String> cities4) {
	this.cities4 = cities4;
}

public List<String> getCities5() {
	return cities5;
}

public void setCities5(List<String> cities5) {
	this.cities5 = cities5;
}

public void setCities(List<String> cities) {
	this.cities = cities;
}


	public String[] getSelectedCities3() {
	return selectedCities3;
}

public void setSelectedCities3(String[] selectedCities3) {
	this.selectedCities3 = selectedCities3;
}

public String[] getSelectedCities4() {
	return selectedCities4;
}

public void setSelectedCities4(String[] selectedCities4) {
	this.selectedCities4 = selectedCities4;
}

public String[] getSelectedCities5() {
	return selectedCities5;
}

public void setSelectedCities5(String[] selectedCities5) {
	this.selectedCities5 = selectedCities5;
}

public String[] getSelectedCities6() {
	return selectedCities6;
}

public void setSelectedCities6(String[] selectedCities6) {
	this.selectedCities6 = selectedCities6;
}

public String[] getSelectedCities7() {
	return selectedCities7;
}

public void setSelectedCities7(String[] selectedCities7) {
	this.selectedCities7 = selectedCities7;
}

	public String[] getSelectedConsoles() {
        return selectedConsoles;
    }
 
    public void setSelectedConsoles(String[] selectedConsoles) {
        this.selectedConsoles = selectedConsoles;
    }
 
    public String[] getSelectedCities() {
        return selectedCities;
    }
 
    public void setSelectedCities2(String[] selectedCities) {
        this.selectedCities = selectedCities;
    }
 
    public String[] getSelectedCities2() {
        return selectedCities;
    }
 
    public void setSelectedCities(String[] selectedCities2) {
        this.selectedCities2 = selectedCities2;
    }
 
    public String[] getSelectedConsoles2() {
        return selectedConsoles2;
    }
 
    public void setSelectedConsoles2(String[] selectedConsoles2) {
        this.selectedConsoles2 = selectedConsoles2;
    }
 
    public List<String> getCities() {
        return cities;
    }
}