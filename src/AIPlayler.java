public class AIPlayler {
    
    private Board board;

    public AIPlayler(Board board) {
        this.board = board;
    }

    private int[][] preferredMoves = {
        {1, 1}, {0, 0}, {0, 2}, {2, 0}, {2, 2},
        {0, 1}, {1, 0}, {1, 2}, {2, 1}};

    private void move() {
        for (int[] move : preferredMoves) {
            if (board[move[0]][move[1]] == '-') {
                return;
            }
    
        }
    }
    
}
