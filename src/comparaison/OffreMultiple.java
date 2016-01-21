package comparaison;

import java.util.ArrayList;

import modele.GareHoraire;

public class OffreMultiple extends Offre{
	ArrayList<OffreSimple> trains;
	
	public OffreMultiple(){
		super();
		trains = new ArrayList<OffreSimple>();
	}
	
	public GareHoraire getDepart(){
		return trains.get(0).depart;
	}
	
	public GareHoraire getArrivee(){
		return trains.get(trains.size()-1).arrivee;
	}
	
	public int getNbCorres(){
		return trains.size()-1;
	}
	
	/**
	 * Affichage d'une offre
	 */
	public String toString(){
		String res = "";
		for(OffreSimple o : trains)
			res += o + "\n";
		return res;
	}
}