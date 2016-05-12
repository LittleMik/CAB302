/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Passengers.First;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;

/**
 * @author Michael Smallcombe
 *
 */
public class FirstTests {
	/** TEST VARIABLES **/
	private Passenger p;
	private First testPassenger, testFirstPassenger;
	
	/**--------- FIRST: SETUP ---------**/	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		int bookingTime = 10, departureTime = 10;
		testPassenger = new First(bookingTime, departureTime);
	}

	/**--------- FIRST: CONSTRUCTOR TESTS ---------**/	
	/**
	 * Test method for {@link asgn2Passengers.First#First(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testFirstIntInt() throws PassengerException {
		int bookingTime = 10, departureTime = 10;
		testFirstPassenger = new First(bookingTime, departureTime);
	}
	
	/**--------- FIRST: OTHER TESTS ---------**/	
	/**
	 * Test method for {@link asgn2Passengers.First#upgrade()}.
	 */
	@Test
	public void testUpgrade() {
		Passenger upgradedPassenger = testPassenger.upgrade();
		//Verify first character of the testPassenger ID identifies First Class ('F') and do not change
		assertEquals(testPassenger.getPassID().charAt(0), upgradedPassenger.getPassID().charAt(0));
	}
	
	//Maybe I should remove this seeing it's practically a getter?
	/**
	 * Test method for {@link asgn2Passengers.First#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsg() {
		assertEquals(testPassenger.noSeatsMsg(), "No seats available in First");
	}

	/**--------- PASSENGER: CONSTRUCTOR TESTS---------**/	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#Passenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testPassengerNegativeBookingTime() throws PassengerException {
		int bookingTime = -1, departureTime = 10;
		p = new First(bookingTime, departureTime);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#Passenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testPassengerZeroDepartureTime() throws PassengerException {
		int bookingTime = 0, departureTime = 0;
		p = new First(bookingTime, departureTime);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#Passenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testPassengerNegativeDepartureTime() throws PassengerException {
		int bookingTime = 0, departureTime = -1;
		p = new First(bookingTime, departureTime);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#Passenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testPassengerBookingTimeGreaterThanDepartureTime() throws PassengerException {
		int bookingTime = 10, departureTime = 5;
		p = new First(bookingTime, departureTime);
	}
	
	/**--------- PASSENGER: STATE TRANSITION TESTS ---------**/
	/**---CancelSeat Tests---**/
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 */
	@Test
	public void testCancelSeat() {
		fail("Not yet implemented");
	}
	
	/**---ConfirmSeat Tests---**/
	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 */
	@Test
	public void testConfirmSeat() {
		fail("Not yet implemented");
	}
	
	/**---FlyPassenger Tests---**/
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 */
	@Test
	public void testFlyPassenger() {
		fail("Not yet implemented");
	}
	
	/**---QueuePassenger Tests---**/
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 */
	@Test
	public void testQueuePassenger() {
		fail("Not yet implemented");
	}
	
	/**---RefusePassenger Tests---**/
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 */
	@Test
	public void testRefusePassenger() {
		fail("Not yet implemented");
	}
	
	/**--------- PASSENGER: OTHER TESTS ---------**/
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasConfirmed()}.
	 */
	@Test
	public void testWasConfirmed() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasQueued()}.
	 */
	@Test
	public void testWasQueued() {
		fail("Not yet implemented");
	}
}
