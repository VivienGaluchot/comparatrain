package comparaison;

import elements.GareHoraire;

public abstract class Evaluateur {
	public static double evalDepart(GareHoraire g, Preference p){
		return g.eval(p.getGDepart(), p.getHDepart());
	}
	
	public static double evalArrivee(GareHoraire g, Preference p){
		return g.eval(p.getGArrivee(), p.getHArrivee());
	}
}
