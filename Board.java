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
//     protected int[][] board;
//     Tetromino tetromino = new Tetromino(); // maybe cur tet
//    // maybe next tet
    public  char[][] board = new char[20][10];   // board with final positions
    public Tetromino currentTetromino;          // current Tetromino
    public int currentXCord;
    public int currentYCord;
    public Board()
    {

    }
    /**
     * Initializes board at the beginning of the game
     * by creating the data structure necessary.
     * */
    public void board_init(){
        board = new char[20][10];
    }
    
    public void place_tetromino(){
        if(validateTetrominoPosition()){
            for(int i = 0; i < currentTetromino.sqrCoords.length;i++){
                for (int j = 0; j < currentTetromino.sqrCoords[i].length-1; j++){
                    board[currentYCord + (int)currentTetromino.sqrCoords[i][j+1]][currentXCord +(int)currentTetromino.sqrCoords[i][j]] = currentTetromino.getShape();
                }
            }
        }
    }
    public void removeTetromino(){
        for(int i = 0; i < currentTetromino.sqrCoords.length;i++){
            for (int j = 0; j < currentTetromino.sqrCoords[i].length-1; j++){
                board[currentYCord + (int)currentTetromino.sqrCoords[i][j+1]][currentXCord +(int)currentTetromino.sqrCoords[i][j]] = 0;
                
            }
        }
    }    
    
    /**
     * This method prints the tetris board; supports updating.
     */
    public void printBoard() 
    { 
        System.out.print("\n         ");
        for (int i = 1; i <= 10;i++){
            System.out.print("   "+ i);
        }
        System.out.print("\n");
        for (int i = 0; i < 20; i++) {
            System.out.println("          +---+---+---+---+---+---+---+---+---+---+");
            if (i > 8){
                System.out.print(i+1 +"        |"); 
            }
            else{
                System.out.print(i+1 +"         |");
            }
            for (int j = 0; j < 10; j++) {
                if(board[i][j] == 0){
                    System.out.print(board[i][j] + "  |"); 
                }
                else{
                System.out.print(" " + board[i][j] + " |");
                } 
            }
            System.out.println();
        }   
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
        currentYCord += 1;
        
    }
    public void moveTetrominoRight()
    {
//    	while(Tetromino.b<20) {
    		 currentXCord +=1;
//    	}
       
    }
    public void moveTetrominoLeft()
    {
//    	while(Tetromino.b>-1) {
   		 currentXCord -=1;
//    	}
    }
    /**
     * Returns if this is a valid position
     * Still in very very basic state.. needs more work later.
     */
    public boolean validateTetrominoPosition()
	{
        for(int i = 0; i < currentTetromino.sqrCoords.length;i++){
            for (int j = 0; j < currentTetromino.sqrCoords[i].length-1; j++){
               if( board[currentYCord + (int)currentTetromino.sqrCoords[i][j+1]][currentXCord +(int)currentTetromino.sqrCoords[i][j]] != 0){
                   System.out.println("Invalid Postion");
                   return false;
               }
            }
        }
		return true;
    }
}
