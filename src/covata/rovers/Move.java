package covata.rovers;

/**
 * The class Move provides a RobotMove definition as well as useful functions
 * e.g. to determine whether a move is valid for a given grid (defined by a
 * right hand corner position) and a Rover.
 * 
 * @author ceven
 *
 */
public class Move {

	/**
	 * Possible moves for a robot:<br>
	 * 
	 * {@code M} Move forward <br>
	 * {@code L} Turn left<br>
	 * {@code R} Turn right
	 * 
	 * @author ceven
	 *
	 */
	enum RobotMove {
		M, L, R
	}

	public static RobotMove getMove(String str) {
		switch (str) {
		case "M":
			return RobotMove.M;
		case "L":
			return RobotMove.L;
		case "R":
			return RobotMove.R;
		default:
			throw new UnsupportedOperationException(
					"RobotMove.getMove(String str): unknown move: " + str);
		}
	}

	/**
	 * A move is valid if the robot does not exit the grid
	 * 
	 * @param rover
	 *            the rover which performs the move
	 * @param move
	 *            the move to perform
	 * @param urCorner
	 *            the upper right corner of the grid
	 * @return true if the move is valid, false otherwise
	 */
	public static boolean isValidMove(Rover rover, RobotMove move,
			Position urCorner) {
		switch (move) {
		case M: {
			Position forward = rover.getPositionForward();
			return (forward.getX() >= 0 && forward.getY() >= 0
					&& (forward.getX() <= urCorner.getX()) && (forward.getY() <= urCorner
					.getY()));
		}
		case R:
		case L:
			// Moves which do not change the robot position are valid as the
			// robot will not exit the grid
			return true;
		default:
			throw new UnsupportedOperationException(
					"Move.isValidMove: unknown move " + move.toString());
		}
	}

}
