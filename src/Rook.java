
/**
 * Class that represents a Rook chess piece
 */

import java.util.ArrayList;

/**
 * @author Andrew
 */
public class Rook extends Piece {

    /**
     * @param color - the color of the piece
     * @param pos   - the Position of the piece
     */
    public Rook(int color, Position pos) {
        super("Rook", color, pos, 5);
    }


    /**
     * Determines the Positions that would be crossed if this Rook were to move from
     * its current position to the given position
     *
     * @param toPos - the given position
     * @return an ArrayList of crossed Positions
     */
    private ArrayList<Position> getCrossedPositions(Position toPos) {
        ArrayList<Position> positions = new ArrayList<Position>();
        Position fromPos = getPosition();
        // The two Positions are in the same row
        if (fromPos.getRow() == toPos.getRow()) {
            for (int c = fromPos.getColumn(); c < toPos.getColumn(); c++) {
                Position pos = new Position(fromPos.getRow(), c);
                positions.add(pos);
            }
        }
        // The two Positions are in the same column
        if (fromPos.getColumn() == toPos.getColumn()) {
            for (int r = fromPos.getRow(); r < toPos.getRow(); r++) {
                Position pos = new Position(r, fromPos.getColumn());
                positions.add(pos);
            }
        }
        return positions;
    }

    @Override
    /**
     * Returns an array of the Positions that would be crossed if this Rook were to
     * move to the given Position
     *
     * @param toPos
     *            - the given Position
     * @return an ArrayList of the Positions that would be crossed
     */
    public ArrayList<Position> move(Position toPos) {
        if (isWithinRangeOfMovement(toPos)) {
            return getCrossedPositions(toPos);
        } else {
            return null;
        }
    }

    @Override
    /**
     * Calculates the Rook's field of control based on known board size and its
     * current position. Positions are ordered ascending in terms of row then
     * column. E.g., (0, 0), (0, 1), (0, 2), (1, 0)...
     *
     * @return the Rook's field of control
     */
    public ArrayList<Position> getRangeOfMovement() {
        Position pos = getPosition();
        ArrayList<Position> fieldOfControl = new ArrayList<Position>();
        fieldOfControl = new ArrayList<Position>();
        // Add all positions above current position (same column)
        for (int r = pos.getRow() - 1; r >= 0; r--) {
            Position p = new Position(r, pos.getColumn());
            fieldOfControl.add(p);
        }

        fieldOfControl.add(pos);
        for (int r = pos.getRow() + 1; r < getSize(); r++) {
            Position p = new Position(r, pos.getColumn());
            fieldOfControl.add(p);
        }

        fieldOfControl.add(pos);
        for (int c = pos.getColumn() - 1; c >= 0; c--) {
            Position p = new Position(pos.getRow(), c);
            fieldOfControl.add(p);
        }

        fieldOfControl.add(pos);
        for (int c = pos.getColumn() + 1; c < getSize(); c++) {
            Position p = new Position(pos.getRow(), c);
            fieldOfControl.add(p);
        }

        return fieldOfControl;
    }

    @Override
    /**
     * Checks whether the given Position is within this Rook's range of movement
     *
     * @param toPos
     *            - the destination Position
     * @return true if toPos is within this Rook's range of movement, false
     *         otherwise
     */
    public boolean isWithinRangeOfMovement(Position toPos) {
        ArrayList<Position> fieldOfControl = getRangeOfMovement();
        for (Position pos : fieldOfControl) {
            if (pos.equals(toPos)) {
                return true;
            }
        }
        return false;
    }
}
