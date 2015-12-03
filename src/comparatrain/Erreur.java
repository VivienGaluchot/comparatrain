/**
 * 
 */
package comparatrain;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Erreur extends Exception {
	private static final long serialVersionUID = 1L;
	
	int type;
	
	public Erreur(int t){
		type = t;
	}
	
	public String toString(){
		String s = "";
		switch(type){
		case 1:
			s = "Données incohérentes";
			break;
		default:
			s = "Erreur " + type;
		}
		return s;
	}
}
