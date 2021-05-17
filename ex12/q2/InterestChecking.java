/*
 * @ Netanel Shoshan @
 *  This class represents the an account without monthly commissions but with lower limit and interest rates
 */
public class InterestChecking extends NoServiceChargeChecking {
    private static final double DEFAULT_INTEREST = 0.1;
    private double interest;

    public InterestChecking(String accNo, String name, String id, double balance, double min) {
        super(accNo, name, id, balance, min);
        interest = DEFAULT_INTEREST;
    }

    public InterestChecking(String accNo, String name, String id, double balance, double min, double interest) {
        super(accNo, name, id, balance, min);
        this.interest = interest;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double calcInterest() {
        return this.getBalance() * interest;
    }

    @Override
    public void monthlyMgmt() {
        this.deposit(this.calcInterest());
    }
    //exception validations are made in NoServiceChargeChecking
    @Override
    public void writeCheck(double amount) {
        super.writeCheck(amount);
    }

    @Override
    public String toString() {
        return super.toString() + "Account interest: " + interest + "\n";
    }
}
