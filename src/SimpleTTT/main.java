// Player vs Player

package SimpleTTT;

import java.util.HashMap;
import java.util.Scanner;

public class main {
    public static Board board;
    public static void main(String[] args) {

        // Set up 1
        board = new Board();
        HashMap<Boolean, Character> turnMap = new HashMap<Boolean, Character>();
        turnMap.put(true, 'X'); turnMap.put(false, 'O');
        Scanner scanner = new Scanner(System.in);

        // Set up 2 for taking turn
        Integer i,j; 
        Boolean isPlayerX = true;

        // Game run
        while (board.getFilledCount() < 9) {

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
            board.markPosition(turnMap.get(isPlayerX),i, j);
            isPlayerX = !isPlayerX;

            // check win condition
            Character outcome = board.checkWin();
            if (outcome == 'X' || outcome == 'O') {
                displayPrompt();
                System.out.println(outcome + " wins");
                break;
            }
            if (outcome == 'T') {
                displayPrompt();
                System.out.println("TIE");
                break;
            }
            else if (outcome == 'N') {
                continue;
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
