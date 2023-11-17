package vianair.elevator.model;

import java.util.LinkedList;
import java.util.Queue;

public class Floor {

	private int floorNumber;
	private Queue<Passenger> passengerQueue;

	public Floor(int floorNumber, Queue<Passenger> passengerQueue) {
		this.floorNumber = floorNumber;
		this.passengerQueue = new LinkedList<Passenger>(passengerQueue);
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public Queue<Passenger> getPassengerQueue() {
		return passengerQueue;
	}

	public boolean isFloorEmpty() {
		return passengerQueue.isEmpty();
	}

	@Override
	public String toString() {
		return "Floor:" + floorNumber + ", passengers=" + passengerQueue+"";
	}

	
}
