package comparaison;

import java.util.ArrayList;

import elements.GareHoraire;

public class OffreMultiple extends Offre{
	private ArrayList<OffreSimple> offres;
	
	public OffreMultiple(){
		super();
		offres = new ArrayList<OffreSimple>();
	}
	
	public GareHoraire getDepart(){
		return offres.get(0).getDepart();
	}
	
	public GareHoraire getArrivee(){
		return offres.get(offres.size()-1).getArrivee();
	}
	
	public int getNbCorres(){
		return offres.size()-1;
	}
	
	public void addOffreSimple(OffreSimple o){
		offres.add(o);
	}
	
	/**
	 * Affichage d'une offre
	 */
	public String toString(){
		String res = "";
		for(OffreSimple o : offres)
			res += o + "\n";
		return res;
	}
}