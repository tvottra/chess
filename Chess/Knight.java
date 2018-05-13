import java.util.ArrayList;

/**
 * Class that represents a Knight chess piece
 * 
 * @author Arjun Agrawal
 */
public class Knight extends Piece {

	/**
	 * Contructor to initialize the Pawn's color and position
	 * 
	 * @param color
	 *            the Knight's color
	 * @param pos
	 *            the Knight's Position
	 */
	public Knight(int color, Position pos) {
		super("Knight", color, pos, 3);
	}

	@Override
	/**
	 * Calculates the Knight's range of movement based on known board size and its
	 * current position.
	 *
	 * @return the Knight's range of movement
	 */
	public ArrayList<Position> getRangeOfMovement() {
		ArrayList<Position> field = new ArrayList<Position>();
		if (new Position(this.getPosition().getRow() + 2, this.getPosition().getColumn() + 1).isWithinBounds())
			field.add(new Position(this.getPosition().getRow() + 2, this.getPosition().getColumn() + 1));
		if (new Position(this.getPosition().getRow() + 2, this.getPosition().getColumn() - 1).isWithinBounds())
			field.add(new Position(this.getPosition().getRow() + 2, this.getPosition().getColumn() - 1));
		if (new Position(this.getPosition().getRow() - 2, this.getPosition().getColumn() - 1).isWithinBounds())
			field.add(new Position(this.getPosition().getRow() - 2, this.getPosition().getColumn() - 1));
		if (new Position(this.getPosition().getRow() - 2, this.getPosition().getColumn() + 1).isWithinBounds())
			field.add(new Position(this.getPosition().getRow() - 2, this.getPosition().getColumn() + 1));
		if (new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() + 2).isWithinBounds())
			field.add(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() + 2));
		if (new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() + 2).isWithinBounds())
			field.add(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() + 2));
		if (new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() - 2).isWithinBounds())
			field.add(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() - 2));
		if (new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() - 2).isWithinBounds())
			field.add(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() - 2));
		return field;
	}
}