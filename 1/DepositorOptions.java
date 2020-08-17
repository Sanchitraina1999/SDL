public class DepositorOptions extends Depositor {
    public void display() {
        System.out.println("1. List of all my Accounts");
        System.out.println("2. Add my new Account");
        System.out.println("3. Delete my Account");
        System.out.println("4. EXIT");
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
                System.out.println("Invalid choice :( Please select a valid choice :)  You are logged out now\n");
        }
    }
}