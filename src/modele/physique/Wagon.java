package modele.physique;

import java.util.ArrayList;

import comparaison.Preference;
import modele.Evaluable;

public class Wagon implements Evaluable{
	public static final int PREMIERE = 1;
	public static final int SECONDE = 2;
	public static final int BAR = 3;
	
	int id;
	int type;
	ArrayList<Banc> bancs;
	
	public Wagon(int t){
		if(t == PREMIERE){
			type = t;
			for(int i=0;i<20;i++){
				bancs.add(new Banc(bancs.size(),Banc.DOUBLE));
			}
		}
		else if(t == SECONDE){
			type = t;
			for(int i=0;i<30;i++){
				bancs.add(new Banc(bancs.size(),Banc.DOUBLE));
				bancs.add(new Banc(bancs.size(),Banc.CARRE));
			}
		}
		else if(t == BAR){
			type = t;
		}
	}
	
	public double eval(Preference pref){
		return 0;
	}
}