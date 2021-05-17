/*
 * @ Netanel Shoshan @
 *  This class represents a saving account with monthly rates.
 */
public class SavingsAccount extends BankAccount {
    protected static final double DEFAULT_INTEREST = 0.1;
    private double interest;

    public SavingsAccount(String accNo, String name, String id, double balance) {
        super(accNo, name, id, balance);
        interest = DEFAULT_INTEREST;
    }

    public SavingsAccount(String accNo, String name, String id, double balance, double interest) {
        super(accNo, name, id, balance);
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

    @Override
    public String toString() {
        return super.toString() +
                "Savings Account interest : " + interest + "\n";
    }
}
