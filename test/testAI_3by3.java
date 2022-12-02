import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class testAI_3by3 {
    public static void main(String[] args) {
        Board board = new Board();
        board.playerChoose(true, 2, 2);
        board.getPrinted();
        AIPlayler ai = new AIPlayler();
        int[] nextMove = ai.move(board);
        board.playerChoose(false, nextMove[0], nextMove[1]);
        board.getPrinted();

        // AIPlayler ai = new AIPlayler(board);
        // int[] nextMove = ai.move(); 
        // assertEquals(new int[]{1,1}, nextMove);
    }
    @Test
    public void testBotFirstMove() {
        Board board = new Board();
        AIPlayler ai = new AIPlayler();

        // Turn 1 - human
        board.playerChoose(true, 2, 2);
        
        // Turn 2 - bot
        int[] nextMove = ai.move(board); 
        assertEquals(1, nextMove[0]);
        assertEquals(1, nextMove[1]);
        board.playerChoose(false, 1, 1);

        // Turn 3 - human
        board.playerChoose(true, 1, 2);
        nextMove = ai.move(board);
        assertEquals(1, nextMove[0]);
        assertEquals(3, nextMove[1]);
    }
}
