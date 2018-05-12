import java.util.ArrayList;

/**
 * Class that represents a chess player
 *
 * @author Andrew Le
 */
public class Player {
	private String myName;
	private int myNumber;
	private int myScore;
	private boolean checkmated;
	private boolean resigned;
	private ArrayList<Comparable> myCapturedPieces = new ArrayList<>();

	/**
	 * Constructor to initialize this player's name and number to the given values
	 * and score to 0
	 *
	 * @param name   - the given name
	 * @param number - the given number
	 */
	public Player(String name, int number) {
		myName = name;
		myNumber = number;
		myScore = 0;
		checkmated = false;
		resigned = false;
	}

	/**
	 * Accessor method to get the player's name
	 *
	 * @return the player's name
	 */
	public String getName() {
		return myName;
	}

	/**
	 * Mutator method to change the player's name
	 *
	 * @param name - the new name
	 */
	public void setName(String name) {
		myName = name;
	}

	/**
	 * Accessor method to get the player's number
	 *
	 * @return the player's number
	 */
	public int getNumber() {
		return myNumber;
	}

	/**
	 * Accessor method to get the player's score
	 *
	 * @return the player's score
	 */
	public int getScore() {
		return myScore;
	}

	/**
	 * Mutator method to increment the player's score by the given value
	 *
	 * @param val - the given value
	 */
	public void incrementScore(int val) {
		myScore += val;
	}

	/**
	 * Accessor method to get the checkmate status
	 *
	 * @return true if this player is checkmated, false otherwise
	 */
	public boolean isCheckMated() {
		return checkmated;
	}

	/**
	 * Mutator method to set the checkmate status
	 *
	 * @param checkmate - true if the player has been checkmated, false otherwise
	 */
	public void setCheckMate(boolean checkmate) {
		checkmated = checkmate;
	}

	/**
	 * Mutator method to set the resign status
	 *
	 * @param resign - true if the player has resigned, false otherwise
	 */
	public void setResign(boolean resign) {
		resigned = resign;
	}

	/**
	 * Accessor method to get the resign status
	 *
	 * @return true if the player has resigned, false otherwise
	 */
	public boolean isResigned() {
		return resigned;
	}

	/**
	 * Determines hether this player and the given player are the same player
	 *
	 * @param other - the given player
	 * @return true if both players are the same player, false otherwise
	 */
	public boolean equals(Player other) {
		if (myName.equals(other.getName()) && myNumber == other.getNumber()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Requests a draw from the opposing player; calls acceptDraw
	 *
	 * @return true if this player offers a draw; false otherwise
	 */
	public boolean requestDraw(Player other) {
		// TO BE IMPLEMENTED
		return true;
	}

	/**
	 * Responds to a draw offer from the opposing player
	 *
	 * @param other - the opposing player
	 * @return true if this player accepts the draw offer, false otherwise
	 */
	public boolean acceptDraw(Player other) {
		// TO BE IMPLEMENTED
		return true;
	}

	/**
	 * toString method to print this player's information
	 */
	public String toString() {
		return myName + " (" + myNumber + ")";
	}

	/**
	 * Add captured Piece to this Player's list and sorts the list in descending order of piece point value.
	 *
	 * @param p piece that was captured
	 */
	public void addCapturedPiece(Piece p) {
		myCapturedPieces.add(p);
		Toolbox.quickSort(myCapturedPieces, 0, myCapturedPieces.size()-1);
	}

	public String getCapturedPieces() {
		String s = "";
		for(Comparable p: myCapturedPieces) {
			s += ((Piece) p).getName() + " (" + ((Piece)p).getPointValue() + "), ";
		}
		if(s.length() >= 3) {
			s = s.substring(0, s.length()-2);
		}
		if(s.length() == 0) {
			s = "You have not captured any pieces.";
		}
		return s;
	}
}
