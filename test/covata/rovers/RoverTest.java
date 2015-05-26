package covata.rovers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class RoverTest {

	@Test
	public void testExecuteInstructions() {
		Rover rover = new Rover(new Position(0, 0), Direction.N);
		List<Move> moves = new ArrayList<Move>();
		moves.add(Move.M);
		moves.add(Move.R);
		moves.add(Move.M);
		moves.add(Move.R);
		rover.executeInstructions(moves, new Position(4, 4));
		assertEquals(new Position(1, 1), rover.getPosition());
		assertEquals(Direction.S, rover.getDirection());
	}

	@Test
	public void testMoveForward() {
		// Test move forward for the 4 cardinal directions
		testMoveForwardNorth();
		testMoveForwardSouth();
		testMoveForwardEast();
		testMoveForwardWest();
	}

	public void testMoveForwardNorth() {
		Rover rover = new Rover(new Position(1, 1), Direction.N);
		rover.moveForward();
		assertEquals(new Position(1, 2), rover.getPosition());
	}

	public void testMoveForwardSouth() {
		Rover rover = new Rover(new Position(1, 1), Direction.S);
		rover.moveForward();
		assertEquals(new Position(1, 0), rover.getPosition());
	}

	public void testMoveForwardEast() {
		Rover rover = new Rover(new Position(1, 1), Direction.E);
		rover.moveForward();
		assertEquals(new Position(2, 1), rover.getPosition());
	}

	public void testMoveForwardWest() {
		Rover rover = new Rover(new Position(1, 1), Direction.W);
		rover.moveForward();
		assertEquals(new Position(0, 1), rover.getPosition());
	}

	@Test
	public void testTurnRight() {
		// Test for the 4 directions until original position is reached again
		Rover rover = new Rover(new Position(0, 0), Direction.N);
		rover.turnRight();
		assertEquals(Direction.E, rover.getDirection());
		rover.turnRight();
		assertEquals(Direction.S, rover.getDirection());
		rover.turnRight();
		assertEquals(Direction.W, rover.getDirection());
		rover.turnRight();
		assertEquals(Direction.N, rover.getDirection());
	}

	@Test
	public void testTurnLeft() {
		// Test for the 4 directions until original position is reached again
		Rover rover = new Rover(new Position(0, 0), Direction.N);
		rover.turnLeft();
		assertEquals(Direction.W, rover.getDirection());
		rover.turnLeft();
		assertEquals(Direction.S, rover.getDirection());
		rover.turnLeft();
		assertEquals(Direction.E, rover.getDirection());
		rover.turnLeft();
		assertEquals(Direction.N, rover.getDirection());
	}

	@Test
	public void testClone() {
		Rover rover = new Rover(new Position(0, 0), Direction.N);
		Rover clone = rover.clone();
		assertEquals("Positions of Rover " + rover.toSimpleString() + " and "
				+ clone.toSimpleString() + " must be the same",
				rover.getPosition(), clone.getPosition());
		assertEquals("Directions of Rover " + rover.toSimpleString() + " and "
				+ clone.toSimpleString() + " must be the same",
				rover.getDirection(), clone.getDirection());
		assertNotEquals(
				"Rover " + rover.toSimpleString() + " and "
						+ clone.toSimpleString() + " are different objects",
				rover, clone);
	}

}
