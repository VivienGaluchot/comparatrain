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
	
	Trajet t;
	
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
		t = new Trajet(new Depart(gDep,hDep), new Arrive(gArr,hArr));
		
		places = new ArrayDeque<Siege>();
		for(int j=0;j<50;j++){
			places.add(new Siege(j));
		}
	}
	
	public Train(int i,Trajet trajet) throws Erreur{
		//intégrité des données ?
		id = i;
		t = trajet;
		
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
		s += " " + t.depart.g + " " + t.depart.h + "\n";
		for(Escale es : t.escales){
			s += " " + es.g + " " + es.hA + " " + es.hD + "\n";
		}
		s += " " + t.arrive.g + " " + t.arrive.h + "\n";
		return s;
	}
	
	/**
	 * @param pref object contenant les préférences permetant d'effectuer une evaluation
	 */
	public ArrayList<Segment> eval(Preference pref) {
		ArrayList<Segment> resultat = new ArrayList<Segment>();
		
		Segment s = new Segment(this);
		
		// Gare depart = t.depart
		for(Escale e : t.escales){
			s.set(t.depart.g, e.g, t.depart.h, e.hA);
			if(s.eval(pref) > 0) resultat.add(s.clone());
		}
		s.set(t.depart.g, t.arrive.g, t.depart.h, t.arrive.h);
		if(s.eval(pref) > 0) resultat.add(s.clone());
		
		// Gare depart = escale
		for(int i=0;i<t.escales.size();i++){
			for(int j=i+1;j<t.escales.size();j++){
				s.set(t.escales.get(i).g, t.escales.get(j).g, t.escales.get(i).hD, t.escales.get(j).hA);
				if(s.eval(pref) > 0) resultat.add(s.clone());
			}
			s.set(t.escales.get(i).g, t.arrive.g, t.escales.get(i).hD, t.arrive.h);
			if(s.eval(pref) > 0) resultat.add(s.clone());
		}
		
		return resultat;
	}
}

class Segment implements Evaluable{
	Gare d;
	Gare a;
	Horaire hD;
	Horaire hA;
	
	Train t;
	
	Double eval;
	
	public Segment(Train train){
		t = train;
	}
	
	public void set(Gare depart, Gare arrive, Horaire hDepart, Horaire hArrive){
		a = arrive;
		d = depart;
		hD = hDepart;
		hA = hArrive;
	}
	
	public double eval(Preference pref) {
		double res = 1;
		
		// Lieux
		res *= d.eval(pref.gDepart);
		if(res == 0) return res;
		res *= a.eval(pref.gArrive);
		if(res == 0) return res;
		
		// Dates
		if(pref.hDepart != null)
			res *= hD.eval(pref.hDepart);
		if(res == 0) return res;
		if(pref.hArrive != null)
			res *= hA.eval(pref.hArrive);
		
		eval = res;
		
		return res;
	}
	
	public Segment clone(){
		Segment s = new Segment(t);
		s.set(d, a, hD, hA);
		s.eval = eval;
		return s;
	}
	
	/**
	 * Affichage d'un segment
	 */
	public String toString(){
		return "Train n°" + t.id + " : " + d + " " + hD + " --> " + a + " " + hA;
	}
}