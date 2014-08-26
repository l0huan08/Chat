package Model;

import Network.IP;
import Network.Message;
import Network.MessageStation;
import Network.MessageType;

public class UserMessager {
	
	private User user;
	private MessageStation station;
	
	public UserMessager() {
		user = null;
		station = new MessageStation();
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
	/**
	 * accept an AddContactRequest from another user.
	 * 
	 * @param requestUser    the user who sent the AddContact request
	 */
	public void acceptAddContact(User requestUser){
		IP destIp = IP.fromString( requestUser.getIp() );
		
		Message acceptMsg = new Message();
		createMessageHeader(acceptMsg,user,requestUser);
		acceptMsg.setType(MessageType.AddContactAccept);
		acceptMsg.setContent("");
		
		station.send(destIp, acceptMsg);
	}

	/**
	 * refuse an AddContactRequest from another user.
	 * 
	 * @param requestUser    The user who sent the AddContact request
	 */
	public void refuseAddContact(User requestUser){
		IP destIp = IP.fromString( requestUser.getIp() );
		
		Message acceptMsg = new Message();
		createMessageHeader(acceptMsg,user,requestUser);
		acceptMsg.setType(MessageType.AddContactRefuse);
		acceptMsg.setContent("");
		
		station.send(destIp, acceptMsg);
	}

	/**
	 * receive message, will block until a message received
	 */
	public Message receiveMessage(){
		return station.receive();
	}
	
	/**
	 * send a normal text message to another user.
	 * 
	 * @param destUser
	 * @param text
	 */
	public boolean sendMessage(User destUser, String text){
		IP destIp = IP.fromString( destUser.getIp() );
		
		Message acceptMsg = new Message();
		createMessageHeader(acceptMsg,user,destUser);
		acceptMsg.setType(MessageType.ChatText);
		acceptMsg.setContent(text);
		
		return station.send(destIp, acceptMsg);
	}
	
	private void createMessageHeader(Message message, User sendUser, User toUser) {
		IP senderIp = IP.fromString(sendUser.getIp());
		IP destIp = IP.fromString( toUser.getIp() );
		
		message.setSrcName(sendUser.getName());
		message.setSrcIp(senderIp);
		message.setDestName(toUser.getName());
		message.setDestIp(destIp);
	}
}
