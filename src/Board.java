import java.util.ArrayList;

/**
 * Class that represents the chess board
 *
 * @author Andrew Le
 * @author Brian Qiu
 * @author Tommy Tran
 */
public class Board {
	Tile[][] board;
	private final int SIZE = 8;

	/**
	 * Default constructor that initializes the chess board with all of the white
	 * pieces, black pieces and the rest of the tiles; establishes the white and
	 * black hot spots
	 */
	public Board() {
		board = new Tile[8][8];
		setUpWhitePieces();
		setUpBlackPieces();
		setUpRestOfBoard();
		// NullPointerException when updateHotSpots is called
		// updateHotSpots();
	}

	/**
	 * Sets up all of the white pieces on the bottom two rows of the board
	 */
	private void setUpWhitePieces() {
		// Set up white pawns
		for (int c = 0; c < SIZE; c++) {
			Position pos = new Position(6, c);
			Piece pawn = new Pawn(0, pos);
			board[6][c] = new Tile(pawn, true, false);
		}
		// Set up white rooks
		Piece rook1 = new Rook(0, new Position(7, 0));
		board[7][0] = new Tile(rook1, true, false);
		Piece rook2 = new Rook(0, new Position(7, 7));
		board[7][7] = new Tile(rook2, true, false);

		// Set up white knights
		Piece knight1 = new Knight(0, new Position(7, 1));
		board[7][1] = new Tile(knight1, true, false);
		Piece knight2 = new Knight(0, new Position(7, 6));
		board[7][6] = new Tile(knight2, true, false);

		// Set up white bishops
		Piece bishop1 = new Bishop(0, new Position(7, 2));
		board[7][2] = new Tile(bishop1, true, false);
		Piece bishop2 = new Bishop(0, new Position(7, 5));
		board[7][5] = new Tile(bishop2, true, false);

		// Set up white queen and king
		Piece queen = new Queen(0, new Position(7, 3));
		board[7][3] = new Tile(queen, true, false);
		Piece king = new King(0, new Position(7, 4));
		board[7][4] = new Tile(king, true, false);
	}

	/**
	 * Sets up all of the black pieces on the upper two rows of the board
	 */
	private void setUpBlackPieces() {
		// Set up black pawns
		for (int c = 0; c < SIZE; c++) {
			Position pos = new Position(1, c);
			Piece pawn = new Pawn(1, pos);
			board[1][c] = new Tile(pawn, false, true);
		}

		// Set up black rooks
		Piece rook1 = new Rook(1, new Position(0, 0));
		board[0][0] = new Tile(rook1, false, true);
		Piece rook2 = new Rook(1, new Position(0, 7));
		board[0][7] = new Tile(rook2, false, true);

		// Set up black knights
		Piece knight1 = new Knight(1, new Position(0, 1));
		board[0][1] = new Tile(knight1, false, true);
		Piece knight2 = new Knight(1, new Position(0, 6));
		board[0][6] = new Tile(knight2, false, true);

		// Set up black bishops
		Piece bishop1 = new Bishop(1, new Position(0, 2));
		board[0][2] = new Tile(bishop1, false, true);
		Piece bishop2 = new Bishop(1, new Position(0, 5));
		board[0][5] = new Tile(bishop2, false, true);

		// Set up black queen and king
		Piece queen = new Queen(1, new Position(0, 3));
		board[0][3] = new Tile(queen, false, true);
		Piece king = new King(1, new Position(0, 4));
		board[0][4] = new Tile(king, false, true);
	}

	/**
	 * Initializes all unoccupied Tiles, along with white/black control of each Tile
	 */
	private void setUpRestOfBoard() {
		// General traversal of Tiles in rows 3 and 4
		for (int r = 3; r < 5; r++) {
			for (int c = 0; c < SIZE; c++) {
				board[r][c] = new Tile(null, false, false);
			}
		}
		// Traversal of row 2
		for (int c = 0; c < SIZE; c++) {
			board[2][c] = new Tile(null, false, true);
		}
		// Traversal of row 5
		for (int c = 0; c < SIZE; c++) {
			board[5][c] = new Tile(null, true, false);
		}

	}

