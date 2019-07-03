/**
 * Jazmin I. Paz
 * Homework 1
 * 19 June 2019
 * CS3331 Advanced Object-Oriented Programming
 */
public class Primary {
    Primary(){
   
    }
    public static void main(String[] args){
        Operations();
    }
    public static void Operations() {
    	Board b = new Board();
        Tetromino t = new Tetromino();
        b.board_init();
        System.out.println("Generating Random Tetromino...");
        Tetromino.TetrominoEnum val = Tetromino.TetrominoEnum.getRandomTetromino();
        b.currentTetromino = new Tetromino(val);
        System.out.println("Placing "+val+" tetromino in default position...");
        b.place_tetromino();
        System.out.println("Printing board...");
        b.printBoard();
        System.out.println();
        System.out.println("Moving tetromino down 1 space...");
        b.removeTetromino();
        b.moveTetrominoDown();
        b.place_tetromino();
        b.printBoard();
        // t.clear_t();
        // rand_tetromino = t.get_tetromino(val);
        // b.place_tetromino(rand_tetromino, Tetromino.c);
        // System.out.println("Printing board...");
        // b.print_board();
        // System.out.println("Moving tetromino down 1 space...");
        // b.moveTetrominoDown();
        // t.clear_t();
        // rand_tetromino = t.get_tetromino(val);
        // b.place_tetromino(rand_tetromino, Tetromino.c);
        // System.out.println("Printing board...");
        // b.print_board();
        // System.out.println("Rotating twice to the right...");
        // t.rotateRight();
        // t.rotateRight();
        // t.clear_t();
        // rand_tetromino = t.get_tetromino(val);
        // b.place_tetromino(rand_tetromino, Tetromino.c);
        // System.out.println("Printing board...");
        // b.print_board();
        // System.out.println("Rotating once to the left...");
        // t.rotateLeft();
        // t.clear_t();
        // rand_tetromino = t.get_tetromino(val);
        // b.place_tetromino(rand_tetromino, Tetromino.c);
        // System.out.println("Printing board...");
        // b.print_board();
        // System.out.println("Rotating once to the right...");
        // t.rotateRight();
        // t.clear_t();
        // rand_tetromino = t.get_tetromino(val);
        // b.place_tetromino(rand_tetromino, Tetromino.c);
        // System.out.println("Printing board...");
        // b.print_board();
        // System.out.println("\nValidating positions..\n");
        // System.out.println("X-axis position is -1..");
        // Tetromino.b = -1;
        // System.out.println(b.validateTetrominoPosition());
        // System.out.println("X-axis position is 10..");
        // Tetromino.b = 10;
        // System.out.println(b.validateTetrominoPosition());
        // Tetromino.b = 0;
        // System.out.println("Y-axis position is 20..");
        // Tetromino.a = 20;
        // System.out.println(b.validateTetrominoPosition());
        // System.out.println("X-axis position is 7 and Y-axis is 4 ..");
        // Tetromino.a = 7;
        // Tetromino.b = 4;
        // System.out.println(b.validateTetrominoPosition());
        
        
    }   
}

