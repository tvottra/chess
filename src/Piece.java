/**
 * Abstract class that represents a chess piece
 * 
 * @author Arjun Agrawal
 * @author Andrew Le
 *
 */
public abstract class Piece {
	private String name;
	private int color; // 0 is white, 1 is black
	private Position pos;
	private Position[][] fieldOfControl;
	private final int POINT_VALUE; // Point value of each piece

	/**
	 * Constructor
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
	 * Accessor method to return the piece's name
	 * 
	 * @return the peice's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Accessor method to return the piece's color
	 * 
	 * @return the piece's color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Accessor method to return the piece's Position
	 * 
	 * @return the Piece's Position
	 */
	public Position getPosition() {
		return pos;
	}

	/**
	 * Accessor method to return the piece's field of control
	 * 
	 * @return the piece's field of control
	 */
	public Position[][] getFieldOfControl() {
		return fieldOfControl;
	}

	/**
	 * Accessor method to return the piece's point value
	 * 
	 * @return
	 */
	public int getPointValue() {
		return POINT_VALUE;
	}

	/**
	 * toString
	 * @return Piece Name + "at" + Position
	 */
	public String toString() {
		name + " at " + pos;
	}

	/**
	 * Returns the Positions that would be crossed if this piece were to move to the
	 * given destination
	 * 
	 * @param toPos
	 *            - the destination position
	 * @return an array of the positions that would be crossed
	 */
	public Position[][] move(Position toPos) {
		if (isWithinRangeOfMovement(toPos)) {
			return fieldOfControl;
		} else {
			return null;
		}

	}

	public abstract void setFieldOfControl();

	/**
	 * Checks whether to given Position is within this Piece's range of movement
	 * 
	 * @param toPos
	 *            - the destination Position
	 * @return true if toPos is within this Piece's range of movement, false
	 *         otherwise
	 */
	public abstract boolean isWithinRangeOfMovement(Position toPos);

}
