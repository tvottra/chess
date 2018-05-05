import java.util.ArrayList;
/**
 * Class used to test the Board class; specifically; the hotspots and move methods
 * 
 *
 */
public class BoardDriver {

	public static void main(String[] args) {
		Board gameBoard = new Board();

		int row = 7;
		int col = 0;
		Piece testPiece = gameBoard.getTile(row, col).getPiece();
		// System.out.println(gameBoard.getTile(row, col));
		
		// Range of movement test
		System.out.println("Here is the range of movement:");
		ArrayList<Position> range = testPiece.getRangeOfMovement();
		for (Position pos : range) {
			System.out.println(pos);
		}
		// Hotspots test
		System.out.println("Here are the hotspots:");
		ArrayList<Position> hotspots = gameBoard.getHotspots(testPiece);
		for (Position pos : hotspots) {
			System.out.println(pos);
		}

	}

}
