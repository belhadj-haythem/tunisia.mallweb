package edu.esprit.managedBeans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.stage.FileChooser;
import tunisia.mall.interfaces.UserServicesLocal;



@ManagedBean
@RequestScoped
public class PdfBean implements Serializable  {

	private static final long serialVersionUID = 1L;

	String defaultFileName = "Catalogue.pdf";

	@EJB
	UserServicesLocal userservice;
	
	
	@SuppressWarnings("restriction")
	public void doCreatePdf() throws DocumentException, IOException  {
		
		String dest=null;
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save file");
		fileChooser.setInitialFileName(defaultFileName);
		File savedFile = fileChooser.showSaveDialog(null);

		if (savedFile != null) {

			File file = new File(savedFile.getAbsolutePath());
			file.getParentFile().mkdirs();
			dest=savedFile.getAbsolutePath();
		}

		String reference = null;

		List<tunisia.mall.persistance.Item> items = userservice.listItem();
		Document document = new Document(PageSize.A4);
		// Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));

		for (tunisia.mall.persistance.Item i : items) {
			if (!i.getReference().equals(reference)) {

				if (reference != null) {
					document.newPage();

				}

				byte[] IMAGE = i.getPhoto();
				document.open();
				document.add(new Paragraph(i.getName(),new Font(FontFamily.TIMES_ROMAN,30)));
				document.add(new Paragraph(i.getPrice() + " Dt",new Font(FontFamily.TIMES_ROMAN,30)));
				document.add(new Paragraph(i.getDescription(),new Font(FontFamily.TIMES_ROMAN,30)));

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


	public String getDefaultFileName() {
		return defaultFileName;
	}


	public void setDefaultFileName(String defaultFileName) {
		this.defaultFileName = defaultFileName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	


}
