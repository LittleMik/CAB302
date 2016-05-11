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
	private First testPassenger;
	
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
	 */
	@Test
	public void testFirstIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.First#First()}.
	 */
	@Test
	public void testFirst() {
		fail("Not yet implemented");
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

	/**--------- PASSENGER TESTS: CONSTRUCTOR ---------**/
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#Passenger(int, int)}.
	 */
	@Test
	public void testPassengerIntInt() {
		fail("Not yet implemented");
	}
	
	/**--- Constructor Exception Tests ---**/
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#Passenger()}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testPassengerNegativeBookingTime() throws PassengerException {
		int bookingTime = -1, departureTime = 10;
		p = new First(bookingTime, departureTime);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#Passenger()}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testPassengerZeroDepartureTime() throws PassengerException {
		int bookingTime = 0, departureTime = 0;
		p = new First(bookingTime, departureTime);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#Passenger()}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testPassengerNegativeDepartureTime() throws PassengerException {
		int bookingTime = 0, departureTime = -1;
		p = new First(bookingTime, departureTime);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#Passenger()}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testPassengerBookingTimeGreaterThanDepartureTime() throws PassengerException {
		int bookingTime = 10, departureTime = 5;
		p = new First(bookingTime, departureTime);
	}
	
	/**--------- PASSENGER TESTS: STATE TRANSITION TESTS ---------**/

	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 */
	@Test
	public void testCancelSeat() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 */
	@Test
	public void testConfirmSeat() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 */
	@Test
	public void testFlyPassenger() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 */
	@Test
	public void testQueuePassenger() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 */
	@Test
	public void testRefusePassenger() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Passenger#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

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

	/**
	 * Test method for {@link asgn2Passengers.Passenger#copyPassengerState(asgn2Passengers.Passenger)}.
	 */
	@Test
	public void testCopyPassengerState() {
		fail("Not yet implemented");
	}

}
