package model;

/**
 * The class Login contains the details of administrator
 * 
 * @author Peccio Leandro
 *
 */

public class Login implements LoginInterface{

	/**
	 * The username of admin
	 */
	private final String USERNAME = "admin";
	
	/**
	 * The password of admin
	 */
	private final String PASSWORD = "helha";
	
	/**
	 * This method verify the username and password to connect
	 *
	 * @param username 
	 * @param password 
	 * @return true, if the login are the same that variables
	 * @see modele.LoginInterface#connect(java.lang.String, java.lang.String)
	 */
	
	public boolean connect(String username, String password) {
		if(username.equalsIgnoreCase(USERNAME) && password.equalsIgnoreCase(PASSWORD)) {
			return true;
		}else {
			return false;
		}
	}
}
