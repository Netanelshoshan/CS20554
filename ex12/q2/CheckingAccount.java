
/*
 * @ Netanel Shoshan @
 *  This class represents the Checking account
 */
public abstract class CheckingAccount extends BankAccount {

    public CheckingAccount(String accNo, String name, String id, double balance) {
        super(accNo, name, id, balance);
    }

    // the write check function with error checking
    public void writeCheck(double amount) {
        try {
            if (amount > getBalance())
                throw new IllegalBalance();
            balance -= amount;
            System.out.println("The check was written for the amount of: " + amount + ". ( accNo. "+ getAccNo() +" )");
        } catch (IllegalBalance i) {
            System.out.println("Insufficient funds for the check. ( accNo. " + getAccNo() + " )");
        }
    }
}
