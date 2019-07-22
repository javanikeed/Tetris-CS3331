
package view;

import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import model.GameState;
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
	private ActionListener actionListener;
	public GameState currentGame;
    private Color bgColor = Color.blue;
    private Color gridColor = Color.white;
    public static  TetrominoEnum curTet; // = TetrominoEnum.getRandomTetromino();
    public  static Tetromino curTetromino; // = new Tetromino(curTet);
    public char[][] board = new char[21][11];
    public static  int xPos = 4;
    public static  int yPos = -1;
    public static  int score = 0;
    public  int level = 0;
    public  int linesCleared = 0;
    
    public static TetrominoEnum nextTet;
    public static Tetromino nextTetromino; 
    public  int x;
	public  int y;
	public boolean gamePaused = false;

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
	public void start() {
		super.start();
		repaint();
		this.gamePaused = false;
	}
	public void stop() {
		super.stop();
		repaint();
		this.gamePaused  = true;
	}
	
	public boolean gamePaused() {
		return this.gamePaused();
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
        g.drawString("Score: "+ score, 350, 300);
        g.drawString("Level: " + level, 350, 350);
        g.drawString("Lines Cleared: "+ linesCleared, 350, 400);
    }
    
    //draws the tetromino (4 squares) onto the grid
    private void drawTetromino(Graphics g) {
        for(int i=0; i<4; i++) {
        		g.setColor(TetrisUI.getTetrominoColor(curTet));
        		g.fillRect(((int) curTetromino.sqrCoords[i][0]+xPos)*30 +26,((int) curTetromino.sqrCoords[i][1]+yPos)*30 +26, 29, 29);
        		
        	}
    }
    
    private void drawCurrentBoard(Graphics g) {
    	for( int i = 0; i < 20 ; i++) {
    		for(int j = 0; j < 10 ; j++) {
    			g.setColor(TetrisUI.getTetrominoColor(board[i][j]));
    			g.fillRect(j*30 +x+1 , i*30 +y+1 , 29, 29);
    		}
    	}
    }
    
    //draws next tetromino in the box
    private void drawNextTetromino(Graphics g) {
        for(int i=0; i<4; i++) {
        		g.setColor(TetrisUI.getTetrominoColor(nextTet));
        		g.fillRect(((int)nextTetromino.sqrCoords[i][0] * 30)+350, ((int)nextTetromino.sqrCoords[i][1]*30)+ 38, 30, 30);
        		
        	}
    }
    private void drawScore(Graphics g) {
    	g.setColor(Color.blue); //might need to change to blue
    	g.fillRect(395, 285, 60, 20);
    	g.setColor(Color.white);
    	g.setFont(new Font( "Helvetica", Font.PLAIN, 15));
    	g.drawString(""+score, 420, 300);
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
		
		repaint();

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
        drawCurrentBoard(g);
        
        //write text to UI
        setGameText(g);
//        drawScore(g);
        


        //draw current tetromino to board
        drawTetromino(g);
        drawNextTetromino(g);

        

    
	}

	/**
	 * Return a color based on the tetromino type
	 * @param tetrominoEnum
	 * @return
	 */
	private static  Color getTetrominoColor(TetrominoEnum tetrominoEnum)
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
	
	
	private static  Color getTetrominoColor(char squareColor)
	{
		Color color = null;
		switch (squareColor)
		{
		case 'I':
			color = Color.RED; break;
		case 'J':
			color = Color.GREEN; break;
		case 'L':
			color = Color.PINK; break;
		case 'O':
			color  = Color.CYAN; break;
		case 'S':
			color = Color.MAGENTA; break;
		case 'Z':
			color = Color.YELLOW; break;
		case 'T':
			color = Color.ORANGE; break;
		default:
			color =  Color.WHITE; break;
		}//end switch
		return color;
	}
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
	    home.add(newToolBar(), "South");
	    return home;
	} 
	/** Creates menu bar that displays new game, controls, how to play and exit.*/  
	public JMenuBar newMenuBar() {
	    JMenuBar menuBar = new JMenuBar();
	    JMenu menuButton = new JMenu("Game");
	    JMenuItem newGameMenu = new JMenuItem("Begin New Game");
	    newGameMenu.setBackground(Color.WHITE);
	    newGameMenu.setForeground(Color.BLACK);
	    newGameMenu.addActionListener(this.actionListener);
	    JMenuItem instructionsMenu = new JMenuItem("Game Controls");
	    instructionsMenu.setBackground(Color.WHITE);
	    instructionsMenu.setForeground(Color.BLACK);
	    instructionsMenu.addActionListener((ActionListener) new ActionListener(){public void actionPerformed(ActionEvent e){
	            JDialog instructions = new JDialog();
	            instructions.setTitle("Tetris Control Keys to Play");
	            instructions.setSize(320, 135); //sets dimensions of instructions box
	            JTextArea instructionsText = new JTextArea(
	            		"Left Arrow Key: Move block to the left.\n" 
	            		+ "Right Arrow Key: Move block to the right.\n"
	            		+ "Down Arrow Key: Move block down.\n"
	            		+"\"Z\" Key: Rotate block counterclockwise.\n"
	            		+  "\"C\" Key: Rotate block clockwise.\n"
	            		+ "Esc Key: Pause/Unpause the game.\n");
	            instructionsText.setBackground(Color.WHITE);
	            instructionsText.setForeground(Color.BLACK);
	            instructionsText.setFont(new Font( "Helvetica", Font.PLAIN, 14));
	            instructions.add(new JScrollPane(instructionsText));
	            instructions.setVisible(true);
	          }});
	    JMenuItem howToPlay = new JMenuItem("How to Play");
	    howToPlay.setBackground(Color.WHITE);
	    howToPlay.setForeground(Color.BLACK);
	    howToPlay.addActionListener((ActionListener) new ActionListener(){public void actionPerformed(ActionEvent e){
            JDialog howToPlayInstructions = new JDialog();
            howToPlayInstructions.setTitle("Instruction Manual");
            howToPlayInstructions.setSize(330, 140); //sets dimensions of instructions box
            JTextArea howToPlayInstructionsText = new JTextArea(
            		"Goal of the game is to make full horizontal lines \n" 
            		+ "with different shaped tetrominos that fall into \n"
            		+ "the game area. Full lines disappear and provide\n"
            		+" points. The more lines you make, the more points\n"
            		+ "you earn. When tetrominos reach the top of the\n"
            		+ "board, the game is over and you lose.\n");
            howToPlayInstructionsText.setBackground(Color.WHITE);
            howToPlayInstructionsText.setForeground(Color.BLACK);
            howToPlayInstructionsText.setFont(new Font( "Helvetica", Font.PLAIN, 14));
            howToPlayInstructions.add(new JScrollPane(howToPlayInstructionsText));
            howToPlayInstructions.setVisible(true);
          }});
	    
	    JMenuItem exit = new JMenuItem("Exit");
	    exit.setBackground(Color.WHITE);
	    exit.setForeground(Color.BLACK);
	    
	    menuButton.add(newGameMenu);
	    menuButton.add(instructionsMenu);
	    menuButton.add(howToPlay);
	    menuButton.add(exit);
	    
	    menuBar.add(menuButton);
	    return menuBar;
	}
	public JToolBar newToolBar() {
		JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
		return toolBar;
	}
}
	
