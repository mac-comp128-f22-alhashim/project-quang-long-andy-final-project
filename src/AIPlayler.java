import java.util.LinkedList;

public class AIPlayler {
    private int[][] simplePreferredMoves = {
        {2, 2}, {1, 1}, {1, 3}, {3, 1}, {3, 3},
        {1, 2}, {2, 1}, {2, 3}, {3, 2} };
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






