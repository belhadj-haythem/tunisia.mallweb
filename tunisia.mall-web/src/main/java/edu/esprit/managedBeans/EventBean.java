package edu.esprit.managedBeans;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import tunisia.mall.interfaces.EventServicesLocal;
import tunisia.mall.persistance.Event;
import tunisia.mall.persistance.User;

@ManagedBean
@SessionScoped
public class EventBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Event event;
	private User user;
	private List<Event> maListe;
	private RepeatPaginatorEvent paginator;

	

	@EJB
	EventServicesLocal eventServicesLocal;
	private StreamedContent myImage;

	@PostConstruct
	public void init() {
		setEvent(new Event());
		user = new User();
		maListe= eventServicesLocal.ListEvent();
		Collections.reverse(maListe);
		paginator = new RepeatPaginatorEvent(this.maListe);
	}

	public StreamedContent getMyImage() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			String id = context.getExternalContext().getRequestParameterMap().get("id");
			Event ne = eventServicesLocal.findEvent(Integer.parseInt(id));
			return new DefaultStreamedContent(new ByteArrayInputStream(ne.getPicture()));
		}
	}
	
public void doCreatePdf(Event ev) throws DocumentException, IOException  {
		

		Document document = new Document(PageSize.A4);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Haythem\\Desktop\\"+ev.getTitle()+".pdf"));


				byte[] IMAGE = ev.getPicture();
				document.open();
				document.add(new Paragraph("Name shop: "+doReturnNameShop(ev),new Font(FontFamily.TIMES_ROMAN,30)));
				document.add(new Paragraph("title: "+ev.getTitle(),new Font(FontFamily.TIMES_ROMAN,30)));
				document.add(new Paragraph("place: "+ev.getPlace() + " Dt",new Font(FontFamily.TIMES_ROMAN,30)));
				document.add(new Paragraph("Start date: "+ev.getDateDebut(),new Font(FontFamily.TIMES_ROMAN,30)));
				document.add(new Paragraph("End date: "+ev.getDateFin() + " Dt",new Font(FontFamily.TIMES_ROMAN,30)));
				document.add(new Paragraph("             "));
				document.add(new Paragraph("Subject: "+ev.getSubject(),new Font(FontFamily.TIMES_ROMAN,30)));
				
				PdfContentByte canvas = writer.getDirectContentUnder();

				Image image = Image.getInstance(IMAGE);
				image.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());
				image.setAbsolutePosition(0, 0);
				canvas.addImage(image);


		
		document.close();

	}
	public Event dernierEvent(){
		
		return maListe.get(0);
		
	}
	
	public String doReturnNameShop(Event ev){
		
		return ev.getShopOwner().getNameShop();
	}
	
	public int dernierEvents(){
		return maListe.size()-1;
	}
	

	public List<Event> getMaListe() {
		return maListe;
	}

	public void setMaListe(List<Event> maListe) {
		this.maListe = maListe;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setMyImage(StreamedContent myImage) {
		this.myImage = myImage;
	}
	public RepeatPaginatorEvent getPaginator() {
		return paginator;
	}

	public void setPaginator(RepeatPaginatorEvent paginator) {
		this.paginator = paginator;
	}

	
	public long compterParSeconde(Date dfin){
		
		Long dfd = dfin.getTime()-new Date().getTime();
		if (dfd<0) 
		return 0;
		
		return dfd;	
		
	}
	
	
	
}
