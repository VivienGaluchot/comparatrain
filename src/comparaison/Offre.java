package comparaison;

import java.util.ArrayList;

import modele.Evaluable;
import modele.GareHoraire;

public class Offre implements Evaluable<Preference>{
	ArrayList<OffreSimple> offre;
	Double eval;
	
	public Offre(){
		offre = new ArrayList<OffreSimple>();
		eval = null;
	}
	
	public double eval(Preference pref) {
		double res = 1;
		res *= getDepart().eval(pref.getGDepart(),pref.getHDepart());
		res *= getArrivee().eval(pref.getGArrivee(),pref.getHArrivee());
		eval = res;		
		return res;
	}
	
	public GareHoraire getDepart(){
		return offre.get(0).depart;
	}
	
	public GareHoraire getArrivee(){
		return offre.get(offre.size()-1).arrivee;
	}
	
	
	/**
	 * Affichage d'une offre
	 */
	public String toString(){
		String res = "";
		for(OffreSimple o : offre)
			res += o + "\n";
		return res;
	}
}