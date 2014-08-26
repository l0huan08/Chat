package Network;

/**
 * IP address.
 * @author hl
 * @version 1.0
 * @created 25-°ËÔÂ-2014 13:32:38
 */
public class IP implements java.io.Serializable {

	public static final int DEFAULT_PORT=2222;
	
	/**
	 * int[4]
	 */
	private int[] address;
	private int port;

	public IP(){
		address=new int[4];
		port=DEFAULT_PORT;
	}

	/**
	 * Create a IP from string, if the string is not valid, return null
	 * @param str    e.g.   "172.168.0.2:1288" , "172.168.0.2"
	 */
	public static IP fromString(String str){
		IP newIP=null;
		boolean isValid = true;
		
		try {
			str = str.trim();
			
			// parse port
			int port = DEFAULT_PORT; 
			int iPort = str.indexOf(':');
			int ipLength=0;
			
			if (iPort<0) {
				port = DEFAULT_PORT;
				ipLength = str.length();
			}
			else {
				String strPort = str.substring(iPort+1);
				port = Integer.parseInt(strPort);
				ipLength = iPort;
			}
			
			// parse IP
			String strIp = str.substring(0, ipLength);
			String[] strIps = strIp.split("\\.");
			int[] ips = new int[4];
			if (strIps.length!=4)
				isValid=false; //not valid
			else {
				for (int i=0;i<4;i++) {
					ips[i] = Integer.parseInt(strIps[i]);
				}
			}
			
			if (isValid) {
				newIP = new IP();
				newIP.address=ips;
				newIP.port=port;
			}
			else {
				newIP=null;
			}
		} catch (Exception e){
			newIP = null;
		}
		
		return newIP;
	}

	/**
	 * e.g.   "172.168.0.2:1288" , "172.168.0.2"
	 */
	public String toString(){
		String strAddress = ""+address[0]+"."+address[1]+"."+address[2]+"."+address[3]+":"+port;
		return strAddress;
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
		if (newVal==null)
			return;
		if (newVal.length!=4)
			return;
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