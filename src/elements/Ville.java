package elements;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Représentation d'une Ville
 */
public class Ville extends Indexable{
	private String nom;
	
	public Ville(){
		nom = null;
	}
	
	public Ville(int i, String n){
		setId(i);
		setNom(n);
	}
	
	public String toString(){
		return getNom();
	}

	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }
}