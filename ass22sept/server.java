import java.io.*;
import java.net.*;

public class server{
    public static void main(String[] args){
        String sentence;
        try {
            ServerSocket serverSocket = new ServerSocket(6066);
            System.out.println("ServerSocket awaiting connections...");
            Socket connectionSocket = serverSocket.accept();
            System.out.println("Connection with " + connectionSocket.getLocalPort() + " established.");

            DataOutputStream outClient = new DataOutputStream(connectionSocket.getOutputStream());

            ObjectInputStream in = new ObjectInputStream(connectionSocket.getInputStream());
    
            Details obj = (Details)in.readObject();

            int p = obj.principal;
            int r=obj.roi;
            int t=obj.duration;

            sentence = Integer.toString((p*r*t)/100);
            outClient.writeBytes(sentence + '\n');

            connectionSocket.close();
            serverSocket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }
}

// Write Client Server Program where you are entering 
// Principal, Rate of Interest, Duration from Client, Send it
//  to Server by serialization and calculate simple interest on
//   server side. Server will send simple interest value to client.