package utilisateur;

import elements.Indexable;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Représente une personne physique
 */
public class Personne extends Indexable{
	private String prenom;
	private String nom;
	
	public Personne(){
		prenom = null;
		nom = null;
	}
	
	public Personne(String prenom, String nom){
		this.prenom = prenom;
		this.nom = nom;
	}
	
	public String toString(){
		String res = prenom + " " + nom;
		return res;
	}
	
	// Prénom
	public String getPrenom(){
		return prenom;
	}
	
	public void setPrenom(String prenom){
		this.prenom =  prenom;
	}
	
	// Nom
	public String getNom(){
		return nom;
	}
	
	public void setNom(String nom){
		this.nom = nom;
	}
}
