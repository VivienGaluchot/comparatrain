/**
 * 
 */
package comparatrain;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Preference {
	Gare gDepart;
	Gare gArrive;
	Horaire hDepart;
	Horaire hArrive;
	
	public Preference (Gare d, Gare a, Horaire hi, Horaire hf){
		gDepart = d;
		gArrive = a;
		hDepart = hi;
		hArrive = hf;
	}
}
