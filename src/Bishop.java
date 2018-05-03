import java.util.ArrayList;

public class Bishop extends Piece {

	public Bishop(int color, Position pos) {
		super("Bishop", color, pos, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Position> move(Position toPos) {

		Position currentPos = new Position(getPosition());
		ArrayList<Position> iWillCross = new ArrayList<Position>();

		if (isWithinRangeOfMovement(toPos)) {
			int r, c;
			if (currentPos.isAbove(toPos)) {
				r = 1;
			} else {
				r = -1;
			}
			if (currentPos.isLeftOf(toPos)) {
				c = 1;
			} else {
				c = -1;
			}
			while (currentPos.isWithinBounds()) {
				iWillCross.add(new Position(currentPos));
				currentPos.addToRow(r);
				currentPos.addToColumn(c);
			}
		}
		return iWillCross;
	}

	@Override
	public boolean isWithinRangeOfMovement(Position toPos) {
		return getRangeOfMovement().contains(toPos);
	}

	@Override
	public ArrayList<Position> getRangeOfMovement() {
		ArrayList<Position> field = new ArrayList<Position>();


		//Get quadrant 1
		Position pos1 = new Position(getPosition());
		while (pos1.isWithinBounds()) {
			field.add(new Position(pos1));
			pos1.addToRow(-1);
			pos1.addToColumn(1);
		}

		//Get quadrant 2
		Position pos2 = new Position(getPosition());
		while (pos2.isWithinBounds()) {
			field.add(new Position(pos2));
			pos2.addToRow(-1);
			pos2.addToColumn(-1);
		}

		//Get quadrant 3
		Position pos3 = new Position(getPosition());
		while (pos3.isWithinBounds()) {
			field.add(new Position(pos3));
			pos3.addToRow(1);
			pos3.addToColumn(-1);
		}

		//Get quadrant 4
		Position pos4 = new Position(getPosition());
		while (pos4.isWithinBounds()) {
			field.add(new Position(pos4));
			pos4.addToRow(1);
			pos4.addToColumn(1);
		}

		return field;

	}
}
