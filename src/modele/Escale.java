package modele;

import defaut.Erreur;

public class Escale {
	public Gare gare;
	public Horaire horaireA;
	public Horaire horaireD;
	
	public Escale(){
		gare = null;
		horaireA = null;
		horaireD = null;
	}
	
	public Escale(Gare g, Horaire hA, Horaire hD) throws Erreur{
		gare = g;
		horaireA = hA;
		horaireD = hD;
		if(!estCoherent()) throw new Erreur(Erreur.INCOHERENCE);
	}
	
	public String toString(){
		return gare + " " + horaireA + " " + horaireD;
	}
	
	/**
	 * L'escale est coherente si horaireA est avant horaireD
	 */
	public boolean estCoherent(){
		if(horaireD == null || horaireA == null) return true;
		else if(horaireA.compareTo(horaireD) > 0) return true;
		else return false;
	}
	
	public GareHoraire getArrivee(){
		return new GareHoraire(gare,horaireA);
	}
	
	public GareHoraire getDepart(){
		return new GareHoraire(gare,horaireD);
	}
}