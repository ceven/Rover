package covata.rovers;

import java.util.List;

/**
 * This class provides a definition of a rover as a position {@code Position}
 * and a direction ({@code Direction}).
 * 
 * @author ceven
 *
 */
public class Rover {

	/** The current position of the rover */
	private Position mPosition;
	/** The direction this rover is facing */
	private Direction mDirection;

	/**
	 * Create a new rover with an initial position and direction
	 * 
	 * @param initialPos
	 *            the initial position
	 * @param initialDir
	 *            the initial direction
	 */
	public Rover(Position initialPos, Direction initialDir) {
		mPosition = initialPos;
		mDirection = initialDir;
	}

	/**
	 * Move this rover according to the sequence of instructions provided.
	 * Ensure that the rover does not exit the grid
	 * 
	 * @param moves
	 *            a list of {@code Move} to perform by this {@code Rover}
	 * @param urCorner
	 *            the {@code Position} of the upper right corner of the grid
	 *            where the rover evolves
	 */
	public void executeInstructions(List<Move> moves, Position urCorner) {
		moves.forEach(m -> move(m, urCorner));
	}

	private void move(Move move, Position urCorner) {
		if (Move.isValidMove(this, move, urCorner)) {
			switch (move) {
			case L:
				turnLeft();
				break;
			case R:
				turnRight();
				break;
			case M:
				moveForward();
				break;
			default:
				throw new UnsupportedOperationException(
						"Rover.move(move): unknown move" + move.toString());
			}
		} else
			throw new UnsupportedOperationException(
					"Rover.move: The move will make the robot exit the grid! Robot pos: "
							+ this.getPosition().toString() + ", move: "
							+ move.toString());
	}

	/**
	 * Get the position of the robot if it is to move forward
	 */
	Position getPositionForward() {
		switch (getDirection()) {
		case N:
			return new Position(getPosition().getX(), getPosition().getY() + 1);
		case S:
			return new Position(getPosition().getX(), getPosition().getY() - 1);
		case W:
			return new Position(getPosition().getX() - 1, getPosition().getY());
		case E:
			return new Position(getPosition().getX() + 1, getPosition().getY());
		default:
			throw new UnsupportedOperationException(
					"Robot.getPositionForward(): invalid robot direction");
		}
	}

	/**
	 * Make this robot move forward (update its position)
	 */
	public void moveForward() {
		setPosition(getPositionForward());
	}

	/**
	 * Update the direction of the robot after turning right
	 * 
	 */
	public void turnRight() {
		this.setDirection(getDirection().getNextClockwise());
	}

	/**
	 * Update the direction of the robot after turning left
	 * 
	 */
	public void turnLeft() {
		this.setDirection(getDirection().getNextAnticlockwise());
	}

	/**
	 * 
	 * @return the position of the rover
	 */
	public Position getPosition() {
		return mPosition;
	}

	/**
	 * 
	 * @return the direction of the rover
	 */
	public Direction getDirection() {
		return mDirection;
	}

	private void setDirection(Direction direction) {
		this.mDirection = direction;
	}

	private void setPosition(Position position) {
		this.mPosition = position;
	}

	@Override
	public String toString() {
		return super.toString()
				+ String.format(" [position: %s, direction: %s]",
						getPosition(), getDirection());
	}

	/**
	 * 
	 * @return a string representing the position and the direction of the
	 *         robot, separated by a blank space
	 */
	public String toSimpleString() {
		return getPosition().toSimpleString() + " " + getDirection().toString();
	}

	@Override
	protected Rover clone() {
		return new Rover(new Position(getPosition().getX(), getPosition()
				.getY()), getDirection());
	}

}
