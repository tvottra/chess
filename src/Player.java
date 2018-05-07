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

	/**
	 * Constructor to initialize this player's name and number to the given values
	 * and score to 0
	 * 
	 * @param name
	 *            - the given name
	 * @param number
	 *            - the given number
	 */
	public Player(String name, int number) {
		myName = name;
		myNumber = number;
		myScore = 0;
		checkmated = false;
		resigned = false;
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
     * Accessor method to get the checkmate status
     *
     * @return true if this player is checkmated, false otherwise
     */
    public boolean isCheckMated() {
        return checkmated;
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
	 * @param val
	 *            - the given value
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
        return myName + "(" + myNumber + ")";
    }


}
