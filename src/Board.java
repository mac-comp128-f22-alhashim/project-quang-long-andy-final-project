public class Board {
    private int size =3;
    private char[][] board;
    private char unmarked = '-';
    private char markO = 'O';
    private char markX = 'X'; 

    public Board() {
        this.board = new char[size][size];
        resetBoard();
    }

    public int getSize() {
        return this.size;
    }

    public void markO(int i, int j) {
        this.board[i][j] = markO;
    }

    public void markX(int i, int j) {
        this.board[i][j] = markX;
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
        if (isPlayer0)
            board[i][j] = 'X';
        else 
            board[i][j] = 'O';
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
        if (char0 == '-' || char1 == '-' || char2 == '-') {
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
     * @return 0 if string is "XXX", 1 if string is "OOO"
     */
    public int identifyXO(String charSeq) {
        if (charSeq.equals("XXX")) {
            return 0;
        }
        else if (charSeq.equals("OOO")) {
            return 1;
        }
        else {
            return -1; // Never happens
        }
    }

}
