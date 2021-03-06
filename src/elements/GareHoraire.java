package elements;

import java.time.Duration;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Est composé d'une gare et d'une horaire
 */
public class GareHoraire implements Comparable<GareHoraire>, Evaluable<GareHoraire> {
	public Gare gare;
	public Horaire horaire;
	
	public GareHoraire(){
		gare = null;
		horaire = null;
	}
	
	public GareHoraire(Gare g, Horaire h){
		gare = g;
		horaire = h;
	}
	
	public String toString(){
		return gare + " " + horaire;
	}
	
	public int compareTo(GareHoraire o){
		return horaire.compareTo(o.horaire);
	}
	
	/**
	 * Est connecté si est avant (o.horaire+durée d'escale),
	 * moins de 5h avant o.horaire
	 * meme gare de o
	 */
	public boolean isConnectableTo(GareHoraire o){
		Duration delta = gare.isConnectableTo(o.gare);
		if(delta == null) return false;		
		return horaire.isConnectableTo(o.horaire, delta);
	}

	public double eval(GareHoraire o) {
		Double res = 1.;
		// Lieu
		res *= gare.eval(o.gare);
		if(res == 0) return res;
		// Date)
		res *= horaire.eval(o.horaire);
		return res;
	}
	
	public double eval(String gareStr, Horaire h) {
		Double res = 1.;
		// Lieu
		res *= gare.eval(gareStr);
		if(res == 0) return res;
		// Date)
		if(h!=null)
			res *= horaire.eval(h);
		return res;
	}
	
	public boolean equals(GareHoraire h){
		return gare.equals(h.gare) && horaire.equals(h.horaire);
	}
}