/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Passengers.Business;
import asgn2Passengers.First;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;

/**
 * @author Michael Smallcombe
 *
 */
public class BusinessTests {
	/** TEST VARIABLES **/
	private Business testPassenger, testBusinessPassenger;

	/**--------- BUSINESS: SETUP ---------**/
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		int bookingTime = 10, departureTime = 10;
		testPassenger = new Business(bookingTime, departureTime);
	}
	
	/**--------- BUSINESS: CONSTRUCTOR TESTS---------**/
	/**
	 * Test method for {@link asgn2Passengers.Business#Business(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testBusinessIntInt() throws PassengerException {
		int bookingTime = 10, departureTime = 10;
		testBusinessPassenger = new Business(bookingTime, departureTime);
	}

	/**--------- BUSINESS: OTHER TESTS---------**/
	/**
	 * Test method for {@link asgn2Passengers.Business#upgrade()}.
	 */
	@Test
	public void testUpgrade() {
		/*
		 * Verify testPassenger is currently identified as a Business Class
		 * passenger, passID first character 'J'
		 */
		assertEquals(testPassenger.getPassID().charAt(0), 'J');
		Passenger upgradedPassenger = testPassenger.upgrade();
		/*
		 * Verify upgradedPassenger was upgraded to First Class,
		 * passID first character 'F'
		 */
		assertEquals(upgradedPassenger.getPassID().charAt(0), 'F');
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Business#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsg() {
		assertEquals(testPassenger.noSeatsMsg(), "No seats available in Business");
	}
	

}
