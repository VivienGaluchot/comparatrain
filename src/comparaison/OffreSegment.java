package comparaison;

import elements.GareHoraire;
import elements.SegmentHoraire;
import train.Place;
import train.Train;

public class OffreSegment{
	SegmentHoraire segment;
	
	Train train;
	Place place;
	
	public OffreSegment(Train train, Place place, SegmentHoraire segment){
		super();
		this.segment = segment;
		this.train = train;
		this.place = place;
	}
	
	public OffreSegment(Train train, Place place, GareHoraire depart, GareHoraire arrivee){
		super();
		this.segment = new SegmentHoraire(depart, arrivee);
		this.train = train;
		this.place = place;
	}
	
	public OffreSegment clone(){
		OffreSegment o = new OffreSegment(train,place,segment);
		return o;
	}
	
	public GareHoraire getDepart() {
		return segment.depart;
	}

	public GareHoraire getArrivee() {
		return segment.arrivee;
	}
	
	/**
	 * Affichage d'une offre
	 */
	public String toString(){
		String res = "";
		if(train == null){
			res += segment.depart + " -- cor. --> " + segment.arrivee;
		}
		else {
			res += segment.depart + " --Train " + train.getId() + "--> " + segment.arrivee;
			if(place != null){
				res += "\n" + place;
			}
			else {
				res += "\n" + "Aucune place attribuée";
			}
		}
		return res;
	}
}