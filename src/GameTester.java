/**
 *
 * @author Andrew Le Class to be used solely for the purpose of testing and
 *         debugging the chess game
 */
public class GameTester {

	/**
	 *
	 * @param args
	 *            - not used
	 */
	public static void main(String[] args) {
		String name1 = "WHITE";
		String name2 = "BLACK";
		Game test = new Game(name1, name2);
		Board myBoard = test.getBoard();
		clearBoard(myBoard);
		generatePawns(1, myBoard);
		setupPawns(myBoard);
		for (int i = 2; i < 6; i++) {
			clearRow(i, myBoard);
		}
		System.out.println(myBoard);
		test.playGame();
	}

	public static void generatePawns(int color, Board aBoard) {
		for (int row = 0; row < aBoard.getSize(); row++) {
			for (int col = 0; col < aBoard.getSize(); col++) {
				aBoard.getTile(row, col).setPiece(new Pawn(color, new Position(row, col)));
			}
		}
	}

	/**
	 * Determines the Position and color for the 16 pawns in the given board
	 *
	 * @param aBoard
	 *            - the given board
	 */
	public static void setupPawns(Board aBoard) {
		int wRow = 6;
		int bRow = 1;
		for (int col = 0; col < aBoard.getSize(); col++) {
			Position whitePos = new Position(wRow, col);
			createPawns(whitePos, 0, aBoard);
			Position blackPos = new Position(bRow, col);
			createPawns(blackPos, 1, aBoard);
		}
	}

	/**
	 *
	 * Creates a Pawn Piece on the given board with the given color and Position
	 *
	 * @param pos
	 *            - the pawn's Position [row][col]
	 * @param color
	 *            - the pawn's color; 0 = white, 1 = black
	 * @param aBoard
	 *            - the given board
	 */
	public static void createPawns(Position pos, int color, Board aBoard) {
		Piece pawn = new Pawn(color, pos);
		aBoard.getTile(pos).setPiece(pawn);
	}

	/**
	 * Determines the Position and color for the 4 Rooks on the given board
	 *
	 * @param aBoard
	 *            - the given board
	 */
	public static void setupRooks(Board aBoard) {
		Position whitePos = new Position(7, 0);
		Position whitePos2 = new Position(7, 7);
		Position blackPos = new Position(0, 0);
		Position blackPos2 = new Position(0, 7);
		createRooks(whitePos, 0, aBoard);
		createRooks(whitePos2, 0, aBoard);
		createRooks(blackPos, 1, aBoard);
		createRooks(blackPos2, 1, aBoard);
	}

	/**
	 *
	 * Creates a Rook Piece on the given board with the given color and Position
	 *
	 * @param pos
	 *            - the rook's Position [row][col]
	 * @param color
	 *            - the rook's color; 0 = white, 1 = black
	 * @param aBoard
	 *            - the given board
	 */
	public static void createRooks(Position pos, int color, Board aBoard) {
		Piece rook = new Rook(color, pos);
		aBoard.getTile(pos).setPiece(rook);
	}

	/**
	 * Determines the Position and color for the 4 Knights on the given board
	 *
	 * @param aBoard
	 *            - the given board
	 */
	public static void setupKnights(Board aBoard) {
		Position whitePos = new Position(7, 1);
		Position whitePos2 = new Position(7, 6);
		Position blackPos = new Position(0, 1);
		Position blackPos2 = new Position(0, 6);
		createKnights(whitePos, 0, aBoard);
		createKnights(whitePos2, 0, aBoard);
		createKnights(blackPos, 1, aBoard);
		createKnights(blackPos2, 1, aBoard);
	}

	/**
	 *
	 * Creates a Knight Piece on the given board with the given color and Position
	 *
	 * @param pos
	 *            - the knight's Position [row][col]
	 * @param color
	 *            - the knight's color; 0 = white, 1 = black
	 * @param aBoard
	 *            - the given board
	 */
	public static void createKnights(Position pos, int color, Board aBoard) {
		Piece knight = new Knight(color, pos);
		aBoard.getTile(pos).setPiece(knight);
	}

