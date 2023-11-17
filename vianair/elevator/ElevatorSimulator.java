package vianair.elevator;

import java.util.List;
import java.util.Objects;

import vianair.elevator.model.Direction;
import vianair.elevator.model.Elevator;
import vianair.elevator.model.Floor;
import vianair.elevator.simulator.ElevatorOperator;

public class ElevatorSimulator {

	private final List<Floor> floorList;
	private final Elevator elevator;
	private Floor currentElevatorFloor;

	private ElevatorOperator elevatorOperator;

	public ElevatorSimulator(List<Floor> floorList, Elevator elevator, ElevatorOperator elevatorOperator) {
		this.floorList = Objects.requireNonNull(floorList);
		this.elevator = Objects.requireNonNull(elevator);
		this.elevatorOperator = Objects.requireNonNull(elevatorOperator);
		
		this.currentElevatorFloor = this.floorList.get(0);
	}

	public void runSimulation() {

		Logger.Log(floorList);

		boolean isSimulationRunning = doPassengersStillExist();

		if (isSimulationRunning == false)
			return;

		unloadLoadPassengers();

		while (isSimulationRunning) {

			this.currentElevatorFloor = elevatorOperator.moveFrom(currentElevatorFloor);

			elevatorOperator.resolveDirection(currentElevatorFloor);

			Logger.Log(floorList);

			unloadLoadPassengers();

			isSimulationRunning = doPassengersStillExist();
		}

		goToGroundFloor();
	}

	private void unloadLoadPassengers() {
		
		Logger.Log("");
		Logger.Log("Elevator passengers: " + elevator.getPassengers());

		var unloadedPassengers = elevatorOperator.unloadPassengers(this.currentElevatorFloor);
		Logger.Log("Passengers exiting elevator:" + unloadedPassengers);
		
		var loadedPassengers = elevatorOperator.loadPassengers(this.currentElevatorFloor);
		Logger.Log("Passengers entering elevator:" + loadedPassengers);

		Logger.Log("Elevator passengers: " + elevator.getPassengers());
		Logger.Log("Door closed...");
	}

	private boolean doPassengersStillExist() {

		if (elevator.getPassengers().isEmpty() == false) {
			return true;
		}

		boolean passengersStillExist = floorList.stream().anyMatch(f -> f.isFloorEmpty() == false);

		return passengersStillExist;
	}

	private void goToGroundFloor() {

		this.elevator.setDirection(Direction.DESCENDING);

		while (this.currentElevatorFloor != this.floorList.get(0)) {
			this.currentElevatorFloor = elevatorOperator.moveFrom(currentElevatorFloor);
		}

		this.elevator.setDirection(Direction.IDLE);
	}
}
