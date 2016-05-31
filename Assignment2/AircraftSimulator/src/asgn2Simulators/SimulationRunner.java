/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import asgn2Aircraft.AircraftException;
import asgn2Aircraft.Bookings;
import asgn2Passengers.PassengerException;

/**
 * Class to operate the simulation, taking parameters and utility methods from the Simulator
 * to control the available resources, and using Log to provide a record of operation. 
 * 
 * @author hogan
 *
 */ 
public class SimulationRunner {
	/**
	 * Main program for the simulation 
	 * 
	 * @param args Arguments to the simulation - 
	 * see {@link asgn2Simulators.SimulationRunner#printErrorAndExit()}
	 */
	public static void main(String[] args) {
		final int NUM_ARGS = 9; 
		Simulator s = null; 
		Log l = null; 
		
		try {
			switch (args.length) {
				case NUM_ARGS: {
					s = createSimulatorUsingArgs(args); 
					break;
				}
				case 0: {
					s = new Simulator(); 
					break;
				}
				default: {
					printErrorAndExit(); 
				}
			}
			l = new Log();
		} catch (SimulationException | IOException e1) {
			e1.printStackTrace();
			System.exit(-1);
		}

	}
	/**
	 * Helper to process args for Simulator  
	 * 
	 * @param args Command line arguments (see usage message) 
	 * @return new <code>Simulator</code> from the arguments 
	 * @throws SimulationException if invalid arguments. 
	 * See {@link asgn2Simulators.Simulator#Simulator(int, int, double, double, double, double, double, double, double)}
	 */
	private static Simulator createSimulatorUsingArgs(String[] args) throws SimulationException {
		int seed = Integer.parseInt(args[0]);
		int maxQueueSize = Integer.parseInt(args[1]);
		double meanBookings = Double.parseDouble(args[2]);
		double sdBookings = Double.parseDouble(args[3]);
		double firstProb = Double.parseDouble(args[4]);
		double businessProb = Double.parseDouble(args[5]);
		double premiumProb = Double.parseDouble(args[6]);
		double economyProb = Double.parseDouble(args[7]);
		double cancelProb = Double.parseDouble(args[8]);
		return new Simulator(seed,maxQueueSize,meanBookings,sdBookings,firstProb,businessProb,
						  premiumProb,economyProb,cancelProb);	
	}
	
	/**
	 *  Helper to generate usage message 
	 */
	private static void printErrorAndExit() {
		String str = "Usage: java asgn2Simulators.SimulationRunner [SIM Args]\n";
		str += "SIM Args: seed maxQueueSize meanBookings sdBookings "; 
		str += "firstProb businessProb premiumProb economyProb cancelProb\n";
		str += "If no arguments, default values are used\n";
		System.err.println(str);
		System.exit(-1);
	}
	
	
	private Simulator sim;
	private Log log;

	/**
	 * Constructor just does initialisation 
	 * 
	 * @param sim <code>Simulator</code> containing simulation parameters
	 * @param log <code>Log</code> object supporting record of operation 
	 */
	public SimulationRunner(Simulator sim, Log log) {
		this.sim = sim;
		this.log = log;
	}

