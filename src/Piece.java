import java.util.ArrayList;

/**
 * Abstract class that represents a chess piece
 *
 * @author Arjun Agrawal
 * @author Andrew Le
 * @author Tommy V. Tran
 */
public abstract class Piece {
	private String name;
	private int color; // 0 is white, 1 is black
	private Position pos;
	private final int POINT_VALUE; // point value of each piece
	private final int SIZE = 8; // the size of a row/column on the Board
	private boolean hasMoved;

	/**
	 * Constructor that initializes the Piece's name, color, Position, and point
	 * value
	 *
	 * @param name
	 *            the name of the piece
	 * @param color
	 *            the color of the piece
	 * @param pos
	 *            the initial position of the piece
	 * @param pointVal
	 *            the point value of the piece
	 */
	public Piece(String name, int color, Position pos, int pointVal) {
		this.name = name;
		this.color = color;
		this.pos = new Position(pos);
		POINT_VALUE = pointVal;
		hasMoved = false;
	}

	/**
	 * Constructor to make a new Piece out of an existing one
	 *
	 * @param other
	 *            some other Piece
	 */
	public Piece(Piece other) {
		this.name = other.getName();
		this.color = other.getColor();
		this.pos = new Position(other.getPosition());
		POINT_VALUE = getPointValue();
		hasMoved = other.hasMoved();
	}

	/**
	 * Accessor method to get the Piece's name
	 *
	 * @return the Piece's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Accessor method to get the Piece's color
	 *
	 * @return the Piece's color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * Accessor method to get the Piece's Position
	 *
	 * @return the Piece's Position
	 */
	public Position getPosition() {
		return pos;
	}

	/**
	 * Mutator method to change the Piece's Position to the given Position
	 *
	 * @param pos
	 *            the given Position
	 */
	public void setPosition(Position pos) {
		this.pos = pos;
	}

	/**
	 * Accessor method to return the Piece's point value
	 *
	 * @return the Piece's point value
	 */
	public int getPointValue() {
		return POINT_VALUE;
	}

	/**
	 * Accessor method to return the size of a row/column on the Board
	 *
	 * @return the size of a row/column
	 */
	public int getSize() {
		return SIZE;
	}

	/**
	 * Accessor method to get this Piece's move state
	 * 
	 * @return true if this Piece has already moved, false otherwise
	 */
	public boolean hasMoved() {
		return hasMoved;
	}

	/**
	 * Mutator method that updates this Piece's move status
	 * 
	 * @param moveState
	 *            true if the Piece has already moved, false otherwise
	 */
	public void setHasMoved(boolean moveState) {
		hasMoved = moveState;
	}

	/**
	 * Compares this Piece's color with the given Piece's color
	 * 
	 * @param other
	 *            the given Piece
	 * @return true if the Pieces are the same color, false otherwise
	 */
	public boolean isSameColorAs(Piece other) {
		return color == other.getColor();
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
	 * Calculates the Piece's range of movement based on known board size and its
	 * current position; the range of movement is defined as all the possible
	 * Positions to which this Piece could move if there were no other Pieces on the
	 * board
	 *
	 * @return the Piece's range of movement
	 */
	public abstract ArrayList<Position> getRangeOfMovement();

	/**
	 * Makes and returns a copy of the Piece, based on the Piece's name
	 * 
	 * @param other
	 *            Piece from which the copy is made
	 * @param moveState
	 *            - true if this Piece has already moved, false otherwise
	 * @return a fresh copy of the Piece or null if the parameter is not a valid
	 *         Piece in the first place
	 */
	public static Piece createPiece(Piece other, boolean moveState) {
		if (other == null) {
			return null;
		}
		Piece piece;
		switch (other.getName()) {
		case "Bishop":
			piece = new Bishop(other.getColor(), other.getPosition());
			piece.setHasMoved(moveState);
			break;
		case "King":
			piece = new King(other.getColor(), other.getPosition());
			piece.setHasMoved(moveState);
			break;
		case "Knight":
			piece = new Knight(other.getColor(), other.getPosition());
			piece.setHasMoved(moveState);
			break;

		case "Pawn":
			piece = new Pawn(other.getColor(), other.getPosition());
			piece.setHasMoved(moveState);
			break;

		case "Queen":
			piece = new Queen(other.getColor(), other.getPosition());
			piece.setHasMoved(moveState);
			break;

		case "Rook":
			piece = new Rook(other.getColor(), other.getPosition());
			piece.setHasMoved(moveState);
			break;
		default:
			System.out.println("Error in creating a copy of the Piece.");
			piece = null;
			break;
		}
		return piece;
	}
}
