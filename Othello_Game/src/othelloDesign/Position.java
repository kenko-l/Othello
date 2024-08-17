package othelloDesign;

public class Position {
    public static final char EMPTY = ' ';
    public static final char BLACK = 'B';
    public static final char WHITE = 'W';

    private char piece; // 'B' for black, 'W' for white, ' ' for empty

    public Position() {
        this.piece = EMPTY;
    }

    public void setPiece(char piece) {
        this.piece = piece;
    }

    public char getPiece() {
        return piece;
    }

    public boolean canPlay() {
        // Define logic to determine if a position can be played
        return piece == EMPTY; // Example: position is playable if it's empty
    }

    @Override
    public String toString() {
        return Character.toString(piece); // Converts the piece character to a string
    }
}



