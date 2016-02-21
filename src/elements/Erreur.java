package elements;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Représentes les différentes erreurs possibles
 */
public class Erreur extends Exception {
	private static final long serialVersionUID = 1L;
	
	public static final int INCOHERENCE = 1;
	public static final int INVALIDE = 2;
	public static final int EXISTE = 3;
	
	int type;
	
	public Erreur(int t){
		type = t;
	}
	
	public int getType(){
		return type;
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
		case EXISTE:
			s = "Entrée existante";
			break;
		default:
			s = "Erreur : " + type;
		}
		return s;
	}
}
