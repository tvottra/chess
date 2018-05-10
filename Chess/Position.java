/**
 * Class that represents the position of a Piece in [row][column] format
 *
 * @author Arjun Agrawal, Tommy Tran
 */
public class Position {
	private int row;
	private int column;
	private final int BOARD_SIZE = 8;

	/**
	 * Constructor to initialize the row and column of this Position
	 *
	 * @param row    - the row index of this Position
	 * @param column - the column index of this Position
	 */
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}

	/**
	 * Constructor initialize a new Position with another Position's coordinates
	 *
	 * @param other - some other Position
	 */
	public Position(Position other) {
		row = other.getRow();
		column = other.getColumn();
	}

	/**
	 * Accessor method to get the row index
	 *
	 * @return the row index
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Accessor method to get the column index
	 *
	 * @return the column index
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Mutator method to set the row index to the given row index
	 *
	 * @param row - the given row index
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Mutator method to set the column index to the given column index
	 *
	 * @param column - the given column index
	 */

	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Method to determine whether this Position and the given Position are equal
	 *
	 * @param other - the given Position
	 * @return true if this Position and other have the same row and column indexes,
	 * false otherwise
	 */
	public boolean equals(Position other) {
		if (this.row == other.getRow() && this.column == other.getColumn())
			return true;
		return false;
	}

	/**
	 * Compares this Position to some other Position
	 *
	 * @param other - the given Position
	 * @return 1 if other is in first quadrant, 2 if other is in second quadrant, 3
	 * if other is in third quadrant, 4 if other is in fourth quadrant, -1
	 * if inappropriate
	 */
	public int compareTo(Position other) {
		if (other.getRow() > this.row && other.getColumn() > this.column) {
			return 1; // other is in first quadrant
		} else if (other.getRow() < this.row && other.getColumn() < this.column) {
			return 3; // other is in third quadrant
		} else if (other.getRow() > this.row && other.getColumn() < this.column) {
			return 2; // other is in second quadrant
		} else if (other.getRow() < this.row && other.getColumn() > this.column) {
			return 4; // other is in fourth quadrant
		} else {
			return -1;
		}
	}

	/**
	 * Determines whether this Position is left of the other Position
	 *
	 * @param other - some other Position
	 * @return true if this Position is left of the other Position, false otherwise.
	 */
	public boolean isLeftOf(Position other) {
		return column < other.getColumn();
	}

	/**
	 * Determines whether this Position is above the other Position
	 *
	 * @param other - some other Position
	 * @return true if this Position is above the other Position, false otherwise.
	 */
	public boolean isAbove(Position other) {
		return row < other.getRow();
	}

	/**
	 * toString method for this Position
	 *
	 * @return the Position in the following format: [row][column]
	 */
	public String toString() {
		return "[" + row + "]" + "[" + column + "]";
	}

	/**
	 * Add to row the given number
	 *
	 * @param n - the given number to add
	 */
	public void addToRow(int n) {
		row = row + n;
	}

	/**
	 * Add to column the given number
	 *
	 * @param n - the given number to add
	 */
	public void addToColumn(int n) {
		column = column + n;
	}

	/**
	 * Checks whether the Position is within the bounds of the board size
	 *
	 * @return true Position is within bounds, false otherwise
	 */
	public boolean isWithinBounds() {
		return (0 <= row && 0 <= column) && (row < BOARD_SIZE && column < BOARD_SIZE);
	}

	/**
	 * Mutator method to set this Position to some other Position
	 *
	 * @param pos some other Position
	 */
	public void setPosition(Position pos) {
		row = pos.getRow();
		column = pos.getColumn();
	}

	/**
	 * Mutator method to set the Piece's Position to some r and c
	 *
	 * @param r desired row
	 * @param c desired column
	 */
	public void setPosition(int r, int c) {
		row = r;
		column = c;
	}

	/**
	 * Get whether the absolute value of the slope from this Position to another Position is 1 by comparing row1 and row2 and column1 and column2.
	 * Favored over division with floats, as floats can lose accuracy.
	 *
	 * @param other - some other Position
	 * @return true if absolute value of slope is 1; false otherwise.
	 */
	public boolean hasAbsSlopeOfOne(Position other) {
		return Math.abs(other.getRow() - row) == Math.abs(other.getColumn() - column);

	}

}