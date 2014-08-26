package Network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * The message sender/receiver.Use UDP protocol.
 * Two stations A,B is connected, and A can send or receive message from station B
 * @author hl
 * @version 1.0
 * @created 25-°ËÔÂ-2014 13:32:38
 */
public class MessageStation {

	private static final int DATA_RECEIVE_BUF_SIZE = 256;
	
	private IP ip; // own ip
	private DatagramSocket receiverSocket; // for UDP receiver, connect to localhost:DEFAULT_PORT
	private DatagramSocket senderSocket; // for UDP sender, connect to remoteIp:DEFAULT_PORT
	private byte dataReceiveBuf[] = new byte[DATA_RECEIVE_BUF_SIZE];

	public MessageStation() {
		
		// get local ip address
		ip=null;
		
		try {
			InetAddress ipAddr = InetAddress.getLocalHost();
			String strIp = ipAddr.getHostAddress();
			ip = IP.fromString(strIp);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}
		
		if (ip==null)
			return;
		
		try {
			receiverSocket= new DatagramSocket(ip.getPort()); // recieverSocket is listen to A's own ip:port
			senderSocket = new DatagramSocket(); // do not specify remote client
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Send a message. Return success.
	 * 
	 * @param dstIp
	 * @param message
	 */
	public boolean send(IP dstIp, Message message){
		if (senderSocket==null)
			return false;
		
		// create data bytes from message object
		ObjectOutputStream obs = null;
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		try {
			obs = new ObjectOutputStream(bs);
			obs.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		byte data[] = bs.toByteArray();  // Converts Message to byte[].
		DatagramPacket dp = new DatagramPacket(data, data.length,  ip2inet(dstIp), dstIp.getPort());
		
		try {
			senderSocket.send(dp);// If client is not already waiting, data is lost.
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	/**
	 * Waiting and receive a message. Blocking until a message received.
	 */
	public Message receive(){
		if (receiverSocket==null)
			return null;
		
		// receive a packet
		DatagramPacket dataPacket = new DatagramPacket(dataReceiveBuf,DATA_RECEIVE_BUF_SIZE); // Can receive a max of 7 bytes
		try {
			receiverSocket.receive(dataPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		dataReceiveBuf = dataPacket.getData();
	    Arrays.fill( dataReceiveBuf, (byte)(0) );// clean the
		
	    
	    // create message object from data
		ObjectInputStream obs = null;
		Message msg = null;
		try {
			obs = new ObjectInputStream(new ByteArrayInputStream(dataReceiveBuf));
			msg = (Message)obs.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return msg;
	}

	
	private InetAddress ip2inet(IP ip) {
		int[] addr = ip.getAddress();
		byte[] addrByte = new byte[4];
		for (int i=0;i<4;i++) {
			addrByte[i] = (byte)(addr[i]);
		}
		try {
			return InetAddress.getByAddress(addrByte);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}