package controller;
/**
 * 
 */



import model.GameState;
import model.NetworkAdapter;
import model.NetworkMessageListener;
import model.Tetromino;
import model.Tetromino.TetrominoEnum;
import view.TetrisUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 * Manages all events between View (GUI) and Model (Game State)
 * @author epadilla2
 *
 */

public class Controller implements KeyListener {
	public static GameState currentGame;
	public TetrisUI currentUI;
	public GameState gameState;
	public Controller(){
	}
	public  void init() { // need to create new instance of both view and game state
		currentGame = new GameState();
		currentGame.board_init();
		TetrisUI.curTet = currentGame.currentType;
		TetrisUI.curTetromino = currentGame.currentTetromino;
		currentUI.xPos = currentGame.currentXCord;
		TetrisUI.nextTet = currentGame.nextType;
		TetrisUI.nextTetromino = currentGame.nextTetromino;
		return;
	}
	public void updateUi() {
		TetrisUI.curTetromino = currentGame.currentTetromino;
		TetrisUI.curTet = currentGame.currentType;
		currentUI.xPos = currentGame.currentXCord;
//		curUi.yPos = curGame.currentYCord;
		currentUI.score = currentGame.getScore();
		currentUI.level = currentGame.getLevel();
		currentUI.linesCleared = currentGame.getTotalLinesCleared();
//		curUi.board = curGame.board;
		currentUI.repaint();
	}
	/**
	 * Defines action when a key is pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if  (keyCode == KeyEvent.VK_ESCAPE)
		{
			/*if (currentUI.gamePaused() == true)
				currentUI.stop();
			else
				currentUI.start(); */
			return;
			
		}
		
		switch(keyCode) 
		{ 
        case KeyEvent.VK_DOWN:
        	GameState.moveTetrominoDown();
        	if(!currentGame.validatePosition()) {
        		currentGame.moveTetrominoUp();
        		currentGame.collision();
        		if(!currentGame.validatePosition()) {
        			currentGame.setIsGameActive(false);
        		}
        		TetrisUI.nextTet = currentGame.nextType;
        		TetrisUI.nextTetromino = currentGame.nextTetromino;
        		
        	}
        	updateUi();
        	currentGame.setScore(currentGame.getScore()+5);
            break;
        case KeyEvent.VK_LEFT:
    		currentGame.moveTetrominoLeft();
        	if(!currentGame.validatePosition()) {
        		currentGame.moveTetrominoRight();
        	}
        	updateUi();
            break;
        case KeyEvent.VK_RIGHT :
        	currentGame.moveTetrominoRight();
        	if(!currentGame.validatePosition()) {
        		currentGame.moveTetrominoLeft();
        	}
        	updateUi();
            break;
        case KeyEvent.VK_Z:
        	currentGame.currentTetromino.rotateLeft();
        	if(!currentGame.validatePosition()) {
        		currentGame.currentTetromino.rotateRight();
        	}
        	updateUi();
            break;
        case KeyEvent.VK_C:
        	currentGame.currentTetromino.rotateRight();
        	if(!currentGame.validatePosition()) {
        		currentGame.currentTetromino.rotateLeft();
        	}
        	updateUi();
        	break;
		default:
			break;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() instanceof javax.swing.JMenuBar)
	    {
	      newGameClicked();
	      System.out.println("This works");
	    }
	    
	   /* if (e.getSource() == currentUI.network.hostButton){
	      hostClicked();
	    }
	    if (e.getSource() == currentUI.network.connectButton) {
	      connectClicked();
	    } */
	  }
	
	
	private void newGameClicked() {
	    this.currentUI.stop();
	    String[] choices = { "Singleplayer", "Mulitplayer"};
	    String numPlayers = (String) JOptionPane.showInputDialog(null, "Please choose number of players:", "Players",
	    		JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]); 
	    //System.out.println(input);
	    
	    
	    if (numPlayers == null) {
	      return;
	    }
	    if ("Singleplayer".equals(numPlayers)) {
	      
	      this.gameState.singleplayerGame();
	      this.currentUI.resetTimer();
	      this.currentUI.start();
	    
	    }
	    else {
	      
	      String name = this.gameState.playerName;
	      name = (name == null) ? "" : name;
	      while ("".equals(name))
	        name = JOptionPane.showInputDialog(null, "What's your name?", "Player Name", 1); 
	      if (name == null)
	        return; 
	      this.gameState.playerName = name;
	      //this.currentUI.setModel(this.gameState);
	      this.currentUI.network.setVisible(true);
	    } 
	  }
	
	
	
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}