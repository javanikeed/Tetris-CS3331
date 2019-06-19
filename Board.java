/**
 * Jazmin I. Paz
 * Homework 1
 * 18 June 2019
 * CS3331 Advanced Object-Oriented Programming
 */
public class Board extends java.util.Observable{

    private int level;
    private int score;
    private boolean isGameActive;
    protected static int[][] board;
    Tetromino tetromino = new Tetromino();
    public Board()
    {

    }
    /**
     * Initializes board at the beginning of the game
     * by creating the data structure necessary.
     * */
    public static void board_init(){
        board = new int[20][10];
    }
    public static void place_tetromino(int[][] t){
        for(int i=0;i<4;i++){
            for(int j=0; j<4;j++){
                board[i][j] = t[i][j];
            }
        }
    }

    /**
     * This method prints the tetris board; supports updating.
     */
    public static void print_board(){
        for(int j=0;j<20;j++){
            System.out.println("+---+---+---+---+---+---+---+---+---+---+");
            for(int k=0;k<10;k++){
                if (k==0)
                    System.out.print("|");
                System.out.print(" "+ board[j][k]+" |");

            }
            System.out.println();
        }
        System.out.println("+---+---+---+---+---+---+---+---+---+---+");
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
        tetromino.b++;
        System.out.println("2Val of B: "+tetromino.b);
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