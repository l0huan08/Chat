package Model;

import java.util.HashMap;
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
		id = 0;
		name = new String("user");
		ip = new String("");
		contacts = new HashMap<Integer, User>();
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
	public User getContact(int userId){
		return contacts.get(userId);
	}

	/**
	 * add a contact to this user's contact list
	 * 
	 * @param user
	 */
	public boolean addContact(User user){
		contacts.put(user.getId(), user); // always can put in. If there is a old user with the same id, replace it.
		return true;
	}

	/**
	 * remove a user from contact list
	 * 
	 * @param user
	 */
	public boolean removeContact(int userId){
		User r = contacts.remove(userId);
		return (r!=null);
	}

	

}