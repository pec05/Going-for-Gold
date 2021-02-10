package model;

/**
 * The class LoginProxy allows the admin to connect
 * 
 * @author Peccio Leandro
 *
 */
public class LoginProxy implements LoginInterface {

	/**
	 * The login instance.
	 */
	private Login login;
	
	/**
	 * instance a new login
	 */
	public LoginProxy() {
		this.login = new Login();
	}

	/**
	 * This method verify the username and password to connect and call the method of Login class
	 *
	 * @param username 
	 * @param password 
	 * @return true, if the login are correct
	 * @see modele.LoginInterface#connect(java.lang.String, java.lang.String)
	 */
	public boolean connect(String username, String password) {
		return login.connect(username, password);
	}
}
