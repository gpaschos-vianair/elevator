package vianair.elevator;

import java.util.List;
import java.util.Objects;

import vianair.elevator.model.Elevator;
import vianair.elevator.model.Floor;

public class ElevatorSimulatorContext {

	private final Elevator elevator;	
	private final List<Floor> floorList;
	
	public ElevatorSimulatorContext(Elevator elevator, List<Floor> floorList) {
		this.elevator = Objects.requireNonNull(elevator);
		this.floorList = Objects.requireNonNull(floorList);
	}

	public Elevator getElevator() {
		return elevator;
	}

	public List<Floor> getFloorList() {
		return floorList;
	}
	
}
