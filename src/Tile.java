/**
 * Class that represents a tile on the board
 *
 * @author Tommy Tran
 * @author Andrew Le (documentation)
 */
public class Tile {
    private Piece myPiece;
    private boolean isWhiteHotSpot;
    private boolean isBlackHotSpot;

    /**
     * Parameter constructor to initialize a Tile on the board
     *
     * @param piece          - the piece that occupies the Slot
     * @param isWhiteHotSpot - whether the Tile is within the range of movement of a white
     *                       Piece
     * @param isBlackHotSpot - whether the Tile is within the range of movement of a black
     *                       Piece
     */
    public Tile(Piece piece, boolean isWhiteHotSpot, boolean isBlackHotSpot) {
        this.myPiece = piece;
        this.isWhiteHotSpot = isWhiteHotSpot;
        this.isBlackHotSpot = isBlackHotSpot;
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
     * @param myPiece - the given Piece
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
     * @param whiteHotSpot - true if white has control of this Tile, false otherwise
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

    public boolean hasPiece() {
        return myPiece != null;
    }

    /**
     * Mutator method to set black control of this Tile
     *
     * @param blackHotSpot - true if black has control of this Tile, false otherwise
     */
    public void setIsBlackHotSpot(boolean blackHotSpot) {
        this.isBlackHotSpot = blackHotSpot;
    }

    public String toString() {
        return myPiece.toString() + " white control = " + isWhiteHotSpot + " black control = " + isBlackHotSpot;
    }

}
