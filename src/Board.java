
public class Board {
	Piece[][] board;
	private int numPawn = 8;

	public Board() {
		board = new Piece[8][8];
		for (int i = 0; i < numPawn; i++) {
			board[1][i] = new Pawn(1, new Position(1, i));
		}
		for (int i = 0; i < numPawn; i++) {
			board[7][i] = new Pawn(0, new Position(7, i));
		}
	}
}
