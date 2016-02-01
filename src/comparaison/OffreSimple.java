package comparaison;

import elements.GareHoraire;
import elements.SegmentHoraire;
import train.Place;
import train.Train;

public class OffreSimple extends Offre{
	SegmentHoraire segment;
	
	Train train;
	Place place;
	
	public OffreSimple(Train train, Place place, SegmentHoraire segment){
		super();
		this.segment = segment;
		this.train = train;
		this.place = place;
	}
	
	public OffreSimple clone(){
		OffreSimple o = new OffreSimple(train,place,segment);
		o.eval = eval;
		o.id = id;
		return o;
	}
	
	@Override
	public GareHoraire getDepart() {
		return segment.depart;
	}

	@Override
	public GareHoraire getArrivee() {
		return segment.arrivee;
	}
	
	/**
	 * Affichage d'une offre
	 */
	public String toString(){
		return "Train n°" + train.getId() + " : " + segment + "\n"
				+ place;
	}
}