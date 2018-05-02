import java.util.ArrayList;

public class Pawn extends Piece {
	private boolean hasMoved;
	private boolean promoted;

	public Pawn(int color, Position pos) {
		super("Pawn", color, pos, 1);
	}

	public boolean isWithinRangeOfMovement(Position toPos) {
		if(!hasMoved) {
			return toPos.getRow() - getPosition().getRow() == 2;
		} else {
			return toPos.isWithinBounds() && toPos.getRow() - getPosition().getRow() == 1;
		}
	}

	public ArrayList<Position> getFieldOfControl() {
		ArrayList<Position> field = new ArrayList<Position>();

		int row = getPosition().getRow();
		int col = getPosition().getColumn();

		field.add(getPosition());
		field.add(new Position(row, col + 1));
		if(!hasMoved) {
			field.add(new Position(row, col + 2));
		}
		return field;
	}

	public ArrayList<Position> move(Position toPos) {
		ArrayList<Position> iWillCross = new ArrayList<Position>();
		if(isWithinRangeOfMovement(toPos)) {
			iWillCross.add(getPosition());
			if(toPos.getRow() - getPosition().getRow() == 2) {
				iWillCross.add(new Position(getPosition().getRow() + 1, getPosition().getColumn()));
			}
			iWillCross.add(toPos);
		}
		return iWillCross;
	}

	public boolean hasMoved() {
		return hasMoved;
	}

	public void setMoveState(boolean moveState) {
		hasMoved = moveState;
	}

	public void promotion() {
		promoted = true;
	}
}