/**
 * 
 */
package comparatrain;

import java.util.ArrayDeque;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Classe de train
 */
public class Train implements Evaluable{
	int id;
	Gare gDepart;
	Gare gArrive;
	Horaire hDepart;
	Horaire hArrive;
	
	ArrayDeque<Siege> places;
	
	/**
	 * Constructeur de train
	 * @param i numéro du train
	 * @param gDep gare de départ
	 * @param gArr gare d'arrivé
	 * @param tDep date de départ
	 * @param tArr date d'arrivé
	 * La cohérence des paramètre sera vérifiée à la création de l'objet, la fonction retourne
	 * 1 si tout est valide, 0 en cas d'érreur
	 */
	public Train(int i,Gare gDep,Gare gArr,Horaire hDep,Horaire hArr) throws Erreur{
		//intégrité des données
		if((gDep.id == gArr.id) || (hDep.compareTo(hArr)>0))
			throw new Erreur(1);
		id = i;
		gDepart = gDep;
		gArrive = gArr;
		hDepart = hDep;
		hArrive = hArr;
		places = new ArrayDeque<Siege>();
		for(int j=0;j<50;j++){
			places.add(new Siege(j));
		}
	}
	
	/**
	 * Affichage d'un train
	 */
	public String toString(){
		return "Train n°" + id + " : " + gDepart + " " + hDepart + " --> " + gArrive + " " + hArrive;
	}
	
	/**
	 * @param pref object contenant les préférences permetant d'effectuer une evaluation
	 */
	public double eval(Preference pref) {
		double res = 1;
		
		// Lieux
		res *= gDepart.eval(pref.gDepart);
		if(res == 0) return res;
		res *= gArrive.eval(pref.gArrive);
		if(res == 0) return res;
		
		// Dates
		if(pref.hDepart != null)
			res *= hDepart.eval(pref.hDepart);
		if(res == 0) return res;
		if(pref.hArrive != null)
			res *= hArrive.eval(pref.hArrive);
		
		return res;
	}
}
