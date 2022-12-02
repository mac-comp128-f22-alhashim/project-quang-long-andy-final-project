import java.util.LinkedList;

/**
* Best move using minimax algorithm
*/

public class MiniMaxAI {
    
}
// A recursive tree
class minimaxNode {
    private Board data;
    private LinkedList<minimaxNode> children;
    private Integer score; // score is 1 if x wins, -1 if O wins, 0 if tie. 
    
    public minimaxNode(Board boardData) {
        data = boardData;
        if (data.getFilledCount()>=5) {
            if (data.checkWin()==0) {
                score = 1;
            }
            else if (data.checkWin()==1) {
                score = -1;
            }
            else if (data.getFilledCount() ==9)
                score = 0; // indicate a tie
        }
        else {
            this.score = null;
        }
    }

    public boolean isPlayer0() {
        return (data.getFilledCount() %2==0);
    }
    /**
     * Find score of a node if the score initiated is null
     * if filledCount is odd, it is O's turn to strike -> minimize
     * if filledCount is even, it is X's turn to strike -> maximize
     */
    public void findScore() {
        if (score == null) {
            if (isPlayer0()) {
                this.score = findMaximum();
            }
            if (!isPlayer0()) {
                this.score = findMinimum();
            }
        }
    }

    /**
     * Find maximum or minimum score of a linkedlist of children
     */
    public Integer findMaximum() {
        Integer max = this.children.getFirst().score;
        for (minimaxNode child : children) {
            if (child.score > max) max = score;
        }
        return max;
    }
    
    /**
     * Find minimum score of a linkedlist of children
     */
    public Integer findMinimum() {
        Integer min = this.children.getFirst().score;
        for (minimaxNode child : children) {
            if (child.score < min) min = score;
        }
        return min;
    }

    public void recurseMinimax(minimaxNode node) {
        if (node.score != null) {
            return;
        }
        

        node.findScore();

    }

}