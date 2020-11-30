package serverController;

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
	
	public Serializer(Socket clientSocket) {
		try {
			output = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Used to send Message objects to client side.
	 * @param message the message to send
	 */
	public void sendObject(Message message) {
		try {
			output.writeObject(message);
			output.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			output.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//output.close();
	}
}
