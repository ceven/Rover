package covata.rovers;

/**
 * The enum class {@code Move} provides a move definition as well as useful
 * functions e.g. to determine whether a move is valid for a given grid (defined
 * by a right hand corner position) and a {@code Rover}.
 * 
 * @author ceven
 *
 */
public enum Move {

	/**
	 * Possible moves:
	 * 
	 * @see #M move forward
	 * @see #L turn left
	 * @see #R turn right
	 * 
	 */
	M, L, R;

	/**
	 * 
	 * @param ch
	 *            a char representing a move: 'M', 'L' or 'R'
	 * @return the {@code Move} corresponding to this character
	 * @throws IllegalArgumentException
	 *             if the character is not one of 'M', 'L' or 'R'
	 */
	public static Move getMove(char ch) {
		switch (ch) {
		case 'M':
			return Move.M;
		case 'L':
			return Move.L;
		case 'R':
			return Move.R;
		default:
			throw new IllegalArgumentException(
					"Move.getMove(Character ch): unknown move: " + ch);
		}
	}

	/**
	 * A move is valid if the rover does not exit the grid
	 * 
	 * @param rover
	 *            the rover which performs the move
	 * @param move
	 *            the move to perform
	 * @param urCorner
	 *            the upper right corner of the grid
	 * @return true if the move is valid, false otherwise
	 */
	public static boolean isValidMove(Rover rover, Move move, Position urCorner) {
		switch (move) {
		case M: {
			Position forward = rover.getPositionForward();
			return (forward.getX() >= 0 && forward.getY() >= 0
					&& (forward.getX() <= urCorner.getX()) && (forward.getY() <= urCorner
					.getY()));
		}
		case R:
		case L:
			// Moves which do not change the rover position are valid as the
			// rover will not exit the grid
			return true;
		default:
			throw new UnsupportedOperationException(
					"Move.isValidMove: unknown move " + move.toString());
		}
	}

}
