/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Passengers.Economy;
import asgn2Passengers.Passenger;
import asgn2Passengers.Premium;

/**
 * @author Michael Smallcombe
 *
 */
public class EconomyTests {
	/** TEST VARIABLES **/
	private Economy testPassenger;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		int bookingTime = 10, departureTime = 10;
		testPassenger = new Economy(bookingTime, departureTime);
	}

	/**
	 * Test method for {@link asgn2Passengers.Economy#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsg() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link asgn2Passengers.Economy#upgrade()}.
	 */
	@Test
	public void testUpgrade() {
		/*
		 * Verify testPassenger is currently identified as a Economy Class
		 * passenger, passID first character 'Y'
		 */
		assertEquals(testPassenger.getPassID().charAt(0), 'Y');
		Passenger upgradedPassenger = testPassenger.upgrade();
		/*
		 * Verify upgradedPassenger was upgraded to Premium Class,
		 * passID first character 'P'
		 */
		assertEquals(upgradedPassenger.getPassID().charAt(0), 'P');
	}

	/**
	 * Test method for {@link asgn2Passengers.Economy#Economy(int, int)}.
	 */
	@Test
	public void testEconomy() {
		fail("Not yet implemented");
	}

}
