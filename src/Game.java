/**
 * Class that represents a Chess game containing Players and a Board
 * 
 * @author Andrew Le
 *
 */
public class Game {
	private Player playerOne; // white
	private Player playerTwo; // black
	private Board board;
	private boolean draw;
	private boolean myTurn; // true if it is playerOne's turn, false otherwise

	/**
	 * Constructor to initialize the players, board, and game state
	 * 
	 * @param name1
	 *            - playerOne's name
	 * @param name2
	 *            - playerTwo's name
	 */
	public Game(String name1, String name2) {
		playerOne = new Player(name1);
		playerTwo = new Player(name2);
		board = new Board();
		draw = false;
		myTurn = true;
		// TO BE IMPLEMENTED
		startGame();
	}

	/**
	 * Begins the chess game
	 */
	public void startGame() {

	}

	/**
	 * Accesses the Board and attempts to move a Piece at a Tile to the given
	 * Position
	 * 
	 * @param fromPos
	 *            - the Piece's current position
	 * @param toPos
	 *            - the Position to which the Piece will be moved
	 */
	public void movePiece(Position fromPos, Position toPos) {
		// TO BE IMPLEMENTED
	}

	/**
	 * Determines whether the Piece at its current position can legally move to the
	 * given position
	 * 
	 * @param fromPos
	 *            - the Piece's current position
	 * 
	 * @param toPos
	 *            - the Position to which the Piece will be moved
	 * @return true if the move is legal, false otherwise
	 */
	public boolean isLegalMove(Position fromPos, Position toPos) {
		// TO BE IMPLEMENTED
	}

	/**
	 * Mutator method that updates the draw status of the game
	 * 
	 * @param draw
	 *            - true if there is a draw, false otherwise
	 */
	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	/**
	 * Mutator method that updates the turn
	 * 
	 * @param turn
	 *            - true if it is playerOne's turn, false otherwise
	 */
	public void setTurn(boolean turn) {
		myTurn = turn;
	}

	/**
	 * Determines whether there is stalemate. A game is in stalemate if and only if:
	 * 1) the turn player is not in check and the turn player has no legal moves; 2)
	 * threefold repitition has occurred; 3) fifty-move rule applies
	 * 
	 * @return true if there is a stalemate, false otherwise
	 */
	public boolean stalemate() {
		// TO BE IMPLEMENTED
	}

	// OTHER METHODS TO BE IMPLEMENTED

}
