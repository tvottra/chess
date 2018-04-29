
public class Knight extends Piece {

    public Knight(int color, Position pos) {
        super(color, pos, 3);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isLegal(Position move) {
        int quadrant = this.getPosition().compareTo(move);
        if (quadrant == 0)
            return false;
        else if (quadrant == 1 && ((this.getPosition().getColumn() + 1 == move.getColumn() && this.getPosition().getRow() + 3 == move.getRow()) || (this.getPosition().getColumn() + 3 == move.getColumn() && this.getPosition().getRow() + 1 == move.getRow())))
            return true;
        else if (quadrant == 2 && ((this.getPosition().getColumn() - 1 == move.getColumn() && this.getPosition().getRow() - 3 == move.getRow()) || (this.getPosition().getColumn() - 3 == move.getColumn() && this.getPosition().getRow() - 1 == move.getRow())))
            return true;
        else if (quadrant == -1 && ((this.getPosition().getColumn() - 1 == move.getColumn() && this.getPosition().getRow() + 3 == move.getRow()) || (this.getPosition().getColumn() - 3 == move.getColumn() && this.getPosition().getRow() + 1 == move.getRow())))
            return true;
        else if (quadrant == -2 && ((this.getPosition().getColumn() + 1 == move.getColumn() && this.getPosition().getRow() - 3 == move.getRow()) || (this.getPosition().getColumn() + 3 == move.getColumn() && this.getPosition().getRow() - 1 == move.getRow())))
            return true;
        else
            return false;
    }
}
