package model;

import model.Tetromino.TetrominoEnum;

/**
 * Model class from MVC. This class contains the Tetris logic but no GUI information. 
 * Model must not know anything about the GUI and Controller.
 * @author epadilla2
 *
 */
public class GameState extends java.util.Observable{

   private int level;
   private int score;
   private int totalLinesCleared;
   private boolean isGameActive;
   public static char[][] board;   // board with final positions
   public Tetromino.TetrominoEnum currentType;
   public Tetromino currentTetromino;          // current Tetromino
   public Tetromino.TetrominoEnum nextType;
   public Tetromino nextTetromino;
   public int currentXCord;
   public static int currentYCord;
   private boolean multiplayerGame;
   public String playerName;
   
   public GameState()
   {

   }
   
 
   /**
    * Initializes board at the beginning of the game
    * by creating the data structure necessary.
    * */
   public void board_init(){
       board = new char[21][11]; 
       currentType = TetrominoEnum.getRandomTetromino();
       currentTetromino = new Tetromino(currentType);
       nextType = TetrominoEnum.getRandomTetromino();
       nextTetromino = new Tetromino(nextType);
       currentXCord = 2;
       currentYCord = -2;
       level = 1;
       score = 1;
       totalLinesCleared = 0;
       isGameActive = true;

   }
   
   public void newTetromino() {
       currentType = nextType;
       currentTetromino = nextTetromino;
       nextType = TetrominoEnum.getRandomTetromino();
       nextTetromino = new Tetromino(nextType);	   
   }
   
   
   public void place_tetromino(){  
       if(validateTetrominoinBounds()){
           for(int i = 0; i < currentTetromino.sqrCoords.length;i++){
               for (int j = 0; j < currentTetromino.sqrCoords[i].length-1; j++){
                   board[currentYCord + (int)currentTetromino.sqrCoords[i][j+1]][currentXCord +(int)currentTetromino.sqrCoords[i][j]] = currentTetromino.getShape();
               }													
           }
       }
   }
   

   /**
    * Returns if game is active
    * @return
    */
   public boolean isGameActive(){
       return isGameActive;
   }
   
   /**
    * Returns score
    * @return
    */
   public int getScore(){
       return score;
   }
   
   /**
    * Return current level
    * @return
    */
   public int getLevel(){
       return level;
   }
   
   public void setIsGameActive(boolean isGameActive) {
	   this.isGameActive = isGameActive;
   }
   
   /**
    * Returns totalLines Cleared
    */
   public int getTotalLinesCleared() {
	   return totalLinesCleared;
   }
   
   public void setLevel(int level) {
	   this.level = level;
   }
   
   public void setScore(int num) {
	   this.score = num;
   }
   
   public void setTotalLinesCleared(int linesCleared) {
	   totalLinesCleared = linesCleared;
   }
   
   
   public void moveTetrominoUp() {
	   currentYCord-=1;
   }
   /**
    * Moves tetromino down
    */
   public static void moveTetrominoDown(){
       currentYCord ++;
       
   }
   
   public void moveTetrominoRight(){
   		 currentXCord +=1;    
   }
 
   public void moveTetrominoLeft()
   {
  		 currentXCord -=1;
   }
   
   /**
    * Checks to see if "row" is full
    */
   public Boolean lineFull(int row) {
	   for(int i =0; i<10;i++) {
		   if( board[row][i] == 0) {
			   return false;
		   }
	   }
	   return true;
	   
   }
   
   /**
    * Removes row  and translates everything above that row downwards
    */
   public void RemoveLine(int row) {
	   for(int i =row; i>0; i--) {
		   for(int j = 9; j >= 0; j--) {
			   board[i][j] =board[i-1][j];
		   }
	   }
	   for(int j =9; j>=0;j--) {
		   board[0][j] = 0;
	   }
	   setChanged();
	   notifyObservers();   
   }
  
   /**
    * Returns if tetromino is within bounds
    * 
    */
   public boolean validateTetrominoinBounds() // checks to see if tetromino is in bounds
	{
       for(int i = 0; i < currentTetromino.sqrCoords.length;i++){
           for (int j = 0; j < currentTetromino.sqrCoords[i].length-1; j++){
              if((currentTetromino.sqrCoords[i][j])+ currentXCord >9||currentTetromino.sqrCoords[i][j] + currentXCord < 0){ // verify that is bounded x
            	  return false;
              }
              if(currentTetromino.sqrCoords[i][j+1]+currentYCord > 19 ||currentTetromino.sqrCoords[i][j+1] +currentYCord <0 ) {
            	  return false;
              }
           }
       }
		return true;
   }
   /**
    * Returns if current position on Board is occupied or not
    */
   public boolean validateAvailableSpace() {
	 int j = 0;
     for(int i = 0; i<4;i++) {
    	 if( board[currentYCord + (int)currentTetromino.sqrCoords[i][j+1]][currentXCord +(int)currentTetromino.sqrCoords[i][j]] != 0){
    		 return false;
    	 }
     }
	 return true;
   }
   
   /**
    * Checks to see that current tetromino position is valid
    */
   public boolean validatePosition() {
	   if(!validateTetrominoinBounds() || !validateAvailableSpace()) {
		   return false;
	   }
	   return true;
   }
   
   /**
    * does all necessary actions once a collision occurs
    */
   public void collision() {
		place_tetromino();
		int linesCleared = 0;
		for(int i = 0; i<20;i++) {
			if(lineFull(i)) {
				linesCleared++;
				RemoveLine(i);
			}
		}
		setTotalLinesCleared(getTotalLinesCleared()+linesCleared);
		switch(linesCleared) {
			case 1:
				setScore(getScore()+100);
				break;
			case 2:
				setScore(getScore()+300);
				break;
			case 3:
				setScore(getScore()+600);
				break;
			case 4:
				setScore(getScore()+1000);
				break;
			default:
				break;
		}
		currentXCord = 2;
		currentYCord = -2;
		newTetromino();
		
		   setChanged();
		   notifyObservers();  
		
   }
   
   public void singleplayerGame() {
	   board_init();
	   multiplayerGame = false;
   }
   public void multiplayerGame() {
	   board_init();
	   multiplayerGame = true;
   }
   
   
   public void setLocalPlayerName(String playerName) {
	    this.playerName = playerName;
	  }
   
}

