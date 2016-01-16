package comparaison;

import modele.Evaluable;
import modele.GareHoraire;
import modele.Train;
import modele.physique.Place;

public class OffreSimple implements Evaluable<Preference>{
	GareHoraire depart;
	GareHoraire arrivee;
	
	Train train;
	Place place;
	
	Double eval;
	
	public OffreSimple(Train train, Place place, GareHoraire depart, GareHoraire arrivee){
		this.depart = depart;
		this.arrivee = arrivee;
		this.train = train;
		this.place = place;
		eval = null;
	}
	
	public double eval(Preference pref) {
		double res = 1;
		
		// Lieux
		res *= depart.gare.eval(pref.getgDepart());
		if(res == 0) return res;
		res *= arrivee.gare.eval(pref.getgArrive());
		if(res == 0) return res;
		
		// Dates
		if(pref.gethDepart() != null)
			res *= depart.horaire.eval(pref.gethDepart());
		if(res == 0) return res;
		if(pref.gethArrive() != null)
			res *= arrivee.horaire.eval(pref.gethArrive());
		
		eval = res;
		
		return res;
	}
	
	/**
	 * Affichage d'une offre
	 */
	public String toString(){
		return "Train n°" + train.getId() + " : " + depart + " --> " + arrivee + "\n"
				+ place;
	}
}