import java.util.*;
import greenfoot.*;

/**
 * Class that represents a chess game containing Players and a Board
 *
 * @author Andrew Le
 * @author Jonathan Lim
 */
public class Game extends World{
    private Player whitePlayer; // white
    private Player blackPlayer; // black
    private Board gameBoard;
    private Position chosenFromPos;
    private Position chosenToPos;
    private Button playAgain = new Button("Play Again?");
    
    private boolean firstTurnRun = true;
    private boolean firstPrint = true;
    private boolean startDraw;
    
    private boolean fromPosChosen;
    private boolean toPosChosen;
    
    private boolean promoted = true;
    
    private boolean singlePlayer;
    
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
     */
    public Game(String name){
        super(80, 80, 10);
        singlePlayer = true;
        gameBoard = new Board();
        whitePlayer = new Player(name, 0);
        blackPlayer = new AI("God", 1, gameBoard);
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
        update();
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
        if (gameIsOver()) {
                gameBoard.getBoard()[0][0].setClicked(null);
                if(playAgain.clicked()){
                    Greenfoot.setWorld(new OpeningScreen());
                }
                if(firstPrint){
                    addObject(playAgain, getWidth() / 2, 3 * getWidth() / 5);
                    displayEndgame();
                    firstPrint = false;
                }
            }
            // White's turn
        else if (isWhiteTurn && firstPrint) {
                PlayerTurn(whitePlayer, blackPlayer);
            } // End White's turn
        else if(firstPrint){
            if(singlePlayer){
                startAIPlayerTurn((AI) blackPlayer, whitePlayer);
            }
            else{
               // Black's turn
               PlayerTurn(blackPlayer, whitePlayer);
            }
        } // End Black's turn
    }

