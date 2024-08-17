package othelloDesign;
import java.io.*;


public class Board {

    private String name;
    private Position[][] board;
    public static final int SIZE = 8;
    private Player player1;
    private Player player2;
    

    public Board(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Position[SIZE][SIZE];
        
    }
    // Default constructor
    public Board() {
        this.board = new Position[SIZE][SIZE];
        //initializeBoard();
    }


    // Constructor with two players and a start type
    public Board(Player p1, Player p2, int start) {
        this();
        // Initialize players and start type if needed
    }

    // Constructor with a filename parameter
    public Board(String filename) {
        this();
        this.name = filename;
     //  loadFromFile(filename);// Load the board from the file (implementation not shown)
    }
    
    // Method to return the piece at a specific position
    public char getPieceAt(int row, int col) {
        return board[row][col].getPiece(); // Assuming Position class has a getPiece() method
    }
    
    // Static method to load a board from a file
    public static Board load() {
        // Define the fixed filename
        String filename = "output.txt";

        // Create a new Board object using the fixed filename
        return new Board(filename);
    }
    
    public void loadFromFile() {
        String filename = "output.txt"; // Fixed filename
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            System.out.println("File opened successfully.");

            // Read player names and current player
            String player1Name = reader.readLine().trim();
            System.out.println("Player 1: " + player1Name);
            String player2Name = reader.readLine().trim();
            System.out.println("Player 2: " + player2Name);
            String currentPlayerLine = reader.readLine().trim();
            System.out.println("Current Player: " + currentPlayerLine);

            // Skip the column labels line
            String columnLabels = reader.readLine(); // Read and discard column labels
            System.out.println("Column Labels: " + columnLabels);

            String line;
            int row = 0;

            // Read and process each line of the file
            while ((line = reader.readLine()) != null && row < SIZE) {
                line = line.trim(); // Remove leading and trailing whitespace
                System.out.println("Processing line: " + line);

                if (!line.isEmpty()) {
                    // Process the board row
                    String[] tokens = line.split("\\s+");
                    for (int col = 0; col < SIZE; col++) {
                        if (col < tokens.length) {
                            char pieceChar = tokens[col].charAt(0);
                            Position position;

                            switch (pieceChar) {
                                case 'B':
                                    position = new Position(); // Default playable position
                                    position.setPiece(Position.BLACK);
                                    break;
                                case 'W':
                                    position = new Position(); // Default playable position
                                    position.setPiece(Position.WHITE);
                                    break;
                                case ' ':
                                    position = new UnplayablePosition(); // Unplayable position
                                    break;
                                default:
                                    position = new Position(); // Default playable position
                                    position.setPiece(Position.EMPTY);
                                    break;
                            }

                            board[row][col] = position; // Directly assign to board array
                        } else {
                            board[row][col] = new Position(); // Default to playable position if missing
                        }
                    }
                    row++; // Move to the next row
                }
            }

            // Check if the board was fully loaded
            if (row < SIZE) { // Check if all rows were loaded
                throw new IOException("File does not contain enough board data.");
            }

            System.out.println("Board loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading board from file: " + e.getMessage());
            // Optionally, you can reset the board to a default state
            // initializeBoard();
        }
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            // Write player names
            writer.write(player1.getName());
            writer.newLine();
            writer.write(player2.getName());
            writer.newLine();
            writer.write((currentPlayer == Position.BLACK ? player1.getName() : player2.getName()));
            writer.newLine();

            // Write column labels
            writer.write("  "); // Leading spaces for row labels
            for (char col = 'A'; col < 'A' + SIZE; col++) {
                writer.write(col + " ");
            }
            writer.newLine();

