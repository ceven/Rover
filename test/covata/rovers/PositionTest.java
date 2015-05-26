package covata.rovers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class PositionTest {

	@Test
	public void testEqualsObject() {
		Position p = new Position(1, 2);
		Position p2 = new Position(3, 5);
		assertNotEquals(p, p2);
		p2 = new Position(1, 2);
		assertEquals(p, p2);
	}

}
