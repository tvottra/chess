/**
 * Represents a Slot on the board
 */
public class Slot {
	private Piece myPiece;
	private boolean whiteControl;
	private boolean blackControl;

	/**
	 * Parameter constructor to initialize a Slot that the board has
	 * @param piece What piece occupies the Slot
	 * @param whiteLeverage whether the Slot is within the range of movement of a white Piece
	 * @param blackLeverage whether the Slot is within the range of movement of a black Piece
	 */
	public Slot(Piece piece, boolean whiteControl, boolean blackControl) {
		this.myPiece = piece;
		this.whiteControl = whiteControl;
		this.blackControl = blackControl;
		this.row = row;
		this.column = column;
	}

	public Piece getMyPiece() {
		return myPiece;
	}

	public void setMyPiece(Piece myPiece) {
		this.myPiece = myPiece;
	}

	public boolean isWhiteControl() {
		return whiteControl;
	}

	public void setWhiteControl(boolean whiteControl) {
		this.whiteControl = whiteControl;
	}

	public boolean isBlackControl() {
		return blackControl;
	}

	public void setBlackControl(boolean blackControl) {
		this.blackControl = blackControl;
	}

}
