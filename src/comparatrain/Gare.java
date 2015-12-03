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
	
	public Gare(String v){
		ville=v;
	}
	
	public int eval(){
		return 0;
	}
}
