/**
 * 
 */
package modele.physique;

import comparaison.Preference;
import modele.Evaluable;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Siege implements Evaluable{
	int id;

	public Siege(int i){
		id=i;
	}
	
	public double eval(Preference pref){
		return 0;
	}
}
