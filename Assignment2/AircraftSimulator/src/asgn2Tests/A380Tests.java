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
	//Define some of the variables that will be used throughout the tests
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
	
	//Set up an iniital flight and an initial passenger
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
	
	
	
	//Testing constructor by checking class is made and that it is initially empty
	@Test
	public void flightEmptyTests() throws AircraftException, PassengerException {
		assertEquals(tempFlight.flightEmpty(), true );
	}
	
	@Test
	public void flightNotEmptyTests() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.flightEmpty(), false );
	}
	
	//Testing constructor throws exceptions
	@Test(expected = AircraftException.class)
	public void EmptyCode() throws AircraftException {
		A380 exceptionFlight = new A380("",airDepartureTime);
	}
	
	@Test(expected = AircraftException.class)
	public void NullCode() throws AircraftException {
		A380 exceptionFlight = new A380(null,airDepartureTime);
	}
	
	@Test(expected = AircraftException.class)
	public void ZeroDepTime() throws AircraftException {
		A380 exceptionFlight = new A380(airCode,zeroVal);
	}
	
	@Test(expected = AircraftException.class)
	public void LessThenZeroDepTime() throws AircraftException {
		A380 exceptionFlight = new A380(airCode,negativeVal);
	}
		
	//Test appropriate exceptions are thrown for cancel booking
	
	//Changed in cancel book departureTime < cancel time... You had greater >. Because I think the 
	//point of the error is you cant cancel if the plain has already left. And Not sure if cancel
	//booking is finished because the passenger is never removed....
	
	@Test
	public void CancelPassengerPassConfirmed() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		
		tempFlight.cancelBooking(tempPassenger, cancellationTime);	

	}
	
	@Test(expected = PassengerException.class)
	public void CancelPassengerPassFlown() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempPassenger.flyPassenger(flightTime);
		tempFlight.cancelBooking(tempPassenger, cancellationTime);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelPassengerPassRefused() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempPassenger.refusePassenger(refusedTime);
		tempFlight.cancelBooking(tempPassenger, cancellationTime);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelPassengerPassQueued() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempPassenger.queuePassenger(queueTime, passDepartureTime);
		tempFlight.cancelBooking(tempPassenger, cancellationTime);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelDepTimeLessThenConTime() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.cancelBooking(tempPassenger, airDepartureTime+1);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelConTimeLessThenZero() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.cancelBooking(tempPassenger, negativeVal);	
	}
	
	@Test(expected = AircraftException.class)
	public void CancelPassengerNotInFlight() throws AircraftException, PassengerException {
		//need to set passenger to confirmed or it will throw an exception. To do this we need another flight
		A380 flightToMakePassConfirmed = new A380(airCode, airDepartureTime);
		flightToMakePassConfirmed.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.cancelBooking(tempPassenger, cancellationTime);	
	}
	
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
	//Test appropriate exceptions are thrown for confirm booking
	
	@Test(expected = PassengerException.class)
	public void ConfirmPassengerAlreadyConfirmed() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempPassenger.confirmSeat(confirmationTime, passDepartureTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);	
	}
	
	@Test(expected = PassengerException.class)
	public void ConfirmPassengerFlown() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempPassenger.flyPassenger(flightTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);	
	}
	
	@Test(expected = PassengerException.class)
	public void ConfirmPassengerRefused() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempPassenger.refusePassenger(19);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);	
	}
	
	@Test(expected = PassengerException.class)
	public void ConfirmDepTimeLessThenConTime() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, airDepartureTime+1);	
	}
	
	@Test(expected = PassengerException.class)
	public void ConfirmConTimeLessThenZero() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, negativeVal);	
	}
	
	
	@Test(expected = PassengerException.class)
	public void ConfirmFullyBooked() throws AircraftException, PassengerException {
		fillThePlane();
		smallFlight.confirmBooking(tempPassenger, confirmationTime);	
	}
	
	@Test
	public void ConfirmbookingTestCheckingIfFlightEmpty() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.flightEmpty(), false);
	}
	
	@Test
	public void ConfirmbookingTestCheckiFBusinessIncreases() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.getNumBusiness(), 1);
	}
	
	@Test
	public void ConfirmbookingTestCheckiFFirstIncreases() throws AircraftException, PassengerException {
		First tempFirst = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempFirst, confirmationTime);
		assertEquals(tempFlight.getNumFirst(), 1);
	}
	
	@Test
	public void ConfirmbookingTestCheckiFPremIncreases() throws AircraftException, PassengerException {
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		assertEquals(tempFlight.getNumPremium(), 1);
	}
	
	@Test
	public void ConfirmbookingTestCheckiFEconIncreases() throws AircraftException, PassengerException {
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempEcon, confirmationTime);
		assertEquals(tempFlight.getNumEonomy(), 1);
	}
	
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
	
	@Test
	public void ConfirmbookingTestCheckingIfBusinessCountChanged() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		UnchangedFlight = new A380(airCode, airDepartureTime);
		assertEquals(tempFlight.getNumBusiness(),UnchangedFlight.getNumBusiness()+1 );
	}
	
	//Test the fly passengers function
	@Test
	public void flightPassengersTests() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.flyPassengers(flightTime);
		assertEquals(tempPassenger.isFlown(), true);
	}
	
	
	@Test(expected = PassengerException.class)
	public void flightPassengersThrowsExceptionOneQueued() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		First firstClassPass = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(firstClassPass, confirmationTime);
		firstClassPass.queuePassenger(passBookingTime,passDepartureTime);
		tempFlight.flyPassengers(flightTime);
	}
	
	@Test(expected = PassengerException.class)
	public void flightPassengersThrowsExceptionOneRefused() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		First firstClassPass = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(firstClassPass, confirmationTime);
		firstClassPass.refusePassenger(refusedTime);
		tempFlight.flyPassengers(flightTime);
	}
	
	@Test(expected = PassengerException.class)
	public void flightPassengersThrowsExceptionOneFlown() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		First firstClassPass = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(firstClassPass, confirmationTime);
		firstClassPass.flyPassenger(flightTime);
		tempFlight.flyPassengers(flightTime);
	}
	
	//Tests for seats available
	@Test
	public void seatsAvailableEmptyPlaneTest(){
		assertEquals(tempFlight.seatsAvailable(tempPassenger),true);
	}
	
	@Test
	public void seatsAvailableFullPlaneTestTryAndSitBusiness() throws AircraftException, PassengerException{
		fillThePlane();
		assertEquals(smallFlight.seatsAvailable(tempPassenger),false);
	}
	
	@Test
	public void seatsAvailableFullPlaneTestTryAndSitFirst() throws AircraftException, PassengerException{
		fillThePlane();
		First tempFirst = new First(passBookingTime,passDepartureTime);
		assertEquals(smallFlight.seatsAvailable(tempFirst),false);
	}
	
	@Test
	public void seatsAvailableFullPlaneTestTryAndSitPremium() throws AircraftException, PassengerException{
		fillThePlane();
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		assertEquals(smallFlight.seatsAvailable(tempPrem),false);
	}
	
	@Test
	public void seatsAvailableFullPlaneTestTryAndSitEcon() throws AircraftException, PassengerException{
		fillThePlane();
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		assertEquals(smallFlight.seatsAvailable(tempEcon),false);
	}
	@Test
	public void seatsAvailableOneCustomerAddedTest() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.seatsAvailable(tempPassenger),true);
	}
	//not sure if flight empty and full work because I filled the plane and tested these functions it they suggest that the plane is still empty...
	//maybe as an idea see if the number of economy, first, business and premium = 0?
	@Test
	public void fullPlane() throws AircraftException, PassengerException{
		fillThePlane();
		assertEquals(smallFlight.flightFull(), true);
	}
	
	@Test
	public void NotfullPlane() throws AircraftException, PassengerException{
		assertEquals(tempFlight.flightFull(), false);
	}
	
	@Test
	public void fullPlaneWithOneBooking() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.flightFull(), false);
	}
	
	@Test
	public void getBookingsTestWithNoBooking() throws AircraftException{
		assertEquals(tempFlight.getBookings().getNumBusiness(), 0);
		assertEquals(tempFlight.getBookings().getNumEconomy(), 0);
		assertEquals(tempFlight.getBookings().getNumFirst(), 0);
		assertEquals(tempFlight.getBookings().getNumPremium(), 0);
		assertEquals(tempFlight.getBookings().getTotal(), 0);
		assertEquals(tempFlight.getBookings().getAvailable(), 484);
	}
	
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

	@Test
	public void testPassengerListOnePassenger() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.getPassengers().get(0).toString(), tempPassenger.toString());
	}
	
	@Test
	public void testPassengerListTwoPassengers() throws AircraftException, PassengerException{
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		tempPrem.queuePassenger(queueTime, passDepartureTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.getPassengers().get(0).toString()+tempFlight.getPassengers().get(1).toString(), tempPrem.toString()+tempPassenger.toString());
	}
	
	@Test
	public void testPassengerListNoPassengers() throws AircraftException, PassengerException{
		assertEquals(tempFlight.getPassengers().isEmpty(), true);
	}
	
	@Test
	public void hasPassengerTestWithOnePassengerBusiness() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempPassenger),true);
	}
	
	@Test
	public void hasPassengerTestWithOnePassengerFirst() throws AircraftException, PassengerException{
		First tempFirst = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempFirst, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempFirst),true);
	}
	
	@Test
	public void hasPassengerTestWithOnePassengerPremium() throws AircraftException, PassengerException{
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempPrem),true);
	}
	
	@Test
	public void hasPassengerTestWithOnePassengerEconomy() throws AircraftException, PassengerException{
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempEcon, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempEcon),true);
	}
	
	@Test
	public void hasPassengerTestNopassengers() throws AircraftException, PassengerException{
		assertEquals(tempFlight.hasPassenger(tempPassenger),false);
	}
	
	@Test
	public void hasPassengerTestOnepassengerButNotTheOneWeWantBusiness() throws AircraftException, PassengerException{
		Business tempBusiness = new Business(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempBusiness, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempPassenger),false);
	}
	
	@Test
	public void hasPassengerTestOnepassengerButNotTheOneWeWantFirst() throws AircraftException, PassengerException{
		First tempFirstOne = new First(passBookingTime,passDepartureTime);
		First tempFirstTwo = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempFirstOne, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempFirstTwo),false);
	}
	
	@Test
	public void hasPassengerTestOnepassengerButNotTheOneWeWantPremium() throws AircraftException, PassengerException{
		Premium tempPremOne = new Premium(passBookingTime,passDepartureTime);
		Premium tempPremTwo = new Premium(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempPremOne, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempPremTwo),false);
	}
	
	@Test
	public void hasPassengerTestOnepassengerButNotTheOneWeWantEconomy() throws AircraftException, PassengerException{
		Economy tempEconOne = new Economy(passBookingTime,passDepartureTime);
		Economy tempEconTwo = new Economy(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempEconOne, confirmationTime);
		assertEquals(tempFlight.hasPassenger(tempEconTwo),false);
	}

	@Test
	public void upgradeBookingsSingleBusiness() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof First);
	}
	
	@Test
	public void upgradeBookingsEconomy() throws AircraftException, PassengerException{
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempEcon, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof Premium);
	}
	
	@Test
	public void upgradeBookingsPremium() throws AircraftException, PassengerException{
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof Business);
	}
	
	@Test
	public void upgradeBookingsFirst() throws AircraftException, PassengerException{
		First tempFirst = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempFirst, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof First);
	}
	
	@Test
	public void upgradeBookingsBusinessFirst() throws AircraftException, PassengerException{
		First tempFirst = new First(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempFirst, confirmationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof First);
		assertTrue(tempFlight.getPassengers().get(1) instanceof First);
	}
	
	@Test
	public void upgradeBookingsBusinessEconomy() throws AircraftException, PassengerException{
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempEcon, confirmationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof Premium);
		assertTrue(tempFlight.getPassengers().get(1) instanceof First);
	}
	
	@Test
	public void upgradeBookingsBusinessPremium() throws AircraftException, PassengerException{
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempPrem, confirmationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof Business);
		assertTrue(tempFlight.getPassengers().get(1) instanceof First);
	}
	
	@Test
	public void upgradeBookingsBusinessBusiness() throws AircraftException, PassengerException{
		Business tempBusiness = new Business(passBookingTime,passDepartureTime);
		tempFlight.confirmBooking(tempBusiness, confirmationTime);
		tempFlight.confirmBooking(tempPassenger, confirmationTime);
		tempFlight.upgradeBookings();
		assertTrue(tempFlight.getPassengers().get(0) instanceof First);
		assertTrue(tempFlight.getPassengers().get(1) instanceof First);
	}
	
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
	
	@Test
	public void upgradeBookingsBusinessWhenFirstIsFullManyPassengers() throws AircraftException, PassengerException{
		A380 testFlight = new A380(airCode,airDepartureTime, numberOne, numberOne+1, numberOne,numberOne);
		Economy tempEcon = new Economy(passBookingTime,passDepartureTime);
		Premium tempPrem = new Premium(passBookingTime,passDepartureTime);
		First tempFirst = new First(passBookingTime,passDepartureTime);
		testFlight.confirmBooking(tempEcon, confirmationTime);
		testFlight.confirmBooking(tempPrem, confirmationTime);
		testFlight.confirmBooking(tempPassenger, confirmationTime);
		testFlight.upgradeBookings();
		assertTrue(testFlight.seatsAvailable(tempFirst));
	}
	
	
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