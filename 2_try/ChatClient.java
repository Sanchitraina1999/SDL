import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient extends Chat{

    public static void cc() {
        String sentence;
        String sentenceFromServer;
        Client c;
        Scanner input = new Scanner(System.in);

        try {

            System.out.print("Enter your name : ");
            String name = input.nextLine();

            Socket clientSocket = new Socket("localhost", 6066);
            System.out.println("Connected to Server. Start Chatting with AGENT:");

            c = new Client();
            c.personName = name;

            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream outToServer =new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            OutputStream o = clientSocket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(o); 
            out.writeObject(c);

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
