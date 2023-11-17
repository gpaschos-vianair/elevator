package vianair.elevator.simulator;

import java.util.List;
import java.util.Objects;

import vianair.elevator.model.Elevator;
import vianair.elevator.model.Floor;
import vianair.elevator.simulator.interfaces.ElevatorDirectionResolver;
import vianair.elevator.simulator.interfaces.ElevatorLoader;
import vianair.elevator.simulator.interfaces.ElevatorMover;

public class DefaultElevatorOperatorFactory implements ElevatorOperatorFactory{

	@Override
	public ElevatorOperator createElevatorOperator(Elevator elevator, List<Floor> floorList) {
		Objects.requireNonNull(elevator);
		Objects.requireNonNull(floorList);
		
		ElevatorLoader loader = new DefaultElevatorLoader(elevator);
		ElevatorMover mover = new DefaultElevatorMover(elevator, floorList);
		ElevatorDirectionResolver directionResolver = new DefaultElevatorDirectionResolver(elevator, floorList);
		
		return new ElevatorOperator(loader, mover, directionResolver);
	}

}
