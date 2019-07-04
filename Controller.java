/**
 *
 */


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Manages all events between View (GUI) and Model (Game State)
 * @author epadilla2
 *
 */
public class Controller extends Board implements KeyListener {
	
	Tetromino pieces = new Tetromino();
	Tetromino.TetrominoEnum val = Tetromino.TetrominoEnum.getRandomTetromino();
	public int[] currentPiece = pieces.get_tetromino(val);
	Tetromino.TetrominoEnum val_next = Tetromino.TetrominoEnum.getRandomTetromino();
	public int[] nextPiece = pieces.get_tetromino(val_next);
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
            	super.moveTetrominoDown();
                break;
            case KeyEvent.VK_LEFT:
            	//moveTetrominoLeft();
                break;
            case KeyEvent.VK_RIGHT :
            	//moveTetrominoRight();
                break;
            case KeyEvent.VK_Z:
            	pieces.rotateLeft();
                break;
            case KeyEvent.VK_C:
            	pieces.rotateRight();
                break;
            default:
                break;
        }
    }
    public void update(){
    }
    
    
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
