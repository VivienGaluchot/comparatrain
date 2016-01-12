/**
 * 
 */
package modele;

import java.util.ArrayDeque;
import java.util.ArrayList;

import comparaison.Preference;
import comparaison.Segment;
import defaut.Erreur;
import modele.Trajet.Escale;
import modele.physique.Siege;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Classe de train
 */
public class Train{
	
	protected Integer id;
	protected Trajet trajet;
	
	public Train(){
		id = null;
		trajet = null;
	}
	/**
	 * Constructeur de train
	 * La cohérence des paramètre sera vérifiée à la création de l'objet, la fonction retourne
	 * 1 si tout est valide, 0 en cas d'érreur
	 */
	public Train(int i,Gare gDep,Gare gArr,Horaire hDep,Horaire hArr) throws Erreur{
		//intégrité des données
		if((gDep.getId() == gArr.getId()) || (hDep.compareTo(hArr)>0))
			throw new Erreur(1);
		setId(i);
		trajet = new Trajet(gDep,hDep,gArr,hArr);
	}
	
	public Train(int i,Trajet t) throws Erreur{
		//intégrité des données ?
		setId(i);
		trajet = t;
	}
	
	public String toString(){
		String s = "Train n°" + getId() + "\n";
		s += " " + trajet.getDepart().gare + " " + trajet.getDepart().horaire + "\n";
		for(Escale es : trajet.getEscales()){
			s += " " + es.gare + " " + es.horaireA + " " + es.horaireD + "\n";
		}
		s += " " + trajet.getArrivee().gare + " " + trajet.getArrivee().horaire + "\n";
		return s;
	}
	
	public ArrayList<Segment> eval(Preference pref) {
		ArrayList<Segment> resultat = new ArrayList<Segment>();
		
		Segment s = new Segment(this);
		
		// Gare depart = t.depart
		for(Escale e : trajet.getEscales()){
			s.set(trajet.getDepart().gare, e.gare, trajet.getDepart().horaire, e.horaireA);
			if(s.eval(pref) > 0) resultat.add(s.clone());
		}
		s.set(trajet.getDepart().gare, trajet.getArrivee().gare, trajet.getDepart().horaire, trajet.getArrivee().horaire);
		if(s.eval(pref) > 0) resultat.add(s.clone());
		
		// Gare depart = escale
		for(int i=0;i<trajet.getEscales().size();i++){
			for(int j=i+1;j<trajet.getEscales().size();j++){
				s.set(trajet.getEscales().get(i).gare, trajet.getEscales().get(j).gare, trajet.getEscales().get(i).horaireD, trajet.getEscales().get(j).horaireA);
				if(s.eval(pref) > 0) resultat.add(s.clone());
			}
			s.set(trajet.getEscales().get(i).gare, trajet.getArrivee().gare, trajet.getEscales().get(i).horaireD, trajet.getArrivee().horaire);
			if(s.eval(pref) > 0) resultat.add(s.clone());
		}
		
		return resultat;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Trajet getTrajet(){
		return trajet;
	}
	
	public void setTrajet( Trajet trajet){
		this.trajet = trajet;
	}
}