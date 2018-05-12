import java.util.ArrayList;

/**
 * Class that represents a Queen chess Piece
 *
 * @author Jonathan Lim
 */
public class Queen extends Piece {

	/**
	 * Constructor to initialize the Queen
	 *
	 * @param color
	 *            - the Queen's color
	 * @param pos
	 *            - the Queen's Position
	 */
	public Queen(int color, Position pos) {
		super("Queen", color, pos, 9);
	}

	@Override
	/**
	 * Returns an array of the Positions that would be crossed if this Queen were to
	 * move to the given Position
	 *
	 * @param toPos
	 *            - the given Position
	 * @return an ArrayList of the Positions that would be crossed
	 */
	public ArrayList<Position> getCrossedPositions(Position toPos) {
		ArrayList<Position> positions = new ArrayList<Position>();
		Position fromPos = getPosition();
		// The two Positions are in the same row
		if (fromPos.getRow() == toPos.getRow()) {
			int i;
			if (fromPos.isLeftOf(toPos)) {
				i = 1;
			} else {
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
			if (fromPos.isAbove(toPos)) {
				i = 1;
			} else {
				i = -1;
			}
			for (int r = fromPos.getRow(); r != toPos.getRow(); r += i) {
				Position pos = new Position(r, fromPos.getColumn());
				positions.add(pos);
			}
		}
		if (getPosition().hasAbsSlopeOfOne(toPos)) {
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

	@Override
	/**
	 * Calculates the Queen's range of movement based on known board size and its
	 * current position.
	 * 
	 * @return the Queen's range of movement
	 */
	public ArrayList<Position> getRangeOfMovement() {
		Position pos = new Position(getPosition());
		ArrayList<Position> rom = new ArrayList<Position>();

		// Get the top vertical vector
		for (int r = (pos.getRow()) - 1; r >= 0; r--) {
			Position p = new Position(r, pos.getColumn());
			rom.add(p);
		}
		rom.add(pos);

		// Get the bottom vertical vector
		for (int r = pos.getRow() + 1; r < getSize(); r++) {
			Position p = new Position(r, pos.getColumn());
			rom.add(p);
		}
		rom.add(pos);

		// Get the left horizontal vector

		for (int c = pos.getColumn() - 1; c >= 0; c--) {
			Position p = new Position(pos.getRow(), c);
			rom.add(p);
		}
		rom.add(pos);

		// Get the right horizontal vector

		for (int c = pos.getColumn() + 1; c < getSize(); c++) {
			Position p = new Position(pos.getRow(), c);
			rom.add(p);
		}

		// Get the diagonals
		Position currentPos = new Position(pos);
		while (currentPos.isWithinBounds()) {
			rom.add(new Position(currentPos));
			currentPos.addToRow(-1);
			currentPos.addToColumn(1);
		}

		currentPos = new Position(getPosition());
		while (currentPos.isWithinBounds()) {
			rom.add(new Position(currentPos));
			currentPos.addToRow(-1);
			currentPos.addToColumn(-1);
		}

		currentPos = new Position(getPosition());
		while (currentPos.isWithinBounds()) {
			rom.add(new Position(currentPos));
			currentPos.addToRow(1);
			currentPos.addToColumn(-1);
		}

		currentPos = new Position(getPosition());
		while (currentPos.isWithinBounds()) {
			rom.add(new Position(currentPos));
			currentPos.addToRow(1);
			currentPos.addToColumn(1);
		}
		return rom;
	}

	@Override
	/**
	 * Checks whether the given Position is within this Queen's range of movement
	 *
	 * @param toPos
	 *            - the destination Position
	 * @return true if toPos is within this Queen's range of movement, false
	 *         otherwise
	 */
	public boolean isWithinRangeOfMovement(Position toPos) {
		return getRangeOfMovement().contains(toPos);
	}
}
