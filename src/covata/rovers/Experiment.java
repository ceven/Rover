package covata.rovers;

import java.io.File;

/**
 * This class takes an input file and compute a solution
 * 
 * @author ceven
 *
 */
public class Experiment {

	private File mInputFile = null;
	private InputFile mInputFileObj = null;
	private String mOutput = null;

	/**
	 * Create a new experiment
	 * 
	 * @param inputFile
	 *            a text file with the following format: <br>
	 *            The first line of input is the upper-right coordinates of the
	 *            plateau, the lower-left coordinates are assumed to be 0,0. The
	 *            rest of the input is information pertaining to the rovers that
	 *            have been deployed. Each rover has two lines of input. The
	 *            first line gives the rover's position, and the second line is
	 *            a series of instructions telling the rover how to explore the
	 *            plateau.
	 */
	public Experiment(final File inputFile) {
		mInputFile = inputFile;
	}

	public InputFile getInputFile() {
		if (mInputFileObj == null && Utilities.isValidFile(mInputFile))
			mInputFileObj = Utilities.readFile(mInputFile);
		return mInputFileObj;
	}

	/**
	 * 
	 * @return the NASA output from the experiment input file
	 */
	public String getOutput() {
		if (mOutput == null) {
			if (getInputFile() != null) {
				for (Rover rover : getInputFile().getRovers().keySet()) {
					rover.executeInstructions(
							getInputFile().getRovers().get(rover),
							getInputFile().getUpperRightCorner());
				}
				mOutput = Utilities.getOutput(getInputFile().getRovers()
						.keySet());
			}
		}
		return mOutput;
	}

	public String getInputFilePath() {
		return mInputFile.getPath();
	}

	public void setInputFile(File mInputFile) {
		this.mInputFile = mInputFile;
		this.mOutput = null;
		this.mInputFileObj = null;
	}
}