	/**
	 * Determines the Position and color for the 4 Bishops on the given board
	 *
	 * @param aBoard
	 *            - the given board
	 */
	public static void setupBishops(Board aBoard) {
		Position whitePos = new Position(7, 2);
		Position whitePos2 = new Position(7, 5);
		Position blackPos = new Position(0, 2);
		Position blackPos2 = new Position(0, 5);
		createBishops(whitePos, 0, aBoard);
		createBishops(whitePos2, 0, aBoard);
		createBishops(blackPos, 1, aBoard);
		createBishops(blackPos2, 1, aBoard);
	}

	/**
	 *
	 * Creates a Bishop Piece on the given board with the given color and Position
	 *
	 * @param pos
	 *            - the bishop's Position [row][col]
	 * @param color
	 *            - the bishop's color; 0 = white, 1 = black
	 * @param aBoard
	 *            - the given board
	 */
	public static void createBishops(Position pos, int color, Board aBoard) {
		Piece bishop = new Bishop(color, pos);
		aBoard.getTile(pos).setPiece(bishop);
	}

	/**
	 * Determines the Position and color for the 2 Queens on the given board
	 *
	 * @param aBoard
	 *            - the given board
	 */
	public static void setupQueens(Board aBoard) {
		Position whitePos = new Position(7, 3);
		Position blackPos = new Position(0, 3);
		createQueens(whitePos, 0, aBoard);
		createQueens(blackPos, 1, aBoard);
	}

	/**
	 *
	 * Creates a Queen Piece on the given board with the given color and Position
	 *
	 * @param pos
	 *            - the queen's Position [row][col]
	 * @param color
	 *            - the queen's color; 0 = white, 1 = black
	 * @param aBoard
	 *            - the given board
	 */
	public static void createQueens(Position pos, int color, Board aBoard) {
		Piece queen = new Queen(color, pos);
		aBoard.getTile(pos).setPiece(queen);
	}

	/**
	 * Determines the Position and color for the 2 Kings on the given board
	 *
	 * @param aBoard
	 *            - the given board
	 */
	public static void setupKings(Board aBoard) {
		Position whitePos = new Position(7, 4);
		Position blackPos = new Position(0, 4);
		createKings(whitePos, 0, aBoard);
		createKings(blackPos, 1, aBoard);
	}

	/**
	 *
	 * Creates a King Piece on the given board with the given color and Position
	 *
	 * @param pos
	 *            - the king's Position [row][col]
	 * @param color
	 *            - the king's color; 0 = white, 1 = black
	 * @param aBoard
	 *            - the given board
	 */
	public static void createKings(Position pos, int color, Board aBoard) {
		Piece king = new King(color, pos);
		aBoard.getTile(pos).setPiece(king);
	}

	/**
	 * Clears the given Board by setting each of the Pieces on its Tiles to null
	 *
	 * @param aBoard
	 *            - the given Board
	 */
	public static void clearBoard(Board aBoard) {
		for (int row = 0; row < aBoard.getSize(); row++) {
			for (int col = 0; col < aBoard.getSize(); col++) {
				aBoard.getTile(row, col).setPiece(null);
			}
		}
	}

	/**
	 * Clears the given row on the given board
	 *
	 * @param row
	 *            - the given row index
	 * @param aBoard
	 *            - the given Board
	 */
	public static void clearRow(int row, Board aBoard) {
		for (int col = 0; col < aBoard.getSize(); col++) {
			aBoard.getTile(row, col).setPiece(null);
		}
	}

	/**
	 * Clears the given column on the given board
	 *
	 * @param col
	 *            - the given column index
	 * @param aBoard
	 *            - the given Board
	 */
	public static void clearCol(int col, Board aBoard) {
		for (int row = 0; row < aBoard.getSize(); row++) {
			aBoard.getTile(row, col).setPiece(null);
		}
	}

	/**
	 * Tests castling by setting up a King and two rooks
	 *
	 * @param aBoard
	 */
	public static void testCastle(Board aBoard) {
		// White pieces
		Piece wKing = new King(0, new Position(7, 4));
		Piece wRook1 = new Rook(0, new Position(7, 0));
		Piece wRook2 = new Rook(0, new Position(7, 7));
		aBoard.getTile(wKing.getPosition()).setPiece(wKing);
		aBoard.getTile(wRook1.getPosition()).setPiece(wRook1);
		aBoard.getTile(wRook2.getPosition()).setPiece(wRook2);
		// Black pieces
		Piece bKing = new King(1, new Position(0, 4));
		aBoard.getTile(bKing.getPosition()).setPiece(bKing);

	}

}
