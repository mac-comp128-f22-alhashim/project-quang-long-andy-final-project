public class AIPlayler {
    
    private Board board;

    public AIPlayler(Board board) {
        this.board = board;
    }

    private int[][] preferredMoves = {
        {2, 2}, {1, 1}, {1, 3}, {3, 1}, {3, 3},
        {1, 2}, {2, 1}, {2, 3}, {3, 2}};

    public int[] move() {
        for (int[] move : preferredMoves) {
            if (board.getBoard()[move[0]][move[1]] == '-') {
                return move;
            }
        }
        assert false : "No empty cell?!";
        return null;
    }
    
}
