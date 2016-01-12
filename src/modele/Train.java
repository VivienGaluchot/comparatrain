/**
 * 
 */
package modele;

import java.util.ArrayList;

import comparaison.Preference;
import defaut.Erreur;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Classe de train
 */
public class Train{
	
	protected Integer id;
	
	private GareHoraire depart;
	private ArrayList<Escale> escales;
	private GareHoraire arrivee;
	
	// Constructeurs	
	public Train(){
		id = null;
		depart = null;
		escales = null;	
		arrivee = null;	
	}
	
	public Train(Integer i){
		id = i;
		depart = null;
		escales = null;	
		arrivee = null;	
	}
	
	public Train (Integer i, GareHoraire d, ArrayList<Escale> e, GareHoraire a) throws Erreur{
		id = i;
		depart = d;
		escales = e;	
		arrivee = a;
		if(!estCoherent()) throw new Erreur(Erreur.INCOHERENCE);
	}
	
	// Evaluation
	public ArrayList<Offre> eval(Preference pref) {
		ArrayList<Offre> resultat = new ArrayList<Offre>();
		Offre s = new Offre(this);
		
		for(Escale e : escales){
			s.set(depart, e.getArrivee());
			if(s.eval(pref) > 0) resultat.add(s.clone());
		}
		s.set(depart, arrivee);
		if(s.eval(pref) > 0) resultat.add(s.clone());
		
		for(int i=0;i<escales.size();i++){
			for(int j=i+1;j<escales.size();j++){
				s.set(escales.get(i).getDepart(), escales.get(j).getArrivee());
				if(s.eval(pref) > 0) resultat.add(s.clone());
			}
			s.set(escales.get(i).getDepart(), arrivee);
			if(s.eval(pref) > 0) resultat.add(s.clone());
		}
		
		return resultat;
	}
	
	// Utilitaire
	public String toString(){
		String s = "Train n°" + getId() + "\n";
		s += depart + "\n";
		for(Escale e : escales){
			s += e + "\n";
		}
		s += arrivee + "\n";
		return s;
	}
	
	/**
	 * Un train est coherent si ses escales sont cohérentes,
	 * et si l'odre chronologique depart - escales - arrivee est respecté
	 */
	public boolean estCoherent(){
		// Non init
		if(depart == null && escales == null && arrivee == null) return true;
		// Seulement l'arrivee est initialisée
		else if(depart == null && escales == null) return true;
		// Seulement le départ est initialisée
		else if(escales == null && arrivee == null) return true;
		
		else if(escales == null) return arrivee.compareTo(depart)>0;
		
		// PAS FINI
		return true;
	}
	
	// Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
		
	// Depart
	public GareHoraire getDepart() {
		return depart;
	}

	public void setDepart(GareHoraire depart) throws Erreur{
		this.depart = depart;
		if(!estCoherent()) throw new Erreur(Erreur.INCOHERENCE);
	}
	
	// Escales
	public ArrayList<Escale> getEscales() {
		return escales;
	}

	public void setEscales(ArrayList<Escale> escales) throws Erreur{
		this.escales = escales;
		if(!estCoherent()) throw new Erreur(Erreur.INCOHERENCE);
	}
	
	public void addEscale(Gare g, Horaire hA, Horaire hD) throws Erreur{
		if(escales == null) escales = new ArrayList<Escale>();
		escales.add(new Escale(g,hA,hD));
	}
	
	// Arrivee
	public GareHoraire getArrivee() {
		return arrivee;
	}

	public void setArrivee(GareHoraire arrivee) throws Erreur{
		this.arrivee = arrivee;
		if(!estCoherent()) throw new Erreur(Erreur.INCOHERENCE);
	}
}