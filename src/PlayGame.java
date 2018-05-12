import java.util.*;

public class PlayGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to chess!");
        System.out.print("Please enter Player 1's name (white): ");
        String nameOne = sc.nextLine();
        System.out.print("Please enter Player 2's name (black): ");
        String nameTwo = sc.nextLine();
        Game myGame = new Game(nameOne, nameTwo);
        myGame.playGame();


    }

}
