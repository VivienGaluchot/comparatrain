package offre;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.graph.DefaultWeightedEdge;

import elements.GareHoraire;
import elements.Preference;
import elements.SegmentHoraire;
import train.Train;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * OffreSimple, composé d'un train parcourant un certain segmentHoraire
 * Aussi composé d'une liste de billet. Représentants les éventuelles places
 * disponibles sur le trajet.
 */
@SuppressWarnings("serial")
public class OffreSimple extends DefaultWeightedEdge{
	private SegmentHoraire segment;	
	private Train train;
	private ArrayList<Billet> billets;
	
	public OffreSimple(){
		segment = null;
		train = null;
		billets = null;
	}
	
	public OffreSimple(Train train, SegmentHoraire segment){
		super();
		this.segment = segment;
		this.train = train;
		billets = null;
	}
	
	public OffreSimple(Train train, GareHoraire depart, GareHoraire arrivee){
		super();
		this.segment = new SegmentHoraire(depart, arrivee);
		this.train = train;
		billets = null;
	}
	
	public double eval(Preference pref) {
		if(billets == null || billets.size() > pref.getNbPlace()) return 0;
		Double res = 1.;
		for(Billet b : billets)
			res *= b.eval(pref);
		return res;
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
				res += "\n--- Billet ---\n" + b.strPlace();
			}
			else {
				res += "\n" + "Aucune place attribuée";
			}
		}
		return res;
	}

	public OffreSimple clone(){
		OffreSimple o = new OffreSimple(train,segment);
		return o;
	}
	
	public double getWeight(){
		return (double) getDepart().horaire.until(getArrivee().horaire);
	}
	
	public GareHoraire getDepart() { return segment.depart; }
	public GareHoraire getArrivee() { return segment.arrivee; }
	
	public SegmentHoraire getSegment() { return segment; }
	public void setSegment(SegmentHoraire segment) { this.segment = segment; }
	
	public Train getTrain(){ return train; }

	public void setBillets(ArrayList<Billet> billets) { this.billets = billets; }
	public List<Billet> getBillets() { return billets; }
}