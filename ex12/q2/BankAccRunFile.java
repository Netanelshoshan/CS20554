//MAIN FILE


import java.io.IOException;
import java.security.SecureRandom;


public class BankAccRunFile {
    public static void main(String[] args) throws IOException {
        BankAccount[] acc = {
                new ServiceChargeChecking("1", "netanel", "12345678", 20000),
                new NoServiceChargeChecking("2", "Lior", "87654321", 10000),
                new NoServiceChargeChecking("3", "Golan", "87654322", 10000, 200),
                new InterestChecking("4", "Yehuda", "12345677", 8500, 500),
                new InterestChecking("5", "Yafit", "12233677", 9000, 500, 0.15),
                new SavingsAccount("6", "Avraham", "12233456", 3000),
                new SavingsAccount("7", "Miryam", "17733456", 4500, 10.0),
                new HighInterestSavings("8", "Rebecca", "12356577", 8000),
                new HighInterestSavings("9", "David", "2226577", 12000, 500),
                new HighInterestSavings("9", "David", "2226577", 12000, 500)};



        /* --------------------------------------------------
         *           INITIAL PRINT
         * --------------------------------------------------*/
        System.out.println("--------------------------------------------\n" +
                "INITIAL ACCOUNTS PRINT\n" +
                "--------------------------------------------\n");
        for (BankAccount account : acc) {
            System.out.println(account);
        }
        System.out.println("-----------------------------------------------\n" +
                "END OF INITIAL ACCOUNTS PRINT\n" +
                "-----------------------------------------------\n");



        /* --------------------------------------------------
        *           WRITING CHECKS
        * --------------------------------------------------*/
        System.out.println("-----------------------------------------------\n" +
                "WRITING CHECKS VALIDATION\n" +
                "-----------------------------------------------\n");
        CheckingAccount netanel = (CheckingAccount) acc[0];
        CheckingAccount lior = (CheckingAccount) acc[1];
        netanel.writeCheck(10000);//valid
        System.out.println(netanel);
        netanel.writeCheck(20000);//exception - insufficiant balance
        System.out.println("\n" + lior);
        lior.writeCheck(9950);//min balance is 100 - should output error
        System.out.println("-------------------------------------------------\n" +
                "END OF WRITING CHECKS VALIDATION\n" +
                "-------------------------------------------------\n");



        /* --------------------------------------------------
         *           EQUALITY CHECKS
         * --------------------------------------------------*/
        System.out.println("-------------------------------------------------\n" +
                "EQUALITY CHECKS VALIDATION\n" +
                "--------------------------------------------\n");
        System.out.println("Account 9 == account 8 ? " + acc[8].equals(acc[7])); // false
        System.out.println("Account 9 == account 9 ? " + acc[8].equals(acc[9]) + "\n"); // true
        System.out.println("--------------------------------------------\n" +
                "END OF EQUALITY CHECKS VALIDATION\n" +
                "-------------------------------------------------\n");





        /* --------------------------------------------------
         *           DEPOSIT/WITHDRAW/MGMT CHECKS
         * --------------------------------------------------*/
        SecureRandom random = new SecureRandom();
        System.out.println("-------------------------------------------------\n" +
                "DEPOSIT/WITHDRAW/MGMT VALIDATION\n" +
                "-------------------------------------------------\n");
        //deposit, withdraw and monthly management with random value's
        for (BankAccount account : acc) {
            account.deposit(random.nextInt(1000));
            System.out.print("After Deposit, ");
            System.out.println(account);
            account.withdraw(random.nextInt(500));
            System.out.print("After withdraw, ");
            System.out.println(account);
            account.monthlyMgmt();
            System.out.print("After management, ");
            System.out.println(account);
        }
        System.out.println("----------------------------------------------------------\n" +
                "END OF DEPOSIT/WITHDRAW/MGMT VALIDATION\n" +
                "----------------------------------------------------------\n");
    }
}

