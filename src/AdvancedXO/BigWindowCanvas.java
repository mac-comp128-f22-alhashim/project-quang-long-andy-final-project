package AdvancedXO;

import java.awt.Color;
import java.io.File;
import java.time.OffsetTime;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.*;
import edu.macalester.graphics.events.*;




// This component attempts to create a window that visualizes the board
// based on a 2d arrary imput.

public class BigWindowCanvas {
    
    private BigBoard board;
    private CanvasWindow f; // "frame"
    private int squareSize = 70;
    private int offSet = squareSize*2; // top vertical space
    private int lw = (int)squareSize/20; // line width 
    private int winwidth;
    private int winheight;
    private boolean isPlayer0 = true;
    private GraphicsText turnLabel;
    private Button t;
    private boolean isWon = false;
    private boolean color = true; // true = default, white

    private GraphicsGroup uiGroup;

    private BigWindowCanvas(BigBoard board){
        this.board = board;
        
        int rowNum = this.board.getSize();
        int colNum = this.board.getSize();

        winwidth = colNum * squareSize+2*lw;
        winheight = offSet + rowNum * squareSize + 2*lw;


        f = new CanvasWindow("TTT", winwidth, winheight);
        Button x = new Button("getSize");
        x.onClick(()->diagprint());
        f.add(x,0,50);

        setUpTest(rowNum, colNum);
        f.onClick(event->mouseClick(event));
    }

    void diagprint(){
        System.out.println(f.getHeight()+" .  " + f.getWidth());
        reposition();
    }

    private void togglePlayer(){
        turnLabel.setFont("Helvetica",FontStyle.BOLD_ITALIC, winwidth/8);
        if (isPlayer0 == true) {
            isPlayer0 = false; 
            turnLabel.setText("O's turn");
            turnLabel.setFillColor(new Color(51,186,215,220));
        }
        else {
            isPlayer0=true; 
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
            double x = row*squareSize+lw+squareSize*.05;
            double y = offSet+col*squareSize+lw;
            Image a = new Image(x, y,"./imgs/X.png");
            a.setMaxWidth(squareSize*.9);
            f.add(a);
        }
        else{
            double x = row*squareSize+lw+squareSize*.05;
            double y = offSet+col*squareSize+lw;
            Image a = new Image(x, y,"./imgs/O.png");
            a.setMaxWidth(squareSize*.9);
            f.add(a);
        }
    }

    private void checkWin(){
        if (board.checkWin() == 1){
            makeSound("./res/soundfx/win.wav");

            f.remove(turnLabel);
            turnLabel = new GraphicsText("Player O won",winwidth/5,offSet/1.5);
            turnLabel.setFont("Signpainter,American TypeWriter, Tahoma", FontStyle.BOLD, winwidth/8);
            turnLabel.setFillColor(new Color(0,119,36,255));
            turnLabel.setText("Player O won");
            f.add(turnLabel);

            isWon = true;
        } else if (board.checkWin() == 0) {
            makeSound("./res/soundfx/win.wav");

            f.remove(turnLabel);
            turnLabel = new GraphicsText("Player X won",winwidth/5,offSet/1.5);
            turnLabel.setFont("Signpainter,American TypeWriter, Tahoma", FontStyle.BOLD, winwidth/8);
            turnLabel.setFillColor(new Color(0,119,36,255));
            turnLabel.setText("Player X won");
            f.add(turnLabel);

            isWon = true;
        }
        else{
            if (board.getFilledCount()!= board.getSize()*board.getSize() ){
                togglePlayer();
            }
            else{
                makeSound("./res/soundfx/fail.wav");
                f.remove(turnLabel);
                turnLabel = new GraphicsText("Draw",winwidth/5,offSet/1.5);
                turnLabel.setFont("Signpainter,American TypeWriter, Tahoma", FontStyle.BOLD, winwidth/8);
                turnLabel.setText("Draw");
                turnLabel.setFillColor(new Color(192,0,0,220));
                f.add(turnLabel);

                isWon = true;
            }
        }
        if (isWon==true){
            Button b  = new Button("restart");
            b.setPosition(f.getCenter());
            b.setScale(100);
            b.onClick(()->restart());
            f.add(b,winwidth/2-b.getScaleX()/2,3*offSet/4);
        }
    }

