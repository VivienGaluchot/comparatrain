package comparaison;

import modele.GareHoraire;
import modele.Train;
import modele.physique.Place;

public class OffreSimple extends Offre{
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
	
	public OffreSimple clone(){
		OffreSimple o = new OffreSimple(train,place,depart,arrivee);
		o.eval = eval;
		return o;
	}
	
	@Override
	public GareHoraire getDepart() {
		return depart;
	}

	@Override
	public GareHoraire getArrivee() {
		return arrivee;
	}
	
	/**
	 * Affichage d'une offre
	 */
	public String toString(){
		return "Train n°" + train.getId() + " : " + depart + " --> " + arrivee + "\n"
				+ place;
	}
}