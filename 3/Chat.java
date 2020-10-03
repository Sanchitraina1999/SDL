import java.io.Serializable;

class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    public String personName;
}

public class Chat extends MainMenu {
    public static void chatwindow() {
        System.out.println();
        System.out.println(centerString(70, "Chat Portal"));
        System.out.println();
        System.out.println("1. Chat as Agent (Start Server)");
        System.out.println("2. Chat as Depositor");
        System.out.println("3. EXIT");
        System.out.print("\nChoose one of the above options: ");
        String choice = input.next();
        switch (choice) {
            case "1":
                ChatServer.cs();
                break;
            case "2":
                ChatClient.cc();
                try {
                    Thread.sleep(2000 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case "3":
                MainMenu.Exit();
            default:
                System.out.println("Invalid choice :( Please select a valid choice :).\n");
        }
    }   
}
