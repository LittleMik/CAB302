/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.*;
import javax.swing.border.Border;

import org.jfree.data.xy.XYSeriesCollection;
import asgn2Aircraft.AircraftException;
import asgn2Passengers.PassengerException;


/**
 * The <code>GUISimulator</code> class is used to display 
 * the AircraftSimulator GUI, outputting data collected from 
 * the <code>SimulationRunner</code> class
 * @author hogan
 *
 */

public class GUISimulator extends JFrame implements ActionListener, Runnable {
	private static final long serialVersionUID = -7031008862559936404L;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 200;
	
	/** Default Display Variables **/
	private boolean showGraph = false;
	private boolean showChart1 = true;
	
	/** GUI Elements **/
	private JPanel pnlOne;
	private JPanel pnlTwo;
	private JPanel pnlThree;
	private JPanel pnlFour;
	private JPanel pnlFive;
	
	private JButton btnRun;
	private JButton btnShow;
	private JButton btnSwitch;
	
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
	
	private ChartPanel chartPanel1;
	private ChartPanel chartPanel2;
	private ChartPanel activeChartPanel;
	
	private String[] args;
	
	/**
	 * GUISimulator constructor
	 * @param <code>String</code> - arg0
	 * @param <code>String</code> - args
	 * @throws HeadlessException
	 */
	public GUISimulator(String arg0, String[]args) throws HeadlessException {
		super(arg0);
		this.args = args;
	}	
	
	private void createGUI(String[]args){
		/** GUI Main Setup **/
		setSize(800, 800);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    setResizable(false);
	    
	    /** Panels **/
	    //Setup Panels
	    pnlOne = createPanel(Color.lightGray);
	    pnlTwo = createPanel(Color.lightGray);
	    pnlThree = new JPanel(new GridBagLayout());
	    pnlThree.setBackground(Color.lightGray);
	    pnlFive = new JPanel(new GridBagLayout());
	    pnlFive.setBackground(Color.lightGray);
	    pnlFour = new JPanel(new GridBagLayout());
	    pnlFour.setBackground(Color.lightGray);
	    
	    //Set Dimensions
	    pnlOne.setPreferredSize(new Dimension(800,550));
	    pnlTwo.setPreferredSize(new Dimension(200,250));
	    pnlThree.setPreferredSize(new Dimension(300,250));
	    pnlFive.setPreferredSize(new Dimension(300,250));
	    
	    /** Buttons **/
	    //Setup Buttons
	    btnRun = createButton("Run Simulation");
	    btnShow = createButton("Show Graph");
	    btnSwitch = createButton("Switch Graph");
	    
	    //Set Dimensions
	    btnRun.setPreferredSize(new Dimension(170,37));
	    btnShow.setPreferredSize(new Dimension(170,37));
	    btnSwitch.setPreferredSize(new Dimension(170,37));
	    
	    /** Constraints **/
	    GridBagConstraints c = new GridBagConstraints();
	    GridBagConstraints c2 = new GridBagConstraints();
	    
		/** Output Area **/
	    txtOutPut = new JTextArea();
	    txtOutPut.setLineWrap(true);
	    txtOutPut.setEditable(false);
	    txtOutPut.setVisible(true);
	    	    	    
	    /** BlankSpace **/	    //check this
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
	    
	    blankSpace.setText("Empty Space");
	    blankSpace2.setText("Empty Space");
	    blankSpace3.setText("Empty Space");
	    blankSpace4.setText("Empty Space");
	    blankSpace5.setText("Empty Space");
	    blankSpace6.setText("Empty Space");
	    
	    /** Font Setup **/
	    Font font1 = new Font("SansSerif", Font.BOLD, 20);
	    Font heading = new Font("SansSerif", Font.BOLD, 22);
	    Border empty = BorderFactory.createEmptyBorder();
	    
	    /** Display Text **/
	    //Section Headers
	    txtOperation = new JLabel();
	    txtFareClasses = new JLabel();
	    txtSimulation = new JLabel();
	    
	    //Column 1
	    txtRng = new JLabel();
	    txtMean = new JLabel();
	    txtQsize = new JLabel();
	    txtCancellation = new JLabel();
	    
	    //Column 2
	    txtFirst = new JLabel();
	    txtPremium = new JLabel();
	    txtBusiness = new JLabel();
	    txtEconomy = new JLabel();
	    	    
	    //Formatting
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
	    
	    /** Scroll Pane **/
	    JScrollPane scroll = new JScrollPane(txtOutPut);
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    scroll.setPreferredSize(new Dimension(700, 550));
	    pnlOne.add(scroll);
	    pnlTwo.add(txtOperation);
	    
	    /** Text Inputs */
	    txtInputFirst = new JTextField(10);
	    txtInputBusiness = new JTextField(10);
	    txtInputEconomy = new JTextField(10);
	    txtInputPremium = new JTextField(10);
	    
	    txtInputRng = new JTextField(10);
	    txtInputMean = new JTextField(10);
	    txtInputQsize = new JTextField(10);
	    txtInputCancellation = new JTextField(10);
	    
	    //Default Inputs
	    if(args.length < 9){
		    txtInputFirst.setText("" + Constants.DEFAULT_FIRST_PROB);
		    txtInputBusiness.setText("" + Constants.DEFAULT_BUSINESS_PROB);
		    txtInputEconomy.setText("" + Constants.DEFAULT_ECONOMY_PROB);
		    txtInputPremium.setText("" + Constants.DEFAULT_PREMIUM_PROB);
		    
		    txtInputRng.setText("" + Constants.DEFAULT_SEED);
		    txtInputMean.setText("" + Constants.DEFAULT_DAILY_BOOKING_MEAN);
		    txtInputQsize.setText("" + Constants.DEFAULT_MAX_QUEUE_SIZE);
		    txtInputCancellation.setText("" + Constants.DEFAULT_CANCELLATION_PROB);
	    }else{
	    	txtInputRng.setText("" + args[0]);
	    	txtInputQsize.setText("" + args[1]);
	    	txtInputMean.setText("" + args[2]);
	    	txtInputFirst.setText("" + args[4]);
	    	txtInputBusiness.setText("" + args[5]);
	    	txtInputPremium.setText("" + args[6]);
	    	txtInputEconomy.setText("" + args[7]);
	    	txtInputCancellation.setText("" + args[8]);
	    }
	    	    
	    //Layout Buttons
	    layoutButtonPanel();
	    
	    /** Grid Setup **/
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
	    
	    //Add Panels to GUI
	    this.getContentPane().add(pnlOne,BorderLayout.NORTH);
	    this.getContentPane().add(pnlTwo,BorderLayout.EAST);
	    this.getContentPane().add(pnlThree,BorderLayout.WEST);
	    this.getContentPane().add(pnlFive,BorderLayout.CENTER);
	    //Refresh and output
	    repaint(); 
	    this.setVisible(true);
	    resetBorders();
	}
	
