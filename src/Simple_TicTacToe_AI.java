import java.util.ArrayList;
import java.util.Scanner;

class Simple_TicTacToe_AI {
    private static Board board;
    private static ArrayList<Character> playerMark;
    private static AIPlayler bot;

    public static void main(String[] args) {
        board = new Board();
        gameRun();

    }

    public static void gameRun() {
        Scanner scanner = new Scanner(System.in);
        RealMiniMax ai = new RealMiniMax();
        Integer count = 0;
        Integer I, J;

        while (true) {
            // Player AI
            ai.bestMove(board);
            board.markX(ai.bestMove[0]+1, ai.bestMove[1]+1);
            count++;
            // Clear screen gimmick
            System.out.print("\033[H\033[2J");
            System.out.flush();

            board.getPrinted();
            System.out.println();

            do {
                System.out.println("Friendly I:");
                I = scanner.nextInt();
                System.out.println("Friendly J:");
                J = scanner.nextInt();
                
            } while(!board.playerChoose(false, I, J));
            

            // if playerX won
            if (board.checkWin() == 0) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                board.getPrinted();
                System.out.println("Player 0 (X) won");
                break;
            }

            // if playerO won
            else if (board.checkWin() == 1) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                board.getPrinted();
                System.out.println("Player 1 (O) won");
                break;
            }

        }
        scanner.close();
    }

}

