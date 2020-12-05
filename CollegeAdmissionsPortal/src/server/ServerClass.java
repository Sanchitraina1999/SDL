package server;

import java.io.*;
import java.net.*;

class clientHandler extends Thread{
	Socket s;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	public clientHandler(Socket s, ObjectOutputStream oos, ObjectInputStream ois) {
		// TODO Auto-generated constructor stub
		this.s = s;
		this.oos = oos;
		this.ois = ois;
	}
	
	public void run()
	{
		while(true)
		{
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String msg = br.readLine();
				
				if(msg.equalsIgnoreCase("Exit"))
				{
					this.s.close();
					break;
				}
				else
					System.out.println(Thread.currentThread().getName() + " : " + msg);
				
				
			} catch (Exception e) {
				System.out.println("Connection Closed With : " + Thread.currentThread().getName());
				break;
			}
			
			
		}
	}
}


public class ServerClass {

	public static void main(String[] args) throws Exception {
		
		System.out.println("Starting Server");
		
		ServerSocket server = new ServerSocket(8000);
		
		System.out.println("Server Started ");
		
		int cnt = 1;
		while(true)
		{
			Socket client = null;
			
			try {
				
				client = server.accept();
				ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				
				Thread t = new clientHandler(client, oos, ois);
				t.start();
				
				
				t.setName("Client - " + cnt);
				cnt++;
				System.out.println("A new Client Connected : " + t.getName());
				
				
			} catch (Exception e) {
				System.out.println("Client Dissconnected");
			}
			
		}
		
		
	}
}

