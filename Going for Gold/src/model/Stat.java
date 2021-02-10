package model;



/**
 *This class holds information about a stats of player and manages its values.
 * 
 * @author Peccio Leandro
 *
 */
public class Stat {
	
	/**
	 * The name of the player playing the game.
	 */
	private String playername;
	/**
	 * The score the player has achieved.
	 */
	private int score;
	/**
	 * The theme of the game.
	 */
	private String theme;
	
	/**
	 * Constructor of Stat, instantiating the parameters above.
	 * @param playername The name of the player at the end of game.
	 * @param score The score of the player at the end of game.
	 * @param theme The theme of played by the player.
	 */
	public Stat(String playername, int score, String theme) {
		super();
		this.playername = playername; 
		this.score = score;
		this.theme = theme;
	}
	
	/**
	 * This method returns the theme of the stat.
	 * @return the theme of the stat.
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * This method changes the name of the theme.
	 * @param theme The name of the theme.
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * This method returns the name of the player.
	 * @return The name of the player.
	 */
	public String getPlayername() {
		return playername;
	}

	/**
	 * This method changes the name of the player.
	 * @param playerName The name of the player.
	 */
	public void setPlayername(String playername) {
		this.playername = playername;
	}

	/**
	 * This method returns the score of the stat.
	 * @return The score of the game.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * This method changes the score of the stat.
	 * @param score The score of the stat.
	 */
	public void setScore(int score) {
		this.score = score;
	}


	/**
	 * This method returns the Stats fields as text.
	 * @return The stats  fields as text
	 */
	@Override
	public String toString() {
		return "Stats [playername=" + playername + ", score=" + score + ", theme=" + theme  + "]";
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playername == null) ? 0 : playername.hashCode());
		result = prime * result + score;
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stat other = (Stat) obj;
		if (playername == null) {
			if (other.playername != null)
				return false;
		} else if (!playername.equals(other.playername))
			return false;
		if (score != other.score)
			return false;
		if (theme == null) {
			if (other.theme != null)
				return false;
		} else if (!theme.equals(other.theme))
			return false;
		return true;
	}

	/**
	 * @return a copy of a stat
	 */
	public Stat clone() {
		return new Stat(playername, score, theme);
	}

}
