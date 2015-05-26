package covata.rovers;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NASAControlTest {

	File mFile;

	@Before
	public void setUp() {
		mFile = new File(FileUtilities.DEFAULT_INPUT_FILE_PATH);
	}

	@After
	public void tearDown() {
		mFile = null;
	}

	@Test
	public void testGetOutputStr() {
		testGetOutputStrWithInputStr();
	}

	public void testGetOutputStrWithInputStr() {
		String input = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM";
		NASAControl exp = new NASAControl(input);
		String expectedOutput = "1 3 N\n5 1 E";
		Assert.assertEquals(expectedOutput, exp.getOutputStr());
	}

	public void testGetOutputStrWithInputFile() {
		String input = FileUtilities.readFile(mFile);
		NASAControl exp = new NASAControl(input);
		String expectedOutput = "1 3 N\n5 1 E";
		Assert.assertEquals(expectedOutput, exp.getOutputStr());
	}

}
