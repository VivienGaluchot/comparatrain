package comparaison;

import elements.GareHoraire;

public abstract class Evaluateur {
	public static double evalDepart(GareHoraire g, Preference p){
		if(p.getGDepart() != null)
			return g.eval(p.getGDepart(), p.getHDepart());
		else
			return 1;
	}
	
	public static double evalArrivee(GareHoraire g, Preference p){
		if(p.getGArrivee() != null)
			return g.eval(p.getGArrivee(), p.getHArrivee());
		else
			return 1;
	}
}
