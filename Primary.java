/**
 * Jazmin I. Paz
 * Homework 1
 * 18 June 2019
 * CS3331 Advanced Object-Oriented Programming
 */
public class Primary {
    Primary(){
        Board b = new Board();
        Tetromino t = new Tetromino();
        b.board_init();
        b.print_board();
        Tetromino.TetrominoEnum val = Tetromino.TetrominoEnum.getEnumByValue(3);
        int[][] rand_tetromino = t.get_tetromino(val);
        b.place_tetromino(rand_tetromino);
        b.print_board();
        b.moveTetrominoDown();
        rand_tetromino = t.get_tetromino(val);
        b.place_tetromino(rand_tetromino);
        b.print_board();


    }
    public static void main(String[] args){
        new Primary();
    }
}
