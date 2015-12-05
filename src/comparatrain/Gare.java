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
	protected Ville ville;
	
	public Gare(int i, Ville v){
		id = i;
		ville = v;
	}
	
	public String toString(){
		return "Gare de " + ville;
	}
	
	public double eval(Gare g){
		double res;
		if(ville.id == g.ville.id) res = 1;
		else res = 0;
		return res;
	}
}
