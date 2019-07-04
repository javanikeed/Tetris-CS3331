

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

/**
 * Creates main user interface. See AnimationApplet to see what is being inherited.
 * @author epadilla2
 *
 */
@SuppressWarnings("serial")
public class  TetrisUI extends AnimationApplet{


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

    }
    /**
     * Here goes what is going to drawn on screen
     */
    protected void paintFrame(Graphics g)
    {
        
    }

    /**
     * Return a color based on the tetromino type
     * @param tetrominoEnum
     * @return
     */
    static Color getTetrominoColor(Tetromino.TetrominoEnum tetrominoEnum)
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
    public static void main(String[] args) {
        new TetrisUI(args).run();
    }
}//end TetrisUI class
