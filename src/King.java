import java.util.ArrayList;

/**
 * Class that represents a King chess piece
 * 
 * @author Brian Qiu
 */
public class King extends Piece {

	/**
	 * Constructor to initialize the King's color and Position
	 * 
	 * @param color
	 *            the King's color
	 * @param pos
	 *            the King's position
	 */
	public King(int color, Position pos) {
		super("King", color, pos, 100);
	}

	/**
	 * Calculates the King's range of movement based on known board size and its
	 * current position.
	 * 
	 * @return the King's range of movement
	 */
	public ArrayList<Position> getRangeOfMovement() {
		ArrayList<Position> field = new ArrayList<Position>();

		int row = getPosition().getRow();
		int col = getPosition().getColumn();
		boolean top = false;
		boolean right = false;
		boolean bot = false;
		boolean left = false;

		if (row + 1 < 7) {
			top = true;
		}
		if (col + 1 < 7) {
			right = true;
		}
		if (row - 1 < 7 && row - 1 >= 0) {
			bot = true;
		}
		if (col - 1 < 7 && col - 1 >= 0) {
			left = true;
		}

		field.add(getPosition());
		if (bot && left) {
			field.add(new Position(row - 1, col - 1));
		}
		if (bot) {
			field.add(new Position(row - 1, col));
		}
		if (bot && right) {
			field.add(new Position(row - 1, col + 1));
		}
		if (right) {
			field.add(new Position(row, col + 1));
		}
		if (left) {
			field.add(new Position(row, col - 1));
		}
		if (top && left) {
			field.add(new Position(row + 1, col - 1));
		}
		if (top) {
			field.add(new Position(row + 1, col));
		}
		if (top && right) {
			field.add(new Position(row + 1, col + 1));
		}
		return field;
	}
}
