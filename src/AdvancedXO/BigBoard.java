/** 5 in a row instead of 3, size is 20 */
package AdvancedXO;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


public class BigBoard {
    private int size; private int defaultSize = 12;
    private char[][] board;
    private int filled = 0;
    private char unmarked = '-';
    private char markO = 'O';
    private char markX = 'X'; 
    private Integer recentI; private Integer recentJ; 
    private HashMap<Character, Integer> outcomeMap; // X -> 0, O -> 1

    public BigBoard(int inputSize) {
        if (inputSize < 5) 
            this.size = defaultSize;
        else {
            this.size = inputSize;
        }
        this.board = new char[size][size];
        this.outcomeMap = new HashMap<>();
        this.outcomeMap.put(markX, 0);
        this.outcomeMap.put(markO, 1);
        resetBoard(); 
    }

    public int getSize() {
        return this.size;
    }

    public void markO(int i, int j) {
        this.board[i][j] = markO;
        filled++;
    }

    public void markX(int i, int j) {
        this.board[i][j] = markX;
        filled++;
    }

    public void unmarked(int i, int j) {
        this.board[i][j] = unmarked;
    }


    public void resetBoard() {
        for (int i=0; i< this.size; i++) {
            for (int j=0; j< this.size; j++) {
                this.board[i][j] = unmarked;
            }
        }
        filled=0;
    }

    public char[][] getBoard(){
        return board;
    }

    /**
     * 
     * @return How many marks have been filled
     */
    public int getFilledCount() {
        return filled;
    }

    public void getPrinted() {
        System.out.println("------ XO Board -------");
        System.out.println();
        for (int i=0; i< this.size; i++) {
            for (int j=0; j< this.size; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    public Integer getRecentI() {
        return recentI;
    }
    public Integer getRecentJ() {
        return recentJ;
    }

    public boolean isValid(int basedZeroIndex) {
        return (basedZeroIndex < size && basedZeroIndex>=0);
    }

    public boolean isValidPair(int basedZeroIndex0, int basedZeroIndex1) {
        return isValid(basedZeroIndex0) && isValid(basedZeroIndex1);
    }

    /**
     * Given the i and j, and the player's turn mark a location on the board
     * @param isPlayer0 Player0's turn or not ?
     * @param i The location of i on the board (1 to size). (Based 1 world - user-friendly !!!) 
     * @param j The location of j on the board (1 to size).
     * @return
     */
    public boolean playerChoose(Boolean isPlayer0, int userFriendlyI, int userFriendlyJ ) {
        int i=userFriendlyI-1; int j=userFriendlyJ-1;
        if ( !(board[i][j] == '-') || !isValid(i) || !isValid(j)) {
            return false;
        }
        if (isPlayer0)
            board[i][j] = 'X';
        else 
            board[i][j] = 'O';
        recentI = i; // Zero-based world
        recentJ = j;
        filled++;
        return true;
    }

    /**
     * check win condition
     * @return -1 if no one wins, 0 if playerO (X) wins, 1 if player1 wins (O)
     */
    public int checkWin() {
        // get the result of a player's mark first:
        if (recentI == null) {
            return -1;
        }
        char desiredChar = board[recentI][recentJ];

        int verticalWinCon = checkWin(recentI, recentJ, desiredChar, 1, 0);
        if (verticalWinCon > -1) return verticalWinCon;
        int horizontalWinCon = checkWin(recentI, recentJ, desiredChar, 0, 1);
        if (horizontalWinCon >-1) return horizontalWinCon;
        int diagPositiveWinCon = checkWin(recentI, recentJ, desiredChar, 1, 1);
        if (diagPositiveWinCon > -1) return diagPositiveWinCon;
        int diagNegativeWinCon = checkWin(recentI, recentJ, desiredChar, -1, 1);
        if (diagNegativeWinCon > -1) return diagNegativeWinCon;
        return -1;
    }

    /**
     * Supporting function. Check whether there is a winning line contatining the most recent mark 
     * @param recentI  the i location of the most recent mark 
     * @param recentJ  the j location of the most recent mark
     * @param desiredChar   the intial mark to be compared
     * @param iIncrement+jIncrement   to determine a winning horizontal line, a vertical line, or a diagonal line
     * @return
     */
    private int checkWin(int recentI, int recentJ, char desiredChar, int iIncrement, int jIncrement) {
        int count = 1;
        int i = recentI; int j = recentJ;
        while (true) {
            i+= iIncrement; j+= jIncrement;
            if ( !isValidPair(i, j) )
                break;
            if (board[i][j] == desiredChar) 
                count++;
            if (count == 5) {
                return outcomeMap.get(desiredChar);
            }
            if (board[i][j]!= desiredChar) {
                // System.out.println("The pos side");
                // System.out.println("The break happens at i=" +i);
                // System.out.println("The break happens at j=" +j);
                break;
            }
        } 
        i = recentI; 
        j = recentJ;
        // System.out.println("Recent i is"+i);
        // System.out.println("Recent j is "+j);
        while (true) {
            // System.out.println("Neg times");
            // System.out.println("The i is " + i);
            // System.out.println("The j is "+j);
            i+= -1*iIncrement; j+= -1*jIncrement;
            if ( !isValidPair(i, j) )
                break;
            if (board[i][j] == desiredChar)  {
                count++;
            }
            if (count == 5)  { // overcount when count the reference board[recentI][recentJ] again
                // System.out.println("Ive returned");
                return outcomeMap.get(desiredChar);
            }
            if (board[i][j] != desiredChar) {
                // System.out.println("Neg side");
                // System.out.println("The break happens at i=" +i);
                // System.out.println("The break happens at j=" +j);
                break;
            }
        } 
        // System.out.println("The final count is "+count);
        return -1;
    }
}

    