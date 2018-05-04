import java.util.ArrayList;

/**
 * @author Arjun
 */
public class Knight extends Piece {
  
    public Knight(int color, Position pos) {
        super("Knight", color, pos, 3);
        // TODO Auto-generated constructor stub
    }

    /**
     * Checks whether the given Position is within this Piece's range of movement
     *
     * @param move - the destination Position
     * @return true if toPos is within this Piece's range of movement, false
     * otherwise
     */
    @Override
    public boolean isWithinRangeOfMovement(Position move) {
        int quadrant = this.getPosition().compareTo(move);
        if (quadrant == 0)
            return false;
        else if (quadrant == 1 && ((this.getPosition().getColumn() + 1 == move.getColumn()
                && this.getPosition().getRow() + 3 == move.getRow())
                || (this.getPosition().getColumn() + 3 == move.getColumn()
                && this.getPosition().getRow() + 1 == move.getRow())))
            return true;
        else if (quadrant == 2 && ((this.getPosition().getColumn() - 1 == move.getColumn()
                && this.getPosition().getRow() - 3 == move.getRow())
                || (this.getPosition().getColumn() - 3 == move.getColumn()
                && this.getPosition().getRow() - 1 == move.getRow())))
            return true;
        else if (quadrant == -1 && ((this.getPosition().getColumn() - 1 == move.getColumn()
                && this.getPosition().getRow() + 3 == move.getRow())
                || (this.getPosition().getColumn() - 3 == move.getColumn()
                && this.getPosition().getRow() + 1 == move.getRow())))
            return true;
        else if (quadrant == -2 && ((this.getPosition().getColumn() + 1 == move.getColumn()
                && this.getPosition().getRow() - 3 == move.getRow())
                || (this.getPosition().getColumn() + 3 == move.getColumn()
                && this.getPosition().getRow() - 1 == move.getRow()))) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    /**
     * Returns an array of the Positions that would be crossed if this Knight were
     * to move to the given Position
     *
     * @param toPos
     *            - the given Position
     * @return an ArrayList of the Positions that would be crossed
     */
    public ArrayList<Position> move(Position toPos) {
        ArrayList<Position> pos = new ArrayList<Position>();
        pos.add(toPos);
        return pos;
    }

    @Override
    /**
     * Calculates the Knight's field of control based on known board size and its
     * current position. Positions are ordered ascending in terms of row then
     * column. E.g., (0, 0), (0, 1), (0, 2), (1, 0)...
     *
     * @return the Knight's field of control
     */
    public ArrayList<Position> getFieldOfControl() {
        ArrayList<Position> field = new ArrayList<Position>();
        if (new Position(this.getPosition().getRow() + 2, this.getPosition().getColumn() + 1).isWithinBounds())
            field.add(new Position(this.getPosition().getRow() + 2, this.getPosition().getColumn() + 1));
        if (new Position(this.getPosition().getRow() + 2, this.getPosition().getColumn() - 1).isWithinBounds())
            field.add(new Position(this.getPosition().getRow() + 2, this.getPosition().getColumn() - 1));
        if (new Position(this.getPosition().getRow() - 2, this.getPosition().getColumn() - 1).isWithinBounds())
            field.add(new Position(this.getPosition().getRow() - 2, this.getPosition().getColumn() - 1));
        if (new Position(this.getPosition().getRow() - 2, this.getPosition().getColumn() + 1).isWithinBounds())
            field.add(new Position(this.getPosition().getRow() - 2, this.getPosition().getColumn() + 1));
        if (new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() + 2).isWithinBounds())
            field.add(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() + 2));
        if (new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() + 2).isWithinBounds())
            field.add(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() + 2));
        if (new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() - 2).isWithinBounds())
            field.add(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() - 2));
        if (new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() - 2).isWithinBounds())
            field.add(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() - 2));
        return field;
    }
}
