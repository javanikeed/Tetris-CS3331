package view;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



import model.Tetromino;
import model.Tetromino.TetrominoEnum;
import view.animation.AnimationApplet;

/**
 * Creates main user interface. See AnimationApplet to see what is being inherited.
 * @author Emilio Ramirez
 * @author Jazmin I. Paz
 * @version 1.2
 * @since 2019-07-05
 *
 */

@SuppressWarnings("serial")
public class TetrisUI extends AnimationApplet implements Observer {
    private Color bgColor = Color.blue;
    private Color gridColor = Color.white;
	private ActionListener actionListener;
    public static TetrominoEnum curTet; 
    public static Tetromino curTetromino; 
    

    public static int xPos;
    public static int yPos;
    public static int score = 0;
    
    public static TetrominoEnum nextTet;
    public static Tetromino nextTetromino; 
    public static int x;
	public static int y;

	public TetrisUI(){
		return;
	}
	
	/**
	 * Initializes board dimensions and x and y coordinates for the grid
	 */
	public void init() {
        super.init();
        start();
        dim = getSize();
        x = 25;
        y = 25;
        
        

		
	}
	/**
	 * sets background to blue and to the default size
	 * @param g
	 */
    private void setBackground(Graphics g) {
    	g.setColor(bgColor);
    	g.fillRect(0, 0, dim.width, dim.height);
    }
    
    /**
	 * draws the window where next tetromino will be displayed
	 * @param g
	 */
    private void setNextTetrominoWindow(Graphics g) {
    	g.setColor(Color.white);
    	g.drawRect(350, 40, 175, 150);
    }
    /**
     * creates the 20x10 grid with dimensions 600x300.
     * @param g
     * @param x
     * @param y
     */
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
    /**
     * sets it onto the background
     * @param g
     */
    private void setTetrisGrid(Graphics g) {
    	g.setColor(gridColor);
        g.fillRect(x, y, 300, 600);
        drawGrid(g, x, y);
    }
    
    /**
     * sets lines, score and level, also "next tetromino" text
     * @param g
     */
    private void setGameText(Graphics g) {
    	g.setColor(Color.white);
        g.setFont(new Font("Helvetica", Font.PLAIN, 15));
        g.drawString("Next Tetromino: ", 350, 220);
        g.drawString("Score: ", 350, 300);
        g.drawString("Level: ", 350, 350);
        g.drawString("Lines Cleared: ", 350, 400);
    }
    
    /**
     * draws the tetromino (4 squares) onto the grid
     * @param g
     */
    private void drawTetromino(Graphics g) {
        for(int i=0; i<4; i++) {
        		g.setColor(TetrisUI.getTetrominoColor(curTet));
        		g.fillRect(((int) curTetromino.sqrCoords[i][0]+xPos)*30 -4,((int) curTetromino.sqrCoords[i][1]+yPos)*30 -4, 30, 30);
        		
        	}
    }
    /**
     * draws next tetromino in the box
     * @param g
     */
    private void drawNextTetromino(Graphics g) {
        for(int i=0; i<4; i++) {
        		g.setColor(TetrisUI.getTetrominoColor(nextTet));
        		g.fillRect(((int)nextTetromino.sqrCoords[i][0] * 30)+350, ((int)nextTetromino.sqrCoords[i][1]*30)+ 38, 30, 30);
        		
        	}
    }
    /**
     * Draws the score onto the board
     * @param g
     */
    private void drawScore(Graphics g) {
    	g.setColor(Color.blue); 
    	g.fillRect(395, 285, 60, 20);
    	g.setColor(Color.white);
    	g.setFont(new Font( "Helvetica", Font.PLAIN, 15));
    	g.drawString(""+score, 420, 300);
    }

	public TetrisUI(String[] args) {
		super(args);
	}

	/**
	 * This will be called based on the timer
	 */
	public void periodicTask()
	{
		repaint();
	}
	
	/**
	 * Here goes what is going to be drawn on screen
	 * @param g
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
        drawScore(g);
        

        
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
	
	/**Initializes the menu bar */
	public JPanel createUI() { 
	    JPanel home = new JPanel();
	    home.setLayout(new BorderLayout());
	    home.add(this, "Center");
	    home.add(newMenuBar(), "North");
	    return home;
	} 
	/** Creates menu bar that displays new game, controls, how to play and exit.*/  
	public JMenuBar newMenuBar() {
	    JMenuBar menuBar = new JMenuBar();
	    JMenu menuButton = new JMenu("Game");
	    JMenuItem newGameMenu = new JMenuItem("Begin New Game");
	    newGameMenu.setBackground(Color.PINK);
	    newGameMenu.setForeground(Color.WHITE);
	    newGameMenu.addActionListener(this.actionListener);
	    JMenuItem instructionsMenu = new JMenuItem("Game Controls");
	    instructionsMenu.setBackground(Color.PINK);
	    instructionsMenu.setForeground(Color.WHITE);
	    instructionsMenu.addActionListener((ActionListener) new ActionListener(){public void actionPerformed(ActionEvent e){
	            JDialog instructions = new JDialog();
	            instructions.setTitle("Tetris Control Keys to Play");
	            instructions.setSize(400, 200); //sets dimensions of instructions box
	            JTextArea instructionsText = new JTextArea(
	            		"Left Arrow Key: Move block to the left.\n" 
	            		+ "Right Arrow Key: Move block to the right.\n"
	            		+ "Down Arrow Key: Move block down.\n"
	            		+"\"Z\" Key: Rotate block counterclockwise.\n"
	            		+  "\"C\" Key: Rotate block clockwise.\n"
	            		+ "Esc Key: Pause/Unpause the game.\n");
	            instructionsText.setBackground(Color.PINK);
	            instructionsText.setForeground(Color.WHITE);
	            instructionsText.setFont(new Font( "Helvetica", Font.PLAIN, 20));
	            instructions.add(new JScrollPane(instructionsText));
	            instructions.setVisible(true);
	          }});
	    JMenuItem howToPlay = new JMenuItem("How to Play");
	    howToPlay.setBackground(Color.PINK);
	    howToPlay.setForeground(Color.WHITE);
	    howToPlay.addActionListener((ActionListener) new ActionListener(){public void actionPerformed(ActionEvent e){
            JDialog howToPlayInstructions = new JDialog();
            howToPlayInstructions.setTitle("Instruction Manual");
            howToPlayInstructions.setSize(400, 200); //sets dimensions of instructions box
            JTextArea howToPlayInstructionsText = new JTextArea(
            		"Goal of the game is to make full horizontal\n" 
            		+ "lines with different shaped tetrominos that\n"
            		+ "fall into the game area. Full lines disappear\n"
            		+"and provide points. The more lines you make, the\n"
            		+ "more points you earn. When tetrominos reach the\n"
            		+ "top of the board, the game is over and you lose.\n");
            howToPlayInstructionsText.setBackground(Color.PINK);
            howToPlayInstructionsText.setForeground(Color.WHITE);
            howToPlayInstructionsText.setFont(new Font( "Helvetica", Font.PLAIN, 20));
            howToPlayInstructions.add(new JScrollPane(howToPlayInstructionsText));
            howToPlayInstructions.setVisible(true);
          }});
	    
	    JMenuItem exit = new JMenuItem("Exit");
	    exit.setBackground(Color.PINK);
	    exit.setForeground(Color.WHITE);
	    
	    menuButton.add(newGameMenu);
	    menuButton.add(instructionsMenu);
	    menuButton.add(howToPlay);
	    menuButton.add(exit);
	    
	    menuBar.add(menuButton);
	    return menuBar;
	  }

}
