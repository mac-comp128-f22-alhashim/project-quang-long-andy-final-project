package Board;
public class Board {
    private int size =3;
    private int filled = 0;
    private char[][] board;
    private char unmarked = '-';
    private char markO = 'O';  //Player 1's mark
    private char markX = 'X';  //Player 0's mark

    public Board() {
        this.board = new char[size][size];
        resetBoard();
    }

    public int getSize() {
        return this.size;
    }

    public Character getPlayer0Mark() {
        return markX;
    }

    public char[][] getBoard(){
        return board;
    }

    public char getPlayer1Mark() {
        return markO;
    }

    // Friendly means based-1 index, non-friendly means based0-computer science index

    /**
     * 
     * @param friendlyI
     * @param friendlyJ
     * @return get a mark at given friendly positions
     */
    public char getPosition(int friendlyI, int friendlyJ) {
        return board[friendlyI-1][friendlyJ-1];
    }
    /**
     * 
     * @return unmark char
     */
    public char getBlankMark() {
        return unmarked;
    }

    /**
     * 
     * @return How many marks have been filled
     */
    public int getFilledCount() {
        return filled;
    }

    public void unmarked(int i, int j) {
        this.board[i][j] = unmarked;
    }

    public char[][] getMatrixData() {
        return board;
    }

    public void resetBoard() {
        for (int i=0; i< this.size; i++) {
            for (int j=0; j< this.size; j++) {
                this.board[i][j] = unmarked;
            }
        }
        filled=0;
    }

    public void getPrinted() {
        System.out.println("------ XO Board -------");
        System.out.println();
        for (int i=0; i< this.size; i++) {
            for (int j=0; j< this.size; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Check if the player can mark given a position
     * @param indexI
     * @param indexJ
     * @return
     */
    public boolean isValid(int indexI, int indexJ) {
        return (indexI<=3 && indexI>=1 && indexJ<=3 && indexJ>=0) && (board[indexI-1][indexJ-1] == unmarked);
    }
    
    public void markX(int friendlyI, int friendlyJ) {
        board[friendlyI-1][friendlyJ-1] = markX;
        filled++;
    }

    public void markO(int friendlyI, int friendlyJ) {
        board[friendlyI-1][friendlyJ-1] = markO;
        filled++;
    }
    /**
     * Given the i and j, and the player's turn mark a location on the board. User's friendly input
     * @param isPlayer0 Player0's turn or not ?
     * @param i The location of i on the board (1 to 3). 
     * @param j The location of j on the board (1 to 3)
     * @return
     */
    public boolean playerChoose(Boolean isPlayer0, int i, int j ) {
        i+=-1;
        j+=-1;
        if ( !(board[i][j] == '-') ) {
            return false;
        }
        if (isPlayer0) {
            board[i][j] = markX;
        }
        else {
            board[i][j] = markO;
        }
        filled++;
        return true;
    }

    /**
     * check win condition
     * @return -1 if no one wins, 0 if playerO (X) wins, 1 if player1 wins (O)
     */
    public int checkWin() {
        String charSeq = new String();

        charSeq = addString(board[0][0], board[1][1], board[2][2]); //diagonal case 1
        if (charSeq != null) {
            return identifyXO(charSeq);
        }

        charSeq = addString(board[2][0], board[1][1], board[0][2]); // diagonal case 2
        if (charSeq != null) {
            return identifyXO(charSeq);
        }

        for (int i=0; i<size; i++) {
            charSeq = addString(board[i][0], board[i][1], board[i][2]); // horizontal cases
            if (charSeq != null) {
                return identifyXO(charSeq);
            }
            charSeq = addString(board[0][i], board[1][i], board[2][i]);  //vertical cases
            if (charSeq != null) {
                return identifyXO(charSeq);
            }
        }
        return -1;
    }

    /**
     *  Concatenate all sequences of winning
     * @param char0
     * @param char1
     * @param char2
     * @return null if 3 chars concatenated are similar, 'XXX', or 'OOO' if char0=char1=char2
     */
    public String addString(char char0, char char1, char char2) {
        if (char0 == unmarked || char1 == unmarked || char2 == unmarked) {
            return null;
        }
        if  (! ((char0 == char1) && (char1==char2) ) ) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(char0); stringBuilder.append(char1); stringBuilder.append(char2);
        return stringBuilder.toString();
    }

    /**
     * 
     * @param charSeq
     * @return 0 if player0 got 3 in a row, 1 if player1 got 3 in a row
     */
    public int identifyXO(String charSeq) {
        String markXSeq = (new StringBuilder()).append(markX).
                            append(markX).
                            append(markX).toString();  
        String markOSeq = (new StringBuilder()).append(markO).
                            append(markO).
                            append(markO).toString();  
        if (charSeq.equals(markXSeq)) {
            return 0;
        }
        else if (charSeq.equals(markOSeq)) {
            return 1;
        }
        else {
            return -1; // Never happens
        }
    }

}
