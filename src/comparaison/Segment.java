package comparaison;

import modele.Evaluable;
import modele.Gare;
import modele.Horaire;
import modele.Train;

public class Segment implements Evaluable<Preference>{
	Gare gareD;
	Gare gareA;
	Horaire horaireD;
	Horaire horaireA;
	
	Train train;
	
	Double eval;
	
	public Segment(Train t){
		train = t;
	}
	
	public void set(Gare gD, Gare gA, Horaire hD, Horaire hA){
		gareA = gA;
		gareD = gD;
		horaireD = hD;
		horaireA = hA;
	}
	
	public double eval(Preference pref) {
		double res = 1;
		
		// Lieux
		res *= gareD.eval(pref.getgDepart());
		if(res == 0) return res;
		res *= gareA.eval(pref.getgArrive());
		if(res == 0) return res;
		
		// Dates
		if(pref.gethDepart() != null)
			res *= horaireD.eval(pref.gethDepart());
		if(res == 0) return res;
		if(pref.gethArrive() != null)
			res *= horaireA.eval(pref.gethArrive());
		
		eval = res;
		
		return res;
	}
	
	public Segment clone(){
		Segment s = new Segment(train);
		s.set(gareD, gareA, horaireD, horaireA);
		s.eval = eval;
		return s;
	}
	
	/**
	 * Affichage d'un segment
	 */
	public String toString(){
		return "Train n°" + train.getId() + " : " + gareD + " " + horaireD + " --> " + gareA + " " + horaireA;
	}
}