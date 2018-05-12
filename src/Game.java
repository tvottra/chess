import java.util.Scanner;

/**
 * Class that represents a chess game containing Players and a Board
 *
 * @author Andrew Le
 * @author Tommy V. Tran
 * @author Brian Qiu
 * @author Arjun Agrawal
 */
public class Game {
	private Player whitePlayer; // white
	private Player blackPlayer; // black
	private Board gameBoard;
	private boolean isWhiteTurn; // true if it is white's turn, false otherwise
	private boolean draw;
	private boolean stalemate;
	Scanner sc = new Scanner(System.in);

	/**
	 * Constructor to initialize the players, gameBoard, and game state
	 *
	 * @param name1 - whitePlayer's name
	 * @param name2 - blackPlayer's name
	 */
	public Game(String name1, String name2) {
		whitePlayer = new Player(name1, 0);
		blackPlayer = new Player(name2, 1);
		gameBoard = new Board();
		isWhiteTurn = true;
		draw = false;
		stalemate = false;
		update();
	}

	/**
	 * Accessor method to get the player with the given color
	 *
	 * @param color - 0 for white, 1 for black
	 * @return white player if color = 0, black player if color = 1
	 */
	public Player getPlayer(int color) {
		if (color == 0) {
			return whitePlayer;
		} else {
			return blackPlayer;
		}
	}

	/**
	 * Accessor method to get the board - for testing only
	 *
	 * @return the board
	 */
	public Board getBoard() {
		return gameBoard;
	}

	/**
	 * Begins the chess game. The game ends when 1) a player has been checkmated, 2)
	 * both players have agreed to a draw, 3) a player resigns, or 4) there is a
	 * stalemate. Calls helper methods to display prompts and play the game
	 */
	public void playGame() {
		System.out.println("Would you like to play against the computer or another person?");
		System.out.println("(1) Computer");
		System.out.println("(2) Person");
		int playerChoice = sc.nextInt();
		if (playerChoice == 1) {
			blackPlayer = new AI(blackPlayer.getName(), 1, gameBoard);
		}
		while (!gameIsOver()) {
			// White's turn
			while (isWhiteTurn) {
				startPlayerTurn(whitePlayer, blackPlayer);
				isWhiteTurn = !isWhiteTurn;
				findCheckmate();
				if (stalemate(whitePlayer, blackPlayer)) {
					setStalemate(true);
				}
			} // End White's turn
			if (gameIsOver()) {
				break;
			} else {
				// Black's turn
				while (!isWhiteTurn) {
					if (playerChoice == 1) {
						startAIPlayerTurn((AI) blackPlayer, whitePlayer);
					} else {
						startPlayerTurn(blackPlayer, whitePlayer);
					}
					isWhiteTurn = !isWhiteTurn;
					findCheckmate();
					if (stalemate(blackPlayer, whitePlayer)) {
						setStalemate(true);
					}
				} // End Black's turn
			}
		} // End game
		displayEndgame();
	}

	/**
	 * Begins a player's turn; a player has 3 possible moves each turn: 1) move a
	 * piece, 2) resign, or 3) request a draw
	 *
	 * @param pl    - the turn player
	 * @param other - the opposing player
	 */
	private void startPlayerTurn(Player pl, Player other) {
		boolean endPrompt = false;
		while (!endPrompt) {

			System.out.println("It is " + pl.getName() + "'s turn. Select one option:");
			System.out.println("(1) Select a piece to move");
			System.out.println("(2) Resign");
			System.out.println("(3) Request draw");
			System.out.println("(4) Pass turn: for testing only");
			System.out.println("(5) View pieces I've captured");
			int playerChoice = sc.nextInt();
			switch (playerChoice) {
				case 1:
					endPrompt = true;
					System.out.println(gameBoard);
					continueTurn(pl);
					break;
				case 2:
					endPrompt = true;
					pl.setResign(true);
					break;
				case 3:
					System.out.println("Does " + other.getName() + " accept " + pl.getName() + "'s draw offer?");
					System.out.println("(1) Accept draw");
					System.out.println("(2) Decline draw");
					int drawChoice = sc.nextInt();
					if (drawChoice == 1) {
						endPrompt = true;
						setDraw(true);
						System.out.println(other.getName() + " has accepted " + pl.getName() + "'s draw offer.");
					} else {
						setDraw(false);
						System.out.println(other.getName() + " has declined " + pl.getName() + "'s draw offer.");
						isWhiteTurn = !isWhiteTurn; // make sure the turn player's turn is not skipped
					}
					break;
				case 4:
					// do nothing
					endPrompt = true;
					System.out.println(pl.getName() + " has passed");
					break;
				case 5:
					System.out.println(pl.getCapturedPieces());
					break;
				default:
					System.out.println("An invalid number was entered: please choose 1-4");
					break;
			}

		}
	}

