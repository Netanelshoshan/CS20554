
/*
 * @ Netanel Shoshan @
 *
 */
public abstract class BankAccount {
    // variables
    protected String accNo, accName, ownerId;
    protected double balance;

    // 1st constructor
    public BankAccount(String accNo, String name, String ownerId, double balance) {
        this.accNo = accNo;
        this.accName = name;
        this.ownerId = ownerId;
        if (balance < 0.0) {
            throw new IllegalArgumentException("The balance must be a positive number.");
        } else {
            this.balance = balance;
        }
    }

    public BankAccount() {
        accNo = "";
        accName = "";
        ownerId = "";
        balance = 0;
    }

    //---------------------setters and getters ------------------------//
    public String getOwnerId() {
        return ownerId;
    }

    public String getAccName() {
        return accName;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }


    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    //deposit function that validates that the amount is bigger then 0, otherwise -
    // will output a messege
    public void deposit(double depositAmount) {
        try {
            if (depositAmount < 0.0)
                throw new IllegalArgumentException();
            balance += depositAmount;
        } catch (IllegalArgumentException i) {
            System.out.println("deposit amount must be a positive number!");
        }
    }

    //withdraw function with error checking
    public void withdraw(double withdrawAmount) {
        try {
            if (this.balance - withdrawAmount < 0.0)
                throw new IllegalBalance();
            this.balance -= withdrawAmount;
        } catch (IllegalBalance i) {
            System.out.println("insufficient funds for withdrawal.");
        }
    }
    // for implementation in later use
    public abstract void monthlyMgmt();

    // the basis of all account detail.
    @Override
    public String toString() {
        return "Account Details:\n--------------\n" + "Account number : " +
                accNo + "\nOwner: " + accName + "\nOwner ID : " +
                ownerId + "\nBalance : " + balance + "\n";
    }


    // function that check if two account are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount)) return false;
        BankAccount that = (BankAccount) o;
        return Double.compare(that.balance, balance) == 0 && accNo.equals(that.accNo) && accName.equals(that.accName) && ownerId.equals(that.ownerId);
    }

}
