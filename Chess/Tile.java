import greenfoot.*;
/**
 * Class that represents a tile on the board
 *
 * @author Tommy Tran
 * @author Andrew Le
 * @author Jonathan Lim
 */
public class Tile extends Actor{
    private Piece myPiece;
    private boolean isWhiteHotSpot;
    private boolean isBlackHotSpot;
    private static Tile clicked;
    GreenfootImage image = new GreenfootImage(100, 100);
    int transparency = 225;

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

    /**
     * Parameter constructor to initialize a Tile on the board
     *
     * @param other - another Tile
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
    
    /**
     * Method to update image of the Tile based on its piece
     */
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
        image.setTransparency(transparency);
        setImage(image);
    }
    
    /**
     * Method of acting of Tile; updates image and checks if Tile is clicked
     */
    public void act(){
        updateImage();
        if(Greenfoot.mouseClicked(this)){
                clicked = this;
        }
    }
    
    /**
     * Method to return the Tile that has been clicked
     * @return clicked - clicked Tile
     */
    public Tile clicked(){
        return clicked;
    }
    
    /**
     * Method to set which Tile is clicked
     * @param c - new clicked Tile
     */
    public void setClicked(Tile c){
        clicked = c;
    }
    
    	/**
	 * Clones a 2D Array of Tiles and returns the fresh copy
	 * 
	 * @param other
	 *            initial 2D Array of Tiles
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
	 * Method to set Transparency of the image of Tile
	 */
	public void setTransparency(int i){
	    transparency = i;
	}
}