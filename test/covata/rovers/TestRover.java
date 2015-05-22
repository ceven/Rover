package covata.rovers;

import org.junit.Assert;
import org.junit.Test;

import covata.rovers.Rover.Direction;

public class TestRover {

	@Test
	public void test() {
		Position pos = new Position(0, 0);
		Direction dir = Direction.N;
		Rover rover = new Rover(pos, dir);
		Assert.assertEquals(Direction.N, rover.getDirection());
		rover.TurnRight();
		Assert.assertEquals(Direction.E, rover.getDirection());
		rover.TurnLeft();
		Assert.assertEquals(Direction.N, rover.getDirection());

		rover.moveForward();
		Position expected = new Position(0, 1);
		Assert.assertEquals(expected, rover.getPosition());
		rover.TurnLeft();
		Assert.assertEquals(Direction.W, rover.getDirection());
		rover.moveForward();
		expected = new Position(-1, 1);
		Assert.assertEquals(expected, rover.getPosition());
	}
}
