/**
 * 
 * @author Arjun Agrawal
 *
 */
public abstract class Piece {

	private int color;
	private Position pos;
	protected final int POINT_VALUE;

	/**
	 * Add an int field for the "rank" of each piece
	 */

	public Piece(int color, Position pos) {
		this.color = color;
		this.pos = pos;
	}

	public int getColor() {
		return color;
	}

	public Position getPosition() {
		return pos;
	}

	public void setPosition(Position move) {
		check(move);
		this.pos = move;
	}

	public abstract boolean check(Position move);

}
