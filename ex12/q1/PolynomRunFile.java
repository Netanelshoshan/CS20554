
import javax.swing.*;
import java.util.Arrays;

public class PolynomRunFile {
    public static void main(String[] args) throws Exception {

        int cnt = 0;
        Polynom p1 = null, p2 = null;
        while (cnt < 2) {
            String pow = "", coe = "";
            switch (cnt) {
                case 0:
                    coe = JOptionPane.showInputDialog("Enter the coefficients for the first polynomial:");
                    pow = JOptionPane.showInputDialog("Enter the powers for the first polynomial:");
                    break;
                case 1:
                    coe = JOptionPane.showInputDialog("Enter the coefficients for the second polynomial:");
                    pow = JOptionPane.showInputDialog("Enter the powers for the second polynomial:");
                    break;
            }


            String[] coefficients_input = coe.split(" ");
            double[] coefficients_convertion = Arrays.stream(coefficients_input).mapToDouble(Double::parseDouble).toArray();
            String[] powers_input = pow.split(" ");
            int powers_convertion[] = Arrays.stream(powers_input).mapToInt(Integer::parseInt).toArray();
            switch (cnt) {
                case 0:
                    p1 = new Polynom(coefficients_convertion, powers_convertion);
                    break;
                case 1:
                    p2 = new Polynom(coefficients_convertion, powers_convertion);
                    break;
            }
            cnt++;
        }
        calc(p1, p2);
    }


    public static void calc(Polynom p1, Polynom p2) {
        boolean equal;
        Polynom add, sub, der1, der2;
        if (p1.equals(p2)) {
            equal = true;
        } else {
            equal = false;
        }
        Polynom p3 = p1.plus(p2);
        Polynom p4 = p1.minus(p2);
        Polynom p5 = p1.derivative();
        Polynom p6 = p2.derivative();

        JOptionPane.showMessageDialog(null, "The first polynomial is ( " + p1 + " )\n" +
                "The second polynomial is ( " + p2 + " )\n" +
                "p = q -> " + equal + "\n" +
                "p + q = " + p3 + "\n" +
                "p - q = " + p4 + "\n" +
                "p' = " + p5 + "\n" +
                "q' = " + p6 + "\n");

    }
}
