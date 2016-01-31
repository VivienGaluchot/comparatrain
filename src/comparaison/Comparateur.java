/**
 * 
 */
package comparaison;

import java.util.ArrayList;

import defaut.Donnees;
import modele.Gare;
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
		
		ArrayList<Offre> e = trouverOffre(pref);
		
		for(Offre o : e){
			if(o.eval(pref) > 0)			
				resultat.ajouter(o);
		}
		
		return resultat;
	}
	
	// En attendant
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
	
	public ArrayList<Offre> trouverOffre(Preference pref) {
		ArrayList<Offre> resultat = new ArrayList<Offre>();
		
		ArrayList<Gare> garesD = data.getGares(pref.getGDepart());
		ArrayList<Gare> garesA = data.getGares(pref.getGArrivee());
		
		for(Gare gD : garesD)
			for(Gare gA : garesA)
				resultat.addAll(trouverOffre(gD,gA,pref.direct));
		
		return resultat;
	}
	
	public ArrayList<Offre> trouverOffre(Gare depart, Gare arrivee, Boolean direct){
		ArrayList<Offre> resultat = new ArrayList<Offre>();
		
		// OffreSimple		
		for(Train t : data.getTrains()){
			OffreSimple o = t.offreSimpleTrajet(depart, arrivee);
			if(o != null) resultat.add(o);
		}
		
		// OffreMultiple
		if(direct==null || !direct){
			// A FAIRE
		}
		
		return resultat;
	}
	
	public ArrayList<Offre> trouverOffreBacktrack(ArrayList<Train> trains, Gare depart, Gare arrivee){
		// A FAIRE
		
		return null;
	}
	
	public Donnees getData(){
		return data;
	}
}
