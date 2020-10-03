import java.sql.SQLException;

public class AgentOptions extends Agent {
    public void display() throws ClassNotFoundException, SQLException {
        System.out.println("\n1. List all Depositor's details");
        System.out.println("2. GENERATE ALL Depositor's REPORTS here");
        System.out.println("3. Add new Depositor");
        System.out.println("4. Add new Account");
        System.out.println("5. Logout Agent");
        System.out.println("6. EXIT");
        System.out.print("Choose one of the above:");
        String choice = input.next();
        switch (choice) {
            case "1":
                ListDepositors();
                break;
            case "2":
                REPORTS();
                break;
            case "3":
                AddDepositor();
                break;
            case "4":
                AddAccount();
                break;
            case "5":
                Logout();
                break;
            case "6":
                MainMenu.Exit();
            default:
                System.out.println("Invalid choice :( Please select a valid choice :) \n");
        }
    }
}