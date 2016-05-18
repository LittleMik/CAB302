package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Aircraft.A380;
import asgn2Aircraft.AircraftException;
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
	
	
	//Set up an inital flight and an itial passenger
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
	public void CancelBookingTest() throws AircraftException, PassengerException{
		tempFlight.confirmBooking(tempPassenger, 10);	
		tempPassenger.confirmSeat(10, 13);
		tempFlight.cancelBooking(tempPassenger, 1);
		assertEquals(tempFlight.getPassengers(), true);

	}
	
	@Test(expected = PassengerException.class)
	public void CancelPassengerPassConfirmed() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempPassenger.confirmSeat(10, 13);
		tempFlight.cancelBooking(tempPassenger, 20);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelPassengerPassFlown() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempPassenger.flyPassenger(22);
		tempFlight.cancelBooking(tempPassenger, 20);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelPassengerPassRefused() throws AircraftException, PassengerException {
		//change the state of passenger.
		tempPassenger.refusePassenger(18);
		tempFlight.cancelBooking(tempPassenger, 20);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelDepTimeLessThenConTime() throws AircraftException, PassengerException {
		tempFlight.cancelBooking(tempPassenger, 14);	
	}
	
	@Test(expected = PassengerException.class)
	public void CancelConTimeLessThenZero() throws AircraftException, PassengerException {
		tempFlight.cancelBooking(tempPassenger, -1);	
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
	
	//Test the fly passengers fucntion
	@Test
	public void flightPassengersTests() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, 11);
		tempPassenger.confirmSeat(10, 13);
		tempFlight.flyPassengers(21);
		assertEquals(tempPassenger.isFlown(), true);
	}
	
	@Test(expected = PassengerException.class)
	public void flightPassengersThrowsExceptionOneNewPassenger() throws AircraftException, PassengerException {	
		tempFlight.confirmBooking(tempPassenger, 11);
		tempFlight.flyPassengers(21);
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
	public void seatsAvailableTest(){
		assertEquals(tempFlight.seatsAvailable(tempPassenger),true);
	}
	//not sure if flight empty and full work because I filled the plane and tested these functions it they suggest that the plane is still empty...
	//maybe as an idea see if the number of economy, first, business and premium = 0?
	@Test
	public void fullPlane() throws AircraftException, PassengerException{
		fillThePlane();
		assertEquals(tempFlight.flightEmpty(), false);
	}
	

	
	//getNumEconomy spent wrong lel
	private void fillThePlane() throws AircraftException, PassengerException{
		int maxEcon = tempFlight.getNumEonomy();
		int maxBus = tempFlight.getNumBusiness();
		int maxPrem = tempFlight.getNumPremium();
		int maxFirst = tempFlight.getNumFirst();
		Economy tempEcon = new Economy(1,20);
		Premium tempPrem = new Premium(1,20);
		First tempFirst = new First(1,20);
		for(int i = 0; i< maxBus ; i++){
			tempFlight.confirmBooking(tempPassenger, 11);
		}
		for(int i = 0; i< maxEcon ; i++){
			tempFlight.confirmBooking(tempEcon, 11);
		}
		for(int i = 0; i< maxPrem ; i++){
			tempFlight.confirmBooking(tempPrem, 11);
		}
		for(int i = 0; i< maxFirst ; i++){
			tempFlight.confirmBooking(tempFirst, 11);
		}
	}

}
