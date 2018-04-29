
public class Queen extends Piece {

	public Queen(int color, Position pos) {
		super(color, pos, 9);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isLegal(Position move, int[][] board) {
		//check if move is off of board or back in same place, if yes, return false
		if((move.getColumn() > 7 || move.getColumn() < 0) || (move.getRow() > 7 || move.getRow() < 0)){
			return false;
		}
		//check if move is in the same column or row, if yes,
		//check if move is behind another piece, if yes, return false, else truw
		if(move.getColumn() == super.getPosition().getColumn()){
			int rowOfOther = -1;
			//if move is behind another piece, move is illegal
			if(move.getRow() > super.getPosition().getRow()){
				for(int i = super.getPosition().getRow(); i < board.length; i++){
					if(board[i][move.getColumn()] != null){
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
					if(board[i][move.getColumn()] != null){
						rowOfOther = i;
						break;
					}
				}
				if(move.getRow() < rowOfOther){
					return false;
				}
			}
			//if move is in same place as piece of the same color, move is illegal
			if(move.getRow() == rowOfOther && board[rowOfOther][move.getColumn()].getColor() == super.getColor()){
					return false;
			}
			return true;
		}
		if(move.getRow() == super.getPosition().getRow()){
			int colOfOther = -1;
			//if move is behind another piece, move is illegal
			if(move.getColumn() > super.getPosition().getColumn()){
				for(int i = super.getPosition().getColumn(); i < board.length; i++){
					if(board[move.getRow()][i] != null){
						colOfOther = i;
						break;
					}
				}
				if(move.getColumn() > colOfOther){
					return false;
				}	
			}
			else{
				for(int i = super.getPosition().getColumn(); i >= 0; i--){
					if(board[move.getRow()][i] != null){
						rowOfOther = i;
						break;
					}
				}
				if(move.getColumn() < colOfOther){
					return false;
				}
			}
			//if move is on same space as piece of same color, move is illegal
			if(move.getColumn() == colOfOther && board[move.getRow()][colOfOther].getColor() == super.getColor()){
					return false;
			}
			return true;
		}
		//check if move is diagonal to the queen; if yes, return true
		//must check if move is behind another piece!!!!!!!!!!!!!!!!!!!!!!
		for(int i = 1; i < board.length; i++){
			if(move.getRow() == super.getPosition().getRow() + i && move.getColumn() == super.getPosition().getColumn() + i){
				return true;
			}
			if(move.getRow() == super.getPosition().getRow() - i && move.getColumn() == super.getPosition().getColumn() + i){
				return true;
			}
			if(move.getRow() == super.getPosition().getRow() + i && move.getColumn() == super.getPosition().getColumn() - i){
				return true;
			}
			if(move.getRow() == super.getPosition().getRow() - i && move.getColumn() == super.getPosition().getColumn() - i){
				return true;
			}
		}
		return false;
	}

}
