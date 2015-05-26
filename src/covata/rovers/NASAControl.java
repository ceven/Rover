package covata.rovers;

import java.util.Collection;
import java.util.Iterator;

/**
 * The class {@code NASAControl} allows to store input {@code Input} and output
 * (a string of final coordinates {@code Position} and direction
 * {@code Direction} for each {@code Rover}, computed from the input) for the
 * navigation of rovers.
 * 
 * @author ceven
 *
 */
public class NASAControl {

	private final String mInputStr;
	private String mOutput = null;

	/**
	 * Create a new {@code NASAControl}
	 * 
	 * @param input
	 *            a string containing the NASA input information, where each
	 *            line of the input is separated by \n. The input information is
	 *            the following:<br>
	 *            The first line is the upper-right coordinates of the plateau,
	 *            the lower-left coordinates are assumed to be 0,0. The rest is
	 *            information pertaining to the rovers that have been deployed.
	 *            Each rover has two lines of input. The first line gives the
	 *            rover's position, and the second line is a series of
	 *            instructions telling the rover how to explore the plateau.
	 */
	public NASAControl(String input) {
		mInputStr = input;
	}

	public final String getInputStr() {
		return mInputStr;
	}

	/**
	 * @return the NASA output from the given input, formatted according to
	 *         {@link #getOutputString(Collection)}.
	 */
	public String getOutputStr() {
		if (mOutput == null) {
			Input input = new Input(getInputStr());
			for (Rover rover : input.getRovers().keySet()) {
				rover.executeInstructions(input.getRovers().get(rover),
						input.getUpperRightCorner());
			}
			mOutput = getOutputString(input.getRovers().keySet());

		}
		return mOutput;
	}

	/**
	 * 
	 * @param rovers
	 *            a list of {@code Rover}
	 * @return a string of {@link #getOutputString(Rover)} of each rover
	 *         {@code Rover} separated by '\n'.
	 */
	private static String getOutputString(Collection<Rover> rovers) {
		String output = "";
		Iterator<Rover> it = rovers.iterator();
		Rover rover;
		while (it.hasNext()) {
			rover = it.next();
			output += getOutputString(rover) + (it.hasNext() ? "\n" : "");
		}
		return output;
	}

	/**
	 * 
	 * @param rover
	 * @return a string with the rover x and y coordinates and its direction,
	 *         all separated by blank spaces.
	 */
	private static String getOutputString(Rover rover) {
		return rover.getPosition().getX() + " " + rover.getPosition().getY()
				+ " " + rover.getDirection().toString();
	}
}
