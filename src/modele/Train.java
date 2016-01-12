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
		setDepart(null);
		escales = null;	
		setArrivee(null);	
	}
	
	public Train(int i) throws Erreur{
		id = i;
		setDepart(null);
		escales = null;	
		setArrivee(null);	
	}
	
	// Evaluation
	public ArrayList<Segment> eval(Preference pref) {
		ArrayList<Segment> resultat = new ArrayList<Segment>();
		
		Segment s = new Segment(this);
		
		// Gare depart = t.depart
		for(Escale e : escales){
			s.set(depart, e.getArrivee());
			if(s.eval(pref) > 0) resultat.add(s.clone());
		}
		s.set(depart, arrivee);
		if(s.eval(pref) > 0) resultat.add(s.clone());
		
		// Gare depart = escale
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
		s += " " + depart + "\n";
		for(Escale e : escales){
			s += " " + e + "\n";
		}
		s += " " + arrivee + "\n";
		return s;
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

	public void setDepart(GareHoraire depart) {
		this.depart = depart;
	}
	
	// Escales
	public ArrayList<Escale> getEscales() {
		return escales;
	}

	public void setEscales(ArrayList<Escale> escales) {
		this.escales = escales;
	}
	
	public void addEscale(Gare g, Horaire hA, Horaire hD) {
		if(escales == null) escales = new ArrayList<Escale>();
		escales.add(new Escale(g,hA,hD));
	}
	
	// Arrivee
	public GareHoraire getArrivee() {
		return arrivee;
	}

	public void setArrivee(GareHoraire arrivee) {
		this.arrivee = arrivee;
	}
}