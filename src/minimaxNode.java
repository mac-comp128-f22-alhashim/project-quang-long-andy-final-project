import java.util.ArrayList;
import java.util.LinkedList;

/**
* Best move using minimax algorithm
*/

// A recursive tree
public class minimaxNode {
    private Integer defaultDepth = 3; // How many move the AI thinks ahead
    public Integer level;
    public Board data;
    private LinkedList<minimaxNode> children;
    private Integer score; // score is 1 if x wins, -1 if O wins, 0 if tie. 
    private Integer[] bestMove; private Integer[] moveStriked;
    
    public minimaxNode(Board boardData) {
        level = 0;
        bestMove = new Integer[2];
        moveStriked = new Integer[2];
        data = boardData;
        this.children = new LinkedList<minimaxNode>();
    }


    public boolean isPlayer0() {
        return (data.getFilledCount() %2==0);
    }
    /**
     * Find score of a node if the score initiated is null
     * if filledCount is odd, it is O's turn to strike -> minimize
     * if filledCount is even, it is X's turn to strike -> maximize
     */
    // public void findScore() {
    //     if (score == null) {
    //         if (isPlayer0()) {
    //             this.score = findMaximum();
    //         }
    //         if (!isPlayer0()) {
    //             this.score = findMinimum();
    //         }
    //     }
    // }

    public void findScore() {
        findScore(this, defaultDepth);
    }

    public Integer[] bestMove() {
        findScore();
        return this.bestMove;
    }
    private void findScore(minimaxNode node,Integer depth) {
        // Base Case
        if (node.data.getFilledCount()>=5) {
            if (node.data.checkWin()==0) { // X wins
                score = 1; 
            }
            else if (node.data.checkWin()==1) { // O wins
                score = -1;
            }
            else if (node.data.getFilledCount() ==9) // A tie  
                score = 0; 
                
            return;
        }
        else if (level == depth) {
            score =0;
            return;
        }

        // Steps
        // Construct children
        Boolean isPlayer0 = node.isPlayer0();
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (node.data.getBoard()[i][j] == node.data.getBlankMark()) {
                    // spawn the children
                    minimaxNode childNode = new minimaxNode(data);
                    childNode.level = node.level+1;
                    childNode.data.playerChoose(isPlayer0, i+1, j+1); // friendly i and j
                    childNode.moveStriked[0] = i+1; // add friendly i and j to help capturing the best move
                    childNode.moveStriked[1] = j+1;
                    node.children.add(childNode);
                    findScore(childNode, defaultDepth);


                }
            }
        }
        if (isPlayer0) {
            node.findMaximum();
        }
        else node.findMinimum();
        
    }

    /**
     * Find maximum or minimum score of a linkedlist of children
     */
    public void findMaximum() {
        minimaxNode firstChild = this.children.getFirst();
        Integer max = firstChild.score;
        this.bestMove[0] = firstChild.moveStriked[0];
        this.bestMove[1] = firstChild.moveStriked[1];
        for (minimaxNode child : children) {
            if (child.score > max) {
                this.bestMove[0] = child.moveStriked[0];
                this.bestMove[1] = child.moveStriked[1];
                max = score;
            }
        }
        this.score = max;
    }
    
    /**
     * Find minimum score of a linkedlist of children
     */
    public void findMinimum() {
        minimaxNode firstChild = this.children.getFirst();
        Integer min = firstChild.score;
        this.bestMove[0] = firstChild.moveStriked[0];
        this.bestMove[1] = firstChild.moveStriked[1];
        for (minimaxNode child : children) {
            if (child.score < min) {
                this.bestMove[0] = child.moveStriked[0];
                this.bestMove[1] = child.moveStriked[1];
                min = score;
            }
        }
        this.score = min;
    }

    public static void boardCopy(Board dest, Board src) {
        dest = src;
    }

    public void minimaxNodeCopy(minimaxNode dest) {
        dest = this;
    }

    // public void recurseMinimax(minimaxNode node) {
    //     if (node.score != null) {
    //         return;
    //     }
        

    //     node.findScore();

    // }

}