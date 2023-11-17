package vianair.elevator.simulator;

import java.util.List;

import vianair.elevator.model.Elevator;
import vianair.elevator.model.Floor;

public interface ElevatorOperatorFactory {

	ElevatorOperator createElevatorOperator(Elevator elevator, List<Floor> floorList);
	
}
