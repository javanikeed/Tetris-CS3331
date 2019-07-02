/**
 * Jazmin I. Paz
 * Homework 1
 * 19 June 2019
 * CS3331 Advanced Object-Oriented Programming
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/** This class is will model a tetromino.
 * @author epadilla2
 * @param <U>
 * @param <T>
 *
 */
public class Tetromino{
    public static int a=0,b=2,c=0;
    //protected int[][] tetromino;
    protected int[][][] t = new int[20][10][4];
    Tetromino(){

    }

    /**
     * This method is in charge of generating a random tetromino
     * including each of its rotations
     * and returning an array with its coordinates
     * @return
     */
    public int[][][] get_tetromino(TetrominoEnum random) {
        switch(random) {
            case O:
                //Creating O
                t[a][b][0] = 1;t[a][b+1][0] = 1;t[a+1][b][0] = 1;t[a+1][b+1][0] = 1;t[a][b][1] = 1;t[a][b+1][1] = 1;t[a+1][b][1] = 1;t[a+1][b+1][1] = 1;t[a][b][2] = 1;t[a][b+1][2] = 1;t[a+1][b][2] = 1;t[a+1][b+1][2] = 1;t[a][b][3] = 1;t[a][b+1][3] = 1;t[a+1][b][3] = 1;t[a+1][b+1][3] = 1;
                break;

            case I:
                //Creating I
                t[a][b][0] = 1;t[a][b+1][0] = 1;t[a][b+2][0] = 1;t[a][b+3][0] = 1;t[a][b][1] = 1;t[a+1][b][1] = 1;t[a+2][b][1] = 1;t[a+3][b][1] = 1;t[a][b][2] = 1;t[a][b+1][2] = 1;t[a][b+2][2] = 1;t[a][b+3][2] = 1;t[a][b][3] = 1;t[a][b+1][3] = 1;t[a][b+2][3] = 1;t[a][b+3][3] = 1;
                break;

            case L:
                //Creating L
                t[a][b][0] = 1;t[a+1][b][0] = 1;t[a+2][b][0] = 1;t[a+2][b+1][0] = 1;t[a][b][1] = 1;t[a+1][b][1] = 1;t[a][b+1][1] = 1;t[a][b+2][1] = 1;t[a][b][2] = 1;t[a][b+1][2] = 1;t[a+1][b+1][2] = 1;t[a+2][b+1][2] = 1;t[a+1][b][3] = 2;t[a+1][b+1][3] = 2;t[a+1][b+2][3] = 1;t[a][b+2][3] = 1;

                break;
            case J:
                //Creating J
                t[a][b+1][0] = 1;t[a+1][b+1][0] = 1;t[a+2][b][0] = 1;t[a+2][b+1][0] = 1;t[a][b][1] = 1;t[a+1][b][1] = 1;t[a+1][b+1][1] = 1;t[a+1][b+2][1] = 1;t[a][b][2] = 1;t[a+1][b][2] = 1;t[a+2][b][2] = 1;t[a][b+1][2] = 1;t[a][b][3] = 1;t[a][b+1][3] = 1;t[a][b+2][3] = 1;t[a+1][b+2][3] = 1;

                break;
            case T:
                //Creating T
                t[a][b+1][0] = 1;t[a+1][b][0] = 1;t[a+1][b+1][0] = 1;t[a+1][b+2][0] = 1;t[a+1][b][1] = 1;t[a+1][b+1][1] = 1;t[a][b+1][1] = 1;t[a+2][b+1][1] = 1;t[a][b][2] = 1;t[a][b+1][2] = 1;t[a][b+2][2] = 1;t[a+1][b+1][2] = 1;t[a][b][3] = 1;t[a+1][b][3] = 1;t[a+2][b][3] = 1;t[a+1][b+1][3] = 1;
                break;
            case S:
                //Creating S
                t[a][b+1][0] = 1;t[a][b+2][0] = 1;t[a+1][b][0] = 1;t[a+1][b+1][0] = 1;t[a][b][1] = 1;t[a+1][b][1] = 1;t[a+1][b+1][1] = 1;t[a+2][b+1][1] = 1;t[a][b+1][2] = 1;t[a][b+2][2] = 1;t[a+1][b][2] = 1;t[a+1][b+1][2] = 1;t[a][b][3] = 1;t[a+1][b][3] = 1;t[a+1][b+1][3] = 1;t[a+2][b+1][3] = 1;
                break;
            case Z:
                //Creating Z
                t[a][b][0] = 1;t[a][b+1][0] = 1;t[a+1][b+1][0] = 1;t[a+1][b+2][0] = 1;t[a][b+1][1] = 1;t[a+1][b][1] = 1;t[a+1][b+1][1] = 1;t[a+2][b][1] = 1;t[a][b][2] = 1;t[a][b+1][2] = 1;t[a+1][b+1][2] = 1;t[a+1][b+2][2] = 1;t[a][b+1][3] = 1;t[a+1][b][3] = 1;t[a+1][b+1][3] = 1;t[a+2][b][3] = 1;
                break;
            default:
                break;
        }
        return t;
    }
    /**
     * Clears the tetromino shape and reinitializes every element
     * in array to 0 for re-use
     */
    public void clear_t() {
        t = new int[20][10][4];
    }

    /**
     * Rotates tetromino to the right.
     */
    public void rotateRight()
    {
        c++;
        if (c==4){
            c=0;
        }
    }

    /**
     * Rotates tetromino to the left.
     */
    public void rotateLeft()
    {
        c--;
        if (c==4){
            c=0;
        }
    }

    /**
     * Enumeration to list the different tetrominos.
     * FILLER used when in multiplayer sending a line to the other player.
     * @author epadilla2
     *
     */
    public enum TetrominoEnum {
        /** Types of tetrominos, filler represents punishment lines added in multiplayer mode */

        I(0), J(1), L(2), O(3), S(4), Z(5), T(6), FILLER(7);
        /** Integer value of each tetromino*/
        private int value;
        /**  Hash for inverse lookup of a tetromino based on value*/
        private static final Map<Integer, TetrominoEnum> reverseLookup = new HashMap<Integer, TetrominoEnum>();

        static {
            for (TetrominoEnum tetromino : TetrominoEnum.values()) {
                reverseLookup.put(tetromino.getValue(), tetromino);
            }
        }
        /**
         * Constructor that sets the integer value of tetromino
         * @param value
         */
        TetrominoEnum(int value) {
            this.value = value;
        }
        /**
         * Return integer value of tetromino
         * @return
         */
        public int getValue()
        {
            return value;
        }
        /**
         * Return TetrominoEnum depending on value
         * @param value
         * @return
         */
        public static TetrominoEnum getEnumByValue(int value) {
            return reverseLookup.get(value);
        }
        /**
         * Returns a random TetrominoEnum
         * @return
         */
        public static TetrominoEnum getRandomTetromino() {
            Random random = new Random();
            return values()[random.nextInt(values().length-1)];
        }
    }
}