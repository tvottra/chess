/**
 * Class that represents the chess board
 * 
 * @author Tommy Tran
 * @author Arjun Agrawal
 * @author Andrew Le
 *
 */
public class Board {
	Tile[][] board;
	private final int SIZE = 8;

	/**
	 * Default constructor that sets up the chess board with all pieces in their
	 * starting positions
	 */
	public Board() {
		board = new Tile[8][8];
		// generates white side first, then black
		for (int pos = 7, color = 0; color < 2; color++, pos -= 6) {
			// generate color pawns pawns
			for (int x = 0; x < SIZE; x++) {
				int temppos = pos - 1;
				if (temppos < 0) {
					temppos = 0 - temppos;
				}
				board[temppos][x] = new Pawn(color, new Position(6, x));
			}
			board[pos][0] = new Rook(color, new Position(7, 0));
			board[pos][1] = new Knight(color, new Position(7, 1));
			board[pos][2] = new Bishop(color, new Position(7, 2));
			board[pos][3] = new Queen(color, new Position(7, 4));
			board[pos][4] = new King(color, new Position(7, 3));
			board[pos][5] = new Bishop(color, new Position(7, 5));
			board[pos][6] = new Knight(color, new Position(7, 6));
			board[pos][7] = new Rook(color, new Position(7, 0));
		}
	}

	/**
	 * Accessor method to get this Board
	 * 
	 * @return the Board
	 */
	public Tile[][] getBoard() {
		return board;
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
	 * @param fromPos - the Piece's current position
	 * @param toPos - the Position to which the Piece will be moved
	 */
	public void moveMyPiece(Position fromPos, Position toPos) {
		// TO BE IMPLEMENTED
	}

	/**
	 * toString method
	 * 
	 * @return
	 */
	public String toString() {
		String x = "";
		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board[i].length; k++) {
				x += board[i][k] + " ";
			}
			x += "\n";
		}
		return x;
	}
}
