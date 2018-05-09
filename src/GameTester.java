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
		generatePawns(0,myBoard);
		setupKnight(myBoard);
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
	 * Determines the Position and color for a select number of Knights on the given
	 * board
	 * 
	 * @param aBoard
	 *            - the given board
	 */
	public static void setupKnight(Board aBoard) {
		Position whitePos = new Position(7, 1);
		Position blackPos = new Position(0, 1);
		createKnight(whitePos, 0, aBoard);
		createKnight(blackPos, 1, aBoard);
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
	public static void createKnight(Position pos, int color, Board aBoard) {
		Piece knight = new Knight(color, pos);
		aBoard.getTile(pos).setPiece(knight);
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
