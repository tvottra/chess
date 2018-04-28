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
	private Position[] fieldOfControl;
	private final int POINT_VALUE; // Point value of each piece

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
	 * @return an array of the Positions that would be crossed
	 */
	public Position[] move(Position toPos) {
		if (isWithinRangeOfMovement(toPos)) {
			return fieldOfControl;
		} else {
			return null;
		}

	}

	/**
	 * Accessor method to get this Piece's field of control
	 * 
	 * @return the Piece's field of Control
	 */
	public Position[] getFieldOfControl() {
		return fieldOfControl;
	}

	/**
	 * Updates this Piece's field of control	 
	 */
	public abstract void setFieldOfControl();

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
