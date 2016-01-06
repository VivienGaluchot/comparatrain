/**
 * 
 */
package comparatrain;

import java.util.ArrayList;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Objet evaluant les offres
 */
public class Comparateur {
	
	Donnees data;
	
	public Comparateur(){
		data = new Donnees();
		data.charger("database2");
		System.out.println("Chargement des données effectué");
		data.sauvegarder("database2");
	}
	
	public Comparateur(Donnees d){
		data = d;
	}
	
	/**
	 * @param pref : Preference avec lesquelles effectuer la comparaison des offres
	 * Tout les trains de la liste ListeTrain sont évaluées, les scores et les trains sont dans une TreeMap
	 */
	public Resultat comparer(Preference pref){
		Resultat evaluations = new Resultat();
		
		for(Train t : data.trains){
			ArrayList<Segment> e = t.eval(pref);
			for(Segment s : e)
				evaluations.ajouter(s.eval, s);
		}
		return evaluations;
	}
}
