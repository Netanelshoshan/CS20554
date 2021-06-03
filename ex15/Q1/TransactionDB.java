/*
This class represents the transaction db
 */
public class TransactionDB {
    private Transaction[] transactionArray;
    private int currPtr;

    public TransactionDB(Transaction[] transactions) {
        this.transactionArray = transactions;
        currPtr = transactions.length - 1;
    }

    /*
     fetch a transaction from the db
     */
    public synchronized Transaction fetch() {
        if (currPtr < 0)
            return null;
        return transactionArray[currPtr--];
    }
}


