import java.net.*;
import java.util.concurrent.*;
import java.sql.*;

public class Server {
	public static Connection con;
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(7041);
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post_office", "root", "root");
		ExecutorService pool = Executors.newFixedThreadPool(5);
		int x=0;
		while (true) {
			System.out.println("Server Waiting");
			Socket socket = ss.accept();
			x++;
			System.out.println("Server is connected to client "+ x);
			HandleClient clientThread = new HandleClient(socket);
			pool.execute(clientThread);
		}
	}
}
