package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Aircraft.A380;
import asgn2Aircraft.AircraftException;
import asgn2Passengers.PassengerException;

public class AircraftTests {
	
	@Test(expected = AircraftException.class)
	public void A380ConstructorTestNegativeEconomy() throws AircraftException, PassengerException {
		A380 testFlight = new A380("1234",1,1,1,1,-1);
	}
	
	@Test(expected = AircraftException.class)
	public void A380ConstructorTestNegativePrium() throws AircraftException, PassengerException {
		A380 testFlight = new A380("1234",1,1,1,-1,1);
	}
	
	@Test(expected = AircraftException.class)
	public void A380ConstructorTestNegativeBusiness() throws AircraftException, PassengerException {
		A380 testFlight = new A380("1234",1,1,-1,1,1);
	}
	
	@Test(expected = AircraftException.class)
	public void A380ConstructorTestNegativeFirst() throws AircraftException, PassengerException {
		A380 testFlight = new A380("1234",1,-1,1,1,1);
	}

}
