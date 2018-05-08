import java.util.ArrayList;
import java.util.Vector;

/**
 * @author Tommy V. T.
 *
 * "AI" that randomly scans through the board for valid moves and goes for the one with highest point value.
 */
public class AI extends Player {
	String name;
	Board boardIAnalyze;

	public AI(String name, int number, Board bd) {
		super(name, number);
		boardIAnalyze = bd;
	}


	public Vector generateMoves() {
		Tile[][] chessBoard = boardIAnalyze.getBoard(); //don't modify original board yet!
		Position pos;
		for(int r = 0; r < chessBoard.length; r++) {
			for(int c = 0; c < chessBoard[0].length; c++) {
				pos = new Position(r, c);
				if(Game.isValidPiece(pos, super.getNumber(), boardIAnalyze)) {
					Piece myPiece = Piece.createPiece(chessBoard[r][c].getPiece());
					ArrayList<Position> hotSpots = boardIAnalyze.getHotSpots(myPiece);								//this points to the same Board as the main Board if the main Board is passed to AI's constructor

					}
				}
			}
		}
	}



}
