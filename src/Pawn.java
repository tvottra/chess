import com.sun.javaws.exceptions.InvalidArgumentException;
import com.sun.tools.corba.se.idl.InvalidArgument;

import java.util.ArrayList;

/**
 * Class that represents a Pawn chess piece
 *
 * @author Brian
 */
public class Pawn extends Piece {
	private boolean promoted;

	/**
	 * Contructor to initialize the Pawn's color and Position
	 *
	 * @param color - the Pawn's color
	 * @param pos   - the Pawn's Position
	 */
	public Pawn(int color, Position pos) {
		super("Pawn", color, pos, 1);
	}

	@Override
	/**
	 * Returns an array of the Positions that would be crossed if this Pawn were to
	 * move to the given Position
	 *
	 * @param toPos
	 *            - the given Position
	 * @return an ArrayList of the Positions that would be crossed
	 */
	public ArrayList<Position> getCrossedPositions(Position toPos) {
		ArrayList<Position> iWillCross = new ArrayList<Position>();
		if (isWithinRangeOfMovement(toPos)) {
			iWillCross.add(getPosition());
			if (Math.abs(toPos.getRow() - getPosition().getRow()) == 2) {
				if (getColor() == 1) {
					iWillCross.add(new Position(getPosition().getRow() + 1, getPosition().getColumn()));
				} else {
					iWillCross.add(new Position(getPosition().getRow() - 1, getPosition().getColumn()));
				}
			}
			iWillCross.add(toPos);
		}
		return iWillCross;
	}

	@Override
	/**
	 * Calculates the Pawn's range of movement based on known board size and its
	 * current position.
	 *
	 * @return the Pawn's range of movement
	 */
	public ArrayList<Position> getRangeOfMovement(){
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

		rom.add(new Position(currentPos.getRow()+r, currentPos.getColumn())); //Add Position 1 Tile in front of Piece

		if (!hasMoved()) {
			rom.add(new Position(currentPos.getRow()+r+r, currentPos.getColumn())); //Add Position 2 Tiles in front of Piece
		}

		return rom;
	}

	@Override
	/**
	 * Checks whether the given Position is within this Pawn's range of movement
	 *
	 * @param toPos
	 *            - the destination Position
	 * @return true if toPos is within this Pawn's range of movement, false
	 *         otherwise
	 */
	public boolean isWithinRangeOfMovement(Position toPos) {
		if (super.getColor() == 0) {
			if (!hasMoved()) {
				return toPos.isWithinBounds() && ((getPosition().getRow() - toPos.getRow() == 2
						&& getPosition().getColumn() == toPos.getColumn())
						|| (getPosition().getRow() - toPos.getRow() == 1
						&& Math.abs(getPosition().getColumn() - toPos.getColumn()) <= 1));
			} else {
				return toPos.isWithinBounds() && (getPosition().getRow() - toPos.getRow() == 1
						&& Math.abs(getPosition().getColumn() - toPos.getColumn()) <= 1);
			}
		} else {
			if (!hasMoved()) {
				return toPos.isWithinBounds() && ((toPos.getRow() - getPosition().getRow() == 2
						&& getPosition().getColumn() == toPos.getColumn())
						|| (toPos.getRow() - getPosition().getRow() == 1
						&& Math.abs(getPosition().getColumn() - toPos.getColumn()) <= 1));
			} else {
				return toPos.isWithinBounds() && (getPosition().getRow() - toPos.getRow() == 1
						&& Math.abs(getPosition().getColumn() - toPos.getColumn()) <= 1);
			}
		}

	}

	public void promotion() {
		promoted = true;
	}
}
