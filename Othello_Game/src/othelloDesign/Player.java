package othelloDesign;

import java.util.Scanner;

public class Player {
    private String name;
    private char color; // 'B' for black, 'W' for white

    // Constructor
    public Player(String name, char color) {
        this.name = name;
        this.color = color;
    }

    // Default constructor
    public Player() {
        // Default values if needed
        this.name = "";
        this.color = ' ';
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for color
    public char getColor() {
        return color;
    }

    // Setter for color
    public void setColor(char color) {
        this.color = color;
    }

    // Static method to create players based on user input
    public static Player[] createPlayers(Scanner sc) {
        System.out.print("Please input player 1's name: ");
        String player1Name = sc.nextLine();
        System.out.print("Please input player 2's name: ");
        String player2Name = sc.nextLine();
        System.out.println();

        Player player1 = new Player(player1Name, 'B'); // Black by default
        Player player2 = new Player(player2Name, 'W'); // White by default

        System.out.println(player1Name + " is black.");
        System.out.println(player2Name + " is white.");

        return new Player[] { player1, player2 };
    }
}
