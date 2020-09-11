import java.io.IOException;
import java.util.*;

public class MainMenu extends Main {
    public static Scanner input = new Scanner(System.in);

    public static String centerString(int width, String s) {
        return String.format("%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }

    public void display() throws IOException {
        System.out.println();
        System.out.println(centerString(70, "Post Office Recurring Deposit"));
        System.out.println();
        System.out.println("1. Agent Portal");
        System.out.println("2. Depositor Portal");
        System.out.println("3. Chat Portal");
        System.out.println("4. EXIT");
        System.out.print("\nChoose one of the above options: ");
        String choice = input.nextLine();
        switch (choice) {
            case "1":
                Agent.AgentLogin();
                break;
            case "2":
                Depositor.DepositorLogin();
                break;
            case "3":
                Chat.chatwindow();
                try {
                    Thread.sleep(10000*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case "4":
                Exit();
            default:
                System.out.println("Invalid choice :( Please select a valid choice :).\n");
        }
    }

    public static void Exit() {
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
    }
}