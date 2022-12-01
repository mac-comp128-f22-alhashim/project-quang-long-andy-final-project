import java.awt.Color;
import java.io.File;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.*;
import edu.macalester.graphics.events.*;




// This component attempts to create a window that visualizes the board
// based on a 2d arrary imput.

public class WindowCanvas {
    
    private char[][] board;
    private CanvasWindow f; // "frame"
    private int squareSize = 100;
    private int offSet = 70; // top vertical space
    private int lw = 7; // line width 
    private int winwidth;
    private int winheight;

    public WindowCanvas(char[][] board){
        makeSound("./src/soundfx/sound of death.wav");
        this.board = board;
        
        int rowNum = this.board.length;
        int colNum = this.board[0].length;

        winwidth = colNum * squareSize + (colNum-1)*lw;
        winheight = offSet + rowNum * squareSize + rowNum*lw;

        f = new CanvasWindow("TTT", winwidth, winheight);
        drawLine(rowNum, colNum);

        f.onMouseMove(event->mouseMove(event));
        f.onClick(event->mouseClick(event));
    }

    private void mouseMove(MouseMotionEvent event){
    }
       

    private void mouseClick(MouseButtonEvent event){
        makeSound("./src/soundfx/ding dong.wav");
        Point p = event.getPosition();
        if (p.getY()>offSet){
            GraphicsObject o = f.getElementAt(p);
            if (o!=null){
                int row = (int)Math.floor(p.getX()/squareSize);
                int col = (int)Math.floor((p.getY()-offSet)/squareSize);
                System.out.println(row + "     "+ col);
            }
        }

        
    }
    private void drawLine(int rowNum, int colNum){
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
        char[][] board = new char[3][3];
        WindowCanvas test = new WindowCanvas(board);

    }
}