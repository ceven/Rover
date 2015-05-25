package covata.rovers;

import org.junit.Assert;
import org.junit.Test;

import covata.rovers.Rover.Direction;

public class TestRover {

	@Test
	public void test() {
		Position pos = new Position(0, 0);
		Rover rover = new Rover(pos, Direction.N);
		Assert.assertEquals(Direction.N, rover.getDirection());
		rover.turnRight();
		Assert.assertEquals(Direction.E, rover.getDirection());
		rover.turnLeft();
		Assert.assertEquals(Direction.N, rover.getDirection());
		rover.turnLeft();
		rover.turnLeft();
		Assert.assertEquals(Direction.S, rover.getDirection());
		rover.turnLeft();
		rover.turnLeft();
		Assert.assertEquals(Direction.N, rover.getDirection());
		rover.turnRight();
		rover.turnRight();
		Assert.assertEquals(Direction.S, rover.getDirection());
		rover.turnRight();
		rover.turnRight();
		Assert.assertEquals(Direction.N, rover.getDirection());

		rover.moveForward();
		Position expected = new Position(0, 1);
		Assert.assertEquals(expected, rover.getPosition());
		rover.turnLeft();
		Assert.assertEquals(Direction.W, rover.getDirection());
		rover.moveForward();
		expected = new Position(-1, 1);
		Assert.assertEquals(expected, rover.getPosition());
	}
}