	/**
	 * Starts a turn played by the AI. AI Never surrenders
	 *
	 * @param myAI  - the turn player
	 * @param other - the opposing player
	 */
	private void startAIPlayerTurn(AI myAI, Player other) {
		System.out.println(myAI.getName() + " is looking at the board.");
		continueAITurn(myAI);

	}

	/**
	 * Selects the piece and destination for the player
	 *
	 * @param pl - the turn player
	 */
	private void continueTurn(Player pl) {
		Scanner sc = new Scanner(System.in);
		// Choose a Piece to move and a destination
		Position fromPos = choosePiece();
		while (!isValidPiece(fromPos, pl.getNumber(), gameBoard)) {
			System.out.println("Please enter valid coordinates for the piece to be moved:");
			fromPos = choosePiece();
		}
		Position toPos = chooseDestination();
		while (!isWithinBounds(toPos.getRow(), toPos.getColumn())) {
			System.out.println("Please enter valid coordinates for the destination:");
			toPos = chooseDestination();
		}
		// Check whether the supposed move is legal
		String feedback = "";
		boolean castle = false;
		if (gameBoard.castleAble(fromPos, toPos, gameBoard.getBoard())) {
			castle = true;
			feedback = "King";
		}

		while (!castle && (!isValidPiece(fromPos, pl.getNumber(), gameBoard)
				|| !isWithinBounds(toPos.getRow(), toPos.getColumn())
				|| !gameBoard.isLegalMove(fromPos, toPos, gameBoard.getBoard())
				|| (gameBoard.getTile(fromPos).getPiece().getName().equals("KING && !castle")))) {
			if (!isValidPiece(fromPos, pl.getNumber(), gameBoard)) {
				System.out.println("You cannot move a piece at that position.");
			}
			if (!isWithinBounds(toPos.getRow(), toPos.getColumn())) {
				System.out.println("Destination is out of bounds.");
			}
			if (!gameBoard.isLegalMove(fromPos, toPos, gameBoard.getBoard())) {
				System.out.println("Move is illegal.");
			}

			if (gameBoard.getTile(fromPos).getPiece().getName().equals("King && !castle")) {
				System.out.println("Illegal: cannot castle");
			}

			System.out.println("Move to " + toPos + " is invalid. Please repeat the move process.");
			fromPos = choosePiece();
			toPos = chooseDestination();
		}

		if (!castle) {
			//System.out.println("!castle evaluated to true"); Debugging
			feedback = gameBoard.getTile(fromPos).getPiece().toString(); // contains the location of the Piece prior
			movePieceOnBoard(fromPos, toPos);
		}
		// postcondition: Piece has been moved from fromPos to toPos on the Board
		// Provide feedback to user
		System.out.println(feedback + " has moved to " + toPos + ".\n");
		// Account for promotion
		Piece currentPiece = gameBoard.getTile(toPos).getPiece();
		if (currentPiece.getName().equals("Pawn") && ((Pawn) (currentPiece)).isWaitingForPromotion()) {
			promotion(currentPiece);
		}
		update();
		System.out.println(gameBoard);
	}

