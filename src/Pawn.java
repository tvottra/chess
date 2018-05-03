import java.util.ArrayList;
/**
 * 
 * @author Brian
 *
 */
public class Pawn extends Piece {
	private boolean hasMoved;
	private boolean promoted;

	public Pawn(int color, Position pos) {
		super("Pawn", color, pos, 1);
	}

	@Override
	/**
	 * Checks whether the given Position is within this Piece's range of movement
	 * 
	 * @param toPos
	 *            - the destination Position
	 * @return true if toPos is within this Piece's range of movement, false
	 *         otherwise
	 */
	public boolean isWithinRangeOfMovement(Position toPos) {
		if(!hasMoved) {
			return toPos.getRow() - getPosition().getRow() == 2;
		} else {
			return toPos.isWithinBounds() && toPos.getRow() - getPosition().getRow() == 1;
		}
	}

	@Override
	/**
	 * Calculates the King's field of control based on known board size and its
	 * current position. Positions are ordered ascending in terms of row then column. E.g., (0, 0), (0, 1), (0, 2), (1, 0)...
	 * 
	 * @return the King's field of control
	 */
	public ArrayList<Position> getFieldOfControl() {
		ArrayList<Position> field = new ArrayList<Position>();

		int row = getPosition().getRow();
		int col = getPosition().getColumn();

		field.add(getPosition());
		field.add(new Position(row, col + 1));
		if(!hasMoved) {
			field.add(new Position(row, col + 2));
		}
		return field;
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
	public ArrayList<Position> move(Position toPos) {
		ArrayList<Position> iWillCross = new ArrayList<Position>();
		if(isWithinRangeOfMovement(toPos)) {
			iWillCross.add(getPosition());
			if(toPos.getRow() - getPosition().getRow() == 2) {
				iWillCross.add(new Position(getPosition().getRow() + 1, getPosition().getColumn()));
			}
			iWillCross.add(toPos);
		}
		return iWillCross;
	}

	public boolean hasMoved() {
		return hasMoved;
	}

	public void setMoveState(boolean moveState) {
		hasMoved = moveState;
	}

	public void promotion() {
		promoted = true;
	}
}
