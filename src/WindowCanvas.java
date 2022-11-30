import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import org.w3c.dom.events.MouseEvent;  

// This component attempts to create a window that visualizes the board
// based on a 2d arrary imput.

public class WindowCanvas{
    
    private char[][] board;
    private boolean[][] boardCheck;

    public WindowCanvas(char[][] board){
        final int tileSize = 70;
        final int offSet = 50;
        this.board = board;
        
        int rowNum = this.board.length;
        int colNum = this.board[0].length;
        this.boardCheck = new boolean[rowNum][colNum];


        JFrame f = new JFrame();
        f.setTitle("ttt");
        f.setSize(rowNum*tileSize,colNum*tileSize+offSet*2);
        
        f.setResizable(false);
        f.setLayout(null);

        for (int i=0; i<rowNum; i++){
            for (int j=0; j<colNum; j++){
                JButton b = new JButton();
                b.setBackground(Color.WHITE);
                char tc = this.board[i][j]; // tile character
                if (tc == 'x' || tc == 'o'){
                    b.setText(Character.toString(tc));
                }
                b.setBounds(tileSize*(i),tileSize*(j)+offSet,tileSize,tileSize);
                b.addActionListener(new ActionListener(){
                    public void ActionListener() e){
                        b.setBackground(Color.blue);
                    }
                })
                f.add(b);
            }
        }
        f.setVisible(true);

    }

    public static void main (String[] args){
        char[][] board = new char[4][4];
        WindowCanvas test = new WindowCanvas(board);
    }
}