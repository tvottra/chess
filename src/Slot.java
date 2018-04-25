
public class Slot {
	private Piece myPiece;
	private boolean whiteControl;
	private boolean blackControl;

	public Slot(Piece piece, boolean whiteControl, boolean blackControl) {
		this.myPiece = piece;
		this.whiteControl = whiteControl;
		this.blackControl = blackControl;
	}

	public Piece getPiece() {
		return myPiece;
	}

	public boolean getWhiteControl() {
		return whiteControl;
	}

	public boolean getBlackControl() {
		return blackControl;
	}

}
