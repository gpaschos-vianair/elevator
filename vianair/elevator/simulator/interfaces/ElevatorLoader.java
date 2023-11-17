package vianair.elevator.simulator.interfaces;

import java.util.Collection;

import vianair.elevator.model.Floor;
import vianair.elevator.model.Passenger;

public interface ElevatorLoader {

	Collection<Passenger> loadPassengers(Floor floor);
	
	Collection<Passenger> unloadPassengers(Floor floor);
	
}