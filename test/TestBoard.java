

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;


import SimpleTTT.Board;

/**
 * Note: JUnit test cannot use scanner input to read txt file.
 * So we have to print on the terminal to check for the filled count();
 * Make sure to run both the main and the unit test
 */
public class TestBoard {
    public static void main(String[] args) {
        System.out.println("Main Test:");
        Board board = new Board();
        board.markPosition('X', 1, 1);
        board.markPosition('O', 1, 1);
        board.markPosition('O', 2, 2);
        board.unmarked(1, 1,2,2);
        board.loadTemp1();
        ArrayList<Boolean> testCases = new ArrayList<>();
        testCases.add(board.getFilledCount()==9);
        testCases.add(board.getRecentMoves()[0] == 2 && board.getRecentMoves()[1] == 2);
        try {
            board.readFromFile(new File("SimpleTTT/BoardTemplate/board2.txt"));
        } catch (FileNotFoundException e) {}
        
        Boolean aWin = board.checkWinXorO('X', 0, 2, 0, +1);
        testCases.add(aWin);
        try {
            board.readFromFile(new File("SimpleTTT/BoardTemplate/board3.txt"));
        } catch (FileNotFoundException e) {}
        board.getPrinted();
        testCases.add(board.checkWinXorO('X', 0, 0, 1, 1));
        testCases.add(board.checkWin() == 'X');



        for (Boolean testCase : testCases) {
            System.out.println("Test passed: " +testCase);
        }
        
    }

    @Test
    public void testFilledCount() {
        Board board1 = new Board();
        assertEquals(0, board1.getFilledCount());
        board1.getPrinted();
        assertEquals(true,board1.markPosition('X', 1, 1));
        assertEquals(1, board1.getFilledCount());
        board1.markPosition('O', 1, 1);
        assertEquals(1, board1.getFilledCount());
        board1.markPosition('O', 2, 2);
        assertEquals(2, board1.getFilledCount());
        board1.unmarked(0, 0,1,1);
        assertEquals(2, board1.getFilledCount());
        board1.unmarked(2, 2,1,1);
        assertEquals(1, board1.getFilledCount());
    }

    @Test
    public void testWithinBound() {
        Board board = new Board();
        assertEquals(true, board.isWithinBound(2, 2));
        assertEquals(true, board.isWithinBound(1, 2));
        assertEquals(false, board.isWithinBound(2, -1));
        assertEquals(false, board.isWithinBound(3, 2));
    }

    @Test
    public void isValidPair() {
        Board board = new Board();
        assertEquals(true, board.isValidPair(0, 2));
    }

    @Test
    public void testRecentMark() {
        Board board = new Board();
        board.markPosition('X', 0, 1);
        board.markPosition('O', 1, 2);
        assertEquals(1, board.getRecentMoves()[0]);
        assertEquals(2, board.getRecentMoves()[1]);
        board.markPosition('X', 1, 3); // not executed
        assertEquals(1, board.getRecentMoves()[0]);
        assertEquals(2, board.getRecentMoves()[1]);
        board.unmarked(1, 2, 0, 1);
        
        assertEquals(0, board.getRecentMoves()[0]);
        assertEquals(1, board.getRecentMoves()[1]);

        board.markPosition('O', 2, 1);
        assertEquals(2, board.getRecentMoves()[0]);
        assertEquals(1, board.getRecentMoves()[1]);
        
    }

    
}
