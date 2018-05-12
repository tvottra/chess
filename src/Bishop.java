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
	 *            the Bishop's color
	 * @param pos
	 *            the Bishop's Position
	 */
	public Bishop(int color, Position pos) {
		super("Bishop", color, pos, 3);
	}

	@Override
	/**
	 * Calculates the Bishop's range of movement based on known board size and its
	 * current position.
	 * 
	 * @return the Bishop's range of movement
	 */
	public ArrayList<Position> getRangeOfMovement() {
		ArrayList<Position> rom = new ArrayList<Position>();

		// Get quadrant 1
		Position pos1 = new Position(getPosition());
		while (pos1.isWithinBounds()) {
			rom.add(new Position(pos1));
			pos1.addToRow(-1);
			pos1.addToColumn(1);
		}

		// Get quadrant 2
		Position pos2 = new Position(getPosition());
		while (pos2.isWithinBounds()) {
			rom.add(new Position(pos2));
			pos2.addToRow(-1);
			pos2.addToColumn(-1);
		}

		// Get quadrant 3
		Position pos3 = new Position(getPosition());
		while (pos3.isWithinBounds()) {
			rom.add(new Position(pos3));
			pos3.addToRow(1);
			pos3.addToColumn(-1);
		}

		// Get quadrant 4
		Position pos4 = new Position(getPosition());
		while (pos4.isWithinBounds()) {
			rom.add(new Position(pos4));
			pos4.addToRow(1);
			pos4.addToColumn(1);
		}
		return rom;
	}
}
