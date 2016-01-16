/**
 * 
 */
package comparaison;

import java.util.ArrayList;

import defaut.Donnees;
import modele.Train;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * utilise le modele pour comparer la base de donnée avec le modele
 */
public class Comparateur {
	
	private Donnees data;
	
	public Comparateur(){
		data = new Donnees();
	}
	
	public Comparateur(Donnees d){
		data = d;
	}
	
	/**
	 * @param pref : Preference avec lesquelles effectuer la comparaison des offres
	 */
	public Resultat comparer(Preference pref){
		Resultat evaluations = new Resultat();
		
		for(Train t : data.getTrains()){
			ArrayList<Offre> e = t.eval(pref);
			for(Offre s : e)
				evaluations.ajouter(s);
		}
		return evaluations;
	}
	
	public Donnees getData(){
		return data;
	}
}
