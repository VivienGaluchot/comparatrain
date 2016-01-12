package modele;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Ville {
	private Integer id;
	private String nom;
	
	public Ville(){
		id = null;
		nom = null;
	}
	
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}