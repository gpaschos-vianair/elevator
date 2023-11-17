package vianair.elevator.model;

public class Passenger {

	private int destinationFloorNumber;

	public Passenger(int destinationFloorNumber) {
		this.destinationFloorNumber = destinationFloorNumber;
	}

	public int getDestinationFloorNumber() {
		return this.destinationFloorNumber;
	}

	@Override
	public String toString() {
		return String.valueOf(this.destinationFloorNumber);
	}

}
