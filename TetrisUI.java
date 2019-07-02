

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

    private Color color = Color.GREEN;
    private int radius = 30;
    private int x, y;
    private int dx = 0, dy = -30;


    @Override
    public void init() {
        super.init();
        start();
        dim = getSize();
        x = 75;
        y = 25;
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
        if (g==null)
            return;
        // fill the background
        g .setColor(Color.black);
        g .fillRect(0, 0, dim.width, dim.height);

        // adjust the position of the ball
        if (x < radius || x > dim.width - radius) {
            dx = -dx;
        }
        if (y < radius || y > dim.height - radius) {
            dy = -dy;
        }
        x += dx;
        y += dy;

        // draw the ball and dump the off-screen image
        int x_coord = 75, y_coord = 25;

        g.setColor(Color.MAGENTA);
        g.fillRect(105, 25-dy, 30, 30);
        g.fillRect(105, 55-dy, 30, 30);
        g.fillRect(105, 85-dy, 30, 30);
        g.fillRect(75, 85-dy, 30, 30);
    }

    /**
     * Return a color based on the tetromino type
     * @param tetrominoEnum
     * @return
     */
    private Color getTetrominoColor(Tetromino.TetrominoEnum tetrominoEnum)
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
