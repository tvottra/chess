/**
 *
 * @author Tommy V. Tran
 * Represents a link of movements from an initial position to a final position.
 */

public class Vector {
	private Position fromPos;
	private Position toPos;

	public Vector(Position fromPos, Position toPos) {
		this.fromPos = new Position(fromPos);
		this.toPos = new Position(toPos);
	}

	public Vector(Vector other) {
		this.fromPos = other.getFromPos();
		this.toPos = other.getToPos();
	}

	public Position getFromPos() {
		return fromPos;
	}

	public void setFromPos(Position fromPos) {
		this.fromPos = fromPos;
	}

	public Position getToPos() {
		return toPos;
	}

	public void setToPos(Position toPos) {
		this.toPos = toPos;
	}

	@Override
	public String toString() {
		return fromPos + " -> " + toPos;
	}
}
