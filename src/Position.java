/**
 * Class that represents the position of a Piece in [row][column] format
 *
 * @author Arjun Agrawal, Tommy V. Tran
 * @author Andrew Le (documentation)
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
		if (this.row == other.getRow() && this.column == other.getRow())
			return true;
		return false;
	}

	/**
	 * Compares this Position to some other Position
	 *
	 * @param other - the given Position
	 * @return 1 if other is in first quadrant, 2 if other is in second quadrant, 3 if other is in third quadrant,
	 * 4 if other is in fourth quadrant, -1 if inapppropriate
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
	 * See whether this Position is left of the other Position
	 *
	 * @param other - some other Position
	 * @return true if this Position is left of the other Position, false otherwise.
	 */
	public boolean isLeftOf(Position other) {
		return column < other.getColumn();
	}

	/**
	 * See whether this Position is above the other Position
	 *
	 * @param other - some other Position
	 * @return true if this Position is above the other Position, false otherwise.
	 */
	public boolean isAbove(Position other) {
		return row < other.getRow();
	}

	/**
	 * toString method
	 *
	 * @return the Position in the following format: [row][column]
	 */
	public String toString() {
		return "[" + row + "]" + "[" + column + "]";
	}

	/**
	 * Add to row this number
	 *
	 * @param n - number to add
	 */
	public void addToRow(int n) {
		row = row + n;
	}

	/**
	 * Add to column this number
	 *
	 * @param n - number to add
	 */
	public void addToColumn(int n) {
		column = column + n;
	}


	/**
	 * Check whether the Position is within the bounds of the board size
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
	 * Get the slope from this Position to another Position
	 *
	 * @param other - some other Position
	 * @return the slope (e.g., an upward slope would be positive as on an xy system, even though rows and columns used)
	 */
	public double slopeTo(Position other) {
		return -(row - other.getRow()) / (double) (column - other.getColumn());
	}


}
