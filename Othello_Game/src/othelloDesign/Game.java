package othelloDesign;

import java.util.Scanner;

public class Game {
	
	//private Board board;

    
    //public Game() {
    //    board = new Board(); // Initialize the Board instance
    //}
    
	public static void startNewGame(Scanner sc) {
		Player[] players = Player.createPlayers(sc);
	    Player player1 = players[0];
	    Player player2 = players[1];
		Board board = new Board(player1, player2); // Create an instance of Board

	    System.out.println("Select board configuration:");
	    System.out.println("1. Standard Othello Board");
	    System.out.println("2. Non-Standard Board");
	    System.out.print("Pick a number: ");
	    String boardTypeInput = sc.nextLine();

	    switch (boardTypeInput) {
	        case "1":
	            board.drawBoard(1);
	            System.out.println();
	            break;
	        case "2":
	            board.drawBoard(2);
	            System.out.println();
	            break;
	        default:
	            System.out.println("Invalid board type.");
	            return;
	    }

	    boolean isPlayer1Turn = true;

	    // Game loop
	    while (true) { // Adjust the condition as needed
	        Player currentPlayer = isPlayer1Turn ? player1 : player2;
	        char currentPlayerColor = currentPlayer.getColor();
	        
	        // Secondary menu for game actions
	        System.out.println();
	        System.out.println(currentPlayer.getName() + "'s turn.");
	        System.out.println();
	        System.out.println("Select what you want to do:");
	        System.out.println("1. MAKE A MOVE");
	        System.out.println("2. SAVE THE GAME");
	        System.out.println("3. CONCEDE THE GAME");
	        System.out.println("4. FORFEIT YOUR TURN");
	        System.out.print("Pick an option: ");
	        String moveInput = sc.nextLine();
	        System.out.println();

	        switch (moveInput) {
	            case "1":
	                // Call the play method to handle a move
	                try {
	                    board.play(currentPlayerColor); // Player makes a move
	                } catch (Exception e) {
	                    System.out.println("Error during the move: " + e.getMessage());
	                }
	                isPlayer1Turn = !isPlayer1Turn; // Switch turn after a move
	                break;
	            case "2":
	                // Save the game functionality
	                System.out.println("Saving game...");
	                board.save(); // Call the save method
	                System.out.println("Game saved successfully.");
	                break;
	            case "3":
	                // Concede the game functionality
	                Player losingPlayer = currentPlayer; // Player who is conceding
	                Player winningPlayer = (currentPlayer == player1) ? player2 : player1;
	                
	                System.out.println(losingPlayer.getName() + " has conceded the game. They have lost.");
	                System.out.println(winningPlayer.getName() + " has won.");

	                // Optionally, print the final score
	                board.printScore();
	                System.out.println();
	                System.out.println("Thanks for playing!");
	                // Terminate the game
	                System.exit(0);

	                return;

	            case "4":
	                board.takeTurn(); // Forfeit the turn
	                isPlayer1Turn = !isPlayer1Turn; // Switch turn after forfeiting
	                break;
	            default:
	                System.out.println("Invalid option!");
	                break;
	        }

	        // Check if the game is over
	        if (board.isGameOver()) {
	            System.out.println("Game Over!");
	            board.printScore(); // Print final score
	            break; // Exit the game loop
	        }
	    }
	}
	
}
