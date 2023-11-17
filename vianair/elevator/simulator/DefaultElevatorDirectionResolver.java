package vianair.elevator.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import vianair.elevator.model.Direction;
import vianair.elevator.model.Elevator;
import vianair.elevator.model.Floor;
import vianair.elevator.simulator.interfaces.ElevatorDirectionResolver;

public class DefaultElevatorDirectionResolver implements ElevatorDirectionResolver {

	private final Elevator elevator;
	private final List<Floor> floorList;

	public DefaultElevatorDirectionResolver(Elevator elevator, List<Floor> floorList) {
		this.elevator = Objects.requireNonNull(elevator, "elevator must not be null");
		this.floorList = Objects.requireNonNull(floorList, "floorList must not be null");
	}

	@Override
	public void resolveDirection(Floor currentFloor) {
		if (shouldChangeElevatorDirection(currentFloor) == false)
			return;

		switch (elevator.getDirection()) {
		case ASCENDING:
			elevator.setDirection(Direction.DESCENDING);
			break;
		case DESCENDING:
			elevator.setDirection(Direction.ASCENDING);
			break;
		case IDLE:
			if (isElevatorAtGroundFloor(currentFloor)) {
				elevator.setDirection(Direction.ASCENDING);
			} else if (isElevatorAtGroundFloor(currentFloor)) {
				elevator.setDirection(Direction.DESCENDING);
			} else {
				// Arbitrarily decide to ascend
				elevator.setDirection(Direction.ASCENDING);
			}
			break;
		default:

		}
	}

	private boolean shouldChangeElevatorDirection(Floor currentFloor) {

		if (isElevatorAtGroundFloor(currentFloor) || isElevatorAtTopFloor(currentFloor)) {
			return true;
		}

		var passengerStream = elevator.getPassengers().stream();

		if (elevator.getDirection() == Direction.ASCENDING) {

			boolean elevatorPassengersGoingUp = passengerStream
					.anyMatch(p -> p.getDestinationFloorNumber() > currentFloor.getFloorNumber());

			boolean anyPassengersWaitingOnAboveFloors = floorList.stream().skip(currentFloor.getFloorNumber())
					.anyMatch(f -> !f.isFloorEmpty());

			return !(elevatorPassengersGoingUp || anyPassengersWaitingOnAboveFloors);

		} else {

			boolean elevatorPassengersGoingDown = passengerStream
					.anyMatch(p -> p.getDestinationFloorNumber() < currentFloor.getFloorNumber());

			var reversedList = new ArrayList<Floor>(floorList);
			Collections.reverse(reversedList);

			boolean anyPassengersWaitingOnBelowFloors = reversedList.stream()
					.skip(floorList.size() - currentFloor.getFloorNumber() - 1).anyMatch(f -> !f.isFloorEmpty());

			return !(elevatorPassengersGoingDown || anyPassengersWaitingOnBelowFloors);

		}
	}

	private boolean isElevatorAtGroundFloor(Floor currentFloor) {
		return currentFloor.equals(this.floorList.get(0));
	}

	private boolean isElevatorAtTopFloor(Floor currentFloor) {
		return currentFloor.equals(this.floorList.get(this.floorList.size() - 1));
	}
}
