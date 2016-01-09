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
	
	protected boolean occupe;
	
	public Siege(int i,boolean sens, boolean cote){
		id=i;
		this.sens = sens;
		this.cote = cote;
		occupe = false;
	}
	
	public Siege(int i,boolean sens, boolean cote, boolean occupe){
		id=i;
		this.sens = sens;
		this.cote = cote;
		this.occupe = occupe;
	}
	
	public void setOccupe(boolean occupe){
		this.occupe = occupe;
	}
	
	public boolean getOccupe(){
		return occupe;
	}
	
	public double eval(Preference pref){
		return 0;
	}
	
	public String toString(){
		String res;
		if(occupe){
			res = "X";
		}
		else if(sens==AVANT)
			res = "u";
		else
			res = "n";
		return res;
	}
}
