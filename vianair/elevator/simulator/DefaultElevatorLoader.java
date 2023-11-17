package vianair.elevator.simulator;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import vianair.elevator.model.Direction;
import vianair.elevator.model.Elevator;
import vianair.elevator.model.Floor;
import vianair.elevator.model.Passenger;
import vianair.elevator.simulator.interfaces.ElevatorLoader;

public class DefaultElevatorLoader implements ElevatorLoader{

	private final Elevator elevator;
	
	public DefaultElevatorLoader(Elevator elevator) {
		this.elevator = Objects.requireNonNull(elevator);
	}
	
	@Override
	public Collection<Passenger> loadPassengers(Floor floor) {
		Queue<Passenger> floorQueue = floor.getPassengerQueue();

		Queue<Passenger> addedPassengers = new LinkedList<>();

		var floorQueueIterator = floorQueue.iterator();

		boolean passengerCanFitInElevator = elevator.getPassengers().size() < elevator.getMaxCapacity();

		while (floorQueueIterator.hasNext() && passengerCanFitInElevator) {

			var examinedPassenger = floorQueueIterator.next();

			boolean passengerAndElevatorAscend = elevator.getDirection() == Direction.ASCENDING
					&& examinedPassenger.getDestinationFloorNumber() > floor.getFloorNumber();

			boolean passengerAndElevatorDescend = elevator.getDirection() == Direction.DESCENDING
					&& examinedPassenger.getDestinationFloorNumber() < floor.getFloorNumber();

			boolean passengerHasTheSameDirectionAsElevator = passengerAndElevatorAscend || passengerAndElevatorDescend;

			if (passengerHasTheSameDirectionAsElevator) {
				elevator.getPassengers().add(examinedPassenger);
				addedPassengers.add(examinedPassenger);
				floorQueueIterator.remove();
			}

			passengerCanFitInElevator = elevator.getPassengers().size() < elevator.getMaxCapacity();

		}

		return addedPassengers;
	}

	@Override
	public Collection<Passenger> unloadPassengers(Floor floor) {
		Queue<Passenger> unloadedPassengers = new LinkedList<Passenger>();

		var passengerIterator = elevator.getPassengers().iterator();

		while (passengerIterator.hasNext()) {

			var passenger = passengerIterator.next();

			if (passenger.getDestinationFloorNumber() == floor.getFloorNumber()) {
				unloadedPassengers.add(passenger);
				passengerIterator.remove();
			}
		}

		return unloadedPassengers;
	}

}