    /**
     * Run a player's turn; a player has 5 possible moves each turn: 1) move a
     * piece, 2) resign, 3) request a draw, 4)check the current player,
     * 5)check the player's current captured pieces
     * 
     * @param pl
     *            - the turn player
     * @param other
     *            - the opposing player
     */
    private void PlayerTurn(Player pl, Player other) {
        if(firstTurnRun){
            Title turn = new Title("It is " + pl.getName() + "'s turn.", 100);
            addObject(turn, getWidth() / 2, getWidth() / 2);
            Greenfoot.setSpeed(15);
            Greenfoot.delay(1);
            Greenfoot.setSpeed(50);
            removeObjects(getObjects(Title.class));
            firstTurnRun = false;
        }
            String key = null;
            if(promoted){
                key = Greenfoot.getKey();
            }
            if(key != null && !startDraw){  
                //Press 2 to start draw request
                if(key.equals("2")){
                    startDraw = true;
                    addObject(new Title("Does " + pl.getName() + " accept Player " + other.getName() + "'s draw offer?", 50), getWidth() / 2, getWidth() / 2);
                }
                //Press 3 to resign
                else if(key.equals("3")){
                    pl.setResign(true);
                }
                //Press 1 to check turn
                else if(key.equals("1")){
                    firstTurnRun = true;
                }
                //Press 4 to check captured pieces
                else if(key.equals("4")){
                    Title captured = new Title(pl.getCapturedPieces(), 50);
                    addObject(captured, getWidth() / 2, getWidth() / 4);
                    Greenfoot.setSpeed(15);
                    Greenfoot.delay(1);
                    Greenfoot.setSpeed(50);
                    removeObjects(getObjects(Title.class));
                }
            }
            else if(key != null && startDraw){
                if (key.equals("1")){
                    setDraw(true);
                    removeObjects(getObjects(Title.class));
                    addObject(new Title(pl.getName() + " has accepted " + other.getName() + "'s draw offer.", 50), getWidth() / 2, getWidth() / 2); 
                    Greenfoot.setSpeed(15);
                    Greenfoot.delay(1);
                    Greenfoot.setSpeed(50);
                    removeObjects(getObjects(Title.class));
                } 
                else if(key.equals("2")){
                    setDraw(false);
                    removeObjects(getObjects(Title.class));
                    addObject(new Title(pl.getName() + " has declined " + other.getName() + "'s draw offer.", 50), getWidth() / 2, getWidth() / 2); 
                    Greenfoot.setSpeed(15);
                    Greenfoot.delay(1);
                    Greenfoot.setSpeed(50);
                    removeObjects(getObjects(Title.class));
                    startDraw = false;
                }
            }
        else if(gameBoard.getBoard()[0][0].clicked() != null){
            if(!fromPosChosen){
                chosenFromPos = choosePosition();
                if(chosenFromPos != null){
                    if(isValidPiece(chosenFromPos.getRow(), chosenFromPos.getColumn(), pl.getNumber(), gameBoard)){
                        fromPosChosen = true;
                        gameBoard.getTile(chosenFromPos).setTransparency(50);
                        gameBoard.getBoard()[0][0].setClicked(null);
                    }
                    else{
                        addObject(new Title("Invalid Position selected", 50), getWidth() / 2, getWidth() / 2); 
                        Greenfoot.setSpeed(15);
                        Greenfoot.delay(1);
                        Greenfoot.setSpeed(50);
                        removeObjects(getObjects(Title.class));
                        chosenFromPos = null;
                        gameBoard.getBoard()[0][0].setClicked(null);
                    }
                }
            }
            else{
                if(!toPosChosen){
                    chosenToPos = choosePosition();
                }
                if(chosenToPos != null){
                    gameBoard.getTile(chosenFromPos).setTransparency(225);
                    boolean castle = (gameBoard.getTile(chosenFromPos).getPiece().getName().equals("King") && gameBoard.castleAble(chosenFromPos, chosenToPos, gameBoard.getBoard()));
                    if(!promoted || gameBoard.isLegalMove(chosenFromPos, chosenToPos, gameBoard.getBoard()) || castle){
                        toPosChosen = true;
                        Piece currentPiece = gameBoard.getTile(chosenFromPos).getPiece();
                        if (currentPiece != null && currentPiece.getName().equals("Pawn")
                        && ((Pawn) (currentPiece)).isWaitingForPromotion()) {
                            promoted = false;
                            Greenfoot.setSpeed(100);
                            promotion(currentPiece);
                        }
                        if(promoted){
                            if(!castle){
                                movePieceOnBoard(chosenFromPos, chosenToPos);
                            }
                           gameBoard.getBoard()[0][0].setClicked(null);
                           findCheckmate();
                           stalemate = stalemate(blackPlayer, whitePlayer) || stalemate(whitePlayer, blackPlayer);
                           
                            isWhiteTurn = !isWhiteTurn;
                            fromPosChosen = false;
                            toPosChosen = false;
                            chosenFromPos = null;
                            chosenToPos = null;
                            firstTurnRun = true;
                            promoted = true;
                            
                            update();
                        }
                        
                    }
                    else{
                        addObject(new Title("Move is illegal.", 50), getWidth() / 2, getWidth() / 2); 
                        Greenfoot.setSpeed(15);
                        Greenfoot.delay(1);
                        Greenfoot.setSpeed(50);
                        removeObjects(getObjects(Title.class));
                        
                        fromPosChosen = false;
                        toPosChosen = false;
                        
                        chosenFromPos = null;
                        chosenToPos = null;
                        gameBoard.getBoard()[0][0].setClicked(null);
                    }
                }
            }
            
        }
    }

