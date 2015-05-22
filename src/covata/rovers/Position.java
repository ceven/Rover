package covata.rovers;

public class Position {

	private int mX;
	private int mY;

	public Position(int x, int y) {
		mX = x;
		mY = y;
	}

	public int getX() {
		return mX;
	}

	public int getY() {
		return mY;
	}

	public void setX(int x) {
		this.mX = x;
	}

	public void setY(int y) {
		this.mY = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Position) {
			return ((Position) obj).getX() == getX()
					&& ((Position) obj).getY() == getY();
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("[%d, %d]", mX, mY);
	}

	public String toSimpleString() {
		return mX + " " + mY;
	}
}
