package comparaison;

import java.util.ArrayList;

import elements.GareHoraire;
import elements.SegmentHoraire;
import train.Place;
import train.Train;

public class OffreMultiple extends Offre{
	private ArrayList<OffreSimple> offres;
	
	public OffreMultiple(){
		super();
		offres = new ArrayList<OffreSimple>();
	}
	
	public GareHoraire getDepart(){
		if(offres.size()==0) return null;
		return offres.get(0).getDepart();
	}
	
	public GareHoraire getArrivee(){
		if(offres.size()==0) return null;
		return offres.get(offres.size()-1).getArrivee();
	}
	
	public int getNbCorres(){
		if(offres.size()==0) return 0;
		return offres.size()-1;
	}
	
	public void addOffreSimple(OffreSimple o){
		offres.add(o);
	}
	
	public void addOffreSimple(Train train, Place place, SegmentHoraire segment){
		OffreSimple o = new OffreSimple(train,place,segment);
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