package comparaison;

import java.util.ArrayList;

import modele.GareHoraire;

public class OffreMultiple extends Offre{
	ArrayList<OffreSimple> offre;
	
	public OffreMultiple(){
		super();
		offre = new ArrayList<OffreSimple>();
	}
	
	public GareHoraire getDepart(){
		return offre.get(0).depart;
	}
	
	public GareHoraire getArrivee(){
		return offre.get(offre.size()-1).arrivee;
	}
	
	/**
	 * Affichage d'une offre
	 */
	public String toString(){
		String res = "";
		for(OffreSimple o : offre)
			res += o + "\n";
		return res;
	}
}