
/**
 * Class that represents a Rook chess piece
 */
import java.util.ArrayList;

/**
 * @author Andrew Le
 *
 */
public class Rook extends Piece {
	private boolean hasMoved;

	/**
	 * @param name
	 *            - the name of the piece
	 * @param color
	 *            - the color of the piece
	 * @param pos
	 *            - the Position of the piece
	 * @param pointVal
	 *            - the point value of the Piece
	 */
	public Rook(String name, int color, Position pos, int pointVal) {
		super(name, color, pos, pointVal);
		hasMoved = false;
	}

	/**
	 * Accessor method to get this Rook's move status
	 *
	 * @return true if this Rook has already moved at least once, false otherwise
	 */
	public boolean hasMoved() {
		return hasMoved;
	}

	/**
	 * Mutator method to update the Rook's move state
	 *
	 * @param moveState
	 *            - true if this Rook has already moved at least once, false
	 *            otherwise
	 */
	public void setMoveState(boolean moveState) {
		hasMoved = moveState;
	}

	/**
	 * Determines the Positions that would be crossed if this Rook were to move from
	 * its current position to the given position
	 *
	 * @param toPos
	 *            - the given position
	 * @return an ArrayList of crossed Positions
	 */
	private ArrayList<Position> getCrossedPositions(Position toPos) {
		ArrayList<Position> positions = new ArrayList<Position>();
		Position fromPos = getPosition();
		// The two Positions are in the same row
		if (fromPos.getRow() == toPos.getRow()) {
			for (int c = fromPos.getColumn(); c < toPos.getColumn(); c++) {
				Position pos = new Position(fromPos.getRow(), c);
				positions.add(pos);
			}
		}
		// The two Positions are in the same column
		if (fromPos.getColumn() == toPos.getColumn()) {
			for (int r = fromPos.getRow(); r < toPos.getRow(); r++) {
				Position pos = new Position(r, fromPos.getColumn());
				positions.add(pos);
			}
		}
		return positions;
	}

	@Override
	public ArrayList<Position> move(Position toPos) {
		if (isWithinRangeOfMovement(toPos)) {
			return getCrossedPositions(toPos);
		} else {
			return null;
		}
	}

	@Override
	public ArrayList<Position> getFieldOfControl() {
		Position pos = getPosition();
		ArrayList<Position> fieldOfControl = new ArrayList<Position>();
		fieldOfControl = new ArrayList<Position>();
		// Add all positions above current position (same column)
		for (int r = pos.getRow() - 1; r >= 0; r--) {
			Position p = new Position(r, pos.getColumn());
			fieldOfControl.add(p);
		}

		fieldOfControl.add(pos);
		for (int r = pos.getRow() + 1; r < getSize(); r++) {
			Position p = new Position(r, pos.getColumn());
			fieldOfControl.add(p);
		}

		fieldOfControl.add(pos);
		for (int c = pos.getColumn() - 1; c >= 0; c--) {
			Position p = new Position(pos.getRow(), c);
			fieldOfControl.add(p);
		}

		fieldOfControl.add(pos);
		for (int c = pos.getColumn() + 1; c < getSize(); c++) {
			Position p = new Position(pos.getRow(), c);
			fieldOfControl.add(p);
		}

		return fieldOfControl;
	}

	@Override
	public boolean isWithinRangeOfMovement(Position toPos) {
		ArrayList<Position> fieldOfControl = getFieldOfControl();
		for (Position pos : fieldOfControl) {
			if (pos.equals(toPos)) {
				return true;
			}
		}
		return false;
	}
}
