/**
 * Class that represents a tile on the board
 *
 * @author Tommy Tran
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
	 *            - the piece that occupies the Slot
	 * @param isWhiteHotSpot
	 *            - whether the Tile is within the range of movement of a white
	 *            Piece
	 * @param isBlackHotSpot
	 *            - whether the Tile is within the range of movement of a black
	 *            Piece
	 */
	public Tile(Piece piece, boolean isWhiteHotSpot, boolean isBlackHotSpot) {
		this.myPiece = piece;
		this.isWhiteHotSpot = isWhiteHotSpot;
		this.isBlackHotSpot = isBlackHotSpot;
	}

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
	 * Determines whether white has control of this Tile
	 *
	 * @return true if white has control over this Tile, false otherwise
	 */
	public boolean isWhiteHotSpot() {
		return isWhiteHotSpot;
	}

	/**
	 * Mutator method to set white control of this Tile
	 *
	 * @param whiteHotSpot
	 *            - true if white has control of this Tile, false otherwise
	 */
	public void setIsWhiteHotSpot(boolean whiteHotSpot) {
		this.isWhiteHotSpot = whiteHotSpot;
	}

	/**
	 * Determines whether black has control of this Tile
	 *
	 * @return true if black has control over this Tile, false otherwise
	 */
	public boolean isBlackHotSpot() {
		return isBlackHotSpot;
	}

	/**
	 * Mutator method to set black control of this Tile
	 *
	 * @param blackHotSpot
	 *            - true if black has control of this Tile, false otherwise
	 */
	public void setIsBlackHotSpot(boolean blackHotSpot) {
		this.isBlackHotSpot = blackHotSpot;
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
	 * toString method for this tile
	 *
	 * @return this tile in the following format: [Piece] white control =
	 *         [true/false] black control [true/false]
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
