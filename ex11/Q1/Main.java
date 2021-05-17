/*
* @ Netanel Shoshan @
* main class for running the program and presenting the graphical menu.
*/

import javax.swing.*;
import java.util.Arrays;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;

public class Main {
	
    private static final int ADD_PRODUCT=1 , PRODUCT_LIST=2, TOTAL_PAYMENT=3, PAYMENT=4, CURRENT_CASH_IN_REGISTER=5,
            EXIT=6;
    private static final String CR_MENU = "Please choose one of the following methods:" + "\n1 - Add new product to shopping cart"
            + "\n2 - Shopping cart list" + "\n3 - Total to pay" + "\n4 - Payment" + "\n5 - Cash Register account status"
            + "\n6 - Exit";


    // main function
    public static void main(String[] args) {
        String initialCash = JOptionPane.showInputDialog("Please inset the initial money in the Cash Register:");
        CashRegister CashRegister;


        if (initialCash != null) {
            CashRegister = new CashRegister(Float.parseFloat(initialCash));
        } else {
            CashRegister = new CashRegister();
        }

        int answer = 0;
        while (answer != EXIT) {
            answer = Integer.parseInt(JOptionPane.showInputDialog(CR_MENU));
            commandInterpreter(answer, CashRegister);
        }
    }

    // command interpreter function
    private static void commandInterpreter(int answer, CashRegister register) {
        switch (answer) {

            case ADD_PRODUCT:
                String name = JOptionPane.showInputDialog("Please insert the product name:");
                float price = Float.parseFloat(JOptionPane.showInputDialog("Please insert the product price:"));
                float amount = Float.parseFloat(JOptionPane.showInputDialog("Please insert the amount:"));
                register.addProductToCart(new Product(name, price),amount);
                JOptionPane.showMessageDialog(null, "The product " + name + " added to shopping cart");
                break;

            case PRODUCT_LIST:
                LineInBill[] bill = register.getPurchaseDetails();
                JOptionPane.showMessageDialog(null, bill , "Your product list", INFORMATION_MESSAGE);
                break;

            case TOTAL_PAYMENT:
                JOptionPane.showMessageDialog(null, "Total to pay: " + register.getTotalToPay());
                break;

            case PAYMENT:
                float client_cash = Float.parseFloat(JOptionPane.showInputDialog("Please enter the amount provided be the client :"));
                if (client_cash < register.getTotalToPay()) {
                    JOptionPane.showMessageDialog(null, "The amount provided isn't enough.\n");
                } else{
                    JOptionPane.showMessageDialog(null, "Thank you about your payment.\n\tThe change will be: " + register.payment(client_cash));
                }
                break;

            case CURRENT_CASH_IN_REGISTER:
                JOptionPane.showMessageDialog(null, "The cash status is: " + register.getCurrCashInReg());
                break;

            default:
                if(answer != EXIT){
                    JOptionPane.showMessageDialog(null, "Illegal option, please try again.");
                }

                break;
        }

    }
}
