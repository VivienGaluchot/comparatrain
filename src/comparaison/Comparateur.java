/**
 * 
 */
package comparaison;

import java.util.ArrayList;

import comparatrain.Donnees;
import comparatrain.Preference;
import modele.Train;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Objet evaluant les offres
 */
public class Comparateur {
	
	Donnees data;
	
	public Comparateur(){
		data = new Donnees();
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
		
		for(Train t : data.getTrains()){
			ArrayList<Segment> e = t.eval(pref);
			for(Segment s : e)
				evaluations.ajouter(s.eval, s);
		}
		return evaluations;
	}
}
