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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

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
	
	private JTextField txtOutPut;
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
		    
	    txtOutPut = new JTextField();
	    txtInputFirst = new JTextField(10);
	    txtInputBusiness = new JTextField(10);
	    txtOutPut.setPreferredSize(new Dimension(700,200));
	    
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
	    
	    pnlOne.add(txtOutPut);
	    pnlTwo.add(txtOperation);

		      

	    txtInputFirst = new JTextField(10);
	    txtInputBusiness = new JTextField(10);
	    txtInputEconomy = new JTextField(10);
	    txtInputPremium = new JTextField(10);
	    
	    txtInputRng = new JTextField(10);
	    txtInputMean = new JTextField(10);
	    txtInputQsize = new JTextField(10);
	    txtInputCancellation = new JTextField(10);
	    
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