	/**
	 * Accessor method to get a tile at the specified location
	 *
	 * @param row - the row index of the Tile
	 * @param col - the column index of the Tile
	 * @return the Tile at board[row][col]
	 */
	public Tile getTile(int row, int col) {
		return board[row][col];
	}

	/**
	 * Accessor method to get a tile at the specified location
	 *
	 * @param pos - the position of the Tile
	 * @return the Tile at board[row][col]
	 */
	public Tile getTile(Position pos) {
		return board[pos.getRow()][pos.getColumn()];
	}

	/**
	 * Accessor method to return the size of one side of the board
	 *
	 * @return the size of the board
	 */
	public int getSize() {
		return SIZE;
	}

	/**
	 * Accessor method to get this board
	 *
	 * @return the board
	 */
	public Tile[][] getBoard() {
		return board;
	}

	/**
	 * toString method to print the Board
	 *
	 * @return the contents of each Tile as they would appear on a 2D board
	 */
	public String toString() {
		String output = "";
		boolean first = true;
		int count = 0;
		output += "   00 01 02 03 04 05 06 07\n";
		for (Tile[] arr : board) {
			for (Tile tile : arr) {
				if (first) {
					output += "0" + count + " ";
					++count;
					first = false;
				}
				output += tile.toString() + " ";
			}
			output += "\n";
			first = true;
		}
		return output;
	}

	/**
	 * Moves a Piece at fromPos to toPos on the board without checking for legality.
	 *
	 * @param fromPos - the Piece's current position
	 * @param toPos   - the Position to which the Piece will be moved
	 * @return true if there was a Piece at fromPos on the board; false otherwise.
	 */
	public boolean movePiece(Position fromPos, Position toPos) {

		int fromRow = fromPos.getRow();
		int fromCol = fromPos.getColumn();
		Piece pieceToMove = Piece.createPiece(board[fromRow][fromCol].getPiece(),
				board[fromRow][fromCol].getPiece().hasMoved());
		if (pieceToMove == null) {
			return false;
		}
		int toRow = toPos.getRow();
		int toCol = toPos.getColumn();
		board[toRow][toCol].setPiece(pieceToMove);
		board[fromRow][fromCol].setPiece(null);
		board[toRow][toCol].getPiece().setPosition(new Position(toPos));
		board[toRow][toCol].getPiece().setHasMoved(true);
		return true;
	}

	/**
	 * Determines whether the King at the given Position may castle
	 *
	 * @param fromPos - the King's current Position
	 * @param toPos   - the King's Position upon castling
	 * @return true if castling is possible, false otherwise
	 */
	public boolean castleAble(Position fromPos, Position toPos) {
		Piece king = board[fromPos.getRow()][fromPos.getColumn()].getPiece();
		Position toPosCheck;
		Position rookToPos;
		int direction = toPos.getColumn() - fromPos.getColumn(); // right if positive, left if negative
		Piece rook;

		if (king.getColor() == 0) {
			if (direction < 0) {
				rook = board[7][0].getPiece();
				toPosCheck = new Position(7, 2);
				rookToPos = new Position(7, 3);
			} else {
				rook = board[7][7].getPiece();
				toPosCheck = new Position(7, 6);
				rookToPos = new Position(7, 5);
			}
		} else {
			if (direction < 0) {
				rook = board[0][0].getPiece();
				toPosCheck = new Position(0, 2);
				rookToPos = new Position(0, 3);
			} else {
				rook = board[0][7].getPiece();
				toPosCheck = new Position(0, 6);
				rookToPos = new Position(0, 5);
			}
		}

		if (!toPos.equals(toPosCheck)) {
			return false;
		}

		if (king.getName().equals("King") && !king.hasMoved() && rook.getName().equals("Rook") && !rook.hasMoved()) {
			int kFromRow = fromPos.getRow();
			int kFromCol = fromPos.getColumn();
			int kToRow = toPos.getRow();
			int kToCol = toPos.getColumn();

			int rFromRow = rook.getPosition().getRow();
			int rFromCol = rook.getPosition().getColumn();
			int rToRow = rookToPos.getRow();
			int rToCol = rookToPos.getColumn();

			if (direction < 0) {
				for (int col = king.getPosition().getColumn() - 1; col > 0; col--) {
					if (board[king.getPosition().getRow()][col].hasPiece()) {
						return false;
					}
				}
				board[kToRow][kToCol].setPiece(king);
				board[kFromRow][kFromCol].setPiece(null);
				board[kToRow][kToCol].getPiece().setPosition(toPos);

				board[rToRow][rToCol].setPiece(rook);
				board[rFromRow][rFromCol].setPiece(null);
				board[rToRow][rToCol].getPiece().setPosition(rookToPos);

				return true;
			} else {
				for (int col = king.getPosition().getColumn() + 1; col < 7; col++) {
					if (board[king.getPosition().getRow()][col].hasPiece()) {
						return false;
					}
				}
				board[kToRow][kToCol].setPiece(king);
				board[kFromRow][kFromCol].setPiece(null);
				board[kToRow][kToCol].getPiece().setPosition(toPos);

				board[rToRow][rToCol].setPiece(rook);
				board[rFromRow][rFromCol].setPiece(null);
				board[rToRow][rToCol].getPiece().setPosition(rookToPos);

				return true;
			}
		} else {
			return false;
		}
	}


