package asgn2Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Aircraft.A380;
import asgn2Aircraft.AircraftException;
import asgn2Aircraft.Bookings;
import asgn2Passengers.Business;
import asgn2Passengers.Economy;
import asgn2Passengers.First;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Premium;

public class A380Tests {
	/** TEST VARIABLES **/
	A380 tempFlight;
	Business tempPassenger;
	A380 UnchangedFlight;
	A380 smallFlight;
	
	int passDepartureTime;
	int airDepartureTime;
	int passBookingTime;
	int confirmationTime;
	int cancellationTime;
	int flightTime;
	int refusedTime;
	int negativeVal;
	int zeroVal;
	int queueTime;
	int numberOne;
	String airCode;
	
	/**--------- A380: SETUP ---------**/	
	@Before 
	public void initialize() throws AircraftException, PassengerException {
		airDepartureTime = 20;
		airCode = "1234";
		tempFlight = new A380(airCode, airDepartureTime);
		passDepartureTime = 15;
		passBookingTime = 3;
		confirmationTime = 11;
		cancellationTime  = 12;
		flightTime = 16;
		refusedTime = 14;
		negativeVal = -1;
		queueTime = 13;
		zeroVal = 0;
		numberOne = 1;
		tempPassenger = new Business(passBookingTime,passDepartureTime);
	}
	
	
	/**--------- A380: CONSTRUCTOR TESTS ---------**/	
	/**Exception Testing
	 * 
	 * AircraftException - if isNull(flightCode) OR (departureTime <=0) OR ({first,business,premium,economy} <0)
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#A380(String, int)}.
	 * @throws AircraftException 
	 */
	@Test(expected = AircraftException.class)
	public void EmptyAirCode() throws AircraftException {
		A380 exceptionFlight = new A380("",airDepartureTime);
	}

	/**Exception Testing
	 * 
	 * AircraftException - if isNull(flightCode) OR (departureTime <=0) OR ({first,business,premium,economy} <0)
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#A380(String, int)}.
	 * @throws AircraftException 
	 */
	@Test(expected = AircraftException.class)
	public void NullCode() throws AircraftException {
		A380 exceptionFlight = new A380(null,airDepartureTime);
	}

	/**Exception Testing
	 * 
	 * AircraftException - if isNull(flightCode) OR (departureTime <=0) OR ({first,business,premium,economy} <0)
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#A380(String, int)}.
	 * @throws AircraftException 
	 */
	@Test(expected = AircraftException.class)
	public void ZeroDepTime() throws AircraftException {
		A380 exceptionFlight = new A380(airCode,zeroVal);
	}
	
	/**Exception Testing
	 * 
	 * AircraftException - if isNull(flightCode) OR (departureTime <=0) OR ({first,business,premium,economy} <0)
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#A380(String, int)}.
	 * @throws AircraftException 
	 */
	@Test(expected = AircraftException.class)
	public void LessThenZeroDepTime() throws AircraftException {
		A380 exceptionFlight = new A380(airCode,negativeVal);
	}
	
