/**
 * Class that represents the position of a Piece in [row][column] format
 * @author Arjun Agrawal
 * @author Andrew Le (documentation)
 *
 */
public class Position {

	private int row;
	private int column;

	/**
	 * Constructor to initialize the row and column of this Position
	 * 
	 * @param row
	 * @param column
	 */
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
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
	 * @param row
	 *            - the given row index
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Mutator method to set the column index to the given column index
	 * 
	 * @param column
	 *            - the given column index
	 */

	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Method to determine whether this Position and the given Position are equal
	 * 
	 * @param other
	 *            - the given Position
	 * @return true if this Position and other have the same row and column indexes,
	 *         false otherwise
	 */
	public boolean equals(Position other) {
		if (this.row == other.getRow() && this.column == other.getRow())
			return true;
		return false;
	}

	/**
	 * Compares this Position to the given Position; (CURRENT POSITION IS ASSUMED AT
	 * ( 0, 0)):
	 *
	 * @param other
	 *            - the given Position
	 * @return 1 if other is in first quadrant; 3 if other is in third quadrant; 2
	 *         if other is in second quadrant; and 4 if other is in fourth quadrant
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
			return 0;
		}
	}
}
