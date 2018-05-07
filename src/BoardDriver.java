import java.util.ArrayList;
/**
 * Class used to test the Board class; specifically; the hotspots and move methods
 * 
 *
 */
public class BoardDriver {

	public static void main(String[] args) {
		Board myBoard = new Board();
		System.out.print(myBoard);
		int row = 6;
		int col = 4;
		Piece testPiece = myBoard.getTile(row, col).getPiece();
		System.out.println("The piece is " + testPiece);

		// Range of movement test
		System.out.println("Here is the range of movement:");
		ArrayList<Position> range = testPiece.getRangeOfMovement();
		for (Position pos : range) {
			System.out.println(pos);
		}
		// Hotspots test

		ArrayList<Position> hotspots = myBoard.getHotspots(testPiece);
		System.out.println("Here are the hotspots:");
		for (Position pos : hotspots) {
			System.out.println(pos);
		}

	}

}