	/**
	 * Checks whether moving a Piece from its current Position to a given Position
	 * is legal.
	 *
	 * @param fromPos - the Piece's current Position
	 * @param toPos   - the Position of the Piece's proposed destination
	 * @param aBoard  - the given 2D array of Tiles
	 * @return true if the move is legal, false otherwise
	 */
	public boolean isLegalMove(Position fromPos, Position toPos, Tile[][] aBoard) {
		int fromRow = fromPos.getRow();
		int fromCol = fromPos.getColumn();

		if (!fromPos.isWithinBounds() || !toPos.isWithinBounds()|| !aBoard[fromRow][fromCol].hasPiece()) {
			return false;
		}

		Piece pieceToMove = Piece.createPiece(board[fromRow][fromCol].getPiece(),
				board[fromRow][fromCol].getPiece().hasMoved());

		if (pieceToMove == null) {
			System.out.println("Something went wrong. No Piece at fromPos."); // Debugging

			return false;
		}
		int toRow = toPos.getRow();
		int toCol = toPos.getColumn();
		if (pieceToMove.getName().equals("Pawn")) {
			if (!isWithinPawnRangeOfMovement(pieceToMove, toPos, aBoard)) {
				//System.out.println("Illegal: Not within Pawn ROM"); Debugging
				return false;
			}
		} else {
			if (!isWithinHotSpots(pieceToMove, toPos, board) || (board[toRow][toCol].hasPiece()
					&& board[toRow][toCol].getPiece().isSameColorAs(board[fromRow][fromCol].getPiece()))) {

//				// For debugging purposes
//				if (!isWithinHotSpots(pieceToMove, toPos, board)) {
//					System.out.println("Illegal: proposed move not within actual range of movement!");
//				}
				if ((board[toRow][toCol].hasPiece()
						&& board[toRow][toCol].getPiece().isSameColorAs(board[fromRow][fromCol].getPiece()))) {
					// System.out.println("Illegal: you can't capture your own piece!");
				}
				return false;
			}
		}

		// Create a copy of the real board to determine whether the move creates a check
		Tile[][] copy = Tile.cloneTile2DArray(board); //If incorrect hasMoved(), then manually code creation of Pieces in Tile.cloneTile2DArray later

		// Perform the move on the copy of the board
		copy[toRow][toCol].setPiece(pieceToMove);
		copy[fromRow][fromCol].setPiece(null);
		// Find the hotSpots and see whether the move would result in a check
		int myColor = pieceToMove.getColor();
		if (isKingChecked(myColor, copy)) {
			// System.out.println("Shouldn't be able to move");
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Checks whether the given Piece's proposed destination is within the Piece's
	 * hotSpots
	 *
	 * @param piece  - the given Piece
	 * @param toPos  - the given destination Position
	 * @param aBoard - the given 2D array of Tiles
	 * @return true if toPos is within the Piece's hotSpots, false otherwise
	 */
	private boolean isWithinHotSpots(Piece piece, Position toPos, Tile[][] aBoard) {
		ArrayList<Position> myHotSpots = getHotSpots(piece, aBoard);


		if (myHotSpots == null) {
			return false;
		}
		for (Position pos : myHotSpots) {
			if (toPos.equals(pos)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Gets all the hotSpots controlled by this Piece; a hotspot is defined as any
	 * Position at which a Piece could perform a capture; calls the appropriate
	 * helper method to get the hotSpots, depending on the identity of the piece
	 *
	 * @param piece  - the given Piece
	 * @param aBoard - the given 2D array of Tiles
	 * @return all the Positions currently checked by this Piece
	 */
	public ArrayList<Position> getHotSpots(Piece piece, Tile[][] aBoard) {
		if (piece.getName().equals("Pawn")) {
			return getPawnHotSpots(piece, aBoard);
		}
		if (piece.getName().equals("Knight")) {
			return getKnightHotSpots(piece, aBoard);
		}
		if (piece.getName().equals("Bishop")) {
			return getBishopHotSpots(piece, aBoard);
		}
		if (piece.getName().equals("Rook")) {
			return getRookHotSpots(piece, aBoard);
		}
		if (piece.getName().equals("Queen")) {
			return getQueenHotSpots(piece, aBoard);
		}
		if (piece.getName().equals("King")) {
			return getKingHotSpots(piece, aBoard);
		}
		return null;

	}

	/**
	 * Returns all of white's checked hotSpots for the given board
	 *
	 * @param aBoard - the given board
	 * @return all of the hotSpots checked by white pieces
	 */
	private ArrayList<Position> getWhiteHotSpots(Tile[][] aBoard) {
		ArrayList<Position> wHotSpots = new ArrayList<Position>();
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {

				if (aBoard[row][col].hasPiece() && aBoard[row][col].getPiece().getColor() == 0) {

					ArrayList<Position> myHotSpots = getHotSpots(aBoard[row][col].getPiece(), aBoard);
					for (Position pos : myHotSpots) {
						wHotSpots.add(pos);
					}
				}
			}
		}
		return wHotSpots;
	}

	/**
	 * Returns all of black's checked hotSpots for the given board
	 *
	 * @param aBoard - the given board
	 * @return all of the hotSpots checked by black pieces
	 */
	private ArrayList<Position> getBlackHotSpots(Tile[][] aBoard) {
		ArrayList<Position> bHotSpots = new ArrayList<Position>();
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (aBoard[row][col].hasPiece() && aBoard[row][col].getPiece().getColor() == 1) {
					ArrayList<Position> myHotSpots = getHotSpots(aBoard[row][col].getPiece(), aBoard);
					for (Position pos : myHotSpots) {
						bHotSpots.add(pos);
					}
				}
			}
		}

		return bHotSpots;
	}

	/**
	 * Returns the hotSpots for the given Pawn
	 *
	 * @param pawn   - the given Pawn
	 * @param aBoard - the given 2D array of Tiles
	 * @return the hotSpots for the given "Pawn"
	 */
	private ArrayList<Position> getPawnHotSpots(Piece pawn, Tile[][] aBoard) {
		ArrayList<Position> hotSpots = new ArrayList<Position>();
		Position currentPos = pawn.getPosition();
		int r;

		if (pawn.getColor() == 0) {
			r = -1;
		} else {
			r = 1;

		}

		Position leftDiag = new Position(currentPos.getRow() + r, currentPos.getColumn() - 1);
		Position rightDiag = new Position(currentPos.getRow() + r, currentPos.getColumn() + 1);
		if (leftDiag.isWithinBounds()) {
			hotSpots.add(leftDiag);

		}
		if (rightDiag.isWithinBounds()) {
			hotSpots.add(rightDiag);
		}

		return hotSpots;
	}

	/**
	 * Returns the given Pawn's range of movement, taking into consideration
	 * obstruction on the Board. The Pawn's range of movement may include forward
	 * tiles, diagonal tiles, both, or none, depending on the current board
	 *
	 * @param pawn   - the given Pawn
	 * @param aBoard - the given 2D array of Tiles
	 * @return the Pawn's real range of movement
	 */
	public ArrayList<Position> getPawnRangeOfMovement(Piece pawn, Tile[][] aBoard) {
		ArrayList<Position> rom = new ArrayList<Position>();
		// get forward tiles (if any)
		ArrayList<Position> list = pawn.getRangeOfMovement();
		for (int i = 0; i < list.size(); i++) {
			Tile currentTile = getTile(list.get(i));
			if (currentTile.hasPiece()) {
				break;
			} else {
				if (pawn.hasMoved() && Math.abs(list.get(i).getRow() - pawn.getPosition().getRow()) == 2) {
					break;
				} else {
					rom.add(list.get(i));
				}
			}
		}
		// get diagonal tiles (if any)
		ArrayList<Position> hotSpots = getPawnHotSpots(pawn, aBoard);
		for (int i = 0; i < hotSpots.size(); i++) {
			Tile currentTile = getTile(hotSpots.get(i));
			if (currentTile.hasPiece() && !currentTile.getPiece().isSameColorAs(pawn)) {
				rom.add(hotSpots.get(i));
			}
		}
		return rom;
	}

	/**
	 * Checks whether the given Pawn's proposed destination is within the given
	 * Pawn's true range of movement
	 *
	 * @param pawn   pawn
	 * @param toPos  - the given Position
	 * @param aBoard - the given 2D array of Tiles
	 * @return true if toPos is within the Pawn's range of mvoement, false otherwise
	 */
	public boolean isWithinPawnRangeOfMovement(Piece pawn, Position toPos, Tile[][] aBoard) {
		ArrayList<Position> myRom = getPawnRangeOfMovement(pawn, aBoard);
		//System.out.println(pawn.hasMoved()); Debugging
		for (Position pos : myRom) {
			//System.out.println(pos); Debugging
			if (toPos.equals(pos)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the hotSpots for the given Knight
	 *
	 * @param knight - the given Knight
	 * @param aBoard - the given 2D array of Tiles
	 * @return the hotSpots for the given "Knight"
	 */
	private ArrayList<Position> getKnightHotSpots(Piece knight, Tile[][] aBoard) {
		ArrayList<Position> rom = knight.getRangeOfMovement();
		ArrayList<Position> hotSpots = new ArrayList<Position>();
		for (int i = 0; i < rom.size(); i++) {
			hotSpots.add(rom.get(i));
		}
		return hotSpots;
	}

	/**
	 * Returns the hotSpots for the given Bishop
	 *
	 * @param bishop - the given Bishop
	 * @param aBoard - the given 2D array of Tiles
	 * @return the hotSpots for the given "Bishop"
	 */
	private ArrayList<Position> getBishopHotSpots(Piece bishop, Tile[][] aBoard) {
		ArrayList<Position> rom = bishop.getRangeOfMovement();
		ArrayList<Position> hotSpots = new ArrayList<Position>();
		// A branch is one of the 4 diagonals for the Bishop
		int branch1end = 0;
		int branch2end = 0;
		int branch3end = 0;
		int branch4end = rom.size();
		for (int i = 1; i < rom.size(); i++) {
			if (bishop.getPosition().equals(rom.get(i))) {
				if (branch1end == 0) {
					branch1end = i;
				} else if (branch2end == 0) {
					branch2end = i;
				} else if (branch3end == 0) {
					branch3end = i;
				}
			}
		}
		for (int i = 1; i < branch1end; i++) {
			Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];

			if (currentTile.hasPiece()) {

				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));
			}
		}
		for (int i = branch1end + 1; i < branch2end; i++) {
			Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];

			if (currentTile.hasPiece()) {

				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));
			}
		}
		for (int i = branch2end + 1; i < branch3end; i++) {
			Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];

