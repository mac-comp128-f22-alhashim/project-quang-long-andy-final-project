// Player vs Bot (in the terminal)
// Note: To decide who goes first, refer to line 10. Change the value to true or false.
//      True ~ Human will go first, False ~ Human will go after Bot

// If you notice, I repeat the check win conditions 4 times in the main.
// But I cannot find a way to write a child method that can break the parent method so I just copy-paste
// the function. 



import java.util.HashMap;
import java.util.Scanner;

import SimpleTTT.Board;
import SimpleTTT.MinimaxAI;

public class TTTwithAI {
    public static Boolean isHumanPlayerX = false; // Change me
    public static Board board;

    public static void main(String[] args) {

        // Set up 1
        board = new Board();
        HashMap<Boolean, Character> turnMap = new HashMap<Boolean, Character>();
        turnMap.put(true, 'X');
        turnMap.put(false, 'O');
        Scanner scanner = new Scanner(System.in);

        // Set up 2 for taking turn
        Integer i, j;
        // Boolean isHumanPlayerX = true; // human is player X
        Boolean isBotPlayerX = !isHumanPlayerX;
        MinimaxAI ai = new MinimaxAI(!isHumanPlayerX);
        Integer[] aiBestMoves = new Integer[2];
        Character outcome;

        // Game run
        while (board.getFilledCount() < 9) {

            if (isHumanPlayerX) { // human goes first
                // Print out the board
                displayPrompt();

                // Take user input
                do {
                    System.out.print("Your I and J (1-3):");
                    i = scanner.nextInt() - 1;
                    j = scanner.nextInt() - 1;
                    System.out.println();
                } while (!board.isValidPair(i, j));

                // mark the position and continue turn
                board.markPosition(turnMap.get(isHumanPlayerX), i, j);

                // check win condition 1
                outcome = board.checkWin();
                System.out.println(outcome);
                if (outcome == 'X' || outcome == 'O') {
                    displayPrompt();
                    System.out.println(outcome + " wins");
                    break;
                }
                if (outcome == 'T') {
                    displayPrompt();
                    System.out.println("TIE");
                    break;
                } else if (outcome == 'N') {
                }

                // AI input
                aiBestMoves = ai.getBestMoves(board);
                board.markPosition(turnMap.get(isBotPlayerX), aiBestMoves[0], aiBestMoves[1]);

                // check win condition 2
                outcome = board.checkWin();
                System.out.println(outcome);
                if (outcome == 'X' || outcome == 'O') {
                    displayPrompt();
                    System.out.println(outcome + " wins");
                    break;
                }
                if (outcome == 'T') {
                    displayPrompt();
                    System.out.println("TIE");
                    break;
                } else if (outcome == 'N') {
                    continue;
                }
            } else { // Bot goes first

                // AI input
                aiBestMoves = ai.getBestMoves(board);
                board.markPosition(turnMap.get(isBotPlayerX), aiBestMoves[0], aiBestMoves[1]);
                displayPrompt();

                // Check win condition 1
                outcome = board.checkWin();
                if (outcome == 'X' || outcome == 'O') {
                    displayPrompt();
                    System.out.println(outcome + " wins");
                    break;
                }
                if (outcome == 'T') {
                    displayPrompt();
                    System.out.println("TIE");
                    break;
                } else if (outcome == 'N') {
                }

                // Take user input
                do {
                    System.out.print("Your I and J (1-3):");
                    i = scanner.nextInt() - 1;
                    j = scanner.nextInt() - 1;
                    System.out.println();
                } while (!board.isValidPair(i, j));

                // mark the position and continue turn
                board.markPosition(turnMap.get(isHumanPlayerX), i, j);

                // check win condition 2
                outcome = board.checkWin();
                System.out.println(outcome);
                if (outcome == 'X' || outcome == 'O') {
                    displayPrompt();
                    System.out.println(outcome + " wins");
                    break;
                }
                if (outcome == 'T') {
                    displayPrompt();
                    System.out.println("TIE");
                    break;
                } else if (outcome == 'N') {
                    continue;
                }
            }

        }
        scanner.close();
    }

    /** Display the board */
    public static void displayPrompt() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("XO Board:");
        System.out.println();
        board.getPrinted();
        System.out.println();
    }
}
