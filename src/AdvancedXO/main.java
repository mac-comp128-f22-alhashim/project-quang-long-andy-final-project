// Play ttt in the terminal

package AdvancedXO;

import java.util.Scanner;

public class main {
    private static BigBoard board;
    
    public static void main(String[] args) {
        board = new BigBoard(7);
        gameRun();
    }

    public static void gameRun() {
        Boolean isPlayer0 = true;
        Scanner scanner = new Scanner(System.in);
        Integer valueI = 0;
        Integer valueJ = 0;
        for (int i = 0; i < board.getSize()*board.getSize(); i++) {
            // Clear screen gimmick
            System.out.print("\033[H\033[2J");  
            System.out.flush(); 
            board.getPrinted();
            System.out.println();
            if (isPlayer0)
                System.out.println("Player 0's (X) turn:");
            else
                System.out.println("Player 1's (O) turn");

            do {    // while loop to check for invalid input
                System.out.print("What is your i (1-size only):");
                valueI = scanner.nextInt();
                System.out.print("What is your j (1-size only):");
                valueJ = scanner.nextInt();
                System.out.println();
            } while (!board.playerChoose(isPlayer0, valueI, valueJ)); // check for invalid input

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
            // if not then continue
            isPlayer0 = !isPlayer0; // Switch turn
        }
        scanner.close();
    }

    
}
