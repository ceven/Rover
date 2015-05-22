package covata.rovers;

import java.util.LinkedHashMap;
import java.util.List;

import covata.rovers.Move.RobotMove;

/**
 * Class to store the information of an input file. The first line of input is
 * the upper-right coordinates of the plateau, the lower-left coordinates are
 * assumed to be 0,0. The rest of the input is information pertaining to the
 * rovers that have been deployed. Each rover has two lines of input. The first
 * line gives the rover's position, and the second line is a series of
 * instructions telling the rover how to explore the plateau. Example: <br>
 * 
 * 5 5 <br>
 * 1 2 N<br>
 * LMLMLMLMM <br>
 * 3 3 E<br>
 * MMRMMRMRRM<br>
 * 
 * @author ceven
 *
 */
public class InputFile {

	/** The upper right corner of the grid (included) */
	private Position mUpperRightCorner;
	/**
	 * A mapping between the rovers with initial position and direction, and the
	 * list of moves they must perform. NB: A LinkedHashMap guarantees to return
	 * keys in the inserting order, which is valuable to produce the NASA
	 * output.
	 */
	private LinkedHashMap<Rover, List<RobotMove>> mRovers;

	public InputFile(Position urCorner,
			LinkedHashMap<Rover, List<RobotMove>> rovers) {
		mUpperRightCorner = urCorner;
		mRovers = rovers;
	}

	public LinkedHashMap<Rover, List<RobotMove>> getRovers() {
		if (mRovers == null) {
			mRovers = new LinkedHashMap<Rover, List<RobotMove>>();
		}
		return mRovers;
	}

	public Position getUpperRightCorner() {
		return mUpperRightCorner;
	}

	@Override
	public String toString() {
		String output = "Corner: " + mUpperRightCorner.toString() + "\n";
		for (Rover rover : getRovers().keySet()) {
			output += rover.toString() + "\n";
			output += getRovers().get(rover).toString() + "\n";
		}
		return output;
	}

	public String toSimpleString() {
		String output = mUpperRightCorner.toSimpleString() + "\n";
		for (Rover rover : getRovers().keySet()) {
			output += rover.toSimpleString() + "\n";
			// Could use guava join for this
			output += getRovers().get(rover).toString() + "\n";
		}
		return output;
	}

}
