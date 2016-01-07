package modele;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Ville {
	private int id;
	private String nom;
	
	public Ville(int i, String n){
		setId(i);
		setNom(n);
	}
	
	public String toString(){
		return getNom();
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
}
