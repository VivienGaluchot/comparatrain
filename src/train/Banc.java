package train;

import java.util.ArrayList;

import comparaison.Preference;
import elements.Evaluable;

public class Banc implements Evaluable<Preference>{	
	public static final boolean GAUCHE = false;
	public static final boolean DROIT = true;
	
	int id;
	boolean cote;
	
	ArrayList<Siege> sieges = new ArrayList<Siege>();
	
	Banc(int i, boolean cote){
		id = i;
		this.cote = cote;
	}
	
	public double eval(Preference pref){
		return 0;
	}

	public String toString(){
		return "--";
	}
}
/**
 * Banc Seul
 */
class BancSeul extends Banc{
	public BancSeul(int i, boolean cote){
		super(i,cote);
		sieges.add(new Siege(sieges.size(),Siege.AVANT,Siege.FENETRE));
	}
	
	public String toString(){
		String res;
		if(cote == GAUCHE)
			res = sieges.get(0) + " ";
		else
			res = " " + sieges.get(0);
		return res;
	}
}

/**
 * Banc Double
 */
class BancDouble extends Banc{
	public BancDouble(int i, boolean cote){
		super(i,cote);
		sieges.add(new Siege(sieges.size(),Siege.AVANT,Siege.FENETRE));
		sieges.add(new Siege(sieges.size(),Siege.AVANT,Siege.COULOIR));
	}
	
	public String toString(){
		String res;
		res = sieges.get(0).toString() + sieges.get(1);
		return res;
	}
}

/**
 * Banc Carré
 */
class BancCarre extends Banc{
	public BancCarre(int i, boolean cote){
		super(i,cote);
		sieges.add(new Siege(sieges.size(),Siege.ARRIERE,Siege.FENETRE));
		sieges.add(new Siege(sieges.size(),Siege.ARRIERE,Siege.COULOIR));
		sieges.add(new Siege(sieges.size(),Siege.AVANT,Siege.FENETRE));
		sieges.add(new Siege(sieges.size(),Siege.AVANT,Siege.COULOIR));
	}
	
	public String toString(){
		String res;
		res = sieges.get(0).toString() + sieges.get(1) + "\n";
		res += sieges.get(2).toString() + sieges.get(3);
		return res;
	}
}