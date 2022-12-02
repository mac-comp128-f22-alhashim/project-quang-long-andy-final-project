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

    /**
     * Best move using minimax algorithm
     */
}

// A recursive tree
class minimaxNode {
    public Board data;
    public LinkedList<minimaxNode> children;
    public Integer score; // score is 1 if x wins, -1 if O wins, 0 if tie. 
    
    public minimaxNode(Board boardData) {
        data = boardData;
        if (data.getFilledCount()>=5) {
            if (data.checkWin()==0) {
                score = 1;
            }
            else if (data.checkWin()==1) {
                score = -1;
            }
            else score = 0; // indicate a tie
        }
    }

    public void findScore() {
        if (score == null) {
            for (minimaxNode child : children) {

            }
        }
    }

    public Integer findMaximum() {
        Integer max = 0;
        for (minimaxNode child : children) {
            if (child.score > max) max = score;
        }
        return max;
    }

    public Integer findMinimum() {
        Integer min = 0;
        for (minimaxNode child : children) {
            if (child.score < min) min = score;
        }
        return min;
    }
}




