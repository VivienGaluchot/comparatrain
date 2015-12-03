/**
 * 
 */
package comparatrain;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Gare implements Evaluable{
	protected int id;
	protected String ville;
	
	public Gare(int i, String v){
		id = i;
		ville=v;
	}
	
	public String toString(){
		return ville;
	}
	
	public int eval(Preference pref){
		return 0;
	}
}
