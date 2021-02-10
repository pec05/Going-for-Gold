package model;


import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

import exception.ExceptionEmptyField;
import util.Serialisation;

/**
 * The class ManagementUser contains a list of users and manages these users
 * by adding them.
 * 
 * @author Peccio Leandro
 *
 */


public class ManagementUser {
	
	
	/**
	 * A list of Users
	 */
	private List<User> users;
	private static ManagementUser mgUser;

	
	/**
	 * Constructor of ManagementUser, instantiating the list of users
	 */
	public ManagementUser() {
		
		users = new ArrayList<User>();
	}
	
	/**
	 * This method return the user and also it make a copy of this user before it put in the list. 
	 * @param pseudo is a String.
	 * @param pw is a String.
	 * @return users
	 * @throws ExceptionEmptyField 
	 */
	public void addUser(String pseudo, String pw) throws ExceptionEmptyField
	{
		users.add(new User(pseudo, pw).clone());
	}
	
	/**
	 * @param pseudo is a String.
	 * @return if the user is in the list, he is deleted and the return is true else false.
	 */
	public void deleteUser(String pseudo)
	{
		int i = findUser(pseudo);
		if(i>-1) {
			users.remove(i);
		}
	}
	/**
	 * @param pseudo is a String.
	 * @return the position of the user thanks to the indexOf method. If the user is not in the list, it returns -1.
	 */
	public int findUser(String pseudo) {
		
		int index = -1;
		for(User u: users)
		{
			if(pseudo.equals(u.getName()))
			{
				index = users.indexOf(u);
			}
		}
		return index;
	}
	
	/**
	 * @param ind is an int
	 * @param newUser is a User
	 */
	public void updateUser(int ind, User newUser)
	{
				
			users.remove(ind);
			users.add(ind, newUser);
				
	}
	
	/**
	 * @return the list users.
	 */
	public List<User> getUsers() {
		return users;
	}
	
	/**
	 * @return the list of users.
	 */
	public String toString() {
		
		return users.toString(); 
	}
	
	/**
	 * @param s
	 * @return the list of user that is saved in a json file.
	 */
	public static ManagementUser fromJson(String s)
	{
		Gson gson = new Gson();
		return gson.fromJson(s, ManagementUser.class);
	}
	
	public boolean removeAllUser(List<User> list)
	{
		return users.removeAll(list);
	}
	public boolean addAllUser(List<User> list)
	{
		return users.addAll(list);
	}
	public String saveJsonUser()
	{
		Gson gson = new Gson();
		String json = gson.toJson(this, ManagementUser.class);
		Serialisation.saveUser(json);
		return gson.toJson(this);
		
	}
	
	/**
	 * Returns the mgUser of the ManagementUser class
	 * load the user.json in deck
	 * @return the mgUser
	 */
	public static ManagementUser getInstanceManagementUser() {
		
		if(mgUser == null) {
			mgUser = Serialisation.loadUser();
			
		}
		return mgUser;
	}
}