    private void restart(){
        isWon=false;
        isPlayer0=true;
        f.removeAll();
        board.resetBoard();
        setUp(board.getSize(), board.getSize());
        makeSound("./res/soundfx/restart.wav");
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
                double x = i*squareSize+lw+squareSize*.05;
                double y = j*squareSize+offSet+.05*squareSize;
                Rectangle r = new Rectangle(x, y, squareSize*.9,squareSize*.9);
                r.setFillColor(new Color(101,177,51,30));
                r.setStroked(false);
                f.add(r);
            }
        }

        turnLabel = new GraphicsText("Begin",winwidth/4,offSet/1.5);
        turnLabel.setFont("Signpainter,American TypeWriter, Tahoma", FontStyle.BOLD, winwidth/8);
        if (color==true){
            turnLabel.setFillColor(new Color(0,0,0,230));
        }
        else  {turnLabel.setFillColor(new Color(226,226,226,230));}
        f.add(turnLabel);

        t = new Button("Change Color");
        t.onClick(()-> toggleColor());
        t.setScale(10,10);
        f.add(t,0,10);
    }



    private void toggleColor(){
        makeSound("./res/soundfx/restart.wav");
      
        if (color==true){
            f.setBackground(new Color(0,0,0,220));
            if (turnLabel.getText().toLowerCase().equals("begin")){
                turnLabel.setFillColor(new Color(226,226,226,230));}
            color=false;
        }
        else{
            f.setBackground(new Color(255,255,255,255));
            if (turnLabel.getText().toLowerCase().equals("begin")){
                turnLabel.setFillColor(new Color(0,0,0,230));}
            color = true;
        }
    
    } 


    private void makeSound(String location){
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
        BigBoard a = new BigBoard(9);
        BigWindowCanvas test = new BigWindowCanvas(a);

    }

    private void setUpTest(int rowNum, int colNum){
        uiGroup = new GraphicsGroup();
        for (int i = 0; i<colNum+1 ; i++){
            //vertical
            Line line = new Line(i*squareSize+lw, offSet, i*squareSize+lw,winheight-3*lw);
            line.setStrokeWidth(lw);
            Color c = new Color(60,128,180,100);
            line.setStrokeColor(c);
            uiGroup.add(line);
        }
        
        for (int i = 0; i<rowNum+1;i++){
            //horizontal
            Line line = new Line(lw, offSet+i*squareSize,winwidth-lw,offSet+i*squareSize);
            line.setStrokeWidth(lw);
            Color c = new Color(60,128,180,100);
            line.setStrokeColor(c);
            uiGroup.add(line);
        }

        for (int i = 0; i<colNum; i++){
            for (int j = 0; j<rowNum; j++){
                double x = i*squareSize+lw+squareSize*.05;
                double y = j*squareSize+offSet+.05*squareSize;
                Rectangle r = new Rectangle(x, y, squareSize*.9,squareSize*.9);
                r.setFillColor(new Color(101,177,51,30));
                r.setStroked(false);
                uiGroup.add(r);
            }
        }

        f.add(uiGroup,0,0);

        turnLabel = new GraphicsText("Begin",winwidth/4,offSet/1.5);
        turnLabel.setFont("Signpainter,American TypeWriter, Tahoma", FontStyle.BOLD, winwidth/8);
        if (color==true){
            turnLabel.setFillColor(new Color(0,0,0,230));
        }
        else  {turnLabel.setFillColor(new Color(226,226,226,230));}
        f.add(turnLabel);

        t = new Button("Change Color");
        t.onClick(()-> toggleColor());
        t.setScale(10,10);
        f.add(t,0,10);
    }

    private void reposition(){
        uiGroup.setCenter(f.getCenter());
    }
}