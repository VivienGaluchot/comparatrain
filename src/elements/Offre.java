package elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comparaison.Preference;
import train.Place;
import train.Train;

public class Offre implements Evaluable<Preference>, Comparable<Offre>{
	private static int compteur = 0;
	
	private Double eval;
	int id;
	
	private ArrayList<OffreSegment> offres;
	
	public Offre(){
		eval = null;
		this.id = compteur;
		compteur++;
		offres = new ArrayList<OffreSegment>();
	}
	
	public double eval(Preference pref) {
		double res = 1;
		if(pref.getDirect() && getNbCorres()>0) return 0;
		res *= getDepart().eval(pref.getGDepart(),pref.getHDepart());
		res *= getArrivee().eval(pref.getGArrivee(),pref.getHArrivee());
		res *= getDepart().horaire.eval(getArrivee().horaire);
		eval = res;
		return res;
	}
	
	public Double getEval() {
		return eval;
	}

	public int compareTo(Offre o) {
		if((getEval() - o.getEval())>0) return 1;
		else if(getEval() == o.getEval()) return id - o.id;
		else return -1;
	}	
	
	public boolean equals(Offre o){
		return getEval() == o.getEval() && id == o.id;
	}
	
	public GareHoraire getDepart(){
		if(offres.size()==0) return null;
		return offres.get(0).getDepart();
	}
	
	public GareHoraire getArrivee(){
		if(offres.size()==0) return null;
		return offres.get(offres.size()-1).getArrivee();
	}
	
	public void addOffreSimple(OffreSegment o){
		offres.add(o);
	}
	
	public OffreSegment getOffreSimple(int i){
		return offres.get(i);
	}
	
	public List<OffreSegment> getOffres(){
		return Collections.unmodifiableList(offres);
	}
	
	public void addOffreSimple(Train train, Place place, SegmentHoraire segment){
		OffreSegment o = new OffreSegment(train,place,segment);
		offres.add(o);
	}
	
	public int getNbCorres(){
		if(offres.size()==0) return 0;
		return offres.size()-1;
	}
	
	/**
	 * Affichage d'une offre
	 */
	public String toString(){
		String res = "";
		for(OffreSegment o : offres)
			res += "\n" + o + "\n";
		return res;
	}
}