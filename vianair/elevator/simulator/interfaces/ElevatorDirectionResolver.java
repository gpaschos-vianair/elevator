package vianair.elevator.simulator.interfaces;

import vianair.elevator.model.Floor;

public interface ElevatorDirectionResolver {

	void resolveDirection(Floor currentFloor);
}
