/*/
This class represents a Transaction in the db
 */
public class Transaction {
    private long accountId;
    private double amount;

    public Transaction(long accountId, double sum) {
        this.accountId = accountId;
        this.amount = sum;
    }

    public long getAccountId() {
        return accountId;
    }

    public double getAmount() {
        return amount;
    }
}
