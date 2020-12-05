public class DepositorOptions extends Depositor {
    public void display() {
        System.out.println("\n1. List of all my PORD Accounts");
        System.out.println("2. Add my new PORD Account");
        System.out.println("3. Delete my PORD Account");
        System.out.println("4. Logout Depositor");
        System.out.println("5. EXIT");
        System.out.print("Choose one of the above:");
        String choice = input.nextLine();
        switch (choice) {
            case "1":
                ListAccounts();
                break;
            case "2":
                AddAccount();
                break;
            case "3":
                DeleteAccount();
                break;
            case "4":
                Logout();
                break;
            case "5":
                MainMenu.Exit();
            default:
                System.out.println("Invalid choice :( Please select a valid choice :)\n");
        }
    }
}