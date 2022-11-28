import java.awt.*;

// This component attempts to create a window that visualizes the board
// based on a 2d arrary imput.

public class WindowCanvas {
    
    private char[][] board;
    private boolean[][] boardCheck;

    public WindowCanvas(char[][] board){
        final int tileSize = 70;
        final int offSet = 50;
        this.board = board;
        
        int rowNum = this.board.length;
        int colNum = this.board[0].length;
        this.boardCheck = new boolean[rowNum][colNum];


        Frame f = new Frame();
        f.setTitle("ttt");
        f.setSize(rowNum*tileSize,colNum*tileSize+offSet);
        f.setVisible(true);
        f.setResizable(false);


        for (int i=0; i<rowNum; i++){
            for (int j=0; j<colNum; j++){
                Button b = new Button();
                char tc = this.board[i][j]; // tile character
                if (tc == 'x' || tc == 'o'){
                    b.setLabel(Character.toString(tc));
                }
                b.setBounds(tileSize*(i),tileSize*(j)+offSet,tileSize,tileSize);
                f.add(b);
            }
        }
    }

    public static void main (String[] args){
        char[][] board = new char[4][4];
        WindowCanvas test = new WindowCanvas(board);
    }
}