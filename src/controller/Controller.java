package controller;
/**
 * 
 */

import model.*;
import view.*;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Manages all events between View (GUI) and Model (Game State)
 * @author epadilla2
 *
 */
public class Controller implements KeyListener {
	public GameState curGame;
	public TetrisUI curUi;
	public Controller(){
	}
	public  void init() { // need to create new instance of both view and game state
		curGame = new GameState();
		curGame.board_init();
		curUi = new TetrisUI() ;
		curUi.init();
		curUi.curTet = curGame.currentType;
		curUi.curTetromino = curGame.currentTetromino;
		curUi.xPos = curGame.currentXCord;
		curUi.yPos = curGame.currentYCord;
		curUi.nextTet = curUi.curTet.getRandomTetromino();
		curUi.nextTetromino = new Tetromino(curUi.nextTet);
		return;
	}
	public void updateUi() {
//		curUi.curTet = curGame.currentType;
		curUi.curTetromino = curGame.currentTetromino;
		curUi.xPos = curGame.currentXCord;
		curUi.yPos = curGame.currentYCord;
	}
	/**
	 * Defines action when a key is pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		//pause
		if  (keyCode == KeyEvent.VK_ESCAPE)
		{
			//your code goes here
			return;
		}

		switch(keyCode) 
		{ 
        case KeyEvent.VK_DOWN:
        	curGame.moveTetrominoDown();
        	updateUi();
        	curGame.setScore(curGame.getScore()+5);
            break;
        case KeyEvent.VK_LEFT:
        	curGame.moveTetrominoLeft();
            break;
        case KeyEvent.VK_RIGHT :
        	curGame.moveTetrominoRight();
            break;
        case KeyEvent.VK_Z:
        	curGame.currentTetromino.rotateLeft();
            break;
        case KeyEvent.VK_C:
        	curGame.currentTetromino.rotateRight();
            break;
		default:
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
