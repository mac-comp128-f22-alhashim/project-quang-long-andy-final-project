

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


import SimpleTTT.Board;
import SimpleTTT.MinimaxAI;

public class TestMinimax {
    public static void main(String[] args) {
        Board board = new Board();
        board.markPosition('X', 0, 1);
        board.markPosition('O',1,1);
        MinimaxAI ai = new MinimaxAI(true);
        Integer[] bestMoves = ai.getBestMoves(board);
        board.getPrinted();
        System.out.println(bestMoves[0]);
        System.out.println(bestMoves[1]);
        board.markPosition('X', bestMoves[0], bestMoves[1]);
        board.markPosition('O', 1, 2);
        bestMoves = ai.getBestMoves(board);
        board.getPrinted();
        System.out.println(bestMoves[0]);
        System.out.println(bestMoves[1]);
        
    }
    @Test
    // A minimax move on the board will not affect the board
    public void testMinimax() {
        Board board = new Board();
        board.markPosition('X', 1, 2);
        board.markPosition('O', 0, 0);
        assertEquals(0, board.getRecentMoveI());
        assertEquals(0, board.getRecentMoveJ());
        MinimaxAI ai = new MinimaxAI(true);
        board.getPrinted();
        System.out.println(board.getRecentMoveI());
        System.out.println(board.getRecentMoveJ());
        assertEquals(0, board.getRecentMoveI());
        assertEquals(0, board.getRecentMoveJ());
        assertEquals(2, board.getFilledCount());
        Integer[] moves = ai.getBestMoves(board);
        System.out.println(moves[0]);
        System.out.println(moves[1]);
    }

    @Test
    public void testIsMinimizing() {
        Board board = new Board();
        MinimaxAI ai = new MinimaxAI(false);
        board.markPosition('X', 1, 1);
        Integer[] bestAIMoves = ai.getBestMoves(board);
        assertEquals(0, bestAIMoves[0]);
        assertEquals(0, bestAIMoves[1]);
        board.markPosition('O', bestAIMoves[0], bestAIMoves[1]);
        board.getPrinted();

        board.markPosition('X', 0, 1);
        bestAIMoves = ai.getBestMoves(board);
        assertEquals(2, bestAIMoves[0]);
        assertEquals(1, bestAIMoves[1]);
        board.markPosition('O', bestAIMoves[0], bestAIMoves[1]);
        board.getPrinted();

    }

}
