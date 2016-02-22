package offre;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import elements.Evaluable;
import elements.GareHoraire;
import elements.Indexable;
import elements.Preference;
import elements.SegmentHoraire;
import train.Train;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Objet représentant une offre, essentiellement une collection d'offre simple
 */
public class Offre extends Indexable implements Evaluable<Preference>{
	private static int compteur = 0;
	private Double eval;	
	private ArrayList<OffreSimple> offres;
	
	public Offre(){
		eval = null;
		setId(compteur++);
		offres = new ArrayList<OffreSimple>();
	}
	
	public double eval(Preference pref) {
		double res = 1;
		if(pref.getDirect() && getNbTrains()>1) res *= 1./getNbTrains();
		res *= getDepart().eval(pref.getLieuxDepart(),pref.getHDepart());
		res *= getArrivee().eval(pref.getLieuxArrivee(),pref.getHArrivee());
		res *= getDepart().horaire.eval(getArrivee().horaire)/2.0 + 0.5;
		
		if(offres == null || offres.size()==0) return 0;
		// moyenne de l'évaluation de tout les billets
		Double moy = 0.;
		for(OffreSimple o : offres){
			Double temp = o.eval(pref);
			if(temp == 0) return 0; // pas de billet trouvé
			moy += temp;
		}
		moy /= offres.size();
		
		res *= moy;
		
		eval = res;
		return res;
	}
	
	public Double getEval() {
		return eval;
	}
	
	public boolean equals(Offre o){
		return getEval() == o.getEval() && getId() == o.getId();
	}
	
	public GareHoraire getDepart(){
		if(offres.size()==0) return null;
		return offres.get(0).getDepart();
	}
	
	public GareHoraire getArrivee(){
		if(offres.size()==0) return null;
		return offres.get(offres.size()-1).getArrivee();
	}
	
	public void addOffreSimple(OffreSimple o){
		offres.add(o);
	}
	
	public OffreSimple getOffreSimple(int i){
		return offres.get(i);
	}
	
	public List<OffreSimple> getOffres(){
		return Collections.unmodifiableList(offres);
	}
	
	public void addOffreSimple(Train train, SegmentHoraire segment){
		OffreSimple o = new OffreSimple(train,segment);
		offres.add(o);
	}
	
	public int getNbTrains(){
		return offres.size();
	}
	
	public List<Billet> getBillets(){
		ArrayList<Billet> result = new ArrayList<Billet>();
		for(OffreSimple o : offres){
			result.addAll(o.getBillets());
		}
		return result;
	}

	/**
	 * Affichage d'une offre
	 */
	public String toString(){
		String res = "";
		for(OffreSimple o : offres)
			res += "\n" + o + "\n";
		return res;
	}
}