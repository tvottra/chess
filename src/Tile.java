/**
 * Class that represents a tile on the board
 *
 * @author Tommy V. Tran
 * @author Andrew Le
 */
public class Tile {
	private Piece myPiece;
	private boolean isWhiteHotSpot;
	private boolean isBlackHotSpot;

	/**
	 * Parameter constructor to initialize a Tile on the board
	 *
	 * @param piece
	 *            - the piece that occupies the Tile
	 * @param isWhiteHotSpot
	 *            - whether the Tile is checked by a white Piece Piece
	 * @param isBlackHotSpot
	 *            - whether the Tile is checked by a black Piece
	 */
	public Tile(Piece piece, boolean isWhiteHotSpot, boolean isBlackHotSpot) {
		this.myPiece = piece;
		this.isWhiteHotSpot = isWhiteHotSpot;
		this.isBlackHotSpot = isBlackHotSpot;
	}

	/**
	 * Constructor to initialize this Tile with the features of another Tile
	 * 
	 * @param other
	 *            - the other Tile
	 */
	public Tile(Tile other) {
		if (other.hasPiece()) {
			this.myPiece = Piece.createPiece(other.getPiece(), other.getPiece().hasMoved());
		} else {
			this.myPiece = Piece.createPiece(other.getPiece(), false);
		}
		this.isWhiteHotSpot = other.isWhiteHotSpot();
		this.isBlackHotSpot = other.isBlackHotSpot();
	}

	/**
	 * Clones a 2D Array of Tiles and returns the fresh copy
	 * 
	 * @param other
	 *            - initial 2D Array of Tiles
	 * @return A copy of the initial 2D Array of Tiles
	 */
	public static Tile[][] cloneTile2DArray(Tile[][] other) {

		Tile[][] copy = new Tile[other.length][other[0].length];

		for (int row = 0; row < other.length; row++) {
			for (int col = 0; col < other[0].length; col++) {
				copy[row][col] = new Tile(other[row][col]);
			}
		}
		return copy;
	}

	/**
	 * Accessor method to get the Piece on this Tile
	 *
	 * @return the Piece on this Tile
	 */
	public Piece getPiece() {
		return myPiece;
	}

	/**
	 * Mutator method to set the Piece on this Tile to the given Piece
	 *
	 * @param myPiece
	 *            - the given Piece
	 */
	public void setPiece(Piece myPiece) {
		this.myPiece = myPiece;
	}

	/**
	 * Determines whether this Tile has a Piece
	 *
	 * @return true if this Tile has a Piece, false otherwise
	 */
	public boolean hasPiece() {
		return myPiece != null;
	}

	/**
	 * Determines whether white checks this Tile
	 *
	 * @return true if white checks this Tile, false otherwise
	 */
	public boolean isWhiteHotSpot() {
		return isWhiteHotSpot;
	}

	/**
	 * Mutator method to set white control of this Tile
	 *
	 * @param whiteHotSpot
	 *            - true if white checks this Tile, false otherwise
	 */
	public void setIsWhiteHotSpot(boolean whiteHotSpot) {
		this.isWhiteHotSpot = whiteHotSpot;
	}

	/**
	 * Determines whether black checks this Tile
	 *
	 * @return true if black checks this Tile, false otherwise
	 */
	public boolean isBlackHotSpot() {
		return isBlackHotSpot;
	}

	/**
	 * Mutator method to set black control of this Tile
	 *
	 * @param blackHotSpot
	 *            - true if black checks this Tile, false otherwise
	 */
	public void setIsBlackHotSpot(boolean blackHotSpot) {
		this.isBlackHotSpot = blackHotSpot;
	}

	/**
	 * toString method for this tile
	 *
	 * @return the first letter of the name of the Piece on this Tile, followed by a
	 *         0 or a 1 based on the Piece's color. If the Piece is a Knight, "n"
	 *         instead of "K" is the first letter. If there is no piece on this
	 *         Tile, "XX" is returned.
	 */
	public String toString() {
		if (!hasPiece()) {
			return "--";
		} else if (myPiece.getName().equals("Knight")) {
			return String.format("%.1s" + myPiece.getColor(), "n");
		} else {
			return String.format("%.1s" + myPiece.getColor(), myPiece);
		}
	}
}
