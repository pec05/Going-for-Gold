package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

import model.Deck;
import model.ManagementUser;
import model.Settings;
import model.StatManagement;

/**
* This class is used to put things into Json file and load things from Json file.
* 
* @author Peccio Leandro
*
*/

public class Serialisation {
	
	/**
	 * This method writes into a json file.
	 * @param file
	 * @param sf
	 */	
	public static void writeString(String file, File f) {
		try (PrintWriter print = new PrintWriter(f))
		{
			print.println(file);
		} catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	
	/**
	 * This method reads a json file.
	 * @param selectedFile
	 * @return what it read.
	 */
	public static String readString(File selectedFile)
	{
		String str="";
		String temp = "";
		try(BufferedReader br = new BufferedReader(new FileReader(selectedFile)))
		{
			while((temp = br.readLine()) != null)
			{
				str += temp;
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * This method saves the deck so all the list of question that are in the deck are put in a Json file names Deck.json.
	 * @param s
	 */
	public static void saveDeck(String s)
	{
		try (PrintWriter print = new PrintWriter("./Deck.json")){
			print.println(s);
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * This method loads the deck from Deck.json, and put all the information that it read into the new deck.
	 * @return the new deck
	 */
	public static Deck loadDeck()
	{
		String json = "";
		try
		{
			json = new String(Files.readAllBytes(Paths.get("./Deck.json")), StandardCharsets.ISO_8859_1);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		Deck deck = new Gson().fromJson(json, Deck.class);
		return deck;
	}
	
	/**
	 * This method saves the managementUser so all the list of user that are in the managementUser are put in a Json file names User.json.
	 * @param s
	 */
	public static void saveUser(String s)
	{
		try (PrintWriter print = new PrintWriter("./User.json")){
			print.println(s);
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * This method loads the user from User.json, and put all the information that it read into the new user.
	 * @return the new mg
	 */
	public static ManagementUser loadUser()
	{
		String json = "";
		try
		{
			json = new String(Files.readAllBytes(Paths.get("./User.json")), StandardCharsets.ISO_8859_1);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		ManagementUser mg = new Gson().fromJson(json, ManagementUser.class);
		return mg;
	}
	
	/**
	 * This method saves the stats so all the list of stats that are in the statManagement are put in a Json file names Stat.json.
	 * @param s
	 */
	public static void saveStat(String s)
	{
		try (PrintWriter print = new PrintWriter("./Stats.json")){
			print.println(s);
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * This method loads the stat from Stats.json, and put all the information that it read into the new stat.
	 * @return the new sm
	 */
	public static StatManagement loadStat()
	{
		String json = "";
		try
		{
			json = new String(Files.readAllBytes(Paths.get("./Stats.json")), StandardCharsets.ISO_8859_1);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		StatManagement sm = new Gson().fromJson(json, StatManagement.class);
		return sm;
	}
	
	/**
	 * This method saves the settings and put in a Json file names settings.json.
	 * @param s
	 */
	public static void saveSettings(String s)
	{
		try (PrintWriter print = new PrintWriter("./Settings.json")){
			print.println(s);
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * This method loads the settings from settings.json, and put all the information that it read into the new settings.
	 * @return the new sm
	 */
	public static Settings loadSettings()
	{
		String json = "";
		try
		{
			json = new String(Files.readAllBytes(Paths.get("./Settings.json")), StandardCharsets.ISO_8859_1);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		Settings set = new Gson().fromJson(json, Settings.class);
		return set;
	}
	
	
}
