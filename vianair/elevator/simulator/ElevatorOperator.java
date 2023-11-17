package vianair.elevator.simulator;

import java.util.Collection;
import java.util.Objects;

import vianair.elevator.model.Floor;
import vianair.elevator.model.Passenger;
import vianair.elevator.simulator.interfaces.ElevatorDirectionResolver;
import vianair.elevator.simulator.interfaces.ElevatorLoader;
import vianair.elevator.simulator.interfaces.ElevatorMover;

public class ElevatorOperator implements ElevatorDirectionResolver, ElevatorLoader, ElevatorMover {

	private final ElevatorDirectionResolver directionResolver;
	private final ElevatorLoader loader;
	private final ElevatorMover mover;

	public ElevatorOperator(ElevatorLoader loader, ElevatorMover mover, ElevatorDirectionResolver directionResolver) {
		this.loader = Objects.requireNonNull(loader);
		this.mover = Objects.requireNonNull(mover);
		this.directionResolver = Objects.requireNonNull(directionResolver);
	}

	@Override
	public Floor moveFrom(Floor currentFloor) {
		return mover.moveFrom(currentFloor);
	}

	@Override
	public Collection<Passenger> loadPassengers(Floor floor) {
		return loader.loadPassengers(floor);
	}

	@Override
	public Collection<Passenger> unloadPassengers(Floor floor) {
		return loader.unloadPassengers(floor);
	}

	@Override
	public void resolveDirection(Floor currentFloor) {
		directionResolver.resolveDirection(currentFloor);
	}

}
