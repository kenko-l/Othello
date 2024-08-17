package othelloDesign;

import java.util.Scanner;

public class Driver {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Othello.");
        System.out.println();
        System.out.println("1. Start a New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Quit");
        System.out.println();
        System.out.print("Pick an option: ");
        String input = sc.nextLine();

        switch (input) {
            case "1":
                Game.startNewGame(sc);
                break;
            case "2":
            	Board board = new Board();
                board.loadFromFile();
                break;
            case "3":
                System.out.println("Exiting the game.");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }

        sc.close(); // Close scanner
    }
}