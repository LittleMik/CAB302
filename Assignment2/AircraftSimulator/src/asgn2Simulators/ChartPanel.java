package asgn2Simulators;

import java.awt.Color;
import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;

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
	 * @param chartNumber - integer for chart identification
	 * @param dataset - dataset for JFreeChart creation
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
	 * Create and Setup JFreeChart XYLineChart
	 * @param XYDataset dataset
	 * @return JFreeChart chart
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
	        //Setup XY Axis
	        ValueAxis domain = plot.getDomainAxis();
	        domain.setRange(Constants.FIRST_FLIGHT, Constants.DURATION);
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
			//Setup XY Axis
	        ValueAxis domain = plot.getDomainAxis();
	        domain.setRange(0, Constants.DURATION);
	        ValueAxis range = plot.getRangeAxis();
	        range.setAutoRange(true);
		}
        return chart;
    }
	
	/**
	 * Get Chart
	 * @return JFreeChart chart
	 */
	public JFreeChart getChart(){
		return this.chart;
	}
	
	/**
	 * Get ChartPanel Object
	 * @return JFreeChart ChartPanel Object
	 */
	public org.jfree.chart.ChartPanel getChartPanel(){
		return this.chartPanel;
	}
}
