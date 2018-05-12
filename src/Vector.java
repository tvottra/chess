/**
 *
 * @author Tommy V. Tran
 * Represents a link of movement from an initial position to a final position.
 */

public class Vector {
	private Position fromPos;
	private Position toPos;

	/**
	 * Constructor to initialize the vector
	 * @param fromPos the initial position
	 * @param toPos the final position
	 */
	public Vector(Position fromPos, Position toPos) {
		this.fromPos = new Position(fromPos);
		this.toPos = new Position(toPos);
	}

	/**
	 * Constructor to initialize the vector based on another vector
	 * @param other some other Vector
	 */
	public Vector(Vector other) {
		this.fromPos = other.getFromPos();
		this.toPos = other.getToPos();
	}

	/**
	 * Getter method for the initial position
	 * @return the initial position
	 */
	public Position getFromPos() {
		return fromPos;
	}

	/**
	 * Setter method for the initial position
	 * @param fromPos what initial position should be set to
	 */
	public void setFromPos(Position fromPos) {
		this.fromPos = fromPos;
	}

	/**
	 * Getter method for the final position
	 * @return the final position
	 */
	public Position getToPos() {
		return toPos;
	}

	/**
	 * Setter method for the final positoin
	 * @param toPos what the final position should be set to
	 */
	public void setToPos(Position toPos) {
		this.toPos = toPos;
	}

	@Override
	/**
	 * Return the vector as a String
	 * @return vector in the format fromPos -> toPos
	 */
	public String toString() {
		return fromPos + " -> " + toPos;
	}
}
