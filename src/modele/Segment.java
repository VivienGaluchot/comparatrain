package modele;

import comparaison.Preference;

public class Segment implements Evaluable<Preference>{
	GareHoraire depart;
	GareHoraire arrivee;
	
	Train train;
	
	Double eval;
	
	public Segment(Train t){
		train = t;
		depart = null;
		arrivee = null;
		eval = null;
	}
	
	public void set(Gare gD, Gare gA, Horaire hD, Horaire hA){		
		depart = new GareHoraire(gD,hD);
		arrivee = new GareHoraire(gA,hA);
	}
	
	public void set(GareHoraire depart, GareHoraire arrivee){		
		this.depart = depart;
		this.arrivee = arrivee;
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
	
	public Segment clone(){
		Segment s = new Segment(train);
		s.depart = depart;
		s.arrivee = arrivee;
		s.eval = eval;
		return s;
	}
	
	/**
	 * Affichage d'un segment
	 */
	public String toString(){
		return "Train n°" + train.getId() + " : " + depart + " --> " + arrivee;
	}
}