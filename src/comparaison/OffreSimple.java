package comparaison;

import modele.Evaluable;
import modele.GareHoraire;
import modele.Train;
import modele.physique.Place;

public class OffreSimple extends Offre implements Evaluable<Preference>{
	GareHoraire depart;
	GareHoraire arrivee;
	
	Train train;
	Place place;
	
	public OffreSimple(Train train, Place place, GareHoraire depart, GareHoraire arrivee){
		super();
		this.depart = depart;
		this.arrivee = arrivee;
		this.train = train;
		this.place = place;
	}
	
	public double eval(Preference pref) {
		double res = 1;
		res *= depart.eval(pref.getGDepart(),pref.getHDepart());
		res *= arrivee.eval(pref.getGArrivee(),pref.getHArrivee());
		eval = res;		
		return res;
	}	
	
	public OffreSimple clone(){
		OffreSimple o = new OffreSimple(train,place,depart,arrivee);
		o.eval = eval;
		return o;
	}
	
	/**
	 * Affichage d'une offre
	 */
	public String toString(){
		return "Train n°" + train.getId() + " : " + depart + " --> " + arrivee + "\n"
				+ place;
	}
}