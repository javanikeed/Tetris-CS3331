/**
 * Jazmin I. Paz
 * Homework 1
 * 18 June 2019
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
    public int a=0,b=0;
    protected int[][] tetromino;
    protected int[][] t = new int[4][4];
    Tetromino(){

    }

    /**
     * This method is in charge of generating a random tetromino
     * and returning an array with its coordinates
     * @return
     */
    public int[][] get_tetromino(TetrominoEnum random) {
        switch(random) {
            case O:
                //Creating O
                System.out.println("Random tetromino: O");
                //t = new int[][] {{a,b},{a,b+1},{a+1,b},{a+1,b+1}};
                t[a][b] = 1;t[a][b+1] = 1;t[a][b+2] = 0;t[a][b+3] = 0;t[a+1][b] = 1;t[a+1][b+1] = 1;t[a+1][b+2] = 0;t[a+1][b+3] = 0;t[a+2][b] = 0;t[a+2][b+1] = 0;t[a+2][b+2] = 0;t[a+2][b+3] = 0;t[a+3][b] = 0;t[a+3][b+1] = 0;t[a+3][b+2] = 0;t[a+3][b+3] = 0;
                System.out.println("1Val of B:"+b);
                break;

            case I:
                //Creating I
                System.out.println("Random tetromino: I");
                //t = new int[][] {{a,b},{a,b+1},{a,b+2},{a,b+3}};
                t[a][b] = 1;t[a][b+1] = 1;t[a][b+2] = 1;t[a][b+3] = 1;t[a+1][b] = 0;t[a+1][b+1] = 0;t[a+1][b+2] = 0;t[a+1][b+3] = 0;t[a+2][b] = 0;t[a+2][b+1] = 0;t[a+2][b+2] = 0;t[a+2][b+3] = 0;t[a+3][b] = 0;t[a+3][b+1] = 0;t[a+3][b+2] = 0;t[a+3][b+3] = 0;
                break;

            case L:
                //Creating L
                System.out.println("Random tetromino: L");
                //t = new int[][] {{a,b},{a+1,b},{a+2,b},{a+2,b+1}};
                t[a][b] = 1;t[a][b+1] = 0;t[a][b+2] = 0;t[a][b+3] = 0;t[a+1][b] = 1;t[a+1][b+1] = 0;t[a+1][b+2] = 0;t[a+1][b+3] = 0;t[a+2][b] = 1;t[a+2][b+1] = 1;t[a+2][b+2] = 0;t[a+2][b+3] = 0;t[a+3][b] = 0;t[a+3][b+1] = 0;t[a+3][b+2] = 0;t[a+3][b+3] = 0;
                break;
            case J:
                //Creating J
                System.out.println("Random tetromino: J");
                //t = new int[][] {{a,b+1},{a+1,b+1},{a+2,b+1},{a+2,b}};
                t[a][b] = 0;t[a][b+1] = 1;t[a][b+2] = 0;t[a][b+3] = 0;t[a+1][b] = 0;t[a+1][b+1] = 1;t[a+1][b+2] = 0;t[a+1][b+3] = 0;t[a+2][b] = 1;t[a+2][b+1] = 1;t[a+2][b+2] = 0;t[a+2][b+3] = 0;t[a+3][b] = 0;t[a+3][b+1] = 0;t[a+3][b+2] = 0;t[a+3][b+3] = 0;
                break;
            case T:
                //Creating T
                System.out.println("Random tetromino: T");
                //t = new int[][] {{a,b+1},{a+1,b},{a+1,b+1},{a+1,b+2}};
                t[a][b] = 0;t[a][b+1] = 1;t[a][b+2] = 0;t[a][b+3] = 0;t[a+1][b] = 1;t[a+1][b+1] = 1;t[a+1][b+2] = 1;t[a+1][b+3] = 0;t[a+2][b] = 0;t[a+2][b+1] = 0;t[a+2][b+2] = 0;t[a+2][b+3] = 0;t[a+3][b] = 0;t[a+3][b+1] = 0;t[a+3][b+2] = 0;t[a+3][b+3] = 0;
                break;
            case S:
                //Creating S
                System.out.println("Random tetromino: S");
                //t = new int[][] {{a+1,b},{a,b+1},{a+1,b+1},{a,b+2}};
                t[a][b] = 0;t[a][b+1] = 1;t[a][b+2] = 1;t[a][b+3] = 0;t[a+1][b] = 1;t[a+1][b+1] = 1;t[a+1][b+2] = 0;t[a+1][b+3] = 0;t[a+2][b] = 0;t[a+2][b+1] = 0;t[a+2][b+2] = 0;t[a+2][b+3] = 0;t[a+3][b] = 0;t[a+3][b+1] = 0;t[a+3][b+2] = 0;t[a+3][b+3] = 0;
                break;
            case Z:
                //Creating Z
                System.out.println("Random tetromino: Z");
                //t = new int[][] {{a,b}, {a,b+1}, {a+1,b+1}, {a+1,b+2}};
                t[a][b] = 1;t[a][b+1] = 1;t[a][b+2] = 0;t[a][b+3] = 0;t[a+1][b] = 0;t[a+1][b+1] = 1;t[a+1][b+2] = 1;t[a+1][b+3] = 0;t[a+2][b] = 0;t[a+2][b+1] = 0;t[a+2][b+2] = 0;t[a+2][b+3] = 0;t[a+3][b] = 0;t[a+3][b+1] = 0;t[a+3][b+2] = 0;t[a+3][b+3] = 0;
                System.out.println();
    		    break;
        }
        return t;
    }
    /**
     * Rotates tetromino to the right.
     */
    public void rotateRight()
    {
        //your code goes here
    }
    /**
     * Rotates tetromino to the left.
     */
    public void rotateLeft()
    {
        //your code goes here
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
