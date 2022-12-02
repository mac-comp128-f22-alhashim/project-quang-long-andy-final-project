import java.awt.Color;
import java.awt.Font;
import java.io.File;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.GroupLayout.Alignment;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.*;
import edu.macalester.graphics.events.*;




// This component attempts to create a window that visualizes the board
// based on a 2d arrary imput.

public class WindowCanvas {
    
    private Board board;
    private CanvasWindow f; // "frame"
    private int squareSize = 100;
    private int offSet = 170; // top vertical space
    private int lw = 7; // line width 
    private int winwidth;
    private int winheight;
    private boolean isPlayer0 = true;
    private GraphicsText turnLabel;
    private boolean isWon = false;

    public WindowCanvas(Board board){
        // makeSound("./src/soundfx/sound of death.wav")
        this.board = board;
        
        int rowNum = this.board.getBoard().length;
        int colNum = this.board.getBoard()[0].length;

        winwidth = colNum * squareSize + (colNum-1)*lw;
        winheight = offSet + rowNum * squareSize + rowNum*lw;


        f = new CanvasWindow("TTT", winwidth, winheight);
        

        setUp(rowNum, colNum);
        f.onMouseMove(event->mouseMove(event));
        f.onClick(event->mouseClick(event));
    }

    private void mouseMove(MouseMotionEvent event){
    }

    private void togglePlayer(){
        turnLabel.setFont("Courier, Comic Sans MS, Helvetica",FontStyle.BOLD_ITALIC, winwidth/8);
        if (isPlayer0 == true) {
            isPlayer0 = false; 
            turnLabel.setText("O's turn");
            turnLabel.setFillColor(new Color(51,186,215,220));
        }
        else {isPlayer0=true; 
            turnLabel.setText("X's turn");
            turnLabel.setFillColor(new Color(215,169,51,220));
    }
    }
       

    private void mouseClick(MouseButtonEvent event){
        Point p = event.getPosition();     
        if (p.getY()>offSet && isWon==false){
            GraphicsObject o = f.getElementAt(p);
            if (o instanceof Rectangle){
                Rectangle r = (Rectangle) o;
                if (r.isStroked()==false){
                    int row = (int)Math.floor(p.getX()/squareSize);
                    int col = (int)Math.floor((p.getY()-offSet)/squareSize);
                    board.playerChoose(isPlayer0, col+1, row+1);
                    r.setStroked(true);
                    addImage(row, col);
                    checkWin();
                    makeSound("./res/soundfx/ding dong.wav");
                }
                else{makeSound("./res/soundfx/click2.wav");}
            }
            else{makeSound("./res/soundfx/click1.wav");}
        }
        else{makeSound("./res/soundfx/click2.wav");}
        board.getPrinted();
    }
    

    private void addImage(int row, int col){
        if (isPlayer0==true){
            double x = row*squareSize+1.5*lw;
            double y = offSet+col*squareSize+lw;
            Image a = new Image(x, y,"./imgs/X.png");
            a.setMaxWidth(squareSize*.9);
            f.add(a);
        }
        else{
            double x = row*squareSize+1.5*lw;
            double y = offSet+col*squareSize+lw;
            Image a = new Image(x, y,"./imgs/O.png");
            a.setMaxWidth(squareSize*.9);
            f.add(a);
        }
    }

    private void checkWin(){
        if (board.checkWin() == 1 || board.checkWin() == 0){
            makeSound("./res/soundfx/win.wav");
            turnLabel.setText("The End");
            turnLabel.setFillColor(new Color(0,119,36,255));
            turnLabel.setFont("Tahoma",FontStyle.PLAIN,winwidth/10);
            isWon = true;
        }
        else{
            togglePlayer();
        }
    }

    private void setUp(int rowNum, int colNum){
        for (int i = 0; i<colNum+1 ; i++){
            //vertical
            Line line = new Line(i*squareSize+lw, offSet, i*squareSize+lw,winheight-3*lw);
            line.setStrokeWidth(lw);
            Color c = new Color(60,128,180,100);
            line.setStrokeColor(c);
            f.add(line);
        }
        
        for (int i = 0; i<rowNum+1;i++){
            //horizontal
            Line line = new Line(lw, offSet+i*squareSize,winwidth-lw,offSet+i*squareSize);
            line.setStrokeWidth(lw);
            Color c = new Color(60,128,180,100);
            line.setStrokeColor(c);
            f.add(line);
        }

        for (int i = 0; i<colNum; i++){
            for (int j = 0; j<rowNum; j++){
                Rectangle r = new Rectangle(i*squareSize+lw,j*squareSize+offSet, squareSize,squareSize);
                r.setFillColor(new Color(101,177,51,30));
                r.setStroked(false);
                f.add(r);
            }
        }

        turnLabel = new GraphicsText("Begin",winwidth/4,offSet/2);
        turnLabel.setFont("Signpainter,American TypeWriter, Tahoma", FontStyle.BOLD, winwidth/4);
        f.add(turnLabel);
    }


    public void makeSound(String location){
        try {
            File f = new File(location);
            if (f.exists()){
                AudioInputStream ip = AudioSystem.getAudioInputStream(f);
                Clip clip = AudioSystem.getClip();
                clip.open(ip);
                clip.start();
            }
            else{
                System.out.println("can't find file");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    
    }

    public static void main (String[] args){
        Board a = new Board();
        WindowCanvas test = new WindowCanvas(a);

    }
}