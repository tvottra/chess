/**
 * Class that represents the chess board
 * 
 * @author Andrew Le
 *
 */
public class Board {
	Tile[][] board;
	private final int SIZE = 8;

	/**
	 * Default constructor that initializes the chess board according to the rules
	 * of chess
	 */
	public Board() {
		board = new Tile[8][8];
		setUpWhitePieces();
		setUpBlackPieces();
		setUpRestOfBoard();
	}

	/**
	 * Sets up all of the white pieces
	 */
	private void setUpWhitePieces() {
		// Set up white pawns
		for (int c = 0; c < SIZE; c++) {
			Position pos = new Position(6, c);
			Piece pawn = new Pawn("pawn", 0, pos, 1);
			board[6][c] = new Tile(pawn, true, false);
		}
		// Set up white rooks
		Piece rook1 = new Rook("rook", 0, new Position(7, 0), 5);
		board[7][0] = new Tile(rook1, true, false);
		Piece rook2 = new Rook("rook", 0, new Position(7, 7), 5);
		board[7][7] = new Tile(rook2, true, false);

		// Set up white knights
		Piece knight1 = new Knight("knight", 0, new Position(7, 1), 3);
		board[7][1] = new Tile(knight1, true, false);
		Piece knight2 = new Knight("knight", 0, new Position(7, 6), 3);
		board[7][6] = new Tile(knight2, true, false);

		// Set up white bishops
		Piece bishop1 = new Bishop("bishop", 0, new Position(7, 2), 3);
		board[7][2] = new Tile(bishop1, true, false);
		Piece bishop2 = new Bishop("bishop", 0, new Position(7, 5), 3);
		board[7][3] = new Tile(bishop2, true, false);

		// Set up white queen and king
		Piece queen = new Queen("queen", 0, new Position(7, 3), 9);
		board[7][3] = new Tile(queen, true, false);
		Piece king = new King("king", 0, new Position(7, 4), Integer.MAX_VALUE);
		board[7][4] = new Tile(king, true, false);
	}

	/**
	 * Sets up all of the black pieces
	 */
	private void setUpBlackPieces() {
		// Set up black pawns
		for (int c = 0; c < SIZE; c++) {
			Position pos = new Position(1, c);
			Piece pawn = new Pawn("pawn", 1, pos, 1);
			board[1][c] = new Tile(pawn, false, true);
		}

		// Set up black rooks
		Piece rook1 = new Rook("rook", 1, new Position(0, 0), 5);
		board[0][0] = new Tile(rook1, false, true);
		Piece rook2 = new Rook("rook", 1, new Position(0, 7), 5);
		board[0][7] = new Tile(rook2, false, true);

		// Set up black knights
		Piece knight1 = new Knight("knight", 1, new Position(0, 1), 3);
		board[0][1] = new Tile(knight1, false, true);
		Piece knight2 = new Knight("knight", 1, new Position(0, 6), 3);
		board[0][6] = new Tile(knight2, false, true);

		// Set up black bishops
		Piece bishop1 = new Bishop("bishop", 1, new Position(0, 2), 3);
		board[0][2] = new Tile(bishop1, false, true);
		Piece bishop2 = new Bishop("bishop", 1, new Position(0, 5), 3);
		board[0][5] = new Tile(bishop2, false, true);

		// Set up black queen and king
		Piece queen = new Queen("queen", 1, new Position(0, 3), 9);
		board[0][3] = new Tile(queen, false, true);
		Piece king = new King("king", 1, new Position(0, 4), Integer.MAX_VALUE);
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
	 * @param row
	 *            - the row index of the Tile
	 * @param col
	 *            - the column index of the Tile
	 * @return the Tile at board[row][col]
	 */
	public Tile getTile(int row, int col) {
		return board[row][col];
	}

	/**
	 * Called each time after a Piece is moved, looping through all of the Tiles and
	 * updating the white and black control for each Tile
	 */
	public void updateControl() {
		// TO BE IMPLEMENTED
	}

	/**
	 * Attempts to move a Piece on a Tile to the given Position
	 * 
	 * @param fromPos
	 *            - the Piece's current position
	 * @param toPos
	 *            - the Position to which the Piece will be moved
	 */
	public void moveMyPiece(Position fromPos, Position toPos) {
		// TO BE IMPLEMENTED
	}

	/**
	 * toString method
	 * 
	 * @return the contents each Tile
	 */
	public String toString() {
		String output = "";
		for (Tile[] arr : board) {
			for (Tile tile : arr) {
				output += tile.toString() + "\n";
			}
		}
		return output;
	}

}
