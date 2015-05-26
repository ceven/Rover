package covata.rovers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * The class {@code Input} stores the information of an input NASA file, i.e.
 * the position {@code Position} of the upper right hand corner of the grid, and
 * the list of rovers {@code Rover} it contains.
 * 
 * @author ceven
 *
 */
public class Input {

	/**
	 * The position {@code Position} of the upper right corner of the grid
	 * (included)
	 */
	private final Position mUpperRightCorner;
	/**
	 * A mapping between the rovers {@code Rover} with initial position
	 * {@code Position} and direction {@code Direction}, and the list of moves
	 * {@code Move} they must perform. NB: A LinkedHashMap guarantees to return
	 * keys in the inserting order, which is valuable to produce the NASA
	 * output.
	 */
	private final LinkedHashMap<Rover, List<Move>> mRovers;

	/**
	 * Initialize this with the position of the upper right corner of the grid
	 * and the list of rovers with initial position and direction
	 * 
	 * @param urCorner
	 *            the position {@code Position} of the upper right corner of the
	 *            grid
	 * @param rovers
	 *            the list of rovers {@code Rover}
	 */
	public Input(final Position urCorner,
			final LinkedHashMap<Rover, List<Move>> rovers) {
		mUpperRightCorner = urCorner;
		mRovers = rovers;
	}

	/**
	 * Initialize with an input string. Each line in the input string is assumed
	 * to be delimited by '\n'. The first element is the upper-right coordinates
	 * of the plateau, the lower-left coordinates are assumed to be 0,0. The is
	 * information pertaining to the rovers that have been deployed. Each rover
	 * has two lines of input. The first line gives the rover's position, and
	 * the second line is a series of instructions telling the rover how to
	 * explore the plateau.
	 * 
	 * @param input
	 *            the input string. Example: 5 5\n1 2 N\nLMLMLMLMM\n3 3
	 *            E\nMMRMMRMRRM
	 */
	public Input(String input) {
		String[] in = input.split("\n");
		if (in.length < 1 || in.length % 2 != 1) {
			throw new IllegalArgumentException(
					"Input(String input): the number of input lines should be odd and contain at least 1 line");
		}
		String[] lineSplit;
		Position roverPos = null;
		Direction dir = null;
		List<Move> moves = null;
		Position upperRightCorner = null;
		LinkedHashMap<Rover, List<Move>> rovers = new LinkedHashMap<Rover, List<Move>>();
		for (int i = 0; i < in.length; i++) {
			try {
				if (i == 0) {
					lineSplit = in[i].split("\\s+");
					upperRightCorner = new Position(
							Integer.parseInt(lineSplit[0]),
							Integer.parseInt(lineSplit[1]));
				} else {
					if (roverPos == null) {
						lineSplit = in[i].split("\\s+");
						roverPos = new Position(Integer.parseInt(lineSplit[0]),
								Integer.parseInt(lineSplit[1]));
						dir = Direction.getDirection(lineSplit[2].charAt(0));
					} else if (moves == null) {
						lineSplit = in[i].split("");
						moves = new ArrayList<Move>();
						for (String moveStr : lineSplit) {
							moves.add(Move.getMove(moveStr.charAt(0)));
						}
					}
					if (roverPos != null && moves != null) {
						Rover rover = new Rover(roverPos, dir);
						rovers.put(rover, moves);
						roverPos = null;
						dir = null;
						moves = null;
					}
				}
			} catch (Exception e) {
				throw new IllegalArgumentException(
						"Input(String input): An error occured during parsing string: "
								+ in[i]);
			}
		}
		mUpperRightCorner = upperRightCorner;
		mRovers = rovers;
	}

	/**
	 * 
	 * @return a mapping between each rover and the list of moves it must
	 *         perform
	 */
	public final LinkedHashMap<Rover, List<Move>> getRovers() {
		return mRovers;
	}

	/**
	 * 
	 * @return the position of the upper right corner of the grid
	 */
	public final Position getUpperRightCorner() {
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
