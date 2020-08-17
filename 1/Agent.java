import java.util.HashMap;
import java.util.Map.Entry;

public class Agent extends MainMenu {
    public static String Id;
    public static String Pin;
    public static HashMap<String, String> agents = new HashMap<String, String>(); // <AgentUsername, AgentPassword>

    public static void addAgent(String id, String pin) {
        agents.put(id, pin);
    }

    public static void setId(String id) {
        Id = id;
    }

    public static String getId() {
        return Id;
    }

    public static void setPin(String pin) {
        Pin = pin;
    }

    public static String getPin() {
        return Pin;
    }

    public static void AgentLogin() {
        if (agents.size() == 0) {
            System.out.println("\nNo Agents found!. Register as a new Agent here ");
            System.out.print("Set your Agent Login ID: ");
            String id = input.nextLine();
            System.out.print("Set your Secret PIN: ");
            String pin = input.nextLine();
            addAgent(id, pin);
        }
        boolean validLogin = false;

        System.out.println(centerString(70, "Welcome to Agent Portal"));
        System.out.print("Enter your Agent Login ID: ");
        String id = input.nextLine();
        setId(id);
        System.out.print("Enter your Secret PIN: ");
        String pin = input.nextLine();
        setPin(pin);

        for (Entry<String, String> entry : agents.entrySet()) {
            if (entry.getKey().equals(getId()) && entry.getValue().equals(getPin())) {
                validLogin = true;
                break;
            }
        }
        if (validLogin) {
            System.out.println(centerString(70, "*****Logged in as Agent*****\n\n"));
            AgentOptions options = new AgentOptions();
            options.display();
        } else {
            System.out.println("\n" + "Invalid Login Details.");
        }

    }

    public void ListDepositors() {
        System.out.println("Display List of Depositors here\n");
    }

    public void AddAccount() {
        System.out.println("Add Account here\n");
    }
}