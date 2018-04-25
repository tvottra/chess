
public class Pawn extends Piece {

	public Pawn(int color, Position pos) {
		super(color, pos, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isLegal(Position move) {
		if (getPosition().getColumn() == move.getColumn() + 1)
			return true;
		else if()
			return false;
		return false;
	}

}
