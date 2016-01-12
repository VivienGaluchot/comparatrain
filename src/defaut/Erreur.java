/**
 * 
 */
package defaut;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Erreur extends Exception {
	private static final long serialVersionUID = 1L;
	
	public static final int INCOHERENCE = 1;
	public static final int INVALIDE = 2;
	
	int type;
	
	public Erreur(int t){
		type = t;
	}
	
	public String toString(){
		String s = "";
		switch(type){
		case INCOHERENCE:
			s = "Données incohérentes";
			break;
		case INVALIDE:
			s = "Entrée invalide";
			break;
		default:
			s = "Erreur : " + type;
		}
		return s;
	}
}
