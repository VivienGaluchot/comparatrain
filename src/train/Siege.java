/**
 * 
 */
package train;

import comparaison.Preference;
import elements.Evaluable;
import elements.Identified;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Siege extends Identified implements Evaluable<Preference>{
	public static final boolean AVANT = true;
	public static final boolean ARRIERE = false;
	public static final boolean FENETRE = true;
	public static final boolean COULOIR = false;
	
	boolean sens;
	boolean cote;
	
	protected boolean occupe;
	
	public Siege(Integer i,boolean sens, boolean cote){
		setId(i);
		this.sens = sens;
		this.cote = cote;
		occupe = false;
	}
	
	public Siege(Integer i,boolean sens, boolean cote, boolean occupe){
		setId(i);
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
