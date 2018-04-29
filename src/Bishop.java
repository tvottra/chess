import java.util.ArrayList;

public class Bishop extends Piece {

	public Bishop(int color, Position pos) {
		super("Bishop", color, pos, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Position> move(Position toPos) {

		Position currentPos = getPosition();
		ArrayList<Position> iWillCross = new ArrayList<Position>();

		if (isWithinRangeOfMovement(toPos)) {
			int r, c;
			if (currentPos.isAbove(toPos)) {
				r = -1;
			} else {
				r = 1;
			}
			if(currentPos.isLeftOf(toPos)) {
				c = -1;
			} else {
				c = 1;
			}
			while(currentPos.isWithinBounds()) {
				iWillCross.add(currentPos);
				currentPos.addToRow(r);
				currentPos.addToColumn(c);
			}
		}
		return iWillCross;
	}

	@Override
	public boolean isWithinRangeOfMovement(Position toPos) {
		return Math.abs(getPosition().getRow() - getPosition().getColumn()) == Math.abs(toPos.getRow() - toPos.getColumn());
	}

	@Override
	public ArrayList<Position> getFieldOfControl() {
		ArrayList<Position> field = new ArrayList<Position>();


		//Get quadrant 1
		Position pos1 = getPosition();
		while(pos1.isWithinBounds()) {
			field.add(pos1);
			pos1.addToRow(-1);
			pos1.addToColumn(1);
		}

		//Get quadrant 2
		Position pos2 = getPosition();
		while(pos2.isWithinBounds()) {
			field.add(pos2);
			pos2.addToRow(-1);
			pos2.addToColumn(-1);
		}

		//Get quadrant 3
		Position pos3 = getPosition();
		while(pos3.isWithinBounds()) {
			field.add(pos3);
			pos3.addToRow(1);
			pos3.addToColumn(-1);
		}

		//Get quadrant 4
		Position pos4 = getPosition();
		while(pos4.isWithinBounds()) {
			field.add(pos4);
			pos4.addToRow(1);
			pos4.addToColumn(1);
		}
		return field;

 	}
}
