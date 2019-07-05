package view;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import model.Tetromino;
import model.Tetromino.TetrominoEnum;
import view.animation.AnimationApplet;

/**
 * Creates main user interface. See AnimationApplet to see what is being inherited.
 * @author epadilla2
 *
 */
@SuppressWarnings("serial")
public class TetrisUI extends AnimationApplet implements Observer {
    private Color bgColor = Color.blue;
    private Color gridColor = Color.white;
    public static TetrominoEnum curTet; // = TetrominoEnum.getRandomTetromino();
    public static Tetromino curTetromino; // = new Tetromino(curTet);
    

    public static int xPos;
    public static int yPos;
    
    public static TetrominoEnum nextTet;
    public static Tetromino nextTetromino; 
    public static int x;
	public static int y;

	public TetrisUI(){
		return;
	}
	public void init() {
        super.init();
        start();
        dim = getSize();
        x = 25;
        y = 25;
        
        

		
	}

    //sets background to blue and to the default size
    private void setBackground(Graphics g) {
    	g.setColor(bgColor);
    	g.fillRect(0, 0, dim.width, dim.height);
    }
    
    //draws the window where next tetromino will be displayed
    private void setNextTetrominoWindow(Graphics g) {
    	g.setColor(Color.white);
    	g.drawRect(350, 40, 175, 150);
    }
    
    //creates the 20x10 grid with dimensions 600x300.
    private void drawGrid(Graphics g, int x, int y){
        g.setColor(Color.blue);
        int reset_x = x;
        for(int i=0;i<11;i++){
            g.fillRect(x, y, 1, 600);
            x+=30;
        }
        x = reset_x;
        for(int j=0;j<21;j++){
            g.fillRect(x, y, 300, 1);
            y+=30;
        }
    }
    
    //sets it onto the background
    private void setTetrisGrid(Graphics g) {
    	g.setColor(gridColor);
        g.fillRect(x, y, 300, 600);
        drawGrid(g, x, y);
    }
    
    //sets lines, score and level, also "next tetromino" text
    private void setGameText(Graphics g) {
    	g.setColor(Color.white);
        g.setFont(new Font("Helvetica", Font.PLAIN, 15));
        g.drawString("Next Tetromino: ", 350, 220);
        g.drawString("Score: ", 350, 300);
        g.drawString("Level: ", 350, 350);
        g.drawString("Lines Cleared: ", 350, 400);
    }
    
    //draws the tetromino (4 squares) onto the grid
    private void drawTetromino(Graphics g) {
        for(int i=0; i<4; i++) {
        		g.setColor(TetrisUI.getTetrominoColor(curTet));
        		g.fillRect(((int) curTetromino.sqrCoords[i][0]+xPos)*30 -4,((int) curTetromino.sqrCoords[i][1]+yPos)*30 -4, 30, 30);
        		
        	}
    }
    
    //draws next tetromino in the box
    private void drawNextTetromino(Graphics g) {
        for(int i=0; i<4; i++) {
        		g.setColor(TetrisUI.getTetrominoColor(nextTet));
        		g.fillRect(((int)nextTetromino.sqrCoords[i][0] * 30)+350, ((int)nextTetromino.sqrCoords[i][1]*30)+ 38, 30, 30);
        		
        	}
    }
	/**
	 * 
	 */
	public TetrisUI(String[] args) {
		super(args);
	}

	/**
	 * This will be called based on the timer
	 */
	public void periodicTask()
	{
//		repaint();
	}
	/**
	 * Here goes what is going to drawn on screen
	 */
	protected void paintFrame(Graphics g) 
	{
        if (g==null) {
        	return;
        }
        // fill the background
        setBackground(g);
        
        //next tetromino window
        setNextTetrominoWindow(g);
        
        // fill the grid
        setTetrisGrid(g);
        
        //write text to UI
        setGameText(g);
        

        
        //draw current tetromino to board
        drawTetromino(g);
        drawNextTetromino(g);

        

    
	}

	/**
	 * Return a color based on the tetromino type
	 * @param tetrominoEnum
	 * @return
	 */
	private static Color getTetrominoColor(TetrominoEnum tetrominoEnum)
	{
		Color color = null;
		switch (tetrominoEnum)
		{
		case I:
			color = Color.RED; break;
		case J:
			color = Color.GREEN; break;
		case L:
			color = Color.PINK; break;
		case O:
			color  = Color.CYAN; break;
		case S:
			color = Color.MAGENTA; break;
		case Z:
			color = Color.YELLOW; break;
		case T:
			color = Color.ORANGE; break;
		default:
			color =  Color.WHITE; break;
		}//end switch
		return color;
	}//end getTetrominoColor
	/**
	 * When there is a change on the model, the View (GUI) gets notified (this method is called)
	 */
	public void update(Observable obs, Object obj)
	{

	}
}//end TetrisUI class
