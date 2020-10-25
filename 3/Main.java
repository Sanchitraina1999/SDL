import java.io.IOException;
import java.sql.*;
import java.util.BitSet;

public class Main {
    public static BitSet bs = new BitSet(3);

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        // 0 - for no one logged in!
        // 1 - for AGENT logged in!
        // 2 - for DEPOSITOR logged in!
        MainMenu mainMenu = new MainMenu();
        bs.set(0);
        CREATE_DATABASE();
        while (true) {
            int nsb = bs.nextSetBit(0);
            switch (nsb) {
                case 1:
                    System.out.println("\nCurrently Logged in as AGENT");
                    break;
                case 2:
                    System.out.println("\nCurrently Logged in as DEPOSITOR");
                    break;
            }
            mainMenu.display();
        }
    }

    private static void CREATE_DATABASE() throws ClassNotFoundException, SQLException {
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
        String DB_URL = "jdbc:mysql://localhost/";
        String USER = "root";
        String PASS = "root";
        Connection conn = null;
        Statement stmt = null;
        String sql;
        try{
            Class.forName(JDBC_DRIVER);
            
            System.out.println("Connecting to MYSQL...");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String dbname = "sdl_assignment_3";
            
    /*DATABASE CREATION*/
            System.out.println("CREATING DATABASE "+dbname+"...");
            sql = "CREATE DATABASE "+dbname;
            stmt.executeUpdate(sql);
            
            sql="USE "+dbname;
            stmt.execute(sql);
            
    /*TABLE CREATIONS*/
            System.out.println("CREATING TABLE depositors_accounts ...");
            sql = "CREATE TABLE depositors_accounts("+
                "KYC VARCHAR(255) NOT NULL,"+
                "dep_name VARCHAR(255) NOT NULL,"+
                "number_of_accounts int NOT NULL default 0,"
                +"PRIMARY KEY(kyc));";
            stmt.execute(sql);
        }
        finally{
            stmt.close();
            conn.close();
        }
    }
}

/*
 * 
 * DS used -
 *
 * BISET
 * HashMap
 * Set
 * Vector
 * List
 * 
 */