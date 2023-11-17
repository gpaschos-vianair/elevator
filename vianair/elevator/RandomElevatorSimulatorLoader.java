package vianair.elevator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import vianair.elevator.model.Direction;
import vianair.elevator.model.Elevator;
import vianair.elevator.model.Floor;
import vianair.elevator.model.Passenger;

public class RandomElevatorSimulatorLoader implements ElevatorSimulatorLoader {

	private final int minFloorCount;
	private final int maxFloorCount;

	private final int minPassengerFloorCount;
	private final int maxPassengerFloorCount;

	private final int minElevatorCapacity;
	private final int maxElevatorCapacity;

	public RandomElevatorSimulatorLoader() {
		this.minFloorCount = 8;
		this.maxFloorCount = 12;

		this.minPassengerFloorCount = 6;
		this.maxPassengerFloorCount = 12;

		this.minElevatorCapacity = 4;
		this.maxElevatorCapacity = 10;
	}

	@Override
	public ElevatorSimulatorContext getElevatorSimulatorContext() {

		int floorCount = getRandomNumberFromTo(this.minFloorCount, this.maxFloorCount);

		List<Floor> floorList = new ArrayList<>();

		for (int i = 0; i <= floorCount; i++) {

			var floorPassengerCount = getRandomNumberFromTo(this.minPassengerFloorCount, this.maxPassengerFloorCount);

			Queue<Passenger> floorPassengers = new LinkedList<Passenger>();

			for (int j = 0; j < floorPassengerCount; j++) {
				int passengerDestinationFloor = getRandomNumberFromToExcept(0, floorCount, i);
				floorPassengers.add(new Passenger(passengerDestinationFloor));
			}

			floorList.add(new Floor(i, floorPassengers));
		}

		Elevator elevator = new Elevator(getRandomNumberFromTo(this.minElevatorCapacity, this.maxElevatorCapacity),
				Direction.IDLE);
		
		return new ElevatorSimulatorContext(elevator, floorList);
	}

	private int getRandomNumberFromToExcept(int startInclusive, int endInclusive, int exceptNumber) {
		int randomNumber;

		do {
			randomNumber = getRandomNumberFromTo(startInclusive, endInclusive);
		} while (randomNumber == exceptNumber);

		return randomNumber;
	}

	private int getRandomNumberFromTo(int startInclusive, int endInclusive) {
		return (int) (Math.random() * (endInclusive + 1 - startInclusive) + startInclusive);
	}

}
