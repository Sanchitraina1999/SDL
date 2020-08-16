package atm;

import java.text.DecimalFormat;
import java.io.IOException;
import java.util.*;

public class optionMenu extends account {
    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();

    public void getLoginDetails() throws IOException {
        int x = 1;
        do {
            try {
                data.put(9876543, 9876);
                data.put(8989898, 1890);

                System.out.println("Welcome to ATM Project");
                System.out.print("Enter your Customer ID:");
                setCustomerID(menuInput.nextInt());
                System.out.print("Enter your PIN Number:");
                setPinNumber(menuInput.nextInt());
            } catch (Exception e) {
                System.out.println("\n" + "Invalid Character(s). ");
                x = 2;
            }
            for (Entry<Integer, Integer> entry : data.entrySet()) {
                if (entry.getKey() == getCustomerID() && entry.getValue() == getPinNumber()) {
                    getAccountType();
                }
            }
            System.out.println("\n" + "Authencation Error. ");
        } while (x == 1);
    }

    public void getAccountType() {
        System.out.println("Select the account type you want to Access: ");
        System.out.println("1 - Checking Account");
        System.out.println("2 - Savings Account");
        System.out.println("3 - EXIT");
        System.out.print("Choice: ");

        int option = menuInput.nextInt();
        switch (option) {
            case 1:
                getChecking();
                break;
            case 2:
                getSavings();
                break;
            case 3:
                System.out.println("Exiting...\n");
                break;
            default:
                System.out.println("\nInvalid Choice\n");
        }
    }

    public void getChecking() {
        System.out.println("Checking Account");
        System.out.println("1 - View Balance");
        System.out.println("2 - Withdraw Funds");
        System.out.println("3 - Deposit Funds");
        System.out.println("4 - EXIT");
        System.out.print("Choice :");

        int selection = menuInput.nextInt();
        switch (selection) {
            case 1:
                System.out.println("Checking Account Balance: " + moneyFormat.format(getCheckingBalance()));
                getAccountType();
                break;
            case 2:
                getCheckingWithdawFunds();
                getAccountType();
                break;
            case 3:
                getCheckingDepositFunds();
                getAccountType();
                break;
            case 4:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid Choice. ");
                getChecking();
        }
    }

    public void getSavings() {
        System.out.println("Savings Account");
        System.out.println("1 - View Balance");
        System.out.println("2 - Withdraw Funds");
        System.out.println("3 - Deposit Funds");
        System.out.println("4 - EXIT");
        System.out.print("Choice :");

        int selection = menuInput.nextInt();
        switch (selection) {
            case 1:
                System.out.println("Savings Account Balance: " + moneyFormat.format(getSavingsBalance()));
                getAccountType();
                break;
            case 2:
                getSavingsWithdawFunds();
                getAccountType();
                break;
            case 3:
                getCSavingsDepositFunds();
                getAccountType();
                break;
            case 4:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid Choice. ");
                getSavings();
        }
    }
}