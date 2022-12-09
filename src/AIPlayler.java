import java.util.LinkedList;

public class AIPlayler {
    private int[][] simplePreferredMoves = {
        {1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
        {0, 1}, {1, 0}, {1, 2}, {2, 1} };
    public AIPlayler() {

    }
    /**
     * Totally bot move
     * @param board
     * @return
     */
    public int[] move(Board board) {
        for (int[] move : simplePreferredMoves) {
            if (board.getPosition(move[0], move[1]) == (board.getBlankMark())) {
                return move;
            }
        }
        return null;
    }

    
}