	/**--------- A380: CANCELBOOKINGS TEST ---------**/	
	/**
	 * Test method for {@link asgn2Aircraft.A380#cancelBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void CancelPassengerPassConfirmed() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		
		tempFlight.cancelBooking(tempPassenger, cancellationTime);	

	}
	
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is not Confirmed OR cancellationTime is invalid. See Passenger.cancelSeat(int)
	 * AircraftException - 
	 * if Passenger is not recorded in aircraft seating
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#cancelBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = PassengerException.class)
	public void CancelPassengerPassFlown() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempPassenger.flyPassenger(flightTime);
		tempFlight.cancelBooking(tempPassenger, cancellationTime);	
	}
	
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is not Confirmed OR cancellationTime is invalid. See Passenger.cancelSeat(int)
	 * AircraftException - 
	 * if Passenger is not recorded in aircraft seating
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#cancelBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = PassengerException.class)
	public void CancelPassengerPassRefused() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempPassenger.refusePassenger(refusedTime);
		tempFlight.cancelBooking(tempPassenger, cancellationTime);	
	}
	
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is not Confirmed OR cancellationTime is invalid. See Passenger.cancelSeat(int)
	 * AircraftException - 
	 * if Passenger is not recorded in aircraft seating
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#cancelBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = PassengerException.class)
	public void CancelPassengerPassQueued() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempPassenger.queuePassenger(queueTime, passDepartureTime);
		tempFlight.cancelBooking(tempPassenger, cancellationTime);	
	}
	
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is not Confirmed OR cancellationTime is invalid. See Passenger.cancelSeat(int)
	 * AircraftException - 
	 * if Passenger is not recorded in aircraft seating
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#cancelBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = PassengerException.class)
	public void CancelDepTimeLessThenConTime() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.cancelBooking(tempPassenger, airDepartureTime+1);	
	}
	
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is not Confirmed OR cancellationTime is invalid. See Passenger.cancelSeat(int)
	 * AircraftException - 
	 * if Passenger is not recorded in aircraft seating
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#cancelBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = PassengerException.class)
	public void CancelConTimeLessThenZero() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.cancelBooking(tempPassenger, negativeVal);	
	}
	
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is not Confirmed OR cancellationTime is invalid. See Passenger.cancelSeat(int)
	 * AircraftException - 
	 * if Passenger is not recorded in aircraft seating
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#cancelBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = AircraftException.class)
	public void CancelPassengerNotInFlight() throws AircraftException, PassengerException {
		//need to set passenger to confirmed or it will throw an exception. To do this we need another flight
		A380 flightToMakePassConfirmed = new A380(airCode, airDepartureTime);
		flightToMakePassConfirmed.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.cancelBooking(tempPassenger, cancellationTime);	
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#cancelBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void CheckBookingActuallyCancelledBusiness() throws AircraftException, PassengerException {
		
		
		First tempFirst = new First(passBookingTime,passDepartureTime);
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		
		tempFlight.confirmBooking(tempEcon, confirmationTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.confirmBooking(tempFirst, confirmationTime);
		
		int beforeconFirst = tempFlight.getNumFirst();
		int beforeconEcon = tempFlight.getNumEonomy();
		int beforeconPrem = tempFlight.getNumPremium();
		
		tempFlight.cancelBooking(tempPassenger, cancellationTime);
		
		assertEquals(tempFlight.getNumBusiness(),0);
		assertEquals(tempFlight.getNumFirst(), beforeconFirst);
		assertEquals(tempFlight.getNumEonomy(), beforeconEcon);
		assertEquals(tempFlight.getNumPremium(), beforeconPrem);
	
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#cancelBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void CheckBookingActuallyCancelledFirst() throws AircraftException, PassengerException {
		
		
		First tempFirst = new First(passBookingTime,passDepartureTime);
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		
		tempFlight.confirmBooking(tempEcon, confirmationTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.confirmBooking(tempFirst, confirmationTime);
		
		int beforeconBusiness = tempFlight.getNumBusiness();
		int beforeconEcon = tempFlight.getNumEonomy();
		int beforeconPrem = tempFlight.getNumPremium();
		
		tempFlight.cancelBooking(tempFirst, cancellationTime);
		
		assertEquals(tempFlight.getNumFirst(),0);
		assertEquals(tempFlight.getNumBusiness(), beforeconBusiness);
		assertEquals(tempFlight.getNumEonomy(), beforeconEcon);
		assertEquals(tempFlight.getNumPremium(), beforeconPrem);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#cancelBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void CheckBookingActuallyCancelledPrem() throws AircraftException, PassengerException {
	
		First tempFirst = new First(passBookingTime,passDepartureTime);
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		
		tempFlight.confirmBooking(tempEcon, confirmationTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.confirmBooking(tempFirst, confirmationTime);
		
		int beforeconFirst = tempFlight.getNumFirst();
		int beforeconEcon = tempFlight.getNumEonomy();
		int beforeconBusiness = tempFlight.getNumPremium();
	
		tempFlight.cancelBooking(tempPrem, cancellationTime);
		
		assertEquals(tempFlight.getNumPremium(),0);
		assertEquals(tempFlight.getNumFirst(), beforeconFirst);
		assertEquals(tempFlight.getNumEonomy(), beforeconEcon);
		assertEquals(tempFlight.getNumBusiness(), beforeconBusiness);
	
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#cancelBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void CheckBookingActuallyCancelledEconomy() throws AircraftException, PassengerException {
		
		First tempFirst = new First(passBookingTime,passDepartureTime);
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		
		tempFlight.confirmBooking(tempEcon, confirmationTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.confirmBooking(tempFirst, confirmationTime);
		
		int beforeconFirst = tempFlight.getNumFirst();
		int beforeconBusiness = tempFlight.getNumBusiness();
		int beforeconPrem = tempFlight.getNumPremium();
		
		tempFlight.cancelBooking(tempEcon, cancellationTime);
		
		assertEquals(tempFlight.getNumEonomy(),0);
		assertEquals(tempFlight.getNumFirst(), beforeconFirst);
		assertEquals(tempFlight.getNumBusiness(), beforeconBusiness);
		assertEquals(tempFlight.getNumPremium(), beforeconPrem);
	
	}
	
	/**--------- A380: CONFIRMBOOKINGS TEST ---------**/	
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is in incorrect state OR confirmationTime OR departureTime is invalid. See Passenger.confirmSeat(int, int)
	 * AircraftException - 
	 * if no seats available in Passenger fare class.
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = PassengerException.class)
	public void ConfirmPassengerAlreadyConfirmed() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempPassenger.confirmSeat(confirmationTime, passDepartureTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);	
	}
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is in incorrect state OR confirmationTime OR departureTime is invalid. See Passenger.confirmSeat(int, int)
	 * AircraftException - 
	 * if no seats available in Passenger fare class.
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = PassengerException.class)
	public void ConfirmPassengerFlown() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempPassenger.flyPassenger(flightTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);	
	}
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is in incorrect state OR confirmationTime OR departureTime is invalid. See Passenger.confirmSeat(int, int)
	 * AircraftException - 
	 * if no seats available in Passenger fare class.
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = PassengerException.class)
	public void ConfirmPassengerRefused() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempPassenger.refusePassenger(19);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);	
	}
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is in incorrect state OR confirmationTime OR departureTime is invalid. See Passenger.confirmSeat(int, int)
	 * AircraftException - 
	 * if no seats available in Passenger fare class.
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = PassengerException.class)
	public void ConfirmDepTimeLessThenConTime() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, airDepartureTime+1);	
	}
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is in incorrect state OR confirmationTime OR departureTime is invalid. See Passenger.confirmSeat(int, int)
	 * AircraftException - 
	 * if no seats available in Passenger fare class.
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = PassengerException.class)
	public void ConfirmConTimeLessThenZero() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, negativeVal);	
	}
	
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is in incorrect state OR confirmationTime OR departureTime is invalid. See Passenger.confirmSeat(int, int)
	 * AircraftException - 
	 * if no seats available in Passenger fare class.
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = PassengerException.class)
	public void ConfirmFullyBooked() throws AircraftException, PassengerException {
		fillThePlane();
		smallFlight.confirmBooking(tempPassenger, confirmationTime);	
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void ConfirmbookingTestCheckingIfFlightEmpty() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.flightEmpty(), false);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void ConfirmbookingTestCheckiFBusinessIncreases() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.getNumBusiness(), 1);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void ConfirmbookingTestCheckiFFirstIncreases() throws AircraftException, PassengerException {
		First tempFirst = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempFirst, confirmationTime);
		assertEquals(tempFlight.getNumFirst(), 1);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void ConfirmbookingTestCheckiFPremIncreases() throws AircraftException, PassengerException {
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		assertEquals(tempFlight.getNumPremium(), 1);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void ConfirmbookingTestCheckiFEconIncreases() throws AircraftException, PassengerException {
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempEcon, confirmationTime);
		assertEquals(tempFlight.getNumEonomy(), 1);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void ConfirmbookingTestCheckiFBusinessIncreasesButNothingElse() throws AircraftException, PassengerException {
		int beforeconFirst = tempFlight.getNumFirst();
		int beforeconEcon = tempFlight.getNumEonomy();
		int beforeconPrem = tempFlight.getNumPassengers();
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.getNumBusiness(), 1);
		assertEquals(tempFlight.getNumFirst(), beforeconFirst);
		assertEquals(tempFlight.getNumEonomy(), beforeconEcon);
		assertEquals(tempFlight.getNumPremium(), beforeconPrem);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void ConfirmbookingTestCheckiFFirstIncreasesButNothingElse() throws AircraftException, PassengerException {
		First tempFirst = new First(passBookingTime,passDepartureTime);
		int beforeconBusiness = tempFlight.getNumBusiness();
		int beforeconEcon = tempFlight.getNumEonomy();
		int beforeconPrem = tempFlight.getNumPassengers();
		tempFlight.confirmBooking(tempFirst, confirmationTime);
		assertEquals(tempFlight.getNumFirst(), 1);
		assertEquals(tempFlight.getNumBusiness(), beforeconBusiness);
		assertEquals(tempFlight.getNumEonomy(), beforeconEcon);
		assertEquals(tempFlight.getNumPremium(), beforeconPrem);
	
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void ConfirmbookingTestCheckiFPremIncreasesButNothingElse() throws AircraftException, PassengerException {
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		int beforeconBusiness = tempFlight.getNumBusiness();
		int beforeconEcon = tempFlight.getNumEonomy();
		int beforeconFirst = tempFlight.getNumFirst();
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		assertEquals(tempFlight.getNumPremium(), 1);
		assertEquals(tempFlight.getNumBusiness(), beforeconBusiness);
		assertEquals(tempFlight.getNumEonomy(), beforeconEcon);
		assertEquals(tempFlight.getNumFirst(), beforeconFirst);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void ConfirmbookingTestCheckiFEconIncreasesButNothingElse() throws AircraftException, PassengerException {
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		int beforeconBusiness = tempFlight.getNumBusiness();
		int beforeconPremium = tempFlight.getNumEonomy();
		int beforeconFirst = tempFlight.getNumFirst();
		tempFlight.confirmBooking(tempEcon, confirmationTime);
		assertEquals(tempFlight.getNumEonomy(), 1);
		assertEquals(tempFlight.getNumBusiness(), beforeconBusiness);
		assertEquals(tempFlight.getNumPremium(), beforeconPremium);
		assertEquals(tempFlight.getNumFirst(), beforeconFirst);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#confrimBooking(Passenger, int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void ConfirmbookingTestCheckingIfBusinessCountChanged() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		UnchangedFlight = new A380(airCode, airDepartureTime);
		assertEquals(tempFlight.getNumBusiness(),UnchangedFlight.getNumBusiness()+1 );
	}
	
	/**--------- A380: FLIGHTEMPTY TEST ---------**/	
	/**
	 * Test method for {@link asgn2Aircraft.A380#flightEmpty()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void flightEmptyTests() throws AircraftException, PassengerException {
		assertEquals(tempFlight.flightEmpty(), true );
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#flightEmpty()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void flightNotEmptyTests() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.flightEmpty(), false );
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#flightEmpty()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void flightIsFullTestingEmptyTests() throws AircraftException, PassengerException {
		fillThePlane();
		assertEquals(smallFlight.flightEmpty(), false );
	}
	
	/**--------- A380: FLIGHTFULL TEST ---------**/	
	/**
	 * Test method for {@link asgn2Aircraft.A380#flightFull()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void fullPlane() throws AircraftException, PassengerException{
		fillThePlane();
		assertEquals(smallFlight.flightFull(), true);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#flightFull()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void NotfullPlane() throws AircraftException, PassengerException{
		assertEquals(tempFlight.flightFull(), false);
	}
		
	/**
	 * Test method for {@link asgn2Aircraft.A380#flightFull()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void fullPlaneWithOneBooking() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.flightFull(), false);
	}
	
	/**--------- A380: FLYPASSENGERS TEST ---------**/	
	/**
	 * Test method for {@link asgn2Aircraft.A380#flyPassengers(int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void flightPassengersTests() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.flyPassengers(flightTime);
		assertEquals(tempPassenger.isFlown(), true);
	}
	/**
	 * Test method for {@link asgn2Aircraft.A380#flyPassengers(int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void flightPassengersOfAllClassesTests() throws AircraftException, PassengerException {	
		First tempFirst = new First(passBookingTime,passDepartureTime);
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		tempFlight.confirmBooking(tempEcon, confirmationTime);
		tempFlight.confirmBooking(tempFirst, cancellationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.flyPassengers(flightTime);
		assertEquals(tempPassenger.isFlown(), true);
		assertEquals(tempEcon.isFlown(), true);
		assertEquals(tempFirst.isFlown(), true);
		assertEquals(tempPrem.isFlown(), true);
		
	}
	
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is in incorrect state See Passenger.flyPassenger(int).
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#flyPassengers(int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = PassengerException.class)
	public void flightPassengersThrowsExceptionOneQueued() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		First firstClassPass = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(firstClassPass, confirmationTime);
		firstClassPass.queuePassenger(passBookingTime,passDepartureTime);
		tempFlight.flyPassengers(flightTime);
	}
	
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is in incorrect state See Passenger.flyPassenger(int).
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#flyPassengers(int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = PassengerException.class)
	public void flightPassengersThrowsExceptionOneRefused() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		First firstClassPass = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(firstClassPass, confirmationTime);
		firstClassPass.refusePassenger(refusedTime);
		tempFlight.flyPassengers(flightTime);
	}
	
	/**Exception Testing
	 * 
	 * PassengerException - 
	 * if Passenger is in incorrect state See Passenger.flyPassenger(int).
	 * 
	 */
	/**
	 * Test method for {@link asgn2Aircraft.A380#flyPassengers(int)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test(expected = PassengerException.class)
	public void flightPassengersThrowsExceptionOneFlown() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		First firstClassPass = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(firstClassPass, confirmationTime);
		firstClassPass.flyPassenger(flightTime);
		tempFlight.flyPassengers(flightTime);
	}
	

	/**--------- A380: GETBOOKINGS TEST ---------**/	
	/**
	 * Test method for {@link asgn2Aircraft.A380#getBookings()}.
	 * @throws AircraftException
	 */	
	@Test
	public void getBookingsTestWithNoBooking() throws AircraftException{
		assertEquals(tempFlight.getBookings().getNumBusiness(), 0);
		assertEquals(tempFlight.getBookings().getNumEconomy(), 0);
		assertEquals(tempFlight.getBookings().getNumFirst(), 0);
		assertEquals(tempFlight.getBookings().getNumPremium(), 0);
		assertEquals(tempFlight.getBookings().getTotal(), 0);
		assertEquals(tempFlight.getBookings().getAvailable(), 484);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#getBookings()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void getBookingsTestWithOneBookingBusiness() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.getBookings().getNumBusiness(), 1);
		assertEquals(tempFlight.getBookings().getNumEconomy(), 0);
		assertEquals(tempFlight.getBookings().getNumFirst(), 0);
		assertEquals(tempFlight.getBookings().getNumPremium(), 0);
		assertEquals(tempFlight.getBookings().getTotal(), 1);
		assertEquals(tempFlight.getBookings().getAvailable(), 483);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#getBookings()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void getBookingsTestWithOneBookingFirst() throws AircraftException, PassengerException{
		First tempFirst = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempFirst, confirmationTime);
		assertEquals(tempFlight.getBookings().getNumBusiness(), 0);
		assertEquals(tempFlight.getBookings().getNumEconomy(), 0);
		assertEquals(tempFlight.getBookings().getNumFirst(), 1);
		assertEquals(tempFlight.getBookings().getNumPremium(), 0);
		assertEquals(tempFlight.getBookings().getTotal(), 1);
		assertEquals(tempFlight.getBookings().getAvailable(), 483);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#getBookings()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void getBookingsTestWithOneBookingPremium() throws AircraftException, PassengerException{
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		assertEquals(tempFlight.getBookings().getNumBusiness(), 0);
		assertEquals(tempFlight.getBookings().getNumEconomy(), 0);
		assertEquals(tempFlight.getBookings().getNumFirst(), 0);
		assertEquals(tempFlight.getBookings().getNumPremium(), 1);
		assertEquals(tempFlight.getBookings().getTotal(), 1);
		assertEquals(tempFlight.getBookings().getAvailable(), 483);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#getBookings()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void getBookingsTestWithOneBookingEconomy() throws AircraftException, PassengerException{
		Economy tempEco = new Economy(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempEco,confirmationTime);
		assertEquals(tempFlight.getBookings().getNumBusiness(), 0);
		assertEquals(tempFlight.getBookings().getNumEconomy(), 1);
		assertEquals(tempFlight.getBookings().getNumFirst(), 0);
		assertEquals(tempFlight.getBookings().getNumPremium(), 0);
		assertEquals(tempFlight.getBookings().getTotal(), 1);
		assertEquals(tempFlight.getBookings().getAvailable(), 483);
	}

	/**--------- A380: GETPASSENGERS TEST ---------**/	
	/**
	 * Test method for {@link asgn2Aircraft.A380#getPassengers()}.
	 * @throws AircraftException, PassengerException
	 */	
	@Test
	public void testPassengerListOnePassenger() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.getPassengers().get(0).toString(), tempPassenger.toString());
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#getPassengers()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void testPassengerListTwoPassengers() throws AircraftException, PassengerException{
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		tempPrem.queuePassenger(queueTime, passDepartureTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.getPassengers().get(0).toString()+tempFlight.getPassengers().get(1).toString(), tempPrem.toString()+tempPassenger.toString());
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#getPassengers()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void testPassengerListNoPassengers() throws AircraftException, PassengerException{
		assertEquals(tempFlight.getPassengers().isEmpty(), true);
	}
	
	/**--------- A380: HASPASSENGER TEST ---------**/	
	/**
	 * Test method for {@link asgn2Aircraft.A380#hasPassenger(Passenger)}.
	 * @throws AircraftException, PassengerException
	 */	
	@Test
	public void hasPassengerTestWithOnePassengerBusiness() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempPassenger),true);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#hasPassenger(Passenger)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void hasPassengerTestWithOnePassengerFirst() throws AircraftException, PassengerException{
		First tempFirst = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempFirst, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempFirst),true);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#hasPassenger(Passenger)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void hasPassengerTestWithOnePassengerPremium() throws AircraftException, PassengerException{
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempPrem),true);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#hasPassenger(Passenger)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void hasPassengerTestWithOnePassengerEconomy() throws AircraftException, PassengerException{
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempEcon, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempEcon),true);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#hasPassenger(Passenger)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void hasPassengerTestNopassengers() throws AircraftException, PassengerException{
		assertEquals(tempFlight.hasPassenger(tempPassenger),false);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#hasPassenger(Passenger)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void hasPassengerTestOnepassengerButNotTheOneWeWantBusiness() throws AircraftException, PassengerException{
		Business tempBusiness = new Business(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempBusiness, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempPassenger),false);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#hasPassenger(Passenger)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void hasPassengerTestOnepassengerButNotTheOneWeWantFirst() throws AircraftException, PassengerException{
		First tempFirstOne = new First(passBookingTime,passDepartureTime);
		First tempFirstTwo = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempFirstOne, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempFirstTwo),false);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#hasPassenger(Passenger)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void hasPassengerTestOnepassengerButNotTheOneWeWantPremium() throws AircraftException, PassengerException{
		Premium tempPremOne = new Premium(passBookingTime,passDepartureTime);
		Premium tempPremTwo = new Premium(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempPremOne, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempPremTwo),false);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#hasPassenger(Passenger)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void hasPassengerTestOnepassengerButNotTheOneWeWantEconomy() throws AircraftException, PassengerException{
		Economy tempEconOne = new Economy(passBookingTime,passDepartureTime);
		Economy tempEconTwo = new Economy(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempEconOne, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempEconTwo),false);
	}

	
	/**--------- A380: SEATSAVAILABLE TEST ---------**/	
	/**
	 * Test method for {@link asgn2Aircraft.A380#seatsAvailable(Passenger)}.
	 * @throws AircraftException,PassengerException
	 */
	@Test
	public void seatsAvailableEmptyPlaneTest(){
		assertEquals(tempFlight.seatsAvailable(tempPassenger),true);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#seatsAvailable(Passenger)}.
	 * @throws AircraftException,PassengerException
	 */
	@Test
	public void seatsAvailableFullPlaneTestTryAndSitBusiness() throws AircraftException, PassengerException{
		fillThePlane();
		assertEquals(smallFlight.seatsAvailable(tempPassenger),false);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#seatsAvailable(Passenger)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void seatsAvailableFullPlaneTestTryAndSitFirst() throws AircraftException, PassengerException{
		fillThePlane();
		First tempFirst = new First(passBookingTime,passDepartureTime);
		assertEquals(smallFlight.seatsAvailable(tempFirst),false);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#seatsAvailable(Passenger)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void seatsAvailableFullPlaneTestTryAndSitPremium() throws AircraftException, PassengerException{
		fillThePlane();
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		assertEquals(smallFlight.seatsAvailable(tempPrem),false);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#seatsAvailable(Passenger)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void seatsAvailableFullPlaneTestTryAndSitEcon() throws AircraftException, PassengerException{
		fillThePlane();
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		assertEquals(smallFlight.seatsAvailable(tempEcon),false);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#seatsAvailable(Passenger)}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void seatsAvailableOneCustomerAddedTest() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.seatsAvailable(tempPassenger),true);
	}
	
	/**--------- A380: UPGRADEBOOKINGS TEST ---------**/	
	/**
	 * Test method for {@link asgn2Aircraft.A380#upgradeBookings()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void upgradeBookingsSingleBusiness() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof First);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#upgradeBookings()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void upgradeBookingsEconomy() throws AircraftException, PassengerException{
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempEcon, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof Premium);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#upgradeBookings()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void upgradeBookingsPremium() throws AircraftException, PassengerException{
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof Business);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#upgradeBookings()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void upgradeBookingsFirst() throws AircraftException, PassengerException{
		First tempFirst = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempFirst, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof First);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#upgradeBookings()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void upgradeBookingsBusinessFirst() throws AircraftException, PassengerException{
		First tempFirst = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempFirst, confirmationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof First);
		assertTrue(tempFlight.getPassengers().get(1) instanceof First);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#upgradeBookings()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void upgradeBookingsBusinessEconomy() throws AircraftException, PassengerException{
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempEcon, confirmationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof Premium);
		assertTrue(tempFlight.getPassengers().get(1) instanceof First);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#upgradeBookings()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void upgradeBookingsBusinessPremium() throws AircraftException, PassengerException{
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof Business);
		assertTrue(tempFlight.getPassengers().get(1) instanceof First);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#upgradeBookings()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void upgradeBookingsBusinessBusiness() throws AircraftException, PassengerException{
		Business tempBusiness = new Business(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempBusiness, confirmationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof First);
		assertTrue(tempFlight.getPassengers().get(1) instanceof First);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#upgradeBookings()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void upgradeBookingsBusinessWhenFirstIsFull() throws AircraftException, PassengerException{
		A380 testFlight = new A380(airCode,airDepartureTime, numberOne, numberOne+1, numberOne,numberOne);
		Business tempBusiness = new Business(passBookingTime,passDepartureTime);
		testFlight.confirmBooking(tempBusiness, confirmationTime);
		testFlight.confirmBooking(tempPassenger, confirmationTime);
		testFlight.upgradeBookings();
		assertTrue(testFlight.getPassengers().get(0) instanceof First);
		assertTrue(testFlight.getPassengers().get(1) instanceof Business);
	}
	
	/**
	 * Test method for {@link asgn2Aircraft.A380#upgradeBookings()}.
	 * @throws AircraftException, PassengerException
	 */
	@Test
	public void upgradeBookingsBusinessWhenFirstIsFullThenWithMoreBusiness() throws AircraftException, PassengerException{
		A380 testFlight = new A380(airCode,airDepartureTime, 3*numberOne, 3*numberOne, 3*numberOne,3*numberOne);
		Business tempBusiness = new Business(passBookingTime,passDepartureTime);
		Business tempBusiness2 = new Business(passBookingTime,passDepartureTime);
		
		Premium tempPrem = new Premium(passBookingTime, passDepartureTime);
		Premium tempPrem2 = new Premium(passBookingTime, passDepartureTime);
		Premium tempPrem3 = new Premium(passBookingTime, passDepartureTime);
		
		testFlight.confirmBooking(tempBusiness, confirmationTime);
		testFlight.confirmBooking(tempBusiness2, confirmationTime);
		testFlight.confirmBooking(tempPrem, confirmationTime);
		testFlight.confirmBooking(tempPrem2, confirmationTime);
		testFlight.confirmBooking(tempPrem3, confirmationTime);
		testFlight.confirmBooking(tempPassenger, confirmationTime);
		testFlight.upgradeBookings();
		assertTrue(testFlight.getPassengers().get(0) instanceof First);
		assertTrue(testFlight.getPassengers().get(1) instanceof First);
		assertTrue(testFlight.getPassengers().get(2) instanceof Business);
		assertTrue(testFlight.getPassengers().get(3) instanceof Business);
		assertTrue(testFlight.getPassengers().get(4) instanceof Business);
		assertTrue(testFlight.getPassengers().get(5) instanceof First);
		
	}
	
	
	/**
	 * Private helper method to make create a small plane that is full
	 */
	private void fillThePlane() throws AircraftException, PassengerException{
		smallFlight = new A380(airCode,airDepartureTime,numberOne,numberOne,numberOne,numberOne);
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		First tempFirst = new First(passBookingTime,passDepartureTime);
		smallFlight.confirmBooking(tempPassenger, confirmationTime);
		smallFlight.confirmBooking(tempEcon, confirmationTime);
		smallFlight.confirmBooking(tempPrem, confirmationTime);
		smallFlight.confirmBooking(tempFirst, confirmationTime);
	}

}