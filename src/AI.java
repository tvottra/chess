import java.util.ArrayList;

/**
 * @author Tommy V. T.
 * <p>
 * "AI" that randomly scans through the board for valid moves and goes for the one with highest point value.
 */
public class AI extends Player {
	String name;
	Board boardIAnalyze;

	public AI(String name, int number, Board bd) {
		super(name, number);
		boardIAnalyze = bd;
	}


	public Vector generateHighestPointValueMove() {
		Tile[][] chessBoard = boardIAnalyze.getBoard(); //don't modify original board yet!
		//ArrayList<Vector> potentialMoves = new ArrayList<Vector>(); //use later maybe
		ArrayList<Vector> optimalPlayPerFromPos = new ArrayList<Vector>();

		Position fromPos;
		for (int r = 0; r < chessBoard.length; r++) {
			for (int c = 0; c < chessBoard[0].length; c++) {
				fromPos = new Position(r, c);
				if (Game.isValidPiece(fromPos, super.getNumber(), boardIAnalyze)) {
					//System.out.println("Found valid piece");
					Piece myPiece = Piece.createPiece(chessBoard[r][c].getPiece());
					ArrayList<Position> hotSpots = boardIAnalyze.getHotSpots(myPiece);                        //this points to the same Board as the main Board if the main Board is passed to AI's constructor

					if (hotSpots != null) {
						//System.out.println("AI hotSpots is not null");
						Position optimalToPos = null;
						boolean firstLegalToPos = true;

						int optimalToPosValue = 0;
						for (Position hSpot : hotSpots) {
							if (boardIAnalyze.isLegalMove(fromPos, hSpot)) {
								if (firstLegalToPos) {
									optimalToPos = hSpot;
									firstLegalToPos = false;
								}
								System.out.println("Legal move detected: " + new Vector(fromPos, hSpot));
								if (boardIAnalyze.getTile(hSpot).hasPiece() && boardIAnalyze.getTile(hSpot).getPiece().getPointValue() > optimalToPosValue) {
									optimalToPos = hSpot;
									optimalToPosValue = boardIAnalyze.getTile(hSpot).getPiece().getPointValue();
								}
							}
						}
						if(optimalToPos != null) {
							optimalPlayPerFromPos.add(new Vector(fromPos, optimalToPos));
						}

					}
				}
			}
		}
		//Scan through all the fromPos, optimalToPos vectors to find the absolute highest toPos
		Vector bestMove = optimalPlayPerFromPos.get(0);
		int bestMoveValue = 0;
		for (Vector move : optimalPlayPerFromPos) {
			if (boardIAnalyze.getTile(move.getToPos()).hasPiece() && boardIAnalyze.getTile(move.getToPos()).getPiece().getPointValue() > bestMoveValue) {
				bestMove = new Vector(move);
				bestMoveValue = boardIAnalyze.getTile(move.getToPos()).getPiece().getPointValue();
			}
		}

		//System.out.println("Best move: " + bestMove);
		return bestMove;
	}


}
