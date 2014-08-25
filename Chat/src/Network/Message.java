package Network;

/**
 * @author hl
 * @version 1.0
 * @created 25-°ËÔÂ-2014 13:32:38
 */
public class Message {

	private MessageType type;
	private String srcName;
	private IP srcIp;
	private String destName;
	private IP destIp;
	/**
	 * message content
	 */
	private String content;
	public MessageType m_MessageType;

	public Message(){
		type = MessageType.ChatText;
		srcName = null;
		srcIp = null;
		destName = null;
		destIp = null;
	}

	public MessageType getType(){
		return type;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setType(MessageType newVal){
		type = newVal;
	}

	public String getSrcName(){
		return srcName;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setSrcName(String newVal){
		srcName = newVal;
	}

	public String getDestName(){
		return destName;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDestName(String newVal){
		destName = newVal;
	}

	/**
	 * message content
	 */
	public String getContent(){
		return content;
	}

	public IP getDestIp(){
		return destIp;
	}

	public IP getSrcIp(){
		return srcIp;
	}

	/**
	 * message content
	 * 
	 * @param newVal
	 */
	public void setContent(String newVal){
		content = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDestIp(IP newVal){
		destIp = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setSrcIp(IP newVal){
		srcIp = newVal;
	}

}