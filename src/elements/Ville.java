package elements;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Représentation d'une Ville
 */
public class Ville extends Indexable{
	private String nom;
	private ArrayList<Gare> gares;
	
	public Ville(){
		gares = new ArrayList<Gare>();
		nom = null;
	}
	
	public Ville(int i, String n){
		setId(i);
		setNom(n);
		gares = new ArrayList<Gare>();
	}
	
	public void addToGares(Gare g){
		if(!gares.contains(g))
			gares.add(g);
	}
	
	public void removeFromGares(Gare g){
		gares.remove(g);
	}
	
	public boolean isInGares(Gare g){
		return gares.contains(g);
	}
	
	public List<Gare> getGares() { return gares; }
	
	public String toString(){
		return getNom();
	}
	
	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }
}