/**
 * Class that represents a tile on the board
 * 
 * @author Tommy Tran
 * @author Andrew Le (documentation)
 * 
 */
public class Tile {
	private Piece myPiece;
	private boolean whiteControl;
	private boolean blackControl;

	/**
	 * Parameter constructor to initialize a Tile on the board
	 * 
	 * @param piece
	 *            - the piece that occupies the Slot
	 * @param whiteLeverage
	 *            - whether the Tile is within the range of movement of a white
	 *            Piece
	 * @param blackLeverage
	 *            - whether the Tile is within the range of movement of a black
	 *            Piece
	 */
	public Tile(Piece piece, boolean whiteControl, boolean blackControl) {
		this.myPiece = piece;
		this.whiteControl = whiteControl;
		this.blackControl = blackControl;
	}

	/**
	 * Accessor method to get the Piece on this Tile
	 * 
	 * @return the Piece on this Tile
	 */
	public Piece getMyPiece() {
		return myPiece;
	}

	/**
	 * Mutator method to set the Piece on this Tile to the given Piece
	 * 
	 * @param myPiece
	 *            - the given Piece
	 */
	public void setMyPiece(Piece myPiece) {
		this.myPiece = myPiece;
	}

	/**
	 * Determines whether white has control of this Tile
	 * 
	 * @return true if white has control over this Tile, false otherwise
	 */
	public boolean isWhiteControl() {
		return whiteControl;
	}

	/**
	 * Mutator method to set white control of this Tile
	 * 
	 * @param whiteControl
	 *            - true if white has control of this Tile, false otherwise
	 */
	public void setWhiteControl(boolean whiteControl) {
		this.whiteControl = whiteControl;
	}

	/**
	 * Determines whether black has control of this Tile
	 * 
	 * @return true if black has control over this Tile, false otherwise
	 */
	public boolean isBlackControl() {
		return blackControl;
	}

	/**
	 * Mutator method to set black control of this Tile
	 * 
	 * @param whiteControl
	 *            - true if black has control of this Tile, false otherwise
	 */
	public void setBlackControl(boolean blackControl) {
		this.blackControl = blackControl;
	}
	
	public String toString() {
		return myPiece.toString() + " white control = " + whiteControl + " black control = " + blackControl;
	}

}
