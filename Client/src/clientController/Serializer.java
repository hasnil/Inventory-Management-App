package clientController;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import message.Message;

/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class Serializer {
	
	/**
	 * Stream for writing to socket.
	 */
	private ObjectOutputStream output;
	
	public Serializer(Socket serverSocket) {
		try {
			output = new ObjectOutputStream(serverSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * For sending messages to server side.
	 * @param message the Message object to be sent
	 */
	public void sendObject(Message message) {
		try {
			output.writeObject(message);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			output.reset(); // ensures new object
		} catch (IOException e) {
			e.printStackTrace();
		}
		//output.close();
	}
}
