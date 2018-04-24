
public class Position {
	/**
	 * Andrew Maybe instead of using x-y coordinates, we can use the chess board
	 * notation (i.e. a-h, 1-8) that would be too complicated to implement on the
	 * board itself, we could just use it on a 2d array and identify the positions
	 * using normal xy values but portray them differently
	 */
	int x;
	int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

}
