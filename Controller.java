/**
 * Controller class.
 * @author Emilio Ramirez
 * @author Jazmin I. Paz
 * @version 1.2
 * @since 2019-07-21
 */
package controller;

import model.*;
import model.Tetromino.TetrominoEnum;
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
		curUi.board = curGame.board;
		curUi.curTet = curGame.currentType;
		curUi.curTetromino = curGame.currentTetromino;
		curUi.xPos = curGame.currentXCord;
		curUi.yPos = curGame.currentYCord;
		curUi.nextTet = curUi.curTet.getRandomTetromino();
		curUi.nextTetromino = new Tetromino(curUi.nextTet);
		return;
	}
	/**updates UI*/
	public void updateUi() {
		curUi.curTetromino = curGame.currentTetromino;
		curUi.xPos = curGame.currentXCord;
		curUi.yPos = curGame.currentYCord;
		curUi.score = curGame.getScore();
		curUi.level = curGame.getLevel();
		curUi.linesCleared = curGame.getTotalLinesCleared();
		curUi.board = curGame.board;
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
			System.out.println("Game Paused. (gets stuck/needs fixing)");
			if (curUi.gamePaused == true) {
				curUi.start();
			}
			if (curUi.gamePaused == false) {
				curUi.stop();
			}
			
			return;
		}
		
	if(curUi.gamePaused == false){
		switch(keyCode) 
		{ 
        case KeyEvent.VK_DOWN:
        	curGame.moveTetrominoDown();
        	if(!curGame.validatePosition()) {
        		curGame.moveTetrominoUp();
        		curGame.collision();
        		curGame.currentTetromino = TetrisUI.nextTetromino; // maybe change how this information is exchanged
        		TetrisUI.curTet = curUi.nextTet;
        		if(!curGame.validatePosition()) {
        			//game ends
        		}
        		TetrisUI.nextTet = TetrominoEnum.getRandomTetromino();
        		TetrisUI.nextTetromino = new Tetromino(TetrisUI.nextTet);
        		
        	}
        	updateUi();
        	curGame.setScore(curGame.getScore()+5);
            break;
        case KeyEvent.VK_LEFT:
    		curGame.moveTetrominoLeft();
        	if(!curGame.validatePosition()) {
        		curGame.moveTetrominoRight();
        	}
        	updateUi();
            break;
        case KeyEvent.VK_RIGHT :
        	curGame.moveTetrominoRight();
        	if(!curGame.validatePosition()) {
        		curGame.moveTetrominoLeft();
        	}
        	updateUi();
            break;
        case KeyEvent.VK_Z:
        	curGame.currentTetromino.rotateLeft();
        	if(!curGame.validatePosition()) {
        		curGame.currentTetromino.rotateRight();
        	}
        	updateUi();
            break;
        case KeyEvent.VK_C:
        	curGame.currentTetromino.rotateRight();
        	if(!curGame.validatePosition()) {
        		curGame.currentTetromino.rotateLeft();
        	}
        	updateUi();
        	break;
		default:
			break;
		}
	}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
