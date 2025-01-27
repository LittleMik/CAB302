/**
 * 
 */
package asgn2Passengers;

/**
 * @author hogan
 *
 */
public class Premium extends Passenger {

	/**
	 * Premium Constructor (Partially Supplied)
	 * Passenger is created in New state, later given a Confirmed Premium Class reservation, 
	 * Queued, or Refused booking if waiting list is full. 
	 * 
	 * @param bookingTime <code>int</code> day of the original booking. 
	 * @param departureTime <code>int</code> day of the intended flight.  
	 * @throws PassengerException if invalid bookingTime or departureTime 
	 * @see asgnPassengers.Passenger#Passenger(int,int)
	 */
	public Premium(int bookingTime,int departureTime) throws PassengerException {
		super(bookingTime,departureTime);
		this.passID = "P:" + this.passID;
	}
	
	/**
	 * Simple constructor to support {@link asgn2Passengers.Passenger#upgrade()} in other subclasses
	 */
	protected Premium() {
		
	}

	/**
	 * upgrade passenger by copying the current properties and returning them as a passenger of the class above.
	 * In this case it returns a passenger of the type Business.
	 */
	@Override
	public Passenger upgrade() {
		Passenger newPass = new Business();
		newPass.copyPassengerState(this);
		newPass.passID = "J:" + this.passID;
		return newPass;
	}
	
	@Override
	public String noSeatsMsg() {
		return "No seats available in Premium";
	}
}