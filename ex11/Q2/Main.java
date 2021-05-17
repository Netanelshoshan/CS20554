/*
 * @ Netanel Shoshan @
 *
 * Initializes the program.
 */
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        final int frame_size = 500;

        JFrame frame = new JFrame("Game Of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frame_size,frame_size);

        Panel p = new Panel();
        frame.add(p);
        frame.setVisible(true);

        while (JOptionPane.showConfirmDialog(null,"Would you like to see the next generation?\n") == 0){
            p.nextGeneration();
            p.repaint();
        };

    }
}
