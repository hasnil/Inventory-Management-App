package serverController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import message.Message;


/**
 * ENSF 607 Project - November 26, 2020
 * @author Completed by Hunter Kimmett and Lotfi Hasni
 *
 */
public class Deserializer {
	
	/**
	 * Stream for reading from socket.
	 */
	private ObjectInputStream input;
	
	public Deserializer(Socket clientSocket) {
		try {
			input = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * For receiving message from client
	 * @return the Message object
	 */
	public Message receiveResponse(){
		Message message = null;
		while(true) {
		try {
			message = (Message)input.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	}

}
