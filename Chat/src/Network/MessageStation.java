package Network;

/**
 * The message sender/receiver.Use UDP protocol.
 * @author hl
 * @version 1.0
 * @created 25-°ËÔÂ-2014 13:32:38
 */
public class MessageStation {

	private IP ip;

	public MessageStation(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * Send a message. Return success.
	 * 
	 * @param dstIp
	 * @param message
	 */
	public boolean send(IP dstIp, Message message){
		return false;
	}

	/**
	 * Waiting and receive a message. Blocking until a message received.
	 */
	public Message receive(){
		return null;
	}

}