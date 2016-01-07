package modele.physique;

import java.util.ArrayList;

import comparaison.Preference;
import modele.Evaluable;

public class Banc implements Evaluable{
	public static final int SEUL = 1;
	public static final int DOUBLE = 2;
	public static final int CARRE = 3;
	
	int id;
	ArrayList<Siege> sieges;
	
	Banc(int i, int type){
		id = i;
		sieges = new ArrayList<Siege>();
		
		if(type == SEUL){
			sieges.add(new Siege(sieges.size()));
		}else if(type == DOUBLE){
			sieges.add(new Siege(sieges.size()));
			sieges.add(new Siege(sieges.size()));
		}else if(type == CARRE){
			sieges.add(new Siege(sieges.size()));
			sieges.add(new Siege(sieges.size()));
			sieges.add(new Siege(sieges.size()));
			sieges.add(new Siege(sieges.size()));
		}
	}
	
	public double eval(Preference pref){
		return 0;
	}
}
