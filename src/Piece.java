/**
 * 
 * @author Arjun Agrawal
 *
 */
public abstract class Piece {
	private int color; // 0 is white, 1 is black
	private Position pos;
	private final int POINT_VALUE; // Point value of each piece

	/**
	 * Add an int field for the "rank" of each piece
	 */

	public Piece(int color, Position pos, int pointVal) {
		this.color = color;
		this.pos = pos;
		POINT_VALUE = pointVal;
	}

	public int getColor() {
		return color;
	}

	public Position getPosition() {
		return pos;
	}

	public void setPosition(Position move, Board board) {
		isLegal(move, board);
		System.out.println("Illegal move");
		this.pos = move;
	}

	public abstract boolean isLegal(Position move, Board board);

}
