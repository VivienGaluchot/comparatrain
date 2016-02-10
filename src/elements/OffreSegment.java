package elements;

import java.util.ArrayList;

import org.jgrapht.graph.DefaultWeightedEdge;

import train.Train;

@SuppressWarnings("serial")
public class OffreSegment extends DefaultWeightedEdge{
	SegmentHoraire segment;	
	Train train;
	ArrayList<Billet> billets;
	
	public OffreSegment(Train train, SegmentHoraire segment){
		super();
		this.segment = segment;
		this.train = train;
		billets = null;
	}
	
	public OffreSegment(Train train, GareHoraire depart, GareHoraire arrivee){
		super();
		this.segment = new SegmentHoraire(depart, arrivee);
		this.train = train;
		billets = null;
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
			if(billets != null){
				for(Billet b : billets)
				res += "\n" + b;
			}
			else {
				res += "\n" + "Aucune place attribuée";
			}
		}
		return res;
	}
}