/**
 * 
 */
package train;

import java.util.ArrayList;

import defaut.Erreur;

import elements.Escale;
import elements.GareHoraire;
import elements.Indexable;
import elements.SegmentHoraire;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Classe de train
 */
public class Train extends Indexable{	
	private GareHoraire depart;
	private ArrayList<Escale> escales;
	private GareHoraire arrivee;
	
	private Rame rame;
	
	// Constructeurs	
	public Train(){
		depart = null;
		escales = null;
		arrivee = null;
		rame = null;
	}
	
	public Train(Integer i){
		setId(i);
		depart = null;
		escales = null;	
		arrivee = null;
		rame = null;
	}
	
	public Train (Integer i, GareHoraire d, ArrayList<Escale> e, GareHoraire a, Rame r) throws Erreur{
		setId(i);
		depart = d;
		escales = e;
		arrivee = a;
		rame = r;
		if(!estCoherent()) throw new Erreur(Erreur.INCOHERENCE);
	}
	
	// Utilitaire
	public String toString(){
		String s = "Non initialise";
		if(depart != null && arrivee != null)
		s = depart + " --> " + arrivee + "\n";
		return s;
	}
	
	/**
	 * Un train est coherent si ses escales sont cohérentes,
	 * et si l'odre chronologique depart - escales - arrivee est respecté
	 */
	public boolean estCoherent(){
		// Si le départ est après l'arrivee de la premiere escale -> FALSE
		if(depart != null && escales != null && escales.size()>0)
			if(depart.compareTo(escales.get(0).getArrivee())>=0) return false;
		
		// Si l'odre des escales est non valide -> FALSE
		if(escales != null)
			for(int i=0;i<escales.size()-1;i++){
				if(!escales.get(i).estCoherent()) return false;
				if(escales.get(i).compareTo(escales.get(i+1))>=0) return false;
			}
		
		// Si l'arrivee est avant le départ de la derniere escale -> FALSE
		if(arrivee != null && escales != null && escales.size()>0)			
			if(arrivee.compareTo(escales.get(escales.size()-1).getDepart())<=0) return false;
		
		// Si le depart est après l'arrivee -> FALSE
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
		
		return new SegmentHoraire(this,depart,arrivee);
	}
	
	public int nbStop(){
		if(escales == null) return 2;
		return 2 + escales.size();
	}
	
	// Depart
	public GareHoraire getDepart() { return depart; }

	public void setDepart(GareHoraire depart) throws Erreur{
		new Train(null,depart,escales,arrivee,null);
		this.depart = depart;
	}
	
	// Escales
	public ArrayList<Escale> getEscales() { return escales; }

	public void setEscales(ArrayList<Escale> escales) throws Erreur{
		new Train(null,depart,escales,arrivee,null);
		this.escales = escales;
	}
	
	// Arrivee
	public GareHoraire getArrivee() { return arrivee; }

	public void setArrivee(GareHoraire arrivee) throws Erreur{
		new Train(null,depart,escales,arrivee,null);
		this.arrivee = arrivee;
	}
	
	// Rame
	public Rame getRame() { return rame; }
	
	public void setRame(Rame rame) { this.rame = rame; }
}