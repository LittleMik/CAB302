/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Aircraft;


import java.util.ArrayList;
import java.util.List;

import asgn2Passengers.Business;
import asgn2Passengers.Economy;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Premium;
import asgn2Simulators.Log;

/**
 * The <code>Aircraft</code> class provides facilities for modelling a commercial jet 
 * aircraft with multiple travel classes. New aircraft types are created by explicitly 
 * extending this class and providing the necessary configuration information. 
 * 
 * In particular, <code>Aircraft</code> maintains a collection of currently booked passengers, 
 * those with a Confirmed seat on the flight. Queueing and Refused bookings are handled by the 
 * main {@link asgn2Simulators.Simulator} class. 
 *   
 * The class maintains a variety of constraints on passengers, bookings and movement 
 * between travel classes, and relies heavily on the asgn2Passengers hierarchy. Reports are 
 * also provided for logging and graphical display. 
 * 
 * @author hogan
 *
 */
public abstract class Aircraft {

	protected int firstCapacity;
	protected int businessCapacity;
	protected int premiumCapacity;
	protected int economyCapacity;
	protected int capacity;
		
	protected int numFirst;
	protected int numBusiness;
	protected int numPremium; 
	protected int numEconomy; 

	protected String flightCode;
	protected String type; 
	protected int departureTime; 
	protected String status;
	protected List<Passenger> seats;

	/**
	 * Constructor sets flight info and the basic size parameters. 
	 * 
	 * @param flightCode <code>String</code> containing flight ID 
	 * @param departureTime <code>int</code> scheduled departure time
	 * @param first <code>int</code> capacity of First Class 
	 * @param business <code>int</code> capacity of Business Class 
	 * @param premium <code>int</code> capacity of Premium Economy Class 
	 * @param economy <code>int</code> capacity of Economy Class 
	 * @throws AircraftException if isNull(flightCode) OR (departureTime <=0) OR ({first,business,premium,economy} <0)
	 */
	public Aircraft(String flightCode,int departureTime, int first, int business, int premium, int economy) throws AircraftException {
		/*
		 * Check for Exceptions before running
		 * - Check flightCode, departureTime and
		 * the class capacities are valid arguments
		 */
		if(flightCode == null || flightCode.isEmpty()){
			throw new AircraftException("Invalid flight code");
		}else if(departureTime <= 0){
			throw new AircraftException("Invalid departure time");
		}else if(first < 0 || business < 0 || premium < 0 || economy < 0){
			throw new AircraftException("Invalid class value");
		}else{
			//Set Class Capacities and Total Capacity
			this.firstCapacity = first;
			this.businessCapacity = business;
			this.premiumCapacity = premium;
			this.economyCapacity = economy;
			this.capacity = first+business+premium+economy;
			
			//Set Aircraft Details
			this.flightCode = flightCode;
			this.departureTime = departureTime;
			this.status = "";
			
			//Initialize seats list as an arraylist
			this.seats = new ArrayList<Passenger>();
		}
	}
	
	/**
	 * Method to remove passenger from the aircraft - passenger must have a confirmed 
	 * seat prior to entry to this method.   
	 *
	 * @param p <code>Passenger</code> to be removed from the aircraft 
	 * @param cancellationTime <code>int</code> time operation performed 
	 * @throws PassengerException if <code>Passenger</code> is not Confirmed OR cancellationTime 
	 * is invalid. See {@link asgn2Passengers.Passenger#cancelSeat(int)}
	 * @throws AircraftException if <code>Passenger</code> is not recorded in aircraft seating 
	 */
	public void cancelBooking(Passenger p,int cancellationTime) throws PassengerException, AircraftException {
		/*
		 * Check for Exceptions before running
		 * - Check the passenger's state is correct and
		 * cancellationTime is valid
		 */
		if(!p.isConfirmed()){
			//Incorrect Passenger State Exception
			throw new PassengerException("Invalid passenger state");
		}else if(this.departureTime < cancellationTime || cancellationTime < 0){
			//Invalid Cancellation Time
			throw new PassengerException("Invalid cancellation time");		
		}else if(!seats.contains(p)){
			//Passenger not found in aircraft seating
			throw new AircraftException("Passenger not found on flight");				
		}else{
			//Change Passenger State
			p.cancelSeat(cancellationTime);
			
			//Status Message
			this.status += Log.setPassengerMsg(p,"C","N");
			
			//Decrement confirmed count according to passenger's class
			switch(getPassengerClassID(p)){
			case 'F':
				this.numFirst--;
				break;
			case 'J':
				this.numBusiness--;
				break;
			case 'P':
				this.numPremium--;
				break;
			default:
				this.numEconomy--;
				break;
			}
			this.seats.remove(p);
		}
	}

