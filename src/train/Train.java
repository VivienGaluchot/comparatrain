/**
 * 
 */
package train;

import java.util.ArrayList;

import defaut.Erreur;

import elements.Escale;
import elements.Gare;
import elements.GareHoraire;
import elements.Horaire;
import elements.Identified;
import elements.Segment;
import elements.SegmentHoraire;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Classe de train
 */
public class Train extends Identified{	
	private GareHoraire depart;
	private ArrayList<Escale> escales;
	private GareHoraire arrivee;
	
	//Utilisé pour optimiser les recherches
	private ArrayList<Gare> gares;
	
	// Constructeurs	
	public Train(){
		depart = null;
		escales = null;	
		arrivee = null;	
		gares = null;
	}
	
	public Train(Integer i){
		setId(i);
		depart = null;
		escales = null;	
		arrivee = null;
		gares = null;
	}
	
	public Train (Integer i, GareHoraire d, ArrayList<Escale> e, GareHoraire a) throws Erreur{
		setId(i);
		depart = d;
		escales = e;	
		arrivee = a;
		if(!estCoherent()) throw new Erreur(Erreur.INCOHERENCE);
		updateGares();
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
		// Si le départ est après l'arrivee de la premiere escale
		if(depart != null && escales != null && escales.size()>0)
			if(depart.compareTo(escales.get(0).getArrivee())>=0) return false;
		
		// Si l'une des escale est après celle d'après
		if(escales != null)
			for(int i=0;i<escales.size()-1;i++){
				if(escales.get(i).compareTo(escales.get(i+1))>=0) return false;
				if(!escales.get(i).estCoherent()) return false;
			}
		
		// Si l'arrivee est avant le départ de la derniere escale
		if(arrivee != null && escales != null && escales.size()>0)			
			if(arrivee.compareTo(escales.get(escales.size()-1).getDepart())<=0) return false;
		
		// Si le depart est après l'arrivee
		if(depart != null && arrivee != null)
			if(depart.compareTo(arrivee) >= 0) return false;
		
		return true;
	}
	
	/**
	 * Retourne de couple gareHoraire du trajet i-j
	 */
	public SegmentHoraire getSegmentHoraire(int i, int j){
		if(i<0 || j<=i || nbStop()<=j) return null;
		GareHoraire depart = null;
		GareHoraire arrivee = null;
		
		if(i == 0)
			depart = this.depart;
		else
			depart = this.escales.get(i-1).getDepart();
		
		if(j == nbStop()-1)
			arrivee = this.arrivee;
		else
			arrivee = this.escales.get(j-1).getArrivee();
		
		return new SegmentHoraire(depart,arrivee);
	}
	
	public SegmentHoraire trouver(Segment s){
		int i = -1;
		int j = -1;
		for(int x=0;x<gares.size();x++){
			if(gares.get(x).getId() == s.depart.getId()) i = x;
			if(gares.get(x).getId() == s.arrivee.getId()) j = x;
		}
		
		return getSegmentHoraire(i,j);
	}
	
	public boolean dessert(Segment s){
		int i = -1;
		int j = -1;
		for(int x=0;x<gares.size();x++){
			if(gares.get(x).getId() == s.depart.getId()) i = x;
			if(gares.get(x).getId() == s.arrivee.getId()) j = x;
		}
		return i>-1 && j>i;
	}
	
	public Place getPlace(){
		// A FAIRE
		return null;
	}
	
	public int nbStop(){
		return 2 + escales.size();
	}
		
	// Depart
	public GareHoraire getDepart() {
		return depart;
	}

	public void setDepart(GareHoraire depart) throws Erreur{
		this.depart = depart;
		if(!estCoherent()) throw new Erreur(Erreur.INCOHERENCE);
		updateGares();
	}
	
	// Escales
	public ArrayList<Escale> getEscales() {
		return escales;
	}
	
	public Escale getEscale(int i){
		return escales.get(i);
	}
	
	public GareHoraire getEscaleArrivee(int i){
		return escales.get(i).getArrivee();
	}
	
	public GareHoraire getEscaleDepart(int i){
		return escales.get(i).getDepart();
	}

	public void setEscales(ArrayList<Escale> escales) throws Erreur{
		this.escales = escales;
		if(!estCoherent()) throw new Erreur(Erreur.INCOHERENCE);
		updateGares();
	}
	
	public void addEscale(Gare g, Horaire hA, Horaire hD) throws Erreur{
		if(escales == null) escales = new ArrayList<Escale>();
		escales.add(new Escale(g,hA,hD));
		if(!estCoherent()) throw new Erreur(Erreur.INCOHERENCE);
		updateGares();
	}
	
	// Arrivee
	public GareHoraire getArrivee() {
		return arrivee;
	}

	public void setArrivee(GareHoraire arrivee) throws Erreur{
		this.arrivee = arrivee;
		if(!estCoherent()) throw new Erreur(Erreur.INCOHERENCE);
		updateGares();
	}
	
	// Gares
	public void updateGares(){
		gares = new ArrayList<Gare>();
		if(depart != null)
			gares.add(depart.gare);
		if(escales != null)
			for(Escale e : escales)
				gares.add(e.gare);
		if(arrivee != null)
			gares.add(arrivee.gare);
	}
	
	public ArrayList<Gare> getGares(){		
		return gares;
	}
}