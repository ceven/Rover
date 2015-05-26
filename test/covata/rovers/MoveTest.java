package covata.rovers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MoveTest {

	@Test
	public void testIsValidMove() {
		Rover rover = new Rover(new Position(0, 0), Direction.N);
		Position urCorner = new Position(1, 1);
		assertTrue(Move.isValidMove(rover, Move.M, urCorner));
		urCorner.setY(0);
		assertFalse(Move.isValidMove(rover, Move.M, urCorner));
	}

}
