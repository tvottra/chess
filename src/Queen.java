
public class Queen extends Piece {

	public Queen(int color, Position pos) {
		super(color, pos, 9);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isLegal(Position move, int[][] board) {
		//check if move is off of board or back in same place, if yes, return false
		if((move.getCol() > 7 || move.getCol() < 0) || (move.getRow() > 7 || move.getRow() < 0)){
			return false;
		}
		//check if move is in the same column or row, if yes,
		//check if move is behind another piece, if yes, return false, else truw
		if(move.getCol() == super.getPosition().getCol()){
			int rowOfOther = -1;
			//if move is behind another piece, move is illegal
			if(move.getRow() > super.getPosition().getRow()){
				for(int i = super.getPosition().getRow(); i < board.length; i++){
					if(board[i][move.getCol()] != null){
						rowOfOther = i;
						break;
					}
				}
				if(move.getRow() > rowOfOther){
					return false;
				}	
			}
			else{
				for(int i = super.getPosition().getRow(); i >= 0; i--){
					if(board[i][move.getCol()] != null){
						rowOfOther = i;
						break;
					}
				}
				if(move.getRow() < rowOfOther){
					return false;
				}
			}
			//if move is in same place as piece of the same color, move is illegal
			if(move.getRow() == rowOfOther && board[rowOfOther][move.getCol()].getColor == super.getColor()){
					return false;
			}
			return true;
		}
		if(move.getRow() == super.getPosition().getRow()){
			int colOfOther = -1;
			//if move is behind another piece, move is illegal
			if(move.getCol() > super.getPosition().getCol()){
				for(int i = super.getPosition().getCol(); i < board.length; i++){
					if(board[move.getRow()][i] != null){
						colOfOther = i;
						break;
					}
				}
				if(move.getCol() > colOfOther){
					return false;
				}	
			}
			else{
				for(int i = super.getPosition().getCol(); i >= 0; i--){
					if(board[move.getRow()][i] != null){
						rowOfOther = i;
						break;
					}
				}
				if(move.getCol() < colOfOther){
					return false;
				}
			}
			//if move is on same space as piece of same color, move is illegal
			if(move.getCol() == colOfOther && board[move.getRow()][colOfOther].getColor == super.getColor()){
					return false;
			}
			return true;
		}
		//check if move is diagonal to the queen; if yes, return true
		for(int i = 1; i < board.length; i++){
			if(move.getRow() == super.getPosition().getRow() + i && move.getCol() == super.getPosition().getCol() + i){
				return true;
			}
			if(move.getRow() == super.getPosition().getRow() - i && move.getCol() == super.getPosition().getCol() + i){
				return true;
			}
			if(move.getRow() == super.getPosition().getRow() + i && move.getCol() == super.getPosition().getCol() - i){
				return true;
			}
			if(move.getRow() == super.getPosition().getRow() - i && move.getCol() == super.getPosition().getCol() - i){
				return true;
			}
		}
		return false;
	}

}
