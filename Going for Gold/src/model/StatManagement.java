package model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import util.Serialisation;


/**
 * The class StatManagement contains a list of stats and manages these stats
 * by adding them.
 * 
 * @author Peccio Leandro
 *
 */
public class StatManagement {
	
	/**
	 * A list of Stat
	 */
	private List<Stat>stats;
	
	private static StatManagement statManagement;
	
	/**
	 * Constructor of StatManagement, instantiating the list of stats
	 */
	public StatManagement() {
		stats = new ArrayList<Stat>();
	}
	
	/**
	 * This method return the stats  and also it make a copy of this stat before it put in the list.
	 * @param s is a stat.
	 * @return stats
	 */
	public boolean addStat(Stat s) {
		return stats.add(s.clone());
	}
	
	public boolean addAllStat(List<Stat> list)
	{
		return stats.addAll(list);
	}
	
	/**
	 * @return the list stats.
	 */
	public List<Stat> getStats(){
		return stats;
	}

	/**
	 * @return the list of stat.
	 */
	@Override
	public String toString() {
		return "GestionStats [stats=" + stats + "]";
	}
	
	/**
	 * @param s
	 * @return the list of stats that is saved in a json file.
	 */
	public static StatManagement fromJson(String s)
	{
		Gson gson = new Gson();
		return gson.fromJson(s, StatManagement.class);
	}
	
	
	public String saveJsonStats()
	{
		Gson gson = new Gson();
		String json = gson.toJson(this, StatManagement.class);
		Serialisation.saveStat(json);
		return gson.toJson(this);
		
	}
	
	/**
	 * Returns the stats of the StatManagement class
	 * load the stats.json in StatManagement
	 * @return the statManagement
	 */
	public static StatManagement getInstanceStatManagement() {
		if(statManagement == null) {
			statManagement = Serialisation.loadStat();
		}
		return statManagement;
	}
	
	

}
