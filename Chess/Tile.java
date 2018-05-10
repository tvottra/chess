import greenfoot.*;
/**
 * Class that represents a tile on the board
 *
 * @author Tommy Tran
 * @author Andrew Le
 */
public class Tile extends Actor{
    private Piece myPiece;
    private boolean isWhiteHotSpot;
    private boolean isBlackHotSpot;
    private static Tile clicked;
    GreenfootImage image = new GreenfootImage(100, 100);

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
        updateImage();
    }

    public Tile(Tile other) {
        this.myPiece = Piece.createPiece(other.getPiece());
        this.isWhiteHotSpot = other.isWhiteHotSpot();
        this.isBlackHotSpot = other.isBlackHotSpot();
        updateImage();
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

    /**
     * Mutator method to set black control of this Tile
     *
     * @param blackHotSpot - true if black has control of this Tile, false otherwise
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
     * [true/false] black control [true/false]
     */
    public String toString() {
        if (!hasPiece()) {
            return "--";
        } else if (myPiece.getName().equals("Knight")) {
            return   String.format("%.1s" + myPiece.getColor(), "n");
        } else {
            return String.format("%.1s" + myPiece.getColor(), myPiece);
        }

    }
    
    
    public void updateImage(){
        if(getPiece() == null){
            image = new GreenfootImage(100, 100);
        }
        else if(getPiece().getName().equals("Pawn")){
            if(getPiece().getColor() == 0){
                image = new GreenfootImage("90pBrigette.png");
            }
            else{
                image = new GreenfootImage("90pBrigetteBlack.png");
            }
        }
        else if(getPiece().getName().equals("Knight")){
            if(getPiece().getColor() == 0){
                image = new GreenfootImage("90pOrisa.png");
            }
            else{
                image = new GreenfootImage("90pOrisaBlack.png");
            }
        }
        else if(getPiece().getName().equals("Bishop")){
            if(getPiece().getColor() == 0){
                image = new GreenfootImage("90pHanzo.png");
            }
            else{
                image = new GreenfootImage("90pHanzoBlack.png");
            }
        }
        else if(getPiece().getName().equals("Queen")){
            if(getPiece().getColor() == 0){
                image = new GreenfootImage("90pMercy.png");
            }
            else{
                image = new GreenfootImage("90pMercyBlack.png");
            }
        }
        else if(getPiece().getName().equals("King")){
            if(getPiece().getColor() == 0){
                image = new GreenfootImage("90pGenji.png");
            }
            else{
                image = new GreenfootImage("90pGenjiBlack.png");
            }
        }
        else if(getPiece().getName().equals("Rook")){
            if(getPiece().getColor() == 0){
                image = new GreenfootImage("90pReinhardt.png");
            }
            else{
                image = new GreenfootImage("90pReinhardtBlack.png");
            }
        }
        if (clicked == this){
            image.setTransparency(50);
        }
        setImage(image);
    }
    
    public void act(){
        updateImage();
        if(Greenfoot.mouseClicked(this)){
                clicked = this;
        }
    }
    
    public Tile clicked(){
        return clicked;
    }
    
    public void setClicked(Tile c){
        clicked = c;
    }
}