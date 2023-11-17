package vianair.elevator.model;

import java.util.Collection;
import java.util.HashSet;

public class Elevator {

	private int maxCapacity;
	private Collection<Passenger> passengerCollection;
	private Direction direction;

	public Elevator(int maxCapacity, Direction initialDirection) {
		this.maxCapacity = maxCapacity;
		this.passengerCollection = new HashSet<Passenger>();
		this.direction = initialDirection;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getMaxCapacity() {
		return this.maxCapacity;
	}

	public Collection<Passenger> getPassengers() {
		return passengerCollection;
	}

	@Override
	public String toString() {
		return "Elevator is " + this.direction.toString().toLowerCase()+ ", passengers=" + passengerCollection + "";
	}

}
