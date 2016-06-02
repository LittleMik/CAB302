/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Passengers.Business;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Premium;

/**
 * @author Michael Smallcombe
 *
 */
public class PremiumTests {
	/** TEST VARIABLES **/
	private Premium testPassenger, testPremiumPassenger;
	
	/**--------- PREMIUM: SETUP---------**/
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		int bookingTime = 10, departureTime = 10;
		testPassenger = new Premium(bookingTime, departureTime);
	}
	
	/**--------- PREMIUM: CONSTRUCTOR TESTS---------**/
	/**
	 * Test method for {@link asgn2Passengers.Premium#Premium(int, int)}.
	 * @throws PassengerException
	 */
	@Test
	public void testPremiumIntInt() throws PassengerException {
		int bookingTime = 10, departureTime = 10;
		testPremiumPassenger = new Premium(bookingTime, departureTime);
	}
	
	/**--------- PREMIUM: OTHER TESTS---------**/
	/**
	 * Test method for {@link asgn2Passengers.Premium#upgrade()}.
	 */
	@Test
	public void testUpgrade() {
		/*
		 * Verify testPassenger is currently identified as a Premium Class
		 * passenger, passID first character 'P'
		 */
		assertEquals(testPassenger.getPassID().charAt(0), 'P');
		Passenger upgradedPassenger = testPassenger.upgrade();
		/*
		 * Verify upgradedPassenger was upgraded to Business Class,
		 * passID first character 'J' and passenger object is an
		 * instance of the class to which it was upgraded (Business)
		 */
		assertEquals(upgradedPassenger.getPassID().charAt(0), 'J');
		assertTrue(upgradedPassenger instanceof Business);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Premium#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsg() {
		assertEquals(testPassenger.noSeatsMsg(), "No seats available in Premium");
	}
}
