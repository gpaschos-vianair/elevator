package vianair.elevator;

import vianair.elevator.simulator.DefaultElevatorOperatorFactory;
import vianair.elevator.simulator.ElevatorOperator;

public class Main {

	public static void main(String[] args) {

		ElevatorSimulatorLoader dataLoader = new RandomElevatorSimulatorLoader();

		var context = dataLoader.getElevatorSimulatorContext();

		ElevatorOperator operator = new DefaultElevatorOperatorFactory().createElevatorOperator(context.getElevator(),
				context.getFloorList());

		ElevatorSimulator simulator = new ElevatorSimulator(context.getFloorList(), context.getElevator(), operator);
		
		simulator.runSimulation();
	}
}
