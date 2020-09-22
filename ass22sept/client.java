import java.io.*;
import java.net.*;
import java.util.*;

class Details implements Serializable {
    private static final long serialVersionUID = 1L;
    int principal, roi, duration;
    Details(int principal,int roi,int duration){
        this.principal = principal;
        this.roi = roi;
        this.duration = duration;
    }
}

public class client{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int p,r,t;
        
        System.out.print("Enter Principal Amount : ");
        p = input.nextInt();
        System.out.print("Enter Rate of Interest : ");
        r = input.nextInt();
        System.out.print("Enter Duration : ");
        t = input.nextInt();

        Details d=new Details(p,r,t);

        String sentenceFromServer;
        try {

            Socket clientSocket = new Socket("localhost", 6066);
            System.out.println("**Connected to Server**");

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
 
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream()); 
            out.writeObject(d);

            sentenceFromServer = inFromServer.readLine();
            System.out.println("[Simple Interest is]: " + sentenceFromServer);
            input.close();
            clientSocket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
