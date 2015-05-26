package covata.rovers;

/**
 * The enum class {@code Direction} provides a definition of the four cardinal
 * directions.<br>
 * 
 * @see #N the North direction<br>
 * @see #S the South direction<br>
 * @see #W the West direction<br>
 * @see #E the East direction<br>
 * 
 * @author ceven
 *
 */
public enum Direction {
	N(0), E(1), S(2), W(3);

	private static final Direction[] vals = Direction.values();

	private final int mVal;

	private Direction(int val) {
		mVal = val;
	}

	/**
	 * 
	 * @return the next cardinal direction after turning clockwise. For example,
	 *         east is the next direction of north after turning clockwise.
	 */
	public Direction getNextClockwise() {
		return vals[(mVal + 1) % 4];
	}

	/**
	 * 
	 * @return the next cardinal direction after turning anticlockwise. For
	 *         example, north is the next direction of east after turning
	 *         anticlockwise.
	 */
	public Direction getNextAnticlockwise() {
		int prev = mVal - 1;
		if (prev < 0)
			prev = 3;
		return vals[prev];
	}

	/**
	 * 
	 * @param ch
	 *            a char representing a cardinal direction: 'N', 'S', 'E' or 'W'
	 * @return the cardinal direction corresponding to the string passed in
	 *         parameter
	 * @throws IllegalArgumentException
	 *             if the char passed in parameter is invalid
	 */
	public static Direction getDirection(char ch) {
		switch (ch) {
		case 'N':
			return Direction.N;
		case 'S':
			return Direction.S;
		case 'E':
			return Direction.E;
		case 'W':
			return Direction.W;
		default:
			throw new IllegalArgumentException(
					"Rover.Direction.getDirection(char ch): Unknown direction: "
							+ ch);
		}
	}
}