	/**
	 * Selects the piece and destination for the player
	 *
	 * @param myAI - the turn player
	 */
	private void continueAITurn(AI myAI) {
		Vector bestMove = myAI.generateHighestPointValueMove();
		if (bestMove == null || bestMove.getFromPos() == null || bestMove.getToPos() == null) {
			blackPlayer.isResigned();
			return;
		}
		String feedback = gameBoard.getTile(bestMove.getFromPos()).getPiece().toString(); // contains the location of
		// the Piece prior
		movePieceOnBoard(bestMove.getFromPos(), bestMove.getToPos());
		System.out.println(feedback + " has moved to " + bestMove.getToPos() + "\n");
		// Account for promotion
		Piece currentPiece = gameBoard.getTile(bestMove.getToPos()).getPiece();
		if (currentPiece.getName().equals("Pawn") && ((Pawn) (currentPiece)).isWaitingForPromotion()) {
			promotion(currentPiece);
		}
		update();
		System.out.println(gameBoard);
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
	 * Prompts the user for the row and column indexes of the Tile to which a Piece
	 * will be moved
	 *
	 * @return a Position object with the destination's coordinates
	 */
	private Position chooseDestination() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose a tile to move to. ");
		System.out.print("Enter the row coordinates of the tile: ");
		int tileRow = sc.nextInt();
		System.out.print("Enter the column coordinates of the tile: ");
		int tileCol = sc.nextInt();
		Position toPos = new Position(tileRow, tileCol);
		System.out.println();
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
	 * First checks whether the given row and column indexes are in bounds, then
	 * checks whether there is a Piece of the correct color at the specified row and
	 * column indexes, then checks whether the player is selecting a piece of the
	 * appropriate color
	 *
	 * @param row   - the row index
	 * @param col   - the column index
	 * @param color - 0 for white, 1 for black
	 * @return true if there is a Piece of the correct color at the row and column
	 * indexes, false otherwise
	 */
	public static boolean isValidPiece(int row, int col, int color, Board board) {
		if (new Position(row, col).isWithinBounds() && board.getTile(row, col).hasPiece()) {

			return board.getTile(row, col).getPiece().getColor() == color;
		} else {
			return false;
		}
	}

	/**
	 * First checks whether the given row and column indexes are in bounds, then
	 * checks whether there is a Piece of the correct color at the specified row and
	 * column indexes
	 *
	 * @param pos   - Position of the board to check at
	 * @param color - 0 for white, 1 for black
	 * @param board to check at
	 * @return true if there is a Piece of the correct color at the row and column
	 * indexes, false otherwise
	 */
	public static boolean isValidPiece(Position pos, int color, Board board) {
		if (pos.isWithinBounds() && board.getTile(pos).hasPiece()) {
			return board.getTile(pos).getPiece().getColor() == color;
		} else {
			return false;
		}
	}

	/**
	 * Moves a Piece from one Tile to another on the board of Tiles without checking
	 * for legality; if a capture is made, adds the point value of the captured
	 * piece to the turn player's score.
	 *
	 * @param fromPos - the Piece's current position
	 * @param toPos   - the Position to which the Piece will be moved
	 */
	private void movePieceOnBoard(Position fromPos, Position toPos) {
		// If a capture is made, add points to the player's score
		if (gameBoard.getTile(toPos).hasPiece()) {
			Piece capturedPiece = gameBoard.getTile(toPos).getPiece();
			System.out.println(capturedPiece + " will be captured!");
			incrementScore(capturedPiece.getColor(), capturedPiece.getPointValue());

			//Add captured Piece to a maintained list of the capturing player

			if (capturedPiece.getColor() == 0) {
				blackPlayer.addCapturedPiece(capturedPiece);
			} else {
				whitePlayer.addCapturedPiece(capturedPiece);
			}
		}
		gameBoard.movePiece(fromPos, toPos);
	}

	/**
	 * Prompts the user to choose a non-Pawn, non-King Piece to replace the given
	 * Pawn. A white Pawn is promoted if it is on row 0; a black Pawn is promoted if
	 * it is on row 7. The method removes the promoted Pawn from the board and
	 * constructs the user's specified Piece at the current Position.
	 *
	 * @param promotedPawn - the given promoted Pawn
	 */
	private void promotion(Piece promotedPawn) {
		Scanner sc = new Scanner(System.in);
		int myColor = promotedPawn.getColor();
		Position currentPos = promotedPawn.getPosition();
		System.out.println(promotedPawn + " is awaiting promotion.");
		System.out.println("Choose a piece to replace " + promotedPawn + ":");
		System.out.println("(1) Promote to Knight");
		System.out.println("(2) Promote to Bishop");
		System.out.println("(3) Promote to Rook");
		System.out.println("(4) Promote to Queen");
		int promotionChoice = sc.nextInt();
		switch (promotionChoice) {
			case 1:
				gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(null);
				Piece knight = new Knight(myColor, currentPos);
				gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(knight);
				System.out.println("Promotion to Knight at " + currentPos);
				break;
			case 2:
				gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(null);
				Piece bishop = new Bishop(myColor, currentPos);
				gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(bishop);
				System.out.println("Promotion to Bishop at " + currentPos);
				break;
			case 3:
				gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(null);
				Piece rook = new Rook(myColor, currentPos);
				gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(rook);
				System.out.println("Promotion to Rook at " + currentPos);
				break;
			case 4:
				gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(null);
				Piece queen = new Queen(myColor, currentPos);
				gameBoard.getTile(currentPos.getRow(), currentPos.getColumn()).setPiece(queen);
				System.out.println("Promotion to Queen at " + currentPos);
				break;
			default:
				System.out.println(promotionChoice + " is not a valid number. Please choose 1-4");
				break;
		}
	}

	/**
	 * Increments the score of the player with the color OPPOSITE of the given color
	 * by the given value
	 *
	 * @param color - 0 if black, 1 if white
	 * @param val   - the value of the captured piece
	 */
	private void incrementScore(int color, int val) {
		if (color == 0) {
			blackPlayer.incrementScore(val); // add points to black
		} else {
			whitePlayer.incrementScore(val); // add points to white
		}
	}

	/**
	 * Determines whether a checkmate has occurred. If so, updates the checkmate
	 * status for a Player accordingly.
	 */
	private void findCheckmate() {
		int color = gameBoard.getWhoIsCheckmated();
		if (color == 0) {
			whitePlayer.setCheckMate(true);
		} else if (color == 1) {
			blackPlayer.setCheckMate(true);
		}

	}

	/**
	 * Determines whether there is stalemate following the given player's move. A
	 * game is in stalemate if and only if: 1) the turn player is not in check and
	 * the turn player has no legal moves; 2) checkmate is impossible for either
	 * player (if a player has only 1 Knight or only 1 Bishop left)
	 *
	 * @param pl    - the Player who just moved a piece
	 * @param other - the opposing Player
	 * @return true if there is a stalemate, false otherwise
	 */
	private boolean stalemate(Player pl, Player other) {
		int color = other.getNumber();
		if (!gameBoard.isKingChecked(color, gameBoard.getBoard())
				&& !gameBoard.hasLegalMoveLeft(color, gameBoard.getBoard())) {
			return true;
		}
		int numPawns = numPawnsLeft(other);
		int numKnights = numKnightsLeft(other);
		int numBishops = numBishopsLeft(other);
		int numRooks = numRooksLeft(other);
		int numQueens = numQueensLeft(other);
		if (numKnights <= 1 && numPawns == 0 && numBishops == 0 && numRooks == 0 && numQueens == 0) {
			return true;
		}
		if (numBishops <= 1 && numPawns == 0 && numKnights == 0 && numRooks == 0 && numQueens == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of Pawns left for the given Player
	 *
	 * @param pl - the given Player
	 * @return the number of Pawns that the given player has left
	 */
	private int numPawnsLeft(Player pl) {
		int count = 0;
		if (pl.getNumber() == 0) {
			for (Tile[] arr : gameBoard.getBoard()) {
				for (Tile obj : arr) {
					if (obj.hasPiece() && obj.getPiece().getName().equals("Pawn") && obj.getPiece().getColor() == 0) {
						count++;
					}
				}
			}
		} else {
			for (Tile[] arr : gameBoard.getBoard()) {
				for (Tile obj : arr) {
					if (obj.hasPiece() && obj.getPiece().getName().equals("Pawn") && obj.getPiece().getColor() == 1) {
						count++;
					}
				}
			}
		}
		return count;
	}

	/**
	 * Returns the number of Knights left for the given Player
	 *
	 * @param pl - the given Player
	 * @return the number of Knights that the given Player has left
	 */
	private int numKnightsLeft(Player pl) {
		int count = 0;
		if (pl.getNumber() == 0) {
			for (Tile[] arr : gameBoard.getBoard()) {
				for (Tile obj : arr) {
					if (obj.hasPiece() && obj.getPiece().getName().equals("Knight") && obj.getPiece().getColor() == 0) {
						count++;
					}
				}
			}
		} else {
			for (Tile[] arr : gameBoard.getBoard()) {
				for (Tile obj : arr) {
					if (obj.hasPiece() && obj.getPiece().getName().equals("Knight") && obj.getPiece().getColor() == 1) {
						count++;
					}
				}
			}
		}
		return count;
	}

	/**
	 * Returns the number of Bishops left for the given Player
	 *
	 * @param pl - the given Player
	 * @return the number of Bishops that the given Player has left
	 */
	private int numBishopsLeft(Player pl) {
		int count = 0;
		if (pl.getNumber() == 0) {
			for (Tile[] arr : gameBoard.getBoard()) {
				for (Tile obj : arr) {
					if (obj.hasPiece() && obj.getPiece().getName().equals("Bishop") && obj.getPiece().getColor() == 0) {
						count++;
					}
				}
			}
		} else {
			for (Tile[] arr : gameBoard.getBoard()) {
				for (Tile obj : arr) {
					if (obj.hasPiece() && obj.getPiece().getName().equals("Bishop") && obj.getPiece().getColor() == 1) {
						count++;
					}
				}
			}
		}
		return count;
	}

	/**
	 * Returns the number of Rooks left for the given Player
	 *
	 * @param pl - the given Player
	 * @return the number of Rooks that the given Player has left
	 */
	private int numRooksLeft(Player pl) {
		int count = 0;
		if (pl.getNumber() == 0) {
			for (Tile[] arr : gameBoard.getBoard()) {
				for (Tile obj : arr) {
					if (obj.hasPiece() && obj.getPiece().getName().equals("Rook") && obj.getPiece().getColor() == 0) {
						count++;
					}
				}
			}
		} else {
			for (Tile[] arr : gameBoard.getBoard()) {
				for (Tile obj : arr) {
					if (obj.hasPiece() && obj.getPiece().getName().equals("Rook") && obj.getPiece().getColor() == 1) {
						count++;
					}
				}
			}
		}
		return count;
	}

	/**
	 * Returns the number of Bishops left for the given Player
	 *
	 * @param pl - the given Player
	 * @return the number of Bishops that the given Player has left
	 */
	private int numQueensLeft(Player pl) {
		int count = 0;
		if (pl.getNumber() == 0) {
			for (Tile[] arr : gameBoard.getBoard()) {
				for (Tile obj : arr) {
					if (obj.hasPiece() && obj.getPiece().getName().equals("Queen") && obj.getPiece().getColor() == 0) {
						count++;
					}
				}
			}
		} else {
			for (Tile[] arr : gameBoard.getBoard()) {
				for (Tile obj : arr) {
					if (obj.hasPiece() && obj.getPiece().getName().equals("Queen") && obj.getPiece().getColor() == 1) {
						count++;
					}
				}
			}
		}
		return count;
	}

	/**
	 * Checks to see if at least 1 of the conditions that would end the game is
	 * present
	 *
	 * @return true if the game is over, false otherwise
	 */
	private boolean gameIsOver() {
		if (whitePlayer.isCheckMated() || blackPlayer.isCheckMated() || draw || whitePlayer.isResigned()
				|| blackPlayer.isResigned() || stalemate) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks the endgame state and prints the appropriate message
	 */
	private void displayEndgame() {
		if (whitePlayer.isCheckMated()) {
			System.out.println(whitePlayer + " has been checkmated. " + blackPlayer + "wins!");
		}
		if (blackPlayer.isCheckMated()) {
			System.out.println(blackPlayer + " has been checkmated. " + whitePlayer + "wins!");
		}
		if (draw) {
			System.out.println("Both players have agreed to a draw.");
		}
		if (whitePlayer.isResigned()) {
			System.out.println(whitePlayer + " has resigned. " + blackPlayer + " wins!");
		}
		if (blackPlayer.isResigned()) {
			System.out.println(blackPlayer + " has resigned. " + whitePlayer + " wins!");
		}
		if (stalemate) {
			System.out.println("Stalemate: neither player wins.");
		}
	}

	/**
	 * Mutator method that updates the draw status of the game
	 *
	 * @param draw - true if there is a draw, false otherwise
	 */
	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	/**
	 * Mutator method that updates the turn
	 *
	 * @param turn - true if it is whitePlayer's turn, false otherwise
	 */
	public void setTurn(boolean turn) {
		isWhiteTurn = turn;
	}

	/**
	 * Mutator method that updates stalemate
	 *
	 * @param stale - true if there is a stalemate, false otherwise
	 */
	public void setStalemate(boolean stale) {
		stalemate = stale;
	}

	/**
	 * Update the hotSpots
	 */
	public void update() {
		gameBoard.updateHotSpots(gameBoard.getBoard());
	}

}
