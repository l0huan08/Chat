package Network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * The message sender/receiver.Use UDP protocol.
 * @author hl
 * @version 1.0
 * @created 25-°ËÔÂ-2014 13:32:38
 */
public class MessageStation {

	private static final int DATA_RECEIVE_BUF_SIZE = 256;
	
	private IP ip;
	private IP listenIp; // the ip to listen
	private DatagramSocket datagramSocket; // for UDP receiver
	private byte dataReceiveBuf[] = new byte[DATA_RECEIVE_BUF_SIZE];

	public MessageStation() {
		
		// get local ip address
		ip=null;
		listenIp=null;
		
		try {
			InetAddress ipAddr = InetAddress.getLocalHost();
			String strIp = ipAddr.getHostAddress();
			ip = IP.fromString(strIp);
			listenIp = IP.fromString(strIp); // default is listen own ip
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			datagramSocket= new DatagramSocket(ip.getPort());
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Connect with a remote station
	 * @param remoteIp
	 * @return if success, return true
	 */
	public boolean connect(IP remoteIp) {
		if (remoteIp==null)
			return false;
		
		this.listenIp = remoteIp;
		int[] addr = listenIp.getAddress();
		byte[] addrByte = new byte[4];
		for (int i=0;i<4;i++) {
			addrByte[i] = (byte)(addr[i]);
		}
		try {
			datagramSocket.connect( InetAddress.getByAddress(addrByte), listenIp.getPort());
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
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
		if ( !datagramSocket.isConnected() ) {
			boolean suc = connect(this.listenIp);
			if (!suc)
				return null;
		}
		
		// receive a packet
		DatagramPacket dataPacket = new DatagramPacket(dataReceiveBuf,DATA_RECEIVE_BUF_SIZE); // Can receive a max of 7 bytes
		try {
			datagramSocket.receive(dataPacket);
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

}