			if (currentTile.hasPiece()) {
				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));
			}
		}
		for (int i = branch3end + 1; i < branch4end; i++) {
			Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];
			if (currentTile.hasPiece()) {

				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));
			}
		}
		return hotSpots;
	}

	/**
	 * Returns the hotSpots for the given Rook on the given board
	 *
	 * @param rook   - the given Rook
	 * @param aBoard - the given 2D array of Tiles
	 * @return the hotSpots for the given "Rook"
	 */
	private ArrayList<Position> getRookHotSpots(Piece rook, Tile[][] aBoard) {
		ArrayList<Position> rom = rook.getRangeOfMovement();

		ArrayList<Position> hotSpots = new ArrayList<Position>();
		rom.add(0, new Position(-1, -1)); // add a "buffer" to prevent row 0 error
		int hbranch1end = 0; // tiles above rook on board
		int hbranch2end = 0; // tiles below rook on board
		int hbranch3end = 0; // tiles "left" of rook
		int hbranch4end = rom.size(); // tiles "right" of rook
		for (int i = 0; i < rom.size(); i++) {
			if (rook.getPosition().equals(rom.get(i))) {
				if (hbranch1end == 0) {
					hbranch1end = i;
				} else if (hbranch2end == 0) {
					hbranch2end = i;
				} else if (hbranch3end == 0) {
					hbranch3end = i;
				} else if (hbranch4end == 0) {
					hbranch4end = i;
				}
			}
		}

		/*
		 * Use for debugging System.out.println("branch 1 = " + hbranch1end);
		 * System.out.println("branch 2 = " + hbranch2end);
		 * System.out.println("branch 3 = " + hbranch3end);
		 * System.out.println("branch 4 = " + hbranch4end);
		 */
		// Look at vector above rook
		for (int i = 1; i < hbranch1end; i++) {
			Tile currentTile = aBoard[rom.get(i).getRow()][rom.get(i).getColumn()];
			if (currentTile.hasPiece()) {
				hotSpots.add(rom.get(i));
				break;
			} else {
				// System.out.println(rom.get(i));
				hotSpots.add(rom.get(i));
			}
		}
		// Look at vector below rook
		for (int i = hbranch1end + 1; i < hbranch2end; i++) {
			Tile currentTile = aBoard[rom.get(i).getRow()][rom.get(i).getColumn()];
			if (currentTile.hasPiece()) {
				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));
			}
		}
		// Look at vector left of rook
		for (int i = hbranch2end + 1; i < hbranch3end; i++) {
			Tile currentTile = aBoard[rom.get(i).getRow()][rom.get(i).getColumn()];
			if (currentTile.hasPiece()) {
				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));
			}
		}
		// Look at vector right of rook
		for (int i = hbranch3end + 1; i < hbranch4end; i++) {
			Tile currentTile = aBoard[rom.get(i).getRow()][rom.get(i).getColumn()];
			if (currentTile.hasPiece()) {
				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));
			}
		}

		return hotSpots;

	}

	/**
	 * Returns the hotSpots for the given Queen
	 *
	 * @param queen  - the given Queen
	 * @param aBoard - the given 2D array of Tiles
	 * @return the hotSpots for the given "Queen"
	 */
	private ArrayList<Position> getQueenHotSpots(Piece queen, Tile[][] aBoard) {
		ArrayList<Position> rom = queen.getRangeOfMovement();

		ArrayList<Position> hotSpots = new ArrayList<Position>();
		rom.add(0, new Position(-1, -1)); // add a buffer to prevent row 0 error
		int hbranch1end = 0; // the tiles above queen
		int hbranch2end = 0; // the tiles below queen
		int hbranch3end = 0; // the tiles left of queen
		int hbranch4end = 0; // the tiles right of queen
		int dbranch1end = 0; // upper right diagonal
		int dbranch2end = 0; // upper left diagonal
		int dbranch3end = 0; // lower left diagonal
		int dbranch4end = rom.size(); // lower right diagonal

		for (int i = 0; i < rom.size(); i++) {
			if (queen.getPosition().equals(rom.get(i))) {
				if (hbranch1end == 0) {
					hbranch1end = i;
					// System.out.println("hbranch1 = " + hbranch1end);
				} else if (hbranch2end == 0) {
					hbranch2end = i;
					// System.out.println("hbranch2 = " + hbranch2end);
				} else if (hbranch3end == 0) {
					hbranch3end = i;
					// System.out.println("hbranch3 = " + hbranch3end);
				} else if (hbranch4end == 0) {
					hbranch4end = i;
					// System.out.println("hbranch4 = " + hbranch4end);
				} else if (dbranch1end == 0) {
					dbranch1end = i;
					// System.out.println("dbranch1 = " + dbranch1end);
				} else if (dbranch2end == 0) {
					dbranch2end = i;
					// System.out.println("dbranch2 = " + dbranch2end);
				} else if (dbranch3end == 0) {
					dbranch3end = i;
					// System.out.println("dbranch3 = " + dbranch3end);
				}
			}
		}
		// System.out.println("dbranch4 = " + dbranch4end);
		for (int i = 1; i < hbranch1end; i++) {
			Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];

			if (currentTile.hasPiece()) {
				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));

			}
		}
		for (int i = hbranch1end + 1; i < hbranch2end; i++) {
			Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];

			if (currentTile.hasPiece()) {
				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));

			}
		}
		for (int i = hbranch2end + 1; i < hbranch3end; i++) {
			Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];

			if (currentTile.hasPiece()) {
				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));

			}
		}
		for (int i = hbranch3end + 1; i < hbranch4end; i++) {
			Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];

			if (currentTile.hasPiece()) {
				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));

			}
		}
		for (int i = hbranch4end + 1; i < dbranch1end; i++) {
			Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];

			if (currentTile.hasPiece()) {
				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));

			}
		}
		for (int i = dbranch1end + 1; i < dbranch2end; i++) {
			Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];

			if (currentTile.hasPiece()) {
				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));

			}
		}
		for (int i = dbranch2end + 1; i < dbranch3end; i++) {
			Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];

			if (currentTile.hasPiece()) {
				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));

			}
		}
		for (int i = dbranch3end + 1; i < dbranch4end; i++) {
			Tile currentTile = board[rom.get(i).getRow()][rom.get(i).getColumn()];

			if (currentTile.hasPiece()) {
				hotSpots.add(rom.get(i));
				break;
			} else {
				hotSpots.add(rom.get(i));
			}
		}

		return hotSpots;

	}

	/**
	 * Returns the hotSpots for the given King
	 *
	 * @param king   - the given King
	 * @param aBoard - the given 2D array of Tiles
	 * @return the hotSpots for the given "King"
	 */
	private ArrayList<Position> getKingHotSpots(Piece king, Tile[][] aBoard) {
		ArrayList<Position> rom = king.getRangeOfMovement();

		ArrayList<Position> hotSpots = new ArrayList<Position>();
		for (int i = 0; i < rom.size(); i++) {
			hotSpots.add(rom.get(i));
		}
		return hotSpots;

	}

	/**
	 * Determines whether a King of a given color is currently in check on the given
	 * board
	 *
	 * @param color  - 0 if white, 1 if black
	 * @param aBoard - @param aBoard - the given 2D array of Tiles
	 * @return true if the King is checked, false otherwise
	 */
	private boolean isKingChecked(int color, Tile[][] aBoard) {
		Position kingPos = findKingPosition(color, aBoard);
		if (color == 0) {
			ArrayList<Position> bHotSpots = getBlackHotSpots(aBoard);
			for (Position pos : bHotSpots) {
				if (kingPos.equals(pos)) {
					return true;
				}
			}
		} else {
			ArrayList<Position> wHotSpots = getWhiteHotSpots(aBoard);
			for (Position pos : wHotSpots) {
				if (kingPos.equals(pos)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Finds the Position of the King of a given color in the given board
	 *
	 * @param color  - 0 if white, 1 if black
	 * @param aBoard - the given 2D array of Tiles
	 * @return the Position of the King
	 */
	private Position findKingPosition(int color, Tile[][] aBoard) {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (aBoard[row][col].hasPiece() && aBoard[row][col].getPiece().getName().equals("King")
						&& aBoard[row][col].getPiece().getColor() == color) {
					return new Position(row, col);
				}
			}
		}
		return null;
	}

	/**
	 * Determines whether a king is checkmated; prints out message if king is
	 * checked
	 *
	 * @return 0 if the white king is checkmated, 1 if the black king is checkmated,
	 * -1 otherwise
	 */
	public int getWhoIsCheckmated() {
		if (isKingChecked(0, board)) {
			System.out.println("White King is in check!");
			if (!hasLegalMoveLeft(0, board)) {
				return 0;
			}
		}
		if (isKingChecked(1, board)) {
			System.out.println("Black King is in check!");
			if (!hasLegalMoveLeft(1, board)) {
				return 1;
			}
		}
		return -1;
	}

	/**
	 * Determines whether a player of the given color has any legal moves left
	 *
	 * @param color  - 0 if white, 1 if black
	 * @param aBoard - the given 2D array of Tiles
	 * @return true if the player has at least 1 legal move left, false otherwise
	 */
	private boolean hasLegalMoveLeft(int color, Tile[][] aBoard) {
		if (color == 0) {
			for (Tile[] arr : board) {
				for (Tile obj : arr) {
					Piece piece = obj.getPiece();
					if (obj.hasPiece() && piece.getColor() == 0) {
						if (performMoves(piece, board)) {
							return true;
						}
					}
				} // inner for loop end
			} // outer for loop end
		} else {
			for (Tile[] arr : board) {
				for (Tile obj : arr) {
					Piece piece = obj.getPiece();
					if (obj.hasPiece() && piece.getColor() == 1) {
						if (performMoves(piece, board)) {
							return true;
						}
					}
				} // inner for loop end
			} // outer for loop end
		}
		return false;
	}

	/**
	 * Determines whether at least one of the given piece's moves can be performed
	 * to avoid a check; calls isLegalMove
	 *
	 * @param piece  - the given piece
	 * @param aBoard - the given 2D array of Tiles
	 * @return true if at least one of the piece's moves can be performed, false
	 * otherwise
	 */
	private boolean performMoves(Piece piece, Tile[][] aBoard) {
		Position currentPosition = piece.getPosition();
		if (piece.getName().equals("Pawn")) {
			ArrayList<Position> moves = getPawnRangeOfMovement(piece, aBoard);
			for (Position toPos : moves) {
				if (isLegalMove(currentPosition, toPos, aBoard)) {
					return true;
				}
			}
		} else {
			ArrayList<Position> moves = getHotSpots(piece, aBoard);
			for (Position toPos : moves) {
				if (isLegalMove(currentPosition, toPos, aBoard)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Should be called each time after a Piece is moved, looping through all of the
	 * Tiles and updating the isWhiteHotSpot and isBlackHotSpot for each Tile
	 */
	public void updateHotSpots() {
		ArrayList<Position> checkedPos;

		// Look through each Piece's field of hotSpots
		for (Tile[] t1 : board) {
			for (Tile t2 : t1) {

				// If there is a Piece
				if (t2.getPiece() != null) {
					Piece myPiece = t2.getPiece();
					checkedPos = getHotSpots(myPiece, board);
					boolean isWhite = myPiece.getColor() == 0;
					// For each Position checked, update each corresponding Tile's isWhiteHotSpot
					// and isBlackHotSpot accordingly
					if (checkedPos != null) {

						for (Position pos : checkedPos) {
							if (pos.isWithinBounds()) {
								if (isWhite) {
									board[pos.getRow()][pos.getColumn()].setIsWhiteHotSpot(true);
								} else {
									board[pos.getRow()][pos.getColumn()].setIsBlackHotSpot(true);
								}
							}

						}
					}
				}
			}

		}
	}
}

