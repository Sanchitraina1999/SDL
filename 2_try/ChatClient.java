import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient extends Chat{

    public static void cc() {
        String sentence;
        String sentenceFromServer;
        String filename = "clientName.txt"; 
        Scanner input = new Scanner(System.in);

        Socket clientSocket;
        try {

            System.out.print("Enter your name : ");

            Client c = new Client();
            c.personName = input.nextLine();
            FileOutputStream file = new FileOutputStream(filename); 
            ObjectOutputStream out = new ObjectOutputStream(file); 
            out.writeObject(c);
            out.close(); 
            file.close();

            clientSocket = new Socket("localhost", 6066);
            System.out.println("Connected to Server. Start Chatting with AGENT:");

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream outToServer =new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while(true)
            {
                // System.out.println("["+c.personName+"]: ");
                sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + '\n');
                sentenceFromServer = inFromServer.readLine();
                System.out.println("[AGENT]: " +sentenceFromServer);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
