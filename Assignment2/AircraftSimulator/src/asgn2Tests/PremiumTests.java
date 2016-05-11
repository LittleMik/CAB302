/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Passengers.Business;
import asgn2Passengers.Passenger;
import asgn2Passengers.Premium;

/**
 * @author Michael Smallcombe
 *
 */
public class PremiumTests {
	/** TEST VARIABLES **/
	private Premium testPassenger;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		int bookingTime = 10, departureTime = 10;
		testPassenger = new Premium(bookingTime, departureTime);
	}
	
	/**--------- PREMIUM TESTS: OTHER ---------**/
	
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
		 * passID first character 'J'
		 */
		assertEquals(upgradedPassenger.getPassID().charAt(0), 'J');
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Premium#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsg() {
		assertEquals(testPassenger.noSeatsMsg(), "No seats available in Premium");
	}
	
	/**--------- PREMIUM TESTS: CONSTRUCTOR ---------**/
	
	/**
	 * Test method for {@link asgn2Passengers.Premium#Premium(int, int)}.
	 */
	@Test
	public void testPremiumIntInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Premium#Premium()}.
	 */
	@Test
	public void testPremium() {
		fail("Not yet implemented");
	}

}
