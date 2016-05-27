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
	
	
	//Set up an iniital flight and an initial passenger
	@Before 
	public void initialize() throws AircraftException, PassengerException {
		tempFlight = new A380("1234", 20);
		tempPassenger = new Business(5,15);		
	}
	
	
	
	//Testing constructor by checking class is made and that it is initially empty
	@Test
	public void flightEmptyTests() throws AircraftException, PassengerException {
		assertEquals(tempFlight.flightEmpty(), true );
	}
	
	@Test
	public void flightNotEmptyTests() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, 11);
		assertEquals(tempFlight.flightEmpty(), false );
	}
	
	//Testing constructor throws exceptions
	@Test(expected = AircraftException.class)
	public void EmptyCode() throws AircraftException {
		A380 exceptionFlight = new A380("",10);
	}
	
	@Test(expected = AircraftException.class)
	public void NullCode() throws AircraftException {
		A380 exceptionFlight = new A380(null,10);
	}
	
	@Test(expected = AircraftException.class)
	public void ZeroDepTime() throws AircraftException {
		A380 exceptionFlight = new A380("123",0);
	}
	
	@Test(expected = AircraftException.class)
	public void LessThenZeroDepTime() throws AircraftException {
		A380 exceptionFlight = new A380("123",-1);
	}
		
	//Test appropriate exceptions are thrown for cancel booking
	
	//Changed in cancel book departureTime < cancel time... You had greater >. Because I think the 
	//point of the error is you cant cancel if the plain has already left. And Not sure if cancel
	//booking is finished because the passenger is never removed....
	
	@Test
	public void CancelPassengerPassConfirmed() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempFlight.confirmBooking(tempPassenger, 11);
		
		tempFlight.cancelBooking(tempPassenger, 15);	

	}
	
	@Test(expected = PassengerException.class)
	public void CancelPassengerPassFlown() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempFlight.confirmBooking(tempPassenger, 11);
		tempPassenger.flyPassenger(22);
		tempFlight.cancelBooking(tempPassenger, 15);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelPassengerPassRefused() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempFlight.confirmBooking(tempPassenger, 11);
		tempPassenger.refusePassenger(18);
		tempFlight.cancelBooking(tempPassenger, 15);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelDepTimeLessThenConTime() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, 11);
		tempFlight.cancelBooking(tempPassenger, 21);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelConTimeLessThenZero() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, 11);
		tempFlight.cancelBooking(tempPassenger, -1);	
	}
	
	@Test(expected = AircraftException.class)
	public void CancelPassengerNotInFlight() throws AircraftException, PassengerException {
		//need to set passenger to confirmed or it will throw an exception. To do this we need another flight
		A380 flightToMakePassConfirmed = new A380("1111", 20);
		flightToMakePassConfirmed.confirmBooking(tempPassenger, 11);
		tempFlight.cancelBooking(tempPassenger, 13);	
	}
	
	@Test
	public void CheckBookingActuallyCancelledBusiness() throws AircraftException, PassengerException {
		
		
		First tempFirst = new First(5,13);
		Premium tempPrem = new Premium(5,13);
		Economy tempEcon = new Economy(5,13);
		
		tempFlight.confirmBooking(tempEcon, 11);
		tempFlight.confirmBooking(tempPrem, 11);
		tempFlight.confirmBooking(tempPassenger, 11);
		tempFlight.confirmBooking(tempFirst, 11);
		
		int beforeconFirst = tempFlight.getNumFirst();
		int beforeconEcon = tempFlight.getNumEonomy();
		int beforeconPrem = tempFlight.getNumPremium();
		
		tempFlight.cancelBooking(tempPassenger, 11);
		
		assertEquals(tempFlight.getNumBusiness(),0);
		assertEquals(tempFlight.getNumFirst(), beforeconFirst);
		assertEquals(tempFlight.getNumEonomy(), beforeconEcon);
		assertEquals(tempFlight.getNumPremium(), beforeconPrem);
	
	}
	
	@Test
	public void CheckBookingActuallyCancelledFirst() throws AircraftException, PassengerException {
		
		
		First tempFirst = new First(5,13);
		Premium tempPrem = new Premium(5,13);
		Economy tempEcon = new Economy(5,13);
		
		tempFlight.confirmBooking(tempEcon, 11);
		tempFlight.confirmBooking(tempPrem, 11);
		tempFlight.confirmBooking(tempPassenger, 11);
		tempFlight.confirmBooking(tempFirst, 11);
		
		int beforeconBusiness = tempFlight.getNumBusiness();
		int beforeconEcon = tempFlight.getNumEonomy();
		int beforeconPrem = tempFlight.getNumPremium();
		
		tempFlight.cancelBooking(tempFirst, 11);
		
		assertEquals(tempFlight.getNumFirst(),0);
		assertEquals(tempFlight.getNumBusiness(), beforeconBusiness);
		assertEquals(tempFlight.getNumEonomy(), beforeconEcon);
		assertEquals(tempFlight.getNumPremium(), beforeconPrem);
	}
	
	@Test
	public void CheckBookingActuallyCancelledPrem() throws AircraftException, PassengerException {
	
		First tempFirst = new First(5,13);
		Premium tempPrem = new Premium(5,13);
		Economy tempEcon = new Economy(5,13);
		
		tempFlight.confirmBooking(tempEcon, 11);
		tempFlight.confirmBooking(tempPrem, 11);
		tempFlight.confirmBooking(tempPassenger, 11);
		tempFlight.confirmBooking(tempFirst, 11);
		
		int beforeconFirst = tempFlight.getNumFirst();
		int beforeconEcon = tempFlight.getNumEonomy();
		int beforeconBusiness = tempFlight.getNumPremium();
	
		tempFlight.cancelBooking(tempPrem, 11);
		
		assertEquals(tempFlight.getNumPremium(),0);
		assertEquals(tempFlight.getNumFirst(), beforeconFirst);
		assertEquals(tempFlight.getNumEonomy(), beforeconEcon);
		assertEquals(tempFlight.getNumBusiness(), beforeconBusiness);
	
	}
	
	@Test
	public void CheckBookingActuallyCancelledEconomy() throws AircraftException, PassengerException {
		
		First tempFirst = new First(5,13);
		Premium tempPrem = new Premium(5,13);
		Economy tempEcon = new Economy(5,13);
		
		tempFlight.confirmBooking(tempEcon, 11);
		tempFlight.confirmBooking(tempPrem, 11);
		tempFlight.confirmBooking(tempPassenger, 11);
		tempFlight.confirmBooking(tempFirst, 11);
		
		int beforeconFirst = tempFlight.getNumFirst();
		int beforeconBusiness = tempFlight.getNumBusiness();
		int beforeconPrem = tempFlight.getNumPremium();
		
		tempFlight.cancelBooking(tempEcon, 11);
		
		assertEquals(tempFlight.getNumEonomy(),0);
		assertEquals(tempFlight.getNumFirst(), beforeconFirst);
		assertEquals(tempFlight.getNumBusiness(), beforeconBusiness);
		assertEquals(tempFlight.getNumPremium(), beforeconPrem);
	
	}
	//Test appropriate exceptions are thrown for confirm booking
	
	@Test(expected = PassengerException.class)
	public void ConfirmPassengerAlreadyConfirmed() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempPassenger.confirmSeat(10, 13);
		tempFlight.confirmBooking(tempPassenger, 11);	
	}
	
	@Test(expected = PassengerException.class)
	public void ConfirmPassengerFlown() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempPassenger.flyPassenger(20);
		tempFlight.confirmBooking(tempPassenger, 11);	
	}
	
	@Test(expected = PassengerException.class)
	public void ConfirmPassengerRefused() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempPassenger.refusePassenger(19);
		tempFlight.confirmBooking(tempPassenger, 11);	
	}
	
	@Test(expected = PassengerException.class)
	public void ConfirmDepTimeLessThenConTime() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, 21);	
	}
	
	@Test(expected = PassengerException.class)
	public void ConfirmConTimeLessThenZero() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, -1);	
	}
	
	
	@Test(expected = PassengerException.class)
	public void ConfirmFullyBooked() throws AircraftException, PassengerException {
		fillThePlane();
		smallFlight.confirmBooking(tempPassenger, 11);	
	}
	
	@Test
	public void ConfirmbookingTestCheckingIfFlightEmpty() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, 11);
		assertEquals(tempFlight.flightEmpty(), false);
	}
	
	@Test
	public void ConfirmbookingTestCheckiFBusinessIncreases() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, 11);
		assertEquals(tempFlight.getNumBusiness(), 1);
	}
	
	@Test
	public void ConfirmbookingTestCheckiFFirstIncreases() throws AircraftException, PassengerException {
		First tempFirst = new First(5,15);
		tempFlight.confirmBooking(tempFirst, 11);
		assertEquals(tempFlight.getNumFirst(), 1);
	}
	
	@Test
	public void ConfirmbookingTestCheckiFPremIncreases() throws AircraftException, PassengerException {
		Premium tempPrem = new Premium(5,15);
		tempFlight.confirmBooking(tempPrem, 11);
		assertEquals(tempFlight.getNumPremium(), 1);
	}
	
	@Test
	public void ConfirmbookingTestCheckiFEconIncreases() throws AircraftException, PassengerException {
		Economy tempEcon = new Economy(5,15);
		tempFlight.confirmBooking(tempEcon, 11);
		assertEquals(tempFlight.getNumEonomy(), 1);
	}
	
	@Test
	public void ConfirmbookingTestCheckiFBusinessIncreasesButNothingElse() throws AircraftException, PassengerException {
		int beforeconFirst = tempFlight.getNumFirst();
		int beforeconEcon = tempFlight.getNumEonomy();
		int beforeconPrem = tempFlight.getNumPassengers();
		tempFlight.confirmBooking(tempPassenger, 11);
		assertEquals(tempFlight.getNumBusiness(), 1);
		assertEquals(tempFlight.getNumFirst(), beforeconFirst);
		assertEquals(tempFlight.getNumEonomy(), beforeconEcon);
		assertEquals(tempFlight.getNumPremium(), beforeconPrem);
	}
	
	@Test
	public void ConfirmbookingTestCheckiFFirstIncreasesButNothingElse() throws AircraftException, PassengerException {
		First tempFirst = new First(5,15);
		int beforeconBusiness = tempFlight.getNumBusiness();
		int beforeconEcon = tempFlight.getNumEonomy();
		int beforeconPrem = tempFlight.getNumPassengers();
		tempFlight.confirmBooking(tempFirst, 11);
		assertEquals(tempFlight.getNumFirst(), 1);
		assertEquals(tempFlight.getNumBusiness(), beforeconBusiness);
		assertEquals(tempFlight.getNumEonomy(), beforeconEcon);
		assertEquals(tempFlight.getNumPremium(), beforeconPrem);
	
	}
	
	@Test
	public void ConfirmbookingTestCheckiFPremIncreasesButNothingElse() throws AircraftException, PassengerException {
		Premium tempPrem = new Premium(5,15);
		int beforeconBusiness = tempFlight.getNumBusiness();
		int beforeconEcon = tempFlight.getNumEonomy();
		int beforeconFirst = tempFlight.getNumFirst();
		tempFlight.confirmBooking(tempPrem, 11);
		assertEquals(tempFlight.getNumPremium(), 1);
		assertEquals(tempFlight.getNumBusiness(), beforeconBusiness);
		assertEquals(tempFlight.getNumEonomy(), beforeconEcon);
		assertEquals(tempFlight.getNumFirst(), beforeconFirst);
	}
	
	@Test
	public void ConfirmbookingTestCheckiFEconIncreasesButNothingElse() throws AircraftException, PassengerException {
		Economy tempEcon = new Economy(5,15);
		int beforeconBusiness = tempFlight.getNumBusiness();
		int beforeconPremium = tempFlight.getNumEonomy();
		int beforeconFirst = tempFlight.getNumFirst();
		tempFlight.confirmBooking(tempEcon, 11);
		assertEquals(tempFlight.getNumEonomy(), 1);
		assertEquals(tempFlight.getNumBusiness(), beforeconBusiness);
		assertEquals(tempFlight.getNumPremium(), beforeconPremium);
		assertEquals(tempFlight.getNumFirst(), beforeconFirst);
	}
	
	@Test
	public void ConfirmbookingTestCheckingIfBusinessCountChanged() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, 11);
		UnchangedFlight = new A380("12345", 15);
		assertEquals(tempFlight.getNumBusiness(),UnchangedFlight.getNumBusiness()+1 );
	}
	
	//Test the fly passengers function
	@Test
	public void flightPassengersTests() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, 11);
		tempFlight.flyPassengers(21);
		assertEquals(tempPassenger.isFlown(), true);
	}
	
	
	@Test(expected = PassengerException.class)
	public void flightPassengersThrowsExceptionOneQueued() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, 11);
		First firstClassPass = new First(1,21);
		tempFlight.confirmBooking(firstClassPass, 11);
		firstClassPass.queuePassenger(3, 21);
		tempFlight.flyPassengers(21);
	}
	
	@Test(expected = PassengerException.class)
	public void flightPassengersThrowsExceptionOneRefused() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, 11);
		First firstClassPass = new First(1,21);
		tempFlight.confirmBooking(firstClassPass, 11);
		firstClassPass.refusePassenger(15);
		tempFlight.flyPassengers(21);
	}
	
	@Test(expected = PassengerException.class)
	public void flightPassengersThrowsExceptionOneFlown() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, 11);
		First firstClassPass = new First(1,21);
		tempFlight.confirmBooking(firstClassPass, 11);
		firstClassPass.flyPassenger(22);
		tempFlight.flyPassengers(21);
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
		First tempFirst = new First(3,20);
		assertEquals(smallFlight.seatsAvailable(tempFirst),false);
	}
	
	@Test
	public void seatsAvailableFullPlaneTestTryAndSitPremium() throws AircraftException, PassengerException{
		fillThePlane();
		Premium tempPrem = new Premium(3,20);
		assertEquals(smallFlight.seatsAvailable(tempPrem),false);
	}
	
	@Test
	public void seatsAvailableFullPlaneTestTryAndSitEcon() throws AircraftException, PassengerException{
		fillThePlane();
		Economy tempEcon = new Economy(3,20);
		assertEquals(smallFlight.seatsAvailable(tempEcon),false);
	}
	@Test
	public void seatsAvailableOneCustomerAddedTest() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, 11);
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
		tempFlight.confirmBooking(tempPassenger, 11);
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
		tempFlight.confirmBooking(tempPassenger, 11);
		assertEquals(tempFlight.getBookings().getNumBusiness(), 1);
		assertEquals(tempFlight.getBookings().getNumEconomy(), 0);
		assertEquals(tempFlight.getBookings().getNumFirst(), 0);
		assertEquals(tempFlight.getBookings().getNumPremium(), 0);
		assertEquals(tempFlight.getBookings().getTotal(), 1);
		assertEquals(tempFlight.getBookings().getAvailable(), 483);
	}
	
	@Test
	public void getBookingsTestWithOneBookingFirst() throws AircraftException, PassengerException{
		First tempFirst = new First(3,20);
		tempFlight.confirmBooking(tempFirst, 11);
		assertEquals(tempFlight.getBookings().getNumBusiness(), 0);
		assertEquals(tempFlight.getBookings().getNumEconomy(), 0);
		assertEquals(tempFlight.getBookings().getNumFirst(), 1);
		assertEquals(tempFlight.getBookings().getNumPremium(), 0);
		assertEquals(tempFlight.getBookings().getTotal(), 1);
		assertEquals(tempFlight.getBookings().getAvailable(), 483);
	}
	
	@Test
	public void getBookingsTestWithOneBookingPremium() throws AircraftException, PassengerException{
		Premium tempPrem = new Premium(3,20);
		tempFlight.confirmBooking(tempPrem, 11);
		assertEquals(tempFlight.getBookings().getNumBusiness(), 0);
		assertEquals(tempFlight.getBookings().getNumEconomy(), 0);
		assertEquals(tempFlight.getBookings().getNumFirst(), 0);
		assertEquals(tempFlight.getBookings().getNumPremium(), 1);
		assertEquals(tempFlight.getBookings().getTotal(), 1);
		assertEquals(tempFlight.getBookings().getAvailable(), 483);
	}
	
	@Test
	public void getBookingsTestWithOneBookingEconomy() throws AircraftException, PassengerException{
		Economy tempEco = new Economy(3,20);
		tempFlight.confirmBooking(tempEco, 11);
		assertEquals(tempFlight.getBookings().getNumBusiness(), 0);
		assertEquals(tempFlight.getBookings().getNumEconomy(), 1);
		assertEquals(tempFlight.getBookings().getNumFirst(), 0);
		assertEquals(tempFlight.getBookings().getNumPremium(), 0);
		assertEquals(tempFlight.getBookings().getTotal(), 1);
		assertEquals(tempFlight.getBookings().getAvailable(), 483);
	}

	@Test
	public void testPassengerListOnePassenger() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, 10);
		assertEquals(tempFlight.getPassengers().get(0).toString(), tempPassenger.toString());
	}
	
	@Test
	public void testPassengerListTwoPassengers() throws AircraftException, PassengerException{
		Premium tempPrem = new Premium(1,20);
		tempPrem.queuePassenger(3, 20);
		tempFlight.confirmBooking(tempPrem, 11);
		tempFlight.confirmBooking(tempPassenger, 10);
		assertEquals(tempFlight.getPassengers().get(0).toString()+tempFlight.getPassengers().get(1).toString(), tempPrem.toString()+tempPassenger.toString());
	}
	
	@Test
	public void testPassengerListNoPassengers() throws AircraftException, PassengerException{
		assertEquals(tempFlight.getPassengers().isEmpty(), true);
	}
	
	@Test
	public void hasPassengerTestWithOnePassengerBusiness() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, 11);
		assertEquals(tempFlight.hasPassenger(tempPassenger),true);
	}
	
	@Test
	public void hasPassengerTestWithOnePassengerFirst() throws AircraftException, PassengerException{
		First tempFirst = new First(3,20);
		tempFlight.confirmBooking(tempFirst, 11);
		assertEquals(tempFlight.hasPassenger(tempFirst),true);
	}
	
	@Test
	public void hasPassengerTestWithOnePassengerPremium() throws AircraftException, PassengerException{
		Premium tempPrem = new Premium(3,20);
		tempFlight.confirmBooking(tempPrem, 11);
		assertEquals(tempFlight.hasPassenger(tempPrem),true);
	}
	
	@Test
	public void hasPassengerTestWithOnePassengerEconomy() throws AircraftException, PassengerException{
		Economy tempEcon = new Economy(3,20);
		tempFlight.confirmBooking(tempEcon, 11);
		assertEquals(tempFlight.hasPassenger(tempEcon),true);
	}
	
	@Test
	public void hasPassengerTestNopassengers() throws AircraftException, PassengerException{
		assertEquals(tempFlight.hasPassenger(tempPassenger),false);
	}
	
	@Test
	public void hasPassengerTestOnepassengerButNotTheOneWeWantBusiness() throws AircraftException, PassengerException{
		Business tempBusiness = new Business(1,20);
		tempFlight.confirmBooking(tempBusiness, 11);
		assertEquals(tempFlight.hasPassenger(tempPassenger),false);
	}
	
	@Test
	public void hasPassengerTestOnepassengerButNotTheOneWeWantFirst() throws AircraftException, PassengerException{
		First tempFirstOne = new First(1,20);
		First tempFirstTwo = new First(1,20);
		tempFlight.confirmBooking(tempFirstOne, 11);
		assertEquals(tempFlight.hasPassenger(tempFirstTwo),false);
	}
	
	@Test
	public void hasPassengerTestOnepassengerButNotTheOneWeWantPremium() throws AircraftException, PassengerException{
		Premium tempPremOne = new Premium(1,20);
		Premium tempPremTwo = new Premium(1,20);
		tempFlight.confirmBooking(tempPremOne, 11);
		assertEquals(tempFlight.hasPassenger(tempPremTwo),false);
	}
	
	@Test
	public void hasPassengerTestOnepassengerButNotTheOneWeWantEconomy() throws AircraftException, PassengerException{
		Economy tempEconOne = new Economy(1,20);
		Economy tempEconTwo = new Economy(1,20);
		tempFlight.confirmBooking(tempEconOne, 11);
		assertEquals(tempFlight.hasPassenger(tempEconTwo),false);
	}

	@Test
	public void upgradeBookings() throws AircraftException, PassengerException{
		A380 tempA380 = new A380("1111",20,1,1,1,1);
		Economy tempEco = new Economy(1,20);
		int count = 0;
		tempFlight.confirmBooking(tempPassenger, 11);
		tempFlight.confirmBooking(tempEco, 11);
		Passenger c = null;
		for(Passenger p: tempFlight.getPassengers()){
			c = p.upgrade();
			p = c;
			count++;
		}
		//tempFlight.upgradeBookings();
		assertEquals(tempFlight.getPassengers(),c);
	}
	
	
	private void fillThePlane() throws AircraftException, PassengerException{
		smallFlight = new A380("111",21,1,1,1,1);
		Economy tempEcon = new Economy(1,20);
		Premium tempPrem = new Premium(1,20);
		First tempFirst = new First(1,20);
		smallFlight.confirmBooking(tempPassenger, 11);
		smallFlight.confirmBooking(tempEcon, 11);
		smallFlight.confirmBooking(tempPrem, 11);
		smallFlight.confirmBooking(tempFirst, 11);
	}

}