

import java.util.Random;

public class MinimaxAI {
    private Integer[] bestMoves;
    private Boolean isPlayerX; // is the bot player X (maximizing) or player O (minimizing)
    private int depth = 3; // difficulty, the higher the depth, the harder the bot
    private Boolean isFirstMove = true;

    public MinimaxAI(Boolean isPlayerX) {
        bestMoves = new Integer[2];
        this.isPlayerX = isPlayerX;
    }

    /**
     * Get the best moves in the form of an array.
     * Call getBestMoves[0] to get the i-coordinate, BestMoves[1] to get the
     * j-coordinate
     * 
     * @param board
     * @return an array of best moves
     */
    public Integer[] getBestMoves(Board board) {
        if (isFirstMove && isPlayerX) opening(); // after turn 1, this code is forever unexecuted
        else bestMove(board);
        isFirstMove = false;
        return bestMoves;
    }

    /**
     * Executed when the bot goes first. A variety of openings to make the bot more
     * fun
     */
    public void opening() {
        Random random = new Random();
        int randomNum = random.nextInt(8);
        if (randomNum>=4) randomNum =4;
        System.out.println(randomNum);
        switch (randomNum) {
            case 0:
                bestMoves[0] = 0;
                bestMoves[1] = 0; 
                break;
            case 1:
                bestMoves[0] = 0;
                bestMoves[1] = 2;
                break;
            case 2:
                bestMoves[0] = 2;
                bestMoves[1] = 0;
                break;
            case 3:
                bestMoves[0] = 2;
                bestMoves[1] = 2;
                break;
            case 4: // best moves for the initial
                bestMoves[0] =1;
                bestMoves[1] =1;
                break;
            default:
                bestMoves[0] =1;
                bestMoves[1] =1;
                break;
        }
    }

    /**
     * Supporting function to get the best moves
     * 
     * @param board
     */
    private void bestMove(Board board) {
        // AI makes its turn
        Integer score;
        Integer recentI = board.getRecentMoveI();
        Integer recentJ = board.getRecentMoveJ();

        if (isPlayerX) { // if AI is player X
            Integer bestScore = -2;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getDataAt(i, j) == '-') {
                        board.markPosition('X', i, j);
                        score = minimax(board, this.depth, false);
                        board.unmarked(i, j, recentI, recentJ);
                        if (score > bestScore) {
                            bestScore = score;
                            bestMoves[0] = i;
                            bestMoves[1] = j;
                        }
                    }
                }
            }
        } else { // if AI is player O
            Integer bestScore = +2;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getDataAt(i, j) == '-') {
                        board.markPosition('O', i, j);
                        score = minimax(board, this.depth, true);
                        board.unmarked(i, j, recentI, recentJ);
                        if (score < bestScore) {
                            bestScore = score;
                            bestMoves[0] = i;
                            bestMoves[1] = j;
                        }
                    }
                }
            }
        }

    }

    /**
     * A recursive tree function to find the score of each child game state
     * 
     * @param board        the board object taken to analyze state
     * @param depth        the depth, refer back to the parent's depth (this.depth)
     * @param isMaximizing is the game state maximizing the score (X's turn to
     *                     strike) or not
     * @return the calculated score
     */
    private Integer minimax(Board board, Integer depth, Boolean isMaximizing) {
        Character result = board.checkWin();
        if (result != 'N') {
            if (result == 'X')
                return 1;
            else if (result == 'O')
                return -1;
            else
                return 0;
        }
        if (depth <= 0) {
            return 0;
        }

        Integer score;
        Integer recentI = board.getRecentMoveI();
        Integer recentJ = board.getRecentMoveJ();

        if (isMaximizing) {
            Integer bestScore = -2;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getDataAt(i, j) == '-') {
                        board.markPosition('X', i, j);
                        score = minimax(board, depth - 1, false);
                        board.unmarked(i, j, recentI, recentJ);
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        }

        else {
            Integer bestScore = 2;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getDataAt(i, j) == '-') {
                        board.markPosition('O', i, j);
                        score = minimax(board, depth - 1, true);
                        board.unmarked(i, j, recentI, recentJ);
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
}
