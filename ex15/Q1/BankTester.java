import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTester {

    public static void main(String[] args) throws InterruptedException {
        //initializing 5 accounts with 0 balance
        BankAccount[] bankAccounts = new BankAccount[5];
        for (int i = 0; i < 5; i++) {
            bankAccounts[i] = new BankAccount(i, 0);
        }
        System.out.println("---------------------------------------\nINITIALIZING 5 ACCOUNTS\n" +
                "---------------------------------------");
        for (BankAccount account : bankAccounts) {
            System.out.println(account);
        }


        System.out.println("\n---------------------------------------\nMaking 50 random transaction amounts\n" +
                "---------------------------------------");
        //making random transaction amounts
        Transaction[] transactions = new Transaction[50];
        for (int i = 0; i < transactions.length; i++)
            transactions[i] = new Transaction((int) (5 * Math.random()), 2000 * Math.random() - 1000);

        double[] amounts = new double[50];
        DecimalFormat df = new DecimalFormat("#.##");
        for (int i = 0; i < transactions.length; i++) {
            amounts[i] = Double.parseDouble(df.format(transactions[i].getAmount()));
        }
        double[][] mat = new double[10][5];
        for (int i = 0; i < 10; i++) {
            System.arraycopy(amounts, (i * 5), mat[i], 0, 5);
        }

        System.out.println(Arrays.deepToString(mat).replace("], ", "]\n"));


        System.out.println("\n---------------------------------------\nCreating 10 different representatives..\n" +
                "---------------------------------------");
        TransactionDB transactionDB = new TransactionDB(transactions);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.execute(new BankRep(transactionDB, bankAccounts));
        }
        executor.shutdownNow();


    }

}