	/**
	 * Method to add a Passenger to the aircraft seating. 
	 * Precondition is a test that a seat is available in the required fare class
	 * 
	 * @param p <code>Passenger</code> to be added to the aircraft 
	 * @param confirmationTime <code>int</code> time operation performed 
	 * @throws PassengerException if <code>Passenger</code> is in incorrect state 
	 * OR confirmationTime OR departureTime is invalid. See {@link asgn2Passengers.Passenger#confirmSeat(int, int)}
	 * @throws AircraftException if no seats available in <code>Passenger</code> fare class. 
	 */
	public void confirmBooking(Passenger p,int confirmationTime) throws AircraftException, PassengerException { 
		/*
		 * Check for Exceptions before running
		 * - Check the passenger's state is correct,
		 * confirmationTime is valid, the aircraft's
		 * departureTime is valid, and that seats are available
		 */
		//Check Correct Passenger State (New or Queued)
		if(p.isNew() || p.isQueued()){
			//Check ConfirmationTime is valid
			if(this.departureTime >= confirmationTime && confirmationTime >= 0){
				//Check DepartureTime is valid
				if(this.departureTime > 0){
					//Check Seats Available on Aircraft
					if(this.seatsAvailable(p)){
						//Change Passenger State
						p.confirmSeat(confirmationTime, this.departureTime);
						
						//Status Message
						this.status += Log.setPassengerMsg(p,"N/Q","C");
						
						//Update confirmed count for given class identifier
						switch(getPassengerClassID(p)){
						case 'F':
							this.numFirst++;
							break;
						case 'J':
							this.numBusiness++;
							break;
						case 'P':
							this.numPremium++;
							break;
						default:
							this.numEconomy++;
							break;
						}
						
						//Add passenger to seats list
						this.seats.add(p);
					}else{
						throw new AircraftException(noSeatsAvailableMsg(p));
					}
				}else{
					throw new PassengerException("Invalid departure time");
				}
			}else{
				throw new PassengerException("Invalid confirmation time");
			}
		}else{
			throw new PassengerException("Invalid passenger state");
		}		
	}
	
	/**
	 * State dump intended for use in logging the final state of the aircraft. (Supplied) 
	 * 
	 * @return <code>String</code> containing dump of final aircraft state 
	 */
	public String finalState() {
		String str = aircraftIDString() + " Pass: " + this.seats.size() + "\n";
		for (Passenger p : this.seats) {
			str += p.toString() + "\n";
		}
		return str + "\n";
	}
	
	/**
	 * Simple status showing whether aircraft is empty
	 * 
	 * @return <code>boolean</code> true if aircraft empty; false otherwise 
	 */
	public boolean flightEmpty() {
		//Check total passenger count is equal to 0 to confirm empty flight
		return this.getNumPassengers() == 0;
	}
	
	/**
	 * Simple status showing whether aircraft is full
	 * 
	 * @return <code>boolean</code> true if aircraft full; false otherwise 
	 */
	public boolean flightFull() {
		//Check total passenger count is at capacity to confirm full flight
		return this.getNumPassengers() == this.capacity;
	}
	
	/**
	 * Method to finalise the aircraft seating on departure. 
	 * Effect is to change the state of each passenger to Flown. 
	 * departureTime parameter allows for rescheduling 
	 * 
	 * @param departureTime <code>int</code> actual departureTime from simulation  
	 * @throws PassengerException if <code>Passenger</code> is in incorrect state 
	 * See {@link asgn2Passengers.Passenger#flyPassenger(int)}. 
	 */
	public void flyPassengers(int departureTime) throws PassengerException { 
		//Iterate through seats array, 'flying' all confirmed passengers
		for(Passenger p: this.seats){
			//Check Correct Passenger State (Confirmed)
			if(p.isConfirmed()){
				//Change confirmed passenger state to flown
				p.flyPassenger(departureTime);
			}else{
				throw new PassengerException("Invalid passenger state");
			}
		}
	}
	
