/**
 * Jazmin I. Paz
 * Homework 1
 * 19 June 2019
 * CS3331 Advanced Object-Oriented Programming
 */
public class Board extends java.util.Observable{

    private int level;
    private int score;
    private boolean isGameActive;
    protected int[][] board;
    Tetromino tetromino = new Tetromino();
    Board()
    {

    }
    /**
     * Initializes board at the beginning of the game
     * by creating the data structure necessary.
     * */
    public void board_init(){
        board = new int[20][10];
    }

    public void place_tetromino(int[][][] t , int val){
        for(int i=0;i<t.length;i++){
            for(int j=0; j<t[i].length;j++){
                for(int k=0;k<4;k++) {
                    board[i][j] = t[i][j][val];
                }

            }
        }
    }

    /**
     * This method prints the tetris board; supports updating.
     */
    public void print_board(){
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
        Tetromino.a += 1;

    }
    public void moveTetrominoRight()
    {
        while(Tetromino.b<20) {
            Tetromino.b +=1;
        }

    }
    public void moveTetrominoLeft()
    {
        while(Tetromino.b>-1) {
            Tetromino.b -=1;
        }
    }
    /**
     * Returns if this is a valid position
     * Still in very very basic state.. needs more work later.
     */
    public boolean validateTetrominoPosition()
    {
        if(Tetromino.b<0) {
            System.out.println("Violating left boundary.");
            return false;
        }
        if(Tetromino.b>9) {
            System.out.println("Violating right boundary.");
            return false;
        }
        if (Tetromino.a>19) {
            System.out.println("Violating lower boundary.");
            return false;
        }
        System.out.println("Valid Tetromino position.");
        return true;
    }
}