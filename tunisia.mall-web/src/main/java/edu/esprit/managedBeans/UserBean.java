package edu.esprit.managedBeans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import tunisia.mall.interfaces.ItemServiceLocal;
import tunisia.mall.interfaces.UserServicesLocal;
import tunisia.mall.persistance.Customer;
import tunisia.mall.persistance.Event;
import tunisia.mall.persistance.Item;
import tunisia.mall.persistance.Shop;
import tunisia.mall.persistance.User;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UserBean implements Serializable {
	
	private StreamedContent image;
	
	private String username;
	private String password;
	private User user;
	private Customer customer;

	// ------- mail -----

	private String to;
	private String from;
	private String name;
	private String message;
	private String subject;
	private String phone;

	// ---------PDF---------
	public String defaultFileName = "Catalogue.pdf";
	// ---------------------

	@EJB
	UserServicesLocal userService;
	@EJB
	ItemServiceLocal itemService;

	@PostConstruct
	public void init() {
		setCustomer(new Customer());
		// customer = userService.findCustomer(1);
	}

	public String doSaveCustomer() {
		String navigateTo = null;
		if (userService.addCustomer(customer))
			navigateTo = "/template/template?faces-redirect=true";
		return navigateTo;
	}

	public String doUpdateCustomer() {
		String navigateTo = null;
		boolean a = userService.updateCustomer(customer);
		System.out.println(a);
		if (a)
			navigateTo = "/template/test?faces-redirect=true";
		return navigateTo;
	}

	public void doSendMail() {
		System.out.println(to);
		System.out.println(from);
		System.out.println(message);
		System.out.println(phone);

		try {
			SimpleMail.envoyerMail("tunisiamalladm@gmail.com", to, message + "\n Phone : " + phone,
					name + " : " + subject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doDeleteCustomer() {
		userService.deleteCustomer(customer);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public StreamedContent doCreatePdf() throws DocumentException, IOException {

		String reference = null;

		List<tunisia.mall.persistance.Item> items = userService.listItem();
		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PdfWriter writer = PdfWriter.getInstance(document, baos);

		for (tunisia.mall.persistance.Item i : items) {
			if (!i.getReference().equals(reference)) {

				if (reference != null) {
					document.newPage();

				}

				byte[] IMAGE = i.getPhoto();
				document.open();

				PdfContentByte canvas = writer.getDirectContentUnder();

				Image image = Image.getInstance(IMAGE);
				image.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight() - 70);
				image.setAbsolutePosition(0, 70);
				canvas.addImage(image);
				canvas.setRGBColorFill(243, 214, 23);
				canvas.setLineWidth(1f);

				canvas.rectangle(0, 0, PageSize.A4.getWidth() - 100, 70);
				canvas.fill();

				canvas.setRGBColorFill(255, 255, 0);
				canvas.rectangle(PageSize.A4.getWidth() - 100, 0, 100, 70);
				canvas.fill();

				canvas.setRGBColorFill(0, 0, 0);
				canvas.beginText();

				BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
				canvas.setFontAndSize(bf, 30);
				canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, i.getName(), (PageSize.A4.getWidth() - 100) / 2, 43,
						0);

				BaseFont bf2 = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
				canvas.setFontAndSize(bf2, 20);
				canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, i.getDescription(), 70, 15, 0);

				BaseFont bf3 = BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
				canvas.setFontAndSize(bf3, 27);
				canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, i.getPrice() + "", PageSize.A4.getWidth() - 50, 43,
						0);
				canvas.showTextAligned(PdfContentByte.ALIGN_CENTER, "Dt", PageSize.A4.getWidth() - 40, 15, 0);

				canvas.endText();
				canvas.stroke();
				reference = i.getReference();
			}

		}
		document.close();

		InputStream stream = new ByteArrayInputStream(baos.toByteArray());

		StreamedContent cat = new DefaultStreamedContent(stream, "application/pdf", "Catalogue.pdf");
		return cat;

	}

	public void doCreatePdf2(Shop s) throws DocumentException, IOException {
		String reference = null;
		List<tunisia.mall.persistance.Item> items = itemService.listItems(s.getId_account());
		Document document = new Document(PageSize.A4);
		document.newPage();
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream("C:\\Users\\khadijahela\\Desktop\\Catalogue.pdf"));
		for (tunisia.mall.persistance.Item i : items) {
			if (!i.getReference().equals(reference)) {
				if (reference != null) {
					document.newPage();
				}
				byte[] IMAGE = i.getPhoto();
				document.open();
				document.add(new Paragraph(i.getName(), new Font(FontFamily.TIMES_ROMAN, 30)));
				document.add(new Paragraph(i.getPrice() + " Dt", new Font(FontFamily.TIMES_ROMAN, 30)));
				document.add(new Paragraph(i.getDescription(), new Font(FontFamily.TIMES_ROMAN, 30)));

				PdfContentByte canvas = writer.getDirectContentUnder();

				Image image = Image.getInstance(IMAGE);
				image.scaleAbsolute(PageSize.A4.getWidth(), PageSize.A4.getHeight());
				image.setAbsolutePosition(0, 0);
				canvas.addImage(image);
				reference = i.getReference();
			}
		}
		document.close();
	}

	
	public List<StreamedContent> listeImages(){
		String reference = null;
		List<StreamedContent> maListe =new ArrayList<StreamedContent>();

		List<tunisia.mall.persistance.Item> items = userService.listItem();
		for (tunisia.mall.persistance.Item i : items) {
			if (!i.getReference().equals(reference)) {

				byte[] IMAGE = i.getPhoto();
				maListe.add(new DefaultStreamedContent(new ByteArrayInputStream(IMAGE)));
				reference = i.getReference();
			}
		}
		return maListe;
		
	}
	
	public List<Item> listItems(){
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

		return maListefiltre;
	}

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


	
	
}
