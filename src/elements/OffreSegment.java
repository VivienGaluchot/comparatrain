package elements;

import org.jgrapht.graph.DefaultWeightedEdge;

import train.Place;
import train.Train;

@SuppressWarnings("serial")
public class OffreSegment extends DefaultWeightedEdge{
	SegmentHoraire segment;	
	Train train;
	Place place;
	
	public OffreSegment(Train train, SegmentHoraire segment){
		super();
		this.segment = segment;
		this.train = train;
		this.place = null;
	}
	
	public OffreSegment(Train train, GareHoraire depart, GareHoraire arrivee){
		super();
		this.segment = new SegmentHoraire(depart, arrivee);
		this.train = train;
		this.place = null;
	}
	
	public OffreSegment clone(){
		OffreSegment o = new OffreSegment(train,segment);
		return o;
	}
	
	public GareHoraire getDepart() {
		return segment.depart;
	}

	public GareHoraire getArrivee() {
		return segment.arrivee;
	}
	
	public Train getTrain(){
		return train;
	}
	
	public Place getPlace(){
		return place;
	}
	
	public double getWeight(){
		return (double) getDepart().horaire.until(getArrivee().horaire);
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