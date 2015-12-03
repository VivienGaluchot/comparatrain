/**
 * 
 */
package comparatrain;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Objet evaluant les offres
 */
public class Comparateur {
	
	ArrayList<Train> ListeTrain;
	
	public Comparateur(){
		ListeTrain=new ArrayList<Train>();
	}
	
	public void ajoutertrain(Train t){
		ListeTrain.add(t);
	}
	
	/**
	 * @param pref : Preference avec lequeles effectuer la comparaison des offres
	 * Tout les trains de la liste ListeTrain sont évalués, les scores et les trains sont stoqués dans une TreeMap
	 */
	public void comparer(Preference pref){
		TreeMap<Integer,Train> evaluations = new TreeMap<Integer,Train>();
		for(Train t : ListeTrain){
			int e = t.eval(pref);
			evaluations.put(e, t);
			System.out.println(t.id + " : " + e);
		}
	}
}
