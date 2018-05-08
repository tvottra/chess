import java.util.ArrayList;

/**
 * Class that represents the chess board
 *
 * @author Andrew Le
 * @author Brian Qiu
 * @author Tommy Tran
 */
public class Board {
    Tile[][] board;
    private final int SIZE = 8;

    /**
     * Default constructor that initializes the chess board with all of the white
     * pieces, black pieces and the rest of the tiles; establishes the white and
     * black hot spots
     */
    public Board() {
        board = new Tile[8][8];
        setUpWhitePieces();
        setUpBlackPieces();
        setUpRestOfBoard();
        // NullPointerException when updateHotSpots is called
        // updateHotspots();
    }

    /**
     * Sets up all of the white pieces on the bottom two rows of the board
     */
    private void setUpWhitePieces() {
        // Set up white pawns
        for (int c = 0; c < SIZE; c++) {
            Position pos = new Position(6, c);
            Piece pawn = new Pawn(0, pos);
            board[6][c] = new Tile(pawn, true, false);
        }
        // Set up white rooks
        Piece rook1 = new Rook(0, new Position(7, 0));
        board[7][0] = new Tile(rook1, true, false);
        Piece rook2 = new Rook(0, new Position(7, 7));
        board[7][7] = new Tile(rook2, true, false);

        // Set up white knights
        Piece knight1 = new Knight(0, new Position(7, 1));
        board[7][1] = new Tile(knight1, true, false);
        Piece knight2 = new Knight(0, new Position(7, 6));
        board[7][6] = new Tile(knight2, true, false);

        // Set up white bishops
        Piece bishop1 = new Bishop(0, new Position(7, 2));
        board[7][2] = new Tile(bishop1, true, false);
        Piece bishop2 = new Bishop(0, new Position(7, 5));
        board[7][5] = new Tile(bishop2, true, false);

        // Set up white queen and king
        Piece queen = new Queen(0, new Position(7, 3));
        board[7][3] = new Tile(queen, true, false);
        Piece king = new King(0, new Position(7, 4));
        board[7][4] = new Tile(king, true, false);
    }

    /**
     * Sets up all of the black pieces on the upper two rows of the board
     */
    private void setUpBlackPieces() {
        // Set up black pawns
        for (int c = 0; c < SIZE; c++) {
            Position pos = new Position(1, c);
            Piece pawn = new Pawn(1, pos);
            board[1][c] = new Tile(pawn, false, true);
        }

        // Set up black rooks
        Piece rook1 = new Rook(1, new Position(0, 0));
        board[0][0] = new Tile(rook1, false, true);
        Piece rook2 = new Rook(1, new Position(0, 7));
        board[0][7] = new Tile(rook2, false, true);

        // Set up black knights
        Piece knight1 = new Knight(1, new Position(0, 1));
        board[0][1] = new Tile(knight1, false, true);
        Piece knight2 = new Knight(1, new Position(0, 6));
        board[0][6] = new Tile(knight2, false, true);

        // Set up black bishops
        Piece bishop1 = new Bishop(1, new Position(0, 2));
        board[0][2] = new Tile(bishop1, false, true);
        Piece bishop2 = new Bishop(1, new Position(0, 5));
        board[0][5] = new Tile(bishop2, false, true);

        // Set up black queen and king
        Piece queen = new Queen(1, new Position(0, 3));
        board[0][3] = new Tile(queen, false, true);
        Piece king = new King(1, new Position(0, 4));
        board[0][4] = new Tile(king, false, true);
    }

