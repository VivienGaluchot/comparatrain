package comparaison;

import modele.Evaluable;
import modele.GareHoraire;

public abstract class Offre implements Evaluable<Preference>{
	Double eval;
	
	public Offre(){
		eval = null;
	}
	
	public double eval(Preference pref) {
		double res = 1;
		res *= getDepart().eval(pref.getGDepart(),pref.getHDepart());
		res *= getArrivee().eval(pref.getGArrivee(),pref.getHArrivee());
		eval = res;		
		return res;
	}
	
	public abstract GareHoraire getDepart();
	
	public abstract GareHoraire getArrivee();
}