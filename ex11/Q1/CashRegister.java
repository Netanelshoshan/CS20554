/*
* @ Netanel Shoshan @
* Function's and data structure related to the program.
*/
import java.util.*;


public class CashRegister {

    /* cashInReg - the amount of cash in the register.
     * totalToPay - the total amount that need's to be paid.
     * bills - the client shopping cart  */
    private float cashInReg, totalToPay;
    private ArrayList<LineInBill> bills;

    // initalizes the cash register as empty.
    public CashRegister() {
        cashInReg = 0;
        totalToPay = 0;
        bills = new ArrayList<>();

    }
    // initializes the cash register with the given value. 
    public CashRegister(float initAmount) {
        cashInReg = initAmount;
        totalToPay = 0;
        bills = new ArrayList<>();
    }

    // return's an array containing the item's for the currect purchase.
    public LineInBill[] getPurchaseDetails() {
        LineInBill[] l = new LineInBill[bills.size()];
        bills.toArray(l);
        return l;
    }

    // add's new product to the cart and validates that there aren't duplicates.
    // (ERROR CHECKING IS BASED ON CORRECT VALUES ONLY)
    public void addProductToCart(Product pToAdd, float am) {

        //LineInBill line = new LineInBill(pToAdd, am);
        if (bills.isEmpty())
            bills.add(new LineInBill(pToAdd, am));
        else {
            for (int i = 0; i < bills.size(); i++) {
                LineInBill line = bills.get(i);
                if (line.getP().getName().equals(pToAdd.getName())) {
                    line.setAmount(line.getAmount() + am);
                    line.total_price = line.getAmount() * line.getP().getPrice();
                    return;
                } else {
                    if (i == bills.size() - 1) {
                        bills.add(new LineInBill(pToAdd, am));
                        return;
                    } else {
                        continue;
                    }
                }
            }


        }
    }

    // calculates how much the client need to pay.
    public float getTotalToPay() {
        float total = 0;
        for (LineInBill p : bills) {
            total += p.getTotal_price();
        }
        return total;
    }

    // get's the amount of case given as payment and return's the change th
    public float payment(float cash) {
        float total = getTotalToPay(),
                change = cash - total;
        if (change < 0) {
            throw new Error("Change must have positive value. Please provide the correct amount.\n");
        }
        cashInReg += total;
        totalToPay += total;
        bills = new ArrayList<>();

        return change;
    }

    // return's the current total cash in register
    public float getCurrCashInReg() {
        return cashInReg;
    }

}
