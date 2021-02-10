package model;

/**
 * The interface LoginInterface redefine the method connect
 * 
 * @author Peccio Leandro
 *
 */
public interface LoginInterface {
	
	/**
	 * This method verify the username and password to connect
	 *
	 * @param username 
	 * @param password 
	 * @return true, if the login are correct
	 */
	public boolean connect(String username, String password);
}
