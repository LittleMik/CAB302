package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Aircraft.A380;
import asgn2Aircraft.AircraftException;
import asgn2Aircraft.Bookings;
import asgn2Passengers.Business;
import asgn2Passengers.Economy;
import asgn2Passengers.First;
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
		tempFlight = new A380("1234", 13);
		tempPassenger = new Business(5,13);		
	}
	
	
	
	//Testing constructor by checking class is made and that it is initally empty
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
	
	@Test(expected = PassengerException.class)
	public void CancelPassengerPassConfirmed() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempFlight.confirmBooking(tempPassenger, 11);
		tempPassenger.confirmSeat(10, 13);
		tempFlight.cancelBooking(tempPassenger, 20);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelPassengerPassFlown() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempFlight.confirmBooking(tempPassenger, 11);
		tempPassenger.flyPassenger(22);
		tempFlight.cancelBooking(tempPassenger, 20);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelPassengerPassRefused() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempFlight.confirmBooking(tempPassenger, 11);
		tempPassenger.refusePassenger(18);
		tempFlight.cancelBooking(tempPassenger, 20);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelDepTimeLessThenConTime() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, 11);
		tempPassenger.confirmSeat(10, 13);
		tempFlight.cancelBooking(tempPassenger, 14);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelConTimeLessThenZero() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, 11);
		tempPassenger.confirmSeat(10, 13);
		tempFlight.cancelBooking(tempPassenger, -1);	
	}
	
	@Test
	public void CheckBookingActuallyCancelled() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, 11);
		tempFlight.cancelBooking(tempPassenger, 11);
		assertEquals(tempFlight.getNumBusiness(),0);
	}
	
	//Test appropriate exceptions are thrown for confirm booking
	
	@Test(expected = PassengerException.class)
	public void ConfirmPassengerNotInCorrectStateException() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempPassenger.confirmSeat(10, 13);
		tempFlight.confirmBooking(tempPassenger, 11);	
	}
	
	@Test(expected = PassengerException.class)
	public void ConfirmDepTimeLessThenConTime() throws AircraftException, PassengerException {
		tempFlight.confirmBooking(tempPassenger, 14);	
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
	public void flightPassengersThrowsException() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, 11);
		tempPassenger.confirmSeat(10, 21);
		First firstClassPass = new First(1,21);
		tempFlight.confirmBooking(firstClassPass, 11);
		tempFlight.flyPassengers(21);
	}
	
	@Test(expected = PassengerException.class)
	public void flightPassengersThrowsExceptionOneQueued() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, 11);
		tempPassenger.confirmSeat(10, 21);
		First firstClassPass = new First(1,21);
		firstClassPass.queuePassenger(3, 21);
		tempFlight.confirmBooking(firstClassPass, 11);
		tempFlight.flyPassengers(21);
	}
	
	@Test(expected = PassengerException.class)
	public void flightPassengersThrowsExceptionOneRefused() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, 11);
		tempPassenger.confirmSeat(10, 21);
		First firstClassPass = new First(1,21);
		firstClassPass.refusePassenger(15);
		tempFlight.confirmBooking(firstClassPass, 11);
		tempFlight.flyPassengers(21);
	}
	
	@Test(expected = PassengerException.class)
	public void flightPassengersThrowsExceptionOneFlown() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, 11);
		tempPassenger.confirmSeat(10, 21);
		First firstClassPass = new First(1,21);
		firstClassPass.flyPassenger(22);
		tempFlight.confirmBooking(firstClassPass, 11);
		tempFlight.flyPassengers(21);
	}
	
	//Tests for seats available
	@Test
	public void seatsAvailableEmptyPlaneTest(){
		assertEquals(tempFlight.seatsAvailable(tempPassenger),true);
	}
	
	@Test
	public void seatsAvailableFullPlaneTest() throws AircraftException, PassengerException{
		fillThePlane();
		assertEquals(smallFlight.seatsAvailable(tempPassenger),false);
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
		//tempFlight.confirmBooking(tempPassenger, 11);
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
	public void getBookingsTestWithOneBooking() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, 11);
		assertEquals(tempFlight.getBookings().getNumBusiness(), 1);
		assertEquals(tempFlight.getBookings().getNumEconomy(), 0);
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
	public void hasPassengerTestWithOnePassenger() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, 11);
		assertEquals(tempFlight.hasPassenger(tempPassenger),true);
	}
	
	@Test
	public void hasPassengerTestNopassengers() throws AircraftException, PassengerException{
		assertEquals(tempFlight.hasPassenger(tempPassenger),false);
	}
	
	@Test
	public void hasPassengerTestOnepassengerButNotTheOneWeWant() throws AircraftException, PassengerException{
		Business tempBusiness = new Business(1,20);
		tempFlight.confirmBooking(tempBusiness, 11);
		assertEquals(tempFlight.hasPassenger(tempPassenger),false);
	}
	
	@Test
	public void hasPassengerWithoutPassengerTest() throws AircraftException, PassengerException{
		First testPassenger = new First(1, 20);
		tempFlight.confirmBooking(testPassenger, 11);
		assertEquals(tempFlight.hasPassenger(tempPassenger),false);
	}
	
	//@Test
	public void upgradeBookings() throws AircraftException, PassengerException{
		A380 tempA380 = new A380("1111",20,1,1,1,1);
		tempA380.confirmBooking(tempPassenger, 11);
		tempA380.upgradeBookings();
		assertEquals(tempPassenger.toString(),"");
	}
	//getNumEconomy spent wrong lel
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
