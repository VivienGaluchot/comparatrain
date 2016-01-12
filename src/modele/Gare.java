/**
 * 
 */
package modele;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Gare implements Evaluable<Gare>, Comparable<Gare>{
	private int id;
	private String nom;
	private Ville ville;
	
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	@Override
	public int compareTo(Gare o) {
		return nom.compareTo(o.nom);
	}
}
