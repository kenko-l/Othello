package othelloDesign;

public class UnplayablePosition extends Position {

    public UnplayablePosition() {
        super();
        setPiece('*'); // Indicating unplayable position
    }

    @Override
    public boolean canPlay() {
        return false; // Positions of this type are not playable
    }
}