            // Write board state with row labels
            for (int i = 0; i < SIZE; i++) {
                writer.write((i + 1) + " "); // Row label
                for (int j = 0; j < SIZE; j++) {
                    char pieceChar = board[i][j].getPiece();
                    writer.write(pieceChar + " ");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }
    
    public void drawBoard(int boardType) {
        switch (boardType) {
            case 1:
                drawStandardBoard();
                break;
            case 2:
                drawNonStandardBoard();
                break;
            default:
                System.out.println("Invalid board type.");
                break;
        }
    }

    private void drawStandardBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Position(); // Initialize to playable
            }
        }
        // Set up the standard board pattern
        board[3][3].setPiece(Position.WHITE);
        board[3][4].setPiece(Position.BLACK);
        board[4][3].setPiece(Position.BLACK);
        board[4][4].setPiece(Position.WHITE);

        printBoard();
    }

    private void drawNonStandardBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Position(); // Initialize to playable
            }
        }

        // Set up the non-standard board pattern
        board[2][2].setPiece(Position.WHITE);
        board[2][3].setPiece(Position.WHITE);
        board[3][2].setPiece(Position.WHITE);
        board[3][3].setPiece(Position.WHITE);
        board[2][4].setPiece(Position.BLACK);
        board[2][5].setPiece(Position.BLACK);
        board[3][4].setPiece(Position.BLACK);
        board[3][5].setPiece(Position.BLACK);
        board[4][2].setPiece(Position.BLACK);
        board[4][3].setPiece(Position.BLACK);
        board[5][2].setPiece(Position.BLACK);
        board[5][3].setPiece(Position.BLACK);
        board[4][4].setPiece(Position.WHITE);
        board[4][5].setPiece(Position.WHITE);
        board[5][4].setPiece(Position.WHITE);
        board[5][5].setPiece(Position.WHITE);

        printBoard();
    }

    public void printBoard() {
        // Print horizontal axis (A-H)
        System.out.print("  "); // Space for the top-left corner
        for (char c = 'A'; c < 'A' + SIZE; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        // Print each row with vertical axis (1-8)
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " "); // Vertical axis number
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " "); // Uses Position's toString() method
            }
            System.out.println();
        }
        System.out.println();
    }

    // Getter and setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean isValidMove(int row, int col, char playerColor) {
        if (board[row][col].getPiece() != Position.EMPTY) {
            return false; // Position is already occupied
        }

        // Check all directions for possible flips
        for (int dRow = -1; dRow <= 1; dRow++) {
            for (int dCol = -1; dCol <= 1; dCol++) {
                if (dRow == 0 && dCol == 0) continue; // Skip the current position

                if (checkDirection(row, col, dRow, dCol, playerColor)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDirection(int row, int col, int dRow, int dCol, char playerColor) {
        int r = row + dRow;
        int c = col + dCol;
        boolean hasOpponentPiece = false;

        while (r >= 0 && r < SIZE && c >= 0 && c < SIZE) {
            char piece = board[r][c].getPiece();
            if (piece == Position.EMPTY) {
                return false;
            }
            if (piece == playerColor) {
                return hasOpponentPiece;
            }
            hasOpponentPiece = true;
            r += dRow;
            c += dCol;
        }
        return false;
    }
    
    public void makeMove(int row, int col, char playerColor) {
        board[row][col].setPiece(playerColor);

        for (int dRow = -1; dRow <= 1; dRow++) {
            for (int dCol = -1; dCol <= 1; dCol++) {
                if (dRow == 0 && dCol == 0) continue; // Skip the current position

                if (checkDirection(row, col, dRow, dCol, playerColor)) {
                    flipDirection(row, col, dRow, dCol, playerColor);
                }
            }
        }
    }

    private void flipDirection(int row, int col, int dRow, int dCol, char playerColor) {
        int r = row + dRow;
        int c = col + dCol;

        while (r >= 0 && r < SIZE && c >= 0 && c < SIZE && board[r][c].getPiece() != playerColor) {
            board[r][c].setPiece(playerColor);
            r += dRow;
            c += dCol;
        }
    }

    private char currentPlayer = Position.BLACK; // Starting with Black

    public void switchPlayer() {
        currentPlayer = (currentPlayer == Position.BLACK) ? Position.WHITE : Position.BLACK;
    }
   
    public boolean isGameOver() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isValidMove(i, j, Position.BLACK) || isValidMove(i, j, Position.WHITE)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printScore() {
        int blackCount = 0;
        int whiteCount = 0;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].getPiece() == Position.BLACK) {
                    blackCount++;
                } else if (board[i][j].getPiece() == Position.WHITE) {
                    whiteCount++;
                }
            }
        }

        System.out.println("Black: " + blackCount + "pts.");
        System.out.println("White: " + whiteCount + "pts.");
    }

    public void play(char playerColor) {
    	printBoard();
        printScore();

        String input = getPlayerInput();
        if (input == null) return; // Exit if input is null

        int row = parseRow(input);
        int col = parseColumn(input);

        if (isValidMove(row, col, playerColor)) {
            makeMove(row, col, playerColor);
            
        } else {
            System.out.println("Invalid move. Try again.");
        }

        printBoard();
        printScore();
    }
    private String getPlayerInput() {
        System.out.print("Enter your move (e.g., A1): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine().trim().toUpperCase();
        } catch (IOException e) {
            System.out.println("Error reading input. Please try again.");
            return null;
        }
        
    }

    private int parseRow(String input) {
        try {
            return Integer.parseInt(input.substring(1)) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid row number. Please enter a number between 1 and 8.");
            return -1;
        }
    }

    private int parseColumn(String input) {
        char column = input.charAt(0);
        return column - 'A';
    }

    public void takeTurn() {
        // Forfeit the current player's turn
        System.out.println("Turn forfeited. Switching to the next player.");
    }
 
}
