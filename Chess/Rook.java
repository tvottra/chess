
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
	 *            the Rook's color
	 * @param pos
	 *            the Rook's Position
	 */
	public Rook(int color, Position pos) {
		super("Rook", color, pos, 5);
	}

	@Override
	/**
	 * Calculates the Rook's range of movement based on known board size and its
	 * current position.
	 * 
	 * @return the Rook's range of movement
	 */
	public ArrayList<Position> getRangeOfMovement() {
		Position pos = getPosition();
		ArrayList<Position> rom = new ArrayList<Position>();
		rom = new ArrayList<Position>();
		// Add all positions above current position (same column)
		for (int r = pos.getRow() - 1; r >= 0; r--) {
			Position p = new Position(r, pos.getColumn());
			rom.add(p);
		}
		rom.add(pos);

		// Add all positions below current position (same column)
		for (int r = pos.getRow() + 1; r < getSize(); r++) {
			Position p = new Position(r, pos.getColumn());
			rom.add(p);
		}
		rom.add(pos);

		// Add all positions left of current position (same row)
		for (int c = pos.getColumn() - 1; c >= 0; c--) {
			Position p = new Position(pos.getRow(), c);
			rom.add(p);
		}
		rom.add(pos);

		// Add all positions right of current position (same row)
		for (int c = pos.getColumn() + 1; c < getSize(); c++) {
			Position p = new Position(pos.getRow(), c);
			rom.add(p);
		}
		return rom;
	}
}