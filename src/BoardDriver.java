import java.util.ArrayList;
/**
 * Class used to test the Board class; specifically; the hotSpots and move methods
 * 
 * @author  Andrew Le
 */
public class BoardDriver {

	public static void main(String[] args) {
		Board myBoard = new Board();
		System.out.print(myBoard);
		int row = 7;
		int col = 4;
		Piece testPiece = myBoard.getTile(row, col).getPiece();
		System.out.println("The piece is " + testPiece);

		// Range of movement test
		System.out.println("Here is the range of movement:");
		ArrayList<Position> range = testPiece.getRangeOfMovement();
		for (Position pos : range) {
			System.out.println(pos);
		}
		// HotSpots test

		ArrayList<Position> hotSpots = myBoard.getHotSpots(testPiece, myBoard.getBoard());
		System.out.println("Here are the hotSpots:");
		if(hotSpots == null) {
			System.out.println("hotSpots is null");
		} else {
			for (Position pos : hotSpots) {
				System.out.println(pos);
			}
		}

	}

}
