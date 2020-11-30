package clientController;

import java.io.IOException;
import java.net.Socket;

/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class MainClientController {

	
	private String ip = "50.99.226.114";
	/**
	 * Socket for communicating with server side.
	 */
	private Socket serverCommunicator;
	
	public MainClientController(){
		try {
			serverCommunicator = new Socket(ip, 1700);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Socket getServerCommunicator() {
		return serverCommunicator;
	}

	public void setServerCommunicator(Socket serverCommunicator) {
		this.serverCommunicator = serverCommunicator;
	}
	
}
