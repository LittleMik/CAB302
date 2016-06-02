/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;


import java.awt.Color;
import java.awt.Dimension;

import org.jfree.chart.*;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

/**
 * The <code>ChartPanel</code> class is used to create and display
 * the charts for the GUI when given a chart identification and XYDataset.
 * The chart is contained in a JFreeChart ChartPanel which is also contained
 * within the <code>ChartPanel</code> class
 * @author Michael
 *
 */
public class ChartPanel{
	
	private int chartNumber;
	private JFreeChart chart;
	private org.jfree.chart.ChartPanel chartPanel;
	
	/**
	 * Empty Constructor for activeChartPanel
	 */
	public ChartPanel(){}
	
	/**
	 * ChartPanel Constructor
	 * @param <code>int</code> - chartNumber for chart identification
	 * @param <code>XYDataset</code> - dataset for JFreeChart creation
	 */
	public ChartPanel(int chartNumber, XYDataset dataset){
		this.chartNumber = chartNumber;
		
		//Setup Chart
		this.chart = createChart(dataset);
		
		//Setup JFreeChart ChartPanel
		this.chartPanel = new org.jfree.chart.ChartPanel(this.chart);
		this.chartPanel.setBackground(Color.lightGray);
		
	    //Set Dimension
	    this.chartPanel.setPreferredSize(new Dimension(800,550));
	}
	
	/**
	 * Get Chart
	 * @return <code>JFreeChart</code> chart
	 */
	public JFreeChart getChart(){
		return this.chart;
	}
	
	/**
	 * Get ChartPanel Object
	 * @return <code>JFreeChart</code> ChartPanel Object
	 */
	public org.jfree.chart.ChartPanel getChartPanel(){
		return this.chartPanel;
	}
	
	/**
	 * Create and Setup JFreeChart XYLineChart
	 * @param <code>XYDataset</code> dataset for XYLineChart creation
	 * @return <code>JFreeChart</code> created XYLineChart chart
	 */
	private JFreeChart createChart(XYDataset dataset) {
		JFreeChart chart;
		
		if(chartNumber == 1){
			//Setup Chart
			chart = ChartFactory.createXYLineChart(
	            "Chart 1: Progress", "Days", "Passengers", dataset, PlotOrientation.VERTICAL, true, true, false);
	        XYPlot plot = chart.getXYPlot();
	        
	        //Set Series Colours
	        plot.getRenderer().setSeriesPaint(0, Color.BLACK);
	        plot.getRenderer().setSeriesPaint(1, Color.BLUE);
	        plot.getRenderer().setSeriesPaint(2, Color.CYAN);
	        plot.getRenderer().setSeriesPaint(3, Color.GRAY);
	        plot.getRenderer().setSeriesPaint(4, Color.GREEN);
	        plot.getRenderer().setSeriesPaint(5, Color.RED);
	        
	        //Set X Axis from FIRST_FLIGHT to DURATION
	        ValueAxis domain = plot.getDomainAxis();
	        domain.setRange(Constants.FIRST_FLIGHT, Constants.DURATION);
	        //Set Y Axis range to be auto
	        ValueAxis range = plot.getRangeAxis();
	        range.setAutoRange(true);
		}else{
			//Setup Chart
			chart = ChartFactory.createXYLineChart(
				"Chart 2: Summary", "Days", "Passengers", dataset, PlotOrientation.VERTICAL, true, true, false);
			XYPlot plot = chart.getXYPlot();
			
			//Set Series Colours
			plot.getRenderer().setSeriesPaint(0, Color.BLACK);
			plot.getRenderer().setSeriesPaint(1, Color.RED);
			
			//Set X Axis from start of simulation (0) to DURATION
	        ValueAxis domain = plot.getDomainAxis();
	        domain.setRange(0, Constants.DURATION);
	        
	        //Set Y Axis range to be auto
	        ValueAxis range = plot.getRangeAxis();
	        range.setAutoRange(true);
		}
        return chart;
    }	
}
