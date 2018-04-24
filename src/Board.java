
public class Board {
	Piece[][] board;
	private final int NUM_PAWN = 8;

	public Board() {
		board = new Piece[8][8];
		for (int pos = 0, color = 0; color < 2; color++, pos += 7) {
			
			//generate white pawns
			for (int x = 0; x < NUM_PAWN; x++) {
				board[6][x] = new Pawn(color, new Position(6,x));
			}
			board[7][0] = new Rook(color, new Position(7,0));
			board[7][1] = new Knight(color, new Position(7, 1));
			board[7][2] = new Bishop(color, new Position(7,2));
			board[7][3] = new King(color, new Position(7,3));
			board[7][4] = new Queen(color, new Position(7,4));
			board[7][5] = new Bishop(color, new Position(7,5));
			board[7][6] = new Knight(color, new Position(7,6));
			
			
		}
	}
}
