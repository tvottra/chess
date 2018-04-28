
/**
 * Class that represents a Rook chess piece
 */
import java.util.ArrayList;

/**
 * @author Andrew Le
 *
 */
public class Rook extends Piece {
	private ArrayList<Position> fieldOfControl;
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

	@Override
	public void setFieldOfControl() {
		Position pos = getPosition();
		fieldOfControl = null;
		fieldOfControl = new ArrayList<Position>();
		// Add all positions above current position (same column)
		for (int r = 0; r < pos.getRow(); r++) {
			Position p = new Position(r, pos.getColumn());
			fieldOfControl.add(p);
		}
		// Add all positions below current position (same column)
		for (int r = pos.getRow() + 1; r < 8; r++) {
			Position p = new Position(r, pos.getColumn());
			fieldOfControl.add(p);
		}
		// Add all positions left of current position (same row)
		for (int c = 0; c < pos.getColumn(); c++) {
			Position p = new Position(pos.getRow(), c);
			fieldOfControl.add(p);
		}
		// Add all positions left of current position (same row)
		for (int c = pos.getColumn() + 1; c < 8; c++) {
			Position p = new Position(pos.getRow(), c);
			fieldOfControl.add(p);
		}

	}

	@Override
	public boolean isWithinRangeOfMovement(Position toPos) {
		setFieldOfControl();
		for (Position pos : fieldOfControl) {
			if (pos.equals(toPos)) {
				return true;
			}
		}
		return false;
	}
}
