package model;

/**
  * This class is used to hold details about a game a player starts,
 * and helps managing it.
 * 
 * @author Peccio Leandro
 *
 */

public class Game {
	
	
	/**
	 * The theme of the game.
	 */ 
	private String theme; 
	
	/**
	 * The score the player has achieved.
	 */
	private int score;

	/**
	 * Constructor of Game, instantiating the parameters above.
	 * @param theme The theme of the actual game.
	 */
	public Game(String theme) {
		super();
		this.theme = theme;
		this.score = 0;
	}

	/**
	 * This method returns the theme of the game.
	 * @return the theme of the game.
	 */
	public String getTheme() {
		return theme;
	}
	
	/**
	 * This method changes the name of the theme.
	 * @param gameTheme The name of the theme.
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * This method returns the score of the game.
	 * @return The score of the game.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * This method changes the score of the game.
	 * @param score The score of the game.
	 */
	public void setScore(int score) {
		if(score >=0) {
			this.score = score;
		}
		
	}

}
