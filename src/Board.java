
public class Board {
	Piece[][] board;
	private final int NUM_PAWN = 8;

	public Board() {
		board = new Slot[8][8];
		// generates white side first, then black
		for (int pos = 7, color = 0; color < 2; color++, pos -= 6) {
			// generate color pawns pawns
			for (int x = 0; x < NUM_PAWN; x++) {
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

	public Piece[][] getBoard() {
		return board;
	}

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
