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
	
	public Resultat comparer(Preference pref){
		Resultat resultat = new Resultat();
		
		ArrayList<OffreSimple> e = trouverOffreSimple(pref);
		
		for(OffreSimple o : e)
			resultat.ajouter(o);
			
		return resultat;
	}
	
	public ArrayList<Offre> trouverOffre(Preference pref) {
		ArrayList<Offre> resultat = new ArrayList<Offre>();
		
		// A FAIRE
		
		return resultat;
	}
	
	public ArrayList<OffreSimple> trouverOffreSimple(Preference pref){
		ArrayList<OffreSimple> resultat = new ArrayList<OffreSimple>();
		
		for(Train t : data.getTrains()){
			for(int i=0;i<t.nbStop();i++){
				for(int j=i+1;j<t.nbStop();j++){
					OffreSimple offre = t.parcourOffre(i, j);
					if(offre.eval(pref) > 0) resultat.add(offre.clone());
				}
			}
		}
			
		return resultat;
	}
	
	public Donnees getData(){
		return data;
	}
}
