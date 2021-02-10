package model;

import exception.ExceptionEmptyField;

/**
 * This class is used to hold details about a player,
* and helps managing it.
* 
* @author Peccio Leandro
*
*/
public class User {
	

	/**
	 * The name of the player.
	 * the password of the player
	 */ 
	private String name, password;

	
	
	/**
	 * Constructor of User, instantiating the parameters above.
	 * @param name The name of the player.
	 * @param password the password of the player
	 */
	public User(String name, String password)throws ExceptionEmptyField{
		if(name.equals("")||password.equals("")) {
			throw new ExceptionEmptyField();
		}else {
			this.name = name;
			this.password = password;
		}
		
		
	}
	
	/**
	 * Constructor of User, instantiating the parameters above.
	 * @param pseudo The pseudo of the player.
	 */
	public User(String pseudo)
	{
		this.name = pseudo;
	}

	/**
	 * This method returns the name of the player.
	 * @return the name of the player.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method changes the name of the player.
	 * @param name The name of the player.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method returns the password of the player.
	 * @return the password of the player.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method changes the password of the player.
	 * @param name The password of the player.
	 */
	public String setPassword(String password) {
		
		this.password= password;
		
		return password;
	}

		@Override
	public boolean equals(Object obj) {

		if(obj instanceof User) {
			
			User u = (User) obj;
			
			return this.name.equals(u.name);
			
		}
		return false;
	}

	/**
	* @return a copy of a user
	*/	
	public User clone() {
		
		try {
			return new User(name, password);
		} catch (ExceptionEmptyField e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * This method returns the user fields as text.
	 * @return The user name fields as text
	 */
	public String toString() {
		
		return name ;
	}

	

}
