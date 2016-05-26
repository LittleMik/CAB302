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
	private int departureTime = 4, confirmationTime = 3, cancellationTime = 2, queueTime = 1, bookingTime = 0, refusalTime = 0;
	
	/**--------- FIRST: SETUP ---------**/	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testPassenger = new First(bookingTime, departureTime);
	}

	/**--------- FIRST: CONSTRUCTOR TESTS ---------**/	
	/**
	 * Test method for {@link asgn2Passengers.First#First(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testFirstIntInt() throws PassengerException {
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
		int negativeBookingTime = -1;
		p = new First(negativeBookingTime, departureTime);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#Passenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testPassengerZeroDepartureTime() throws PassengerException {
		int zeroDepartureTime = 0;
		p = new First(bookingTime, zeroDepartureTime);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#Passenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testPassengerNegativeDepartureTime() throws PassengerException {
		int negativeDepartureTime = -1;
		p = new First(bookingTime, negativeDepartureTime);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#Passenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testPassengerBookingTimeGreaterThanDepartureTime() throws PassengerException {
		int greaterBookingTime = 10;
		p = new First(greaterBookingTime, departureTime);
	}
	
	/**--------- PASSENGER: STATE TRANSITION TESTS ---------**/
	
	/**---CancelSeat Tests---**/
	/**Exception Testing
	 * 
	 * PassengerException-
	 * if isNew(this) OR isQueued(this) OR isRefused(this) OR isFlown(this) OR (cancellationTime < 0) OR (departureTime < cancellationTime) 
	 * 
	 */
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testCancelSeatPassengerExceptionIncorrectStateNew() throws PassengerException {
		p = new First(bookingTime, departureTime);
		//Verify New State
		assertTrue(p.isNew());
		
		p.cancelSeat(cancellationTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testCancelSeatPassengerExceptionIncorrectStateQueued() throws PassengerException {
		//Setup Passenger
		p = new First(bookingTime, departureTime);
		p.queuePassenger(queueTime, departureTime);
		//Verify Queue State
		assertTrue(p.isQueued());
		
		p.cancelSeat(cancellationTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testCancelSeatPassengerExceptionIncorrectStateRefused() throws PassengerException {
		//Setup Passenger
		p = new First(bookingTime, departureTime);
		p.refusePassenger(refusalTime);
		//Verify Refused State
		assertTrue(p.isRefused());
		
		p.cancelSeat(cancellationTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testCancelSeatPassengerExceptionIncorrectStateFlown() throws PassengerException {
		//Setup Passenger
		p = new First(bookingTime, departureTime);
		p.confirmSeat(confirmationTime, departureTime);
		p.flyPassenger(departureTime);
		//Verify Flown State
		assertTrue(p.isFlown());
		
		p.cancelSeat(cancellationTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testCancelSeatPassengerExceptionInvalidCancellationTime() throws PassengerException {
		int invalidCancellationTime = -1;
		p = new First(bookingTime, departureTime);
		p.cancelSeat(invalidCancellationTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testCancelSeatPassengerExceptionDepartureTimeLessThanCancellationTime() throws PassengerException {
		int greaterCancellationTime = departureTime + 1;
		p = new First(bookingTime, departureTime);
		p.cancelSeat(greaterCancellationTime);
	}
	
	/**Transition Testing**/
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testCancelSeat() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.confirmSeat(confirmationTime, departureTime);
		//Verify Confirmed State
		assertTrue(p.isConfirmed());
		
		//Test Cancellation
		p.cancelSeat(cancellationTime);
		//Verify State Change
		assertTrue(p.isNew());
	}
	
	/**---ConfirmSeat Tests---**/
	/**Exception Testing
	 * 
	 * PassengerException-
	 * if isConfirmed(this) OR isRefused(this) OR isFlown(this) OR (confirmationTime < 0) OR (departureTime < confirmationTime) 
	 */
	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testConfirmSeatPassengerExceptionIncorrectStateConfirmed() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.confirmSeat(confirmationTime, departureTime);
		//Verify Confirmed State
		assertTrue(p.isConfirmed());
		
		p.confirmSeat(confirmationTime, departureTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testConfirmSeatPassengerExceptionIncorrectStateRefused() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.refusePassenger(refusalTime);
		//Verify Refused State
		assertTrue(p.isRefused());
		
		p.confirmSeat(confirmationTime, departureTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testConfirmSeatPassengerExceptionIncorrectStateFlown() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.confirmSeat(confirmationTime, departureTime);
		p.flyPassenger(departureTime);
		//Verify Flown State
		assertTrue(p.isFlown());
		
		p.confirmSeat(confirmationTime, departureTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testConfirmSeatPassengerExceptionDepartureTimeLessThanConfirmationTime() throws PassengerException {
		//Setup Test Conditions
		int greaterConfirmationTime = departureTime + 1;
		p = new First(bookingTime, departureTime);
		
		p.confirmSeat(greaterConfirmationTime, departureTime);
	}
	
	/**Transition Testing**/
	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testConfirmSeatFromNew() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		//Verify New State
		assertTrue(p.isNew());
		
		//Test Confirmation
		p.confirmSeat(confirmationTime, departureTime);
		//Verify State Change
		assertTrue(p.isConfirmed());
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testConfirmSeatFromQueued() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.queuePassenger(queueTime, departureTime);
		//Verify Queued State
		assertTrue(p.isQueued());
		
		//Test Confirmation
		p.confirmSeat(confirmationTime, departureTime);
		//Verify State Change
		assertTrue(p.isConfirmed());
	}
	
	/**---FlyPassenger Tests---**/
	/**Exception Testing
	 * 
	 * PassengerException-
	 * if isNew(this) OR isQueued(this) OR isRefused(this) OR isFlown(this) OR (departureTime <= 0)
	 * 
	 */
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testFlyPassengerPassengerExceptionIncorrectStateNew() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		//Verify New State
		assertTrue(p.isNew());
		
		p.flyPassenger(departureTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testFlyPassengerPassengerExceptionIncorrectStateQueued() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.queuePassenger(queueTime, departureTime);
		//Verify Queued State
		assertTrue(p.isQueued());
		
		p.flyPassenger(departureTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testFlyPassengerPassengerExceptionIncorrectStateRefused() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.refusePassenger(refusalTime);
		//Verify Refused State
		assertTrue(p.isRefused());
		
		p.flyPassenger(departureTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testFlyPassengerPassengerExceptionIncorrectStateFlown() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.confirmSeat(confirmationTime, departureTime);
		p.flyPassenger(departureTime);
		//Verify Flown State
		assertTrue(p.isFlown());
		
		p.flyPassenger(departureTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testFlyPassengerPassengerExceptionZeroDepartureTime() throws PassengerException {
		//Setup Test Conditions
		int zeroDepartureTime = 0;
		p = new First(bookingTime, departureTime);
		p.confirmSeat(confirmationTime, departureTime);
		//Verify Confirmed State
		assertTrue(p.isConfirmed());
		
		p.flyPassenger(zeroDepartureTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testFlyPassengerPassengerExceptionNegativeDepartureTime() throws PassengerException {
		//Setup Test Conditions
		int negativeDepartureTime = 0;
		p = new First(bookingTime, departureTime);
		p.confirmSeat(confirmationTime, departureTime);
		//Verify Confirmed State
		assertTrue(p.isConfirmed());
		
		p.flyPassenger(negativeDepartureTime);
	}
	
	/**Transition Testing**/
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testFlyPassenger() throws PassengerException {
		p = new First(bookingTime, departureTime);
		p.confirmSeat(confirmationTime, departureTime);
		//Verify Confirmed State
		assertTrue(p.isConfirmed());
		
		//Test Fly Passenger
		p.flyPassenger(departureTime);
		//Verify State Change
		assertTrue(p.isFlown());
		
	}
	
	/**---QueuePassenger Tests---**/
	/**Exception Testing
	 * 
	 * PassengerException-
	 * if isQueued(this) OR isConfirmed(this) OR isRefused(this) OR isFlown(this) OR (queueTime < 0) OR (departureTime < queueTime)
	 * 
	 */
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testQueuePassengerPassengerExceptionIncorrectStateQueued() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.queuePassenger(queueTime, departureTime);
		//Verify Queued State
		assertTrue(p.isQueued());
		
		p.queuePassenger(queueTime, departureTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testQueuePassengerPassengerExceptionIncorrectStateConfirmed() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.confirmSeat(confirmationTime, departureTime);
		//Verify Confirmed State
		assertTrue(p.isConfirmed());
		
		p.queuePassenger(queueTime, departureTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testQueuePassengerPassengerExceptionIncorrectStateRefused() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.refusePassenger(refusalTime);
		//Verify Refused State
		assertTrue(p.isRefused());
		
		p.queuePassenger(queueTime, departureTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testQueuePassengerPassengerExceptionIncorrectStateFlown() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.confirmSeat(confirmationTime, departureTime);
		p.flyPassenger(departureTime);
		//Verify Flown State
		assertTrue(p.isFlown());
		
		p.queuePassenger(queueTime, departureTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testQueuePassengerPassengerExceptionInvalidQueueTime() throws PassengerException {
		//Setup Test Conditions
		int negativeQueueTime = -1;
		p = new First(bookingTime, departureTime);
		//Verify New State
		assertTrue(p.isNew());
		
		p.queuePassenger(negativeQueueTime, departureTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testQueuePassengerPassengerExceptionDepartureTimeLessThanQueueTime() throws PassengerException {
		//Setup Test Conditions
		int greaterQueueTime = departureTime + 1;
		p = new First(bookingTime, departureTime);
		//Verify New State
		assertTrue(p.isNew());
		
		p.queuePassenger(greaterQueueTime, departureTime);
	}
	
	/**Transition Testing**/
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testQueuePassenger() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		//Verify New State
		assertTrue(p.isNew());
		
		//Test Queue Passenger
		p.queuePassenger(queueTime, departureTime);
		//Verify State Change
		assertTrue(p.isQueued());
	}
	
	/**---RefusePassenger Tests---**/
	/**Exception Testing
	 * 
	 * PassengerException-
	 * if isConfirmed(this) OR isRefused(this) OR isFlown(this) OR (refusalTime < 0) OR (refusalTime < bookingTime)
	 * 
	 */
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testRefusePassengerPassengerExceptionIncorrectStateConfirmed() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.confirmSeat(confirmationTime, departureTime);
		//Verify Confirmed State
		assertTrue(p.isConfirmed());
		
		p.refusePassenger(refusalTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testRefusePassengerPassengerExceptionIncorrectStateRefused() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.refusePassenger(refusalTime);
		//Verify Confirmed State
		assertTrue(p.isRefused());
		
		p.refusePassenger(refusalTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testRefusePassengerPassengerExceptionIncorrectStateFlown() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.confirmSeat(confirmationTime, departureTime);
		p.flyPassenger(departureTime);
		//Verify Flown State
		assertTrue(p.isFlown());
		
		p.refusePassenger(refusalTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testRefusePassengerPassengerExceptionNegativeRefusalTime() throws PassengerException {
		//Setup Test Conditions
		int negativeRefusalTime = -1;
		p = new First(bookingTime, departureTime);
		//Verify New State
		assertTrue(p.isNew());
		
		p.refusePassenger(negativeRefusalTime);
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected=PassengerException.class)
	public void testRefusePassengerPassengerExceptionRefusalTimeLessThanBookingTime() throws PassengerException {
		//Setup Test Conditions
		int greaterBookingTime = refusalTime + 1;
		p = new First(greaterBookingTime, departureTime);
		//Verify New State
		assertTrue(p.isNew());
		
		p.refusePassenger(refusalTime);
	}
	
	/**Transition Testing**/
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testRefusePassengerNew() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		//Verify New State
		assertTrue(p.isNew());
		
		//Test Refuse Passenger
		p.refusePassenger(refusalTime);
		//Verify State Change
		assertTrue(p.isRefused());
	}
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testRefusePassengerQueued() throws PassengerException {
		//Setup Test Conditions
		p = new First(bookingTime, departureTime);
		p.queuePassenger(queueTime, departureTime);
		//Verify Queued State
		assertTrue(p.isQueued());
		
		//Test Refuse Passenger
		p.refusePassenger(refusalTime);
		//Verify State Change
		assertTrue(p.isRefused());
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
