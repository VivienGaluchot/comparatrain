package train;

import java.util.ArrayList;

import comparaison.Preference;
import elements.Evaluable;
import elements.Indexable;

public class Wagon extends Indexable implements Evaluable<Preference>{
	public static final int PREMIERE = 1;
	public static final int SECONDE = 2;
	public static final int BAR = 3;
	
	protected ArrayList<Banc> bancs;
	
	public Wagon(){
		bancs = null;
	}
	
	public Wagon(int i, Integer type){
		setId(i);		
		bancs = new ArrayList<Banc>();
		if(type == PREMIERE){
			for(int j=0;j<3;j++){
				bancs.add(new Banc(bancs.size(),Banc.GAUCHE,Banc.SEUL));
				bancs.add(new Banc(bancs.size(),Banc.DROIT,Banc.DOUBLE));
			}
		}else if(type == SECONDE){
			for(int j=0;j<1;j++){
				bancs.add(new Banc(bancs.size(),Banc.GAUCHE,Banc.DOUBLE));
				bancs.add(new Banc(bancs.size(),Banc.DROIT,Banc.DOUBLE));
				bancs.add(new Banc(bancs.size(),Banc.GAUCHE,Banc.CARRE));
				bancs.add(new Banc(bancs.size(),Banc.DROIT,Banc.CARRE));
				bancs.add(new Banc(bancs.size(),Banc.DROIT,Banc.DOUBLE));
				bancs.add(new Banc(bancs.size(),Banc.DROIT,Banc.DOUBLE));
			}
		}else if(type == BAR){
			// pas de bancs
		}
	}
	
	public String toString(){		
		return "Wagon " + getId();
	}
	
	public double eval(Preference pref){
		return 1;
	}

	public ArrayList<Banc> getBancs() { return bancs; }
	public void setBancs(ArrayList<Banc> bancs) { this.bancs = bancs; }
}