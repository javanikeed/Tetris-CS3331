
/**
 * Model class from MVC. This class contains the Tretris logic but no GUI information.
 * Model must not know anything about the GUI and Controller.
 * @author epadilla2
 *
 */
public class GameState extends java.util.Observable{

    private int level;
    private int score;
    private boolean isGameActive;
    Tetromino piece = new Tetromino();
    Controller controller = new Controller();
    public GameState()
    {

    }
    /**
     * Returns if game is active
     * @return
     */
    public boolean isGameActive()
    {
        return isGameActive;
    }
    /**
     * Returns score
     * @return
     */
    public int getScore()
    {
        return score;
    }
    /**
     * Return current level
     * @return
     */
    public int getLevel()
    {
        return level;
    }
    /**
     * Moves tetromino down
     */
    public void moveTetrominoDown()
    {
    
    }
    public void moveTetrominoRight()
    {
        //your code goes here
    }
    public void moveTetrominoLeft()
    {
        //your code goes here
    }
    /**
     * Returns if this is a valid position
     * @return
     */
    private boolean validateTetrominoPosition()
    {
        //your code goes here
        return true;
    }
}

