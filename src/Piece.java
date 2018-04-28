import java.util.ArrayList;

/**
 * Abstract class that represents a chess piece
 * 
 * @author Arjun Agrawal
 * @author Andrew Le (documentation)
 *
 */
public abstract class Piece {
	private String name;
	private int color; // 0 is white, 1 is black
	private Position pos;
	private final int POINT_VALUE; // Point value of each piece
	private final int SIZE = 8; // the size of a row/column on the Board

	/**
	 * Constructor that initializes the piece's name, color, Position, and point
	 * value
	 * 
	 * @param name
	 *            - the name of the piece
	 * @param color
	 *            - the color of the piece
	 * @param pos
	 *            - the initial position of the piece
	 * @param pointVal
	 *            - the point value of the piece
	 */
	public Piece(String name, int color, Position pos, int pointVal) {
		this.name = name;
		this.color = color;
		this.pos = pos;
		POINT_VALUE = pointVal;
	}

	/**
	 * Accessor method to get the piece's name
	 * 
	 * @return the peice's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Accessor method to get the piece's color
	 * 
	 * @return the piece's color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Accessor method to get the piece's Position
	 * 
	 * @return the Piece's Position
	 */
	public Position getPosition() {
		return pos;
	}

	/**
	 * Accessor method to return the piece's point value
	 * 
	 * @return the Piece's point value
	 */
	public int getPointValue() {
		return POINT_VALUE;
	}

	/**
	 * Accessor method to return the SIZE of a row/column on the Board
	 * 
	 * @return the size of a row/column
	 */
	public int getSIZE() {
		return SIZE;
	}

	/**
	 * toString method to print this Piece's information
	 * 
	 * @return a String in the following format: [Piece Name] + " at " + [Position]
	 */
	public String toString() {
		return name + " at " + pos;
	}

	/**
	 * Returns an array of the Positions that would be crossed if this Piece were to
	 * move to the given Position
	 * 
	 * @param toPos
	 *            - the given Position
	 * @return an ArrayList of the Positions that would be crossed
	 */
	public abstract ArrayList<Position> move(Position toPos);

	/**
	 * Calculates the Piece's field of control based on known board size and its
	 * current position.
	 * 
	 * @return the Piece's field of control
	 */
	public abstract ArrayList<Position> getFieldOfControl();

	/**
	 * Checks whether the given Position is within this Piece's range of movement
	 * 
	 * @param toPos
	 *            - the destination Position
	 * @return true if toPos is within this Piece's range of movement, false
	 *         otherwise
	 */
	public abstract boolean isWithinRangeOfMovement(Position toPos);

}
