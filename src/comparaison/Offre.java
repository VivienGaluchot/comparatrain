package comparaison;

import elements.Evaluable;
import elements.GareHoraire;

public abstract class Offre implements Evaluable<Preference>, Comparable<Offre>{
	private static int compteur = 0;
	
	Double eval;
	int id;
	
	public Offre(){
		eval = null;
		this.id = compteur;
		compteur++;
	}
	
	public double eval(Preference pref) {
		double res = 1;
		res *= getDepart().eval(pref.getGDepart(),pref.getHDepart());
		res *= getArrivee().eval(pref.getGArrivee(),pref.getHArrivee());
		eval = res;
		return res;
	}
	
	public int compareTo(Offre o) {
		if((eval - o.eval)>0) return 1;
		else if(eval == o.eval) return id - o.id;
		else return -1;
	}
	
	public boolean equals(Offre o){
		return eval == o.eval && id == o.id;
	}
	
	public abstract GareHoraire getDepart();
	
	public abstract GareHoraire getArrivee();
}