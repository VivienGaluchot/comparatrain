package elements;

import java.util.ArrayList;

import org.jgrapht.graph.DefaultWeightedEdge;

import comparaison.Preference;
import train.Train;

@SuppressWarnings("serial")
public class OffreSegment extends DefaultWeightedEdge{
	private SegmentHoraire segment;	
	private Train train;
	private ArrayList<Billet> billets;
	
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

	public double eval(Preference pref) {
		if(billets == null || billets.size() > pref.getNbPlace()) return 0;
		Double res = 1.;
		for(Billet b : billets){
			res *= b.getRame().eval(pref);
			res *= b.getWagon().eval(pref);
			res *= b.getSiege().eval(pref);
		}
		return res;
	}
	
	public OffreSegment clone(){
		OffreSegment o = new OffreSegment(train,segment);
		return o;
	}
	
	public double getWeight(){
		return (double) getDepart().horaire.until(getArrivee().horaire);
	}
	
	public GareHoraire getDepart() { return segment.depart; }
	public GareHoraire getArrivee() { return segment.arrivee; }
	
	public SegmentHoraire getSegment() { return segment; }
	
	public Train getTrain(){ return train; }

	public void setBillets(ArrayList<Billet> billets) { this.billets = billets; }
}