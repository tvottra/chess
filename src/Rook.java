
import java.util.ArrayList;

/**
 * Class that represents a Rook chess piece
 * 
 * @author Andrew Le
 *
 */
public class Rook extends Piece {

	/**
	 * Constructor to initialize the Rook's color and Position
	 * 
	 * @param color
	 *            - the Rook's color
	 * @param pos
	 *            - the Rook's Position
	 */
	public Rook(int color, Position pos) {
		super("Rook", color, pos, 5);
	}

	@Override
	/**
	 * Returns an array of the Positions that would be crossed if this Rook were to
	 * move to the given Position
	 * 
	 * @param toPos
	 *            - the given Position
	 * @return an ArrayList of the Positions that would be crossed
	 */
	public ArrayList<Position> getCrossedPositions(Position toPos) {
		if (isWithinRangeOfMovement(toPos)) {
			ArrayList<Position> positions = new ArrayList<Position>();
			Position fromPos = getPosition();
			if (fromPos.getRow() == toPos.getRow()) {
				for (int c = fromPos.getColumn(); c < toPos.getColumn(); c++) {
					Position pos = new Position(fromPos.getRow(), c);
					positions.add(pos);
				}
			}
			if (fromPos.getColumn() == toPos.getColumn()) {
				for (int r = fromPos.getRow(); r < toPos.getRow(); r++) {
					Position pos = new Position(r, fromPos.getColumn());
					positions.add(pos);
				}
			}
			return positions;
		} else {
			return null;
		}
	}

	@Override
	/**
	 * Calculates the Rook's range of movement based on known board size and its
	 * current position. Positions are ordered ascending in terms of row then
	 * column. E.g., (0, 0), (0, 1), (0, 2), (1, 0)...
	 * 
	 * @return the Rook's range of movement
	 */
	public ArrayList<Position> getRangeOfMovement() {
		Position pos = getPosition();
		ArrayList<Position> fieldOfControl = new ArrayList<Position>();
		fieldOfControl = new ArrayList<Position>();
		// Add all positions above current position (same column)
		for (int r = pos.getRow() - 1; r >= 0; r--) {
			Position p = new Position(r, pos.getColumn());
			fieldOfControl.add(p);
		}
		fieldOfControl.add(pos);

		// Add all positions below current position (same column)
		for (int r = pos.getRow() + 1; r < getSize(); r++) {
			Position p = new Position(r, pos.getColumn());
			fieldOfControl.add(p);
		}
		fieldOfControl.add(pos);

		// Add all positions left of current position (same row)
		for (int c = pos.getColumn() - 1; c >= 0; c--) {
			Position p = new Position(pos.getRow(), c);
			fieldOfControl.add(p);
		}
		fieldOfControl.add(pos);

		// Add all positions right of current position (same row)
		for (int c = pos.getColumn() + 1; c < getSize(); c++) {
			Position p = new Position(pos.getRow(), c);
			fieldOfControl.add(p);
		}
		return fieldOfControl;
	}

	@Override
	/**
	 * Checks whether the given Position is within this Rook's range of movement
	 * 
	 * @param toPos
	 *            - the destination Position
	 * @return true if toPos is within this Rook's range of movement, false
	 *         otherwise
	 */
	public boolean isWithinRangeOfMovement(Position toPos) {
		ArrayList<Position> fieldOfControl = getRangeOfMovement();
		for (Position pos : fieldOfControl) {
			if (pos.equals(toPos)) {
				return true;
			}
		}
		return false;
	}
}
