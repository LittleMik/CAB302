/**
 * 
 */
package asgn2Simulators;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;

/**
 * @author Michael
 *
 */
public class Chart extends ApplicationFrame {
	
	//Chart Constructor
	public Chart(String title, XYDataset dataset){
		super(title);
        JFreeChart chart = createChart(dataset);
        this.add(new ChartPanel(chart), BorderLayout.CENTER);
	}
	/**
	 * Create and Setup JFreeChart Chart
	 * @param XYDataset dataset
	 * @return JFreeChart chart
	 */
	private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            "Chart 1: Progress", "Days", "Passengers", dataset, true, true, false);
        XYPlot plot = chart.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setAutoRange(true);
        return chart;
    }
}
