import java.util.ArrayList;

/**
 * Class that represents the chess board
 * 
 * @author Andrew Le
 *
 */
public class Board {
	Tile[][] board;
	private final int SIZE = 8;

	/**
	 * Default constructor that initializes the chess board according to the rules
	 * of chess
	 */
	public Board() {
		board = new Tile[8][8];
		setUpWhitePieces();
		setUpBlackPieces();
		setUpRestOfBoard();
	}

	/**
	 * Sets up all of the white pieces
	 */
	private void setUpWhitePieces() {
		// Set up white pawns
		for (int c = 0; c < SIZE; c++) {
			Position pos = new Position(6, c);
			Piece pawn = new Pawn( 0, pos);
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
		Piece knight2 = new Knight( 0, new Position(7, 6));
		board[7][6] = new Tile(knight2, true, false);

		// Set up white bishops
		Piece bishop1 = new Bishop(0, new Position(7, 2));
		board[7][2] = new Tile(bishop1, true, false);
		Piece bishop2 = new Bishop( 0, new Position(7, 5));
		board[7][3] = new Tile(bishop2, true, false);

		// Set up white queen and king
		Piece queen = new Queen(0, new Position(7, 3));
		board[7][3] = new Tile(queen, true, false);
		Piece king = new King(0, new Position(7, 4));
		board[7][4] = new Tile(king, true, false);
	}

	/**
	 * Sets up all of the black pieces
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
		Piece rook2 = new Rook( 1, new Position(0, 7));
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
		Piece king = new King( 1, new Position(0, 4));
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
	 * @param row
	 *            - the row index of the Tile
	 * @param col
	 *            - the column index of the Tile
	 * @return the Tile at board[row][col]
	 */
	public Tile getTile(int row, int col) {
		return board[row][col];
	}

	/**
	 * Called each time after a Piece is moved, looping through all of the Tiles and
	 * updating the white and black control for each Tile
	 */
	public void updateHotSpots() {
		// TO BE IMPLEMENTED
	}

	/**
	 * Attempts to move a Piece on a Tile to the given Position
	 * 
	 * @param fromPos
	 *            - the Piece's current position
	 * @param toPos
	 *            - the Position to which the Piece will be moved
	 */
	public void moveMyPiece(Position fromPos, Position toPos) {
		// TO BE IMPLEMENTED
	}

	/**
	 * toString method
	 * 
	 * @return the contents each Tile
	 */
	public String toString() {
		String output = "";
		for (Tile[] arr : board) {
			for (Tile tile : arr) {
				output += tile.toString() + "\n";
			}
		}
		return output;
	}

	public ArrayList<Position> getHotspots(Piece piece){
	    ArrayList<Position> foc = piece.getRangeOfMovement();
	    ArrayList<Position> hotspots = new ArrayList<Position>();

		if(piece.getName().equals("Bishop")) {
		    int branch1end = 0;
		    int branch2end = 0;
		    int branch3end = 0;
		    int branch4end = foc.size();
		    for(int i = 1; i < foc.size(); i++) {
		        if(piece.getPosition().equals(foc.get(i))) {
		            if(branch1end == 0) {
		                branch1end = i;
                    } else if(branch2end == 0) {
		                branch2end = i;
                    } else if(branch3end == 0) {
		                branch3end = i;
                    }
                }
            }
            for(int i = 1; i < branch1end; i++) {
		        Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
		        if(currentTile != null) {
		            hotspots.add(foc.get(i));
		            break;
                } else {
		            hotspots.add(foc.get(i));
                }
            }
            for(int i = branch1end + 1; i < branch2end; i++) {
                Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
				if(currentTile != null) {
					hotspots.add(foc.get(i));
					break;
				} else {
					hotspots.add(foc.get(i));
				}
            }
            for(int i = branch2end + 1; i < branch3end; i++) {
                Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
				if(currentTile != null) {
					hotspots.add(foc.get(i));
					break;
				} else {
					hotspots.add(foc.get(i));
				}
            }
            for(int i = branch3end + 1; i < branch4end; i++) {
                Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
				if(currentTile != null) {
					hotspots.add(foc.get(i));
					break;
				} else {
					hotspots.add(foc.get(i));
				}
            }
            return hotspots;
        }

        if(piece.getName().equals("King") || piece.getName().equals("Knight")) {
            for(int i = 0; i < foc.size(); i++) {
                hotspots.add(foc.get(i));
            }
		}

        if(piece.getName().equals("Pawn")) {
		    Tile currentTile = board[foc.get(1).getRow()][foc.get(1).getColumn()];
		    boolean blocked = true;
		    if(currentTile != null) {
		        hotspots.add(foc.get(1));
            } else {
                hotspots.add(foc.get(1));
                blocked = false;
            }
		    if(foc.size() == 3 && !blocked) {
		        hotspots.add(foc.get(2));
            }
            Position left = new Position(foc.get(1).getRow() + 1, foc.get(1).getColumn() - 1);
            Position right = new Position(foc.get(1).getRow() + 1, foc.get(1).getColumn() + 1);
            if(left.isWithinBounds()) {
                hotspots.add(left);
            }
            if(right.isWithinBounds()) {
                hotspots.add(right);
            }
            return hotspots;
        }

        if(piece.getName().equals("Queen")) {
		    int hbranch1end = 0;
		    int hbranch2end = 0;
		    int hbranch3end = 0;
		    int hbranch4end = 0;
		    int dbranch1end = 0;
		    int dbranch2end = 0;
		    int dbranch3end = 0;
		    int dbranch4end = foc.size();

		    for(int i = 0; i < foc.size(); i++) {
                if(piece.getPosition().equals(foc.get(i))) {
                    if(hbranch1end == 0) {
                        hbranch1end = i;
                    } else if(hbranch2end == 0) {
                        hbranch2end = i;
                    } else if(hbranch3end == 0) {
                        hbranch3end = i;
                    } else if(hbranch4end == 0) {
                        hbranch4end = i;
                    } else if(dbranch1end == 0) {
                        dbranch1end = i;
                    } else if(dbranch2end == 0) {
                        dbranch2end = i;
                    } else if(dbranch3end == 0) {
                        dbranch3end = i;
                    }
                }
            }

            for(int i = 0; i < hbranch1end; i++) {
                Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
                if(currentTile != null) {
                   hotspots.add(foc.get(i));
                   break;
                } else {
                    hotspots.add(foc.get(i));
                }
            }
            for(int i = hbranch1end + 1; i < hbranch2end; i++) {
				Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
				if(currentTile != null) {
					hotspots.add(foc.get(i));
					break;
				} else {
					hotspots.add(foc.get(i));
				}
            }
            for(int i = hbranch2end + 1; i < hbranch3end; i++) {
				Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
				if(currentTile != null) {
					hotspots.add(foc.get(i));
					break;
				} else {
					hotspots.add(foc.get(i));
				}
            }
            for(int i = hbranch3end + 1; i < hbranch4end; i++) {
				Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
				if(currentTile != null) {
					hotspots.add(foc.get(i));
					break;
				} else {
					hotspots.add(foc.get(i));
				}
            }
            for(int i = hbranch4end + 1; i < dbranch1end; i++) {
				Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
				if(currentTile != null) {
					hotspots.add(foc.get(i));
					break;
				} else {
					hotspots.add(foc.get(i));
				}
            }
            for(int i = dbranch1end + 1; i < dbranch2end; i++) {
				Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
				if(currentTile != null) {
					hotspots.add(foc.get(i));
					break;
				} else {
					hotspots.add(foc.get(i));
				}
            }
            for(int i = dbranch2end + 1; i < dbranch3end; i++) {
				Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
				if(currentTile != null) {
					hotspots.add(foc.get(i));
					break;
				} else {
					hotspots.add(foc.get(i));
				}
            }
            for(int i = hbranch3end + 1; i < hbranch4end; i++) {
				Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
				if(currentTile != null) {
					hotspots.add(foc.get(i));
					break;
				} else {
					hotspots.add(foc.get(i));
				}
            }
            return hotspots;
        }

        if(piece.getName().equals("Rook")) {
            int hbranch1end = 0;
            int hbranch2end = 0;
            int hbranch3end = 0;
            int hbranch4end = foc.size();
            for(int i = 0; i < foc.size(); i++) {
                if(piece.getPosition().equals(foc.get(i))) {
                    if(hbranch1end == 0) {
                        hbranch1end = i;
                    } else if(hbranch2end == 0) {
                        hbranch2end = i;
                    } else if(hbranch3end == 0) {
                        hbranch3end = i;
                    } else if(hbranch4end == 0) {
                        hbranch4end = i;
                    }
                }
            }
            for(int i = 0; i < hbranch1end; i++) {
				Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
				if(currentTile != null) {
					hotspots.add(foc.get(i));
					break;
				} else {
					hotspots.add(foc.get(i));
				}
            }
            for(int i = hbranch1end + 1; i < hbranch2end; i++) {
				Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
				if(currentTile != null) {
					hotspots.add(foc.get(i));
					break;
				} else {
					hotspots.add(foc.get(i));
				}
            }
            for(int i = hbranch2end + 1; i < hbranch3end; i++) {
				Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
				if(currentTile != null) {
					hotspots.add(foc.get(i));
					break;
				} else {
					hotspots.add(foc.get(i));
				}
            }
            for(int i = hbranch3end + 1; i < hbranch4end; i++) {
				Tile currentTile = board[foc.get(i).getRow()][foc.get(i).getColumn()];
				if(currentTile != null) {
					hotspots.add(foc.get(i));
					break;
				} else {
					hotspots.add(foc.get(i));
				}
            }

            return hotspots;
        }

        return hotspots;
    }

    public int getSIZE() {
		return SIZE;
	}

}
