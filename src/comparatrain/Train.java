/**
 * 
 */
package comparatrain;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Classe de train
 */
public class Train{
	int id;
	
	Trajet trajet;
	
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
		trajet = new Trajet(new Depart(gDep,hDep), new Arrivee(gArr,hArr));
		
		places = new ArrayDeque<Siege>();
		for(int j=0;j<50;j++){
			places.add(new Siege(j));
		}
	}
	
	public Train(int i,Trajet t) throws Erreur{
		//intégrité des données ?
		id = i;
		trajet = t;
		
		places = new ArrayDeque<Siege>();
		for(int j=0;j<50;j++){
			places.add(new Siege(j));
		}
	}
	
	/**
	 * Affichage d'un train
	 */
	public String toString(){
		String s = "Train n°" + id + "\n";
		s += " " + trajet.depart.gare + " " + trajet.depart.horaire + "\n";
		for(Escale es : trajet.escales){
			s += " " + es.gare + " " + es.horaireA + " " + es.horaireD + "\n";
		}
		s += " " + trajet.arrivee.gare + " " + trajet.arrivee.horaire + "\n";
		return s;
	}
	
	/**
	 * @param pref object contenant les préférences permetant d'effectuer une evaluation
	 */
	public ArrayList<Segment> eval(Preference pref) {
		ArrayList<Segment> resultat = new ArrayList<Segment>();
		
		Segment s = new Segment(this);
		
		// Gare depart = t.depart
		for(Escale e : trajet.escales){
			s.set(trajet.depart.gare, e.gare, trajet.depart.horaire, e.horaireA);
			if(s.eval(pref) > 0) resultat.add(s.clone());
		}
		s.set(trajet.depart.gare, trajet.arrivee.gare, trajet.depart.horaire, trajet.arrivee.horaire);
		if(s.eval(pref) > 0) resultat.add(s.clone());
		
		// Gare depart = escale
		for(int i=0;i<trajet.escales.size();i++){
			for(int j=i+1;j<trajet.escales.size();j++){
				s.set(trajet.escales.get(i).gare, trajet.escales.get(j).gare, trajet.escales.get(i).horaireD, trajet.escales.get(j).horaireA);
				if(s.eval(pref) > 0) resultat.add(s.clone());
			}
			s.set(trajet.escales.get(i).gare, trajet.arrivee.gare, trajet.escales.get(i).horaireD, trajet.arrivee.horaire);
			if(s.eval(pref) > 0) resultat.add(s.clone());
		}
		
		return resultat;
	}
}