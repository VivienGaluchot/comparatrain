package comparatrain;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Ville {
	int id;
	String nom;
	
	public Ville(int i, String n){
		id = i;
		nom = n;
	}
	
	public String toString(){
		return nom;
	}
}
