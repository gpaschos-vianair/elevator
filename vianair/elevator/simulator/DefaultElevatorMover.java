package vianair.elevator.simulator;

import java.util.List;
import java.util.Objects;

import vianair.elevator.Logger;
import vianair.elevator.model.Elevator;
import vianair.elevator.model.Floor;
import vianair.elevator.simulator.interfaces.ElevatorMover;

public class DefaultElevatorMover implements ElevatorMover {

	private final Elevator elevator;
	private final List<Floor> floorList;

	public DefaultElevatorMover(Elevator elevator, List<Floor> floorList) {
		this.elevator = Objects.requireNonNull(elevator);
		this.floorList = Objects.requireNonNull(floorList);
	}

	@Override
	public Floor moveFrom(Floor currentFloor) {
		switch (elevator.getDirection()) {
		case ASCENDING:
			return tryAscend(currentFloor);
		case DESCENDING:
			return tryDescend(currentFloor);
		default:
			return currentFloor;
		}
	}

	private Floor tryAscend(Floor currentFloor) {
		var currentFloorNumber = currentFloor.getFloorNumber();
		var nextFloorNumber = currentFloorNumber + 1;

		if (nextFloorNumber <= this.floorList.get(floorList.size() - 1).getFloorNumber()) {
			currentFloor = floorList.get(nextFloorNumber);
			Logger.Log("Elevator ascended to " + currentFloor.getFloorNumber());
			return currentFloor;
		}
		return currentFloor;
	}

	private Floor tryDescend(Floor currentFloor) {
		var currentFloorNumber = currentFloor.getFloorNumber();
		var nextFloorNumber = currentFloorNumber - 1;

		if (nextFloorNumber >= this.floorList.get(0).getFloorNumber()) {
			currentFloor = floorList.get(nextFloorNumber);
			Logger.Log("Elevator descended to " + currentFloor);
			return currentFloor;
		}
		return currentFloor;
	}

}
