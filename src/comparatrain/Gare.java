/**
 * 
 */
package comparatrain;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Gare{
	protected int id;
	protected String nom;
	protected Ville ville;
	
	public Gare(int i, String n, Ville v){
		id = i;
		nom = n;
		ville = v;
	}
	
	public String toString(){
		return nom;
	}
	
	public double eval(Gare g){
		double res;
		if(ville.id == g.ville.id) res = 1;
		else res = 0;
		return res;
	}
	
	public double eval(String g){
		double res;
		if((nom.compareTo(g) == 0)||(ville.nom.compareTo(g) == 0)) res = 1;
		else res = 0;
		return res;
	}
}
