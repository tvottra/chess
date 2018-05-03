import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(int color, Position pos) {
		super("Queen", color, pos, 9);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns an array of the Positions that would be crossed if this Piece were to
	 * move to the given Position
	 * 
	 * @param toPos
	 *            - the given Position
	 * @return an ArrayList of the Positions that would be crossed
	 */
	public ArrayList<Position> move(Position toPos){
		ArrayList<Position> positions = new ArrayList<Position>();
		Position fromPos = getPosition();
		// The two Positions are in the same row
		if (fromPos.getRow() == toPos.getRow()) {
			int i;
			if(fromPos.isLeftOf(toPos)) {
				i = 1;
			}
			else {
				i = -1;
			}
			for (int c = fromPos.getColumn(); c != toPos.getColumn(); c += i) {
				Position pos = new Position(fromPos.getRow(), c);
				positions.add(pos);
			}
		}
		// The two Positions are in the same column
		if (fromPos.getColumn() == toPos.getColumn()) {
			int i;
			if(fromPos.isAbove(toPos)) {
				i = 1;
			}
			else {
				i = -1;
			}
			for (int r = fromPos.getRow(); r != toPos.getRow(); r += i) {
				Position pos = new Position(r, fromPos.getColumn());
				positions.add(pos);
			}
		}
		if (Math.abs(getPosition().slopeTo(toPos)) == 1.0) {
			int r, c;
			if (fromPos.isAbove(toPos)) {
				r = 1;
			} else {
				r = -1;
			}
			if (fromPos.isLeftOf(toPos)) {
				c = 1;
			} else {
				c = -1;
			}
			while (!fromPos.equals(toPos)) {
				positions.add(new Position(fromPos));
				fromPos.addToRow(r);
				fromPos.addToColumn(c);
			}
		}
		return positions;
	}

	/**
	 * Calculates the Piece's field of control based on known board size and its
	 * current position. Positions are ordered ascending in terms of row then column. E.g., (0, 0), (0, 1), (0, 2), (1, 0)...
	 * 
	 * @return the Piece's field of control
	 */
	public ArrayList<Position> getFieldOfControl(){
		Position pos = new Position(getPosition());
		ArrayList<Position> fieldOfControl = new ArrayList<Position>();
		fieldOfControl = new ArrayList<Position>();

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

		while (pos.isWithinBounds()) {
			fieldOfControl.add(new Position(pos));
			pos.addToRow(-1);
			pos.addToColumn(1);
		}

		pos = new Position(getPosition());
		while (pos.isWithinBounds()) {
			fieldOfControl.add(new Position(pos));
			pos.addToRow(-1);
			pos.addToColumn(-1);
		}

		pos = new Position(getPosition());
		while (pos.isWithinBounds()) {
			fieldOfControl.add(new Position(pos));
			pos.addToRow(1);
			pos.addToColumn(-1);
		}

		pos = new Position(getPosition());
		while (pos.isWithinBounds()) {
			fieldOfControl.add(new Position(pos));
			pos.addToRow(1);
			pos.addToColumn(1);
		}
		return fieldOfControl;
	}

	/**
	 * Checks whether the given Position is within this Piece's range of movement
	 * 
	 * @param toPos
	 *            - the destination Position
	 * @return true if toPos is within this Piece's range of movement, false
	 *         otherwise
	 */
	public boolean isWithinRangeOfMovement(Position toPos) {
		return toPos.isWithinBounds() && (Math.abs(getPosition().slopeTo(toPos)) == 1.0
										|| getPosition().getRow() == toPos.getRow()
										|| getPosition().getColumn() == toPos.getColumn());
	}
}
