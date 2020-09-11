public class Chat extends MainMenu {
    public static void chatwindow(){
        System.out.println();
        System.out.println(centerString(70, "Chat Portal"));
        System.out.println();
        System.out.println("1. Start Server as Agent");
        System.out.println("2. Chat as Depositor");
        System.out.println("3. EXIT");
        System.out.print("\nChoose one of the above options: ");
        String choice = input.nextLine();
        switch (choice) {
            case "1":
                ChatServer.cs();
                break;
            case "2":
                ChatClient.cc();
                break;
            case "3":
                MainMenu.Exit();
            default:
                System.out.println("Invalid choice :( Please select a valid choice :).\n");
        }
    }    
}
