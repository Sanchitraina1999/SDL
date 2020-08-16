package atm;

import java.text.DecimalFormat;
import java.util.*;

public class account {
    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    public int setCustomerID(int customerID) {
        this.customerID = customerID;
        return customerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int setPinNumber(int PinNumber) {
        this.PinNumber = PinNumber;
        return PinNumber;
    }

    public int getPinNumber() {
        return PinNumber;
    }

    public double getCheckingsBalance() {
        return checkingsBalance;
    }

    public double getSavingsBalance() {
        return savingsBalance;
    }

    public double calcCheckingWithdraw(double amount) {
        checkingsBalance -= amount;
        return checkingsBalance;
    }

    public double calcSavingWithdraw(double amount) {
        savingsBalance -= amount;
        return savingsBalance;
    }

    public double calcCheckingDeposit(double amount) {
        checkingsBalance += amount;
        return checkingsBalance;
    }

    public double calcSavingDeposit(double amount) {
        savingsBalance += amount;
        return savingsBalance;
    }

    public void getCheckingWithdawFunds() {
        System.out.println("here");
    }

    public void getSavingsWithdawFunds() {
        System.out.println("here");
    }

    public void getCheckingDepositFunds() {
        System.out.println("here");
    }

    public void getSavingsDepositFunds() {
        System.out.println("here");
    }

    private int customerID;
    private int PinNumber;
    private double checkingsBalance = 0;
    private double savingsBalance = 0;
}
