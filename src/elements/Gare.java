package elements;

import java.time.Duration;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Représentation d'une Gare
 */
public class Gare extends Indexable implements Evaluable<Gare>{
	private String nom;
	private Ville ville;
	
	public Gare(){
		nom = null;
		ville = null;
	}
	
	public Gare(int i, String n, Ville v){
		setId(i);
		setNom(n);
		setVille(v);
	}
	
	public String toString(){
		return getNom();
	}
	
	public double eval(Gare g){
		double res;
		if(getVille().getId() == g.getVille().getId()) res = 1;
		else res = 0;
		return res;
	}
	
	public double eval(String g){
		double res;
		if(getNom().compareTo(g) == 0) res = 1;
		else if(getVille().getNom().compareTo(g) == 0) res = 0.8;
		else res = 0;
		return res;
	}
	
	/**
	 * Utilisé pour faire le test de correspondance.
	 * @return le temps nécéssaire au trajet entre les gares, s'il est envisageable pour une correspondance
	 * null sinon
	 */
	public Duration isConnectableTo(Gare g){
		if(this.equals(g))
			return Duration.ofMinutes(3);
		if(this.getVille().equals(g.getVille()))
			return Duration.ofMinutes(15);
		return null;
	}

	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }

	public Ville getVille() { return ville; }
	public void setVille(Ville ville) { this.ville = ville; }
}
