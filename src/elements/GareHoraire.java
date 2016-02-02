package elements;

import java.time.Duration;

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
	public boolean isConnectedTo(GareHoraire o){
		Duration delta = gare.isConnectedTo(o.gare);
		if(delta == null) return false;		
		return horaire.isConnectedTo(o.horaire, delta);
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