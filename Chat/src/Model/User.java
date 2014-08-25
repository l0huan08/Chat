package Model;

import java.util.Map;
/**
 * Chat user.
 * Each user can chat with other users.
 * @version 1.0
 * @created 25-°ËÔÂ-2014 13:32:38
 */
public class User {

	/**
	 * user id
	 */
	private int id;
	/**
	 * user name
	 */
	private String name;
	/**
	 * user IP address. e.g. "172.168.0.1"
	 */
	private String ip;
	/**
	 * Contacts List. Map<id,User>
	 */
	private Map<Integer, User> contacts;

	public User(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * user id
	 */
	public int getId(){
		return id;
	}

	/**
	 * user id
	 * 
	 * @param newVal
	 */
	public void setId(int newVal){
		id = newVal;
	}

	/**
	 * user name
	 */
	public String getName(){
		return name;
	}

	/**
	 * user name
	 * 
	 * @param newVal
	 */
	public void setName(String newVal){
		name = newVal;
	}

	/**
	 * user IP address. e.g. "172.168.0.1".
	 */
	public String getIp(){
		return ip;
	}

	/**
	 * user IP address. e.g. "172.168.0.1".
	 * 
	 * @param newVal
	 */
	public void setIp(String newVal){
		ip = newVal;
	}

	/**
	 * get the Contacts List. Map<id,User>.
	 */
	public Map<Integer, User> getContacts(){
		return contacts;
	}

	/**
	 * get a contact by user id.
	 * 
	 * @param userId
	 */
	public void getContact(int userId){

	}

	/**
	 * add a contact to this user's contact list
	 * 
	 * @param user
	 */
	public boolean addContact(User user){
		return false;
	}

	/**
	 * remove a user from contact list
	 * 
	 * @param user
	 */
	public boolean removeContact(User user){
		return false;
	}

	/**
	 * accept an AddContactRequest from another user.
	 * 
	 * @param requestUser    the user who sent the AddContact request
	 */
	public void acceptAddContact(User requestUser){

	}

	/**
	 * refuse an AddContactRequest from another user.
	 * 
	 * @param requestUser    The user who sent the AddContact request
	 */
	public void refuseAddContact(User requestUser){

	}

	/**
	 * send a normal text message to another user.
	 * 
	 * @param destUser
	 * @param text
	 */
	public boolean sendMessage(User destUser, String text){
		return false;
	}

}