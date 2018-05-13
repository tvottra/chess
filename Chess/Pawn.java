import java.util.ArrayList;

/**
 * Class that represents a Pawn chess piece
 *
 * @author Brian Qiu
 */
public class Pawn extends Piece {
	private boolean hasMovedTwo; // whether the Pawn has used the 2-tile move

	/**
	 * Contructor to initialize the Pawn's color and Position
	 *
	 * @param color
	 *            the Pawn's color
	 * @param pos
	 *            the Pawn's Position
	 */
	public Pawn(int color, Position pos) {
		super("Pawn", color, pos, 1);
		hasMovedTwo = false;
	}

	@Override
	/**
	 * Calculates the Pawn's range of movement based on known board size and its
	 * current position.
	 *
	 * @return the Pawn's range of movement
	 */
	public ArrayList<Position> getRangeOfMovement() {
		ArrayList<Position> rom = new ArrayList<Position>();
		Position currentPos = new Position(getPosition());
		int r = 0;
		if (getColor() == 0) {
			r = -1;
		} else if (getColor() == 1) {
			r = 1;
		} else {
			System.out.println("Incorrect implementation of Pawn ROM");
		}
		rom.add(new Position(currentPos.getRow() + r, currentPos.getColumn())); // Add Position 1 Tile in front of Piece

		if (!hasMoved()) {
			rom.add(new Position(currentPos.getRow() + r + r, currentPos.getColumn())); // Add Position 2 Tiles in front
																						// of Piece
		}
		return rom;
	}

	/**
	 * Determines whether this Pawn need to be promoted; a white Pawn is promoted if
	 * it is on row 0; a black Pawn is promoted if it is on row 7.
	 *
	 * @return true if this Pawn should be promoted, false otherwise
	 */
	public boolean isWaitingForPromotion() {
		if(getColor() == 0){
		    if(getPosition().getRow() == 1){
		        return true;
		      }
		      else{
		          return false;
		      }
		  }
		  else{
		      if(getPosition().getRow() == 6){
		          return true;
		      }
		      else{
		          return false;
		      }
		  }
	}

	/**
	 * Accessor method to get the Pawn's 2-tile move status
	 * 
	 * @return true if the Pawn has already used the 2-tile move, false otherwise
	 */
	public boolean hasMovedTwo() {
		return hasMovedTwo;
	}

	/**
	 * Mutator method to update the Pawn's 2-tile move status
	 * 
	 * @param moved
	 *            true if the Pawn has used the 2-tile move, false otherwise
	 */
	public void setHasMovedTwo(boolean moved) {
		hasMovedTwo = moved;
	}
}