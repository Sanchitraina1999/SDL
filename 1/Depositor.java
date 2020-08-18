import java.util.*;
import java.util.Map.Entry;

public class Depositor extends MainMenu {
    public static class Details{
        public String KYC;
        public String Name;
        public Long MobileNumber;
    }
    public static String Id;
    public static String Pin;

    // Related to Login
    public static HashMap<String, String> depositors = new HashMap<String, String>(); // <DepositorUsername,DepositorPassword>

    // Depositor and Details
    public static Set<String> KYCs = new HashSet<String>(); // for unique KYCs
    public static Vector<Details> DepositorsDetails = new Vector<Details>(); // Depositor Details {KYC, Name, MobileNumber}

    // Depositors and their Accounts 
    public static Set<String> AccountNumbers = new HashSet<String>(); // for unique Account Numbers
    public static HashMap<String, Vector<Integer>> KYCtoAccounts = new HashMap<String, Vector<Integer>>(); // <KYC Number,Vector of Account Numbers>

    public static void addDepositor(String randomkyc, String name, Long mobile_number,String id, String pin) {
        Details details = new Details();
        details.KYC = randomkyc;
        details.Name = name;
        details.MobileNumber = mobile_number;

        KYCs.add(randomkyc);    //added current KYC to the Set
        DepositorsDetails.add(details); //added Depositors Details to Vector of Details
        depositors.put(id, pin);    //added <ID,Password> to HashMap
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
            System.out.println("You were logged in as AGENT. Now logging out AGENT...");
            bs.clear(0);
            bs.clear(1);
            bs.clear(2);
        }
        if (nsb == 2) {
            System.out.println("\nYou are already logged in as DEPOSITOR");
            DepositorOptions options = new DepositorOptions();
            options.display();
        } else {
            if (depositors.size() == 0) {
                System.out.println("\nNo Depositors found!. Ask for your Login Credentials from Agent ");
                return;
            }
            else{
                boolean validLogin = false;
                System.out.println();
                System.out.println(centerString(70, "Welcome to Depositor Portal"));
                System.out.println();
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
                    System.out.println();
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

    public void DeleteDepositer() {
        System.out.println("Delete Depositer here\n");
    }

    public void Logout() {
        bs.clear(0);
        bs.clear(1);
        bs.clear(2);
        System.out.print("\nDepositor Logged Out!\n");
    }

    public static void RegisterNewDepositor() {
        String randomkyc = RandomStringGen.getAlphaNumericString();
        while(KYCs.contains(randomkyc)){
            randomkyc = RandomStringGen.getAlphaNumericString();
        }
        System.out.print("\nEnter your Name: ");
        String name = input.nextLine();
        System.out.print("Enter your Mobile Number: ");
        Long  mobile_number = input.nextLong();

        // Skip the newline after inputing Int. Case of "Enter"
        input.nextLine();

        System.out.print("Set your Depositor Login ID: ");
        String id = input.nextLine();
        System.out.print("Set your Secret PIN: ");
        String pin = input.nextLine();
        addDepositor(randomkyc,name,mobile_number,id, pin);
    }
}