	/**
	 * Add a component to the panel
	 * @param <code>JPanel</code> - jp panel to add to
	 * @param <code>Component</code> - c component to add
	 * @param <code>GridBagConstraints</code> - constraints to add upon adding
	 * @param <code>int</code> - x value for setting grid x
	 * @param <code>int</code> - y value for setting grid y
	 * @param <code>int</code> - w value for setting grid width
	 * @param <code>int</code> - h value for setting grid height
	 */
	private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
	      constraints.gridx = x;
	      constraints.gridy = y;
	      constraints.gridwidth = w;
	      constraints.gridheight = h;
	      jp.add(c, constraints);
	   }
	
	/**
	 * Create a Panel
	 * @param <code>Color</code> c - containing desired background colour
	 * @return <code>JPanel</code> new panel to return
	 */
	private JPanel createPanel(Color c) {
		JPanel jp = new JPanel();
		jp.setBackground(c);
		return jp;
	}
	
	/**
	 * Create a Button
	 * @param <code>String</code> str containing button text
	 * @return <code>JButton</code> new button to return
	 */
	private JButton createButton(String str) {
		JButton jb = new JButton(str); 
		jb.addActionListener(this);
		return jb; 
	}
	
	/**
	 * Layout the button panel
	 */
	private void layoutButtonPanel(){
	    //Add components to grid	    
	    GridBagConstraints buttons = new GridBagConstraints(); 

	    //Defaults
	    buttons.fill = GridBagConstraints.NONE;
	    buttons.anchor = GridBagConstraints.SOUTHEAST;
	    buttons.weightx = 300;
	    buttons.weighty = 300;
	    
	    //Add Components to panel with constraints
	    addToPanel(pnlTwo, btnRun , buttons,0,0,2,1);
	    addToPanel(pnlTwo, btnShow , buttons,0,2,2,1);
	    addToPanel(pnlTwo, btnSwitch, buttons,0,4,2,1);
	    
	    //Set button default states
	    btnShow.setEnabled(false);
	    btnSwitch.setEnabled(false);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		createGUI(this.args);
	}

	/**
	 * Main method
	 * @param <code>String</code> args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new GUISimulator("Aircraft Simulator", args));
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed()
	 */
	@Override
	public void actionPerformed(ActionEvent c) {
		Object src=c.getSource(); 
		Log l = null; 
		SimulationRunner sr = null;
		Simulator s = null;
		//Consider the alternatives - not all active at once. 
		resetBorders();
		
		//Check Button Run Press
		if (src== btnRun) {
			fillInTheBlanks();
			if(checkSimulation()&& checkProbs()){
				try {
					l = new Log();
				} catch (IOException e1) {
					//Output Error
					e1.printStackTrace();
				} 
				try { 
					 s = new Simulator(Integer.parseInt(txtInputRng.getText()),Integer.parseInt(txtInputQsize.getText()),Double.parseDouble(txtInputMean.getText()),0.33*Double.parseDouble(txtInputMean.getText()),Double.parseDouble(txtInputFirst.getText()),Double.parseDouble(txtInputBusiness.getText()),Double.parseDouble(txtInputPremium.getText()),Double.parseDouble(txtInputEconomy.getText()),Double.parseDouble(txtInputCancellation.getText()));
				} catch (NumberFormatException | SimulationException e) {
					//Output Error
					e.printStackTrace();
				}
				SimulationRunner runSIm = new SimulationRunner(s, l);
				try {
					runSIm.runSimulation(this);
				} catch (AircraftException | PassengerException | SimulationException | IOException e) {
					//Output Error
					e.printStackTrace();
				}
				
				//Enable Show Button
				btnShow.setEnabled(true);	 
			}
		
		//Check Button Show Press
		}else if(src == btnShow){
			/*
			 * Toggle Graph Display
			 * Invert showGraph variable and update the GUI to display
			 * a graph if showGraph is true or the logs if showGraph is false
			 */
			//Update showGraphs variable
			showGraph = (showGraph) ? false : true;
			if(showGraph){
				//Show Graph/Hide Logs
				this.getContentPane().remove(pnlOne);
				//Update ChartPanel according to active chart
				activeChartPanel = (showChart1) ? chartPanel1 : chartPanel2;
				this.getContentPane().add(activeChartPanel.getChartPanel(), BorderLayout.NORTH);
				
				//Change Toggle Graph Button Text
				btnShow.setText("Show Log");
				
				//Enable Graph Switching
				btnSwitch.setEnabled(true);
				
				//Disable Run Simulation
				btnRun.setEnabled(false);
			}else{
				//Hide Graphs/Show Logs
				this.getContentPane().remove(activeChartPanel.getChartPanel());
				this.getContentPane().add(pnlOne, BorderLayout.NORTH);
				
				//Change Toggle Graph Button Text
				btnShow.setText("Show Graph");
				
				//Disable Graph Switching
				btnSwitch.setEnabled(false);
				
				//Enable Run Simulation
				btnRun.setEnabled(true);
			}
			//Refresh Contents
			this.revalidate();
			this.repaint();
			
		//Check Button Switch Press
		}else if(src == btnSwitch){
			/*
			 * Toggle Active Chart
			 * If displaying chart1, update chartPanel to show chart2
			 * If displaying chart2, update chartPanel to show chart1
			 */
			//Update showChart1 variable
			showChart1 = (showChart1) ? false : true;
			//Update ChartPanel according to which chart is currently being displayed
			this.getContentPane().remove(activeChartPanel.getChartPanel());
			//Update ChartPanel according to which chart should be displayed
			activeChartPanel = (showChart1) ? chartPanel1 : chartPanel2;
			this.getContentPane().add(activeChartPanel.getChartPanel(), BorderLayout.NORTH);
			//Refresh Contents
			this.revalidate();
			this.repaint();
		}
	}
	
	/**
	 * Add text output result to the GUI
	 * @param outputString - string containing text to push to display 
	 */
	public void addToGUI(String outputString){
		txtOutPut.setText(outputString);
	}
	
	/**
	 * Add charts created from the simulator to the GUI
	 * @param dataset - XYSeriesCollection containing charts data
	 * @param chartNumber - chart number the dataset belongs to
	 */
	public void addChart(int chartNumber, XYSeriesCollection dataset){
		/*
		 * Create chart for the dataset according to the
		 * chart number specified
		 */
		if(chartNumber == 1){
			chartPanel1 = new ChartPanel(chartNumber, dataset);
		}else{
			chartPanel2 = new ChartPanel(chartNumber, dataset);
		}
		activeChartPanel = new ChartPanel();
	}
	
	/**
	 * Check Simulation for Errors
	 * @return <code>boolean</code> - (true) if the simulation contained no errors, (false) if errors were detected
	 */
	private boolean checkSimulation(){ 
		try{
			 if(Integer.parseInt(txtInputRng.getText()) < 0){
				 JOptionPane.showMessageDialog(null,"Invalid rng seed input");
				 txtInputRng.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
				 return false;
			 }
			 
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid rng seed input");
			 txtInputRng.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			return false;
		}
		 
		 try{
			 if(Integer.parseInt(txtInputQsize.getText()) < 0){
				 JOptionPane.showMessageDialog(null,"Invalid queue size input");
				 txtInputQsize.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
				 return false;
			 }
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid queue size input");
			 txtQsize.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			return false;
		}
		 
		 try{
			 if(Double.parseDouble(txtInputMean.getText()) < 0){
				 JOptionPane.showMessageDialog(null,"Invalid mean input");
				 txtInputMean.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
				 return false;
			 }
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid daily mean input");
			 txtInputMean.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			return false;
		}
		 
		 try{
			 if( Double.parseDouble(txtInputFirst.getText()) < 0){
				 JOptionPane.showMessageDialog(null,"Invalid first input");
				 txtInputFirst.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
				 return false;
			 }
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid first input");
			 txtInputFirst.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			return false;
		}
		 
		 try{
			 if(Double.parseDouble(txtInputBusiness.getText()) < 0){
				 JOptionPane.showMessageDialog(null,"Invalid business input");
				 txtInputBusiness.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
				 return false;
			 }
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid business input");
			 txtInputBusiness.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			return false;
		}
		 
		 try{
			 if( Double.parseDouble(txtInputPremium.getText())< 0){
				 JOptionPane.showMessageDialog(null,"Invalid premium input");
				 txtInputPremium.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
				 return false;
			 }
			
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid premium input");
			 txtPremium.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			e.printStackTrace();
			return false;
		}
		 
		 try{
			 if( Double.parseDouble(txtInputEconomy.getText())< 0){
				 JOptionPane.showMessageDialog(null,"Invalid economy input");
				 txtInputEconomy.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
				 return false;
			 }
		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid economy input");
			 txtInputEconomy.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			return false;
		}
		 
		 try{
			 if(Double.parseDouble(txtInputCancellation.getText())< 0){
				 JOptionPane.showMessageDialog(null,"Invalid cancellation probabilty input");
				 txtInputCancellation.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
				 return false;
			 }

		 }catch (NumberFormatException | NullPointerException e) {
			 JOptionPane.showMessageDialog(null,"Invalid cancellation input");
			 txtInputCancellation.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			return false;
		}

		 return true;		
	}
	
	/**
	 * Reset borders of the input fields
	 */
	private void resetBorders(){
		//Setup Border
		Border border = BorderFactory.createLineBorder(Color.decode("#190707"));
		
		//Set borders of all inputs
		txtInputRng.setBorder(border);
		txtInputQsize.setBorder(border);
		txtInputMean.setBorder(border);
		txtInputFirst.setBorder(border);
		txtInputBusiness.setBorder(border);
		txtInputPremium.setBorder(border);
		txtInputEconomy.setBorder(border);
		txtInputCancellation.setBorder(border);
	}
	
	/**
	 * Method to round a double to a certain number of places
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	/**
	 * Check the probabilities of the class are valid
	 * @return <code>boolean</code> - 
	 * (true) if probabilities are valid, 
	 * (false) if the probabilities do not add up to one
	 */
	private boolean checkProbs(){
		/*
		 * Check the sum of all inputs is one
		 * if so return true, otherwise alert user of probability error
		 */
		Double probs = round(Double.parseDouble(txtInputEconomy.getText())+Double.parseDouble(txtInputBusiness.getText())+Double.parseDouble(txtInputPremium.getText())+Double.parseDouble(txtInputFirst.getText()),4);
		if(probs == 1){
			return true;
		}else{
			JOptionPane.showMessageDialog(null,"probabilities need to add to one");
			txtInputFirst.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			txtInputBusiness.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			txtInputPremium.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			txtInputEconomy.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
			return false;
		}
	}
	
	/**
	 * Fill the input box with a default constant value
	 * if no input is detected
	 */
	private void fillInTheBlanks(){
		/*
		 * Check the text field of each input for text
		 * if empty, fill with a default value matching 
		 * the input from Constants class
		 */
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
