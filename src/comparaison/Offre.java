package comparaison;

import modele.Evaluable;
import modele.Gare;
import modele.GareHoraire;
import modele.Horaire;
import modele.Train;

public class Offre implements Evaluable<Preference>{
	GareHoraire depart;
	GareHoraire arrivee;
	
	Train train;
	
	Double eval;
	
	public Offre(Train t){
		train = t;
		depart = null;
		arrivee = null;
		eval = null;
	}
	
	public void set(Gare gD, Horaire hD, Gare gA, Horaire hA){		
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
	
	public Offre clone(){
		Offre o = new Offre(train);
		o.depart = depart;
		o.arrivee = arrivee;
		o.eval = eval;
		return o;
	}
	
	/**
	 * Affichage d'une offre
	 */
	public String toString(){
		return "Train n°" + train.getId() + " : " + depart + " --> " + arrivee;
	}
}