package model;

import java.io.File;
import com.google.gson.Gson;
import util.IConstant;
import util.Serialisation;
import util.Levenshtein;


/**
 * The class Settings allows to manage the settings of the game.
 * It is defined by various game settings.
 * 
 * @author Peccio Leandro
 *
 */
public class Settings {
	
	/**
	 * Contains the single instance of this class.
	 * This field is part of the Singleton design pattern.
	 */
	public static Settings instance;
	
	/**
	 * The delay between clues to show up.
	 * The delay before a game ends
	 * The distance of Levenshtein
	 * the time added by the bonus 
	 */
	private int clueDelay, timerTotal, distance, bonusTimer;

	
	/**
	 * Constructor of Settings.
	 * Instantiates all the fields using the {@link IConstant} interface.
	 */
	public Settings() {
		this.clueDelay = IConstant.CLUEDELAY;
		this.timerTotal = IConstant.TIMERTOTAL;
		this.distance = IConstant.DISTANCE;
		this.bonusTimer = IConstant.BONUSTIMER;
	}
	
	/**
	 * Returns the single instance of the Settings class
	 * @return the instance
	 */
	public static Settings getInstance() {
		if(instance == null) {
			instance = new Settings();
			instance.reloadConfig();
		}
		return instance;
	}
	
	/**
	 * This method restores the settings to the default ones, using the settings file included
	 * in the resources folder.
	 */
	public void restoreDefaultConfig() { 
		instance = new Settings();
		saveSettings();
		reloadConfig();
	}
	
	/**
	 * This method saves the settings into an external settings.json file.
	 */
	public void saveSettings() {
		Gson gson = new Gson();
		String json = gson.toJson(this, Settings.class);
		Serialisation.saveSettings(json);
	}
	
	/**
	 * This method loads the configuration from the external settings.json file.
	 */
	public void reloadConfig() {
		if(new File("settings.json").exists()) {
			Serialisation.loadSettings();
		}
		
	}

	/**
	 * This method returns the delay between clues.
	 * @return The delay between each clue.
	 */
	public int getClueDelay() {
		return clueDelay;
	}

	/**
	 * This method returns the delay before a game ends.
	 * @return The delay before a game ends.
	 */
	public int getTimerTotal() {
		return timerTotal;
	}

	/**
	 * This method returns the distance according to {@link Levenshtein}.
	 * @return the distance between the two strings.
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * This method sets the delay between each clue.
	 * @param clueDelay The delay between each clue to be set.
	 */
	public void setClueDelay(int clueDelay) {
		this.clueDelay = clueDelay;
	}

	/**
	 * This method sets the new delay before a game ends.
	 * @param roundDelay The new delay.
	 */
	public void setTimerTotal(int timerTotal) {
		this.timerTotal = timerTotal;
	}

	/**
	 * This method changes the distance between two strings according to {@link Levenshtein}.
	 * @param distance The new distance between the two strings.
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * This method returns the time added by the bonus
	 * @return The bonusTimer.
	 */
	public int getBonusTimer() {
		return bonusTimer;
	}

	/**
	 * This method sets the time added by the bonus
	 * @param bonusTimer The new bonusTimer.
	 */
	public void setBonusTimer(int bonusTimer) {
		this.bonusTimer = bonusTimer;
	}
	
	
	
	
	
	

}
