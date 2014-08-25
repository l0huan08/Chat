package Network;

/**
 * IP address.
 * @author hl
 * @version 1.0
 * @created 25-°ËÔÂ-2014 13:32:38
 */
public class IP {

	/**
	 * int[4]
	 */
	private int[] address;
	private int port;

	public IP(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param str    e.g.   "172.168.0.2:1288" , "172.168.0.2"
	 */
	public static IP fromString(String str){
		return null;
	}

	/**
	 * e.g.   "172.168.0.2:1288" , "172.168.0.2"
	 */
	public String toString(){
		return "";
	}

	/**
	 * int[4]
	 */
	public int[] getAddress(){
		return address;
	}

	/**
	 * int[4]
	 * 
	 * @param newVal
	 */
	public void setAddress(int[] newVal){
		address = newVal;
	}

	public int getPort(){
		return port;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setPort(int newVal){
		port = newVal;
	}

}