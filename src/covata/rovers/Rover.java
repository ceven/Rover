package covata.rovers;

import java.util.List;

import covata.rovers.Move.RobotMove;

/**
 * This class provides a definition of a rover as a position (x,y) and a
 * direction (N,S,E,W).
 * 
 * @author ceven
 *
 */
public class Rover {

	/**
	 * Possible directions for the robot:<br>
	 * 
	 * {@code N} the robot is facing North<br>
	 * {@code S} the robot is facing South<br>
	 * {@code W} the robot is facing West<br>
	 * {@code E} the robot is facing East<br>
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

		public Direction getNextClockwise() {
			return vals[(mVal + 1) % 4];
		}

		public Direction getPrevious() {
			int prev = mVal - 1;
			if (prev < 0)
				prev = 3;
			return vals[prev];
		}

		public static Direction getDirection(String str) {
			switch (str) {
			case "N":
				return Direction.N;
			case "S":
				return Direction.S;
			case "E":
				return Direction.E;
			case "W":
				return Direction.W;
			default:
				throw new UnsupportedOperationException(
						"Rover.Direction.getDirection(String str): Unknown direction: "
								+ str);
			}
		}
	}

	private Position mPosition;
	private Direction mDirection;

	/**
	 * Create a new rover with an initial position and direction
	 * 
	 * @param initialPos
	 * @param initialDir
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
	 * @param urCorner
	 */
	public void executeInstructions(List<RobotMove> moves, Position urCorner) {
		moves.forEach(m -> move(m, urCorner));
	}

	private void move(RobotMove move, Position urCorner) {
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
	 * Make this robot move forward
	 */
	public void moveForward() {
		setPosition(getPositionForward());
	}

	/**
	 * Update the direction of the robot after turning right
	 * 
	 * @param urCorner
	 */
	public void turnRight() {
		this.setDirection(getDirection().getNextClockwise());
	}

	/**
	 * Update the direction of the robot after turning left
	 * 
	 * @param urCorner
	 */
	public void turnLeft() {
		this.setDirection(getDirection().getPrevious());
	}

	public Position getPosition() {
		return mPosition;
	}

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

	public String toSimpleString() {
		return getPosition().toSimpleString() + " " + getDirection().toString();
	}

}