	/**
	 * Method to run the simulation from start to finish. 
	 * Exceptions are propagated upwards as necessary 
	 * 
	 * @throws AircraftException See methods from {@link asgn2Simulators.Simulator} 
	 * @throws PassengerException See methods from {@link asgn2Simulators.Simulator} 
	 * @throws SimulationException See methods from {@link asgn2Simulators.Simulator} 
	 * @throws IOException on logging failures See methods from {@link asgn2Simulators.Log} 
	 */
	public void runSimulation(GUISimulator gs) throws AircraftException, PassengerException, SimulationException, IOException {
		String outPutString = "";
		
		//Set Chart Numbers
		int chart1 = 1;
		int chart2 = 2;
		
		this.sim.createSchedule();
		this.log.initialEntry(this.sim);

		
		//Setup Chart Datasets
		XYSeriesCollection chart1Dataset = createDataset(chart1);
		XYSeriesCollection chart2Dataset = createDataset(chart2);
		
		String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		outPutString = timeLog + ": Start of Simulation\n" +sim.toString() + "\n" + sim.getFlights(Constants.FIRST_FLIGHT).initialState();
		//Main simulation loop 
		for (int time=0; time<=Constants.DURATION; time++) {
			this.sim.resetStatus(time); 
			this.sim.rebookCancelledPassengers(time); 
			this.sim.generateAndHandleBookings(time);
			this.sim.processNewCancellations(time);
			
			//Update Chart2 Dataset
			updateDataset(chart2, chart2Dataset, null, time);
			
			if (time >= Constants.FIRST_FLIGHT) {
				this.sim.processUpgrades(time);
				this.sim.processQueue(time);
				this.sim.flyPassengers(time);
				this.sim.updateTotalCounts(time); 
				this.log.logFlightEntries(time, sim);
				Flights flights = sim.getFlights(time); 
	
				//outPutString = outPutString + flights.getStatus(time)+"\r\n";
				
				//Update Chart1 Dataset
				Bookings b = this.sim.getFlightStatus(time);
				updateDataset(chart1, chart1Dataset, b, time);
			} else {
				this.sim.processQueue(time);
			}
			//Log progress 
			this.log.logQREntries(time, sim);
			//outPutString = outPutString + sim.getStatus(time);
			this.log.logEntry(time,this.sim);
			boolean flying = (time >= Constants.FIRST_FLIGHT);
			outPutString = outPutString + sim.getSummary(time, flying);
		}
		this.sim.finaliseQueuedAndCancelledPassengers(Constants.DURATION); 
		this.log.logQREntries(Constants.DURATION, sim);
		this.log.finalise(this.sim);
		String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		outPutString = outPutString + "\n" + time + ": End of Simulation\n";
		outPutString = outPutString + sim.finalState();		
		
		//Add Output to GUI
		gs.addToGUI(outPutString);
		
		//Add Charts to GUI
		gs.addChart(chart1Dataset, chart1);
		gs.addChart(chart2Dataset, chart2);
	}
	
	/**
	 * Create an XYSeriesCollection with all series setup
	 * according to the chartNumber specified
	 * @return XYSeriesCollection dataset
	 */
	private XYSeriesCollection createDataset(int chartNumber){
		XYSeriesCollection dataset = new XYSeriesCollection();
		if(chartNumber == 1){
			//Setup Chart1 Dataset
			XYSeries firstTotal = new XYSeries("First");
			XYSeries businessTotal = new XYSeries("Business");
			XYSeries premiumTotal = new XYSeries("Premium");
			XYSeries economyTotal = new XYSeries("Economy");
			XYSeries passengerTotal = new XYSeries("Total");
			XYSeries seatsAvailable = new XYSeries("Seats Available");
			
			dataset.addSeries(firstTotal);
			dataset.addSeries(businessTotal);
			dataset.addSeries(premiumTotal);
			dataset.addSeries(economyTotal);
			dataset.addSeries(passengerTotal);
			dataset.addSeries(seatsAvailable);
		}else{
			//Setup Chart2 Dataset
			XYSeries queueSize = new XYSeries("Queue Size");
			XYSeries passengersRefused = new XYSeries("Passengers Refused");
			
			dataset.addSeries(queueSize);
			dataset.addSeries(passengersRefused);
		}
		return dataset;
	}

	/**
     * Update a dataset's series with new plot points at the specified time
     * according to the chartNumber specified
	 */
	private void updateDataset(int chartNumber, XYSeriesCollection dataset, Bookings b, int time) {
		if(chartNumber == 1){
			XYSeries firstTotal = dataset.getSeries(0);
			XYSeries businessTotal = dataset.getSeries(1);
			XYSeries premiumTotal = dataset.getSeries(2);
			XYSeries economyTotal = dataset.getSeries(3);
			XYSeries passengerTotal = dataset.getSeries(4);
			XYSeries seatsAvailable = dataset.getSeries(5);
			
		    firstTotal.add(time, b.getNumFirst());
		    businessTotal.add(time, b.getNumBusiness());
		    premiumTotal.add(time, b.getNumPremium());
		    economyTotal.add(time, b.getNumEconomy());
		    passengerTotal.add(time, b.getTotal());
		    seatsAvailable.add(time, b.getAvailable());
		}else{
			XYSeries queueSize = dataset.getSeries(0);
			XYSeries passengersRefused = dataset.getSeries(1);
			
			queueSize.add(time, this.sim.numInQueue());
			passengersRefused.add(time, this.sim.numRefused());
		}
	}
}