package util;


/**
* This class contains a static method used to calculate the distance between
* two strings
* 
* @author Peccio Leandro
*
*/
public class Levenshtein {
	
	/**
	 * This method calculates the distance between two strings based on Levenshtein's
	 * algorithm.
	 * @param a The first string to be compared.
	 * @param b The second string to be compared.
	 * @param nbError The allowed distance between the two strings.
	 * @return Whether the distance between the two strings are less or equals the allowed distance (true) or not (false).
	 */
	public static boolean comparator(String a, String b, int nbError) {
		a = a.toLowerCase();
		b = b.toLowerCase();
		int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()]<=nbError;
	}


}
