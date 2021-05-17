/*
 * @ Netanel Shoshan @
 *
 */

public class Element {
    private int power;
    private double coefficient;

    public Element(double coefficient, int power){
        this.coefficient = coefficient;
        this.power = power;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        String str = "";
        if (coefficient < 0){
            str+= " - " + Math.abs(coefficient) +"x";
        }else if(coefficient>0){
            str+= " + "  + coefficient + "x";
        }if (power > 1){
            str+= "^" + power;
        }
        return str;
    }
}
