package edu.esprit.managedBeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

import tunisia.mall.interfaces.SurveyServiceRemote;
 
@ManagedBean
public class ChartView implements Serializable {
 
    private LineChartModel animatedModel1;
    private BarChartModel animatedModel2;
    
    @EJB
	SurveyServiceRemote surveyService;
	
 
    @PostConstruct
    public void init() {
        createAnimatedModels();
    }
 
    public LineChartModel getAnimatedModel1() {
        return animatedModel1;
    }
 
    public BarChartModel getAnimatedModel2() {
        return animatedModel2;
    }
 
    private void createAnimatedModels() {
        animatedModel1 = initLinearModel();
        animatedModel1.setTitle("Line Chart");
        animatedModel1.setAnimate(true);
        animatedModel1.setLegendPosition("se");
        Axis yAxis = animatedModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(20);
         
        animatedModel2 = initBarModel();
        animatedModel2.setTitle("Bar Charts");
        animatedModel2.setAnimate(true);
        animatedModel2.setLegendPosition("ne");
        yAxis = animatedModel2.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(20);
    }
     
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("yes");
        boys.set("Question1", yes());
        boys.set("Question2", yes2());
        boys.set("Question3", yes3());
        boys.set("Question4", yes4());
        boys.set("Question5", yes5());
      
        ChartSeries girls = new ChartSeries();
        girls.setLabel("no");
        girls.set("Question1", no());
        girls.set("Question2", no2());
        girls.set("Question3",no3());
        girls.set("Question4", no4());
        girls.set("Question5", no5());
     
        model.addSeries(boys);
        model.addSeries(girls);
         
        return model;
    }
     
    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
 
        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);
 
        model.addSeries(series1);
        model.addSeries(series2);
         
        return model;
    }
    
    

    private int yes(){
    	int a = 0;
    	for(String aa : surveyService.listr1()){
    		if(aa.equals("yes"))
    			a++;
    	}
    	return a;
    }
    
    private int yes2(){
    	int a = 0;
    	for(String aa : surveyService.listr2()){
    		if(aa.equals("yes"))
    			a++;
    	}
    	return a;
    }
    private int yes3(){
    	int a = 0;
    	for(String aa : surveyService.listr3()){
    		if(aa.equals("yes"))
    			a++;
    	}
    	return a;
    }
    private int yes4(){
    	int a = 0;
    	for(String aa : surveyService.listr4()){
    		if(aa.equals("yes"))
    			a++;
    	}
    	return a;
    }
    private int yes5(){
    	int a = 0;
    	for(String aa : surveyService.listr5()){
    		if(aa.equals("yes"))
    			a++;
    	}
    	return a;
    }
    
    
    private int no(){
    	int a = 0;
    	for(String aa : surveyService.listr1()){
    		if(aa.equals("no"))
    			a++;
    	}
    	return a;
    }
    
    private int no2(){
    	int a = 0;
    	for(String aa : surveyService.listr2()){
    		if(aa.equals("no"))
    			a++;
    	}
    	return a;
    }
    private int no3(){
    	int a = 0;
    	for(String aa : surveyService.listr3()){
    		if(aa.equals("no"))
    			a++;
    	}
    	return a;
    }
    private int no4(){
    	int a = 0;
    	for(String aa : surveyService.listr4()){
    		if(aa.equals("no"))
    			a++;
    	}
    	return a;
    }
    private int no5(){
    	int a = 0;
    	for(String aa : surveyService.listr5()){
    		if(aa.equals("no"))
    			a++;
    	}
    	return a;
    }
}