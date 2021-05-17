
/*
 * @ Netanel Shoshan @
 *  This class represents the an account without monthly commissions but with lower limit
 */
public class NoServiceChargeChecking extends CheckingAccount {

    private final double DEF_MIN_BALANCE = 100;
    private double minBalance;

    public NoServiceChargeChecking(String accNo, String name, String id, double balance) {
        super(accNo, name, id, balance);
        minBalance = DEF_MIN_BALANCE;
    }

    public NoServiceChargeChecking(String accNo, String name, String id, double balance, double min) {
        super(accNo, name, id, balance);
        minBalance = min;
    }

    @Override
    public void monthlyMgmt() {
    }


    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    // writing check with exception handling
    @Override
    public void writeCheck(double amount) {
        try {
            if (this.getBalance() - amount < minBalance)
                throw new IllegalBalance();
            super.writeCheck(amount);
        } catch (IllegalBalance e) {
            System.out.println("The amount specified on check ( " +amount +" ) is exceeding this account limits.\n" +
                    "The minimum balance allowed for your account is 100. Your current balance is " + getBalance());
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Minimum balance : " + minBalance + "\n";
    }
}
