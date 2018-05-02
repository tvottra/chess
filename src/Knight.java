
public class Knight extends Piece {

    public Knight(int color, Position pos) {
        super("Knight", color, pos, 3);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean isWithinRangeOfMovement(Position move) {
        int quadrant = this.getPosition().compareTo(move);
        if (quadrant == 0)
            return false;
        else if (quadrant == 1 && ((this.getPosition().getColumn() + 1 == move.getColumn() && this.getPosition().getRow() + 3 == move.getRow()) || (this.getPosition().getColumn() + 3 == move.getColumn() && this.getPosition().getRow() + 1 == move.getRow())))
            return true;
        else if (quadrant == 2 && ((this.getPosition().getColumn() - 1 == move.getColumn() && this.getPosition().getRow() - 3 == move.getRow()) || (this.getPosition().getColumn() - 3 == move.getColumn() && this.getPosition().getRow() - 1 == move.getRow())))
            return true;
        F
        else
        if (quadrant == -1 && ((this.getPosition().getColumn() - 1 == move.getColumn() && this.getPosition().getRow() + 3 == move.getRow()) || (this.getPosition().getColumn() - 3 == move.getColumn() && this.getPosition().getRow() + 1 == move.getRow())))
            return true;
        else if (quadrant == -2 && ((this.getPosition().getColumn() + 1 == move.getColumn() && this.getPosition().getRow() - 3 == move.getRow()) || (this.getPosition().getColumn() + 3 == move.getColumn() && this.getPosition().getRow() - 1 == move.getRow())))
            return true;
        else
            return false;
    }

    public ArrayList<Position> move(Position toPos) {
        ArrayList<Position> pos = new ArrayLost<Position>();
        pos.add(toPos);
        return pos;
    }


    public abstract ArrayList<Position> getFieldOfControl() {
        ArrayList<Position> pos = new ArrayList<Position>();
        pos.add(new Position(this.getPosition().row + 2, this.getPosition().column + 1));
        pos.add(new Position(this.getPosition().row + 2, this.getPosition().column - 1));
        pos.add(new Position(this.getPosition().row - 2, this.getPosition().column - 1));
        pos.add(new Position(this.getPosition().row - 2, this.getPosition().column + 1));
        pos.add(new Position(this.getPosition().row + 1, this.getPosition().column + 2));
        pos.add(new Position(this.getPosition().row - 1, this.getPosition().column + 2));
        pos.add(new Position(this.getPosition().row + 1, this.getPosition().column - 2));
        pos.add(new Position(this.getPosition().row - 1, this.getPosition().column - 2));
        return pos;
    }


}
