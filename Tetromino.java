/**
 * Tetromino class where all the tetrominos are created.
 * @author Emilio Ramirez
 * @author Jazmin I. Paz
 * @version 1.2
 * @since 2019-07-21
 *
 */
package model;

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
    public double [][] sqrCoords = new double[4][2];    // stores coordinates for each square in the Tetromino  
    public double rotationCenter;           // Indicates about which point the Tetromino Rotates.
    public TetrominoEnum shape;                     // maybe switch from using 2d array to point2d.Double ??

    Tetromino(){

    }

    /**
     * This is in charge of generating a random tetromino
     * including each of its rotations
     * and returning an array with its coordinates
     * 
     */
    public Tetromino(TetrominoEnum random) { // instead of function made it a constructor
        this.shape =random;
        switch(random) {
            case O:
                //Creating O
                sqrCoords[0][0] = 2; // sqr1
                sqrCoords[0][1] = 2;
                sqrCoords[1][0] = 3; 
                sqrCoords[1][1] = 2; 
                sqrCoords[2][0] = 2; 
                sqrCoords[2][1] = 3; 
                sqrCoords[3][0] = 3; 
                sqrCoords[3][1] = 3;
                rotationCenter = 2.5;
                break;
            case I:
                //Creating I
                sqrCoords[0][0] = 1;
                sqrCoords[0][1] = 2;
                sqrCoords[1][0] = 2; 
                sqrCoords[1][1] = 2; 
                sqrCoords[2][0] = 3; 
                sqrCoords[2][1] = 2; 
                sqrCoords[3][0] = 4; 
                sqrCoords[3][1] = 2;
                rotationCenter = 2.5;
                break;
            case L:
                //Creating L
                sqrCoords[0][0] = 4;
                sqrCoords[0][1] = 2;
                sqrCoords[1][0] = 2; 
                sqrCoords[1][1] = 3; 
                sqrCoords[2][0] = 3; 
                sqrCoords[2][1] = 3; 
                sqrCoords[3][0] = 4; 
                sqrCoords[3][1] = 3;
                rotationCenter = 3.0;
                break;
            case J:
                //Creating J
                sqrCoords[0][0] = 2;
                sqrCoords[0][1] = 2;
                sqrCoords[1][0] = 2; 
                sqrCoords[1][1] = 3; 
                sqrCoords[2][0] = 3; 
                sqrCoords[2][1] = 3; 
                sqrCoords[3][0] = 4; 
                sqrCoords[3][1] = 3;
                rotationCenter = 3.0;
                break;
            case T:
                //Creating T
                sqrCoords[0][0] = 3;
                sqrCoords[0][1] = 2;
                sqrCoords[1][0] = 2; 
                sqrCoords[1][1] = 3; 
                sqrCoords[2][0] = 3; 
                sqrCoords[2][1] = 3; 
                sqrCoords[3][0] = 4; 
                sqrCoords[3][1] = 3;
                rotationCenter = 3.0;
                break;
            case S:
                //Creating S
                sqrCoords[0][0] = 3;
                sqrCoords[0][1] = 2;
                sqrCoords[1][0] = 4; 
                sqrCoords[1][1] = 2; 
                sqrCoords[2][0] = 2; 
                sqrCoords[2][1] = 3; 
                sqrCoords[3][0] = 3; 
                sqrCoords[3][1] = 3;
                rotationCenter = 3.0;
                break;
            case Z:
                //Creating Z
                sqrCoords[0][0] = 2;
                sqrCoords[0][1] = 2;
                sqrCoords[1][0] = 3; 
                sqrCoords[1][1] = 2; 
                sqrCoords[2][0] = 3; 
                sqrCoords[2][1] = 3; 
                sqrCoords[3][0] = 4; 
                sqrCoords[3][1] = 3;
                rotationCenter = 3.0;
                break;
    		default:
    			break;
        }
    }
    /**
     * Clears the tetromino shape and reinitializes every element
     * in array to 0 for re-use
	 * @return shape
	 */
	public char getShape()
	{
		return  (shape.name()).charAt(0);
	}


    /**
     * Rotates tetromino to the right.
     */
	public void rotateRight()
	{
        double tempX = 0.0;
        double tempY;
        for( int i = 0; i < sqrCoords.length;i++){
            for(int j = 0; j < sqrCoords[i].length;j++){
                if(j == 0){
                    tempX = sqrCoords[i][j];
                    tempY = sqrCoords[i][j+1];

                    sqrCoords[i][j] = (-(tempY-rotationCenter)+rotationCenter);
                }
                else{
                    sqrCoords[i][j] = (tempX);
                }

            }

        }
	}
    /**
     * Rotates tetromino to the left.
     */
	public void rotateLeft()
	{
        double tempX = 0.0;
        double tempY;
        for( int i = 0; i < sqrCoords.length;i++){
            for(int j = 0; j < sqrCoords[i].length;j++){
                if(j == 0){
                    tempX = sqrCoords[i][j];
                    tempY = sqrCoords[i][j+1];

                    sqrCoords[i][j] = (tempY);
                }
                else{
                    sqrCoords[i][j] = (-(tempX-rotationCenter)+ rotationCenter);
                }

            }

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
        public static  TetrominoEnum getEnumByValue(int value) { // removed static
            return reverseLookup.get(value);
        }
        /**
         * Returns a random TetrominoEnum
         * @return
         */
        public static TetrominoEnum getRandomTetromino() { // removed static
            Random random = new Random();
            return values()[random.nextInt(values().length-1)];
        }
    }
}