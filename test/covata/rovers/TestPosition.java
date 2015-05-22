package covata.rovers;

import org.junit.Assert;
import org.junit.Test;

public class TestPosition {

	@Test
	public void test() {
		Position p = new Position(1, 2);
		Position p2 = new Position(3, 5);
		Assert.assertNotEquals(p, p2);
		p2 = new Position(1, 2);
		Assert.assertEquals(p, p2);
	}

}
