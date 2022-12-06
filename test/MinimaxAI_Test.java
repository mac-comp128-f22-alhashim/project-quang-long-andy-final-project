import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MinimaxAI_Test {
    
    public static void main(String[] args) {
        Board parent = new Board();
        parent.playerChoose(true, 1, 1);
        minimaxNode node = new minimaxNode(parent);
        minimaxNode child = new minimaxNode(parent);
        copy2(child, node);
        System.out.println(child.level);
        child.level++;
        System.out.println(child.level);
        System.out.println(node.level);
    }

    public static void boardCopy(Board dest, Board src) {
        dest = src;
    }

    public static minimaxNode nodeCopy(minimaxNode src) {
        return src;
    }

    public static void copy2(minimaxNode dest, minimaxNode src) {
        dest = src;
    }
    @Test
    public void testBaseCase() {
        Board board = new Board();
        board.playerChoose(true, 1, 1);
        board.playerChoose(false, 2, 2);
        board.playerChoose(true, 2, 1);
        board.playerChoose(false, 3, 1);
        board.getPrinted();
        // board.playerChoose(true, 1, 2);
        // board.playerChoose(false, i, j)
    }
}
