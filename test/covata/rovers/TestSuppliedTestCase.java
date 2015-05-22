package covata.rovers;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class TestSuppliedTestCase {

	@Test
	public void test() {
		File file = new File(Utilities.DEFAULT_INPUT_FILE_PATH);
		Experiment exp = new Experiment(file);
		String expectedOutput = "1 3 N\n5 1 E";
		Assert.assertEquals(expectedOutput, exp.getOutput());
	}
}
