import java.util.ArrayList;

/**
 * Class that represents a Pawn chess piece
 * 
 * @author Brian
 */
public class Pawn extends Piece {
	private boolean promoted;

	/**
	 * Contructor to initialize the Pawn's color and Position
	 * 
	 * @param color
	 *            - the pawn's color
	 * @param pos
	 *            - the pawn's Position
	 */
	public Pawn(int color, Position pos) {
		super("Pawn", color, pos, 1);
	}

	@Override
	/**
	 * Checks whether the given Position is within this Pawn's range of movement
	 *
	 * @param toPos
	 *            - the destination Position
	 * @return true if toPos is within this Pawn's range of movement, false
	 *         otherwise
	 */
	public boolean isWithinRangeOfMovement(Position toPos) {
		if (super.getColor() == 0) {
			if (!hasMoved()) {
				return toPos.isWithinBounds() && ((getPosition().getRow() - toPos.getRow() == 2
						&& getPosition().getColumn() == toPos.getColumn())
						|| (getPosition().getRow() - toPos.getRow() == 1
								&& Math.abs(getPosition().getColumn() - toPos.getColumn()) <= 1));
			} else {
				return toPos.isWithinBounds() && (getPosition().getRow() - toPos.getRow() == 1
						&& Math.abs(getPosition().getColumn() - toPos.getColumn()) <= 1);
			}
		} else {
			if (!hasMoved()) {
				return toPos.isWithinBounds() && ((toPos.getRow() - getPosition().getRow() == 2
						&& getPosition().getColumn() == toPos.getColumn())
						|| (toPos.getRow() - getPosition().getRow() == 1
								&& Math.abs(getPosition().getColumn() - toPos.getColumn()) <= 1));
			} else {
				return toPos.isWithinBounds() && (getPosition().getRow() - toPos.getRow() == 1
						&& Math.abs(getPosition().getColumn() - toPos.getColumn()) <= 1);
			}
		}

	}

	@Override
	/**
	 * Calculates the Pawn's range of movement based on known board size and its
	 * current position. Positions are ordered ascending in terms of row then
	 * column. E.g., (0, 0), (0, 1), (0, 2), (1, 0)...
	 *
	 * @return the Pawn's range of movement
	 */
	public ArrayList<Position> getRangeOfMovement() {
		ArrayList<Position> range = new ArrayList<Position>();

		int row = getPosition().getRow();
		int col = getPosition().getColumn();

		range.add(getPosition());
		if (getColor() == 1) {
			Position fPos = new Position(row + 1, col);
			if (fPos.isWithinBounds()) {
				range.add(fPos);
			}
			if (!hasMoved()) {
				range.add(new Position(row + 2, col));
			}
			Position pos1 = new Position(row + 1, col + 1);
			if (pos1.isWithinBounds()) {
				range.add(pos1);
			}
			Position pos2 = new Position(row + 1, col - 1);
			if (pos2.isWithinBounds()) {
				range.add(pos2);
			}

		} else {
			Position fPos = new Position(row - 1, col);
			if (fPos.isWithinBounds()) {
				range.add(fPos);
			}
			if (!hasMoved()) {
				range.add(new Position(row - 2, col));
			}
			Position pos1 = new Position(row - 1, col + 1);
			if (pos1.isWithinBounds()) {
				range.add(pos1);
			}
			Position pos2 = new Position(row - 1, col - 1);
			if (pos2.isWithinBounds()) {
				range.add(pos2);
			}
		}

		return range;
	}

	@Override
	/**
	 * Returns an array of the Positions that would be crossed if this Pawn were to
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
			if (Math.abs(toPos.getRow() - getPosition().getRow()) == 2) {
				if (getColor() == 1) {
					iWillCross.add(new Position(getPosition().getRow() + 1, getPosition().getColumn()));
				} else {
					iWillCross.add(new Position(getPosition().getRow() - 1, getPosition().getColumn()));
				}
			}
			iWillCross.add(toPos);
		}
		return iWillCross;
	}

	public void promotion() {
		promoted = true;
	}
}
