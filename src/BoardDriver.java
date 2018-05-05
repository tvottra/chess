import java.util.ArrayList;
public class BoardDriver {

    public static void main(String[] args) {
        Board gameBoard = new Board();
        int row = 6;
        int col = 3;
        System.out.println(gameBoard.getTile(row, col));
        Piece piece = gameBoard.getTile(row, col).getPiece();
        System.out.println("Here are the hotspots:");
        ArrayList <Position> hotspots = gameBoard.getHotspots(piece);
        for (Position pos : hotspots) {
            System.out.println(pos);
        }

    }

}
