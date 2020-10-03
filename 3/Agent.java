import java.sql.*;
import java.util.*;
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

    public static void AgentLogin() throws ClassNotFoundException, SQLException {
        int nsb = bs.nextSetBit(0);
        if (nsb == 2) {
            System.out.println("You were logged in as DEPOSITOR. Now logging out DEPOSITOR...");
            bs.clear(0);
            bs.clear(1);
            bs.clear(2);
        }
        if (nsb == 1) {
            System.out.println("You are already logged in as AGENT");
            AgentOptions options = new AgentOptions();
            options.display();
        } else {
            if (agents.size() == 0) {
                addAgent("agent", "agent"); // agentLogin
            }
            boolean validLogin = false;
            System.out.println();
            System.out.println(centerString(70, "Welcome to Agent Portal"));
            System.out.print("\nEnter your Agent Login ID: ");
            String id = input.next();
            setId(id);
            System.out.print("Enter your Secret PIN: ");
            String pin = input.next();
            setPin(pin);
            System.out.println();

            for (Entry<String, String> entry : agents.entrySet()) {
                if (entry.getKey().equals(getId()) && entry.getValue().equals(getPin())) {
                    validLogin = true;
                    break;
                }
            }
            if (validLogin) {
                System.out.println(centerString(70, "*****Logged in as Agent*****\n\n"));
                bs.clear(0);
                bs.set(1);
                bs.clear(2);
                AgentOptions options = new AgentOptions();
                options.display();
            } else {
                System.out.println("\n" + "Invalid Login Details.");
            }
        }
    }

    public void ListDepositors() {
        System.out.println("\n");
        Vector<Depositor.Details> list = Depositor.DepositorsDetails;
        if (list.isEmpty()) {
            System.out.println("No Depositors found!\n");
            return;
        }

        System.out.println("KYC\t\tName\t\tMobile Number\n");
        list.forEach((n) -> System.out.println(n.KYC + "\t" + n.Name + "\t\t" + n.MobileNumber));
        System.out.println("\n");
    }

    public void AddAccount() throws ClassNotFoundException, SQLException {
        // System.out.println("Add Account here\n");
        System.out.print("Do you want to Add Account for Existing Depositor(y/n): ");
        char choice = input.next().charAt(0);
        if (choice == 'y') {
            String kycProvided;
            System.out.print("Provide KYC of Existing User: ");
            kycProvided = input.next();
            if (Depositor.KYCs.contains(kycProvided)) {
                Depositor.AddAccountWithKYC(kycProvided);
            } else {
                System.out.println("\n\t\tNo Such KYC Exists\n");
            }
        } else {
            System.out.println("REGISTER new Depositor first: ");
            String kycProvided = Depositor.AddDepositorWithReturnKYC();
            Depositor.AddAccountWithKYC(kycProvided);
        }
    }

    public void AddDepositor() throws ClassNotFoundException, SQLException {
        Depositor.RegisterNewDepositor();
    }

    public void Logout() {
        bs.clear(0);
        bs.clear(1);
        bs.clear(2);
        System.out.print("\nAgent Logged Out!\n");
    }

    public void REPORTS() throws ClassNotFoundException, SQLException {
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/";
        String USER = "root";
        String PASS = "root";
        ResultSet rs = null;
        Connection conn = null;
        Statement stmt = null;
        String sql;
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to MYSQL...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String dbname = "sdl_assignment_3";

            sql = "USE "+dbname;
            stmt.execute(sql);

            sql = "SELECT * FROM depositors_accounts";
            rs = stmt.executeQuery(sql);

            int sno=1;
            System.out.println("S_No."+"\t\t"+"KYC" + "\t\t" + "NAME" + "\t\t" + "Number_of_Accounts");
            while(rs.next()){
                System.out.println(sno+"\t\t"+rs.getString(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getInt(3));
                sno++;
            }
        } finally {
            stmt.close();
            conn.close();
        }
    }
}