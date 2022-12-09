

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {

    // blank mark: '-'
    // markX: 'X'
    // markO: 'O'
    private int size = 3;

    /** Instance vars */
    private Character[][] data = new Character[3][3];
    private int filled = 0; // how many spaces have been filled
    private Integer[] recentMove = new Integer[2];

    // for (int i=0; i< 3; i++) {
    // for (int j=0; j<3; j++) {

    // }
    // }

    public Board() {
        resetBoard();
    }


    public int getSize() {
        return this.size;
    }

    // 0. Debugging
    public void test() {
        data[1][1] = 'X';
    }

    // I. Set information

    /**
     * Reset the board, all positions to blank mark
     */
    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                data[i][j] = '-';
            }
        }
    }

    /**
     * Set recent move in location i,j
     * @param i
     * @param j
     */
    public void setRecentMoves(Integer i, Integer j) {
        recentMove[0] = i;
        recentMove[1] = j;
    }


    /**
     * Read data from a txt file and overwrite the value to this.data
     * Debugging purpose
     * 
     * @param file can be initiated by calling new File(String path)
     * @throws FileNotFoundException
     */

    public void readFromFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        int i = 0;
        filled = 0;
        while (true) {
            if (!sc.hasNextLine())
                break;
            char[] arr = sc.nextLine().toCharArray();
            for (int j = 0; j < 3; j++) {
                if (arr[j] == 'X' || arr[j] == 'O' || arr[j] == '-') {
                    data[i][j] = arr[j];
                    if (arr[j] != '-') {
                        this.filled++;
                        recentMove[0] = i;
                        recentMove[1] = j;
                    }
                }  
            }
            i++;
        }
        sc.close();
    }

    /**
     * read template 1 data ~ board1.txt
     * 
     * @throws FileNotFoundException
     */
    public void loadTemp1() {
        File file = new File("src/BoardTemplate/board1.txt");
        try {
            readFromFile(file);
        } catch (FileNotFoundException e) {
            System.out.println("Java error: Cannot read file");
        }
    }


    /**
     * Mark down the X or O char in the location (i,j) of the board
     * 
     * @param playerMark can be only 'X' or 'O'
     * @param i          from 0-2
     * @param j          from 0-2
     * @return true if this function is executed
     */
    public boolean markPosition(char playerMark, int i, int j) {
        // System.out.println("This line 1 is executed");
        if (!isValidPair(i, j))
            return false;
        // System.out.println("This line 2 is executed");
        if (playerMark != 'X' && playerMark != 'O')
            return false;
        this.data[i][j] = playerMark;
        filled++;
        recentMove[0] = i; recentMove[1] = j;
        return true;
    }


    /**
     * Given a data in cell i,j. if the data is a player mark, unmark that position
     * into '-'
     * 
     * @param i location i of board data (0-2)
     * @param j location j of board data (0-2)
     * @param recentI unmark makes the whole recent move confusing, keep track and set it by yourself
     * @param recentJ unmark makes the whole recent move confusing, keep track and set it by yourself
     * @return true if executed
     */
    public boolean unmarked(int i, int j, Integer recentI, Integer recentJ) {
        if (data[i][j] == '-')
            return false;
        data[i][j] = '-';
        filled--;
        setRecentMoves(recentI, recentJ);
        return true;
    }
    // II. Get information

    /**
     * Get the filled count
     */
    public int getFilledCount() {
        return filled;
    }

    /**
     * 
     * @return Get the most recent location played on the board
     * i = getRecentMoves[0], j = getRecentMoves[1]
     */
    public Integer[] getRecentMoves() {
        return recentMove;
    }

    public Integer getRecentMoveI() {
        return recentMove[0];
    }

    public Integer getRecentMoveJ() {
        return recentMove[1];
    }

    /**
     * Print out the board data
     */
    public void getPrinted() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(data[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Get the board data. a 2D array
     */
    public Character[][] getData() {
        return data;
    }
    
    public Character getDataAt(int i, int j) {
        if (isWithinBound(i, j)) return data[i][j];
        return null;
    }

    /**
     * @param i the based-0 index of the location on board data
     * @param j the based-0 index of the location on board data
     * @return true if player can mark their choice in this location
     */
    public boolean isValidPair(int i, int j) {
        if (i >= 0 && i <= 2 && j >=0  && j <= 2) {
            return (data[i][j] == '-');
        } else {
            return false;
        }
    }

    /**
     * @param i
     * @param j
     * @return true if the pair is within the accepted bound >=0 and <=2
     */
    public boolean isWithinBound(int i, int j) {
        return (i >= 0 && i <= 2 && j >=0  && j <= 2);
    }

    /**
     * 
     * @return 'X' if X wins, 'O' if O wins
     * @return 'T' if tie, 'N' if no one wins
     */
    public Character checkWin() {
        if (filled <5) return 'N';
        else {
            // if X wins or O wins
            Integer recentI = recentMove[0]; Integer recentJ = recentMove[1];
            Character refMark = data[recentI][recentJ];
            if (checkWinXorO(refMark, recentI, recentJ, 0, 1)
                || checkWinXorO(refMark, recentI, recentJ, 1, 0)
                || checkWinXorO(refMark, recentI, recentJ, 1, 1)
                || checkWinXorO(refMark, recentI, recentJ, 1, -1)) {
                    return refMark;
            }

            // No one wins
            if (filled < 9) {
                return 'N';
            }
            // Tie
            return 'T';
        }
    }

    /**
     * @param . the other 3 takes in the most recent mark info
     * @param iIncrement checking pattern
     * @param jIncrement checking pattern
     * @return true if a win happens
     */
    public Boolean checkWinXorO(Character refMark, Integer recentI, 
        Integer recentJ, Integer iIncr, Integer jIncr) {

        Integer i = recentI; Integer j = recentJ;
        int count = 1;
        // positive side
        while (true) {
            i+= iIncr;
            j += jIncr;
            if (!isWithinBound(i, j)) break;
            if (data[i][j] != refMark ) break;
            else {
                count++;
            }
        }
        // negative side
        i = recentI; j = recentJ;
        while (true) {
            i+= -1*iIncr;
            j += -1*jIncr;
            if (!isWithinBound(i, j)) break;
            if (data[i][j] != refMark ) break;
            else {
                count++;
            }
        }
        // System.out.println("The count is");
        // System.out.println(count);
        if (count==3) return true;
        return false;
    }
    

}
