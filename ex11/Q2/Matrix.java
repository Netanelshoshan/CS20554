/*
 * @ Netanel Shoshan @
 *
 * This class generate and calcuate the matrix according to the
 * game rules
 */

import javax.swing.*;
import java.security.SecureRandom;

public class Matrix extends JPanel {
    private int[][] mat;


    public Matrix(int size) {
        mat = new int[size][size];
        fillRandom();
    }

    // matrix filling function with random values of 1/0;
    public void fillRandom() {
        SecureRandom randomNumbers = new SecureRandom();
        int i, j;
        for (i = 0; i < mat.length; i++) {
            for (j = 0; j < mat.length; j++) {
                int random = randomNumbers.nextInt(2);
                mat[i][j] = random;
            }
        }
    }

    // A function to change the values of the matrix based on the rules of the game.
    public void changeMatrix() {
        int[][] newMat = new int[mat.length][mat.length];
        int aliveNeighbors = 0, i, j;
        for (i = 0; i < mat.length; i++) {
            for (j = 0; j < mat.length; j++) {
                if (i != 0 && i != mat.length - 1 && j != 0 && j != mat.length - 1) { //range validation
                    aliveNeighbors = mat[i - 1][j - 1] + mat[i][j - 1] + mat[i + 1][j - 1] + mat[i - 1][j] +
                            mat[i + 1][j] + mat[i - 1][j + 1] + mat[i][j + 1] + mat[i + 1][j + 1];
                } else { // Let's check the edges
                    if (i == 0) {//I'm on the left edges
                        if (j == 0) { // I'm on the top left corner
                            aliveNeighbors = mat[i][j + 1] + mat[i + 1][j + 1] + mat[i + 1][j];
                        } else if (j == mat.length - 1) { //I'm on the bottom left corner
                            aliveNeighbors = mat[i][j - 1] + mat[i + 1][j - 1] + mat[i + 1][j];
                        } else { // I'm in the middle
                            aliveNeighbors = mat[i][j - 1] + mat[i][j + 1] + mat[i + 1][j - 1] + mat[i + 1][j] + mat[i + 1][j + 1];
                        }
                    } else if (i == mat.length - 1) {//I'm on the right edges
                        if (j == 0) {//I'm on the top right corner
                            aliveNeighbors = mat[i][j + 1] + mat[i - 1][j] + mat[i - 1][j + 1];
                        } else if (j == mat.length - 1) {//I'm on the bottom right corner
                            aliveNeighbors = mat[i - 1][j] + mat[i - 1][j - 1] + mat[i][j - 1];
                        } else {// I'm in the middle
                            aliveNeighbors = mat[i][j - 1] + mat[i][j + 1] + mat[i - 1][j - 1] + mat[i - 1][j] +
                                    mat[i - 1][j + 1];
                        }
                    } else { // I'm in the middle of the matrix
                        if (j == 0) { // I'm on the first row
                            aliveNeighbors = mat[i - 1][j] + mat[i + 1][j] + mat[i][j + 1] + mat[i - 1][j + 1] + mat[i + 1][j + 1];
                        } else if (j == mat.length - 1) { //I'm on the middle of the bottom row
                            aliveNeighbors = mat[i - 1][j] + mat[i + 1][j] + mat[i - 1][j - 1] + mat[i][j - 1] + mat[i + 1][j - 1];
                        }
                    }
                }
                //sending the calculated neighbors in order to decide if the tile will be filled or not
                newMat[i][j] = paintTile(aliveNeighbors, mat[i][j]);
            }
        }
        mat = newMat;
    }//end of changeMatrix

    /* This function will decide if the current tile will be filled or not/
    *   ( based on the calculated value of the current cell that was given from "changeMatrix") */
    public int paintTile(int aliveNeighbors, int currTile) {
        if (currTile == 0)
            return (aliveNeighbors == 3) ? 1 : 0;
        else
            return (aliveNeighbors < 2 || aliveNeighbors > 3) ? 0 : 1;
    }

    /*Matrix getter function */
    public int[][] getMat() {
        return mat;
    }


}
