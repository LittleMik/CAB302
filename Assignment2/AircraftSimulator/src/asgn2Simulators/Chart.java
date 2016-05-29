/**
 * 
 */
package asgn2Simulators;

import java.awt.BorderLayout;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

/**
 * @author Michael
 *
 */
@SuppressWarnings("serial")
public class Chart extends ApplicationFrame {
	
	//Chart Constructor
	public Chart(String title, Dataset dataset, int chartNumber){
		super(title);
		JFreeChart chart;
        if(chartNumber == 1){
        	chart = createXYChart((XYDataset) dataset);
        }else{
        	chart = createBarChart((CategoryDataset) dataset);
        }
        
        this.add(new ChartPanel(chart), BorderLayout.CENTER);
	}
	/**
	 * Create and Setup JFreeChart XYLineChart
	 * @param XYDataset dataset
	 * @return JFreeChart chart
	 */
	private JFreeChart createXYChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Chart 1: Progress", "Days", "Passengers", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = chart.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setRange(Constants.FIRST_FLIGHT, Constants.DURATION);
        ValueAxis range = plot.getRangeAxis();
        range.setAutoRange(true);
        return chart;
    }
	
	/**
	 * Create and Setup JFreeChart Bar Chart
	 * @param XYDataset dataset
	 * @return JFreeChart chart
	 */
	private JFreeChart createBarChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
            "Chart 2: Summary", "Passengers", "Categories", dataset, PlotOrientation.VERTICAL, true, true, false);
        return chart;
    }
}
