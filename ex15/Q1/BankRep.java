import java.util.Random;

// Clerk in a bank, can perform transactions
public class BankRep implements Runnable {

    private BankAccount[] accountsArray;
    private TransactionDB transactionDB;

    public BankRep(TransactionDB transactionDB, BankAccount[] accounts) {
        this.transactionDB = transactionDB;
        this.accountsArray = accounts;
    }


    /*
    Get's transaction from transactionDB and commit the transaction.
     */
    public void run() {
        Transaction currTransaction;

        while ((currTransaction = transactionDB.fetch()) != null) {
            long currId = currTransaction.getAccountId();//transaction accountID
            double currAmount = currTransaction.getAmount();//transaction amount
            BankAccount account = this.searchBankAccount(currId);//search for the account

            System.out.printf("%s , Transaction Amount: %.2f \n", account, currAmount);

            try {
                account.transaction(currAmount, this);//make the transaction
            } catch (InterruptedException ignored) { }
            try {
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " Finished!");

    }


    // return the bankaccount from  db
    public BankAccount searchBankAccount(long accountId) {
        for (BankAccount bankAccount : accountsArray)
            if (accountId == bankAccount.getAccountId())
                return bankAccount;
        return null;

    }

}