        /**
     * Moves a Piece from one Tile to another on the board of Tiles without checking
     * for legality; if a capture is made, adds the point value of the captured
     * piece to the turn player's score.
     *
     * @param fromPos
     *            the Piece's current position
     * @param toPos
     *            the Position to which the Piece will be moved
     */
    private void movePieceOnBoard(Position fromPos, Position toPos) {
        // If a capture is made, add points to the player's score
        if (gameBoard.getTile(toPos).hasPiece()) {
            Piece capturedPiece = gameBoard.getTile(toPos).getPiece();
            incrementScore(capturedPiece.getColor(), capturedPiece.getPointValue());

            // Add captured Piece to a maintained list of the capturing player

            if (capturedPiece.getColor() == 0) {
                blackPlayer.addCapturedPiece(capturedPiece);
            } else {
                whitePlayer.addCapturedPiece(capturedPiece);
            }
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
            addObject(new Title(whitePlayer.getName() + " has been checkmated. " + blackPlayer.getName() + " wins!", 50), getWidth() / 2, getWidth() / 2);
        }
        if (blackPlayer.isCheckMated()) {
            addObject(new Title(blackPlayer.getName() + " has been checkmated. " + whitePlayer.getName() + " wins!", 50), getWidth() / 2, getWidth() / 2);
        }
        if (draw) {
            addObject(new Title("Both players have agreed to a draw.", 50), getWidth() / 2, getWidth() / 2);
        }
        if (whitePlayer.isResigned()) {
            addObject(new Title(whitePlayer.getName() + " has resigned. " + blackPlayer.getName() + " wins!", 50), getWidth() / 2, getWidth() / 2);
        }
        if (blackPlayer.isResigned()) {
            addObject(new Title(blackPlayer.getName() + " has resigned. " + whitePlayer.getName() + " wins!", 50), getWidth() / 2, getWidth() / 2);
        }
        if (stalemate) {
            addObject(new Title("Stalemate: neither player wins.", 50), getWidth() / 2, getWidth() / 2);
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
     * column indexes, then checks whether the player is selecting a piece of the
     * appropriate color
     *
     * @param row
     *            the row index
     * @param col
     *            the column index
     * @param color
     *            0 for white, 1 for black
     * @param board
     *            the given Board object
     * @return true if there is a Piece of the correct color at the row and column
     *         indexes, fa  lse otherwise
     */
    public boolean isValidPiece(int row, int col, int color, Board board) {
        if (new Position(row, col).isWithinBounds() && board.getTile(row, col).hasPiece()) {
            return board.getTile(row, col).getPiece().getColor() == color;
        } else {
            return false;
        }
    }
    
        /**
     * First checks whether the given row and column indexes are in bounds, then
     * checks whether there is a Piece of the correct color at the specified row and
     * column indexes
     *
     * @param pos
     *            Position of the board to check at
     * @param color
     *            0 for white, 1 for black
     * @param board
     *            to check at
     * @return true if there is a Piece of the correct color at the row and column
     *         indexes, false otherwise
     */
    public static boolean isValidPiece(Position pos, int color, Board board) {
        if (pos.isWithinBounds() && board.getTile(pos).hasPiece()) {
            return board.getTile(pos).getPiece().getColor() == color;
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
     * Determines whether there is stalemate following the given player's move. A
     * game is in stalemate if and only if: 1) the turn player is not in check and
     * the turn player has no legal moves; 2) checkmate is impossible for either
     * player (if a player has only 1 Knight or only 1 Bishop left)
     *
     * @param pl
     *            the Player who just moved a piece
     * @param other
     *            the opposing Player
     * @return true if there is a stalemate, false otherwise
     */
    private boolean stalemate(Player pl, Player other) {
        int color = other.getNumber();
        if (!gameBoard.isKingChecked(color, gameBoard.getBoard())
                && !gameBoard.hasLegalMoveLeft(color, gameBoard.getBoard())) {
            return true;
        }
        int numPawns = numPawnsLeft(other);
        int numKnights = numKnightsLeft(other);
        int numBishops = numBishopsLeft(other);
        int numRooks = numRooksLeft(other);
        int numQueens = numQueensLeft(other);
        if (numKnights <= 1 && numPawns == 0 && numBishops == 0 && numRooks == 0 && numQueens == 0) {
            return true;
        }
        if (numBishops <= 1 && numPawns == 0 && numKnights == 0 && numRooks == 0 && numQueens == 0) {
            return true;
        }
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
        int myColor = promotedPawn.getColor();
        Position currentPos = promotedPawn.getPosition();
        addObject(new Title(promotedPawn + " is awaiting promotion.\nChoose a piece to replace " + promotedPawn + ":", 50), getWidth() / 2, getWidth() / 2); 
        String promotionChoice = Greenfoot.getKey();
        if(promotionChoice != null){
            if(promotionChoice.equals("1")){
                 gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(null);
                 Piece knight = new Knight(myColor, currentPos);
                 gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(knight);
                 removeObjects(getObjects(Title.class));
                 addObject(new Title("Promotion to Knight at " + currentPos, 50), getWidth() / 2, getWidth() / 2); 
                 Greenfoot.setSpeed(15);
                 Greenfoot.delay(1);
                 Greenfoot.setSpeed(50);
                 removeObjects(getObjects(Title.class));
                 promoted = true;
            }
            else if(promotionChoice.equals("2")){
                gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(null);
                Piece bishop = new Bishop(myColor, currentPos);
                gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(bishop);
                removeObjects(getObjects(Title.class));
                 addObject(new Title("Promotion to Bishop at " + currentPos, 50), getWidth() / 2, getWidth() / 2); 
                 Greenfoot.setSpeed(15);
                 Greenfoot.delay(1);
                 Greenfoot.setSpeed(50);
                 removeObjects(getObjects(Title.class));
                 promoted = true;
            }
            else if(promotionChoice.equals("3")){
                gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(null);
                Piece rook = new Rook(myColor, currentPos);
                gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(rook);
                removeObjects(getObjects(Title.class));
                 addObject(new Title("Promotion to Rook at " + currentPos, 50), getWidth() / 2, getWidth() / 2); 
                 Greenfoot.setSpeed(15);
                 Greenfoot.delay(1);
                 Greenfoot.setSpeed(50);
                 removeObjects(getObjects(Title.class));
                 promoted = true;
            }
            else if(promotionChoice.equals("4")){
                gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(null);
                Piece queen = new Queen(myColor, currentPos);
                gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(queen);
                removeObjects(getObjects(Title.class));
                 addObject(new Title("Promotion to Queen at " + currentPos, 50), getWidth() / 2, getWidth() / 2); 
                 Greenfoot.setSpeed(15);
                 Greenfoot.delay(1);
                 Greenfoot.setSpeed(50);
                 removeObjects(getObjects(Title.class));
                 promoted = true;
            }
        }
        
    }

    /**
     * Update the hotSpots
     */
    public void update() {
        gameBoard.updateHotSpots(gameBoard.getBoard());
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
    
    /**
     * Starts a turn played by the AI. AI Never surrenders
     *
     * @param myAI
     *            the turn player
     * @param other
     *            the opposing player
     */
    private void startAIPlayerTurn(AI myAI, Player other) {
        addObject(new Title(myAI.getName() + " is looking at the board.", 50), getWidth() / 2, getWidth() / 2); 
        Greenfoot.setSpeed(15);
        Greenfoot.delay(1);
        Greenfoot.setSpeed(50);
        removeObjects(getObjects(Title.class));
        continueAITurn(myAI);

    }
    
    /**
     * Selects the piece and destination for the player
     *
     * @param myAI
     *            the turn player
     */
    private void continueAITurn(AI myAI) {
        Vector bestMove = myAI.generateHighestPointValueMove();
        if (bestMove == null || bestMove.getFromPos() == null || bestMove.getToPos() == null) {
            blackPlayer.isResigned();
            return;
        }
        String feedback = gameBoard.getTile(bestMove.getFromPos()).getPiece().toString(); // contains the location of
        // the Piece prior
        movePieceOnBoard(bestMove.getFromPos(), bestMove.getToPos());
        // Account for promotion
        Piece currentPiece = gameBoard.getTile(bestMove.getToPos()).getPiece();
        if (currentPiece.getName().equals("Pawn") && ((Pawn) (currentPiece)).isWaitingForPromotion()) {
            promotion(currentPiece);
        }
        isWhiteTurn = !isWhiteTurn;
        update();
    }
    
        /**
     * Returns the number of Pawns left for the given Player
     *
     * @param pl
     *            the given Player
     * @return the number of Pawns that the given player has left
     */
    private int numPawnsLeft(Player pl) {
        int count = 0;
        if (pl.getNumber() == 0) {
            for (Tile[] arr : gameBoard.getBoard()) {
                for (Tile obj : arr) {
                    if (obj.hasPiece() && obj.getPiece().getName().equals("Pawn") && obj.getPiece().getColor() == 0) {
                        count++;
                    }
                }
            }
        } else {
            for (Tile[] arr : gameBoard.getBoard()) {
                for (Tile obj : arr) {
                    if (obj.hasPiece() && obj.getPiece().getName().equals("Pawn") && obj.getPiece().getColor() == 1) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
        /**
     * Returns the number of Bishops left for the given Player
     *
     * @param pl
     *            the given Player
     * @return the number of Bishops that the given Player has left
     */
    private int numBishopsLeft(Player pl) {
        int count = 0;
        if (pl.getNumber() == 0) {
            for (Tile[] arr : gameBoard.getBoard()) {
                for (Tile obj : arr) {
                    if (obj.hasPiece() && obj.getPiece().getName().equals("Bishop") && obj.getPiece().getColor() == 0) {
                        count++;
                    }
                }
            }
        } else {
            for (Tile[] arr : gameBoard.getBoard()) {
                for (Tile obj : arr) {
                    if (obj.hasPiece() && obj.getPiece().getName().equals("Bishop") && obj.getPiece().getColor() == 1) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
        /**
     * Returns the number of Rooks left for the given Player
     *
     * @param pl
     *            the given Player
     * @return the number of Rooks that the given Player has left
     */
    private int numRooksLeft(Player pl) {
        int count = 0;
        if (pl.getNumber() == 0) {
            for (Tile[] arr : gameBoard.getBoard()) {
                for (Tile obj : arr) {
                    if (obj.hasPiece() && obj.getPiece().getName().equals("Rook") && obj.getPiece().getColor() == 0) {
                        count++;
                    }
                }
            }
        } else {
            for (Tile[] arr : gameBoard.getBoard()) {
                for (Tile obj : arr) {
                    if (obj.hasPiece() && obj.getPiece().getName().equals("Rook") && obj.getPiece().getColor() == 1) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Returns the number of Queens left for the given Player
     *
     * @param pl
     *            the given Player
     * @return the number of Bishops that the given Player has left
     */
    private int numQueensLeft(Player pl) {
        int count = 0;
        if (pl.getNumber() == 0) {
            for (Tile[] arr : gameBoard.getBoard()) {
                for (Tile obj : arr) {
                    if (obj.hasPiece() && obj.getPiece().getName().equals("Queen") && obj.getPiece().getColor() == 0) {
                        count++;
                    }
                }
            }
        } else {
            for (Tile[] arr : gameBoard.getBoard()) {
                for (Tile obj : arr) {
                    if (obj.hasPiece() && obj.getPiece().getName().equals("Queen") && obj.getPiece().getColor() == 1) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
    /**
     * Returns the number of Knights left for the given Player
     *
     * @param pl
     *            the given Player
     * @return the number of Knights that the given Player has left
     */
    private int numKnightsLeft(Player pl) {
        int count = 0;
        if (pl.getNumber() == 0) {
            for (Tile[] arr : gameBoard.getBoard()) {
                for (Tile obj : arr) {
                    if (obj.hasPiece() && obj.getPiece().getName().equals("Knight") && obj.getPiece().getColor() == 0) {
                        count++;
                    }
                }
            }
        } else {
            for (Tile[] arr : gameBoard.getBoard()) {
                for (Tile obj : arr) {
                    if (obj.hasPiece() && obj.getPiece().getName().equals("Knight") && obj.getPiece().getColor() == 1) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}