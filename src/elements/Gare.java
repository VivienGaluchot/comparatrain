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
		// Meme gare
		if(getId() == g.getId())
			return 1;
		// Gares dans la meme Ville
		if(getVille().getId() == g.getVille().getId())
			return 0.8;
		return 0;
	}
	
	public double eval(String g){
		// Nom de la gare
		if(getNom().compareTo(g) == 0) return 1;
		// Nom de la ville
		else if(getVille().getNom().compareTo(g) == 0) return 0.8;
		// Nom d'une gare de la ville
		for(Gare gare : getVille().getGares()){
			if(gare != this && gare.getNom().compareTo(g) == 0)
				return 0.6;
		}
		return 0;
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
	public void setVille(Ville ville) {
		this.ville = ville;
		ville.addToGares(this);
	}
}
