/**
 * 
 * @author Arjun Agrawal
 *
 */
public abstract class Piece {
	private String name;
	private int color;

	public Piece(String name, int color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public int getColor() {
		return color;
	}
}
