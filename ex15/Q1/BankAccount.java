/*
This class represents the bankaccount with all of it's features.
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private static int countTransactions = 0;
    private long accountId;
    private double balance;

    //builder for the class
    public BankAccount(long accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public synchronized long getAccountId() {
        return accountId;
    }

    public synchronized double getBalance() {
        return balance;
    }

    // This syncronized function also validets that two reps cant make the balance negative.
    public synchronized void transaction(double amount, BankRep bankRep) throws InterruptedException{
        // if we have enough money for the transaction set the var to true, otherwise to false
        while (balance + amount < 0) {
            System.err.printf("Invalid Transaction Amount: %.2f for %s - FAILED\n\n", amount, this);
            wait();
        }
        balance += amount;
        System.out.printf("Transaction No. %d :%n%s ,Previous balance: %.2f - SUCCESS.\n\n", countTransactions++, this, (balance - amount));
        notifyAll();
    }

    public String toString() {
        String bal = String.format("%.2f", balance);
        return "Account ID : " + accountId + ", Account balance: " + bal;
    }

}