	/**
	 * Method to return an {@link asgn2Aircraft.Bookings} object containing the Confirmed 
	 * booking status for this aircraft. 
	 * 
	 * @return <code>Bookings</code> object containing the status.  
	 */
	public Bookings getBookings() {
		//Get total passenger count and seats available
		int total = this.getNumPassengers();
		int available = this.capacity - total;
		//Create Bookings object with aircraft's variables
		Bookings b = new Bookings(this.numFirst, this.numBusiness, this.numPremium, this.numEconomy, total, available);
		//Return Bookings
		return b;
	}
	
	/**
	 * Simple getter for number of confirmed Business Class passengers
	 * 
	 * @return <code>int</code> number of Business Class passengers 
	 */
	public int getNumBusiness() {
		return this.numBusiness;
	}
	
	
	/**
	 * Simple getter for number of confirmed Economy passengers
	 * 
	 * @return <code>int</code> number of Economy Class passengers 
	 */
	public int getNumEonomy() {
		return this.numEconomy;
	}

	/**
	 * Simple getter for number of confirmed First Class passengers
	 * 
	 * @return <code>int</code> number of First Class passengers 
	 */
	public int getNumFirst() {
		return this.numFirst;
	}

	/**
	 * Simple getter for the total number of confirmed passengers 
	 * 
	 * @return <code>int</code> number of Confirmed passengers 
	 */
	public int getNumPassengers() {
		//Return the total of all current passenger classes
		return this.numEconomy + this.numPremium + this.numBusiness + this.numFirst;
	}
	
	/**
	 * Simple getter for number of confirmed Premium Economy passengers
	 * 
	 * @return <code>int</code> number of Premium Economy Class passengers
	 */
	public int getNumPremium() {
		return this.numPremium;
	}
	
	/**
	 * Method to return an {@link java.util.List} object containing a copy of 
	 * the list of passengers on this aircraft. 
	 * 
	 * @return <code>List<Passenger></code> object containing the passengers.  
	 */
	public List<Passenger> getPassengers() {
		//Return a copy of the seats array
		return new ArrayList<Passenger> (seats);
	}
	
	/**
	 * Method used to provide the current status of the aircraft for logging. (Supplied) 
	 * Uses private status <code>String</code>, set whenever a transition occurs. 
	 *  
	 * @return <code>String</code> containing current aircraft state 
	 */
	public String getStatus(int time) {
		String str = time +"::"
		+ this.seats.size() + "::"
		+ "F:" + this.numFirst + "::J:" + this.numBusiness 
		+ "::P:" + this.numPremium + "::Y:" + this.numEconomy; 
		str += this.status;
		this.status="";
		return str+"\n";
	}
	
	/**
	 * Simple boolean to check whether a passenger is included on the aircraft 
	 * 
	 * @param p <code>Passenger</code> whose presence we are checking
	 * @return <code>boolean</code> true if isConfirmed(p); false otherwise 
	 */
	public boolean hasPassenger(Passenger p) {
		/*
		 * Check the seats array contains the given passenger
		 * and the passenger is in a confirmed state
		 */
		return (this.seats.contains(p) && p.isConfirmed());
	}
	

	/**
	 * State dump intended for logging the aircraft parameters (Supplied) 
	 * 
	 * @return <code>String</code> containing dump of initial aircraft parameters 
	 */ 
	public String initialState() {
		return aircraftIDString() + " Capacity: " + this.capacity 
				+ " [F: " 
				+ this.firstCapacity + " J: " + this.businessCapacity 
				+ " P: " + this.premiumCapacity + " Y: " + this.economyCapacity
				+ "]";
	}
	
	/**
	 * Given a Passenger, method determines whether there are seats available in that 
	 * fare class. 
	 *   
	 * @param p <code>Passenger</code> to be Confirmed
	 * @return <code>boolean</code> true if seats in Class(p); false otherwise
	 */
	public boolean seatsAvailable(Passenger p) {
		/*
		 * Check seats availability according to the
		 * given passenger's class
		 */
		boolean available;
		//Find the passenger's class identifier
		switch(getPassengerClassID(p)){
		case 'F':
			//Check first passengers are less than the first capacity
			available = this.numFirst < this.firstCapacity;
			break;
		case 'J':
			//Check business passengers are less than the business capacity
			available = this.numBusiness < this.businessCapacity;
			break;
		case 'P':
			//Check premium passengers are less than the premium capacity
			available = this.numPremium < this.premiumCapacity;
			break;
		default:
			//Check economy passengers are less than the economy capacity
			available = this.numEconomy < this.economyCapacity;
			break;
		}
		//Return seat availability status
		return available;
	}

