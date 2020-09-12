import java.net.*;
import java.io.*;

public class ChatClient {
	private String hostname;
	private int port;
	private String userName;

	public ChatClient(String hostname, int port) {
		this.hostname = hostname;
		this.port = port;
	}

	public void execute() {
		try {
			Socket socket = new Socket(hostname, port);
			System.out.println("Connected to the chat server");
			new ReadThread(socket, this).start();
			new WriteThread(socket, this).start();
		} catch (UnknownHostException ex) {
			System.out.println("Server not found: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("I/O Error: " + ex.getMessage());
		}
	}

	void setUserName(String userName) {
		this.userName = userName;
	}

	String getUserName() {
		return this.userName;
	}


	public static void cc() {
		String hostname = "localhost";
		int port = 3001;
		ChatClient client = new ChatClient(hostname, port);
		client.execute();
	}
}