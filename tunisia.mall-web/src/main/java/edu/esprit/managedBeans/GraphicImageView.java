package edu.esprit.managedBeans;



import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.imageio.ImageIO;
 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import tunisia.mall.interfaces.CartServiceLocal;
import tunisia.mall.interfaces.ItemServiceLocal;
import tunisia.mall.interfaces.SurveyServiceRemote;
 
@ManagedBean
public class GraphicImageView {
	@EJB
	CartServiceLocal cartServiceLocal;
	@EJB
	SurveyServiceRemote surveyService;
	
	@EJB
	ItemServiceLocal itemServiceLocal;
	
	
	
    private StreamedContent graphicText;
         
    private StreamedContent chart;
 
    @PostConstruct
    public void init() {
        try {
            //Graphic Text
            BufferedImage bufferedImg = new BufferedImage(100, 25, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bufferedImg.createGraphics();
            g2.drawString("This is a text", 0, 10);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferedImg, "png", os);
            graphicText = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png"); 
 
            //Chart
            JFreeChart jfreechart = ChartFactory.createPieChart("", createDataset(), true, true, false);
            File chartFile = new File("dynamichart");
            ChartUtilities.saveChartAsPNG(chartFile, jfreechart, 375, 300);
            chart = new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public StreamedContent getGraphicText() {
        return graphicText;
    }
         
    public StreamedContent getChart() {
        return chart;
    }
     
    private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Nbre of Items", itemServiceLocal.listItem().size());
        dataset.setValue("Nbre of cart", cartServiceLocal.listCart().size());
       
 
        return dataset;
    }
}