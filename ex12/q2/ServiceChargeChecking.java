
/*
 * @ Netanel Shoshan @
 *  This class represents the an account with monthly commision
 */
public class ServiceChargeChecking extends CheckingAccount {
    public static final double DEFAULT_COMMISSION = 14.90;
    private double commission;

    public ServiceChargeChecking(String accNo, String name, String id, double balance) {
        super(accNo, name, id, balance);
        commission = DEFAULT_COMMISSION;
    }

    public ServiceChargeChecking(String accNo, String name, String id, double balance, double commission) {
        super(accNo, name, id, balance);
        this.commission = commission;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commissionToSet) {
        this.commission = commissionToSet;
    }

    @Override
    public void monthlyMgmt() {
        this.withdraw(commission);
    }
    //exceptions are made in the Checking account.
    @Override
    public void writeCheck(double amount) {
        super.writeCheck(amount);
    }

    @Override
    public String toString() {
        return super.toString() + "Account monthly commission : " + commission + "\n";
    }
}