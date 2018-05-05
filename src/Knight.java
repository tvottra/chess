import java.util.ArrayList;

/**
 * Class that represents a Knihgt chess piece
 * 
 * @author Arjun Agrawal
 */
public class Knight extends Piece {

	/**
	 * Contructor to initialize the Pawn's color and position
	 * 
	 * @param color
	 *            - the Knight's color
	 * @param pos
	 *            - the Knight's Position
	 */
	public Knight(int color, Position pos) {
		super("Knight", color, pos, 3);
	}

	@Override
	/**
	 * Returns an array of the Positions that would be crossed if this Knight were
	 * to move to the given Position
	 *
	 * @param toPos
	 *            - the given Position
	 * @return an ArrayList of the Positions that would be crossed
	 */
	public ArrayList<Position> getCrossedPositions(Position toPos) {
		if (isWithinRangeOfMovement(toPos)) {
			ArrayList<Position> pos = new ArrayList<Position>();
			pos.add(toPos);
			return pos;
		}
		return null;
	}

	@Override
	/**
	 * Calculates the Knight's range of movement based on known board size and its
	 * current position. Positions are ordered ascending in terms of row then
	 * column. E.g., (0, 0), (0, 1), (0, 2), (1, 0)...
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

	/**
	 * Checks whether the given Position is within this Knight's range of movement
	 *
	 * @param move
	 *            - the destination Position
	 * @return true if toPos is within this Knight's range of movement, false
	 *         otherwise
	 */
	@Override
	public boolean isWithinRangeOfMovement(Position move) {
		return getRangeOfMovement().contains(move);
	}
}
