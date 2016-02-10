package comparaison;

import elements.GareHoraire;

public abstract class Evaluateur {
	public static double evalDepart(GareHoraire g, Preference p){
		if(p.getLieuxDepart() != null)
			return g.eval(p.getLieuxDepart(), p.getHDepart());
		else
			return 1;
	}
	
	public static double evalArrivee(GareHoraire g, Preference p){
		if(p.getLieuxArrivee() != null)
			return g.eval(p.getLieuxArrivee(), p.getHArrivee());
		else
			return 1;
	}
}
