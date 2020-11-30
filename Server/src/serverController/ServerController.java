package serverController;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;


/**
 * 
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class ServerController {
	
	/**
	 * Socket for communicating with client.
	 */
	Socket clientSocket;
	
	/**
	 * Server socket used to listen for requests.
	 */
	ServerSocket serverSocket;
	
	
	/**
	 * Server constructor. Creates ServerSocket object
	 * with port number 8099.
	 */
	public ServerController() {
		try {
			serverSocket = new ServerSocket(8099);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
	
}
