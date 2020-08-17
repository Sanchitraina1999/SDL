package atm;

import java.text.DecimalFormat;
import java.util.*;

public class account {
    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'₹'###,##0.00");

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setPinNumber(int PinNumber) {
        this.PinNumber = PinNumber;
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
    `
    public double calcCheckingWithdraw(double amount) {
        checkingsBalance -= amount;
        System.out.println("\n"+moneyFormat.format(amount)+" withdrawn from Checking Account!");
        return checkingsBalance;
    }

    public double calcSavingWithdraw(double amount) {
        savingsBalance -= amount;
        System.out.println("\n"+moneyFormat.format(amount)+" withdrawn from Saving Account!");
        return savingsBalance;
    }

    public double calcCheckingDeposit(double amount) {
        checkingsBalance += amount;
        System.out.println("\n"+moneyFormat.format(amount)+" deposited in Checking Account!");
        return checkingsBalance;
    }

    public double calcSavingDeposit(double amount) {
        savingsBalance += amount;
        System.out.println("\n"+moneyFormat.format(amount)+" deposited in Saving Account!");
        return savingsBalance;
    }

    public void getCheckingWithdawFunds() {
        System.out.println("\nChecking Account Balance: " + moneyFormat.format(checkingsBalance));
        System.out.println("Enter Amount you want to withdraw from Checking Account: ");
        double amt = input.nextDouble();
        if((checkingsBalance-amt)>=0){
            calcCheckingWithdraw(amt);
            System.out.println("New Checking Account Balance: "+ moneyFormat.format(checkingsBalance));
        }
        else{
            System.out.println("Balance can't be negative");
        }
    }

    public void getSavingsWithdawFunds() {
        System.out.println("\nSaving Account Balance: " + moneyFormat.format(savingsBalance));
        System.out.println("Enter Amount you want to withdraw from Saving Account: ");
        double amt = input.nextDouble();
        if((savingsBalance-amt)>=0){
            calcSavingWithdraw(amt);
            System.out.println("New Saving Account Balance: "+ moneyFormat.format(savingsBalance));
        }
        else{
            System.out.println("Balance can't be negative");
        }
    }

    public void getCheckingDepositFunds() {
        System.out.println("\nChecking Account Balance: " + moneyFormat.format(checkingsBalance));
        System.out.println("Enter Amount you want to deposit to Checking Account: ");
        double amt = input.nextDouble();
        if(amt>=0){
        	calcCheckingDeposit(amt);
            System.out.println("New Checking Account Balance: "+ moneyFormat.format(checkingsBalance));
        }
        else{
            System.out.println("Deposit can't be negative");
        }
    }

    public void getSavingsDepositFunds() {
        System.out.println("\nSaving Account Balance: " + moneyFormat.format(savingsBalance));
        System.out.println("Enter Amount you want to deposit to Saving Account: ");
        double amt = input.nextDouble();
        if(amt>=0){
        	calcSavingDeposit(amt);
            System.out.println("New Saving Account Balance: "+ moneyFormat.format(savingsBalance));
        }
        else{
            System.out.println("Deposit can't be negative");
        }
    }

    private int customerID;
    private int PinNumber;
    private double checkingsBalance = 0;
    private double savingsBalance = 0;
}
