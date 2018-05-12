import java.util.ArrayList;

/**
 * Class that represents a Bishop chess piece
 * 
 * @author Tommy V. Tran
 */
public class Bishop extends Piece {

	/**
	 * Constructor to initialize the Bishop's color and Position
	 * 
	 * @param color
	 *            - the Bishop's color
	 * @param pos
	 *            - the Bishop's Position
	 */
	public Bishop(int color, Position pos) {
		super("Bishop", color, pos, 3);
	}

	@Override
	/**
	 * Returns an array of the Positions that would be crossed if this Bishop were
	 * to move to the given Position
	 * 
	 * @param toPos
	 *            - the given Position
	 * @return an ArrayList of the Positions that would be crossed
	 */
	public ArrayList<Position> getCrossedPositions(Position toPos) {

		Position currentPos = new Position(getPosition());
		ArrayList<Position> iWillCross = new ArrayList<Position>();

		if (isWithinRangeOfMovement(toPos)) {
			int r, c;
			if (currentPos.isAbove(toPos)) {
				r = 1;
			} else {
				r = -1;
			}
			if (currentPos.isLeftOf(toPos)) {
				c = 1;
			} else {
				c = -1;
			}
			while (currentPos.isWithinBounds()) {
				iWillCross.add(new Position(currentPos));
				currentPos.addToRow(r);
				currentPos.addToColumn(c);
			}
		}
		return iWillCross;
	}

	@Override
	/**
	 * Calculates the Bishop's range of movement based on known board size and its
	 * current position. Positions are ordered ascending in terms of row then
	 * column. E.g., (0, 0), (0, 1), (0, 2), (1, 0)...
	 * 
	 * @return the Bishop's range of movement
	 */
	public ArrayList<Position> getRangeOfMovement() {
		ArrayList<Position> field = new ArrayList<Position>();

		// Get quadrant 1
		Position pos1 = new Position(getPosition());
		while (pos1.isWithinBounds()) {
			field.add(new Position(pos1));
			pos1.addToRow(-1);
			pos1.addToColumn(1);
		}

		// Get quadrant 2
		Position pos2 = new Position(getPosition());
		while (pos2.isWithinBounds()) {
			field.add(new Position(pos2));
			pos2.addToRow(-1);
			pos2.addToColumn(-1);
		}

		// Get quadrant 3
		Position pos3 = new Position(getPosition());
		while (pos3.isWithinBounds()) {
			field.add(new Position(pos3));
			pos3.addToRow(1);
			pos3.addToColumn(-1);
		}

		// Get quadrant 4
		Position pos4 = new Position(getPosition());
		while (pos4.isWithinBounds()) {
			field.add(new Position(pos4));
			pos4.addToRow(1);
			pos4.addToColumn(1);
		}
		return field;
	}

	@Override
	/**
	 * Checks whether the given Position is within this Bishop's range of movement
	 * 
	 * @param toPos
	 *            - the destination Position
	 * @return true if toPos is within this Bishop's range of movement, false
	 *         otherwise
	 */
	public boolean isWithinRangeOfMovement(Position toPos) {
		return getRangeOfMovement().contains(toPos);
	}
}
