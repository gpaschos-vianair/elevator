package vianair.elevator.simulator.interfaces;

import vianair.elevator.model.Floor;

public interface ElevatorMover {

	Floor moveFrom(Floor currentFloor);
}
