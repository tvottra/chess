/**
 * Class that represents a chess player
 * 
 * @author Andrew Le
 *
 */
public class Player {
	private String myName;
	private boolean checkMated;

	/**
	 * Constructor to initialize this player's name
	 * 
	 * @param name
	 *            - the given name
	 */
	public Player(String name) {
		myName = name;
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
	 * Accessor method to get the checkmate status
	 * 
	 * @return true if this player is checkmated, false otherwise
	 */
	public boolean isCheckMated() {
		return checkMated;
	}

	/**
	 * Mutator method to set the checkmate status
	 * 
	 * @param checkmated
	 *            - true for checkmate, false otherwise
	 */
	public void setCheckMate(boolean checkmated) {
		this.checkMated = checkMated;
	}

	/**
	 * Concedes the game to this player's opponent
	 */
	public void resign() {
		// TO BE IMPLEMENTED
	}

	/**
	 * Requests a draw from the opposing player; calls acceptDraw
	 * 
	 * @return true if this player offers a draw; false otherwise
	 */
	public boolean requestDraw(Player other) {
		// TO BE IMPLEMENTED
	}

	/**
	 * Responds to a draw offer from the opposing player
	 * 
	 * @param other
	 *            - the opposing player
	 * @return true if this player accepts the draw offer, false otherwise
	 */
	public boolean acceptDraw(Player other) {
		// TO BE IMPLEMENTED
	}

}
