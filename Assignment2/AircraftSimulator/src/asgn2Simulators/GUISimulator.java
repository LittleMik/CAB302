/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import asgn2Aircraft.AircraftException;
import asgn2Passengers.PassengerException;

/**
 * @author hogan
 *
 */
@SuppressWarnings("serial")
public class GUISimulator extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 200;
	

	private JPanel pnlOne;
	private JPanel pnlTwo;
	private JPanel pnlThree;
	private JPanel pnlFour;
	private JPanel pnlFive;
	
	private JButton btnRun;
	private JButton btnShow;
	
	private JLabel txtOperation;
	private JLabel txtFareClasses;
	private JLabel txtSimulation;
	
	private JLabel txtFirst;
	private JLabel txtPremium;
	private JLabel txtBusiness;
	private JLabel txtEconomy;
	
	private JLabel txtRng;
	private JLabel txtMean;
	private JLabel txtQsize;
	private JLabel txtCancellation;
	
	private JTextArea txtOutPut;
	private JLabel blankSpace;
	private JLabel blankSpace2;
	private JLabel blankSpace3;
	private JLabel blankSpace4;
	private JLabel blankSpace5;
	private JLabel blankSpace6;
	
	
	private JTextField txtInputFirst;
	private JTextField txtInputBusiness;
	private JTextField txtInputPremium;
	private JTextField txtInputEconomy;
	
	private JTextField txtInputRng;
	private JTextField txtInputMean;
	private JTextField txtInputQsize;
	private JTextField txtInputCancellation;
	
	private Chart chart1;
	private Chart chart2;
	
	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public GUISimulator(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}	
	
	private void createGUI(){
		
		setSize(800, 500);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    setResizable(false);
	    
	    //Solution code uses different colours to highlight different panels 
	    pnlOne = createPanel(Color.lightGray);
	    pnlTwo = createPanel(Color.lightGray);
	    
	    
	    btnRun = createButton("Run Simulation");
	    btnShow = createButton("Show Graph");
	    
	    btnRun.setPreferredSize(new Dimension(170,75));
	    btnShow.setPreferredSize(new Dimension(170,75));
	    
	    pnlThree = new JPanel(new GridBagLayout());
	    pnlThree.setBackground(Color.lightGray);
	    pnlFive = new JPanel(new GridBagLayout());
	    pnlFive.setBackground(Color.lightGray);
	    pnlFour = new JPanel(new GridBagLayout());
	    pnlFour.setBackground(Color.lightGray);
	    
	    GridBagConstraints c = new GridBagConstraints();
	    GridBagConstraints c2 = new GridBagConstraints();
	    GridBagConstraints c3 = new GridBagConstraints();
	    
	    pnlOne.setPreferredSize(new Dimension(800,250));
	    pnlTwo.setPreferredSize(new Dimension(200,250));
	    pnlThree.setPreferredSize(new Dimension(300,250));
	    pnlFive.setPreferredSize(new Dimension(300,250));
		    
	    txtOutPut = new JTextArea();
	    
	    txtInputFirst = new JTextField(10);
	    txtInputBusiness = new JTextField(10);
	    //txtOutPut.setPreferredSize(new Dimension(700,50));
	    txtOutPut.setLineWrap(true);
	    txtOutPut.setEditable(false);
	    txtOutPut.setVisible(true);
	    
	    txtOperation = new JLabel();
	    txtFareClasses = new JLabel();
	    txtSimulation = new JLabel();
	    
	    blankSpace = new JLabel();
	    blankSpace.setForeground(Color.lightGray);
	    blankSpace2 = new JLabel();
	    blankSpace2.setForeground(Color.lightGray);
	    blankSpace3 = new JLabel();
	    blankSpace3.setForeground(Color.lightGray);
	    blankSpace4 = new JLabel();
	    blankSpace4.setForeground(Color.lightGray);
	    blankSpace5 = new JLabel();
	    blankSpace5.setForeground(Color.lightGray);
	    blankSpace6 = new JLabel();
	    blankSpace6.setForeground(Color.lightGray);
	    
	    txtFirst = new JLabel();
	    txtPremium = new JLabel();
	    txtBusiness = new JLabel();
	    txtEconomy = new JLabel();
	    
	    txtRng = new JLabel();
	    txtMean = new JLabel();
	    txtQsize = new JLabel();
	    txtCancellation = new JLabel();
	    
	    Font font1 = new Font("SansSerif", Font.BOLD, 20);
	    Font heading = new Font("SansSerif", Font.BOLD, 22);
	    Border empty = BorderFactory.createEmptyBorder();

	    blankSpace.setText("Empty Space");
	    blankSpace2.setText("Empty Space");
	    blankSpace3.setText("Empty Space");
	    blankSpace4.setText("Empty Space");
	    blankSpace5.setText("Empty Space");
	    blankSpace6.setText("Empty Space");
	    
	    txtOperation.setBackground(Color.gray);
	    txtOperation.setBorder(empty);
	    txtOperation.setFont(heading);
	    txtOperation.setText("Operation");
	    
	    txtFareClasses.setBackground(Color.gray);
	    txtFareClasses.setBorder(empty);
	    txtFareClasses.setFont(heading);
	    txtFareClasses.setText("Fare Classes");

	    txtSimulation.setBackground(Color.gray);
	    txtSimulation.setBorder(empty);
	    txtSimulation.setFont(heading);
	    txtSimulation.setText("Simulation");
	    
	    txtFirst.setBackground(Color.gray);
	    txtFirst.setBorder(empty);
	    txtFirst.setFont(font1);
	    txtFirst.setText("First:");
	    
	    txtBusiness.setBackground(Color.gray);
	    txtBusiness.setBorder(empty);
	    txtBusiness.setFont(font1);
	    txtBusiness.setText("Business:");
	    
	    txtPremium.setBackground(Color.gray);
	    txtPremium.setBorder(empty);
	    txtPremium.setFont(font1);
	    txtPremium.setText("Premium:");
	    
	    txtEconomy.setBackground(Color.gray);
	    txtEconomy.setBorder(empty);
	    txtEconomy.setFont(font1);
	    txtEconomy.setText("Economy:");
	    
	    txtRng.setBackground(Color.gray);
	    txtRng.setBorder(empty);
	    txtRng.setFont(font1);
	    txtRng.setText("RNG Seed:");
	    
	    txtMean.setBackground(Color.gray);
	    txtMean.setBorder(empty);
	    txtMean.setFont(font1);
	    txtMean.setText("Daily Mean:");
	    
	    txtQsize.setBackground(Color.gray);
	    txtQsize.setBorder(empty);
	    txtQsize.setFont(font1);
	    txtQsize.setText("Queue Size:");
	    
	    txtCancellation.setBackground(Color.gray);
	    txtCancellation.setBorder(empty);
	    txtCancellation.setFont(font1);
	    txtCancellation.setText("Cancellation:");
	    
	    //pnlOne.add(txtOutPut);
	    JScrollPane scroll = new JScrollPane(txtOutPut);
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    scroll.setPreferredSize(new Dimension(700, 200));
	    pnlOne.add(scroll);
	    pnlTwo.add(txtOperation);
	    

	    txtInputFirst = new JTextField(10);
	    txtInputBusiness = new JTextField(10);
	    txtInputEconomy = new JTextField(10);
	    txtInputPremium = new JTextField(10);
	    
	    txtInputRng = new JTextField(10);
	    txtInputMean = new JTextField(10);
	    txtInputQsize = new JTextField(10);
	    txtInputCancellation = new JTextField(10);
	    
	    //Default Inputs
	    txtInputFirst.setText("" + Constants.DEFAULT_FIRST_PROB);
	    txtInputBusiness.setText("" + Constants.DEFAULT_BUSINESS_PROB);
	    txtInputEconomy.setText("" + Constants.DEFAULT_ECONOMY_PROB);
	    txtInputPremium.setText("" + Constants.DEFAULT_PREMIUM_PROB);
	    
	    txtInputRng.setText("" + Constants.DEFAULT_SEED);
	    txtInputMean.setText("" + Constants.DEFAULT_DAILY_BOOKING_MEAN);
	    txtInputQsize.setText("" + Constants.DEFAULT_MAX_QUEUE_SIZE);
	    txtInputCancellation.setText("" + Constants.DEFAULT_CANCELLATION_PROB);
	    
	    layoutButtonPanel();
	    
	    c.gridx = 0;
	    c.gridy = 0;
	    pnlFive.add(txtFareClasses, c);
	    c.gridy++;
	    pnlFive.add(blankSpace, c);
	    c.gridy++;
	    pnlFive.add(blankSpace3, c);
	    c.gridy++;
	    pnlFive.add(blankSpace5, c);	
	    c.gridy++;
	    pnlFive.add(txtFirst, c);
	    c.gridy++;
	    pnlFive.add(txtBusiness, c);
	    c.gridy++;
	    pnlFive.add(txtPremium, c);
	    c.gridy++;
	    pnlFive.add(txtEconomy, c);
	    
	    
	    c.gridx = 1;
	    c.gridy = 4;
	    pnlFive.add(txtInputFirst, c);
	    c.gridy++;
	    pnlFive.add(txtInputBusiness, c);
	    c.gridy++;
	    pnlFive.add(txtInputPremium, c);
	    c.gridy++;
	    pnlFive.add(txtInputEconomy, c);
	    
	    c2.gridx = 0;
	    c2.gridy = 0;
	    pnlThree.add(txtSimulation,c2);
	    c2.gridy++;
	    pnlThree.add(blankSpace2, c2);	    
	    c2.gridy++;
	    pnlThree.add(blankSpace4, c2);	
	    c2.gridy++;
	    pnlThree.add(blankSpace6, c2);	
	    c2.gridy++;
	    pnlThree.add(txtRng, c2);
	    c2.gridy++;
	    pnlThree.add(txtMean, c2);
	    c2.gridy++;
	    pnlThree.add(txtQsize, c2);
	    c2.gridy++;
	    pnlThree.add(txtCancellation, c2);
	    
	    
	    c2.gridx = 1;
	    c2.gridy = 4;
	    pnlThree.add(txtInputRng, c2);
	    c2.gridy++;
	    pnlThree.add(txtInputMean, c2);
	    c2.gridy++;
	    pnlThree.add(txtInputQsize, c2);
	    c2.gridy++;
	    pnlThree.add(txtInputCancellation, c2);
	    
	    
	   
	    this.getContentPane().add(pnlOne,BorderLayout.NORTH);
	   // this.getContentPane().add(pnlFour,BorderLayout.LINE_START);
	    this.getContentPane().add(pnlTwo,BorderLayout.EAST);
	    this.getContentPane().add(pnlThree,BorderLayout.WEST);
	    this.getContentPane().add(pnlFive,BorderLayout.CENTER);
	    repaint(); 
	    this.setVisible(true);
	    resetBorders();
	}
	
	private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
	      constraints.gridx = x;
	      constraints.gridy = y;
	      constraints.gridwidth = w;
	      constraints.gridheight = h;
	      jp.add(c, constraints);
	   }

	private JPanel createPanel(Color c) {
		JPanel jp = new JPanel();
		jp.setBackground(c);
		return jp;
	}
	
	private JButton createButton(String str) {
		JButton jb = new JButton(str); 
		jb.addActionListener(this);
		return jb; 
	}
	
	private void layoutButtonPanel(){
	    //add components to grid
	   
	    
	    GridBagConstraints buttons = new GridBagConstraints(); 

	    //Defaults
	    buttons.fill = GridBagConstraints.NONE;
	    buttons.anchor = GridBagConstraints.SOUTHEAST;
	    buttons.weightx = 300;
	    buttons.weighty = 300;
	    
	    addToPanel(pnlTwo, btnRun , buttons,0,0,2,1);
	    addToPanel(pnlTwo, btnShow , buttons,0,2,2,1);
	    btnShow.setEnabled(false);
	   
	   
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		createGUI();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new GUISimulator("BorderLayout"));

	}

	@Override
	public void actionPerformed(ActionEvent c) {
		Object src=c.getSource(); 
		Log l = null; 
		SimulationRunner sr = null;
		Simulator s = null;
		//Consider the alternatives - not all active at once. 
		resetBorders();
		if (src== btnRun) {
			fillInTheBlanks();
			if(checkSimulation()){
				 try {
					l = new Log();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				try { 
					
					 s = new Simulator(Integer.parseInt(txtInputRng.getText()),Integer.parseInt(txtInputQsize.getText()),Double.parseDouble(txtInputMean.getText()),0.33*Double.parseDouble(txtInputMean.getText()),Double.parseDouble(txtInputFirst.getText()),Double.parseDouble(txtInputBusiness.getText()),Double.parseDouble(txtInputPremium.getText()),Double.parseDouble(txtInputEconomy.getText()),Double.parseDouble(txtInputCancellation.getText()));
				} catch (NumberFormatException | SimulationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 SimulationRunner runSIm = new SimulationRunner(s, l);
				 try {
					runSIm.runSimulation(this);
				} catch (AircraftException | PassengerException | SimulationException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 btnShow.setEnabled(true);
			}
		}else if(src == btnShow){
			chart1.pack();
            RefineryUtilities.centerFrameOnScreen(chart1);
            chart1.setVisible(true);
            chart2.pack();
            RefineryUtilities.centerFrameOnScreen(chart2);
            chart2.setVisible(true);
		}
	}
	
	public void addToGUI(String outputString){
		txtOutPut.setText(outputString);
	}
	
	public void addChart(Dataset dataset, int chartNumber){
		if(chartNumber == 1){
			chart1 = new Chart("Chart1: Progress", dataset, chartNumber);
		}else{
			chart2 = new Chart("Chart2: Summary", dataset, chartNumber);
		}
	}
	
	private boolean checkSimulation(){ 
		try{
			 Integer.parseInt(txtInputRng.getText());
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid rng seed input");
			 txtInputRng.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			e.printStackTrace();
			return false;
		}
		 
		 try{
			 Integer.parseInt(txtInputQsize.getText());
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid queue size input");
			 txtQsize.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			e.printStackTrace();
			return false;
		}
		 
		 try{
			 Double.parseDouble(txtInputMean.getText());
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid daily mean input");
			 txtInputMean.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			e.printStackTrace();
			return false;
		}
		 
		 try{
			 Double.parseDouble(txtInputFirst.getText());
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid first input");
			 txtInputFirst.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			e.printStackTrace();
			return false;
		}
		 
		 try{
			 Double.parseDouble(txtInputBusiness.getText());
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid business input");
			 txtInputBusiness.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			e.printStackTrace();
			return false;
		}
		 
		 try{
			 Double.parseDouble(txtInputPremium.getText());
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid premium input");
			 txtPremium.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			e.printStackTrace();
			return false;
		}
		 
		 try{
			 Double.parseDouble(txtInputEconomy.getText());
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid economy input");
			 txtInputEconomy.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			e.printStackTrace();
			return false;
		}
		 
		 try{
			 Double.parseDouble(txtInputCancellation.getText());
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid cancellation input");
			 txtInputCancellation.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			e.printStackTrace();
			return false;
		}

		 return true;		
	}
	
	private void resetBorders(){
		Border border = BorderFactory.createLineBorder(Color.decode("#190707"));
		txtInputRng.setBorder(border);
		txtInputQsize.setBorder(border);
		txtInputMean.setBorder(border);
		txtInputFirst.setBorder(border);
		txtInputBusiness.setBorder(border);
		txtInputPremium.setBorder(border);
		txtInputEconomy.setBorder(border);
		txtInputCancellation.setBorder(border);
	}
	
	private void fillInTheBlanks(){
		if(txtInputRng.getText().isEmpty()){
			txtInputRng.setText(Integer.toString(Constants.DEFAULT_SEED));
		}else if(txtInputQsize.getText().isEmpty()){
			txtInputQsize.setText(Integer.toString(Constants.DEFAULT_MAX_QUEUE_SIZE));
		}else if(txtInputMean.getText().isEmpty()){
			txtInputMean.setText(Double.toString(Constants.DEFAULT_DAILY_BOOKING_MEAN));
		}else if(txtInputFirst.getText().isEmpty()){
			txtInputFirst.setText(Double.toString(Constants.DEFAULT_FIRST_PROB));
		}else if(txtInputPremium.getText().isEmpty()){
			txtInputPremium.setText(Double.toString(Constants.DEFAULT_PREMIUM_PROB));
		}else if(txtInputBusiness.getText().isEmpty()){
			txtInputBusiness.setText(Double.toString(Constants.DEFAULT_BUSINESS_PROB));
		}else if(txtInputEconomy.getText().isEmpty()){
			txtInputEconomy.setText(Double.toString(Constants.DEFAULT_ECONOMY_PROB));
		}else if(txtInputCancellation.getText().isEmpty()){
			txtInputCancellation.setText(Double.toString(Constants.DEFAULT_CANCELLATION_PROB));
		}
	}
}
