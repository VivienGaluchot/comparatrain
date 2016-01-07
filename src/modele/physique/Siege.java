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
	public static final boolean AVANT = true;
	public static final boolean ARRIERE = false;
	public static final boolean FENETRE = true;
	public static final boolean COULOIR = false;
	
	int id;
	boolean sens;
	boolean cote;
	
	public Siege(int i,boolean sens, boolean cote){
		id=i;
		this.sens = sens;
		this.cote = cote;
	}
	
	public double eval(Preference pref){
		return 0;
	}
	
	public String toString(){
		String res;
		if(sens==AVANT)
			res = "u";
		else
			res = "n";
		return res;
	}
}
