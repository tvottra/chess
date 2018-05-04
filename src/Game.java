
/**
 * Class that represents a chess game containing Players and a Board
 * 
 * @author Andrew Le
 *
 */
import java.util.Scanner;

/**
 * 
 * @author Andrew
 *
 */
public class Game {
	private Player playerOne; // white
	private Player playerTwo; // black
	private Board gameBoard;
	private boolean draw;
	private boolean stalemate;
	private boolean isPlayerOneTurn; // true if it is player 1's turn, false otherwise

	/**
	 * Constructor to initialize the players, gameBoard, and game state
	 * 
	 * @param name1
	 *            - playerOne's name
	 * @param name2
	 *            - playerTwo's name
	 */
	public Game(String name1, String name2) {
		playerOne = new Player(name1, 1);
		playerTwo = new Player(name2, 2);
		gameBoard = new Board();
		draw = false;
		stalemate = false;
		isPlayerOneTurn = true;
		playGame();
	}

	/**
	 * Begins the chess game. The game ends when 1) a player has been checkmated, 2)
	 * both players have agreed to a draw, 3) a player resigns, or 4) there is a
	 * stalemate. Calls helper methods to display prompts and play the game
	 */
	public void playGame() {
		while (!gameIsOver()) {
			// Player 1's turn
			while (isPlayerOneTurn) {
				startPlayerTurn(playerOne, playerTwo);
				setTurn(false);
			} // End Player 1's turn
			if (gameIsOver()) {
				break;
			} else {
				// Player 2's turn
				while (!isPlayerOneTurn) {
					startPlayerTurn(playerTwo, playerOne);
					setTurn(true);
				} // End Player 2's turn
			}
		} // End game
		displayEndgame();
	}

	/**
	 * Begins a player's turn; a player has 3 possible moves each turn: 1) move a
	 * piece, 2) resign, or 3) request a draw
	 * 
	 * @param pl
	 *            - the turn player
	 * @param other
	 *            - the opposing player
	 */
	private void startPlayerTurn(Player pl, Player other) {
		Scanner sc = new Scanner(System.in);
		System.out.println("It is " + pl.getName() + "'s turn. Select one option:");
		System.out.println("(1) Select a piece to move");
		System.out.println("(2) Resign");
		System.out.println("(3) Request draw");
		int playerChoice = sc.nextInt();
		switch (playerChoice) {
		case 1:
			continueTurn(pl);
			break;
		case 2:
			pl.setResign(true);
			break;
		case 3:
			System.out.println(
					"Does Player " + pl.getNumber() + " accept Player " + other.getNumber() + "'s draw offer?");
			System.out.println("(1) Accept draw");
			System.out.println("(2) Decline draw");
			int drawChoice = sc.nextInt();
			if (drawChoice == 1) {
				setDraw(true);
				System.out.println(
						"Player " + pl.getNumber() + " has accepted Player " + other.getNumber() + "'s draw offer.");
			} else {
				setDraw(false);
				System.out.println(
						"Player " + pl.getNumber() + " has declined Player " + other.getNumber() + "'s draw offer.");
			}
			break;
		}
	}

	/**
	 * Selects the piece and destination for the player
	 * 
	 * @param pl
	 *            - the turn player
	 */
	private void continueTurn(Player pl) {
		Scanner sc = new Scanner(System.in);
		// Choose a tile on the gameBoard
		Position fromPos = choosePiece();
		// Check whether there is a piece of correct color at the chosen tile
		while (!isValidPiece(fromPos.getRow(), fromPos.getColumn(), pl.getNumber() - 1)) {
			System.out.println("Please enter valid coordinates:");
			fromPos = choosePiece();
		}
		// Choose a tile to move to
		Position toPos = chooseDestination();
		// Check whether the Tile position is in bounds
		while (!isWithinBounds(toPos.getRow(), toPos.getColumn())) {
			System.out.println("Please enter valid coordinates:");
			toPos = chooseDestination();
		}
		// Check whether the supposed move is legal
		while (!isLegalMove(fromPos, toPos)) {
			System.out.println("Move to " + toPos.getRow() + ", " + toPos.getColumn()
					+ " is not legal. Please choose another destination.");
			toPos = chooseDestination();
		}
		// Move Player's piece
		movePiece(fromPos, toPos);
		// Provide feedback to user
		int fromRow = fromPos.getRow();
		int fromCol = fromPos.getColumn();
		System.out.println(
				gameBoard.getTile(fromRow, fromCol).getPiece().getName() + " at " + fromPos + " has moved to " + toPos);

	}

