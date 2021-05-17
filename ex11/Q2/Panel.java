/*
 * @ Netanel Shoshan @
 *
 * functions that are responsible to paint the matrix
 * according to the game rules
 */
import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private Matrix matrix;
    final private int rectSize = 30;
    final private int matrixSize = 10;

    public Panel(){
        matrix = new Matrix(matrixSize);
    }
    public void nextGeneration(){
        matrix.changeMatrix();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x=0,y=0,i,j;
        int[][] mat= matrix.getMat();
        for (i=0;i<matrixSize;i++){
            for (j=0;j<matrixSize;j++){
                if(mat[i][j] == 1){
                    g.fillRect(x,y,rectSize,rectSize);
                }else {
                    g.drawRect(x,y,rectSize,rectSize);
                }
                x+=rectSize;
            }
            y+=rectSize;
            x=0;
        }
    }
}