    /**
     * Initializes all unoccupied Tiles, along with white/black control of each Tile
     */
    private void setUpRestOfBoard() {
        // General traversal of Tiles in rows 3 and 4
        for (int r = 3; r < 5; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c] = new Tile(null, false, false);
            }
        }
        // Traversal of row 2
        for (int c = 0; c < SIZE; c++) {
            board[2][c] = new Tile(null, false, true);
        }
        // Traversal of row 5
        for (int c = 0; c < SIZE; c++) {
            board[5][c] = new Tile(null, true, false);
        }
    }

    /**
     * Accessor method to get a tile at the specified location
     *
     * @param row - the row index of the Tile
     * @param col - the column index of the Tile
     * @return the Tile at board[row][col]
     */
    public Tile getTile(int row, int col) {
        return board[row][col];
    }

    /**
     * Accessor method to get a tile at the specified location
     *
     * @param pos - the position of the Tile
     * @return the Tile at board[row][col]
     */
    public Tile getTile(Position pos) {
        return board[pos.getRow()][pos.getColumn()];
    }

    /**
     * Accessor method to return the size of one side of the board
     *
     * @return the size of the board
     */
    public int getSize() {
        return SIZE;
    }

    /**
     * Attempts to move a Piece on a Tile to the given Position; if the move is
     * successful, updates the Piece's Position and the hotspots on the board
     *
     * @param fromPos - the Piece's current position
     * @param toPos   - the Position to which the Piece will be moved
     * @return true if the Piece was successfully moved, false otherwise
     */
    public boolean movePiece(Position fromPos, Position toPos) {
        if (!isLegalMove(fromPos, toPos)) {
            return false;
        }
        int fromRow = fromPos.getRow();
        int fromCol = fromPos.getColumn();
        Piece pieceToMove = board[fromRow][fromCol].getPiece();
        int toRow = toPos.getRow();
        int toCol = toPos.getColumn();
        board[toRow][toCol].setPiece(pieceToMove);
        board[fromRow][fromCol].setPiece(null);
        board[toRow][toCol].getPiece().setPosition(toPos);
        // NullPointerException when updateHotSpots is called
        // updateHotspots();
        return true;
    }

    /**
     * Checks whether moving a Piece from its current Position to a given Position
     * is legal.
     *
     * @param fromPos - the Piece's current Position
     * @param toPos   - the Position of the Piece's proposed destination
     * @return true if the move is legal, false otherwise
     */
    public boolean isLegalMove(Position fromPos, Position toPos) {
        int fromRow = fromPos.getRow();
        int fromCol = fromPos.getColumn();
        Piece pieceToMove = board[fromRow][fromCol].getPiece();
        int toRow = toPos.getRow();
        int toCol = toPos.getColumn();
        if (pieceToMove.getName().equals("Pawn")) {
            if (!pieceToMove.isWithinRangeOfMovement(toPos)) {
                if (isWithinHotspots(pieceToMove, toPos)) { // If the move is not forward but instead diagonal
                    if (board[toRow][toCol].hasPiece()
                            && board[toRow][toCol].getPiece().getColor() != pieceToMove.getColor()) { // Check if the
                        // Tile is
                        // occupied by
                        // an enemy; if
                        // so, then
                        // valid move to
                        // "take" that
                        // Tile
                        // No code here. Proceed with the rest of the code to verify legality.
                    } else {
                        return false; // if the diagonal has a Piece of the same color
                    }
                } else {
                    return false; // if the Tile is NOT in the Pawn's range of movement
                }
            }
            return true;
        }

        if (!isWithinHotspots(pieceToMove, toPos)
                || board[toRow][toCol].getPiece().isSameColor(board[fromRow][fromCol].getPiece())) {
            return false;
        }
        // Create a copy of the real board to determine whether the move creates a check
        Tile[][] copy = new Tile[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                copy[row][col] = board[row][col];
            }
        }
        // Perform the move on the copy of the board
        copy[toRow][toCol].setPiece(pieceToMove);
        copy[fromRow][fromCol].setPiece(null);
        // Find the hotspots and see whether the move would result in a check
        ArrayList<Position> allHotspots = new ArrayList<Position>();
        int myColor = pieceToMove.getColor();
        if (isKingChecked(myColor, copy)) {
            return false;
        }
        return true;
    }

    /**
     * Checks whether the given Piece's proposed destination is within the Piece's
     * hotspots
     *
     * @param piece - the given Piece
     * @param toPos - the given destination Position
     * @return true if toPos is within the Piece's hotspots, false otherwise
     */
    private boolean isWithinHotspots(Piece piece, Position toPos) {
        ArrayList<Position> myHotspots = getHotSpots(piece);
        for (Position pos : myHotspots) {
            if (toPos.equals(pos)) {
                return true;
            }
        }
        return false;
    }

    /**
     * toString method to print the Board
     *
     * @return the contents of each Tile as they would appear on a 2D board
     */
    public String toString() {
        String output = "";
        boolean first = true;
        int count = 0;
        output += "   00 01 02 03 04 05 06 07\n";
        for (Tile[] arr : board) {
            for (Tile tile : arr) {
                if (first) {
                    output += "0" + count + " ";
                    ++count;
                    first = false;
                }
                output += tile.toString() + " ";
            }
            output += "\n";
            first = true;
        }
        return output;
    }

    /**
     * Gets all the hotspots controlled by this Piece; a hotspot is defined as any
     * Position at which a Piece could perform a capture; calls the appropriate
     * helper method to get the hotspots, depending on the identity of the piece
     *
     * @param piece - the given Piece
     * @return all the Positions currently checked by this Piece
     */
    public ArrayList<Position> getHotSpots(Piece piece) {
        if (piece.getName().equals("Pawn")) {
            return getPawnHotspots(piece);
        }
        if (piece.getName().equals("Knight")) {
            return getKnightHotspots(piece);
        }
        if (piece.getName().equals("Bishop")) {
            return getBishopHotspots(piece);
        }
        if (piece.getName().equals("Rook")) {
            return getRookHotspots(piece);
        }
        if (piece.getName().equals("Queen")) {
            return getQueenHotspots(piece);
        }
        if (piece.getName().equals("King")) {
            return getKingHotspots(piece);
        }
        return null;

    }

    /**
     * Returns all of white's checked hotspots for the given board
     *
     * @param aBoard - the given board
     * @return all of the hotspots checked by white pieces
     */
    public ArrayList<Position> getWhiteHotspots(Tile[][] aBoard) {
        ArrayList<Position> wHotspots = new ArrayList<Position>();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (aBoard[row][col].getPiece() != null && aBoard[row][col].getPiece().getColor() == 0) {
                    ArrayList<Position> myHotspots = new ArrayList<Position>();
                    for (Position pos : myHotspots) {
                        wHotspots.add(pos);
                    }
                }
            }
        }
        return wHotspots;
    }

    /**
     * Returns all of black's checked hotspots for the given board
     *
     * @param aBoard - the given board
     * @return all of the hotspots checked by black pieces
     */
    public ArrayList<Position> getBlackHotspots(Tile[][] aBoard) {
        ArrayList<Position> bHotspots = new ArrayList<Position>();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (aBoard[row][col].getPiece() != null && aBoard[row][col].getPiece().getColor() == 1) {
                    ArrayList<Position> myHotspots = new ArrayList<Position>();
                    for (Position pos : myHotspots) {
                        bHotspots.add(pos);
                    }
                }
            }
        }
        return bHotspots;
    }

    /**
     * Returns the hotspots for the given Pawn
     *
     * @param pawn - the given Pawn
     * @return the hotspots for the given "Pawn"
     */
    private ArrayList<Position> getPawnHotspots(Piece pawn) {
        ArrayList<Position> hotSpots = new ArrayList<Position>();
        Position currentPos = pawn.getPosition();
        int r;

        if (pawn.getColor() == 0) {
            r = -1;
        } else {
            r = 1;
        }

        Position leftDiag = new Position(currentPos.getRow() + r, currentPos.getColumn() - 1);
        Position rightDiag = new Position(currentPos.getRow() + r, currentPos.getColumn() + 1);
        if (leftDiag.isWithinBounds()) {
            hotSpots.add(leftDiag);
        }
        if (rightDiag.isWithinBounds()) {
            hotSpots.add(rightDiag);
        }
        return hotSpots;
    }

    /**
     * Returns the hotspots for the given Knight
     *
     * @param knight - the given Knight
     * @return the hotspots for the given "Knight"
     */
    private ArrayList<Position> getKnightHotspots(Piece knight) {
        ArrayList<Position> rom = knight.getRangeOfMovement();
        ArrayList<Position> hotspots = new ArrayList<Position>();
        for (int i = 0; i < rom.size(); i++) {
            hotspots.add(rom.get(i));
        }
        return hotspots;
    }

    /**
     * Returns the hotspots for the given Bishop
     *
     * @param bishop - the given Bishop
     * @return the hotspots for the given "Bishop"
     */
    private ArrayList<Position> getBishopHotspots(Piece bishop) {
        ArrayList<Position> rom = bishop.getRangeOfMovement();
        ArrayList<Position> hotspots = new ArrayList<Position>();
        // A branch is one of the 4 diagonals for the Bishop
        int branch1end = 0;
        int branch2end = 0;
        int branch3end = 0;
        int branch4end = rom.size();
        for (int i = 1; i < rom.size(); i++) {
            if (bishop.getPosition().equals(rom.get(i))) {
                if (branch1end == 0) {
                    branch1end = i;
                } else if (branch2end == 0) {
                    branch2end = i;
                } else if (branch3end == 0) {
                    branch3end = i;
                }
            }
        }
        for (int i = 1; i < branch1end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotspots.add(rom.get(i));
                break;
            } else {
                hotspots.add(rom.get(i));
            }
        }
        for (int i = branch1end + 1; i < branch2end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotspots.add(rom.get(i));
                break;
            } else {
                hotspots.add(rom.get(i));
            }
        }
        for (int i = branch2end + 1; i < branch3end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotspots.add(rom.get(i));
                break;
            } else {
                hotspots.add(rom.get(i));
            }
        }
        for (int i = branch3end + 1; i < branch4end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotspots.add(rom.get(i));
                break;
            } else {
                hotspots.add(rom.get(i));
            }
        }
        return hotspots;
    }

    /**
     * Returns the hotspots for the given Rook
     *
     * @param rook - the given Rook
     * @return the hotspots for the given "Rook"
     */
    private ArrayList<Position> getRookHotspots(Piece rook) {
        ArrayList<Position> rom = rook.getRangeOfMovement();
        ArrayList<Position> hotSpots = new ArrayList<Position>();
        rom.add(0, new Position(-1, -1)); // add a "buffer" to prevent row 0 error
        int hbranch1end = 0; // tiles above rook on board
        int hbranch2end = 0; // tiles below rook on board
        int hbranch3end = 0; // tiles "left" of rook
        int hbranch4end = rom.size(); // tiles "right" of rook
        for (int i = 0; i < rom.size(); i++) {
            if (rook.getPosition().equals(rom.get(i))) {
                if (hbranch1end == 0) {
                    hbranch1end = i;
                } else if (hbranch2end == 0) {
                    hbranch2end = i;
                } else if (hbranch3end == 0) {
                    hbranch3end = i;
                } else if (hbranch4end == 0) {
                    hbranch4end = i;
                }
            }
        }

        /*
         * Use for debugging System.out.println("branch 1 = " + hbranch1end);
         * System.out.println("branch 2 = " + hbranch2end);
         * System.out.println("branch 3 = " + hbranch3end);
         * System.out.println("branch 4 = " + hbranch4end);
         */
        // Look at vector above rook
        for (int i = 1; i < hbranch1end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotSpots.add(rom.get(i));
                break;
            } else {
                hotSpots.add(rom.get(i));
            }
        }
        // Look at vector above rook
        for (int i = hbranch1end + 1; i < hbranch2end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotSpots.add(rom.get(i));
                break;
            } else {
                hotSpots.add(rom.get(i));
            }
        }
        // Look at vector left of rook
        for (int i = hbranch2end + 1; i < hbranch3end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotSpots.add(rom.get(i));
                break;
            } else {
                hotSpots.add(rom.get(i));
            }
        }
        // Look at vector right of rook
        for (int i = hbranch3end + 1; i < hbranch4end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotSpots.add(rom.get(i));
                break;
            } else {
                hotSpots.add(rom.get(i));
            }
        }
        return hotSpots;
    }

    /**
     * Returns the hotspots for the given Queen
     *
     * @param queen - the given Queen
     * @return the hotspots for the given "Queen"
     */
    private ArrayList<Position> getQueenHotspots(Piece queen) {
        ArrayList<Position> rom = queen.getRangeOfMovement();
        ArrayList<Position> hotSpots = new ArrayList<Position>();
        rom.add(0, new Position(-1, -1)); // add a buffer to prevent row 0 error
        int hbranch1end = 0; // the tiles above queen
        int hbranch2end = 0; // the tiles below queen
        int hbranch3end = 0; // the tiles left of queen
        int hbranch4end = 0; // the tiles right of queen
        int dbranch1end = 0; // upper right diagonal
        int dbranch2end = 0; // upper left diagonal
        int dbranch3end = 0; // lower left diagonal
        int dbranch4end = rom.size(); // lower right diagonal

        for (int i = 0; i < rom.size(); i++) {
            if (queen.getPosition().equals(rom.get(i))) {
                if (hbranch1end == 0) {
                    hbranch1end = i;
                    // System.out.println("hbranch1 = " + hbranch1end);
                } else if (hbranch2end == 0) {
                    hbranch2end = i;
                    // System.out.println("hbranch2 = " + hbranch2end);
                } else if (hbranch3end == 0) {
                    hbranch3end = i;
                    // System.out.println("hbranch3 = " + hbranch3end);
                } else if (hbranch4end == 0) {
                    hbranch4end = i;
                    // System.out.println("hbranch4 = " + hbranch4end);
                } else if (dbranch1end == 0) {
                    dbranch1end = i;
                    // System.out.println("dbranch1 = " + dbranch1end);
                } else if (dbranch2end == 0) {
                    dbranch2end = i;
                    // System.out.println("dbranch2 = " + dbranch2end);
                } else if (dbranch3end == 0) {
                    dbranch3end = i;
                    // System.out.println("dbranch3 = " + dbranch3end);
                }
            }
        }
        // System.out.println("dbranch4 = " + dbranch4end);
        for (int i = 1; i < hbranch1end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotSpots.add(rom.get(i));
                break;
            } else {
                hotSpots.add(rom.get(i));
            }
        }
        for (int i = hbranch1end + 1; i < hbranch2end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotSpots.add(rom.get(i));
                break;
            } else {
                hotSpots.add(rom.get(i));
            }
        }
        for (int i = hbranch2end + 1; i < hbranch3end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotSpots.add(rom.get(i));
                break;
            } else {
                hotSpots.add(rom.get(i));
            }
        }
        for (int i = hbranch3end + 1; i < hbranch4end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotSpots.add(rom.get(i));
                break;
            } else {
                hotSpots.add(rom.get(i));
            }
        }
        for (int i = hbranch4end + 1; i < dbranch1end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotSpots.add(rom.get(i));
                break;
            } else {
                hotSpots.add(rom.get(i));
            }
        }
        for (int i = dbranch1end + 1; i < dbranch2end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotSpots.add(rom.get(i));
                break;
            } else {
                hotSpots.add(rom.get(i));
            }
        }
        for (int i = dbranch2end + 1; i < dbranch3end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotSpots.add(rom.get(i));
                break;
            } else {
                hotSpots.add(rom.get(i));
            }
        }
        for (int i = dbranch3end + 1; i < dbranch4end; i++) {
            Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
            if (currentTile != null) {
                hotSpots.add(rom.get(i));
                break;
            } else {
                hotSpots.add(rom.get(i));
            }
        }
        return hotSpots;
    }

    /**
     * Returns the hotspots for the given King
     *
     * @param king - the given King
     * @return the hotspots for the given "King"
     */
    private ArrayList<Position> getKingHotspots(Piece king) {
        ArrayList<Position> rom = king.getRangeOfMovement();
        ArrayList<Position> hotSpots = new ArrayList<Position>();
        for (int i = 0; i < rom.size(); i++) {
            hotSpots.add(rom.get(i));
        }
        return null;
    }

    /**
     * Determines whether a King of a given color is currently in check on the given
     * board
     *
     * @param color  - 0 if white, 1 if black
     * @param aBoard - the given board
     * @return true if the King is checked, false otherwise
     */
    private boolean isKingChecked(int color, Tile[][] aBoard) {
        Position kingPos = findKingPosition(color, aBoard);
        if (color == 0) {
            ArrayList<Position> wHotspots = getWhiteHotspots(aBoard);
            for (Position pos : wHotspots) {
                if (kingPos.equals(pos)) {
                    return true;
                }
            }
        } else {
            ArrayList<Position> bHotspots = getWhiteHotspots(aBoard);
            for (Position pos : bHotspots) {
                if (kingPos.equals(pos)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Finds the Position of the King of a given color in the given board
     *
     * @param color  - 0 if white, 1 if black
     * @param aBoard - the given board
     * @return the Position of the King
     */
    private Position findKingPosition(int color, Tile[][] aBoard) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (aBoard[row][col].hasPiece() && aBoard[row][col].getPiece().getName().equals("King")
                        && aBoard[row][col].getPiece().getColor() == color) {
                    return new Position(row, col);
                }
            }
        }
        return null;
    }

    /**
     * Should be called each time after a Piece is moved, looping through all of the
     * Tiles and updating the isWhiteHotSpot and isBlackHotSpot for each Tile
     */
    public void updateHotspots() {
        ArrayList<Position> checkedPos;

        // Look through each Piece's field of hotSpots
        for (Tile[] t1 : board) {
            for (Tile t2 : t1) {

                // If there is a Piece
                if (t2.getPiece() != null) {
                    Piece myPiece = t2.getPiece();
                    checkedPos = getHotSpots(myPiece);
                    boolean isWhite = false;
                    if (myPiece.getColor() == 0) {
                        isWhite = true;
                    }
                    // For each Position checked, update each corresponding Tile's isWhiteHotSpot
                    // and isBlackHotSpot accordingly
                    for (Position pos : checkedPos) {
                        if (isWhite) {
                            board[pos.getRow()][pos.getColumn()].setIsWhiteHotSpot(true);
                        } else {
                            board[pos.getRow()][pos.getColumn()].setIsBlackHotSpot(true);
                        }
                    }
                }
            }

        }
    }

    public int isCheckmated() {
        for (int color = 0; color < 2; color++) {
            if (isKingChecked(color, board)) {
                Position kingPos = findKingPosition(color, board);
                Piece king = getTile(kingPos).getPiece();
                ArrayList<Position> kingROM = king.getRangeOfMovement();
                ArrayList<Boolean> canMove = new ArrayList<Boolean>(kingROM.size());
                for (int i = 0; i < kingROM.size(); i++) {
                    if (isLegalMove(kingPos, kingROM.get(i))) {
                        canMove.set(i, true);
                    }
                    canMove.set(i, false);
                }
                if (!canMove.contains(new Boolean(true))) {
                    return color;
                }
            }

        }
        return -1;
    }

}
