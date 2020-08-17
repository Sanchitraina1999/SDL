import java.util.Scanner;

public class MainMenu {
    Scanner input = new Scanner(System.in);

    public static String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void display() {
        System.out.println(centerString(70, "Post Office Recurring Deposit"));
        System.out.println();
        System.out.println("1. Agent Login");
        System.out.println("2. Depositor Login");
        System.out.println("3. EXIT");
        System.out.println("\nChoose one of the above options: ");
        int choice = input.nextInt();
        error: {
            switch (choice) {
                case 1:
                    // AgentLogin();
                case 2:
                    // DepositorLogin();
                case 3:
                    try {
                        Thread.sleep(1000);
                        System.out.print("Exiting in 3...");
                        Thread.sleep(1000);
                        System.out.print("2...");
                        Thread.sleep(1000);
                        System.out.println("1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
                    break error;
            }
        }
    }
}