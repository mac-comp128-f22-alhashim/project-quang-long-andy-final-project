import java.util.HashMap;

public class RealMiniMax {
    public Integer[] bestMove;
    public RealMiniMax() {
        bestMove = new Integer[2];
        HashMap map = new HashMap<Integer, Integer>();
        map.put(0,1); // playerX wins
        map.put(1, -1); // playerO wins
        map.put(-1,0); // Tie
    }

    public void bestMove(Board board) {
        Integer bestScore = -999;
        // all available spaces
        for (int i=0; i< 3; i++) {
            for (int j=0; j<3 ; j++) {
                if (board.getBoard()[i][j] == board.getBlankMark()) {
                    board.markX(i+1, j+1);
                    Integer score = minimax(board,0, true);
                    board.getBoard()[i][j] = board.getBlankMark();
                    if (score >bestScore) {
                        bestScore = score;
                        bestMove[0] =i;
                        bestMove[1]=j;
                    }
                }
            }
        }
    }

    public Integer minimax(Board board, Integer depth, Boolean isMaximizing) {
        int result = board.checkWin();
        if (result == 0)
        return 1;
    }
}
