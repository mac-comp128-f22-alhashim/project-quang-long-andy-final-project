// Test bigboard win condition and behavior

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import AdvancedXO.BigBoard;

public class BigBoardTest {
    @Test
    public void testWinConEmpty() {
        BigBoard board = new BigBoard(4);
        assertEquals(-1, board.checkWin());
    }
    @Test 
    public void testSize() {
        BigBoard board = new BigBoard(5);
        assertEquals(5, board.getSize());
    }

    @Test
    public void testVerticalWinCon() {
        BigBoard board = new BigBoard(12);
        board.playerChoose(true, 2, 1);
        board.playerChoose(true, 3, 1);
        board.playerChoose(true, 5, 1);
        board.playerChoose(true, 6, 1);
        board.playerChoose(true, 4, 1);
        assertEquals(0, board.checkWin());
    }
    
    @Test
    public void testVerticalWinCon2() {
        BigBoard board = new BigBoard(12);
        board.playerChoose(true, 8, 12);
        board.playerChoose(true, 7, 12);
        board.playerChoose(true, 9, 12);
        board.playerChoose(true, 11, 12);
        board.playerChoose(true, 12, 12);
        assertEquals(-1, board.checkWin());
    }
    @Test 
    public void testVerticalWinCon3() {
        BigBoard board = new BigBoard(5);
        board.playerChoose(true, 5, 4);
        assertEquals(4, board.getRecentI());
        assertEquals(3, board.getRecentJ());
        board.playerChoose(true, 3, 4);
        board.playerChoose(true, 1, 4);
        board.playerChoose(true, 2, 4);
        board.playerChoose(false, 5, 5);
        board.playerChoose(true, 4, 4);
        // assertEquals(4, board.getRecentJ());
        assertEquals(0, board.checkWin());
    }

    @Test
    public void testHorizontalWinCon() {
        BigBoard board = new BigBoard(5);
        board.playerChoose(false, 1, 3);
        board.playerChoose(false, 1, 5);
        board.playerChoose(false, 1, 4);
        board.playerChoose(true, 2, 2);
        board.playerChoose(false, 1, 1);
        board.playerChoose(false, 1, 2);
        assertEquals(1, board.checkWin());
    }

    @Test
    public void testHorizontalWinCon2() {
        BigBoard board = new BigBoard(5);
        int arr[] = {1,2,3,4,5};
        board.playerChoose(false, 3, 4);
        for (int j=0; j<5; j++) {
            board.playerChoose(true, 2, arr[j]);
        }
        assertEquals(0, board.checkWin());
    }

    @Test 
    public void testDiagonal() {
        BigBoard board = new BigBoard(20);
        board.playerChoose(true, 20, 20);
        board.playerChoose(true, 19, 19);
        board.playerChoose(true, 18, 18);
        board.playerChoose(true, 17, 17);
        board.playerChoose(true, 16, 16);
        assertEquals(0, board.checkWin());

        board.unmarked(16, 16);
        assertEquals(-1, board.checkWin());

        board.playerChoose(false, 5, 5);
        board.playerChoose(false, 4, 6);
        board.playerChoose(false, 3, 7);
        board.playerChoose(false, 2, 8);
        board.playerChoose(false, 1, 9);
        assertEquals(1, board.checkWin());
    }

    // -----
    // XXXX-
    // -OXO-
    // -XOO-
    // -----
    @Test
    public void generalTest() {
        BigBoard board = new BigBoard(5);
        int[][] input = {{0,2,2},{1,4,3},{0,2,1},{1,4,4},{0,2,3},{1,3,2},{1,3,4},{0,3,3},{0,4,2},{0,2,4}};
        boolean bool = false;
        for (int i = 0; i<input.length;i++) {
            if (input[i][0] == 0) {
                bool = true;
            }
            else if (input[i][0] == 1) {
                bool = false;
            }
            board.playerChoose(bool, input[i][1], input[i][2]);
        }
        board.getPrinted();
        assertEquals(-1, board.checkWin());

        board.playerChoose(true, 2, 5);
        assertEquals(0, board.checkWin());
    }
}
