public class AgentOptions extends Agent {
    public void display() {
        System.out.println("1. List all Depositors' details");
        System.out.println("2. Add new Account");
        System.out.println("3. EXIT");
        System.out.print("Choose one of the above:");
        String choice = input.nextLine();
        switch (choice) {
            case "1":
                ListDepositors();
                break;
            case "2":
                AddAccount();
                break;
            case "3":
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