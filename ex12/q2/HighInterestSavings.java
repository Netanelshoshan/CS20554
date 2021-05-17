

/*
 * @ Netanel Shoshan @
 *  This class represents a saving account with high monthly rates and higher minimum balance.
 */
public class HighInterestSavings extends SavingsAccount {

    public static final double DEFAULT_HIGH_MINIMUM = 200;
    public static final double DEFAULT_HIGH_INTEREST = 10.0;
    private double higherMinimum;


    public HighInterestSavings(String accNo, String name, String id, double balance) {
        super(accNo, name, id, balance, DEFAULT_HIGH_INTEREST);
        higherMinimum = DEFAULT_HIGH_MINIMUM;
    }

    public HighInterestSavings(String accNo, String name, String id, double balance, double highMinimum) {
        super(accNo, name, id, balance, DEFAULT_HIGH_INTEREST);
        this.higherMinimum = highMinimum;
    }

    public double getHigherMinimum() {
        return higherMinimum;
    }

    public void setHigherMinimum(double higherMinimum) {
        this.higherMinimum = higherMinimum;
    }


    public double minAllowed() {
        return higherMinimum;
    }

    @Override
    public String toString() {
        return super.toString() + "High interest Account with high minimum balance : " +
                +higherMinimum +
                "\n";
    }
}
