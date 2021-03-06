package elements;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Lorsqu'un train arrive dans une gare a une certaine horaire et y repart plus tard,
 * il y fait une Escale
 */
public class Escale implements Comparable<Escale>{
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
		return gare + " : " + horaireA.getHeure() + " " + horaireD.getHeure();
	}
	
	/**
	 * L'escale est coherente si horaireA est strictement avant horaireD
	 */
	public boolean estCoherent(){
		if(horaireD == null || horaireA == null) return true;
		if(horaireA.compareTo(horaireD) < 0) return true;
		return false;
	}
	
	public GareHoraire getArrivee(){
		return new GareHoraire(gare,horaireA);
	}
	
	public GareHoraire getDepart(){
		return new GareHoraire(gare,horaireD);
	}
	
	/**
	 * Return > 0 si son depart est après l'arrivee de o
	 */
	public int compareTo(Escale o) {
		return horaireD.compareTo(o.horaireA);
	}
}