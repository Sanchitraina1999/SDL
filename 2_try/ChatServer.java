import java.io.*;
import java.net.*;

public class ChatServer extends Chat{
    public static void cs() {
        String sentenceFromClient;
        String filename = "clientName.txt"; 
        String sentence;
        try {
            ServerSocket serverSocket = new ServerSocket(6066);
            System.out.println("ServerSocket awaiting connections...");
            Socket connectionSocket = serverSocket.accept();
            System.out.println("Connection with " + connectionSocket.getLocalPort() + " established");

            BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outClient = new DataOutputStream(connectionSocket.getOutputStream());

            FileInputStream file = new FileInputStream(filename); 
            ObjectInputStream in = new ObjectInputStream(file); 
            
            Client obj = null;
            obj = (Client)in.readObject(); 
            in.close(); 
            file.close();

            while(true){
                // System.out.println("[AGENT]: ");
                sentenceFromClient = fromClient.readLine();
                System.out.println("["+  obj.personName +"]:" + sentenceFromClient);
                sentence = fromUser.readLine();
                outClient.writeBytes(sentence + '\n');
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }
}