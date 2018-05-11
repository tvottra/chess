
/**
 * Class that represents a chess game containing Players and a Board
 *
 * @author Andrew Le
 */

import java.util.Scanner;
import greenfoot.*;

/**
 * @author Andrew Le
 */
public class Game extends World{
    private Player whitePlayer; // white
    private Player blackPlayer; // black
    private Board gameBoard;
    private Position chosenFromPos;
    
    private boolean firstTurnRun = true;
    private boolean firstPrint = true;
    private boolean fromPosChosen;
    private boolean toPosChosen;
    private boolean promoted = true;
    private boolean draw;
    private boolean stalemate;
    private boolean isWhiteTurn; // true if it is white's turn, false otherwise

    /**
     * Constructor to initialize the players, gameBoard, and game state
     */
    public Game(){
        super(80, 80, 10);
        whitePlayer = new Player("A", 0);
        blackPlayer = new Player("B", 1);
        gameBoard = new Board();
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                addObject(gameBoard.getBoard()[row][col], col * 10 + 4, row * 10 + 5);
              }
          }
        draw = false;
        stalemate = false;
        isWhiteTurn = true;     
    }
    
    /**
     * Constructor to initialize the players, gameBoard, and game state
     * 
     * @param name1
     *            - whitePlayer's name
     * @param name2
     *            - blackPlayer's name
     */
    public Game(String name1, String name2) {
        super(80, 80, 10);
        whitePlayer = new Player(name1, 0);
        blackPlayer = new Player(name2, 1);
        gameBoard = new Board();
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                addObject(gameBoard.getBoard()[row][col], col * 10 + 4, row * 10 + 5);
              }
          }
        draw = false;
        stalemate = false;
        isWhiteTurn = true;     
    }

    /**
     * Accessor method to get the board - for testing only
     * 
     * @return the board
     */
    public Board getBoard() {
        return gameBoard;
    }

    /**
     * Begins the chess game. The game ends when 1) a player has been checkmated, 2)
     * both players have agreed to a draw, 3) a player resigns, or 4) there is a
     * stalemate. Calls helper methods to display prompts and play the game
     */
    public void playGame() {
        
        if (gameIsOver() && firstPrint) {
                displayEndgame();
                firstPrint = false;
            }
            // White's turn
        else if (isWhiteTurn) {
                PlayerTurn(whitePlayer, blackPlayer);
            } // End White's turn
        else {
            // Black's turn
            PlayerTurn(blackPlayer, whitePlayer);
        } // End Black's turn
    }

    /**
       * Begins a player's turn; a player has 3 possible moves each turn: 1) move a
     * piece, 2) resign, or 3) request a draw
     * 
     * @param pl
     *            - the turn player
     * @param other
     *            - the opposing player
     */
    private void PlayerTurn(Player pl, Player other) {
        if(firstTurnRun){
            Title turn = new Title("It is " + pl.getName() + "'s turn.", 100, false);
            addObject(turn, getWidth() / 2, getWidth() / 2);
            Greenfoot.setSpeed(15);
            Greenfoot.delay(1);
            Greenfoot.setSpeed(50);
            removeObjects(getObjects(Title.class));
            firstTurnRun = false;
        }
        String key = Greenfoot.getKey();
        if(key != null){
            if(key.equals("s")){
                pl.setResign(true);
            }
            else if(key.equals("d")){
            addObject(new Title("Does " + pl.getName() + " accept Player " + other.getName() + "'s draw offer?", 25, false), getWidth() / 2, getWidth() / 2);
            String drawChoice = Greenfoot.getKey();
            if(drawChoice != null){
                if (drawChoice.equals("1")){
                setDraw(true);
                System.out.println(pl.getName() + " has accepted " + other.getName() + "'s draw offer.");
                } 
                else {
                    setDraw(false);
                    removeObjects(getObjects(Title.class));
                    System.out.println(pl.getName() + " has declined " + other.getName() + "'s draw offer.");
                }
            }
        }
        }
        else if(gameBoard.getBoard()[0][0].clicked() != null){
            if(!fromPosChosen){
                chosenFromPos = choosePosition();
                if(chosenFromPos != null){
                    if(isValidPiece(chosenFromPos.getRow(), chosenFromPos.getColumn(), pl.getNumber())){
                        fromPosChosen = true;
                    }
                }
            }
            else{
                Position toPos = choosePosition();
                if(toPos != null){
                    boolean castle = gameBoard.castleAble(chosenFromPos, toPos);
                    if(gameBoard.isLegalMove(chosenFromPos, toPos) || castle){
                        movePieceOnBoard(chosenFromPos, toPos);
                        Piece currentPiece = gameBoard.getTile(toPos).getPiece();
                        if (currentPiece != null && currentPiece.getName().equals("Pawn")
                        && ((Pawn) (currentPiece)).isWaitingForPromotion()) {
                            promoted = false;
                            promotion(currentPiece);
                        }
                        if(promoted){
                            gameBoard.getBoard()[0][0].setClicked(null);
                            isWhiteTurn = !isWhiteTurn;
                            fromPosChosen = false;
                            firstTurnRun = true;
                            update();
                        }
                        
                    }
                }
            }
            
        }
    }

    /**
     * Moves a Piece from one Tile to another onthe board of Tiles without checking
     * for legality; if a capture is made, adds the point value of the captured
     * piece to the turn player's score.
     *
     * @param fromPos
     *            - the Piece's current position
     * @param toPos
     *            - the Position to which the Piece will be moved
     * 
     */
    public void movePieceOnBoard(Position fromPos, Position toPos) {
        // If a capture is made, add points to the player's score
        if (gameBoard.getTile(toPos).hasPiece()) {
            Piece capturedPiece = gameBoard.getTile(toPos).getPiece();
            incrementScore(capturedPiece.getColor(), capturedPiece.getPointValue());
        }
        gameBoard.movePiece(fromPos, toPos);

    }

    /**
     * Checks to see if at least 1 of the conditions that would end the game is
     * present
     *
     * @return true if the game is over, false otherwise
     */
    private boolean gameIsOver() {
        if (whitePlayer.isCheckMated() || blackPlayer.isCheckMated() || draw || whitePlayer.isResigned()
                || blackPlayer.isResigned() || stalemate) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks the endgame state and prints the appropriate message
     */
    private void displayEndgame() {
        if (whitePlayer.isCheckMated()) {
            System.out.println(whitePlayer + " has been checkmated. " + blackPlayer + "wins!");
        }
        if (blackPlayer.isCheckMated()) {
            System.out.println(blackPlayer + " has been checkmated. " + whitePlayer + "wins!");
        }
        if (draw) {
            System.out.println("Both players have agreed to a draw.");
        }
        if (whitePlayer.isResigned()) {
            System.out.println(whitePlayer + " has resigned. " + blackPlayer + " wins!");
        }
        if (blackPlayer.isResigned()) {
            System.out.println(blackPlayer + " has resigned. " + whitePlayer + " wins!");
        }
        if (stalemate) {
            System.out.println("Stalemate: neither player wins.");
        }
    }

    /**
     * Prompts the user for the row and column indexes of the Piece to be moved
     *
     * @return a Position object with Piece's current location
     */
    private Position choosePosition() {
        Position pos = null;
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                if(gameBoard.getBoard()[row][col] == gameBoard.getBoard()[row][col].clicked()){
                    pos = new Position(row, col);
                }
            }
        }
        return pos;
    }

    /**
     * First checks whether the given row and column indexes are in bounds, then
     * checks whether there is a Piece of the correct color at the specified row and
     * column indexes
     * 
     * @param row
     *            - the row index
     * @param col
     *            - the column index
     * @param color
     *            - 0 for white, 1 for black
     * @return true if there is a Piece of the correct color at the row and column
     *         indexes, false otherwise
     */
    private boolean isValidPiece(int row, int col, int color) {
        if (gameBoard.getTile(row, col).hasPiece()) {

            return gameBoard.getTile(row, col).getPiece().getColor() == color;
        } else {
            return false;
        }
    }

    /**
     * Increments the score of the player with the color OPPOSITE of the given color
     * by the given value
     * 
     * @param color
     *            - 0 if black, 1 if white
     * @param val
     *            - the value of the captured piece
     * 
     */
    private void incrementScore(int color, int val) {
        if (color == 0) {
            blackPlayer.incrementScore(val); // add points to black
        } else {
            whitePlayer.incrementScore(val); // add points to white
        }
    }

    /**
     * Accessor method to get the player with the given color
     * 
     * @param color
     *            - 0 for white, 1 for black
     * @return white player if color = 0, black player if color = 1
     */
    public Player getPlayer(int color) {
        if (color == 0) {
            return whitePlayer;
        } else {
            return blackPlayer;
        }
    }

    /**
     * Mutator method that updates the draw status of the game
     * 
     * @param draw
     *            - true if there is a draw, false otherwise
     */
    public void setDraw(boolean draw) {
        this.draw = draw;
    }

    /**
     * Mutator method that updates the turn
     * 
     * @param turn
     *            - true if it is whitePlayer's turn, false otherwise
     */
    public void setTurn(boolean turn) {
        isWhiteTurn = turn;
    }

    /**
     * Mutator method that updates stalemate
     * 
     * @param stale
     *            - true if there is a stalemate, false otherwise
     */
    public void setStalemate(boolean stale) {
        stalemate = stale;
    }

    /**
     * Determines whether there is stalemate. A game is in stalemate if and only if:
     * 1) the turn player is not in check and the turn player has no legal moves; 2)
     * threefold repitition has occurred; 3) fifty-move rule applies
     *
     * @return true if there is a stalemate, false otherwise
     */
    private boolean stalemate() {
        // TO BE IMPLEMENTED
        return false;
    }

    /**
     * Prompts the user to choose a non-Pawn, non-King Piece to replace the given
     * Pawn. A white Pawn is promoted if it is on row 0; a black Pawn is promoted if
     * it is on row 7. The method removes the promoted Pawn from the board and
     * constructs the user's specified Piece at the current Position.
     * 
     * @param promotedPawn
     *            - the given promoted Pawn
     */
    private void promotion(Piece promotedPawn) {
        Scanner sc = new Scanner(System.in);
        int myColor = promotedPawn.getColor();
        Position currentPos = promotedPawn.getPosition();
        System.out.println(promotedPawn + " is awaiting promotion.");
        System.out.println("Choose a piece to replace " + promotedPawn + ":");
        int promotionChoice = sc.nextInt();
        System.out.println("(1) Promote to Knight");
        System.out.println("(2) Promote to Bishop");
        System.out.println("(3) Promote to Rook");
        System.out.println("(4) Promote to Queen");
        switch (promotionChoice) {
        case 1:
            gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(null);
            Piece knight = new Knight(myColor, currentPos);
            gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(knight);
            System.out.println("Promotion to Knight at " + currentPos);
            break;
        case 2:
            gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(null);
            Piece bishop = new Bishop(myColor, currentPos);
            gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(bishop);
            System.out.println("Promotion to Bishop at " + currentPos);
            break;
        case 3:
            gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(null);
            Piece rook = new Rook(myColor, currentPos);
            gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(rook);
            System.out.println("Promotion to Rook at " + currentPos);
            break;
        case 4:
            gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(null);
            Piece queen = new Queen(myColor, currentPos);
            gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(queen);
            System.out.println("Promotion to Queen at " + currentPos);
            break;
        }
        promoted = true;
    }

    /**
     * Not currently used
     */
    public void update() {
        gameBoard.updateHotSpots();
    }

    /**
     * Determines whether a checkmate has occurred. Method is incomplete; must find
     * a way to use findKingPosition() on the copy of the board
     */
    public void findCheckmate() {
        int color = gameBoard.getWhoIsCheckmated();
        if (color == 0) {
            whitePlayer.setCheckMate(true);
        } else if (color == 1) {
            blackPlayer.setCheckMate(true);
        }

    }
    
    public void act(){
        playGame();
    }
}