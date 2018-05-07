import java.util.ArrayList;

/**
 * Class that represents a King chess piece
 *
 * @author Brian Qiu
 */
public class King extends Piece {
	private boolean isChecked;

	/**
	 * COnstructor to intialize the King's color and Position
	 *
	 * @param color
	 *            - the King's color
	 * @param pos
	 *            - the King's position
	 */
	public King(int color, Position pos) {
		super("King", color, pos, 100);
	}

	/**
	 * Returns an array of the Positions that would be crossed if this King were to
	 * move to the given Position
	 *
	 * @param toPos
	 *            - the given Position
	 * @return an ArrayList of the Positions that would be crossed
	 */
	public ArrayList<Position> getCrossedPositions(Position toPos) {
		ArrayList<Position> iWillCross = new ArrayList<Position>();
		if (isWithinRangeOfMovement(toPos)) {
			iWillCross.add(getPosition());
			iWillCross.add(toPos);
		}
		return iWillCross;
	}

	/**
	 * Calculates the King's range of movement based on known board size and its
	 * current position. Positions are ordered ascending in terms of row then
	 * column. E.g., (0, 0), (0, 1), (0, 2), (1, 0)...
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
		if (row - 1 < 7) {
			bot = true;
		}
		if (col - 1 < 7) {
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

	@Override
	/**
	 * Checks whether the given Position is within this King's range of movement
	 *
	 * @param toPos
	 *            - the destination Position
	 * @return true if toPos is within this King's range of movement, false
	 *         otherwise
	 */
	public boolean isWithinRangeOfMovement(Position toPos) {
		return toPos.isWithinBounds() && Math.sqrt(Math.pow(getPosition().getRow() - toPos.getRow(), 2)
				- Math.pow(getPosition().getColumn(), toPos.getColumn())) == 1;
	}
}