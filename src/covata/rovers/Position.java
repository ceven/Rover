package covata.rovers;

/**
 * The class {@code Position} defines a 2D spatial location with coordinates x
 * and y belonging to the set of integers.
 * 
 * @author ceven
 *
 */
public class Position {

	/** The x coordinate */
	private int mX;
	/** The y coordinate */
	private int mY;

	/**
	 * Create a new position with coordinates x and y
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 */
	public Position(int x, int y) {
		mX = x;
		mY = y;
	}

	/**
	 * 
	 * @return the x coordinate of this position
	 */
	public int getX() {
		return mX;
	}

	/**
	 * 
	 * @return the y coordinate of this position
	 */
	public int getY() {
		return mY;
	}

	/**
	 * Set the x coordinate to a new value
	 * 
	 * @param x
	 *            the new x coordinate for this position
	 */
	public void setX(int x) {
		this.mX = x;
	}

	/**
	 * Set the y coordinate to a new value
	 * 
	 * @param y
	 *            the new y coordinate for this position
	 */
	public void setY(int y) {
		this.mY = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Position) {
			Position other = (Position) obj;
			return (other.getX() == getX() && other.getY() == getY());
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("[%d, %d]", mX, mY);
	}

	/**
	 * 
	 * @return a string with coordinates x and y separated by a space
	 */
	public String toSimpleString() {
		return mX + " " + mY;
	}
}
