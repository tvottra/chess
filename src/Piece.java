/**
 * 
 * @author Arjun Agrawal
 *
 */
public abstract class Piece {
	private String name;
	private int color;
	private Position pos;

	public Piece(String name, int color, Position pos) {
		this.name = name;
		this.color = color;
		this.pos = pos;
	}

	public String getName() {
		return name;
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