	/**
	 * Checks the endgame state and prints the appropriate message
	 */
	private void displayEndgame() {
		if (playerOne.isCheckMated()) {
			System.out.println(playerOne + "has been checkmated. " + playerTwo + "wins!");
		}
		if (playerTwo.isCheckMated()) {
			System.out.println(playerTwo + "has been checkmated. " + playerOne + "wins!");
		}
		if (draw) {
			System.out.println("Both players have agreed to a draw.");
		}
		if (playerOne.isResigned()) {
			System.out.println(playerOne + " has resigned. " + playerTwo + " wins!");
		}
		if (playerTwo.isResigned()) {
			System.out.println(playerTwo + " has resigned. " + playerOne + " wins!");
		}
		if (stalemate) {
			System.out.println("Stalemate: neither player wins.");
		}
	}

	/**
	 * Checks to see if at least 1 of the conditions that would end the game is
	 * present
	 * 
	 * @return true if the game is over, false otherwise
	 */
	private boolean gameIsOver() {
		if (playerOne.isCheckMated() || playerTwo.isCheckMated() || draw || playerOne.isResigned()
				|| playerTwo.isResigned() || stalemate) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Prompts the user for the row and column indexes of the Piece to be moved
	 * 
	 * @return a Position object with Piece's current location
	 */
	private Position choosePiece() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the row coordinate of the piece: ");
		int pieceRow = sc.nextInt();
		System.out.print("Enter the column coordinate of the piece: ");
		int pieceCol = sc.nextInt();
		System.out.println();
		Position fromPos = new Position(pieceRow, pieceCol);
		return fromPos;
	}

	/**
	 * First checks whether the given row and column indexes are in bounds, then
	 * checks whether there is a Piece of the correct color at the specified row and
	 * column indexes
	 * 
	 * @param row
	 *            - the row index
	 * @param col
	 *            - the column index
	 * @param color
	 *            - 0 for white, 1 for black
	 * @return true if there is a Piece of the correct color at the row and column
	 *         indexes, false otherwise
	 */
	private boolean isValidPiece(int row, int col, int color) {
		if (isWithinBounds(row, col) && gameBoard.getTile(row, col).getPiece() != null) {
			// Check for player 1
			if (color == 0) {
				if (gameBoard.getTile(row, col).getPiece().getColor() == 0) {
					return true;
				} else {
					return false;
				}
				// Check for player 2
			} else {
				if (gameBoard.getTile(row, col).getPiece().getColor() == 1) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}

	/**
	 * Prompts the user for the row and column indexes of the Tile to which a Piece
	 * will be moved
	 * 
	 * @return a Position object with the destination's coordinates
	 */
	private Position chooseDestination() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose a tile to move to: ");
		System.out.print("Enter the row coordinates of the tile: ");
		int tileRow = sc.nextInt();
		System.out.print("Enter the column coordinates of the tile: ");
		int tileCol = sc.nextInt();
		Position toPos = new Position(tileRow, tileCol);
		return toPos;
	}

	/**
	 * Check whether the suggested row and column indexes are within the bounds of
	 * the gameBoard size
	 *
	 * @return true if the row and position are within bounds, false otherwise
	 */
	private boolean isWithinBounds(int row, int col) {
		return (row >= 0 && col >= 0) && (row < gameBoard.getSize() && col < gameBoard.getSize());
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
		isPlayerOneTurn = turn;
	}

	/**
	 * Mutator method that updates stalemate
	 * 
	 * @param stale
	 *            - true if there is a stalemate, false otherwise
	 */
	public void setStalemate(boolean stale) {
		stalemate = stale;
	}

	/**
	 * Calls Board class to determine whether the Piece at its current position can
	 * legally move to the given position; a Piece can legally move to a given
	 * Position if upon moving, the King is not checked by any other Piece
	 * 
	 * @param fromPos
	 *            - the Piece's current position
	 * 
	 * @param toPos
	 *            - the Position to which the Piece will be moved
	 * @return true if the move is legal, false otherwise
	 */
	public boolean isLegalMove(Position fromPos, Position toPos) {
		return gameBoard.isLegalMove(fromPos, toPos);
	}

	/**
	 * Calls the Board class's move method and moves a Piece at a Tile to the given
	 * Position
	 * 
	 * @param fromPos
	 *            - the Piece's current position
	 * @param toPos
	 *            - the Position to which the Piece will be moved
	 * @return true if the Piece was successfully moved, false otherwise
	 */
	public boolean movePiece(Position fromPos, Position toPos) {
		if (isLegalMove(fromPos, toPos)) {
			gameBoard.movePiece(fromPos, toPos);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines whether there is stalemate. A game is in stalemate if and only if:
	 * 1) the turn player is not in check and the turn player has no legal moves; 2)
	 * threefold repitition has occurred; 3) fifty-move rule applies
	 * 
	 * @return true if there is a stalemate, false otherwise
	 */
	private boolean stalemate() {
		// TO BE IMPLEMENTED
		return false;
	}

	public void update() {
		gameBoard.updateHotspots();
	}

}
