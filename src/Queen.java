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
	 *            the Queen's color
	 * @param pos
	 *            the Queen's Position
	 */
	public Queen(int color, Position pos) {
		super("Queen", color, pos, 9);
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
}
