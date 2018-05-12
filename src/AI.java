import java.util.ArrayList;

/**
 * @author Tommy V. Tran
 *
 * "AI" that randomly scans through the board for valid moves and goes for the one with highest point value.
 */
public class AI extends Player {
	String name;
	Board boardIAnalyze;

	/**
	 * Constructor to initialize the "AI"
	 * @param name - name of the AI
	 * @param number - player number (usually 1 for Black)
	 * @param bd - Board the AI is playing on
	 */
	public AI(String name, int number, Board bd) {
		super("AI " + name, number);
		boardIAnalyze = bd;
	}


	/**
	 * The AI reads in the Board it has access to and generates a vector of movement based on capturing the Piece with the highest point value. If highest point value
	 * is 0, then AI just chooses the first legal move.
	 * @return a vector of movement that yields the capture with the highest point value
	 */
	public Vector generateHighestPointValueMove() {
		Tile[][] chessBoard = boardIAnalyze.getBoard(); //don't modify original board yet!
		//ArrayList<Vector> potentialMoves = new ArrayList<Vector>(); //use later maybe
		ArrayList<Vector> optimalPlayPerFromPos = new ArrayList<Vector>();

		Position fromPos;
		for (int r = 0; r < chessBoard.length; r++) {
			for (int c = 0; c < chessBoard[0].length; c++) {
				fromPos = new Position(r, c);
				if (Game.isValidPiece(fromPos, super.getNumber(), boardIAnalyze)) {

					Piece myPiece = Piece.createPiece(chessBoard[r][c].getPiece(), chessBoard[r][c].getPiece().hasMoved());
					ArrayList<Position> hotSpots = boardIAnalyze.getHotSpots(myPiece, chessBoard);                        //this points to the same Board as the main Board if the main Board is passed to AI's constructor

					if (hotSpots != null) {
						//System.out.println("AI hotSpots is not null");
						Position optimalToPos = null;
						boolean firstLegalToPos = true;

						int optimalToPosValue = 0;
						for (Position hSpot : hotSpots) {
							if (boardIAnalyze.isLegalMove(fromPos, hSpot, chessBoard)) {
								if (firstLegalToPos) {
									optimalToPos = hSpot;
									firstLegalToPos = false;
								}

								if (boardIAnalyze.getTile(hSpot).hasPiece() && boardIAnalyze.getTile(hSpot).getPiece().getPointValue() > optimalToPosValue) {
									optimalToPos = hSpot;
									optimalToPosValue = boardIAnalyze.getTile(hSpot).getPiece().getPointValue();
								}
							}
						}
						if (optimalToPos != null) {
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