	/* 
	 * (non-Javadoc) (Supplied) 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return aircraftIDString() + " Count: " + this.seats.size() 
				+ " [F: " + numFirst
				+ " J: " + numBusiness 
				+ " P: " + numPremium 
				+ " Y: " + numEconomy 
			    + "]";
	}


	/**
	 * Method to upgrade Passengers to try to fill the aircraft seating. 
	 * Called at departureTime. Works through the aircraft fare classes in 
	 * descending order of status. No upgrades are possible from First, so 
	 * we consider Business passengers (upgrading if there is space in First), 
	 * then Premium, upgrading to fill spaces already available and those created 
	 * by upgrades to First), and then finally, we do the same for Economy, upgrading 
	 * where possible to Premium.  
	 * 
	 * See {@link asgn2Passengers.Passenger#upgrade()}
	 */
	public void upgradeBookings() { 
		/*
		 * Each passenger is only upgraded once
		 * Seats index does not change for a given passenger
		 */
		List<Passenger> pass = this.seats;
		
		//Repeat upgrade process for each class from Business->Economy passengers
		int numberOfClasses = 4;
		for(int j = 0; j< numberOfClasses; j++){
			//Upgrade passengers from seats array loop
			for(int i = 0; i < this.seats.size(); i++){
				//Get the current passenger
				Passenger p = pass.get(i);
				//Check if currently processing Business upgrades
				if(j == 0){
					//Check the passenger is of the Business class
					if(p instanceof Business){
						//Check seats available in First
						if(this.seatsAvailable(p.upgrade())){
							//Upgrade Passenger
							this.seats.remove(i);
							p = p.upgrade();
							this.seats.add(i, p);
							this.numFirst++;
							this.numBusiness--;
						}
					}
				
				//Check if currently processing Premium upgrades
				}else if(j == 1){
					//Check the passenger is of the Premium class
					if(p instanceof Premium){
						//Check seats available in Business
						if(this.seatsAvailable(p.upgrade())){
							//Upgrade passenger
							this.seats.remove(i);
							p = p.upgrade();
							this.seats.add(i, p);
							this.numBusiness++;
							this.numPremium--;
						}
					}
				//Check if currently processing Economy upgrades
				}else if(j == 2){
					//Check the passenger is of the Economy Class
					if(p instanceof Economy){
						//Check seats available in Premium
						if(this.seatsAvailable(p.upgrade())){
							//Upgrade passenger
							this.seats.remove(i);
							p = p.upgrade();
							this.seats.add(i, p);
							this.numPremium++;
							this.numEconomy--;
						}
					}
				}
			}
		}
	}

	/**
	 * Simple String method for the Aircraft ID 
	 * 
	 * @return <code>String</code> containing the Aircraft ID 
	 */
	private String aircraftIDString() {
		return this.type + ":" + this.flightCode + ":" + this.departureTime;
	}

	//Various private helper methods to check arguments and throw exceptions, to increment 
	//or decrement counts based on the class of the Passenger, and to get the number of seats 
	//available in a particular class

	//Used in the exception thrown when we can't confirm a passenger 
	/** 
	 * Helper method with error messages for failed bookings
	 * @param p Passenger seeking a confirmed seat
	 * @return msg string failure reason 
	 */
	private String noSeatsAvailableMsg(Passenger p) {
		String msg = "";
		return msg + p.noSeatsMsg(); 
	}
	
	/**
	 * Identifies passenger's classID
	 *
	 * @param p - Passenger
	 * @return <code>char</code> containing the class identifier character (Y, P, J, or F)
	 */
	private char getPassengerClassID(Passenger p){
		/*
		 * ClassID is always the first character
		 * of the passenger's passID
		 */
		char classID = p.getPassID().charAt(0);
		return classID;
	}
}
