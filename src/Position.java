
public class Position {

    int row;
    int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean equals(Position other) {
        if (this.row == other.getRow() && this.column == other.getRow())
            return true;
        return false;
    }

    /**
     * Compares the current position to position other
     *
     * @param other the other position
     * @return (IF CURRENT POSITION IS ASSUMED AT ( 0, 0)): 1 if other is in first quadrant, 3 if other is in third quadrant, 2 if other is in second quadrant, and 4 if other is in fourth quadrant
     */
    public int compareTo(Position other) {
        if (other.getRow() > this.row && other.getColumn() > this.column) {
            return 1; //other is in first quadrant
        } else if (other.getRow() < this.row && other.getColumn() < this.column) {
            return 3; //other is in third quadrant
        } else if (other.getRow() > this.row && other.getColumn() < this.column) {
            return 2; //other is in second quadrant
        } else if (other.getRow() < this.row && other.getColumn() > this.column) {
            return 4; //other is in fourth quadrant
        } else {
            return 0;
        }
    }
}
