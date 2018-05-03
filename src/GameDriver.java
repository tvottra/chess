import java.util.Scanner;

/**
 * 
 * @author Andrew
 *
 */
public class GameDriver {

	/**
	 * 
	 * @param args
	 *            - not used
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to chess!");
		System.out.print("Please enter Player 1's name (white): ");
		String nameOne = sc.next();
		System.out.print("Please enter Player 2's name (black): ");
		String nameTwo = sc.next();
		Game startGame = new Game(nameOne, nameTwo);
	}

}
