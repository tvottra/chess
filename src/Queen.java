
public class Queen extends Piece {

	public Queen(int color, Position pos) {
		super(color, pos, 9);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isLegal(Position move, int[][] board) {
		//check if move is off of board, if yes, return false
		if((move.getCol() > 7 || move.getCol() < 0) && (move.getRow() > 7 || move.getRow() < 0)){
			return false;
		}
		//check if move is in the same column or row, if yes,
		//check if move is behind another piece, if yes, return false, else truw
		if(move.getCol() == super.pos.getCol()){
			int rowOfOther = -1;
			if(move.getRow() > super.pos.getRow(){
				for(int i = super.pos.getRow(); i < board.length; i++){
					if(board[i][move.getCol()] != null){
						rowOfOther = i;
						break;
					}
				}
				if((move.getRow()
			}
			else{
				for(int i = super.pos.getRow(); i >= 0; i--){
					if(board[i][move.getCol()] != null){
						rowOfOther = i;
						break;
					}
				}
			}
			return true;
		}
		if(move.getRow() == super.pos.getRow()){
			
			return true;
		}
		//check if move is diagonal to the queen; if yes, return true
		for(int i = 1; i < board.length; i++){
			if(move.getRow() == super.pos.getRow() + i && move.getCol() == super.pos.getCol() + i){
				return true;
			}
			if(move.getRow() == super.pos.getRow() - i && move.getCol() == super.pos.getCol() + i){
				return true;
			}
			if(move.getRow() == super.pos.getRow() + i && move.getCol() == super.pos.getCol() - i){
				return true;
			}
			if(move.getRow() == super.pos.getRow() - i && move.getCol() == super.pos.getCol() - i){
				return true;
			}
		}
		return false;
	}

}
