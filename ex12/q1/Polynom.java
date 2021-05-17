
import java.util.*;

public class Polynom {
    // array that holds the polynomial
    private ArrayList<Element> polynomial = new ArrayList<>();

    // constructor that gets two arrays that represents the polynomial and adds them to the arrayList
    public Polynom(double[] coefficients, int[] powers) throws Exception {
        if (coefficients.length != powers.length) {
            throw new Exception("Error. The coefficients array differ in size from the powers array. ");
        }
        for (int i = 0; i < powers.length; i++) {
            polynomial.add(i, new Element(coefficients[i], powers[i]));
        }
        sort(polynomial);
    }


    //A second constructor which gets an ArrayList that already holds a polynomial.
    public Polynom(ArrayList<Element> pol) {
        this.polynomial = new ArrayList<>(pol);
    }


    public ArrayList<Element> getPolynomial() {
        return this.polynomial;
    }

    /*
     * This function gets the ArrayList and sorts it in desc order.
     */
    public void sort(ArrayList<Element> polynomial) {
        for (int i = 0; i < polynomial.size(); i++) {
            for (int j = i + 1; j < polynomial.size(); j++) {
                if (polynomial.get(i).getPower() < polynomial.get(j).getPower()) {
                    Element tmp = polynomial.get(i);
                    polynomial.set(i, polynomial.get(j));
                    polynomial.set(j, tmp);
                }
            }
        }
        //and Making sure to sum two coefficients with the same degree.
        for (int i = 0, j = 1; j < polynomial.size(); i++, j++) {
            if (polynomial.get(i).getPower() == polynomial.get(j).getPower()) {
                polynomial.set(j, new Element(polynomial.get(i).getCoefficient() +
                                polynomial.get(j).getCoefficient(), polynomial.get(j).getPower()));
                polynomial.remove(i);
            }
        }
    }

    // plus function that sums the current polynomial with the given one.
    public Polynom plus(Polynom pol) {
        Polynom new_polynomial = new Polynom(this.polynomial);
        boolean found;
        /**
         * for each i in the GIVEN polynomial do:
         * get his power and compare it with j that is in the CURRENT polynomial.
         * if there's a match - sums them up and flag that we found the a match.
         * otherwise - add the element to the new array list.
         */
        for (int i = 0; i < pol.polynomial.size(); i++) {
            found = false;
            int new_power = pol.polynomial.get(i).getPower();
            int j = 0;
            while (j < new_polynomial.polynomial.size() && !found) {
                int curr_power = new_polynomial.polynomial.get(j).getPower();
                double new_coefficient = pol.polynomial.get(i).getCoefficient();
                if (new_power == curr_power) {
                    new_polynomial.polynomial.set(j, new Element(new_coefficient + new_polynomial.polynomial.get(j).getCoefficient(), new_power));
                    found = true;
                }
                j++;
            }
            if (!found) {
                new_polynomial.polynomial.add(0, pol.polynomial.get(i));
            }
        }
        // sort the new one and return.
        new_polynomial.sort(new_polynomial.getPolynomial());
        return new_polynomial;
    }

    /* minus function that subtract the current polynomial with the given one.
    * This function basically does almost the same thing that plus does except for a little change*/
    public Polynom minus(Polynom pol) {
        Polynom new_polynomial = new Polynom(this.polynomial);
        boolean found;
        for (int i = 0; i < pol.polynomial.size(); i++) {
            found = false;
            int new_power = pol.polynomial.get(i).getPower();
            double new_coefficient = pol.polynomial.get(i).getCoefficient();
            int j = 0;
            while (j < new_polynomial.polynomial.size() && !found) {
                int curr_power = new_polynomial.polynomial.get(j).getPower();
                if (new_power == curr_power) {
                    new_polynomial.polynomial.set(j, new Element(new_polynomial.polynomial.get(j).getCoefficient() - new_coefficient, new_power));
                    found = true;
                }
                j++;
            }
            if (!found) {
                // add the element with a minus sign.
                new_polynomial.polynomial.add(0, new Element(-new_coefficient, new_power));
            }
        }
        new_polynomial.sort(new_polynomial.getPolynomial());
        return new_polynomial;
    }
    // this function calculates the derivative of the current polynomial.
    public Polynom derivative() {
        Polynom new_polynomial = new Polynom(this.polynomial);
        for (int i = 0; i < new_polynomial.polynomial.size(); i++) {
            int pow = new_polynomial.polynomial.get(i).getPower();
            if (pow != 0) {
                double coe = new_polynomial.polynomial.get(i).getCoefficient();
                new_polynomial.polynomial.set(i, new Element(pow * coe, pow - 1));
            } else { // power = 0 -> remove the element.
                new_polynomial.polynomial.remove(i);
            }
        }
        return new_polynomial;
    }

    // this function checks if the given polynomial equal the current polynomial
    public boolean equals(Object pol) {
        if (pol instanceof Polynom) {
            if (this.polynomial.size() != ((Polynom) pol).polynomial.size()) // different sizes
                return false;
            for (int i = 0; i < this.polynomial.size(); i++) { // basic compare
                if (this.polynomial.get(i).getCoefficient() != ((Polynom) pol).polynomial.get(i).getCoefficient()
                        || this.polynomial.get(i).getPower() != ((Polynom) pol).polynomial.get(i).getPower())
                    return false;
            }
            return true;
        } else {
            System.out.println("Mismatch. The given object should be a Polynomial.");
            return false;
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        // append the first element to str
        str.append(this.polynomial.get(0).getCoefficient()).append("x").append("^").append(this.polynomial.get(0).getPower());
        // do the same for all others.
        for (int i = 1; i < this.polynomial.size() - 1; i++) {
            str.append(polynomial.get(i).toString());
        }
        // if the element power is a 0, check if its positive or a negative and append it.
        if (this.polynomial.get(this.polynomial.size() - 1).getPower() == 0) {
            if (this.polynomial.get(this.polynomial.size() - 1).getCoefficient() < 0) {
                str.append(this.polynomial.get(this.polynomial.size() - 1).getCoefficient());

            } else {
                str.append(" + ").append(this.polynomial.get(this.polynomial.size() - 1).getCoefficient());
            }
        } else {
            str.append(polynomial.get(this.polynomial.size() - 1).toString());
        }
        return str.toString();
    }
}
