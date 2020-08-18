import java.util.HashMap;
import java.util.Map.Entry;

public class Depositor extends MainMenu {
    public static String Id;
    public static String Pin;
    public static HashMap<String, String> depositors = new HashMap<String, String>(); // <DepositorUsername,DepositorPassword>

    public static void addDepositor(String id, String pin) {
        depositors.put(id, pin);
    }

    public static void setDepositorId(String id) {
        Id = id;
    }

    public static String getDepositorId() {
        return Id;
    }

    public static void setDepositorPin(String pin) {
        Pin = pin;
    }

    public static String getDepositorPin() {
        return Pin;
    }
    
    public static void DepositorLogin() {
        int nsb = bs.nextSetBit(0);
        if (nsb == 1) {
            System.out.println("You were logged in as AGENT. Now logging out AGENT");
            bs.clear(0);
            bs.clear(1);
            bs.clear(2);
        }
        if (nsb == 2) {
            System.out.println("You are already logged in as DEPOSITOR");
            DepositorOptions options = new DepositorOptions();
            options.display();
        } else {
            if (depositors.size() == 0) {
                System.out.println("\nNo Depositors found!. Register as a new Depositor here ");
                System.out.print("Set your Depositor Login ID: ");
                String id = input.nextLine();
                System.out.print("Set your Secret PIN: ");
                String pin = input.nextLine();
                addDepositor(id, pin);
            }
            boolean validLogin = false;

            System.out.println(centerString(70, "Welcome to Depositor Portal"));

            System.out.print("Enter your Depositor Login ID: ");
            String depositorId = input.nextLine();
            setDepositorId(depositorId);

            System.out.print("Enter your Secret PIN: ");
            String depositorPin = input.nextLine();
            setDepositorPin(depositorPin);

            for (Entry<String, String> entry : depositors.entrySet()) {
                if (entry.getKey().equals(getDepositorId()) && entry.getValue().equals(getDepositorPin())) {
                    validLogin = true;
                    break;
                }
            }

            if (validLogin) {
                System.out.println(centerString(70, "*****Logged in as Depositor*****\n\n"));
                bs.clear(0);
                bs.clear(1);
                bs.set(2);
                DepositorOptions options = new DepositorOptions();
                options.display();
            } else {
                System.out.println("\n" + "Invalid Login Details.");
            }
        }
    }

    public void ListAccounts() {
        System.out.println("Display List of Accounts of here\n");
    }

    public void AddAccount() {
        System.out.println("Add new Account here\n");
    }

    public void DeleteAccount() {
        System.out.println("Delete Account here\n");
    }

    public void DeleteDepositer(){
        System.out.println("Delete Depositer here\n");
    }
    public void Logout(){
        System.out.println("Logout Depositer here\n");
    }
}                                                                                                                   